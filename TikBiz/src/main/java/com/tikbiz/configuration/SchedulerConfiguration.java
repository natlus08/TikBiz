package com.tikbiz.configuration;

import java.net.InetSocketAddress;
import java.net.Proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.tikbiz.service.ScheduledTasks;

@Configuration
public class SchedulerConfiguration {
	
	@Autowired
	private Environment environment;
	
	@Bean
	public ScheduledTasks scheduledTasks(){
		return new ScheduledTasks();
	}
	
	@Bean
	public RestTemplate restTemplate() {
		 SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		 InetSocketAddress address = new InetSocketAddress(environment.getProperty("tikbiz.proxy"),Integer.parseInt(environment.getProperty("tikbiz.port")));
		 Proxy proxy = new Proxy(Proxy.Type.HTTP,address);
		 factory.setProxy(proxy);
		 return new RestTemplate(factory);
	}
}
