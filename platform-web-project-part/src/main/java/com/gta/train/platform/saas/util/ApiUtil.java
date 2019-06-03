package com.gta.train.platform.saas.util;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author huan.xu
 * @version 1.0
 * @className ApiUtil
 * @description
 * @date 2018-11-29 11:18
 */
public class ApiUtil {

    private static Logger LOG = Logger.getLogger(ApiUtil.class);
    private static final String DEF_CHATSET = "UTF-8";
    private static final int DEF_CONN_TIMEOUT = 30000;
    private static final int DEF_READ_TIMEOUT = 30000;
    private static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";


    /**
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return 网络请求字符串
     * @throws Exception
     */
    public static String net(String strUrl, Map params, String method) {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if (method == null || method.toUpperCase().equals("GET")) {
                strUrl = strUrl + "?" + urlencode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if (method == null || method.toUpperCase().equals("GET")) {
                conn.setRequestMethod("GET");
            } else {
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params != null && method.toUpperCase().equals("POST")) {
                try (DataOutputStream out = new DataOutputStream(conn.getOutputStream())) {
                    out.writeBytes(urlencode(params));
                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {

                    LOG.error(e.getMessage(), e);
                }
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }


    //将map型转为请求参数型
    private static String urlencode(Map<String, Object> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return sb.toString();
    }

    /**
     * @param [paramsStr]
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @description 处理配置文件读的
     * @author huan.xu
     * @date 2018-11-29 11:22
     **/
    public static Map<String, Object> createParams(String paramsStr, String appkey, String sign,String bankno) {
        Map<String, Object> result = new HashMap<String, Object>();
        String[] split = paramsStr.split(",");
        if (split.length != 0) {
            for (String s : split) {
                String[] split1 = s.split(":");
                result.put(split1[0], split1[1]);
            }
        }
        result.put("appkey", appkey);
        result.put("sign", sign);
        if(bankno!=null) {
            result.put("bankno", bankno);
        }
        return result;
    }
}
