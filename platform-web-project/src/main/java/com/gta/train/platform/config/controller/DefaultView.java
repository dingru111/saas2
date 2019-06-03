package com.gta.train.platform.config.controller;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.gta.train.platform.config.interceptor.DemoInterceptor;

//@Configuration
public class DefaultView extends WebMvcConfigurerAdapter {

 
   // @Autowired
    private DemoInterceptor examInterceptor;//考试拦截

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/WEB-INF/jsp/nologin/main.jsp");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }


    //统一页码处理配置
   // @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                //ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
                ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404.html");
                // ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/Err500.html");

                container.addErrorPages(error404Page);
            }
        };
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(examInterceptor).addPathPatterns("/**")
                ///无需拦截的url
                .excludePathPatterns("/commons/**","/nologin/**", "/nologin/exam/submitExam","/nologin/error/**"
                        ,"/nologin/exam/yyyy/examTimeOut","/nologin/person/epay/**"
                        ,"/nologin/serviceProvider/**","/nologin/interfaceService/**");
    }

}
