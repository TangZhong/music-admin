package com.music.admin;

import com.music.admin.dao.UserDao;
import com.music.admin.formBean.UserFormBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by donald.tang on 2019/7/31.
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account = req.getParameter("account");
        String password = req.getParameter("password");

        UserDao dao = new UserDao();
        UserFormBean user = dao.getUser(account,password);

        if(user == null){
            req.setAttribute("msg","login fail");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }else {
            req.getSession().setAttribute("user",user);
            req.getRequestDispatcher("index.jsp").forward(req,resp);
        }
    }
}
