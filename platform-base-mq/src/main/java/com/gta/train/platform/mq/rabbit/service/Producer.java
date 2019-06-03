package com.gta.train.platform.mq.rabbit.service;

import com.gta.train.platform.mq.rabbit.work.dto.Mail;

public interface Producer {
	public void sendMail(String queue,Mail mail);//向队列queue发送消息
}
