package com.gta.train.platform.config.tomcat;

import org.apache.catalina.valves.AccessLogValve;
import org.apache.catalina.valves.Constants;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

/**
 * <p> Title: </p>
 * <p> Description: tomcat的优化配置</p>
 * <p> Copyright: Copyright (c) 2017 </p>
 * <p> Company: www.gtafe.com </p>
 * @author fengya
 * @version 1.0
 * @date  2018年7月2日 下午4:55:00
 */
@SpringBootConfiguration
public class WebServerConfiguration {
	
	@Bean
    public EmbeddedServletContainerFactory embeddedServletContainerFactory(@Value("${tomcat.page.not_found.url}") String pageNotFoundUrl,@Value("${tomcat.maxConnections}") int maxConnections,
    		@Value("${tomcat.maxThreads}") int maxThreads,@Value("${tomcat.connectionTimeout}") int connectionTimeout,@Value("${tomcat.logPath}") String logPath) {
        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
        //设置端口
        //factory.setPort(8080);
        //设置404错误界面
        factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, pageNotFoundUrl));
        //设置在容器初始化的时候触发
        factory.addInitializers((servletContext) -> {
//            System.out.println(" = = = = 获取服务器信息 = = " + servletContext.getServerInfo());
        });
        //设置最大连接数和最大线程数
        factory.addConnectorCustomizers((connector) -> {
            Http11NioProtocol protocolHandler = (Http11NioProtocol) connector.getProtocolHandler();
            protocolHandler.setMaxConnections(maxConnections);
            protocolHandler.setMaxThreads(maxThreads);
            protocolHandler.setConnectionTimeout(connectionTimeout);
            protocolHandler.setCompression("on");
        });
        //设置访问日志记录文件的目录
        factory.addContextValves(getLogAccessLogValue(logPath));
        return factory;
    }

    private AccessLogValve getLogAccessLogValue(String logPath) {
        AccessLogValve accessLogValve = new AccessLogValve();
        accessLogValve.setDirectory(logPath);
        accessLogValve.setEnabled(true);
        accessLogValve.setPattern(Constants.AccessLog.COMMON_PATTERN);
        accessLogValve.setPrefix("tomcat-Access-Log");
        accessLogValve.setSuffix(".log");
        return accessLogValve;
    }
}
