package com.simple.asyncexams.simple;

import java.util.Collection;

import org.springframework.scheduling.annotation.Async;

public interface NotificationService {
	@Async
	public void sendNotification(String subject, String message, Collection<String> recipients);
}
