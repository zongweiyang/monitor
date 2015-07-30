package com.datayes.trading.monitor.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.datayes.trading.monitor.service.Cache;

@Path("/health")
public class HealthCheck {
	
	@GET
	@Path("/check")
	@Produces(MediaType.APPLICATION_JSON)
	public Object check(){
		return Cache.getCache().getMonitorInfoMap();
	}
}
