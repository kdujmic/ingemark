package hr.kresod.springbootingemark.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;


/*
 * Login environment on start and shuhtdown
 */

@Service
public class StartupServiceLog {
	
	@Value("${okolina}")
	String okolina;
	private Logger logger = LoggerFactory.getLogger(StartupServiceLog.class);
	

    @EventListener
    public void onStartup(ApplicationReadyEvent event) { 
    	
    	logger.info("Server started, environment: " + okolina);
    	
    }

    @EventListener
    public void onShutdown(ContextStoppedEvent event) { 
    	logger.info("Server stopped, environment: " + okolina); 
    }


}
