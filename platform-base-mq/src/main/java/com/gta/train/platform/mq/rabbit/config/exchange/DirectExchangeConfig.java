package com.gta.train.platform.mq.rabbit.config.exchange;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//direct直连模式的交换机配置,包括一个direct交换机，两个队列，三根网线binding
@Configuration
public class DirectExchangeConfig {
	@Bean(name="directExchange")
 	public DirectExchange directExchange(){
		DirectExchange directExchange=new DirectExchange("direct");
		 
 		return directExchange;
 	}
	 
}
