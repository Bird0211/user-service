package com.demo.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigurationProperties
@EnableAspectJAutoProxy
@EnableAsync
@ComponentScan(basePackages = {
		"com.demo.user.controller",
		"com.demo.user.service",
		"com.demo.user.adapter",
		"com.demo.user.config",
		"com.demo.user.config.properties",
		"com.demo.user.advice"

})
public class UsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersApplication.class, args);
	}

}
