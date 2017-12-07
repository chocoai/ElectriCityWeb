package com.ikkong.dg.meta.intercept;

import java.util.List;
import java.util.Map;

import com.ikkong.core.aop.AopContext;
import com.ikkong.core.meta.PageIntercept;
import com.ikkong.core.toolbox.Func;
import com.ikkong.core.toolbox.support.BladePage;

public class WorkTypeIntercept extends PageIntercept {

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
			map.put("checkFlag", Func.getDictName(210, map.get("check")));
			map.put("orderFlag", Func.getDictName(211, map.get("order_flag")));
		}
	}
}
