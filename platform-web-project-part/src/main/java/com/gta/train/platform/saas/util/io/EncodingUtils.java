package com.gta.train.platform.saas.util.io;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by yi.wang1 on 2014/12/25.
 * 文件下载文件名编码解决方案
 */
public class EncodingUtils {


    /**
     * 不同浏览器下载时，中文文件名乱码问题解决
     *
     * @param request  请求信息，用与判断浏览器类型
     * @param fileName 文件名称
     * @return 文件下载文件名参数写法
     */
    public static String processFileName(HttpServletRequest request, String fileName) throws UnsupportedEncodingException {
        String Utf8FileName = URLEncoder.encode(fileName, "UTF8");

        String userAgent = request.getHeader("User-Agent");
        // 如果没有UA，则默认使用IE的方式进行编码，因为毕竟IE还是占多数的
        String rtn = "filename=\"" + Utf8FileName + "\"";
        if (userAgent != null) {
            userAgent = userAgent.toLowerCase();
            // IE浏览器，只能采用URLEncoder编码
            if (userAgent.indexOf("msie") != -1) {
                // 解决空格变加号问题
//                Utf8FileName = Utf8FileName.replaceAll("\\+", "%20");
                rtn = "filename=\"" + Utf8FileName + "\"";
            }
            // Opera浏览器只能采用filename*（未测试）
            else if (userAgent.indexOf("opera") != -1) {
                rtn = "filename*=UTF-8''" + Utf8FileName;
            }
            // Chrome浏览器，只能采用MimeUtility编码或ISO编码的中文输出
            else if (userAgent.indexOf("applewebkit") != -1) {
                Utf8FileName = MimeUtility.encodeText(fileName, "UTF8", "B");
                rtn = "filename=\"" + Utf8FileName + "\"";
            }
            // Safari浏览器，只能采用ISO编码的中文输出（未测试）
            else if (userAgent.indexOf("safari") != -1) {
                //fileName = fileName.replaceAll("\\+", "%20");
                rtn = "filename=\"" + new String(Utf8FileName.getBytes("UTF-8"), "ISO8859-1") + "\"";
            }
            // FireFox浏览器，可以使用MimeUtility或filename*或ISO编码的中文输出
            else if (userAgent.indexOf("mozilla") != -1) {
                // 解决空格变加号问题
//                Utf8FileName = Utf8FileName.replaceAll("\\+", "%20");
                rtn = "filename*=UTF-8''" + Utf8FileName;
            }
        }
        return rtn;
    }
}
