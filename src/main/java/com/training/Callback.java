package com.training;

//前后端交互数据类
public class Callback<T> {

    private int code;   //全局状态码
    private String msg; //状态说明
    private T data;     //返回数据

    public Callback(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Callback(int code, String msg) {
        this(code, msg, null);
    }

    public Callback(int code) {
        this(code, null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
