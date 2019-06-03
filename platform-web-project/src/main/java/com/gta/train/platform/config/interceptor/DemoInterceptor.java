package com.gta.train.platform.config.interceptor;

import com.google.gson.Gson;
import com.gta.edu.sdk.redis.RedisCommData;
import com.gta.train.platform.common.shiro.util.SessionUtil;
import com.gta.train.platform.common.util.JedisClusterUtil;
import com.gta.train.platform.common.util.StringUtil;
import com.gta.yyyf.vo.user.LoginUserInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author huan.xu
 * @version 1.0
 * @className ExamInterceptor
 * @description
 * @date 2018-09-14 16:13
 */
@Component
public class DemoInterceptor implements HandlerInterceptor {

    private static final Logger LOG = Logger.getLogger(DemoInterceptor.class);

    /**
     * @param [httpServletRequest, httpServletResponse, o]
     * @return boolean
     * @description 在DispatcherServlet之前执行, 也就是方法执行前
     * @author huan.xu
     * @date 2018-09-14 16:19
     **/
    @Override
    @SuppressWarnings("all")
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        LOG.debug("---开始拦截---");
    /*    LoginUserInfo loginUserInfo = RedisCommData.getLoginUserInfo(this.getTicket(httpServletRequest));
        if (loginUserInfo == null) {
            // 未登录的url
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + Constant.NOT_LOGIN__TIP_URL);
            return false;
        }
        String yyyfExamAutoSubmitStr = JedisClusterUtil.get(Constant.YYYF_EXAM_AUTO_SUBMIT);
        if (yyyfExamAutoSubmitStr != null) {
            Gson gson = new Gson();
            Map<Object, Object> yyyfExamAutoSubmitMap = gson.fromJson(JedisClusterUtil.get(Constant.YYYF_EXAM_AUTO_SUBMIT), Map.class);
            String userId = loginUserInfo.getId();
            if (yyyfExamAutoSubmitMap.size() != 0 && yyyfExamAutoSubmitMap.containsKey(userId)) {
                String assessmentId=(String)yyyfExamAutoSubmitMap.get(userId);
                if(!yyyfExamAutoSubmitMap.containsValue(assessmentId)){ //如果不存在直接访问
                    yyyfExamAutoSubmitMap.remove(userId);
                    JedisClusterUtil.del(Constant.YYYF_EXAM_AUTO_SUBMIT);
                    JedisClusterUtil.set(Constant.YYYF_EXAM_AUTO_SUBMIT,gson.toJson(yyyfExamAutoSubmitMap),1*60*60 );
                    return true;
                }
                //判断是否为ajax请求，默认不是
                if (!StringUtils.isBlank(httpServletRequest.getHeader("x-requested-with")) && httpServletRequest.getHeader("x-requested-with").equals("XMLHttpRequest")) {
                    httpServletResponse.setCharacterEncoding("UTF-8");
                    httpServletResponse.sendError(Constant.EXAM_TIME_OUT_STATUS_CODE, Constant.EXAM_TIME_OUT_STATUS_MSG);
                } else {
                    httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + Constant.EXAM_TIMEOUT_TIP_URL);
                }
                yyyfExamAutoSubmitMap.remove(userId);
                JedisClusterUtil.del(Constant.YYYF_EXAM_AUTO_SUBMIT);
                JedisClusterUtil.set(Constant.YYYF_EXAM_AUTO_SUBMIT,gson.toJson(yyyfExamAutoSubmitMap),1*60*60 );
                return false;
            }
        }*/
        return true;
    }

    /**
     * @param [httpServletRequest, httpServletResponse, o, modelAndView]
     * @return void
     * @description 在controller执行之后的DispatcherServlet之后执行
     * @author huan.xu
     * @date 2018-09-14 16:18
     **/
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    /**
     * @param [httpServletRequest, httpServletResponse, o, e]
     * @return void
     * @description 在页面渲染完成返回给客户端之前执行
     * @author huan.xu
     * @date 2018-09-14 16:18
     **/
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }

    /**
     * @description 获取ticket
     * @author huan.xu
     * @date  2018-12-19 10:03
     * @param [httpServletRequest]
     * @return java.lang.String
     **/
    private String getTicket(HttpServletRequest httpServletRequest){
        Cookie[] cookies = httpServletRequest.getCookies();
        String ticket = null;
        if (cookies != null) {
            Cookie[] var6 = cookies;
            int var5 = cookies.length;
            for (int var4 = 0; var4 < var5; ++var4) {
                Cookie ck = var6[var4];
                if (ck.getName().trim().equals("ticket")) {
                    ticket = ck.getValue();
                    break;
                }
            }
        }
        return ticket;
    }
}
