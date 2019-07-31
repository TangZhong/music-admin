package com.music.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.music.admin.dao.SingerDao;
import com.music.admin.dao.SongDao;
import com.music.admin.formBean.SingerFormBean;
import com.music.admin.formBean.SongFormBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by donald.tang on 2019/7/17.
 */
public class SingerApiServlert extends AbstractApiServlet{

    @Override
    protected JSONObject loadApiData(HttpServletRequest req) {
        SingerDao singerDao = new SingerDao();
        List<SingerFormBean> singerList = singerDao.list();

        JSONArray ja = new JSONArray();

        if(singerList != null && singerList.size() > 0){

            for(SingerFormBean singer : singerList){
                JSONObject jo = new JSONObject();
                jo.put("singerName",singer.getSingerName());
                jo.put("singerImgPath",singer.getSingerImgPath());
                ja.add(jo);
            }
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("singerList",ja);
        return jsonObject;
    }
}
