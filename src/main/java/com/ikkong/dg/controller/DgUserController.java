package com.ikkong.dg.controller;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.ikkong.common.constant.CommonConts.UserStatus;
import com.ikkong.core.jfinal.ext.kit.JsonKit;
import com.ikkong.core.toolbox.Func;
import com.ikkong.core.toolbox.Record;
import com.ikkong.core.toolbox.kit.DateKit;
import com.ikkong.core.toolbox.kit.DateTimeKit;
import com.ikkong.dg.meta.intercept.DgUserIntercept;
import com.ikkong.dg.model.DgUser;
import com.ikkong.dg.service.DgUserService;
import com.ikkong.dg.service.impl.DgUserServiceImpl;
import com.ikkong.system.controller.base.UrlPermissController;
import com.jfinal.aop.Clear;

/**
 * Generated by Blade.
 * 2017-09-24 12:44:38
 */
public class DgUserController extends UrlPermissController {
	private static String CODE = "dgUser";
	private static String PERFIX = "dg_user";
	private static String LIST_SOURCE = "DgUser.list";
	private static String BASE_PATH = "/dg/dgUser/";
	
	DgUserService service = new DgUserServiceImpl();
	
	public void index() {
		setAttr("code", CODE);
		render(BASE_PATH + "dgUser.html");
	}

	public void add() {
		setAttr("code", CODE);
		render(BASE_PATH + "dgUser_add.html");
	}

	public void edit() {
		String id = getPara(0);
		DgUser dgUser = service.findById(id);
		setAttr("model", JsonKit.toJson(dgUser));
		setAttr("id", id);
		setAttr("code", CODE);
		render(BASE_PATH + "dgUser_edit.html");
	}

	public void view() {
		info();
	}
	
	private void info(){
		String id = getPara(0);
		DgUser dgUser = service.findById(id);
		Record maps = Record.parse(dgUser);
		DgUser introUser = service.findById(dgUser.getIntro_id());
		if(introUser != null) maps.set("introName", introUser.getName());
		
		maps.set("statusName", Func.getDictName(208, dgUser.getStatus()))
			.set("genderName", Func.getDictName(101, dgUser.getGender()))
			.set("birthDate", DateTimeKit.formatDateTime(dgUser.getRegister_time()));;		
		setAttr("user", maps);
		setAttr("id", id);
		setAttr("code", CODE);
		render(BASE_PATH + "dgUser_view.html");
	}
	
	public void introView() {
		info();
	}
	
	/**
	 * 解除冻结与挂起状态
	 */
	public void relieve(){
		Long id = getParaToLong("ids");
		DgUser user = service.findById(id);
		if(UserStatus.NORMAL.getStatusVal() == user.getStatus()) {
			renderJson(warn("用户当前状态已变更为正常！"));
			return ;
		} 
		user.setStatus(UserStatus.NORMAL.getStatusVal());
		user.setVersion(user.getVersion()+1);
		
		try {
			if(service.update(user)) {
				renderJson(success("账户解除成功，已恢复正常！"));
			} else {
				renderJson(fail("账户解除失败！"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderJson(error("账户解除操作服务端异常！"));
			return ;
		}
	}

	public void hangup(){// 挂起
		Long id = getParaToLong("ids");
		DgUser user = service.findById(id);
		if(UserStatus.HANGUP.getStatusVal() == user.getStatus()) {
			renderJson(warn("当前用户已被挂起处理！"));
			return ;
		} 
		user.setStatus(UserStatus.HANGUP.getStatusVal());
		user.setVersion(user.getVersion()+1);
		
		try {
			if(service.update(user)) {
				renderJson(success("账户挂起成功！"));
			} else {
				renderJson(fail("账户挂起失败！"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderJson(error("账户挂起操作服务端异常！"));
			return ;
		}
	}

	public void freeze(){//
		Long id = getParaToLong("ids");
		DgUser user = service.findById(id);
		if(UserStatus.FREEZE.getStatusVal() == user.getStatus()) {
			renderJson(warn("当前账户已被冻结！"));
			return ;
		} 
		user.setStatus(UserStatus.FREEZE.getStatusVal());
		user.setVersion(user.getVersion()+1);
		
		try {
			if(service.update(user)) {
				renderJson(success("账户冻结成功！"));
			} else {
				renderJson(fail("账户冻结失败！"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderJson(error("账户冻结操作服务端异常！"));
			return ;
		}
	}

	public void disable(){// 注销账户
		Long id = getParaToLong("ids");
		DgUser user = service.findById(id);
		if(UserStatus.DISABLE.getStatusVal() == user.getStatus()) {
			renderJson(warn("账户已被注销！"));
			return ;
		} 
		user.setStatus(UserStatus.DISABLE.getStatusVal());
		user.setVersion(user.getVersion()+1);
		
		try {
			if(service.update(user)) {
				renderJson(success("账户注销成功！"));
			} else {
				renderJson(fail("账户注销失败！"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderJson(error("账户注销操作服务端异常！"));
			return ;
		}
	}

	public void list() {
		Object grid = paginate(LIST_SOURCE, new DgUserIntercept());
		renderJson(grid);
	}

	public void save() {
		DgUser dgUser = mapping(PERFIX, DgUser.class);
		boolean temp = service.save(dgUser);
		if (temp) {
			renderJson(success(SAVE_SUCCESS_MSG));
		} else {
			renderJson(error(SAVE_FAIL_MSG));
		}
	}

	public void update() {
		DgUser dgUser = mapping(PERFIX, DgUser.class);
		dgUser.setVersion(dgUser.getVersion()+1);
		boolean temp = service.update(dgUser);
		if (temp) {
			renderJson(success(UPDATE_SUCCESS_MSG));
		} else {
			renderJson(error(UPDATE_FAIL_MSG));
		}
	}

	public void remove() {
		String ids = getPara("ids");
		int cnt = service.deleteByIds(ids);
		if (cnt > 0) {
			renderJson(success(DEL_SUCCESS_MSG));
		} else {
			renderJson(error(DEL_FAIL_MSG));
		}
	}
	
	public void userworktype(){
		String dgId = getPara(0);		
		setAttr("code", CODE);
		setAttr("dgId", dgId);
		render(BASE_PATH + "userWorktype_list.html");
	}
	
	@Clear
	public void userworkList(){
		Object grid = paginate("UserWorktype.findByPage");
		renderJson(grid);
	}
	
	public void business(){
		setAttr("dgUserId", getPara(0));
		setAttr("code", "business");
		setAttr("menuCode", "dgUser_business");
		render(BASE_PATH + "dgUser_business.html");
	}
	
}
