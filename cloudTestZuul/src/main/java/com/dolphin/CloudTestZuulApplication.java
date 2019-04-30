package com.dolphin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.transform.impl.AccessFieldTransformer;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringCloudApplication
@EnableHystrixDashboard
@EnableEurekaClient
public class CloudTestZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudTestZuulApplication.class, args);
	}
	
	@Bean
	public AccessFilterTtest2 accessFieldTransformer() {
		return new AccessFilterTtest2();
		
	}
	
}
