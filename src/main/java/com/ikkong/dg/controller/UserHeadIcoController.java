package com.ikkong.dg.controller;

import java.util.Map;
import com.ikkong.core.base.BaseController;
import com.ikkong.core.jfinal.ext.kit.JsonKit;
import com.ikkong.system.controller.base.UrlPermissController;
import com.ikkong.dg.model.UserHeadIco;
import com.ikkong.dg.service.UserHeadIcoService;
import com.ikkong.dg.service.impl.UserHeadIcoServiceImpl;

/**
 * Generated by Blade.
 * 2017-10-28 20:22:10
 */
public class UserHeadIcoController extends UrlPermissController {
	private static String CODE = "userHeadIco";
	private static String PERFIX = "dg_user_head_ico";
	private static String LIST_SOURCE = "UserHeadIco.list";
	private static String BASE_PATH = "/platform/userHeadIco/";
	
	UserHeadIcoService service = new UserHeadIcoServiceImpl();
	
	public void index() {
		setAttr("code", CODE);
		render(BASE_PATH + "userHeadIco.html");
	}

	public void add() {
		setAttr("code", CODE);
		render(BASE_PATH + "userHeadIco_add.html");
	}

	public void edit() {
		String id = getPara(0);
		UserHeadIco userHeadIco = service.findById(id);
		setAttr("model", JsonKit.toJson(userHeadIco));
		setAttr("id", id);
		setAttr("code", CODE);
		render(BASE_PATH + "userHeadIco_edit.html");
	}

	public void view() {
		String id = getPara(0);
		UserHeadIco userHeadIco = service.findById(id);
		setAttr("model", JsonKit.toJson(userHeadIco));
		setAttr("id", id);
		setAttr("code", CODE);
		render(BASE_PATH + "userHeadIco_view.html");
	}

	public void list() {
		Object grid = paginate(LIST_SOURCE);
		renderJson(grid);
	}

	public void save() {
		UserHeadIco userHeadIco = mapping(PERFIX, UserHeadIco.class);
		boolean temp = service.save(userHeadIco);
		if (temp) {
			renderJson(success(SAVE_SUCCESS_MSG));
		} else {
			renderJson(error(SAVE_FAIL_MSG));
		}
	}

	public void update() {
		UserHeadIco userHeadIco = mapping(PERFIX, UserHeadIco.class);
		userHeadIco.setVersion(userHeadIco.getVersion()+1);
		boolean temp = service.update(userHeadIco);
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
}