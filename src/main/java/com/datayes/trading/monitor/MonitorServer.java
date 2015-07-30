package com.datayes.trading.monitor;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import com.datayes.trading.monitor.service.ReadLogService;

/**
 * Hello world!
 *
 */
public class MonitorServer 
{
    public static void main( String[] args )
    {
    	if(args.length < 1){
    		throw new RuntimeException("Please provide scan file path!");
    	}
    	if(args.length < 2){
    		throw new RuntimeException("Please provide scan time seconds!");
    	}
    	
       ReadLogService service = new ReadLogService(args[0], Integer.parseInt(args[1]));
       service.start();
       
    	//config server context
       ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
       context.setContextPath("/trading");
       Server jettyServer = new Server(7070);
       jettyServer.setHandler(context);
       
       //config web container servlet
       ResourceConfig config = new ResourceConfig();
       config.packages("com.datayes.trading.monitor.controller");
       config.register(JacksonFeature.class);
       ServletContainer container = new ServletContainer(config);
       ServletHolder jerseyServlet = new ServletHolder(container);
       jerseyServlet.setInitOrder(0);
       context.addServlet(jerseyServlet, "/*");
       
       jerseyServlet.setInitParameter("jersey.config.server.provider.packages", 
    		   "com.datayes.trading.monitor.controller");
       try {
    	   jettyServer.start();
    	   jettyServer.join();
       } catch (Exception e) {
    	   e.printStackTrace();
       } finally {
    	   service.stop();
           jettyServer.destroy();
       }
    }
}
