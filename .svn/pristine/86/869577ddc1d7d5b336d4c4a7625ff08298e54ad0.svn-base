package com.ikkong.dg.meta.blade;

import com.ikkong.core.dao.Blade;
import com.ikkong.core.toolbox.Record;
import com.ikkong.system.model.Parameter;

/**
 * Blade 单表查询
 * 
 * @author skybiran
 *
 */
public class BladeUtils {

	/**
	 * 
	 * @return
	 */
	public static Parameter getParaConfByCode(String agingCode) {
		Parameter paraType = Blade.create(Parameter.class).findFirstBy(" where code=#{code}",
				Record.create().set("code", agingCode));
		return paraType;
	}

	/**
	 * 
	 * @param agingCode
	 * @return
	 */
	public static Integer getParaValByCode(String agingCode) {
		Parameter para = getParaConfByCode(agingCode);
		if (para != null) {
			return Integer.valueOf(para.getPara());
		}
		return null;
	}
}
