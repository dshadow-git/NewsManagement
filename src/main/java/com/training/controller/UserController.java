package com.training.controller;

import com.google.gson.Gson;
import com.training.Callback;
import com.training.utils.SpareData;
import com.training.dao.UserDao;
import com.training.dao.CoverDao;
import com.training.service.UserService;
import com.training.utils.Base64Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Controller层，接受客户端请求并并向客户端发送数据
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService service;

    //处理登录请求
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public void saveRegisterUser(@RequestBody UserDao user, HttpServletResponse response) throws IOException {
        //设置传输数据的编码形式
        response.setContentType("text/html;charset=utf-8");

        //注册账号并获取状态实例
        Callback<UserDao> callback = service.saveByRegister(user);

        //将Callback实例通过Gson工具转换成json数据
        String jsonString = new Gson().toJson(callback);

        //通过response对象获取Writer输出流向客户端输出对象
        response.getWriter().println(jsonString);
    }

    //处理登录请求
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void loginUser(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        //通过request对象获取客户端传来的参数
        String phone = request.getParameter("phone");
        String pwd = request.getParameter("pwd");
        String status = request.getParameter("status");
        Callback<UserDao> callback = service.loginUser(phone, pwd, status);
        response.getWriter().println(new Gson().toJson(callback));
    }

    //处理昵称更改请求
    @RequestMapping(value = "/update/name", method = RequestMethod.GET)
    public void updateUserName(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String id = request.getParameter("userId");
        String value = request.getParameter("name");
        response.getWriter().println(new Gson().toJson(service.updateNameById(id, value)));
    }

    //处理密码更改请求
    @RequestMapping(value = "/update/pwd", method = RequestMethod.GET)
    public void updateUserPwd(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String id = request.getParameter("userId");
        String oldPwd = request.getParameter("oldPassword");
        String newPwd = request.getParameter("newPassword");
        response.getWriter().println(new Gson().toJson(service.updatePwdById(id, oldPwd, newPwd)));
    }

    //处理头像更改请求
    @RequestMapping(value = "/update/img", method = RequestMethod.POST)
    @ResponseBody
    public void updateUserImg(@RequestBody CoverDao img, HttpServletResponse response) throws IOException {
        //根据用户id创建用户头像路径
        String coverFileName = "img/user/" + img.getUserId() + 0 + ".jpg";
        Callback<UserDao> callback;
        //将传入的Base64编码的图片转换成文件形式，若失败则结束
        if (!Base64Utils.Base64ToImage(img.getCover(), coverFileName)){
            callback = new SpareData<UserDao>().failedByBackstage();
            response.getWriter().println(new Gson().toJson(callback));
            return;
        }
        //将文件路径传入数据库中
        response.getWriter().println(new Gson().toJson(service.updateIconById(img.getUserId(), coverFileName)));
    }

    //处理用户信息获取请求
    @RequestMapping(value = "/select/id", method = RequestMethod.GET)
    public void selectUserById(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String value = request.getParameter("userId");
        response.getWriter().println(new Gson().toJson(service.selectById(value)));
    }

    //处理用户信息获取请求
    @RequestMapping(value = "/select/phone", method = RequestMethod.GET)
    public void selectUserByPhone(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String value = request.getParameter("value");
        Callback<UserDao> callback;
        callback = service.selectByPhone(value);
        response.getWriter().println(new Gson().toJson(callback));
    }

}
