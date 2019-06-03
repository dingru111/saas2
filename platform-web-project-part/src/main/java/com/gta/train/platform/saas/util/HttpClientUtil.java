package com.gta.train.platform.saas.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author huan.xu
 * @version 1.0
 * @className HttpClientUtil
 * @description
 * @date 2018-08-25 9:15
 */
public class HttpClientUtil {


    /** logger */
    private static final Logger LOGGER = Logger.getLogger(HttpClientUtil.class);

    public final static  String   COULD_CASE_INSERT_UPDATE_DELET_PATH="/api/yh/v1.0/cases"; //项目的增删改
    public final static  String  COULD_ASSESSMENT_PATH="/api/yh/v1.0/assessment";//学生成绩同步
    public final static  String  COUD_INTEGRAL_PATH="/api/yh/v1.0/integral";//在线积分
   // public final static  String  COULD_ASSESSMENT_LIST_PATH="/api/yh/v1.0/assessmentList";//学生成绩同步
    public final static  String  COULD_ASSESSMENT_LIST_PATH="/api/yh/v1.1/assessmentList";//学生成绩批量同步


    private static final CloseableHttpClient httpClient;
    public static final String CHARSET = "UTF-8";

    static {
        RequestConfig config = RequestConfig.custom().setConnectTimeout(5000).setSocketTimeout(15000).build();
        httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
    }

    public static String doGet(String url, Map<String, String> params) {
        return doGet(url, params, CHARSET);
    }

    public static String doPost(String url, Map<String, String> params) {
        return doPost(url, params, CHARSET);
    }

    public static String doPostWithJSON(String url, Object params) throws Exception {
        return doPostWithJSON(url, params, CHARSET);
    }

    public static String doPutWithJSON(String url, Object params) throws Exception {
        return doPutWithJSON(url, params, CHARSET);
    }

    public static String doDeleteWithJSON(String url, Object params) throws Exception {
        return doDeleteWithJSON(url, params, CHARSET);
    }


    /**
     * HTTP Get 获取内容
     *
     * @param url     请求的url地址 ?之前的地址
     * @param params  请求的参数
     * @param charset 编码格式
     * @return 页面内容
     */
    public static String doGet(String url, Map<String, String> params, String charset) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        try {
            if (params != null && !params.isEmpty()) {
                List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    String value = entry.getValue();
                    if (value != null) {
                        pairs.add(new BasicNameValuePair(entry.getKey(), value));
                    }
                }
                url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
            }
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpGet.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
           LOGGER.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * HTTP Post 获取内容
     *
     * @param url     请求的url地址 ?之前的地址
     * @param params  请求的参数
     * @param charset 编码格式
     * @return 页面内容
     */
    public static String doPost(String url, Map<String, String> params, String charset) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        try {
            List<NameValuePair> pairs = null;
            if (params != null && !params.isEmpty()) {
                pairs = new ArrayList<>(params.size());
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    String value = entry.getValue();
                    if (value != null) {
                        pairs.add(new BasicNameValuePair(entry.getKey(), value));
                    }
                }
            }
            HttpPost httpPost = new HttpPost(url);
            if (pairs != null && pairs.size() > 0) {
                httpPost.setEntity(new UrlEncodedFormEntity(pairs, CHARSET));
            }
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
           LOGGER.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * json post
     *
     * @author: fengyu.wang
     * @date: 2017-10-27 16:14:47
     */
    public static String doPostWithJSON(String url, Object params, String charset) throws Exception {
        LOGGER.debug("httpClient doPostWithJSON url:\t" + url);

        HttpPost httpPost = new HttpPost(url);

        StringEntity entity = new StringEntity(JSON.toJSONString(params), charset);
        entity.setContentEncoding(charset);
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        LOGGER.debug("httpClient doPostWithJSON params:\t" + JSON.toJSONString(params));

        HttpResponse response = httpClient.execute(httpPost);
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != 200) {
            httpPost.abort();
            throw new RuntimeException("HttpClient,error status code :" + statusCode);
        }
        HttpEntity responseEntity = response.getEntity();
        String respContent = EntityUtils.toString(responseEntity, charset);
        LOGGER.debug("httpClient doPostWithJSON respContent:\t" + respContent);
        return respContent;
    }

    /**
     * @Title: doPutWithJSON
     * @Description:
     * @param: url
     * @param: params
     * @param: charset
     * @return: java.lang.String
     * @throws:
     * @author: qianqian.zhang 2017/10/30 0030 17:23
     */
    public static String doPutWithJSON(String url, Object params, String charset) throws Exception {
        LOGGER.debug("httpClient doPutWithJSON url:\t" + url);
        HttpPut httpPut = new HttpPut(url);

        StringEntity entity = new StringEntity(JSON.toJSONString(params), charset);
        entity.setContentEncoding(charset);
        entity.setContentType("application/json");
        httpPut.setEntity(entity);
        LOGGER.debug("httpClient doPutWithJSON params:\t" + JSON.toJSONString(params));

        HttpResponse response = httpClient.execute(httpPut);
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != 200) {
            httpPut.abort();
            throw new RuntimeException("HttpClient,error status code :" + statusCode);
        }
        HttpEntity responseEntity = response.getEntity();
        String respContent = EntityUtils.toString(responseEntity, charset);
        LOGGER.debug("httpClient doPutWithJSON respContent:\t" + respContent);
        return respContent;
    }

    /**
     * @Title: doDeleteWithJSON
     * @Description:
     * @param: url
     * @param: params
     * @param: charset
     * @return: java.lang.String
     * @throws:
     * @author: qianqian.zhang 2017/10/31 0031 9:32
     */
    public static String doDeleteWithJSON(String url, Object params, String charset) throws Exception {
        /**
         * 没有现成的delete可以带json的，自己实现一个，参考HttpPost的实现
         */
        class HttpDeleteWithBody extends HttpEntityEnclosingRequestBase {
            public static final String METHOD_NAME = "DELETE";

            @SuppressWarnings("unused")
            public HttpDeleteWithBody() {
            }

            @SuppressWarnings("unused")
            public HttpDeleteWithBody(URI uri) {
                setURI(uri);
            }

            public HttpDeleteWithBody(String uri) {
                setURI(URI.create(uri));
            }

            public String getMethod() {
                return METHOD_NAME;
            }
        }
        LOGGER.debug("httpClient doDeleteWithJSON url:\t" + url);
        HttpDeleteWithBody httpDelete = new HttpDeleteWithBody(url);

        StringEntity entity = new StringEntity(JSON.toJSONString(params), charset);
        entity.setContentEncoding(charset);
        entity.setContentType("application/json");
        httpDelete.setEntity(entity);
        LOGGER.debug("httpClient doDeleteWithJSON params:\t" + JSON.toJSONString(params));

        HttpResponse response = httpClient.execute(httpDelete);
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != 200) {
            httpDelete.abort();
            throw new RuntimeException("HttpClient,error status code :" + statusCode);
        }
        HttpEntity responseEntity = response.getEntity();
        String respContent = EntityUtils.toString(responseEntity, charset);
        LOGGER.debug("httpClient doDeleteWithJSON respContent:\t" + respContent);
        return respContent;
    }

}
