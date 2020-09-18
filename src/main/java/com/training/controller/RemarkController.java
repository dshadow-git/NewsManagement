package com.training.controller;

import com.google.gson.Gson;
import com.training.bean.RemarkBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(value = "/remark")
public class RemarkController {

    @RequestMapping(value = "/upload")
    @ResponseBody
    public void saveRemark(@RequestBody RemarkBean remark, HttpServletResponse response) throws IOException {
        response.setContentType("text/html, charset=utf-8");

        response.getWriter().println(new Gson().toJson("暂无数据"));
    }

    @RequestMapping(value = "/delete")
    public void deleteRemark(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html, charset=utf-8");

        response.getWriter().println(new Gson().toJson("暂无数据"));
    }

}
