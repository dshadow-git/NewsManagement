package com.training.controller;

import com.google.gson.Gson;
import com.training.bean.JokeBean;
import com.training.service.AssortService;
import com.training.service.JokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
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

    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public void saveJoke(@RequestParam("cover") MultipartFile file, @RequestBody JokeBean joke, HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String url = request.getSession().getServletContext().getRealPath("/img");
        String filePath ="/joke/" + joke.getJokeId() + "_" + file.getOriginalFilename();//保存图
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
    @RequestMapping(value = "delete")
    public void deleteJoke(HttpServletResponse response, HttpServletRequest request){
        String jokeId = request.getParameter("jokeId");
        String userId = request.getParameter("userId");

    }
}
