package com.hailei.web.servlet;

import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/search")
public class SearchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = request.getParameter("keyword");

        // 查询逻辑，这里假设根据关键词从数据库或其他数据源获取结果列表
        List<String> searchResults = new ArrayList<>();
        searchResults.add("结果1");
        searchResults.add("结果2");
        searchResults.add("结果3");

        // 返回JSON格式的搜索结果
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.println(new Gson().toJson(searchResults));
        out.flush();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 处理POST请求的逻辑
    }
}
