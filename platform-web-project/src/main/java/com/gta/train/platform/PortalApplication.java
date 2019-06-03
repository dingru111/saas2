package com.gta.train.platform;

import com.gta.edu.sdk.util.SpringContextHolder;
import com.gta.train.platform.config.cache.InitCacheData;
import com.gta.train.platform.saas.service.platform.baseData.AreaService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;
import java.io.File;
import java.util.Arrays;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
public class PortalApplication   {

	@Value("${gta.upload.projectPath}")
	private String projectPath;

	@Bean
	MultipartConfigElement multipartConfigElement() {
		System.setProperty("java.io.tmpdir",projectPath);
		MultipartConfigFactory factory = new MultipartConfigFactory();
		String location = StringUtils.isNotBlank(projectPath) ? projectPath  : "/opt/tomcat/static/ebank/tmp";
		File tmpFile = new File(location);
		if (!tmpFile.exists()) {
			tmpFile.mkdirs();
		}
		System.out.println("--------初始化文件临时路径为------- "+location);
		factory.setLocation(location);
		return factory.createMultipartConfig();
	}

/*	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			//AreaService areaService = (AreaService) SpringContextHolder.getBean("areaService");
			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}

			//InitCacheData.getInstance().initData();

		};
	}*/


		public static void main(String[] args) {
		SpringApplication.run(PortalApplication.class, args);
	}
/*
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(EBankApplication.class);
	}*/
}
