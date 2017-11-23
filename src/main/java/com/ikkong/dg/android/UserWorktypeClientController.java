package com.ikkong.dg.android;

import java.util.Date;
import java.util.List;

import com.ikkong.common.constant.CommonConts;
import com.ikkong.core.base.BaseController;
import com.ikkong.core.dao.Blade;
import com.ikkong.core.dao.Db;
import com.ikkong.core.toolbox.Record;
import com.ikkong.core.toolbox.support.BladePage;
import com.ikkong.dg.model.UserWorktype;
import com.ikkong.dg.service.UserWorktypeService;
import com.ikkong.dg.service.impl.UserWorktypeServiceImpl;

/**
 * 电工工种记录
 * 
 * @author sparker
 *
 */
public class UserWorktypeClientController extends BaseController {
	
	private static String PAGING_QUERY = "UserWorktype.findByPage";
	
	private static int pageSize = CommonConts.CLIENT_PAGE_SIZE;

	private UserWorktypeService uwtServ = new UserWorktypeServiceImpl();
	
	public void add() {
		Integer status = getParaToInt("status");
		Long workTypeId = getParaToLong("workTypeId");
		
		if(workTypeId == null || status == null || workTypeId <= 0) {
			renderJson(error("工种或审核状态不能为空"));
			return;
		}
		
		UserWorktype uwType = new UserWorktype();
		Long userId = dgUserToken().getUserId();
		uwType.setDg_userid(userId);
		uwType.setStatus(status);
		uwType.setVersion(1);
		uwType.setWorktype_id(workTypeId);
		uwType.setCreate_time(new Date());
		
		try {
			if(uwtServ.save(uwType)) {
				renderJson(success("用户工种记录添加成功！"));
			} else {
				renderJson(error("用户工种记录添加失败！"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderJson(fail("用户工种记录添加失败！"));
		}
	}
	
	public void query() {
		Integer page = getParaToInt("page");
		Long userId = dgUserToken().getUserId();
		if (page == null) {
			page = 1;
		}

		int start = (page - 1) * pageSize + 1;// 分页起始位置
		Record paraMap = Record.create().set("dgUserId", userId);
		String sql = Blade.dao().getScript(PAGING_QUERY).getSql();
		StringBuffer sqlBuf = new StringBuffer(sql);
		sqlBuf.append(" where a.dg_userid = #{dgUserId}");
		sqlBuf.append(" order by a.create_time desc");

		try {
			BladePage<Object> bladePage = Db.init().paginate(sqlBuf.toString(), paraMap, start,
					pageSize);
			List<Object> list = bladePage.getRows();
			if (list == null || list.isEmpty()) {
				renderJson(success("暂无对应记录!"));
			} else {
				renderJson(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderJson(error("获取失败，服务端异常！"));
		}
	}
}
