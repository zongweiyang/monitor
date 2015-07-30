package com.datayes.trading.monitor.service;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class ReadLogService implements Runnable{
	
	private String path;
	private ScheduledExecutorService exec;
	private int timeSeconds;
	
	public ReadLogService(String path,int timeSeconds) {
		this.path = path;
		this.timeSeconds = timeSeconds;
		exec = Executors.newSingleThreadScheduledExecutor();
	}
	public void start(){
		exec.scheduleAtFixedRate(this, 0, timeSeconds, TimeUnit.SECONDS);
	}
	public void stop(){
		exec.shutdown();
	}
	
	@Override
	public void run() {
		System.out.println("Server starting " + path);
		Date date = new Date();
		Cache.getCache().getMonitorInfoMap().put("hello", "world"+date.toString());
	}
}
