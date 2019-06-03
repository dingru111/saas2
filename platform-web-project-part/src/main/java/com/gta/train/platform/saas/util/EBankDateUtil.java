package com.gta.train.platform.saas.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @author huan.xu
 * @version 1.0
 * @className EBankDateUtil
 * @description 时间计算公共类
 * @date 2018-11-24 12:19
 */
public class EBankDateUtil {


    /**
     * @description  距当前时间后的第 differDays 的日期
     * @author huan.xu
     * @date 2018-11-24 12:21
     * @param [disparityDays]
     * @return java.util.Date
     **/
    public static Date getDayByDifferDays(Integer differDays){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE,differDays);
        return  calendar.getTime();
    }

    /***
     * @description 距date时间后的第 differDays 的日期
     * @author huan.xu
     * @date 2018-11-24 17:49
     * @param [date, differDays]
     * @return java.util.Date
     **/
    public static Date getDayByDifferDays(Date date,Integer differDays){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,differDays);
        return  calendar.getTime();
    }



    /**
   * @description  返回相差天数
   * @author huan.xu
   * @date 2018-11-24 17:38
   * @param [beforeDate, afterDate]
   * @return java.lang.Integer
   **/
    public static long getDifferDays(Date beforeDate, Date afterDate){
        long difference =  (afterDate.getTime()-beforeDate.getTime())/86400000;
        return  Math.abs(difference);
    }


    /**
     * @description 返回相差年数
     * @author huan.xu
     * @date 2018-11-26 13:39
     * @param [beforeDate, afterDate]
     * @return int
     **/
    public static int getDifferYears(Date beforeDate, Date afterDate){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beforeDate);
        int beforeYear=calendar.get(Calendar.YEAR);
        calendar.setTime(afterDate);
        int afterYear=calendar.get(Calendar.YEAR);
        return  afterYear-beforeYear;
    }


}
