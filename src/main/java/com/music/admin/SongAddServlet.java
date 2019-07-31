package com.music.admin;

import com.music.admin.dao.SingerDao;
import com.music.admin.formBean.SingerFormBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by donald.tang on 2019/7/31.
 */
public class SongAddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //歌手下拉列表
        SingerDao singerDao = new SingerDao();
        List<SingerFormBean> singerList = singerDao.list();

        request.setAttribute("singerList",singerList);

        request.getRequestDispatcher("song_add.jsp").forward(request,response);
    }
}
