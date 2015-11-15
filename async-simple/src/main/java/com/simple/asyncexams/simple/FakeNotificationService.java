package com.simple.asyncexams.simple;

import java.util.Collection;
import java.util.logging.Logger;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class FakeNotificationService implements NotificationService {
	private static final Logger logger = Logger.getLogger(FakeNotificationService.class.getName());
	
	@Override
	@Async
	public void sendNotification(String subject, String message, Collection<String> recipients) {
		logger.info("Start notifying recipients.");
		for(int i=0;i<5;i++){
			try{
				Thread.sleep(2_000L);
			}catch(InterruptedException ignore){
				logger.info("Finished by some reason.");
			}
			logger.info("\n\n"+i+ "th message send.\n\n");
		}
		logger.info("\n\nreleaded thread.\n\n");
	}
}
