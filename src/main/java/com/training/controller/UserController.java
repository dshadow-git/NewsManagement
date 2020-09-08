package com.training.controller;

import com.google.gson.Gson;
import com.training.dao.UserDao;
import com.training.service.UserService;
import com.training.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService service;

    @RequestMapping(value = "/register")
    @ResponseBody
    public void saveRegisterUser(@RequestBody UserDao user){
        service.saveByRegister(user);
    }

    @RequestMapping(value = "/update/*")
    public void updateUser(HttpServletRequest request){
        String id = request.getParameter("id");
        String value = request.getParameter("value");
        if (request.getRequestURL().toString().endsWith("/name")){
            service.updateNameById(id, value);
        } else if (request.getRequestURL().toString().endsWith("/pwd")) {
            service.updatePwdById(id, value);
        }
    }

    @RequestMapping(value = "/select/id")
    public void selectUserById(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String value = request.getParameter("value");
        UserDao user;
        user = service.selectById(value);

        response.getWriter().println(new Gson().toJson(user));
    }

    @RequestMapping(value = "/select/phone", method = RequestMethod.POST)
    public void selectUserByPhone(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String value = request.getParameter("value");
        UserDao user;
        user = service.selectByPhone(value);

        response.getWriter().println(new Gson().toJson(user));
    }

}
