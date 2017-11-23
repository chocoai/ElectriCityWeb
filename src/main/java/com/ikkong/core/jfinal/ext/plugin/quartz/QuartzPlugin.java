package com.ikkong.core.jfinal.ext.plugin.quartz;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.*;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.CronScheduleBuilder.cronSchedule;

import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.IPlugin;

public class QuartzPlugin implements IPlugin {

	private static Logger log = Logger.getLogger(QuartzPlugin.class);
	// private String propertiesFile = "quartz.properties";
	private String jobsFile = "quartz/jobs.properties";
	// private boolean isEnableRun = false;
	private List<String> jobNameList = new ArrayList<String>(); // Job 名称清单
	private Map<String, QuartzJob> JobsMap = new HashMap<String, QuartzJob>(); // Job
																				// 对象Map
	private SchedulerFactory sf;
	private Scheduler sched;

	public QuartzPlugin(Quartz q) {
		// this.propertiesFile = q.getPropFile();
		this.jobsFile = q.getJobsFile();
		// this.isEnableRun = q.isEnableRun();
		init();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean start() {
		// TODO Auto-generated method stub
		log.info("---------- QuartzPlugin start begin--------------");
		try {
			sf = new StdSchedulerFactory();
			sched = sf.getScheduler();

			for (int i = 0, length = jobNameList.size(); i < length; i++) {
				String jobName = jobNameList.get(i);
				QuartzJob jobObj = JobsMap.get(jobName);
				boolean isRun = jobObj.isRun();

				if (isRun) {
					Class<? extends Job> jobClass1;
					jobClass1 = (Class<? extends Job>) Class.forName(jobObj.getClassName().trim());

					JobDetail job = newJob(jobClass1).withIdentity(jobName, jobObj.getGroupName()).build();

					CronTrigger trigger = newTrigger().withIdentity("trigger1", jobObj.getGroupName())
							.withSchedule(cronSchedule(jobObj.getCron())).build();

					sched.scheduleJob(job, trigger);
					log.info("add " + jobName + " to quartz schedule ");
				} else {
					log.info(jobName + " run  is " + isRun);
				}
			}
			sched.start();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e.toString());
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e.toString());
		}
		log.info("---------- QuartzPlugin start end --------------");
		return true;
	}

	@Override
	public boolean stop() {
		log.info("---------- QuartzPlugin stop begin --------------");
		try {
			sched.shutdown(true);
			SchedulerMetaData metaData = sched.getMetaData();
			log.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e.toString());
		}
		log.info("---------- QuartzPlugin stop end --------------");
		return false;
	}

	private boolean init() {
		log.info("----------init Quartz Jobs begin -------------");
		boolean result = false;
		// Prop prop = PropKit.use(propertiesFile);
		Prop jobProp = PropKit.use(jobsFile);
		Enumeration<Object> e = jobProp.getProperties().keys();

		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			if (key.indexOf("job.name") == 0) {
				String jobName = jobProp.get(key);
				jobNameList.add(jobName);

				QuartzJob job = new QuartzJob();
				job.setClassName(jobProp.get(jobName + ".class"));
				job.setCron(jobProp.get(jobName + ".cron"));
				job.setGroupName(jobProp.get(jobName + ".group"));
				job.setRun(jobProp.getBoolean(jobName + ".run"));
				JobsMap.put(jobName, job);
				log.info("job.name." + jobName + " put to map");
			} else {
				continue;
			}
		}
		log.info("----------init Quartz Jobs end   -------------");
		return result;
	}

}
