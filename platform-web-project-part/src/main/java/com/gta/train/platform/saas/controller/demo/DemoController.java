package com.gta.train.platform.saas.controller.demo;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gta.train.platform.common.base.controller.BaseController;

 

@Controller(value="demoController")
@RequestMapping("/demo") // 统一包//
public class DemoController  extends BaseController{
	private static final Logger LOG = Logger.getLogger(DemoController.class);
	 
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	@ResponseBody
	public String success( ){//返回ajax
		LOG.info("success1");
		return "success";
	} 
 
	@GetMapping("/demo")
	public String editTime( ModelMap map) {//返回页面
		LOG.info("demo");
		map.put("obj", "我是测试数据1");
		return "/demo/demo";
	}
}
