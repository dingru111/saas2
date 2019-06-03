package com.gta.train.platform;

 
 
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: 遇见小星
 * Email: tengxing7452@163.com
 * Date: 17-6-16
 * Time: 下午12:18
 * Describe: member应用测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseTest {

    /**
     * Spring RestTemplate的便利替代。你可以获取一个普通的或发送基本HTTP认证（使用用户名和密码）的模板
     * 这里不使用
     */
   
 

 
     
    public void test(){
        
        System.out.println("size:" );
        System.out.println("-----测试完毕-------");
      
    }
}