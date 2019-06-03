package com.gta.train.platform.base.datasource;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.gta.train.platform.config.project.ProjectConfig;
import com.gta.train.platform.persis.mybatis.plugin.FunctionMapper;

//import com.lkt.Professional.MyMappers.MyMapper;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;
@Configuration
@Component

//必须在MyBatisConfig注册后再加载MapperScannerConfigurer，否则会报错
@AutoConfigureAfter(MyBatisConfig.class)
public class MyBatisMapperScannerConfig {
	/* //@Autowired
	private ProjectConfig projectConfig;*/
	// @Autowired
	 //   private Environment env;
	@Bean 
	public MapperScannerConfigurer mapperScannerConfigurer(){// Environment env 
		//String projectName=projectConfig.getProjectName();
		String projectName="project"; 
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
		mapperScannerConfigurer.setBasePackage("com.gta.train.platform.saas.dao");	
		mapperScannerConfigurer.setMarkerInterface(FunctionMapper.class);
		Properties properties = new Properties();
	    properties.setProperty("notEmpty", "true");
	    properties.setProperty("ORDER", "BEFORE");
	    mapperScannerConfigurer.setProperties(properties);
	    return mapperScannerConfigurer;
	}
	/*public ProjectConfig getProjectConfig() {
		return projectConfig;
	}
	public void setProjectConfig(ProjectConfig projectConfig) {
		this.projectConfig = projectConfig;
	}
	 */

}
