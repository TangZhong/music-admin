package com.music.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.music.admin.dao.SongDao;
import com.music.admin.formBean.SongFormBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

/**
 * 可以根据singerName来过滤
 */
public class SongApiServlert extends AbstractApiServlet{

    @Override
    protected JSONObject loadApiData(HttpServletRequest req) {
        SongDao songDao = new SongDao();
        List<SongFormBean> songList = null;

        String singerName = req.getParameter("singerName");

        songList = songDao.queryBySingerName(singerName);

        JSONArray ja = new JSONArray();

        if(songList != null && songList.size() > 0){

            for(SongFormBean song : songList){
                JSONObject jo = new JSONObject();
                jo.put("song_name",song.getSongName());
                jo.put("song_url",song.getSongPath());
                jo.put("singer_name",song.getSingerName());
                jo.put("lrc_url",song.getLrcPath());
                ja.add(jo);
            }
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("songlist",ja);

        return jsonObject;
    }
}
