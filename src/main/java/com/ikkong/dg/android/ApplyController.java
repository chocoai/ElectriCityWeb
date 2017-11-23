package com.ikkong.dg.android;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ikkong.common.constant.CommonConts;
import com.ikkong.common.constant.CommonConts.ApplyStatus;
import com.ikkong.common.constant.CommonConts.ApplyType;
import com.ikkong.common.constant.CommonConts.ItemStatus;
import com.ikkong.common.constant.CommonConts.MsgStatus;
import com.ikkong.common.constant.CommonConts.MsgType;
import com.ikkong.common.constant.CommonConts.UserStatus;
import com.ikkong.core.base.BaseController;
import com.ikkong.core.dao.Blade;
import com.ikkong.core.dao.Db;
import com.ikkong.core.toolbox.Record;
import com.ikkong.core.toolbox.support.BladePage;
import com.ikkong.dg.model.DgUser;
import com.ikkong.dg.model.ItemsReject;
import com.ikkong.dg.model.Messages;
import com.ikkong.dg.model.OrderApply;
import com.ikkong.dg.model.UserWorktype;
import com.ikkong.dg.model.WorkOrder;
import com.ikkong.dg.service.DgUserService;
import com.ikkong.dg.service.ItemsRejectService;
import com.ikkong.dg.service.MessagesService;
import com.ikkong.dg.service.OrderApplyService;
import com.ikkong.dg.service.UserWorktypeService;
import com.ikkong.dg.service.WorkOrderService;
import com.ikkong.dg.service.impl.DgUserServiceImpl;
import com.ikkong.dg.service.impl.ItemsRejectServiceImpl;
import com.ikkong.dg.service.impl.MessagesServiceImpl;
import com.ikkong.dg.service.impl.OrderApplyServiceImpl;
import com.ikkong.dg.service.impl.UserWorktypeServiceImpl;
import com.ikkong.dg.service.impl.WorkOrderServiceImpl;

/**
 * 申请API
 * 
 * @author sparker
 *
 */
public class ApplyController extends BaseController {

	private static int pageSize = CommonConts.CLIENT_PAGE_SIZE;

	private static final String SQL_LIST = "OrderApply.findList";

	private OrderApplyService service = new OrderApplyServiceImpl();

	private DgUserService userServ = new DgUserServiceImpl();

	private UserWorktypeService userWorktypeServ = new UserWorktypeServiceImpl();

	private WorkOrderService workOrderServ = new WorkOrderServiceImpl();

	private ItemsRejectService itemReServ = new ItemsRejectServiceImpl();

	private MessagesService msgServ = new MessagesServiceImpl();

	public void add() {// 电工对项目子单的申请
		Long dgUserId = dgUserToken().getUserId();
		DgUser dgUser = userServ.findById(dgUserId);
		if (UserStatus.NORMAL.getStatusVal() != dgUser.getStatus()) {
			renderJson(warn("您当前账户状态不能申请！"));
			return;
		}
		Long workOrderId = getParaToLong("workOrderId");
		Integer status = getParaToInt("status");
		Integer type = getParaToInt("type");
		if (workOrderId == null || workOrderId <= 0) {
			renderJson(warn("请提供工种子项目!"));
			return;
		}

		if (status == null || status < 0) {
			renderJson(warn("请提供有效的申请状态!"));
			return;
		}

		if (type == null || type < 0) {
			renderJson(warn("请提供有效的申请类型!"));
			return;
		}

		WorkOrder item = workOrderServ.findById(workOrderId);// 子项目
		if (item == null) {
			renderJson(warn("子项目选择不正确，系统不存在！"));
			return;
		}

		if (type == ApplyType.APPLY.getTypeVal()) {// 如果当前申请类型是招标申请
			if (ItemStatus.BIDDING.getStatusVal() != item.getStatus()) {
				renderJson(warn("当前项目不在招标期！"));
				return;
			}

			// 查询电工用户工种表，当前电工，当前申请工种，状态为审核通过记录
			String whereSql = " where status = 3 and dg_userid = #{dgUserId} and worktype_id = #{workTypeId}";
			Record param = Record.create().set("dgUserId", dgUserId).set("workTypeId", item.getWorktype_id());
			UserWorktype userWorktype = userWorktypeServ.findFirstBy(whereSql, param);// 电工工种
			if (userWorktype == null) {
				renderJson(warn("您还没有认证该工种!"));
				return;
			}

			Record para = Record.create().set("workOrderId", workOrderId);
			List<OrderApply> exitsList = service.findBy("where type=1 and status=0 and work_order_id = #{workOrderId}", para);
			if (exitsList != null && !exitsList.isEmpty()) {
				renderJson(warn("当前工种单已被申请中，暂时不可申请！"));
				return;
			}

			String rejectSql = " where work_order_id = #{workOrderId} and dg_userid = #{dgUId}";
			Record rejectParam = Record.create().set("workOrderId", workOrderId).set("dgUId", dgUserId);
			ItemsReject reject = itemReServ.findFirstBy(rejectSql, rejectParam);
			if (reject != null) {
				renderJson(warn("抱歉，雇主已拒绝过您的申请，无法再次申请！"));// 在申请类型为1，状态为0或1时才提示
				return;
			}

			StringBuffer existBuf = new StringBuffer();
			existBuf.append("select 1 from dg_order_apply a, dg_work_order b ");
			existBuf.append(" where a.work_order_id = b.id ");
			existBuf.append(" and a.type=1 and a.status in (0,1) ");
			existBuf.append(" and b.order_id not in (#{orderId}) ");
			existBuf.append(" and a.create_id = #{applyUId} ");
			Record existPara = Record.create().set("orderId", item.getOrder_id()).set("applyUId", dgUserId);
			boolean hasRecord = Db.init().isExist(existBuf.toString(), existPara);
			if (hasRecord) {
				renderJson(warn("不能同时申请两个项目下的工种单！"));
				return;
			}
		}

		String mark = getPara("mark");

		OrderApply apply = new OrderApply();
		apply.setCreate_id(dgUserId);
		apply.setType(type);
		apply.setStatus(status);
		apply.setWork_order_id(workOrderId);
		apply.setCreate_time(new Date());
		apply.setMark(mark);
		apply.setVersion(1);

		try {
			if (service.save(apply)) {
				// 发送系统提示消息到发单用户手机端提示
				saveMsg(dgUserId);
				renderJson(success("申请记录添加成功!"));
			} else {
				renderJson(fail("申请记录新增失败!"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderJson(fail("服务端保存异常, 请联系管理员!"));
		}
	}

	private boolean checkApply(Long createId, Long workOrderId, Integer type, Integer status) {
		String countSql = "select count(1) from dg_order_apply where " + " create_id = #{createId} and work_order_id = #{workOrderId} " + " and type = #{type} and status = #{status}";
		Record record = Record.create().set("createId", createId).set("workOrderId", workOrderId).set("type", type).set("status", status);

		return service.isExist(countSql, record);
	}

	private void saveMsg(Long dgUserId) {
		Messages msg = new Messages();
		msg.setVersion(1);
		msg.setDg_id(dgUserId);
		msg.setCreate_time(new Date());
		msg.setStatus(MsgStatus.UNREAD.getStatusVal());
		msg.setType(MsgType.USER_MSG.getStatusVal());
		msgServ.save(msg);
	}

	public void modifyStatus() {
		Integer status = getParaToInt("status");
		Long id = getParaToLong("id");
		String auditDesc = getPara("audit_desc");
		Integer type = getParaToInt("type");// 新增申请类型参数
		
		if (status == null || status < 0 || id == null || id < 1 || type == null || type < 0) {
			renderJson(warn("参数传入不正确,请确保参数合乎逻辑!"));
			return;
		}
		
		if (StringUtils.isBlank(auditDesc)) {
			renderJson(warn("描述信息不能为空！"));
			return;
		}

		if (status != ApplyStatus.AGREE.getStatusVal() && status != ApplyStatus.REFUSED.getStatusVal()) {
			renderJson(warn("状态传入不正确, 必须是同意或拒绝状态才能操作!"));
			return;
		}

		OrderApply apply = service.findById(id);
		apply.setVersion(apply.getVersion() + 1);
		apply.setStatus(status);
		apply.setAudit_desc(auditDesc);
		apply.setType(type);
		try {
			if (service.update(apply)) {
				renderJson(success("申请状态变更成功!"));
			} else {
				renderJson(fail("申请状态变更失败!"));
				return;
			}
			// 注意：先修改记录，成功后才更新状态
			if (type == ApplyType.APPLY.getTypeVal()) {// 如果当前申请类型是招标申请
				WorkOrder wo = workOrderServ.findById(apply.getWork_order_id());
				if (status == ApplyStatus.REFUSED.getStatusVal()) {// 子项目记录拒绝次数
					if (wo.getReject_times() == null) {
						wo.setReject_times(1);
					} else {
						wo.setReject_times(wo.getReject_times() + 1);
					}
					// 不应该在招标操作里面挂起，应该在终止操作里面修改
					// wo.setStatus(ItemStatus.HUNG_UP.getStatusVal());
					wo.setVersion(wo.getVersion() + 1);
					workOrderServ.update(wo);

					// 添加招标申请拒绝记录
					ItemsReject itemRt = new ItemsReject();
					itemRt.setVersion(1);
					itemRt.setCreate_time(new Date());
					itemRt.setDg_userid(apply.getCreate_id());
					itemRt.setWork_order_id(apply.getWork_order_id());
					itemReServ.save(itemRt);
				} else if (status == ApplyStatus.AGREE.getStatusVal()) {
					wo.setAccept_user_id(apply.getCreate_id());
					wo.setAccpet_time(new Date());
					wo.setVersion(wo.getVersion() + 1);

					// 项目改成已招标
					wo.setStatus(ItemStatus.TERMINATE.getStatusVal());
					wo.setOverbid_time(new Date());// 已招标时间设置
					workOrderServ.update(wo);
				}
			} else if (type == ApplyType.ABORT.getTypeVal()) {// 如果当前申请类型是终止申请
				if (status == ApplyStatus.REFUSED.getStatusVal()) {// 拒绝
					WorkOrder wo = workOrderServ.findById(apply.getWork_order_id());
					wo.setStatus(ItemStatus.HUNG_UP.getStatusVal());// 挂起
					wo.setVersion(wo.getVersion() + 1);
					workOrderServ.update(wo);
				}
			} else if (type == ApplyType.OVERTIME.getTypeVal()) {// 如果当前申请类型是加班申请
				// 修改加班信息流程
			} else if (type == ApplyType.FINISH.getTypeVal()) {// 如果当前申请类型是完成申请
				// 走结算流程
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderJson(error("申请状态变更失败,服务端异常!"));
		}
	}

	public void query() {// 申请查询
		Integer page = getParaToInt("page");
		Integer status = getParaToInt("status");
		Integer type = getParaToInt("type");
		Long workOrderId = getParaToLong("workOrderId");
		String createStart = getPara("createStart");
		String createEnd = getPara("createEnd");
		Long createId = getParaToLong("create_id");

		if (page == null) {
			page = 1;
		}

		int start = (page - 1) * pageSize + 1;// 分页起始位置
		String sql = Blade.dao().getScript(SQL_LIST).getSql();
		Record paraMap = Record.create();//
		StringBuffer sqlBuf = new StringBuffer(sql);
		sqlBuf.append(" where 1=1 ");// @Modify 先不管我的申请

		if (createId != null && createId > 0) {
			Long dgUserId = dgUserToken().getUserId();
			if (dgUserId == createId) {
				paraMap.set("dgUserId", createId);
				sqlBuf.append(" and a.create_id = #{dgUserId} ");
			} else {
				renderJson(warn("申请人和当前账号不对应!"));
				return;
			}
		}

		if (workOrderId != null && workOrderId > 0) {
			paraMap.set("workOrderId", workOrderId);
			sqlBuf.append(" and a.work_order_id = #{workOrderId} ");
		}

		if (status != null && status > -1) {
			paraMap.set("status", status);
			sqlBuf.append(" and a.status = #{status} ");
		}

		if (type != null && type > -1) {
			paraMap.set("type", type);
			sqlBuf.append(" and a.type = #{type} ");
		}

		if (StringUtils.isNotBlank(createStart)) {
			paraMap.set("createStart", createStart);//
			sqlBuf.append(" and a.create_time >= #{createStart}");
		}

		if (StringUtils.isNotBlank(createEnd)) {
			paraMap.set("createEnd", createEnd);
			sqlBuf.append(" and a.create_time < DATE_ADD(#{createEnd},INTERVAL 1 DAY)");
		}

		sqlBuf.append(" order by a.create_time desc");
		BladePage<Object> resultPage = Db.init().paginate(sqlBuf.toString(), paraMap, start, pageSize);
		if (resultPage != null && !resultPage.getRows().isEmpty()) {
			renderJson(success("查询到结果！").setData(resultPage));
		} else {
			renderJson(warn("未查询到申请记录!"));
		}
	}

}
