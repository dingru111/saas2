package com.gta.train.platform.mq.rabbit.service.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gta.train.platform.mq.rabbit.service.Publisher;
import com.gta.train.platform.mq.rabbit.work.dto.Mail;
import com.gta.train.platform.mq.rabbit.work.dto.Mail2;

 

@Service("publisher")
public class PublisherImpl implements Publisher{
	@Autowired
	RabbitTemplate rabbitTemplate;

	public void publishMail(Mail mail) {
		rabbitTemplate.convertAndSend("fanout", "", mail);
	 
	}

	public void senddirectMail(Mail mail, String routingkey) {
		rabbitTemplate.convertAndSend("direct", routingkey, mail);
	}

	public void sendtopicMail(Mail mail, String routingkey) {
		rabbitTemplate.convertAndSend("mytopic", routingkey, mail);
	}

	@Override
	public void senddirectMail(Mail2 mail2, String routingkey) {
		// TODO 自动生成的方法存根
		rabbitTemplate.convertAndSend("direct", routingkey, mail2);
	}
 

	
	
}
