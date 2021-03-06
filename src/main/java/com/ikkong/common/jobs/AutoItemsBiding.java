package com.ikkong.common.jobs;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.ikkong.common.constant.CommonConts.AgingTypeCode;
import com.ikkong.common.constant.CommonConts.ItemStatus;
import com.ikkong.common.constant.CommonConts.MsgTempCode;
import com.ikkong.common.constant.CommonConts.MsgType;
import com.ikkong.common.constant.SmsTempConts;
import com.ikkong.common.utils.AgingTypeUtil;
import com.ikkong.common.utils.SmsSender;
import com.ikkong.core.constant.ConstCache;
import com.ikkong.core.dao.Blade;
import com.ikkong.core.toolbox.Record;
import com.ikkong.dg.model.WorkOrder;
import com.ikkong.dg.service.DgUserService;
import com.ikkong.dg.service.MessagesService;
import com.ikkong.dg.service.impl.DgUserServiceImpl;
import com.ikkong.dg.service.impl.MessagesServiceImpl;
import com.ikkong.system.model.Parameter;
import com.jfinal.kit.LogKit;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.IDataLoader;

/**
 * 201>>工种单自动将发布中设置为招标中
 * 
 * 自动设置为招标中
 * 
 * @author sparker
 *
 */
public class AutoItemsBiding implements Job {
	
	private Blade blade = Blade.create(WorkOrder.class);
	private DgUserService userServ = new DgUserServiceImpl();
	private MessagesService msgServ = new MessagesServiceImpl();
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		String code = AgingTypeCode.AGE_201.getCodeVal();
		Parameter parameter = CacheKit.get(ConstCache.DEFAULT_CACHE, "getParamter_" + code, new IDataLoader() {
			@Override
			public Object load() {
				return Blade.create(Parameter.class).findFirstBy("CODE=#{code}", Record.create().set("code", code));
			}
		});
		
		if(parameter == null) {
			LogKit.info("工种单自动将发布中设置为招标中参数未配置!");
			return;
		}
		
		String condition = AgingTypeUtil.dateCondition(parameter.getAgingtype(), parameter.getPara());
		
		Record para = Record.create().set("status", ItemStatus.PUBLISH.getStatusVal());
		List<WorkOrder> workOrderList = blade.findBy("where status = #{status} and date_add(publish_time, "+condition+") < now()", para);
		if(workOrderList == null || workOrderList.isEmpty()){
			LogKit.info("定时任务[工种单自动将发布中设置为招标中], 暂无可操作记录!");
			return;
		}
		
		Set<Long> userIds = new HashSet<Long>();
		String[] array = new String[workOrderList.size()];
		for(int i=0; i < workOrderList.size(); i++){
			WorkOrder wo = workOrderList.get(i);
			array[i] = String.valueOf(wo.getId());
			userIds.add(wo.getCreate_id());
		}		
		
		Record params = Record.create().set("ids", array);
		params.set("status", ItemStatus.BIDDING.getStatusVal());
		params.set("bidTime", new Date());
		
		try {
			boolean done = blade.updateBy("status=#{status}, biding_time=#{bidTime}", "id in (#{join(ids)})", params);
			if(done) {
				
				List<String> phoneList = userServ.findPhonesByList(userIds);
				SmsSender.sendTipSms(SmsTempConts.ITEM_PASSED_AUDIT_TMP, StringUtils.join(phoneList, ","));
				msgServ.addMsgByUserList(userIds, MsgType.EVENT_MSG.getStatusVal(), MsgTempCode.TMP_1037.getTmpVal());
				LogKit.info("【工种单自动将发布中设置为招标中】定时处理成功！"+new Date());
			} else {
				LogKit.info("【工种单自动将发布中设置为招标中】定时处理失败！"+new Date());
			}
		} catch (Exception e) {
			LogKit.error("【工种单自动将发布中设置为招标中】更新异常！", e);
			e.printStackTrace();			
		}
	}

}
