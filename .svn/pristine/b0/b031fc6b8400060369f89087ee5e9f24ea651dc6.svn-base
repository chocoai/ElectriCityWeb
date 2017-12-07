package com.ikkong.common.jobs;

import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.ikkong.common.constant.CommonConts.AgingType;
import com.ikkong.common.constant.CommonConts.ApplyStatus;
import com.ikkong.common.constant.CommonConts.ApplyType;
import com.ikkong.common.utils.AgingTypeUtil;
import com.ikkong.core.constant.ConstCache;
import com.ikkong.core.dao.Blade;
import com.ikkong.core.toolbox.Record;
import com.ikkong.dg.model.OrderApply;
import com.ikkong.system.model.Parameter;
import com.jfinal.kit.LogKit;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.IDataLoader;

/**
 * 申请记录定时变更状态
 * 默认将status修改为同意
 * 
 * @author sparker
 *
 */
public class AutoApplyAgress implements Job {
	
	private Blade blade = Blade.create(OrderApply.class);	

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		String code = AgingType.AGE_801.getStatusVal();
		Parameter parameter = CacheKit.get(ConstCache.DEFAULT_CACHE, "getParamter_" + code, new IDataLoader() {
			@Override
			public Object load() {
				return Blade.create(Parameter.class).findFirstBy("CODE=#{code}", Record.create().set("code", code));
			}
		});
		
		String condition = AgingTypeUtil.dateCondition(parameter.getAgingtype(), parameter.getPara());
		String[] types = {String.valueOf(ApplyType.APPLY.getTypeVal()), String.valueOf(ApplyType.ABORT.getTypeVal()), String.valueOf(ApplyType.OVERTIME.getTypeVal())};
		
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append("where status = #{status} and date_add(create_time, "+condition+") < now() ");
		sqlBuf.append(" and type in (#{join(types)}) ");
		
		Record para = Record.create().set("status", ApplyStatus.APPLYING.getStatusVal());		
		para.set("types", types);
		List<OrderApply> applyList = blade.findBy(sqlBuf.toString(), para);
		if(applyList != null && !applyList.isEmpty()) {
			for(OrderApply apply: applyList) {
				apply.setVersion(apply.getVersion()+1);
				apply.setStatus(ApplyStatus.AGREE.getStatusVal());
			}
			try {
				blade.updateBathById(applyList);
			} catch (Exception e) {
				e.printStackTrace();
				LogKit.error("申请记录定时变更[同意]状态异常!", e);
			}
		}		
	}
}
