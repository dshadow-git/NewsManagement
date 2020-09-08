package com.training.mapper;

import com.training.dao.UserDao;

import java.util.List;

public interface UserMapper {

    //注册添加用户(type和icon采用默认值)
    public void saveByRegister(UserDao user);

    //根据用户id修改用户头像地址
    public void updateIconById(String id, String icon);

    //根据用户id修改用户昵称
    public void updateNameById(String id, String name);

    //根据用户id修改用户密码
    public void updatePwdById(String id, String pwd);

    //获取所用用户信息
    public List<UserDao> selectAll();

    //根据用户id获取用户数据
    public UserDao selectById(String id);

    //根据用户电话号码获取用户数据
    public UserDao selectByPhone(String phone);

}
