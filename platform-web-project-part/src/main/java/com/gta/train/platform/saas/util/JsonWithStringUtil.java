package com.gta.train.platform.saas.util;

public class JsonWithStringUtil {
	/**
	 * @description 字符串转义后html字符串，转义成正常的html标签
	 * @author wbh
	 * @date 2018年9月11日 下午2:13:36
	 * @param contentC
	 * @return
	 * String
	 */
	public static String getJsonWithString(String contentC) {  
	    String content = contentC;  
	    if (content == null) {  
	        return "";  
	    }  
	    content = content.replaceAll("&amp;", "&");  
	    content = content.replaceAll("&lt;", "<");  
	    content = content.replaceAll("&gt;", ">");  
	    content = content.replaceAll("&quot;", "\"");  
	    content = content.replaceAll("\r&#10;", "　\n");  
	    content = content.replaceAll("&#10;", "　\n");  
	    content = content.replaceAll("&#032;", " ");  
	    content = content.replaceAll("&#039;", "'");  
	    content = content.replaceAll("&#033;", "!");  
	  
	    return content;  
	}  
	/**
	 * @description 含有html标签的字符串转义后提供给html显示
	 * @author wbh
	 * @date 2018年9月11日 下午2:13:40
	 * @param contentC
	 * @return
	 * String
	 */
	public static String getStringWithJson(String contentC) {
	    String content = contentC;  
	    if (content == null) {  
	        return "";  
	    }  
	  /*  content = content.replaceAll("&", "&amp;");  
	    content = content.replaceAll("<", "&lt;");  
	    content = content.replaceAll(">", "&gt;");  */
//	    content = content.replaceAll("\\\"", "\\\\\"");  
	    content = content.replaceAll("\\\\n\\\\r", "&#10;");  
	    content = content.replaceAll("\\\\r\\\\n", "&#10;");  
	    content = content.replaceAll("\\\\n", "&#10;"); 
	    content = content.replaceAll("\\\\t", "&#10;");
	    content = content.replaceAll(" ", "&#032;");  
	    content = content.replaceAll("'", "&#039;");  
	    content = content.replaceAll("!", "&#033;");  
	  
	    return content;  
	}
	public static void main(String[] args) {
		/*String s="&<>\"\n\r\r\n\n '!";
		s=getStringWithHTML(s);
		System.out.println(s);
		s=getHTMLWithString(s);
		System.out.println(s);*/
		
		String  s="&nbsp; <div></div> <a></a> <input type='text' value=\"fsa\" /> \\ \" ";
		System.out.println("原始字符串：                     "+s);
		s=getStringWithJson(s);
		System.out.println("html转义字符串："+s);
		s=getJsonWithString(s);
		System.out.println("html转义成原始字符串："+s);
	}
	
}
