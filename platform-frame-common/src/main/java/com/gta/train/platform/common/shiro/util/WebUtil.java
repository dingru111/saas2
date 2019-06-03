/******************************************************************************
 * Copyright (c) 2017.3.28 by JoyLau.                                         *
 * Copyright © 2017 Shenzhen GTA Education Tech Ltd. All rights reserved      *
 ******************************************************************************/

package com.gta.train.platform.common.shiro.util;

 
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by JoyLau on 3/28/2017.
 * com.gtafe.tools.utils
 * WebUtil
 * fa.liu@gtade.com
 */
public class WebUtil {
    /**
     * @author joylau
     * Verify the asynchronous request
     */
    public static boolean isAjax(ServletRequest request) {
        return ((HttpServletRequest) request).getHeader("X-requested-with") != null
                && "XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"));
    }

    /**
     * @author joylau
     * is Post request
     */
    public static boolean isPost(ServletRequest request) {
        return ((HttpServletRequest) request).getMethod().equalsIgnoreCase("POST");
    }

    /**
     * @author joylau
     * return asynchronous messages;
     * Usually, we can also use HashMap
     */
    public static void PrintOut(ServletResponse response, String str) {
        PrintWriter pw = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");
            pw = response.getWriter();
            pw.println("<font color = 'red'>" + str + "</font>");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != pw) {
                pw.flush();
                pw.close();
            }
        }
    }
    
    /**
     * 两个bean属性的拷贝
     */
    public static void copyBean(Object src, Object dest) {
        // 注册日期转换器
        ConvertUtils.register(new Converter() {
            public Object convert(Class tClass, Object o) {
                String value = (String) o;
                if (value == null || "".equals(value.trim())) {
                    return null;
                }
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    return sdf.parse(value);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }, Date.class);
        try {
            BeanUtils.copyProperties(dest, src);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     *@author joylau
     */
    public static boolean pushARequest(String url){
        try {
            URI uri = new URI(url);
            SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
            ClientHttpRequest clientHttpRequest = simpleClientHttpRequestFactory.createRequest(uri, HttpMethod.POST);
            clientHttpRequest.execute();
            return true;
        } catch (URISyntaxException | IOException e) {
            return false;
        }
    }

    /**
     *@author joylau
     */
    public static String encrypt(String password) {
        return new SimpleHash("md5",password, ByteSource.Util.bytes("gta"),2).toHex();
    }
    /**
     *@author joylau
     */
    public static String getIdentity(){
        Object odentity = getSession().getAttribute("identity");
        String identity = null == odentity ? null : (String) odentity;
        return null == identity ? "user" : identity;
    }
    /**
     *@author joylau
     */
    public static String getIdentity(Subject subject){
        Object odentity = subject.getSession().getAttribute("identity");
        String identity = null == odentity ? null : (String) odentity;
        return null == identity ? "user" : identity;
    }

 

 
    /**
     *@author joylau
     */
    public static Subject getSubject(){
        return SecurityUtils.getSubject();
    }
    /**
     *@author joylau
     */
    public static Session getSession(){
        return getSubject().getSession();
    }
 

    public static String getRedirectUrl(){
        return "admin".equals(getIdentity()) ? "/admin" : "/";
    }

    /**
     *@author joylau
     * 异或检查权限
     */
    public static boolean hasAnyPermissions(String... permissions) {
        boolean hasAnyPermissions = false;
        Subject subject = getSubject();
        if (subject != null) {
            for (String permission : permissions) {
                if (subject.isPermitted(permission)) {
                    hasAnyPermissions = true;
                    break;
                }
            }
        }
        return hasAnyPermissions;
    }

    /*public static void main(String[] args) throws IOException, URISyntaxException {
        URI uri = new URI("http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=36.7.72.49");
        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        ClientHttpRequest clientHttpRequest = simpleClientHttpRequestFactory.createRequest(uri, HttpMethod.GET);
        ClientHttpResponse response = clientHttpRequest.execute();
        InputStream is = response.getBody();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String str = "";
        while((str = br.readLine())!=null){
            HashMap map = JSON.parseObject(str, HashMap.class);
            System.out.println(map.get("province"));
        }
    }*/
}
