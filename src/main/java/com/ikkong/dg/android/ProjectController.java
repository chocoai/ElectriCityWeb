package com.ikkong.dg.android;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ikkong.common.constant.CommonConts;
import com.ikkong.core.base.BaseController;
import com.ikkong.core.dao.Blade;
import com.ikkong.core.dao.Db;
import com.ikkong.core.toolbox.Record;
import com.ikkong.core.toolbox.support.BladePage;
import com.ikkong.dg.model.DgOrder;
import com.ikkong.dg.model.WorkOrder;
import com.ikkong.dg.model.json.Items;
import com.ikkong.dg.service.DgOrderService;
import com.ikkong.dg.service.WorkOrderService;
import com.ikkong.dg.service.impl.DgOrderServiceImpl;
import com.ikkong.dg.service.impl.WorkOrderServiceImpl;

/**
 * 项目头信息管理
 * 
 * @author zaoxin.xie
 *
 */
public class ProjectController extends BaseController {
	
	private static int pageSize = CommonConts.CLIENT_PAGE_SIZE;
	
	private static final String SQL_LIST = "DgOrder.list";

	private DgOrderService projServ = new DgOrderServiceImpl();
	
	private WorkOrderService workOrderServ = new WorkOrderServiceImpl();
	
	public void add(){		
		String title = getPara("title");
		String content = getPara("content");
		String money = getPara("total_money");	
		String address = getPara("address");
		
		if(StringUtils.isBlank(title)){
			renderJson(error("请填写项目名称！"));
			return;
		}
		
		if(StringUtils.isBlank(address)){
			renderJson(error("请填写项目地址！"));
			return;
		}
		
		Long dgId = dgUserToken().getUserId();
		DgOrder proj = new DgOrder();
		proj.setContent(content);
		proj.setTitle(title);
		proj.setVersion(1);
		proj.setCreate_id(dgId);
		proj.setAddress(address);
		proj.setCreatetm(new Date());
		if(StringUtils.isNotBlank(money)) {
			proj.setTotal_money(Double.valueOf(money));
		}
		Integer curPId = null;
		try {
			curPId = projServ.saveRtId(proj);
			if(curPId > 0){
				String itmesStr = getPara("items");
				JSONArray array = JSONObject.parseArray(itmesStr);
				if(array != null && !array.isEmpty()){
					saveItems(array, dgId, curPId);
				}
				renderJson(success("项目信息添加成功！"));
			} else {
				renderJson(error("项目信息添加失败！"));
				if(curPId != null)
					projServ.delete(curPId);
			}
		} catch (Exception e) {
			if(curPId != null)
				projServ.delete(curPId);
			e.printStackTrace();
			renderJson(fail("服务端报错，添加失败！"));
		}
	}
	
	private static String timesModifySql = "update dg_worktype set times= times +1 where id = #{worktypeId}";
	private void saveItems(JSONArray array, Long dgUserId, Integer proId){
		Record record = Record.create();
		List<WorkOrder> itemList = new ArrayList<WorkOrder>();
		for(Object json : array){
			Items obj = JSON.parseObject(json.toString(), Items.class);
			itemList.add(buildItem(obj, dgUserId, proId));
			record.set("worktypeId", obj.getWorktypeId());
			Db.init().update(timesModifySql, record);
		}
		workOrderServ.saveBatchs(itemList);
	}
	
	private WorkOrder buildItem(Items vo, Long dgUserId, Integer proId){
		WorkOrder item = new WorkOrder();
		item.setCreate_id(dgUserId);
		item.setCreate_time(new Date());
		item.setMark(vo.getMark());
		item.setMoney(vo.getMoney());
		item.setTotal_money(vo.getTotalMoney());
		item.setOrder_date(vo.getOrderDays());
		item.setOrder_id(proId.longValue());
		item.setStatus(vo.getStatus());
		item.setSubsidy(vo.getSubsidy());
		item.setSubsidy_mark(vo.getSubsidyMark());
		item.setVersion(1);
		item.setWorktype_id(vo.getWorktypeId());
		return item;
	}
	
	/**
	 * 根据项目Id获取信息
	 */
	public void info(){
		Long pid = getParaToLong("id");
		if(pid == null){
			renderJson(error("请提供项目id！"));
			return;
		}
		DgOrder proj = projServ.findById(pid);
		if(proj == null){
			renderJson(error("无效的项目id！"));
			return;
		}
		renderJson(proj);
	}
	
	public void query() {
		Long dgUserId = dgUserToken().getUserId();
		String title = getPara("title");
		String createStart = getPara("createStart");
		String createEnd = getPara("createEnd");
		Integer page = getParaToInt("page");
		if (page == null) {
			page = 1;
		}

		int start = (page - 1) * pageSize + 1;// 分页起始位置
		String sql = Blade.dao().getScript(SQL_LIST).getSql();
		Record paraMap = Record.create().set("dgUserId", dgUserId);
		StringBuffer sqlBuf = new StringBuffer(sql).append(" where 1=1 ");
		sqlBuf.append(" and a.create_id = #{dgUserId}");//默认我创建的项目
		if(StringUtils.isNotBlank(title)){
			paraMap.set("title", title);
			sqlBuf.append(" and a.title like '%#{title}%'");
		}
		if(StringUtils.isNotBlank(createStart)){
			paraMap.set("createStart", createStart);//
			sqlBuf.append(" and a.createtm >= #{createStart}");
		}
		
		if(StringUtils.isNotBlank(createEnd)){
			paraMap.set("createEnd", createEnd);
			sqlBuf.append(" and a.createtm < DATE_ADD(#{createEnd},INTERVAL 1 DAY)");
		}
		
		sqlBuf.append(" order by a.createtm desc");
		BladePage<Object> resultPage = Db.init().paginate(sqlBuf.toString(), paraMap, start,
				pageSize);
		if(resultPage != null && !resultPage.getRows().isEmpty()){
			renderJson(success("查询到结果！").setData(resultPage));
		} else {
			renderJson(success("未查询到项目头记录!"));
		}
	}
	
	public void modify(){
		Long pid = getParaToLong("id");
		String title = getPara("title");
		String content = getPara("content");
		String money = getPara("total_money");
		if(pid == null) {
			renderJson(error("项目Id不能为空！"));
			return;
		}
		if(StringUtils.isBlank(title)){
			renderJson(error("项目名称不能置空！"));
			return;
		}
		DgOrder proj = projServ.findById(pid);
		proj.setTitle(title);
		proj.setContent(content);
		proj.setVersion(proj.getVersion()+1);
		if(StringUtils.isNotBlank(money)) {
			proj.setTotal_money(Double.valueOf(money));
		}
		try {
			if(projServ.update(proj)){
				String itmesStr = getPara("items");
				JSONArray array = JSONObject.parseArray(itmesStr);
				if(array != null && !array.isEmpty()){
					updateItems(array, pid);
				}
				renderJson(success("项目信息修改成功！"));
				return;
			} else {
				renderJson(error("项目信息修改失败！"));
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderJson(fail("服务端异常，修改失败！"));
		}
	}
	
	private void updateItems(JSONArray array, Long pid){
		for(Object json : array){
			Items vo = JSON.parseObject(json.toString(), Items.class);
			WorkOrder item = workOrderServ.findById(vo.getId());
			item.setCreate_time(new Date());
			item.setMark(vo.getMark());
			item.setMoney(vo.getMoney());
			item.setTotal_money(vo.getTotalMoney());
			item.setOrder_date(vo.getOrderDays());
			item.setStatus(vo.getStatus());
			item.setSubsidy(vo.getSubsidy());
			item.setSubsidy_mark(vo.getSubsidyMark());
			item.setVersion(item.getVersion()+1);
			item.setWorktype_id(vo.getWorktypeId());
			if(!workOrderServ.update(item, null)) {
				throw new RuntimeException("子项目记录更新失败，失败ID： "+vo.getId());
			}
		}
	}
}
