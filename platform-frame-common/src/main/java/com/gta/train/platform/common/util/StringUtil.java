package com.gta.train.platform.common.util;

/**
 * Title:
 * Description:字符串工具类
 * Copyright: Copyright (c) 2018
 * Company: www.gtafe.com
 * @author fengya
 * @version 1.0
 * @date  2018年6月19日 下午4:17:35
 */
public class StringUtil {

	/**
	 * @description 判断字符串是否为空（true:null,""," "）
	 * @author fengya
	 * @date 2018年6月19日 下午4:13:35
	 * @param str
	 * @return
	 * @return boolean
	 */
	public static boolean isNullOrEmpty(String str) {
		if (str == null || str != null && str.trim().length() == 0) {
			return true;
		}
		return false;
	}
}
