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
import org.springframework.util.ErrorHandler;

@Configuration
@EnableAsync(proxyTargetClass = true)
@EnableScheduling
@ComponentScan(basePackages = "com.simple.asyncexams.simple")
public class TestRootContextConfig implements AsyncConfigurer, SchedulingConfigurer{
	private static final Logger logger = Logger.getLogger(TestRootContextConfig.class.getName());
	
	// @Async와 @Scheduled메서드는 이제 동일 쓰레드 풀 사용
	@Bean
	public ThreadPoolTaskScheduler taskScheduler(){
		logger.info("Setting task scheduler in Test Config");
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setPoolSize(20);
		scheduler.setThreadNamePrefix("Task -- ");
		scheduler.setWaitForTasksToCompleteOnShutdown(true);
		scheduler.setErrorHandler(new ErrorHandler(){
			@Override
			public void handleError(Throwable t) {
				logger.info("Execution of task {} was rejected for some reason in Test Config.");
			}});
		return scheduler;
	}
	
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		TaskScheduler scheduler = this.taskScheduler();
		taskRegistrar.setTaskScheduler(scheduler);
		
	}

	@Override
	public Executor getAsyncExecutor() {
		Executor executor = this.taskScheduler();
		logger.info("Configuring async method executor in Test Config.");
		return executor;
	}
}
