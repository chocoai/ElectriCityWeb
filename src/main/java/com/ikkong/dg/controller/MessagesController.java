package com.ikkong.dg.controller;

import com.ikkong.core.jfinal.ext.kit.JsonKit;
import com.ikkong.dg.model.Messages;
import com.ikkong.dg.service.MessagesService;
import com.ikkong.dg.service.impl.MessagesServiceImpl;
import com.ikkong.system.controller.base.UrlPermissController;
import com.jfinal.aop.Clear;

/**
 * Generated by Blade.
 * 2017-09-24 12:44:38
 */
public class MessagesController extends UrlPermissController {
	private static String CODE = "messages";
	private static String PERFIX = "dg_message";
	private static String LIST_SOURCE = "Messages.list";
	private static String BASE_PATH = "/dg/messages/";
	
	MessagesService service = new MessagesServiceImpl();
	
	public void index() {
		setAttr("code", CODE);
		render(BASE_PATH + "messages.html");
	}

	public void add() {
		setAttr("code", CODE);
		render(BASE_PATH + "messages_add.html");
	}

	public void edit() {
		String id = getPara(0);
		Messages messages = service.findById(id);
		setAttr("model", JsonKit.toJson(messages));
		setAttr("id", id);
		setAttr("code", CODE);
		render(BASE_PATH + "messages_edit.html");
	}

	public void view() {
		String id = getPara(0);
		Messages messages = service.findById(id);
		setAttr("model", JsonKit.toJson(messages));
		setAttr("id", id);
		setAttr("code", CODE);
		render(BASE_PATH + "messages_view.html");
	}
	
	public void check() {
		
	}

	public void list() {
		Object grid = paginate(LIST_SOURCE);
		renderJson(grid);
	}

	public void save() {
		Messages messages = mapping(PERFIX, Messages.class);
		boolean temp = service.save(messages);
		if (temp) {
			renderJson(success(SAVE_SUCCESS_MSG));
		} else {
			renderJson(error(SAVE_FAIL_MSG));
		}
	}

	public void update() {
		Messages messages = mapping(PERFIX, Messages.class);
		messages.setVersion(messages.getVersion()+1);
		boolean temp = service.update(messages);
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
	
	@Clear
	public void all(){
		setAttr("messageCode", "messages");
		setAttr("messages", service.findAll());
		render("/index.html");
	}
}