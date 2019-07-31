package com.music.admin.dao;

import com.music.admin.formBean.SingerFormBean;
import com.music.admin.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by donald.tang on 2019/7/17.
 */
public class SingerDao {

    public void addSinger(SingerFormBean formBean){

        Connection conn = null;
        PreparedStatement ptmt = null;
        try {
            //获取连接
            conn = DbUtil.getConnection();
            //sql
            String sql = "INSERT INTO singer(name,imgPath) VALUES(?,?)";
            //预编译
            ptmt = conn.prepareStatement(sql); //预编译SQL，减少sql执行

            //传参
            ptmt.setString(1, formBean.getSingerName());
            ptmt.setString(2, formBean.getSingerImgPath());

            //执行
            ptmt.execute();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DbUtil.close(ptmt,conn);
        }

    }

    public void deleteSinger(Integer id){

        Connection conn = null;
        PreparedStatement ptmt = null;
        try {
            //获取连接
            conn = DbUtil.getConnection();
            //sql
            String sql = "delete from singer where id = ?";
            //预编译
            ptmt = conn.prepareStatement(sql); //预编译SQL，减少sql执行

            //传参
            ptmt.setInt(1,id);

            //执行
            ptmt.execute();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DbUtil.close(ptmt,conn);
        }

    }

    public List<SingerFormBean> list(){

        List<SingerFormBean> result = new ArrayList<>();
        SingerFormBean g = null;

        Connection conn = null;
        PreparedStatement ptmt = null;
        ResultSet rs = null;

        try {
            //获取连接
            conn = DbUtil.getConnection();
            //sql, 每行加空格
            String sql = "select * from singer where 1 = 1";
            //预编译SQL，减少sql执行
            ptmt = conn.prepareStatement(sql);

            //执行
            rs = ptmt.executeQuery();
            while (rs.next()) {
                g = new SingerFormBean();
                g.setId(rs.getInt("id"));
                g.setSingerName(rs.getString("name"));
                g.setSingerImgPath(rs.getString("imgPath"));
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
