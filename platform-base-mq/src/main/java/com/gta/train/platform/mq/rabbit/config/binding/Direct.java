package com.gta.train.platform.mq.rabbit.config.binding;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class Direct {
 	//3个binding将交换机和相应队列连起来
	@Autowired 
	private Queue directqueue1;
	@Autowired
	private Queue directqueue2;
	@Autowired
	private DirectExchange directExchange;
 	@Bean
 	@DependsOn(value= {"directqueue1","directExchange"})
 	public Binding bindingorange(){
 		Binding binding=BindingBuilder.bind(directqueue1).to(directExchange).with("orange");
 		return binding;
 	}
 	
 	@Bean
 	@DependsOn(value= {"directqueue2","directExchange"})
 	public Binding bindingblack(){
 		Binding binding=BindingBuilder.bind(directqueue2).to(directExchange).with("black");
 		return binding;
 	}
 	
 	@Bean
 	@DependsOn(value= {"directqueue2","directExchange"})
 	public Binding bindinggreen(){
 		Binding binding=BindingBuilder.bind(directqueue2).to(directExchange).with("green");
 		return binding;
 	}
 	
}
