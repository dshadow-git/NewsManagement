package com.training.controller;

import com.google.gson.Gson;
import com.training.bean.RemarkBean;
import com.training.service.RemarkService;
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
@RequestMapping(value = "/remark")
public class RemarkController {

    @Autowired
    RemarkService service;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public void saveRemark(@RequestBody RemarkBean remark, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        System.out.println(remark.toString());
        response.getWriter().println(new Gson().toJson(service.saveRemark(remark)));
    }

    @RequestMapping(value = "/delete")
    public void deleteRemark(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String remarkId = request.getParameter("remarkId");
        String userId = request.getParameter("userId");
        response.getWriter().println(new Gson().toJson(service.deleteById(remarkId, userId)));
    }

}
