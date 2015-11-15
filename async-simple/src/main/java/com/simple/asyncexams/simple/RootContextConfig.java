package com.simple.asyncexams.simple;

import java.util.concurrent.Executor;

import java.util.logging.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Controller;
import org.springframework.util.ErrorHandler;

@Configuration
@EnableAsync(proxyTargetClass = true)
@EnableScheduling
@ComponentScan(basePackages = "com.simple.asyncexams.simple",
				excludeFilters = @ComponentScan.Filter(Controller.class))
public class RootContextConfig implements AsyncConfigurer, SchedulingConfigurer{
	private static final Logger logger = Logger.getLogger(RootContextConfig.class.getName());
	
	// @Async와 @Scheduled메서드는 이제 동일 쓰레드 풀 사용
	@Bean
	public ThreadPoolTaskScheduler taskScheduler(){
		logger.info("\n\nSetting task scheduler\n\n");
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setPoolSize(20);
		scheduler.setThreadNamePrefix("Task -- ");
		scheduler.setWaitForTasksToCompleteOnShutdown(true);
		scheduler.setErrorHandler(new ErrorHandler(){
			@Override
			public void handleError(Throwable t) {
				logger.info("Execution of task {} was rejected for some reason.");
			}});
		return scheduler;
	}
	
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		logger.info("\n\nConfiguring scheduled.\n\n");
		TaskScheduler scheduler = this.taskScheduler();
		taskRegistrar.setTaskScheduler(scheduler);
		
	}

	@Override
	public Executor getAsyncExecutor() {
		Executor executor = this.taskScheduler();
		logger.info("\n\nConfiguring async method executor.\n\n");
		return executor;
	}
}
