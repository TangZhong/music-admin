package com.music.admin;

import com.music.admin.dao.SingerDao;
import com.music.admin.formBean.SingerFormBean;
import com.music.admin.util.FileUtil;
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
import java.util.List;

/**
 * post请求：创建歌手
 * get请求：获取歌手列表
 */
public class SingerServlert extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload fileUpload = new ServletFileUpload(factory);

        List<FileItem> parseRequest = null;
        try {
            parseRequest = fileUpload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
            return;
        }

        SingerFormBean formBean = new SingerFormBean();

        for (FileItem fileItem : parseRequest) {

            String fileName = fileItem.getName();
            String fieldName = fileItem.getFieldName();
            String fieldValue = fileItem.getString();

            String targetPath = request.getSession().getServletContext().getRealPath("/image") + File.separator + fileName;
            InetAddress addr = InetAddress.getLocalHost();
            String savePath = request.getScheme() + "://"+addr.getHostAddress()+":" +
                    request.getServerPort() + request.getContextPath() + "/image/" + fileName;

            if(!fileItem.isFormField()) {
                if("singerImgFile".equals(fieldName)){
                    FileUtil.saveFile(fileItem.getInputStream(),targetPath);
                    formBean.setSingerImgPath(savePath);
                }

            }else {
                if("singerName".equals(fieldName)){
                    formBean.setSingerName(fieldValue);
                }
            }
        }

        SingerDao dao = new SingerDao();

        //保存操作
        dao.addSinger(formBean);

        List<SingerFormBean> singerList = dao.list();

        request.setAttribute("singerList",singerList);

        request.getRequestDispatcher("singer_list.jsp").forward(request,resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        SingerDao dao = new SingerDao();

        String type = request.getParameter("type");

        if("delete".equals(type)){
            int id = Integer.valueOf(request.getParameter("id"));
            dao.deleteSinger(id);
        }

        List<SingerFormBean> singerList = dao.list();

        request.setAttribute("singerList",singerList);

        request.getRequestDispatcher("singer_list.jsp").forward(request,resp);
    }
}
