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
 * 无人招标的项目自动撤销
 * 804>>项目自动撤销时效>>1>>1
 * 
 * @author skybiran
 *
 */
public class AutoItemsCancel implements Job {
	
	private Blade blade = Blade.create(WorkOrder.class);

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		String code = AgingType.AGE_804.getStatusVal();
		Parameter parameter = CacheKit.get(ConstCache.DEFAULT_CACHE, "getParamter_" + code, new IDataLoader() {
			@Override
			public Object load() {
				return Blade.create(Parameter.class).findFirstBy("CODE=#{code}", Record.create().set("code", code));
			}
		});
		
		String condition = AgingTypeUtil.dateCondition(parameter.getAgingtype(), parameter.getPara());
		
		Record para = Record.create().set("status", ItemStatus.PUBLISH.getStatusVal());
		List<WorkOrder> workOrderList = blade.findBy("where status = #{status} and date_add(publish_time, "+condition+") < now()", para);
		if(workOrderList == null || workOrderList.isEmpty()){
			LogKit.info("定时任务[无人招标的项目自动撤销], 暂无可操作记录!");
			return;
		}
		
		String[] array = new String[workOrderList.size()];
		for(int i=0; i < workOrderList.size(); i++){
			array[i] = String.valueOf(workOrderList.get(i).getId());
		}		
		
		Record params = Record.create().set("ids", array);
		params.set("status", ItemStatus.REVOCATION.getStatusVal());
		params.set("cancelTime", new Date());
		
		try {
			boolean done = blade.updateBy("status=#{status}, revocation_time=#{cancelTime}", "id in (#{join(ids)})", params);
			if(done)
				LogKit.info("【无人招标的项目自动撤销】定时处理成功！"+new Date());
			else {
				LogKit.info("【无人招标的项目自动撤销】定时处理失败！"+new Date());
			}
		} catch (Exception e) {
			LogKit.error("【无人招标的项目自动撤销】更新异常！", e);
			e.printStackTrace();			
		}
	}

}
