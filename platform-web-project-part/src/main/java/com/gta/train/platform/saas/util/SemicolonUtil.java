package com.gta.train.platform.saas.util;

import java.math.BigDecimal;

/**
 * @author huan.xu
 * @version 1.0
 * @className SemicolonUtil
 * @description
 * @date 2018-11-22 14:51
 */
public class SemicolonUtil {
    /**
     * 返回每3位加一个逗号的字符串
     *
     * @param data 具体的数据
     * @return
     */
    public static String displayWithCommaJsp(Integer data) {
        try {
            String str2 = "";
            if (data != null) {
                //因为data是integer的，有可能是负值
                if (data > 0) {
                    String str = data + "";
                    str = new StringBuffer(str).reverse().toString(); // 先将字符串颠倒顺序
                    int size = (str.length() % 3 == 0) ? (str.length() / 3) : (str.length() / 3 + 1); // 每三位取一长度
                    //比如把一段字符串分成n段,第n段可能不是三个数,有可能是一个或者两个,
                    //现将字符串分成两部分.一部分为前n-1段,第二部分为第n段.前n-1段，每一段加一",".而第n段直接取出即可
                    for (int i = 0; i < size - 1; i++) { // 前n-1段
                        str2 += str.substring(i * 3, i * 3 + 3) + ",";
                    }
                    for (int i = size - 1; i < size; i++) { // 第n段
                        str2 += str.substring(i * 3, str.length());
                    }
                    str2 = new StringBuffer(str2).reverse().toString();
                    return str2;
                } else {
                    String str = data + "";
                    str = str.substring(str.indexOf("-"), str.length());
                    str = new StringBuffer(str).reverse().toString(); // 先将字符串颠倒顺序
                    int size = (str.length() % 3 == 0) ? (str.length() / 3) : (str.length() / 3 + 1); // 每三位取一长度
                    //比如把一段字符串分成n段,第n段可能不是三个数,有可能是一个或者两个,
                    //现将字符串分成两部分.一部分为前n-1段,第二部分为第n段.前n-1段，每一段加一",".而第n段直接取出即可
                    for (int i = 0; i < size - 1; i++) { // 前n-1段
                        str2 += str.substring(i * 3, i * 3 + 3) + ",";
                    }
                    for (int i = size - 1; i < size; i++) { // 第n段
                        str2 += str.substring(i * 3, str.length());
                    }
                    str2 = new StringBuffer(str2).reverse().toString();
                    return str2;
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 返回每3位加一个逗号的字符串
     *
     * @param str
     * @return
     */
    public static String displayWithComma(String str) {
        String prefix;
        String str2 = "";
        try {
            if (str.contains(".")) {
                prefix = str.substring(str.indexOf("."), str.length());
                str = str.substring(0, str.indexOf("."));
                str = new StringBuffer(str).reverse().toString(); // 先将字符串颠倒顺序
                int size = (str.length() % 3 == 0) ? (str.length() / 3) : (str.length() / 3 + 1); // 每三位取一长度
                //比如把一段字符串分成n段,第n段可能不是三个数,有可能是一个或者两个,
                //现将字符串分成两部分.一部分为前n-1段,第二部分为第n段.前n-1段，每一段加一",".而第n段直接取出即可
                for (int i = 0; i < size - 1; i++) { // 前n-1段
                    str2 += str.substring(i * 3, i * 3 + 3) + ",";
                }
                for (int i = size - 1; i < size; i++) { // 第n段
                    str2 += str.substring(i * 3, str.length());
                }
                str2 = new StringBuffer(str2).reverse().toString();
                return str2 + "" + prefix;
            } else {
                str = new StringBuffer(str).reverse().toString(); // 先将字符串颠倒顺序
                int size = (str.length() % 3 == 0) ? (str.length() / 3) : (str.length() / 3 + 1); // 每三位取一长度
                //比如把一段字符串分成n段,第n段可能不是三个数,有可能是一个或者两个,
                //现将字符串分成两部分.一部分为前n-1段,第二部分为第n段.前n-1段，每一段加一",".而第n段直接取出即可
                for (int i = 0; i < size - 1; i++) { // 前n-1段
                    str2 += str.substring(i * 3, i * 3 + 3) + ",";
                }
                for (int i = size - 1; i < size; i++) { // 第n段
                    str2 += str.substring(i * 3, str.length());
                }
                str2 = new StringBuffer(str2).reverse().toString();
                return str2;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        BigDecimal a=new BigDecimal("190000.00");
        System.out.println(displayWithComma(a.toString()));
    }
}
