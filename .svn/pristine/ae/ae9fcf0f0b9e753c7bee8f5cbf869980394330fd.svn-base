package com.ikkong.dg.meta.intercept;

import java.util.List;
import java.util.Map;

import com.ikkong.core.aop.AopContext;
import com.ikkong.core.meta.PageIntercept;
import com.ikkong.core.toolbox.Func;
import com.ikkong.core.toolbox.support.BladePage;

public class DgUserIntercept extends PageIntercept {

	/**
	 * 查询后操作 字典项、部门不通过数据库查询,通过缓存附加,减轻数据库压力,提高分页效率
	 * 
	 * @param ac
	 */
	@SuppressWarnings("unchecked")
	public void queryAfter(AopContext ac) {
		BladePage<Map<String, Object>> page = (BladePage<Map<String, Object>>) ac.getObject();
		List<Map<String, Object>> list = page.getRows();
		for (Map<String, Object> map : list) {
			map.put("statusName", Func.getDictName(208, map.get("status")));
			map.put("genderName", Func.getDictName(101, map.get("gender")));
		}
	}
}
