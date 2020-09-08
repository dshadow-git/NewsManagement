package com.training.service.impl;

import com.training.dao.UserDao;
import com.training.mapper.UserMapper;
import com.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

//添加Service注解
@Service
public class UserServiceImpl implements UserService {

    //Autowired注解，获取UserMapper类实例
    @Autowired
    UserMapper mapper;

    @Override
    public void saveByRegister(UserDao user) {
        if (user == null){
            return;
        }

        //随机生成用户id，并判断该id是否已被用，若被用则重新生成
        String id;
        UserDao userDao;
        do{
            id = 1 + String.format("%5d", new Random().nextInt(99999));
            userDao = mapper.selectById(id);

        }while (userDao != null);

        //设置用户id
        user.setUserId(id);

        //持久化在数据库中
        mapper.saveByRegister(user);
    }

    @Override
    public void updateIconById(String id, String icon) {
        mapper.updateIconById(id, icon);
    }

    @Override
    public void updateNameById(String id, String name) {
        mapper.updateNameById(id, name);
    }

    @Override
    public void updatePwdById(String id, String pwd) {
        mapper.updatePwdById(id, pwd);
    }

    @Override
    public List<UserDao> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public UserDao selectById(String id) {
        return mapper.selectById(id);
    }

    @Override
    public UserDao selectByPhone(String phone) {
        return mapper.selectByPhone(phone);
    }
}