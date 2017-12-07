package com.ikkong.common.jobs;

import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import com.ikkong.core.dao.Blade;
import com.ikkong.system.model.Parameter;

public class RunJobs implements Job {

	@Override
	public void execute(JobExecutionContext jobExecution) throws JobExecutionException {
		// TODO Auto-generated method stub
		JobKey key = jobExecution.getJobDetail().getKey();

//        JobDataMap dataMap = jobExecution.getMergedJobDataMap();
		System.out.println(key.getName() + ", 》》》》》》" + new Date());

		//工种单自动将发布中设置为招标中
		//工种单自动将招标中设置为已招标
		//工种单进自动将已招标的设置为行中
		
		//项目状态，0保存,1发布中，2招标中，3已招标，4项目进行中，5完成，6终止，7撤销，8挂起

		
//		801>>自动变更招标中时效  >>2(AgingType)  >>2
//		802>>自动变更已招标时效   >>1(AgingType)  >>1
//		803>>自动变更进行中时效   >>1(AgingType)   >>1
//		804>>项目自动撤销时效      >>1(AgingType)   >>1
		
//		805>>发单拒接次数              >>5(AgingType)   	>>3
//		806>>接单人拒绝招标单次数    >>5(AgingType)   	>>3
//		807>>招标单自动进行时效       >>1(AgingType)   	>>2
//		808>>佣金获取次数                  >>5(AgingType)     >>3
//		809>>佣金额占比			>>4(AgingType)    	>>20
//		810>>加班确认变更时效	>>2(AgingType)		>>3
//		811>>终止项目次数		>>5(AgingType)		>>3
//		812>>消息过期时效		>>1(AgingType)		>>2
		

		List<Parameter> list = Blade.dao().select("Parameter.list", Parameter.class);
		if (list != null) {
			for (Parameter p : list) {
				System.out.println(p.getCode() + ">>" + p.getName() + ">>" + p.getAgingtype() + ">>" + p.getPara());
			}
		}
	}
	
}
