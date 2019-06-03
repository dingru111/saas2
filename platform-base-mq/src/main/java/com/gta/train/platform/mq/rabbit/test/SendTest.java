package com.gta.train.platform.mq.rabbit.test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
 
import org.springframework.stereotype.Component;

import com.gta.train.platform.mq.rabbit.service.Producer;
import com.gta.train.platform.mq.rabbit.service.Publisher;
import com.gta.train.platform.mq.rabbit.work.dto.Mail;
import com.gta.train.platform.mq.rabbit.work.dto.Mail2;
 
@Component
public class SendTest implements ApplicationRunner {
    private static final Logger LOG = LoggerFactory.getLogger(SendTest.class);

    @Autowired
    private Producer producer;
    @Autowired
    private Publisher publisher;
    @Override
    public void run(ApplicationArguments args) throws Exception {
       System.out.println("00000000000000");
       int i=0;
       int ii=0;
       int iii=0;
       while(true) {
    	   Mail mail = new Mail(""+i++,"orange"+Mail.class,0.0);
           //publisher.senddirectMail(mail, "orange");
           
           mail = new Mail(""+ii++,"black"+Mail.class,0.0);
          // publisher.senddirectMail(mail, "black");
           
           mail = new Mail(""+iii++,"green"+Mail.class,0.0);
           publisher.senddirectMail(mail, "green");
           Thread.sleep(5000);
       }
      
    }
}
 