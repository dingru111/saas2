package com.gta.train.platform.mq.rabbit.work.customer;

import java.util.List;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.gta.train.platform.mq.rabbit.work.dto.Mail;
import com.gta.train.platform.mq.rabbit.work.dto.Mail2;


//@Component
public class DirectListener1 {
	@RabbitListener(queues = "directqueue1")
	public void displayMail(Mail mail) throws AmqpException{
		System.out.println("DirectListener1 : directqueue1队列监听器1号收到消息"+mail.toString());
		//throw new AmqpException("");
	}
	
	/*@RabbitListener(queues = "directqueue2")
	public void displayMail2(Mail2 mail) throws AmqpException {
		System.err.println("DirectListener1 : directqueue2队列监听器2号收到消息"+mail.toString());
		//throw new AmqpException("");
	}
	*/
	@RabbitListener(queues = "directqueue2")
	public void displayMaillist(Mail list) throws AmqpException {
		System.err.println("DirectListener1 : directqueue2队列监听器2号收到消息"+list.toString());
		//throw new AmqpException("");
	}
	
	@RabbitListener(queues = "directqueue2")
	public void foo( Message message) {
		System.err.println("message : directqueue2队列监听器2号收到消息"+message.toString());
	}
}

