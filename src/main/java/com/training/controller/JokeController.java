package com.training.controller;

import com.google.gson.Gson;
import com.training.bean.JokeBean;
import com.training.service.AssortService;
import com.training.service.JokeService;
import com.training.utils.IntactUtils;
import com.training.utils.SpareData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

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

    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public void saveJoke(@RequestParam("cover") MultipartFile file, HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("text/html;charset=utf-8");

        String userId = request.getParameter("userId");
        String title = request.getParameter("title");
        String post = request.getParameter("postTime");
        String content = request.getParameter("content");
        String source = request.getParameter("source");
        String assort = request.getParameter("assortId");

        if (!IntactUtils.isIntact(userId, title, post, content, content, source, assort)){
            response.getWriter().println(new Gson().toJson(new SpareData<JokeBean>().failedByParameter()));
        }
        Timestamp postTime = new Timestamp(Long.parseLong(post));
        int assortId = Integer.parseInt(assort);
        JokeBean joke = new JokeBean(userId, title, postTime, content, source, assortId);

        String url = request.getSession().getServletContext().getRealPath("/img");

        String filePath ="/joke/" + joke.getUserId() + "_" + file.getOriginalFilename();//保存图
        file.transferTo(new File(url + filePath));
        joke.setCoverImg("img" + filePath);
        response.getWriter().println(new Gson().toJson(service.saveJoke(joke)));
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public void searchJoke(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String searchString = request.getParameter("searchString");
        response.getWriter().println(new Gson().toJson(service.selectJokeBySearch(searchString)));
    }

    //删除新闻
    @RequestMapping(value = "/delete")
    public void deleteJoke(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String jokeId = request.getParameter("jokeId");
        String userId = request.getParameter("userId");
        response.getWriter().println(new Gson().toJson(service.deleteJokeById(userId, jokeId)));
    }
}
