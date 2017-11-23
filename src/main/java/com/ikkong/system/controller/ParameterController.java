/**
 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ikkong.system.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;

import com.ikkong.core.constant.ConstShiro;
import com.ikkong.core.jfinal.ext.kit.JsonKit;
import com.ikkong.core.toolbox.Record;
import com.ikkong.system.controller.base.AdminBaseController;
import com.ikkong.system.meta.intercept.ParameterIntercept;
import com.ikkong.system.model.Parameter;
import com.ikkong.system.service.ParameterService;
import com.ikkong.system.service.impl.ParameterServiceImpl;

@RequiresRoles(ConstShiro.ADMINISTRATOR)
public class ParameterController extends AdminBaseController {
	private static String CODE = "parameter";
	private static String PERFIX = "tfw_parameter";
	private static String LIST_SOURCE = "Parameter.list";
	private static String BASE_PATH = "/system/parameter/";
	
	ParameterService service = new ParameterServiceImpl();
	
	public void index() {
		setAttr("code", CODE);
		render(BASE_PATH + "parameter.html");
	}

	public void add() {
		setAttr("code", CODE);
		render(BASE_PATH + "parameter_add.html");
	}

	public void edit() {
		String id = getPara(0);
		Parameter parameter = service.findById(id);
		setAttr("model", JsonKit.toJson(parameter));
		setAttr("id", id);
		setAttr("code", CODE);
		render(BASE_PATH + "parameter_edit.html");
	}

	public void view() {
		String id = getPara(0);
		Parameter parameter = service.findById(id);
		setAttr("model", JsonKit.toJson(parameter));
		setAttr("id", id);
		setAttr("code", CODE);
		render(BASE_PATH + "parameter_view.html");
	}

	public void list() {
		Object grid = paginate(LIST_SOURCE, new ParameterIntercept());
		renderJson(grid);
	}

	public void save() {
		Parameter parameter = mapping(PERFIX, Parameter.class);
		
		Record rcd = Record.create().set("code", parameter.getCode().trim());
		Parameter para = service.findFirstBy(" where code=#{code}", rcd);
		if(para != null) {
			renderJson(warn("该编码已经存在，不允许重复记录！"));
			return;
		}
		boolean temp = service.save(parameter);
		if (temp) {
			renderJson(success(SAVE_SUCCESS_MSG));
		} else {
			renderJson(error(SAVE_FAIL_MSG));
		}
	}

	public void update() {
		Parameter parameter = mapping(PERFIX, Parameter.class);
		parameter.setVersion(parameter.getVersion()+1);
		boolean temp = service.update(parameter);
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