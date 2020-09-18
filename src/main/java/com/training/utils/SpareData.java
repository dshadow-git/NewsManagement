package com.training.utils;

import com.training.Callback;

//全局静态数据类
public class SpareData<T> {

    //状态码
    public static final int CALL_SUCCESS = 1001;     //成功
    public static final int CALL_FAILED = 1002;      //失败
    public static final int CALL_PARAMETER = 1003;   //参数不全

    public Callback<T> failed(){
        return new Callback<T>(CALL_FAILED);
    }

    public Callback<T> failed(String msg){
        return new Callback<T>(CALL_FAILED, msg);
    }

    public Callback<T> success(){
        return new Callback<T>(CALL_SUCCESS);
    }

    public Callback<T> success(String msg, T data){
        return new Callback<T>(CALL_SUCCESS, msg, data);
    }

    public Callback<T> success(String msg){
        return new Callback<T>(CALL_SUCCESS, msg);
    }

    public Callback<T> failedByParameter(){
        return new Callback<T>(CALL_PARAMETER, "上传数据不全或错误");
    }

    public Callback<T> failedByBackstage(){
        return new Callback<T>(CALL_FAILED, "后台出错");
    }

    public Callback<T> failedByPwd(){
        return new Callback<T>(CALL_FAILED, "密码错误");
    }

    public Callback<T> failedByNoFindPhone(){
        return new Callback<T>(CALL_FAILED, "账号不存在");
    }

    public Callback<T> failedByDuplicatePhone() {
        return new Callback<T>(CALL_FAILED, "该账号已被注册存在");
    }

    public Callback<T> failedByJurisdiction(){
        return new Callback<T>(CALL_PARAMETER, "你暂无权限执行该操作");
    }

    public Callback<T> successByLogin(boolean status, T data){
        if (status){
            return new Callback<T>(CALL_SUCCESS, "登录成功", data);
        }
        return new Callback<T>(CALL_SUCCESS, "退出登录成功");
    }

    public Callback<T> successByInsert(){
        return new Callback<T>(CALL_SUCCESS, "操作成功");
    }

    public Callback<T> successBySelect(T data){
        return new Callback<T>(CALL_SUCCESS, "获取成功", data);
    }

}