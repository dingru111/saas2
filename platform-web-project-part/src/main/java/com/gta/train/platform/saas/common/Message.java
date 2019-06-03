package com.gta.train.platform.saas.common;

import java.io.Serializable;

public class Message<T> implements Serializable {
    private boolean status;
    private String msg;
    private T obj;

    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public T getObj() {
        return obj;
    }
    public void setObj(T obj) {
        this.obj = obj;
    }
}
