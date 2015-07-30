package com.datayes.trading.monitor.service;

import java.util.HashMap;
import java.util.Map;

public class Cache {
	private Map monitorInfoMap ;
	private Cache(){
		monitorInfoMap = new HashMap<>(50);
	}
	public Map getMonitorInfoMap(){
		return monitorInfoMap;
	}
	public static Cache getCache(){
		return CacheHolder.cache;
	}
	private static class CacheHolder{
		static Cache cache = new Cache();
	}
}
