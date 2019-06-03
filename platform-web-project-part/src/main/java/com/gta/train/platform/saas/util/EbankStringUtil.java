package com.gta.train.platform.saas.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * @author huan.xu
 * @version 1.0
 * @className EbankStringUtil
 * @description
 * @date 2018-11-09 9:25
 */
public class EbankStringUtil {

    private static Logger LOG = Logger.getLogger(EbankStringUtil.class);


    private static final String BANK_ACCOUNT_12_START = "**** **** **** ";

    /**
     * @param [bankAccount]
     * @return java.lang.String
     * @description 获取存入交易明细表的卡号
     * @author huan.xu
     * @date 2018-11-09 9:30
     **/
    public static String getDetailBankAccount(String bankAccount) {

        return BANK_ACCOUNT_12_START + bankAccount.substring(14);
    }

    /**
     * @param [chines]
     * @return java.lang.String
     * @description 中文转拼音
     * @author huan.xu
     * @date 2018-11-12 15:40
     **/
    public static String converterToFirstSpell(String chines) {
        String pinyinName = "";
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < nameChar.length; i++) {
            String s = String.valueOf(nameChar[i]);
            if (s.matches("[\\u4e00-\\u9fa5]")) {
                try {
                    String[] mPinyinArray = PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat);
                    pinyinName += mPinyinArray[0] + " ";
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    LOG.error(e.getMessage(), e);
                }
            } else {
                pinyinName += nameChar[i];
            }
        }
        return pinyinName.toUpperCase();
    }

    /**
     * @param [request]
     * @return java.lang.String
     * @description 获取真实ip 如果通过了多级反向代理的话，取X-Forwarded-For中第一个非unknown的有效IP字符串
     * @author huan.xu
     * @date 2018-11-12 16:05
     **/
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.contains(",")) {
            return ip.split(",")[0];
        } else {
            return ip;
        }
    }

    /**
     * @param []
     * @return java.lang.String
     * @description 获取问候语
     * @author huan.xu
     * @date 2018-11-12 16:32
     **/
    public static String getGreetings() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        String format = sdf.format(new Date());
        int hour = Integer.valueOf(format);
        if (hour >= 0 && hour <= 5) {
            return "凌晨好";
        } else if (hour >= 6 && hour <= 11) {
            return "早上好";
        } else if (hour >= 12 && hour <= 18) {
            return "下午好";
        } else {
            return "晚上好";
        }
    }

    /**
     * @param []
     * @return java.lang.String
     * @description 获取gta支付的交易流水显示账号
     * @author huan.xu
     * @date 2018-11-13 11:45
     **/
    public static String getGtaPayAccountSimple(String gtaPayAccount) {
        int flagIndex = gtaPayAccount.lastIndexOf("@");
        String result = "*" + gtaPayAccount.substring(flagIndex);
        switch (flagIndex) {
            case 0:
                result = "*" + gtaPayAccount.substring(flagIndex);
                break;
            case 1:
                result = "*" + gtaPayAccount.substring(flagIndex - 1);
                break;
            case 2:
                result = "*" + gtaPayAccount.substring(flagIndex - 1);
                break;
            case 3:
                result = "**" + gtaPayAccount.substring(flagIndex - 2);
                break;
            case 4:
                result = "*" + gtaPayAccount.substring(flagIndex - 3);
                break;
            case 5:
                result = "**" + gtaPayAccount.substring(flagIndex - 3);
                break;
            case 6:
                result = "***" + gtaPayAccount.substring(flagIndex - 3);
                break;
            case 7:
                result = "****" + gtaPayAccount.substring(flagIndex - 3);
                break;
            case 8:
                result = "*****" + gtaPayAccount.substring(flagIndex - 3);
                break;
            default:
                result = "******" + gtaPayAccount.substring(flagIndex - 3);
                break;
        }
        return result;
    }

    /**
     * @param []
     * @return java.lang.String
     * @description 获取gta支付的交易流水显示账号
     * @author wbh
     * @date 2018-11-13 11:45
     **/
    public static String formatPayAccountSimple(String gtaPayAccount) {
        int flagIndex = gtaPayAccount.lastIndexOf("@");
        String result = "*" + gtaPayAccount.substring(flagIndex);
        switch (flagIndex) {
            case 0:
                result = "***" + gtaPayAccount.substring(0);
                break;
            case 1:
                result = "***" + gtaPayAccount.substring(0  );
                break;
            case 2:
                result = "***" + gtaPayAccount.substring(0  );
                break;
            case 3:
                result = "***" + gtaPayAccount.substring(0  );
                break;
            case 4:
                result = "***" + gtaPayAccount.substring(flagIndex-3);
                break;
            default:
                result = "***" + gtaPayAccount.substring(flagIndex-3);
                break;
        }
        //System.out.print(gtaPayAccount+"格式化:");
        return result;
    }

   
    /**
     * 生成合同号：6位年月日+4位随机数字
     *
     * @return
     */
    public static String generateContractNum() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        String timeStr = sdf.format(new Date());
        Random random = new Random();
        String ranNum = String.format("%04d", random.nextInt(10000));// 获取4位随机数[0,10000)
        return timeStr + ranNum;
    }


    /**
     * 生成 信用卡 有效期 ：5年后任意月份
     * @return
     */
    public static Date generateEffectiveTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //5年后
        calendar.add(Calendar.YEAR, 5);
//        //随机月
//        calendar.set(Calendar.MONTH, new Random().nextInt(12));
//        //1日
//        calendar.set(Calendar.DAY_OF_MONTH, 1);
//        // 0时
//        calendar.set(Calendar.HOUR_OF_DAY, 0);
//        // 0分
//        calendar.set(Calendar.MINUTE, 0);
//        // 0秒
//        calendar.set(Calendar.SECOND, 0);
//        // 0毫秒
//        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }
    /**
     * @param [riskRating]
     * @return java.lang.Integer
     * @description 根据类型评估风险级别
     * @author huan.xu
     * @date 2018-11-23 11:52
     **/
    public static Integer getRiskLevelByRiskRating(Integer riskRating) {
        //保守型、相对保守型：低[0]；稳健型：中[1]；积极型、相对积极型：高[2]
        //0:未评估,1:保守型,2:相对保守型,3:稳健型,4:相对积极型,5:积极型；
        switch (riskRating) {
            case 1:
                riskRating = 0;
                break;
            case 2:
                riskRating = 0;
                break;
            case 3:
                riskRating = 1;
                break;
            case 4:
                riskRating = 2;
                break;
            case 5:
                riskRating = 2;
                break;
            default:
                riskRating = -1;
                break;
        }
        return riskRating;
    }




   /**
    * @description  根据分数返回风险类型
    * @author huan.xu
    * @date 2018-11-23 17:14
    * @param [score]
    * @return int
    **/
    public static int getRiskRatingByScore(int score) {
        // 11-24 保守  25-36 相对保守型 37-72 稳健型 73-86 相对积极型 87-100 积极型
        //0:未评估,1:保守型,2:相对保守型,3:稳健型,4:相对积极型,5:积极型；
        int result = 0 ;
        if (24 < score && score < 37)
            result = 2;
        else if (36 < score && score < 73)
            result = 3;
        else if (72< score && score < 87)
            result = 4;
        else if (86 < score)
            result = 5;
        else
            result = 1;
        return result;
    }



}
