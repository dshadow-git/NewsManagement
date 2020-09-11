package com.training.service.impl;

import com.training.Callback;
import com.training.dao.UserDao;
import com.training.mapper.UserMapper;
import com.training.service.UserService;
import com.training.utils.IntactUtils;
import com.training.utils.SpareData;
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

    SpareData<UserDao> spare = new SpareData<>();

    SpareData<List<UserDao>> spares = new SpareData<>();

    @Override
    public Callback<UserDao> saveByRegister(UserDao user) {

        //判断传入参数是否合法
        if (user == null || !IntactUtils.isIntact(new Object[]{user.getPhone(), user.getPassword(), user.getName()})){
            return new SpareData<UserDao>().failedByParameter();
        }

        //随机生成用户id，并判断该id是否已被用，若被用则重新生成
        String id;
        UserDao userDao;
        int count = 0;
        do{
            //若1000次生成id后还未成功则判断插入失败
            if (count >= 1000){
                return spare.failedByBackstage();
            }
            count ++;

            //把随机生成的整形数据格式化成字符串
            id = 1 + String.format("%5d", new Random().nextInt(99999));

            //根据获取id获取user实例，若为空则未被占用，
            userDao = mapper.selectById(id);
        }while (userDao != null);

        //设置用户id
        user.setUserId(id);

        //持久化在数据库中
        mapper.saveByRegister(user);

        //再次根据id获取user实例，若为空则插入失败，不为空则插入成功
        if (mapper.selectById(id) == null){
            return spare.failedByBackstage();
        } else {
            return spare.successByInsert();
        }
    }

    @Override
    public Callback<UserDao> updateIconById(String id, String icon) {

        //参数判断
        if (!IntactUtils.isIntact(new Object[]{id, icon})){
            return spare.failedByParameter();
        }

        //更改数据库数据
        mapper.updateIconById(id, icon);

        //根据用户id获取user实例
        UserDao user = mapper.selectById(id);
        //如果实例为null则说明id无效错误
        if (user == null){
            return spare.failedByParameter();
        }

        //判断更改后的数据和传入的数据是否一样，一样则说明更改成功，否则失败
        if (user.getIcon().equals(icon)){
            return spare.successByInsert();
        } else {
            return spare.failedByBackstage();
        }
    }

    @Override
    public Callback<UserDao> updateNameById(String id, String name) {

        //参数合法性判断
        if (!IntactUtils.isIntact(new Object[]{id, name})){
            return spare.failedByParameter();
        }

        //更改数据库数据
        mapper.updateNameById(id, name);

        UserDao user = mapper.selectById(id);

        //如果实例为null则说明id无效错误
        if (user == null){
            return spare.failedByParameter();
        }
        //判断更改后的数据和传入的数据是否一样，一样则说明更改成功，否则失败
        if (user.getName().equals(name)){
            return spare.successByInsert();
        } else {
            return spare.failedByBackstage();
        }
    }

    @Override
    public Callback<UserDao> updatePwdById(String id, String oldPwd, String newPwd) {
        //参数合法性判断
        if (!IntactUtils.isIntact(new Object[]{id, newPwd})){
            return spare.failedByParameter();
        }


        UserDao user = mapper.selectById(id);

        //判断id是否有效
        if (user == null){
            return spare.failedByParameter();
        }

        //比对原密码是否正确
        if (!user.getPassword().equals(oldPwd)){
            return spare.failedByPwd();
        }

        //更新密码
        mapper.updatePwdById(id, newPwd);

        //再次获取user实例
        user = mapper.selectById(id);

        //判断是否更改成功
        if (user.getPassword().equals(newPwd)){
            return spare.successByInsert();
        } else {
            return spare.failedByBackstage();
        }
    }

    @Override
    public Callback<List<UserDao>> selectAll() {

        //直接获取所有用户信息
        List<UserDao> users = mapper.selectAll();

        //判断是否获取成功
        if (users != null){
            return spares.successBySelect(users);
        }else {
            return spares.failedByBackstage();
        }
    }

    @Override
    public Callback<UserDao> selectById(String id) {

        //判断参数合法性
        if (id == null){
            return spare.failedByParameter();
        }

        //获取user实例
        UserDao user = mapper.selectById(id);

        //判断是否获取成功
        if (user != null){
            return spare.successBySelect(user);
        } else {
            return spare.failedByBackstage();
        }
    }

    @Override
    public Callback<UserDao> selectByPhone(String phone) {

        //判断参数合法性
        if (phone == null){
            return spare.failedByParameter();
        }

        //判断是否获取成功
        UserDao user = mapper.selectByPhone(phone);
        if (user != null){
            return spare.successBySelect(user);
        } else {
            return spare.failedByBackstage();
        }
    }

    @Override
    public Callback<UserDao> loginUser(String phone, String pwd, String status) {

        //判断参数合法性
        if (!IntactUtils.isIntact(new Object[]{phone, pwd, status})){
            return spare.failedByParameter();
        }

        UserDao user = mapper.selectByPhone(phone);


        if (user == null){
            return spare.failedByPhone();
        }

        //比对密码
        if (!user.getPassword().equals(pwd)){
            return spare.failedByPwd();
        }

        //更改登录状态
        if (updateStatus(user.getUserId(), status).getCode() == SpareData.CALL_SUCCESS){
            return spare.successByLogin(Boolean.parseBoolean(status));
        } else {
            return spare.failedByBackstage();
        }
    }

    @Override
    public Callback<UserDao> updateStatus(String id, String status) {

        //判断参数合法性
        if (id == null || status == null){
            return spare.failedByParameter();
        }

        //判断账号是否存在，若返回实例为空则不存在否则继续
        if (mapper.selectById(id) == null){
            return spare.failedByPhone();
        }

        //更改登录状态
        mapper.updateStatus(id, Boolean.parseBoolean(status));

        if (mapper.getStatus(id) == Boolean.parseBoolean(status)){
            return spare.successByInsert();
        } else {
            return spare.failedByBackstage();
        }
    }

    @Override
    public Boolean getStatus(String id) {
        return mapper.getStatus(id);
    }

}