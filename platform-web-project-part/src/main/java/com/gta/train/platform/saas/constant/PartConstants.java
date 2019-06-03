package com.gta.train.platform.saas.constant;

/**
 * @author huan.xu
 * @version 1.0
 * @className PartConstants
 * @description
 * @date 2019-04-30 17:20
 */
public class PartConstants {

    public static final boolean IS_SUCCESS = true;
    public static final boolean IS_UN_SUCCESS = false;
    public static final String MSG = "msg";
    public static final String UN_SUCCESS_TEXT = "unSuccess";
    public static final String SUCCESS_TEXT = "success";

    public static final String SAVE_SUCCESS = "saveSuccess";
    public static final String SAVE_UN_SUCCESS = "saveUnsuccess";
    public static final String APPLY_SUCCESS = "applySuccess";
    public static final String APPLY_UN_SUCCESS = "applyUnsuccess";
    public static final String APPROVE_SUCCESS = "approveSuccess";
    public static final String APPROVE_UN_SUCCESS = "approveUnsuccess";
    public static final String DEL_SUCCESS = "delSuccess";
    public static final String DEL_UN_SUCCESS = "delUnsuccess";
    public static final String BIND_SUCCESS = "bindSuccess";
    public static final String USER_NO_EXIST = "userNoExist";
    public static final String PSW_NO_RIGNT = "pswNoRight";
    public static final String LOGIN_SUCCESS = "loginSuccess";
    public static final String SUCCESS = "success";
    public static final String UNSUCCESS = "unsuccess";
    public static final String EXE_SUCCESS = "exeSuccess";
    public static final String LOGIN_USER = "loginUser";
    public static final String BIND_UNSUCCESS = "bindUnSuccess";
    public static final String NO_END = "noEnd";
    public static final String DEL_COMMITED = "delCommited";
    public static final String HAVE_BINDED = "haveBinded";
    public static final String HAVE_COMMITED = "haveCommited";
    public static final String SIM_NOT_FOUND = "simNotFound";
    public static final String SIM_FOUND = "simFound";
    public static final String SUBMIT_SUCCESS = "submitSuccess";
    public static final String SUBMIT_UNSUCCESS = "submitUnSuccess";
    public static final String RECORD_IS_NOT_ALL_DRAFT = "recordIsNotAllDraft";
    public static final String INFO_IMPERFECT = "templatePositionInfoImperfect";
    public static final String TEXT_OR_PARK_IS_EMPTY = "textOrParkIsEmpty";
    public static final String INFO_IS_NOT_PERFECT = "infoIsNotPerfect";

    /** HTTP GET 请求 */
    public static final String HTTP_GET = "GET";
    /** HTTP POST 请求 */
    public static final String HTTP_POST = "POST";
    /** api请求成功返回数据 的代码 */
    public static final String API_SUCCESS_CODE = "1";
    /** api请求成功返回数据结果 */
    public static final String API_RESULT = "result";
    /** 请求失败的错误代码 */
    public static final String API_MSG_ID = "msgid";
    /** 请求失败的错误信息 */
    public static final String API_ERROR_MSG = "msg";
    /** api请求成功返回错误的代码 */
    public static final String API_ERROR_CODE = "0";

    /** 外汇代号 */

    public enum DEL {
        NOT_DEL(0, "未删除"), IS_DEL(1, "已经删除"), TEMP_SAVE(2, "暂存");
        private final Integer value;
        private final String name;

        /**
         * 构造器默认也只能是private, 从而保证构造函数只能在内部使用
         *
         */
        private DEL(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        public String getName() {
            return name;
        }

        // 普通方法
        public static String getName(Integer index) {
            for (DEL c : DEL.values()) {
                if (c.getValue() == index) {
                    return c.name;
                }
            }
            return null;
        }
    }

    public enum SEX {
        MALE(1, "男"), FEMALE(2, "女");
        private final Integer value;
        private final String name;

        /**
         * 构造器默认也只能是private, 从而保证构造函数只能在内部使用
         *
         */
        private SEX(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        public String getName() {
            return name;
        }

        // 普通方法
        public static String getName(Integer index) {
            for (SEX c : SEX.values()) {
                if (c.getValue() == index) {
                    return c.name;
                }
            }
            return null;
        }
    }

    public enum RESULT {
        SUCCESS(1,"成功"), FAIL(2,"失败");
        private final Integer value;
        private final String name;
        /**
         * 构造器默认也只能是private, 从而保证构造函数只能在内部使用
         *
         */
        private RESULT(Integer value,String name) {
            this.value = value;
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }
        public String getName() {
            return name;
        }
        // 普通方法
        public static String getName(Integer index) {
            for (RESULT c : RESULT.values()) {
                if (c.getValue() == index) {
                    return c.name;
                }
            }
            return null;
        }
    }

    /**
     * 国家 Country Key
     */
    public static final String COUNTRY_KEY = "country";
    /**
     * 省 Provinces Key
     */
    public static final String PROVINCES_KEY = "provinces";
    /**
     * 省 city Key
     */
    public static final String CITY_KEY = "city";
    /**
     * 国家 父id Key的值：0
     */
    public static final String COUNTRY_PARENT_MAP_KEY = "0";
}