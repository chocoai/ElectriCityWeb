package com.ikkong.dg.android;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ikkong.core.base.BaseController;
import com.ikkong.core.dao.Blade;
import com.ikkong.core.toolbox.Record;
import com.ikkong.dg.model.WorkType;
import com.ikkong.dg.service.WorkTypeService;
import com.ikkong.dg.service.impl.WorkTypeServiceImpl;

/**
 * 工种客户端交互类
 * 
 * @author sparker
 *
 */
public class WorkTypeClientController extends BaseController {
	
	private static final String SQL_LIST = "WorkType.findList";
	
	private WorkTypeService workTypeSvr = new WorkTypeServiceImpl();
	
	// 新增工种类型
	/*public void add(){
		AjaxResult res = addValidate();
		if(res.isSuccess()){
			String name = getPara("name");
			Integer check = getParaToInt("check");
			String salary = getPara("salary");
			String otMoney = getPara("overMoney");
			Integer orderFlag = getParaToInt("orderFlag");
			String mark = getPara("mark");
			
			WorkType vo = new WorkType();
			vo.setName(name);
			vo.setCheck(check);
			vo.setOrder_flag(orderFlag);
			vo.setMark(mark);
			vo.setSalary(Double.valueOf(salary));
			vo.setOver_money(Double.valueOf(otMoney));
			vo.setVersion(1);
			
			try {
				if(!workTypeSvr.save(vo)){
					renderJson(error("保存失败!"));
					return;
				} else {
					res = success("添加成功！");
				}
			} catch (Exception e) {
				LogKit.error("工种添加失败,服务端保存异常：{}", e);
				renderJson(error("保存失败!"));
				return;
			}				
		}
		renderJson(res);
	}*/
	
	// 删除
	/*public void del(){
		Integer id = getParaToInt("id");
		WorkType wt = workTypeSvr.findById(id);
		if(wt == null){
			renderJson(error("该工种记录已被删除!"));
		} else {
			int delRecords = workTypeSvr.delete(id);
			if(delRecords > 0){
				renderJson(success("记录删除成功!"));
			} else {
				renderJson(error("工种记录删除失败!"));
			}
		}
	}*/
	
	// 修改
	/*public void edit(){
		Integer id = getParaToInt("id");
		String name = getPara("name");
		Integer check = getParaToInt("check");
		String salary = getPara("salary");
		String otMoney = getPara("overMoney");
		Integer orderFlag = getParaToInt("orderFlag");
		String mark = getPara("mark");
		
		if(id == null) {
			renderJson(error("未提供Id，无效操作！"));
			return;
		}
		WorkType wt = workTypeSvr.findById(id);
		if(wt == null){
			renderJson(error("该工种记录不存在或已被删除!"));
		} else {
			wt.setName(name);
			wt.setCheck(check);
			wt.setOrder_flag(orderFlag);
			wt.setMark(mark);
			wt.setSalary(Double.valueOf(salary));
			wt.setOver_money(Double.valueOf(otMoney));
			wt.setVersion(wt.getVersion()+1);
			
			try {
				if(!workTypeSvr.update(wt)){
					renderJson(error("修改失败!"));
				} else {
					renderJson(success("工种信息修改成功!"));
				}
			} catch (Exception e) {
				LogKit.error("工种修改失败,服务端保存异常：{}", e);
				renderJson(error("修改失败,服务端异常!"));
			}
		}
	}*/
	
	// 查询
	public void query(){
		StringBuffer sqlBuf = new StringBuffer(Blade.dao().getScript(SQL_LIST).getSql());
		Record paraMap = Record.create();
		List<WorkType> list = null;
		String name = getPara("name");
		if(StringUtils.isNotBlank(name)){
			paraMap.set("name", name);
			sqlBuf.append(" where a.name like '%#{name}%' ");
		}
		list = workTypeSvr.find(sqlBuf.append(" order by a.times desc").toString(), paraMap);
		if(list == null || list.isEmpty()){
			renderJson(warn("工种类型记录查询为空!"));
			return;
		}
		renderJson(list);
	}
	
	/**
	 * 根据Id获取
	 */
	public void getById() {
		Integer id = getParaToInt("id");
		if(id == null) {
			renderJson(error("无效的请求，请传入ID！"));
			return ;
		}
		WorkType wt = workTypeSvr.findById(id);
		if(wt == null) {
			renderJson(error("该ID不存在记录！"));
			return ;
		} else {
			renderJson(wt);
		}
		
	}
	
	/*public AjaxResult addValidate(){
		String name = getPara("name");
		Integer check = getParaToInt("check");
		String salary = getPara("salary");
		String orderFlag = getPara("orderFlag");
		
		if(StringUtils.isBlank(name)){
			return error("工种名不能为空!");
		} else {
			WorkType worktype = workTypeSvr.findFirstBy("name = #{name}", 
					Record.create().set("name", name.trim()));
			if(worktype != null){
				return error("该工种名系统已经存在记录!");
			}
		}
		if(check == null){
			return error("工种类型不能为空!");
		}
		if(StringUtils.isBlank(salary)){
			return error("工种酬劳不能为空!");
		}
		if(orderFlag == null){
			return error("发单权限不能为空!");
		}
		
		return success("验证通过！");
	}*/
}
