package com.ikkong.common.jobs;

import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.ikkong.common.constant.CommonConts.AgingType;
import com.ikkong.common.constant.CommonConts.ItemStatus;
import com.ikkong.common.utils.AgingTypeUtil;
import com.ikkong.core.constant.ConstCache;
import com.ikkong.core.dao.Blade;
import com.ikkong.core.toolbox.Record;
import com.ikkong.dg.model.WorkOrder;
import com.ikkong.system.model.Parameter;
import com.jfinal.kit.LogKit;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.IDataLoader;

/**
 * 803>>工种单自动将已招标的设置为进行中
 * 
 * @author skybiran
 *
 */
public class AutoItemsBegining implements Job {
	
	private Blade blade = Blade.create(WorkOrder.class);

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		String code = AgingType.AGE_803.getStatusVal();
		Parameter parameter = CacheKit.get(ConstCache.DEFAULT_CACHE, "getParamter_" + code, new IDataLoader() {
			@Override
			public Object load() {
				return Blade.create(Parameter.class).findFirstBy("CODE=#{code}", Record.create().set("code", code));
			}
		});
		
		String condition = AgingTypeUtil.dateCondition(parameter.getAgingtype(), parameter.getPara());
		
		Record para = Record.create().set("status", ItemStatus.OVER_BIDED.getStatusVal());
		List<WorkOrder> workOrderList = blade.findBy("where status = #{status} and date_add(overbid_time, "+condition+") < now()", para);
		if(workOrderList == null || workOrderList.isEmpty()){
			LogKit.info("定时任务[工种单自动将已招标的设置为进行中], 暂无可操作记录!");
			return;
		}
		
		String[] array = new String[workOrderList.size()];
		for(int i=0; i < workOrderList.size(); i++){
			array[i] = String.valueOf(workOrderList.get(i).getId());
		}		
		
		Record params = Record.create().set("ids", array);
		params.set("status", ItemStatus.INPROCCESS.getStatusVal());
		params.set("proceedTime", new Date());
		
		try {
			boolean done = blade.updateBy("status=#{status}, proceed_time=#{proceedTime}", "id in (#{join(ids)})", params);
			if(done)
				LogKit.info("【工种单自动将已招标的设置为进行中】定时处理成功！"+new Date());
			else {
				LogKit.info("【工种单自动将已招标的设置为进行中】定时处理失败！"+new Date());
			}
		} catch (Exception e) {
			LogKit.error("【工种单自动将已招标的设置为进行中】更新异常！", e);
			e.printStackTrace();			
		}
	}

}
