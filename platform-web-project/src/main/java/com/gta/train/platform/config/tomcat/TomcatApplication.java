package com.gta.train.platform.config.tomcat;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.gta.train.platform.PortalApplication;

public class TomcatApplication extends SpringBootServletInitializer{

	/**
     * war包启动类  
     * return 中传入的对象是需要有@SpringBootApplication 注解的启动类
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(PortalApplication.class);
    }
}
