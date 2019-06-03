package com.gta.train.platform.mq.rabbit.work.customer;


import java.io.IOException;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.gta.train.platform.mq.rabbit.work.dto.Mail;
import com.rabbitmq.client.Channel;


@Component
public class DirectListener3 {
	 
	
 
	 @RabbitListener(queues = "directqueue2")
	public void notSuccess(Mail mail, Message message,Channel channel) {
		System.err.println("message : DirectListener3"+mail.toString()+"失败返回");
		try {//接收失败后数据放入队列
			channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			System.err.println("消息发送失败"+e.toString());
		}
	}
	@RabbitListener(queues = "directqueue2")
	public void isSuccess(Mail mail, Message message) {
		System.out.println("message : DirectListener3"+mail.toString());
	}
 
}
