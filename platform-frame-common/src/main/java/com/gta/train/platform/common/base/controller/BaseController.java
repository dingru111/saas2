package com.gta.train.platform.common.base.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


import com.google.gson.Gson;
import com.gta.train.platform.common.base.constant.Constants;
import com.gta.train.platform.common.base.dto.ExamDto;
import com.gta.train.platform.common.base.dto.LoginUserInfo;
import com.gta.train.platform.common.shiro.util.SessionUtil;
import com.gta.train.platform.common.util.JedisClusterUtil;
import com.gta.train.platform.common.util.StringUtils;
import com.gta.train.platform.persis.page.plugin.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class BaseController {
    @Autowired
    private HttpServletRequest request;
    /**
     * 以渔有方考核练习存储Key
     */
    public static final String YYYF_EXAM_KEY = "yyyfExamkey";

    /**所有交易地址的key*/
    public static  final String TRAINING_REQUEST_URL_KEY="trainingRequestUrlKey";
    /**
     * @return String
     * @throws
     * @description 得到用户登陆的ticket数据
     * @author fengya
     * @date 2017年5月26日 上午9:05:45
     */
    public String getTicket() {
        Cookie cookies[] = request.getCookies();
        String ticket = null;
        if (cookies != null) {
            for (Cookie ck : cookies) {
                if (ck.getName().trim().equals("ticket")) {
                    ticket = ck.getValue();
                    break;
                }
            }
        }
        return ticket;
    }

    protected HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        return request;
    }


    /**
     * @return int
     * @description 得到第一页
     * @author wbh
     * @date 2018年11月15日 上午9:35:49
     */
    protected int getPageNum() {
        String reqPage = getRequest().getParameter("startPage");
        //System.out.println("startPage:"+reqPage);
        return Integer.parseInt(reqPage == null || "".equals(reqPage) || reqPage.compareTo("0") <= 0 ? "1" : reqPage);  // 页码
    }

    /**
     * @return int
     * @description 得到一页有多少记录
     * @author wbh
     * @date 2018年11月15日 上午9:36:31
     */
    protected int getPageSize() {
        String reqPageSize = getRequest().getParameter("pageSize");
        //System.out.println("pageSize:"+reqPageSize);
        return Integer.parseInt(reqPageSize == null || "".equals(reqPageSize) ? Constants.PAGE.DEFAULT_PAGE_SIZE : reqPageSize); // 分页数量
    }

    /**
     * @return Page
     * @description 活动页面对象
     * @author wbh
     * @date 2018年11月15日 上午9:38:01
     */
    protected Page getPage() {
        Page page = new Page(getPageNum(), getPageSize());
        page.setAfterPage(Constants.PAGE.AFTERPAGE);
        page.setBeforPage(Constants.PAGE.BEFORPAGE);
        return page;
    }

    /**
     * @param viewName
     * @param page
     * @return ModelAndView
     * @description 分页跳转封装
     * @author wbh
     * @date 2018年11月15日 上午9:38:05
     */
    protected ModelAndView pageModelAndView(String viewName, Page page) {
        ModelAndView modelAndView = new ModelAndView(viewName);
        modelAndView.addObject("pageInfo", page);
        return modelAndView;
    }

    /**
     * 得到登录用户
     *
     * @return
     */
    public LoginUserInfo getLoginUserInfo() {
        return SessionUtil.getLoginUser();
    }


   /**
    * @description插入考试操作记录到缓存，用于计分
    * @author huan.xu
    * @date  2018-12-13 17:06
    * @param [platformUserId 平台用户id, isPathVariable 是否PathVariabl 方式 ,
    *                        param：相同url，但带不同参数处理不同业务]
    * @return void
    **/
    public void insertTestScore(String platformUserId,boolean  isPathVariable,String param) {
        String key=YYYF_EXAM_KEY + platformUserId;
        Gson gson = new Gson();
        ExamDto examDto = gson.fromJson(JedisClusterUtil.get(key), ExamDto.class);
        if(examDto!=null && examDto.getTotalScore()==null){ //如果存在
            String requestURI=request.getRequestURI();
            String trainngId=null;
            Map<Object, Object> cacheMap = JedisClusterUtil.getCacheMap(TRAINING_REQUEST_URL_KEY);
             if(isPathVariable){
                Set<Map.Entry<Object, Object>> entries = cacheMap.entrySet();
                 requestURI=requestURI+"/";
                for (Map.Entry map:entries){
                    String url = (String) map.getKey();
                    if(requestURI.indexOf(url+"/") !=-1 ){
                        trainngId= (String) map.getValue();
                        break;
                    }
                }
            }else {
                 int i = requestURI.indexOf("?");
                 if(i != -1 ) {
                     requestURI = requestURI.substring(0,i);
                 }
                 requestURI=requestURI.substring(requestURI.lastIndexOf("/"));
                if(!StringUtils.isNullOrEmpty(param)){
                    requestURI+="/"+param;
                }
                trainngId= (String) cacheMap.get(requestURI);
            }
            if(trainngId==null){
                throw  new RuntimeException("参数错误");
            }
            Map<String, Integer> result = examDto.getResult();
            if (result == null) {
                result = new HashMap<>();
            }
            result.put(trainngId, 0);
            examDto.setResult(result);
            JedisClusterUtil.set(YYYF_EXAM_KEY + platformUserId, gson.toJson(examDto), 6 * 60 * 60);
        }

    }
}
