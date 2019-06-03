package com.gta.train.platform.base.datasource;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig { 
	
	@Bean(name = "dataSource")
	public DataSource datasource(Environment env) throws NumberFormatException, SQLException {
		HikariDataSource ds = new HikariDataSource();
		ds.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
		ds.setJdbcUrl(env.getProperty("spring.datasource.url"));
		ds.setUsername(env.getProperty("spring.datasource.username"));
		ds.setPassword(env.getProperty("spring.datasource.password"));
		ds.setReadOnly(Boolean.valueOf(env.getProperty("spring.datasource.readOnly")));
		ds.setConnectionTimeout(Long.valueOf(env.getProperty("spring.datasource.connectionTimeout")));
		ds.setIdleTimeout(Long.valueOf(env.getProperty("spring.datasource.idleTimeout")));
		ds.setValidationTimeout(Long.valueOf(env.getProperty("spring.datasource.validationTimeout")));
		ds.setLoginTimeout(Integer.valueOf(env.getProperty("spring.datasource.loginTimeout")));
		ds.setMaxLifetime(Long.valueOf(env.getProperty("spring.datasource.maxLifetime")));
		ds.setMaximumPoolSize(Integer.valueOf(env.getProperty("spring.datasource.maximumPoolSize")));
		return ds;
	}
}
