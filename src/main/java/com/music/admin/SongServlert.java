package com.music.admin;

import com.music.admin.dao.SingerDao;
import com.music.admin.dao.SongDao;
import com.music.admin.formBean.SingerFormBean;
import com.music.admin.formBean.SongFormBean;
import com.music.admin.util.FileUtil;
import com.music.admin.util.WebUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by donald.tang on 2019/7/17.
 */
public class SongServlert extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String type = request.getParameter("type");

        SongDao dao = new SongDao();

        if("delete".equals(type)){
            int id = Integer.valueOf(request.getParameter("id"));
            dao.delete(id);
        }

        List<SongFormBean> songList = dao.list();

        request.setAttribute("songList",songList);

        request.getRequestDispatcher("song_list.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        SongFormBean formBean = WebUtils.request2Bean(request, SongFormBean.class);

        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload fileUpload = new ServletFileUpload(factory);

        List<FileItem> parseRequest = null;
        try {
            parseRequest = fileUpload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
            return;
        }
        //获取数据
        for (FileItem fileItem : parseRequest) {

            String fileName = fileItem.getName();
            String fieldName = fileItem.getFieldName();
            String fieldValue = fileItem.getString();

            String targetPath = request.getSession().getServletContext().getRealPath("/music") + File.separator + fileName;
            InetAddress addr = InetAddress.getLocalHost();
            String savePath = request.getScheme() + "://"+addr.getHostAddress()+":" +
                    request.getServerPort() + request.getContextPath() + "/music/" + fileName;

            //判断数据类型是不是普通的form表单字段
            if(!fileItem.isFormField()) {
                if("songFile".equals(fieldName)){
                    FileUtil.saveFile(fileItem.getInputStream(),targetPath);
                    formBean.setSongPath(savePath);
                }
                if("lrcFile".equals(fieldName)){
                    FileUtil.saveFile(fileItem.getInputStream(),targetPath);
                    formBean.setLrcPath(savePath);
                }
            }else {
                if("songName".equals(fieldName)){
                    formBean.setSongName(fieldValue);
                }
                if("singerName".equals(fieldName)){
                    formBean.setSingerName(fieldValue);
                }
            }
        }

        SongDao dao = new SongDao();

        //处理文件上传
        dao.add(formBean);

        List<SongFormBean> songList = dao.list();

        request.setAttribute("songList",songList);

        request.getRequestDispatcher("song_list.jsp").forward(request,resp);

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }
}
