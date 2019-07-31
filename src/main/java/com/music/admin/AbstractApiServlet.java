package com.music.admin;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * api接口抽象类
 */
public abstract class AbstractApiServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/json; charset=utf-8");
        PrintWriter out = response.getWriter();

        JSONObject jo = loadApiData(req);

        out.print(jo);

    }

    protected abstract JSONObject loadApiData(HttpServletRequest req);
}
