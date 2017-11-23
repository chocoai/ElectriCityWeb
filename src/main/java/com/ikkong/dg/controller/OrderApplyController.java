package com.ikkong.dg.controller;

import com.ikkong.core.jfinal.ext.kit.JsonKit;
import com.ikkong.dg.model.OrderApply;
import com.ikkong.dg.service.OrderApplyService;
import com.ikkong.dg.service.impl.OrderApplyServiceImpl;
import com.ikkong.system.controller.base.UrlPermissController;

/**
 * Generated by Blade.
 * 2017-10-02 11:50:45
 */
public class OrderApplyController extends UrlPermissController {
	private static String CODE = "orderApply";
	private static String PERFIX = "dg_order_apply";
	private static String LIST_SOURCE = "OrderApply.list";
	private static String BASE_PATH = "/dg/orderApply/";
	
	OrderApplyService service = new OrderApplyServiceImpl();
	
	public void index() {
		setAttr("code", CODE);
		render(BASE_PATH + "orderApply.html");
	}

	public void add() {
		setAttr("code", CODE);
		render(BASE_PATH + "orderApply_add.html");
	}

	public void edit() {
		String id = getPara(0);
		OrderApply orderApply = service.findById(id);
		setAttr("model", JsonKit.toJson(orderApply));
		setAttr("id", id);
		setAttr("code", CODE);
		render(BASE_PATH + "orderApply_edit.html");
	}

	public void view() {
		String id = getPara(0);
		OrderApply orderApply = service.findById(id);
		setAttr("model", JsonKit.toJson(orderApply));
		setAttr("id", id);
		setAttr("code", CODE);
		render(BASE_PATH + "orderApply_view.html");
	}

	public void list() {
		Object grid = paginate(LIST_SOURCE);
		renderJson(grid);
	}

	public void save() {
		OrderApply orderApply = mapping(PERFIX, OrderApply.class);
		boolean temp = service.save(orderApply);
		if (temp) {
			renderJson(success(SAVE_SUCCESS_MSG));
		} else {
			renderJson(error(SAVE_FAIL_MSG));
		}
	}

	public void update() {
		OrderApply orderApply = mapping(PERFIX, OrderApply.class);
		orderApply.setVersion(orderApply.getVersion()+1);
		boolean temp = service.update(orderApply);
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
