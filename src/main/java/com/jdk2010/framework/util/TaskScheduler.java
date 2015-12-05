package com.jdk2010.framework.util;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 任务调度类
 
 */
public class TaskScheduler extends Timer{
	
	public TaskScheduler(boolean isDeamon) {
		//daemon进程
		super(true);
	}
	
	/**
	 * 实例化一个新对象
	 * @param isDaemon 是否守护进程
	 * @return 任务调度对象
	 */
	public static TaskScheduler newInstance(boolean isDaemon){
		return new TaskScheduler(isDaemon);
	}
	
	/**
	 * 开始任务计划
	 * @param timerTask	定时任务对象
	 * @param startTime 开始时间 格式是 HH:mm:ss
	 * @param period 任务运行间隔的时间，毫秒
	 */
	public void schedule(TimerTask timerTask, String startTime, long period){
		String  firstDate = DateUtil.formatDateTime(new Date(), "yyyy-MM-dd") + " " + startTime;
		super.scheduleAtFixedRate(timerTask, DateUtil.parse(firstDate), period);
		System.out.println("任务将于 " + firstDate + " 启动，启动间隔时间为 " + period /1000+ " 秒");
	}
	
	/**
	 * 开始任务计划
	 * @param timerTask 定时任务对象
	 * @param delay 从启动起等待的时间，毫秒
	 * @param period 任务运行间隔的时间，毫秒
	 */
	public void schedule(TimerTask timerTask, long delay, long period) {
		super.scheduleAtFixedRate(timerTask, delay, period);
		System.out.println("任务将于 " + delay / 1000 + " 秒后启动，启动间隔时间为 " + period / 1000 + " 秒");
	}
}
