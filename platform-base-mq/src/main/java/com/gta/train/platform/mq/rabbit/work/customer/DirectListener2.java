package com.gta.train.platform.mq.rabbit.work.customer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.gta.train.platform.mq.rabbit.work.dto.Mail;
import com.gta.train.platform.mq.rabbit.work.dto.Mail2;



//@Component
public class DirectListener2 {
	@RabbitListener(queues = "directqueue2")
	public void displayMail(Mail mail) throws Exception {
		System.out.println("DirectListener2 : directqueue2队列监听器2号收到消息"+mail.toString());
	}
	@RabbitListener(queues = "directqueue2")
	public void displayMail(Mail2 mail) throws Exception {
		System.out.println("DirectListener2 : Mail2 directqueue2队列监听器2号收到消息"+mail.toString());
	}
}
