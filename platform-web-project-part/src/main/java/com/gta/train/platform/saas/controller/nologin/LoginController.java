package com.gta.train.platform.saas.controller.nologin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author huan.xu
 * @version 1.0
 * @className LoginController
 * @description 登录控制类
 * @date 2019-05-07 10:15
 */
@Controller
public class LoginController {

    /**
     * @description 登录页
     * @author huan.xu
     * @date  2019-05-07 16:29:12
     * @param []
     * @return java.lang.String
     **/
    @GetMapping("/nologin/login")
    public String login(){
        return "/nologin/login";
    }

    /**
     * @description 首页
     * @author huan.xu
     * @date  2019-05-07 16:29:18
     * @param []
     * @return java.lang.String
     **/
    @GetMapping("/nologin/index")
    public String index(){
        return "/index";
    }
}