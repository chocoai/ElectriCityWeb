package com.ikkong.dg.android;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.ikkong.common.constant.CommonConts;
import com.ikkong.common.constant.CommonConts.ItemStatus;
import com.ikkong.common.constant.CommonConts.ProgressStatus;
import com.ikkong.core.base.BaseController;
import com.ikkong.core.dao.Blade;
import com.ikkong.core.dao.Db;
import com.ikkong.core.toolbox.Record;
import com.ikkong.core.toolbox.support.BladePage;
import com.ikkong.dg.model.OrderProgress;
import com.ikkong.dg.model.WorkOrder;
import com.ikkong.dg.service.OrderProgressService;
import com.ikkong.dg.service.WorkOrderService;
import com.ikkong.dg.service.impl.OrderProgressServiceImpl;
import com.ikkong.dg.service.impl.WorkOrderServiceImpl;

/**
 * 进度API
 * 
 * @author sparker
 *
 */
public class ProgressController extends BaseController {

	private static int pageSize = CommonConts.CLIENT_PAGE_SIZE;

	private static final String SQL_LIST = "OrderProgress.findList";

	private OrderProgressService service = new OrderProgressServiceImpl();

	private WorkOrderService workOrdServ = new WorkOrderServiceImpl();

	public void add() {// 新增
		Long workOrderId = getParaToLong("workOrderId");
		String location = getPara("location");// 坐标地址
		String longitude = getPara("longitude");// 经度
		String latitude = getPara("latitude");// 纬度
//		Integer status = getParaToInt("status");
		String mark = getPara("mark");

		if (workOrderId == null || workOrderId <= 0) {
			renderJson(warn("请提供工种子项目!"));
			return;
		}

		Long dgId = dgUserToken().getUserId();
		OrderProgress progress = new OrderProgress();
		progress.setCreate_id(dgId);
		if (StringUtils.isNotBlank(longitude)) {
			progress.setLongitude(Double.valueOf(longitude));
		}
		if (StringUtils.isNotBlank(latitude)) {
			progress.setLatitude(Double.valueOf(latitude));
		}

		progress.setMark(mark);
		progress.setStatus(ProgressStatus.SUBMIT.getStatusVal());
		progress.setWork_order_id(workOrderId);
		progress.setLocation(location);
		progress.setVersion(1);
		progress.setCreate_time(new Date());

		try {
			if (service.save(progress)) {
				renderJson(success("项目进度记录添加成功!"));
			} else {
				renderJson(fail("项目进度记录新增失败!"));
			}
		} catch (Exception e) {
			renderJson(fail("服务端保存异常, 请联系管理员!"));
		}
	}

	/**
	 * 根据Id修改状态
	 */
	public void modifyStatus() {
		Long workOrderId = getParaToLong("workOrderId");// 新增参数
		Long pid = getParaToLong("id");
		Integer status = getParaToInt("status");
		if (pid == null || pid < 1) {
			renderJson(warn("进度Id无效，非法参数！"));
			return;
		}
		Integer[] stats = { 0, 1, 2, 3 };
		OrderProgress vo = service.findById(pid);
		if (!ArrayUtils.contains(stats, status)) {
			renderJson(warn("状态参数值不在定义范围内！"));
			return;
		}
		vo.setStatus(status);
		vo.setVersion(vo.getVersion() + 1);

		try {
			if (service.update(vo)) {
				// 当状态是修改为同意或者默认时处理
				if (status == ProgressStatus.CONFIRM.getStatusVal() || status == ProgressStatus.DEFAULT.getStatusVal()) {
					Long dgId = dgUserToken().getUserId();
					Record paras = Record.create().set("createId", dgId).set("workOrderId", workOrderId);
					// 修改记录：增加状态=(1,2)条件,表示只有当存在已经被同意或者默认的记录
					String sql = "where status in (1,2) and create_id = #{createId} and work_order_id = #{workOrderId}";
					List<OrderProgress> proList = service.findBy(sql, paras);
					if (proList != null && proList.size() == 1) {
						WorkOrder wo = workOrdServ.findById(workOrderId);
						wo.setVersion(wo.getVersion() + 1);
						wo.setStatus(ItemStatus.INPROCCESS.getStatusVal());
						wo.setProceed_time(new Date());
						workOrdServ.update(wo);
					}
				}
				renderJson(success("进度状态修改成功！"));
			} else {
				renderJson(fail("进度状态修改成功！"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderJson(error("状态修改异常，服务端报错！"));
		}
	}

	public void query() {// 进度查询
		Long dgUserId = dgUserToken().getUserId();
		Long workOrderId = getParaToLong("workOrderId");// 必传参数
		Integer status = getParaToInt("status");
		String createStart = getPara("createStart");
		String createEnd = getPara("createEnd");
		Long createId = getParaToLong("create_id");

		if (workOrderId == null || workOrderId <= 0) {
			renderJson(error("子项目Id不能为空！"));
			return;
		}

		Integer page = getParaToInt("page");
		if (page == null) {
			page = 1;
		}
		String sql = Blade.dao().getScript(SQL_LIST).getSql();
		Record paraMap = Record.create();
		StringBuffer sqlBuf = new StringBuffer(sql).append(" where 1=1 ");
		sqlBuf.append(" and a.work_order_id = ").append(workOrderId);

		if (createId != null && createId > 0) {
			if (dgUserId == createId) {
				paraMap.set("dgUserId", createId);
				sqlBuf.append(" and a.create_id = #{dgUserId} ");
			} else {
				renderJson(warn("进度提起人和当前账号不对应!"));
				return;
			}
		}

		if (status != null && status > -1) {
			paraMap.set("status", status);
			sqlBuf.append(" and a.status = #{status} ");
		}

		if (StringUtils.isNotBlank(createStart)) {
			paraMap.set("createStart", createStart);//
			sqlBuf.append(" and a.create_time >= #{createStart}");
		}

		if (StringUtils.isNotBlank(createEnd)) {
			paraMap.set("createEnd", createEnd);
			sqlBuf.append(" and a.create_time < DATE_ADD(#{createEnd},INTERVAL 1 DAY)");
		}

		int start = (page - 1) * pageSize + 1;// 分页起始位置
		sqlBuf.append(" order by a.create_time desc");
		BladePage<Object> resultPage = Db.init().paginate(sqlBuf.toString(), paraMap, start, pageSize);
		if (resultPage != null && !resultPage.getRows().isEmpty()) {
			renderJson(success("查询到结果！").setData(resultPage));
		} else {
			renderJson(success("未查询到项目进度的记录!"));
		}
	}
}
