package com.gss.testers;

import java.sql.Date;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;

public class PromoChecker {
	
	public static void check() throws SchedulerException{
		//Creating scheduler factory and scheduler
		SchedulerFactory factory = new StdSchedulerFactory();
		Scheduler scheduler = factory.getScheduler();

		//Creating Job and link to our Job class
		JobDetailImpl jobDetail = new JobDetailImpl();
		jobDetail.setName("Promo Date Checker");
		jobDetail.setJobClass(PromoCheckerJob.class);

		//Creating schedule time with trigger
		SimpleTriggerImpl simpleTrigger = new SimpleTriggerImpl();
		simpleTrigger.setStartTime(new Date(System.currentTimeMillis() + 1000));
		simpleTrigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
		simpleTrigger.setRepeatInterval(3000);
		simpleTrigger.setName("FirstTrigger");

		//Start scheduler
		scheduler.start();
		scheduler.scheduleJob(jobDetail,simpleTrigger);
	}
}
