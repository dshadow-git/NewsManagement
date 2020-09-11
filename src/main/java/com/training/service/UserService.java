package com.training.service;

import com.training.Callback;
import com.training.dao.UserDao;

import java.util.List;

public interface UserService {

    /**
     * 注册添加用户(type和icon采用默认值)
     * @param user 用户实例
     * @return 状态码，及说明
     */
    public Callback<UserDao> saveByRegister(UserDao user);

    /**
     * 根据用户id修改用户头像地址
     * @param id 用户id
     * @param icon 用户头像地址
     * @return 状态码及说明
     */
    public Callback<UserDao> updateIconById(String id, String icon);

    /**
     * 根据用户id修改用户昵称
     * @param id 用户id
     * @param name 用户昵称
     * @return 状态码及说明
     */
    public Callback<UserDao> updateNameById(String id, String name);

    /**
     * 根据用户id修改用户密码
     * @param id 用户密码
     * @param oldPwd 用户旧密码
     * @param newPwd 用户新密码
     * @return 状态码及说明
     */
    public Callback<UserDao> updatePwdById(String id, String oldPwd, String newPwd);

    /**
     * 获取所用用户信息
     * @return 状态码及说明
     */
    public Callback<List<UserDao>> selectAll();

    /**
     * 根据用户id获取用户数据
     * @param id 用户id
     * @return 状态码，说明及用户集实例
     */
    public Callback<UserDao> selectById(String id);

    /**
     * 根据用户电话号码获取用户数据
     * @param phone 用户手机号
     * @return 状态码，说明及用户实例
     */
    public Callback<UserDao> selectByPhone(String phone);

    /**
     * 用户登录处理
     * @param phone 用户手机号
     * @param pwd 用户密码
     * @return 状态码及说明
     */
    public Callback<UserDao> loginUser(String phone, String pwd, String status);

    /**
     * 更新用户登录状态码
     * @param id 用户id
     * @param status  用户状态（true: 登录状态， false: 非登录状态）
     * @return 状态码及说明
     */
    public Callback<UserDao> updateStatus(String id, String status);

    /**
     * 获取用户登录状态
     * @param id 用户id
     * @return 状态码及说明
     */
    public Boolean getStatus(String id);

}
