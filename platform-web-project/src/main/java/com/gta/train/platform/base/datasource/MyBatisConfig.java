package com.gta.train.platform.base.datasource;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.gta.train.platform.persis.page.plugin.PageInterceptor;

@Configuration
//加上这个注解，使得支持事务
@EnableTransactionManagement
public class MyBatisConfig implements TransactionManagementConfigurer {
	@Autowired
    private DataSource dataSource;

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
         return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        bean.setMapperLocations(resolver.getResources("classpath*:/mapper/**/**.xml"));
        Interceptor baseOptPlugin= new com.gta.train.platform.persis.mybatis.plugin.baseopt.BaseOptPlugin() ;
        Interceptor pageInterceptor = new PageInterceptor() ;

        Interceptor[] plugins= {baseOptPlugin,pageInterceptor};
        bean.setPlugins(plugins);
        try {
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
