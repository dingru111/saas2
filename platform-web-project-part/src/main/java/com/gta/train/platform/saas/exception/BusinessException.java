package com.gta.train.platform.saas.exception;

/**
 * Desc: 一般业务异常
 *
 * @author qianqian.zhang
 * @date 2018-11-12 15:58
 **/
public class BusinessException extends RuntimeException{

    private static final long serialVersionUID = -4108560590386094683L;

    public BusinessException(String msg) {
        super(msg);
    }
}
