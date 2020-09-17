package com.training.controller;

import com.google.gson.Gson;
import com.training.dao.JokeDao;
import com.training.service.AssortService;
import com.training.service.JokeService;
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
@RequestMapping(value = "/joke")
public class JokeController {

    @Autowired
    JokeService service;

    @Autowired
    AssortService assortService;

    @RequestMapping(value = "/assort", method = RequestMethod.GET)
    public void selectAll(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(new Gson().toJson(assortService.selectAssortAll()));
    }

    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public void selectByAssort(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String assort = request.getParameter("assort");
        response.getWriter().println(new Gson().toJson(service.selectJokeByAssort(assort)));
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public void saveJoke(@RequestBody JokeDao joke,  HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String coverFileName = "img/user/" + joke.getJokeId() + 0 + ".jpg";
//        if (!Base64Utils.Base64ToImage(joke.getCoverImg(), coverFileName)){
//            Callback<JokeDao> callback = new Callback<>(SpareData.CALL_FAILED, "系统出错");
//            response.getWriter().println(new Gson().toJson(callback));
//            return;
//        }
//        joke.setCoverImg(coverFileName);
//        response.getWriter().println(new Gson().toJson(service.saveJoke(joke)));
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public void searchJoke(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String searchString = request.getParameter("searchString");
        response.getWriter().println(new Gson().toJson(service.selectJokeBySearch(searchString)));
    }
}
