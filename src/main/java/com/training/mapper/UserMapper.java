package com.training.mapper;

import com.training.bean.UserBean;

import java.util.List;

public interface UserMapper {

    //注册添加用户(type和icon采用默认值)
    public void saveByRegister(UserBean user);

    //根据用户id修改用户头像地址
    public void updateIconById(String id, String icon);

    //根据用户id修改用户昵称
    public void updateNameById(String id, String name);

    //根据用户id修改用户密码
    public void updatePwdById(String id, String pwd);

    //获取所用用户信息
    public List<UserBean> selectAll();

    //根据用户id获取用户数据
    public UserBean selectById(String id);

    //根据用户电话号码获取用户数据
    public UserBean selectByPhone(String phone);

    //根据用户id设置登录状态
    public void updateStatus(String id, boolean status);

    //根据用户id获取用户登录状态
    public Boolean getStatus(String id);

}
