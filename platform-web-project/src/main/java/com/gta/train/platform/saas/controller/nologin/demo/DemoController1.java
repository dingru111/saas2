package com.gta.train.platform.saas.controller.nologin.demo;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gta.train.platform.common.base.controller.BaseController;
import com.gta.train.platform.persis.page.PageExample;
import com.gta.train.platform.persis.page.plugin.Page;
import com.gta.train.platform.saas.entity.base.LoginHistory;
import com.gta.train.platform.saas.service.base.LoginHistoryService;

import tk.mybatis.mapper.entity.Example.Criteria;

@Controller(value="nologinDemoController1")
@RequestMapping("nologin/demo1") // 统一包//
public class DemoController1  extends BaseController{
	private static final Logger LOG = Logger.getLogger(DemoController1.class);
	@Autowired
	private LoginHistoryService loginHistoryService;
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
	
	//1 分页一定要使用POST方法  避免出现乱码
	/**个人登录日志 详情
	 * @param map
	 * @param operateId
	 * @return
	 */
	@RequestMapping(value = "personLogs", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView personLogs(ModelMap map,String loginIp) {
	/*	LoginHistory loginHistory = new LoginHistory();
		Page page=getPage();//2 使用此方法获得对象 不能new
		page.setPageSize(15);//3 可选参数设置每页最大显示条数 默认 10
		loginHistory.setPage(page);//4 传入的对象必须存在 set com.gta.edu.persis.page.plugin.Page 对象。 Entity已经内置   vo自己 添加 实现 IPage 接口
		loginHistory.setOperateId(operateId);// 5 查询条件
		loginHistoryService.select(loginHistory);//6 自己定义的查询方法
*/	 
		
		LOG.info("personLogs");
		Page page=getPage();//2 使用此方法获得对象 不能new
		page.setPageSize(15);//3 可选参数设置每页最大显示条数 默认 10
		PageExample example = new  PageExample(LoginHistory.class  ,page);
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(loginIp)) {
			criteria.andCondition ("login_ip =", loginIp);
		}
		example.setOrderByClause("login_time desc");
		
		loginHistoryService.selectByExample(example);
		
		ModelAndView modelAndView = pageModelAndView("nologin/demo/personLogs", page);
 
		return modelAndView;
	}
}
