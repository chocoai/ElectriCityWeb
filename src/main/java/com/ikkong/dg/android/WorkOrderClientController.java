package com.ikkong.dg.android;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ikkong.common.constant.CommonConts;
import com.ikkong.common.constant.CommonConts.ItemStatus;
import com.ikkong.common.constant.CommonConts.UserStatus;
import com.ikkong.core.base.BaseController;
import com.ikkong.core.dao.Blade;
import com.ikkong.core.dao.Db;
import com.ikkong.core.toolbox.Record;
import com.ikkong.core.toolbox.support.BladePage;
import com.ikkong.dg.model.DgOrder;
import com.ikkong.dg.model.DgUser;
import com.ikkong.dg.model.UserWorktype;
import com.ikkong.dg.model.WorkOrder;
import com.ikkong.dg.service.DgOrderService;
import com.ikkong.dg.service.DgUserService;
import com.ikkong.dg.service.UserWorktypeService;
import com.ikkong.dg.service.WorkOrderService;
import com.ikkong.dg.service.impl.DgOrderServiceImpl;
import com.ikkong.dg.service.impl.DgUserServiceImpl;
import com.ikkong.dg.service.impl.UserWorktypeServiceImpl;
import com.ikkong.dg.service.impl.WorkOrderServiceImpl;

/**
 * 工种订单
 * 
 * @author zaoxin.xie
 *
 */
public class WorkOrderClientController extends BaseController {
	
	private static int pageSize = CommonConts.CLIENT_PAGE_SIZE;
	
	private static final String SQL_LIST = "WorkOrder.findList";
	
	private DgOrderService orderServ = new DgOrderServiceImpl();
	
	private WorkOrderService service = new WorkOrderServiceImpl();
	
	private DgUserService userServ = new DgUserServiceImpl();
	
	private UserWorktypeService userWorkTpServ = new UserWorktypeServiceImpl();

	/**
	 * 子项目订单添加
	 */
	public void addProItem(){
		Long dgUserId = dgUserToken().getUserId();
		DgUser dgUser = userServ.findById(dgUserId);
		if(UserStatus.NORMAL.getStatusVal() != dgUser.getStatus()){
			renderJson(warn("您当前账户状态不能发布工种单！"));
			return;
		}
		Long orderId = getParaToLong("orderId");
		Integer orderDate = getParaToInt("orderDate");
		Integer worktypeId = getParaToInt("worktypeId");
		String money = getPara("money");//Double
		Integer status = getParaToInt("status");
		String subsidy = getPara("subsidy");
		String subsidyMark = getPara("subsidyMark");//Double
		String mark = getPara("mark");
		
		if(orderId == null){
			renderJson(error("项目头引用不能为空!"));
			return ;
		}
		if(orderDate == null){
			renderJson(error("项目周期不能为空!"));
			return ;
		}
		if(worktypeId == null){
			renderJson(error("工种类型不能为空!"));
			return ;
		}
		if(status == null){
			renderJson(error("子项目状态不能为空!"));
			return ;
		}
		if(StringUtils.isEmpty(money)){
			renderJson(error("项目金额不能为空!"));
			return ;
		} else if(Double.valueOf(money) < 0D){
			renderJson(error("项目金额不能小于 0 !"));
			return ;
		}
		
		WorkOrder item = new WorkOrder();
		item.setOrder_date(orderDate);
		if(money == null) {
			money = "0";
		}
		if(subsidy == null) {
			subsidy = "0";
		}
		item.setMoney(Double.valueOf(money));
		item.setMark(mark);
		item.setOrder_id(orderId);
		item.setStatus(status);
		item.setSubsidy(Double.valueOf(subsidy));
		item.setSubsidy_mark(subsidyMark);
		item.setCreate_id(dgUserId);
		item.setCreate_time(new Date());
		item.setVersion(1);
		if(ItemStatus.PUBLISH.getStatusVal() == status) {
			item.setPublish_time(new Date());
		}
		
		try {
			if(service.save(item)){
				renderJson(success("子项目单添加成功!"));
			} else {
				renderJson(fail("子项目单新增失败!"));
			}
		} catch (Exception e) {
			renderJson(fail("服务端保存异常, 请联系管理员!"));
		}
	}
	
	/**
	 * 修改子项目信息
	 */
	public void modifyProItem(){
		Long id = getParaToLong("id");
		Long orderId = getParaToLong("orderId");
		Integer orderDate = getParaToInt("orderDate");
		Integer worktypeId = getParaToInt("worktypeId");
		String money = getPara("money");//Double
		Integer status = getParaToInt("status");
		String subsidy = getPara("subsidy");
		String subsidyMark = getPara("subsidyMark");//Double
		String mark = getPara("mark");
		
		if(id == null){
			renderJson(error("请提供子项目单Id参数!"));
			return ;
		}
		if(orderId == null){
			renderJson(error("项目头引用不能为空!"));
			return ;
		}
		if(orderDate == null){
			renderJson(error("项目周期不能为空!"));
			return ;
		}
		if(worktypeId == null){
			renderJson(error("工种类型不能为空!"));
			return ;
		}
		if(status == null){
			renderJson(error("子项目状态不能为空!"));
			return ;
		}
		if(StringUtils.isEmpty(money)){
			renderJson(error("项目金额不能为空!"));
			return ;
		} else if(Double.valueOf(money) < 0D){
			renderJson(error("项目金额不能小于 0 !"));
			return ;
		}
		
		WorkOrder item = service.findById(id);		
		item.setOrder_date(orderDate);
		item.setMoney(Double.valueOf(money));
		item.setMark(mark);
		item.setOrder_id(orderId);
		item.setStatus(status);
		item.setSubsidy(Double.valueOf(subsidy));
		item.setSubsidy_mark(subsidyMark);
		item.setCreate_time(new Date());
		item.setVersion(item.getVersion()+1);
		
		try {
			if(service.update(item)){
				renderJson(success("子项目单信息更新成功!"));
			} else {
				renderJson(fail("子项目单信息更新失败!"));
			}
		} catch (Exception e) {
			renderJson(error("服务端保存异常, 请联系管理员!"));
		}
	}
	
	/**
	 * @type 类型1: 查我发布的，类型2：查我招标的
	 * @status 根据多状态查询
	 * @worktypeId  工种类型Id
	 * @createStart  创建日期区间-开始日期
	 * @createEnd   创建日期区间-截止日期
	 * @finishStart  结束日期区间-开始日期
	 * @finishEnd   结束日期区间-截止日期
	 * @author Administrator
	 */
	public void query() {
		String status = getPara("status");
		Integer type = getParaToInt("type");//
		Integer worktypeId = getParaToInt("worktypeId");//
		String createStart = getPara("createStart");
		String createEnd = getPara("createEnd");
		String finishStart = getPara("finishStart");
		String finishEnd = getPara("finishEnd");
		
		Integer page = getParaToInt("page");
		if (page == null) {
			page = 1;
		}

		Long dgId = dgUserToken().getUserId();
		int start = (page - 1) * pageSize + 1;// 分页起始位置
		String sql = Blade.dao().getScript(SQL_LIST).getSql();
		Record paraMap = Record.create();
		StringBuffer buf = new StringBuffer(sql).append(" where 1=1 ");		
				
		if(type == null){
			renderJson(error("类型参数不能填空！"));
			return ;
		} else if(type == 1){
			paraMap.set("dgUserId", dgId);
			buf.append(" and a.create_id = #{dgUserId}");
		} else if(type == 2){
			paraMap.set("dgUserId", dgId);
			buf.append(" and a.accept_user_id = #{dgUserId}");
		}
		
		if(worktypeId != null && worktypeId > 0){
			paraMap.set("worktypeId", worktypeId);
			buf.append(" and a.worktype_id = #{worktypeId}");
		} else {// 默认电工自己的
			String workIds = getWorkTypeIds(dgId);
			if(workIds != null) {
				paraMap.set("worktypeIds", workIds.split(","));
				buf.append(" and a.worktype_id in (#{join(worktypeIds)}) ");			
			}
		}
		
		if(StringUtils.isNotBlank(status) && !"-1".equals(status)){
			paraMap.set("status", status.split(","));//多状态查询
			buf.append(" and a.status in (#{join(status)})");//join 
		}
		
		if(StringUtils.isNotBlank(createStart)){
			paraMap.set("createStart", createStart);//
			buf.append(" and a.create_time >= #{createStart}");
		}
		
		if(StringUtils.isNotBlank(createEnd)){
			paraMap.set("createEnd", createEnd);
			buf.append(" and a.create_time < DATE_ADD(#{createEnd},INTERVAL 1 DAY)");
		}
		
		if(StringUtils.isNotBlank(finishStart)){
			paraMap.set("finishStart", finishStart);
			buf.append(" and a.finish_time >= #{finishStart} ");
		}
		
		if(StringUtils.isNotBlank(finishEnd)){
			paraMap.set("finishEnd", finishEnd);
			buf.append(" and a.finish_time < DATE_ADD(#{finishEnd},INTERVAL 1 DAY)");
		}		
		buf.append(" order by a.create_time desc");
		
		try {
			BladePage<Object> resultPage = Db.init().paginate(buf.toString(), paraMap, start, pageSize);
			if (resultPage != null && !resultPage.getRows().isEmpty()) {
				renderJson(success("查询到子项目记录！").setData(resultPage));
			} else {
				renderJson(warn("未查询到子项目记录!"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderJson(error("服务端查询异常！"));
		}
	}
	
	/**
	 * @myType 类型1: 符合我工种的招标单，类型2：所有工种的招标单
	 * @createStart  创建日期区间-开始日期
	 * @createEnd   创建日期区间-截止日期
	 * @finishStart  结束日期区间-开始日期
	 * @finishEnd   结束日期区间-截止日期
	 * @author Administrator
	 */
	public void usefull() {
		String createStart = getPara("createStart");
		String createEnd = getPara("createEnd");
		String finishStart = getPara("finishStart");
		String finishEnd = getPara("finishEnd");
		Integer myType = getParaToInt("myType");
		
		Integer page = getParaToInt("page");
		if (page == null) {
			page = 1;
		}

		Long dgId = dgUserToken().getUserId();
		int start = (page - 1) * pageSize + 1;// 分页起始位置
		String sql = Blade.dao().getScript(SQL_LIST).getSql();
		Record paraMap = Record.create();
		StringBuffer buf = new StringBuffer(sql).append(" where a.status = #{status} ");
		paraMap.set("status", ItemStatus.BIDDING.getStatusVal());
		buf.append(" and a.create_id <> ").append(dgId);
		
		if(myType == null) {
			renderJson(warn("myType参数为必传参数，请提供！"));
			return;
		}
		
		if(myType != 1 && myType != 2) {
			renderJson(warn("该myType参数值未做定义！"));
			return;
		}
		
		if(myType == 1) {
			String workIds = getWorkTypeIds(dgId);
			if(workIds != null) {
				paraMap.set("worktypeIds", workIds.split(","));
				buf.append(" and a.worktype_id in (#{join(worktypeIds)}) ");			
			}
		}
		
		if(StringUtils.isNotBlank(createStart)){
			paraMap.set("createStart", createStart);//
			buf.append(" and a.create_time >= #{createStart}");
		}
		
		if(StringUtils.isNotBlank(createEnd)){
			paraMap.set("createEnd", createEnd);
			buf.append(" and a.create_time < DATE_ADD(#{createEnd},INTERVAL 1 DAY)");
		}
		
		if(StringUtils.isNotBlank(finishStart)){
			paraMap.set("finishStart", finishStart);
			buf.append(" and a.finish_time >= #{finishStart} ");
		}
		
		if(StringUtils.isNotBlank(finishEnd)){
			paraMap.set("finishEnd", finishEnd);
			buf.append(" and a.finish_time < DATE_ADD(#{finishEnd},INTERVAL 1 DAY)");
		}
		buf.append(" and NOT EXISTS(SELECT z.work_order_id FROM dg_order_apply z WHERE a.id = z.work_order_id AND z.type=1 and z.status in (0,1)) ");		
		buf.append(" order by a.create_time desc");
		
		try {
			BladePage<Object> resultPage = Db.init().paginate(buf.toString(), paraMap, start, pageSize);
			if (resultPage != null && !resultPage.getRows().isEmpty()) {
				renderJson(success("查询到正在招标的记录！").setData(resultPage));
			} else {
				renderJson(warn("未查询到正在招标的记录!"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderJson(error("服务端查询异常！"));
		}
	}
	
	/**
	 * @itemId 项目头Id
	 * @worktypeId 工种类型Id
	 * @status 0保存,1发布中，2招标中，3已招标，4项目进行中，5完成，6终止，7撤销，8挂起
	 * @createStart  创建日期区间-开始日期
	 * @createEnd   创建日期区间-截止日期
	 * @finishStart  结束日期区间-开始日期
	 * @finishEnd   结束日期区间-截止日期
	 * @author Administrator
	 */
	public void byItemId() {
		String createStart = getPara("createStart");
		String createEnd = getPara("createEnd");
		String finishStart = getPara("finishStart");
		String finishEnd = getPara("finishEnd");
		Integer status = getParaToInt("status");// 
		Long itemId = getParaToLong("itemId");// 项目id
		Integer worktypeId = getParaToInt("worktypeId");//
		
		if(itemId == null || itemId <= 0) {
			renderJson(warn("项目头Id无效，请提供正确的头Id！"));
			return;
		}
		
		DgOrder order = orderServ.findById(itemId);
		if(order == null) {
			renderJson(warn("该项目不存在！"));
			return;
		}
		
		Integer page = getParaToInt("page");
		if (page == null) {
			page = 1;
		}

		int start = (page - 1) * pageSize + 1;// 分页起始位置
		String sql = Blade.dao().getScript(SQL_LIST).getSql();
		Record paraMap = Record.create();
		StringBuffer buf = new StringBuffer(sql).append(" where a.order_id = #{orderId} ");
		paraMap.set("orderId", itemId);
		
		if(status > -1) {
			buf.append(" and a.status = #{status}");
			paraMap.set("status", status);
		}
		
		if(worktypeId > -1) {
			buf.append(" and a.worktype_id = #{worktypeId}");
			paraMap.set("worktypeId", worktypeId);
		}
		
		if(StringUtils.isNotBlank(createStart)){
			paraMap.set("createStart", createStart);//
			buf.append(" and a.create_time >= #{createStart}");
		}
		
		if(StringUtils.isNotBlank(createEnd)){
			paraMap.set("createEnd", createEnd);
			buf.append(" and a.create_time < DATE_ADD(#{createEnd},INTERVAL 1 DAY)");
		}
		
		if(StringUtils.isNotBlank(finishStart)){
			paraMap.set("finishStart", finishStart);
			buf.append(" and a.finish_time >= #{finishStart} ");
		}
		
		if(StringUtils.isNotBlank(finishEnd)){
			paraMap.set("finishEnd", finishEnd);
			buf.append(" and a.finish_time < DATE_ADD(#{finishEnd},INTERVAL 1 DAY)");
		}
		
		buf.append(" order by a.create_time desc");
		
		try {
			BladePage<Object> resultPage = Db.init().paginate(buf.toString(), paraMap, start, pageSize);
			if (resultPage != null && !resultPage.getRows().isEmpty()) {
				renderJson(success("查询到项目itemId为"+itemId+"的记录！").setData(resultPage));
			} else {
				renderJson(warn("未查询到itemId为"+itemId+"的记录!"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderJson(error("服务端查询异常！"));
		}
	}
	
	private String getWorkTypeIds(Long dgUId) {
		List<UserWorktype> resList = userWorkTpServ.findBy("where dg_userid = #{dgUserId}",
				Record.create().set("dgUserId", dgUId));
		if(resList != null && !resList.isEmpty()) {
			StringBuffer ids = new StringBuffer();
			for(UserWorktype vo : resList) {
				ids.append(vo.getWorktype_id()).append(",");
			}
			return ids.delete(ids.length()-1, ids.length()).toString();
		}
		return null;
	}
}
