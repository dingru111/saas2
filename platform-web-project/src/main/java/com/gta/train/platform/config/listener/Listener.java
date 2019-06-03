package com.gta.train.platform.config.listener;

import java.util.EventListener;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;

@Configuration
public class Listener {
	@Bean
	public ServletListenerRegistrationBean<EventListener> getDemoListener() {
		ServletListenerRegistrationBean<EventListener> registrationBean = new ServletListenerRegistrationBean<>();
		registrationBean.setListener(new RequestContextListener());
		// registrationBean.setOrder(1);
		return registrationBean;
	}
}
