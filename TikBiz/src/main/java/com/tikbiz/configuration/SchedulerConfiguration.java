package com.tikbiz.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tikbiz.service.ScheduledTasks;

@Configuration
public class SchedulerConfiguration {
	@Bean
	public ScheduledTasks scheduledTasks(){
		return new ScheduledTasks();
	}
}
