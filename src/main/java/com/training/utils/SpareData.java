package com.training.utils;

import com.training.Callback;

//全局静态数据类
public class SpareData<T> {

    //状态码
    public static final int CALL_SUCCESS = 1001;     //成功
    public static final int CALL_FAILED = 1002;      //失败
    public static final int CALL_PARAMETER = 1003;   //参数不全

    public Callback<T> failedByParameter(){
        return new Callback<T>(CALL_PARAMETER, "上传数据不全或错误");
    }

    public Callback<T> failedByBackstage(){
        return new Callback<T>(CALL_FAILED, "后台出错");
    }

    public Callback<T> failedByPwd(){
        return new Callback<T>(CALL_FAILED, "密码错误");
    }

    public Callback<T> failedByPhone(){
        return new Callback<T>(CALL_FAILED, "账号不存在");
    }

    public Callback<T> successByLogin(boolean status){
        if (status){
            return new Callback<T>(CALL_SUCCESS, "登录成功");
        }
        return new Callback<T>(CALL_SUCCESS, "退出登录成功");
    }

    public Callback<T> successByInsert(){
        return new Callback<T>(CALL_SUCCESS, "上传成功");
    }

    public Callback<T> successBySelect(T data){
        return new Callback<T>(CALL_SUCCESS, "获取成功", data);
    }
}