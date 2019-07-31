package com.music.admin.dao;

import com.music.admin.formBean.SongFormBean;
import com.music.admin.util.DbUtil;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by donald.tang on 2019/7/17.
 */
public class SongDao {

    public void add(SongFormBean formBean){

        Connection conn = null;
        PreparedStatement ptmt = null;
        try {
            //获取连接
            conn = DbUtil.getConnection();
            //sql
            String sql = "INSERT INTO song(songName,singerName,lrcPath,songPath) VALUES(?,?,?,?)";
            //预编译
            ptmt = conn.prepareStatement(sql); //预编译SQL，减少sql执行

            //传参
            ptmt.setString(1, formBean.getSongName());
            ptmt.setString(2, formBean.getSingerName());
            ptmt.setString(3, formBean.getLrcPath());
            ptmt.setString(4, formBean.getSongPath());

            //执行
            ptmt.execute();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DbUtil.close(ptmt,conn);
        }

    }

    public void delete(int id){

        Connection conn = null;
        PreparedStatement ptmt = null;
        try {
            //获取连接
            conn = DbUtil.getConnection();
            //sql
            String sql = "delete from song where id = ?";
            //预编译
            ptmt = conn.prepareStatement(sql); //预编译SQL，减少sql执行

            //传参
            ptmt.setInt(1, id);

            //执行
            ptmt.execute();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DbUtil.close(ptmt,conn);
        }

    }

    public List<SongFormBean> queryBySingerName(String singerName){

        List<SongFormBean> result = new ArrayList<>();
        SongFormBean g = null;

        Connection conn = null;
        PreparedStatement ptmt = null;
        ResultSet rs = null;

        try {
            //获取连接
            conn = DbUtil.getConnection();
            //sql, 每行加空格
            String sql = "select * from song where 1 = 1 and singerName = ?";

            //预编译SQL，减少sql执行
            ptmt = conn.prepareStatement(sql);
            ptmt.setString(1,singerName);

            //执行
            rs = ptmt.executeQuery();
            while (rs.next()) {
                g = new SongFormBean();
                g.setId(rs.getInt("id"));
                g.setSongName(rs.getString("songName"));
                g.setSingerName(rs.getString("singerName"));
                g.setLrcPath(rs.getString("lrcPath"));
                g.setSongPath(rs.getString("songPath"));
                result.add(g);
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            DbUtil.close(rs,ptmt,conn);
        }
    }

    public List<SongFormBean> list(){

        List<SongFormBean> result = new ArrayList<>();
        SongFormBean g = null;

        Connection conn = null;
        PreparedStatement ptmt = null;
        ResultSet rs = null;

        try {
            //获取连接
            conn = DbUtil.getConnection();
            //sql, 每行加空格
            StringBuffer sb = new StringBuffer("select * from song where 1 = 1");

            //预编译SQL，减少sql执行
            ptmt = conn.prepareStatement(sb.toString());

            //执行
            rs = ptmt.executeQuery();
            while (rs.next()) {
                g = new SongFormBean();
                g.setId(rs.getInt("id"));
                g.setSongName(rs.getString("songName"));
                g.setSingerName(rs.getString("singerName"));
                g.setLrcPath(rs.getString("lrcPath"));
                g.setSongPath(rs.getString("songPath"));
                result.add(g);
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            DbUtil.close(rs,ptmt,conn);
        }
    }

}
