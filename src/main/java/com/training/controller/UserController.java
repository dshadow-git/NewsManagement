package com.training.controller;

import com.google.gson.Gson;
import com.training.Callback;
import com.training.dao.UserDao;
import com.training.service.UserService;
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

    @RequestMapping(value = "/register")
    @ResponseBody
    public void saveRegisterUser(@RequestBody UserDao user, HttpServletResponse response, HttpServletRequest request) throws IOException {
        //设置传输数据的编码形式
        response.setContentType("text/html;charset=utf-8");

        //注册账号并获取状态实例
        Callback<UserDao> callback = service.saveByRegister(user);

        //将Callback实例通过Gson工具转换成json数据
        String jsonString = new Gson().toJson(callback);

        //通过response对象获取Writer输出流向客户端输出对象
        response.getWriter().println(jsonString);
    }



    @RequestMapping(value = "/login")
    public void loginUser(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        //通过request对象获取客户端传来的参数
        String phone = request.getParameter("phone");
        String pwd = request.getParameter("pwd");
        Callback<UserDao> callback = service.loginUser(phone, pwd);
        response.getWriter().println(new Gson().toJson(callback));
    }

    @RequestMapping(value = "/update/name")
    public void updateUserName(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String id = request.getParameter("userId");
        String value = request.getParameter("name");
        response.getWriter().println(new Gson().toJson(service.updateNameById(id, value)));
    }

    @RequestMapping(value = "/update/pwd")
    public void updateUserPwd(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String id = request.getParameter("userId");
        String oldPwd = request.getParameter("oldPassword");
        String newPwd = request.getParameter("newPassword");
        response.getWriter().println(service.updatePwdById(id, oldPwd, newPwd));
    }

    @RequestMapping(value = "/select/id")
    public void selectUserById(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String value = request.getParameter("userId");
        response.getWriter().println(new Gson().toJson(service.selectById(value)));
    }

    @RequestMapping(value = "/select/phone", method = RequestMethod.POST)
    public void selectUserByPhone(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String value = request.getParameter("value");
        Callback<UserDao> callback;
        callback = service.selectByPhone(value);
        response.getWriter().println(new Gson().toJson(callback));
    }

}
