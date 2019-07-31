package com.music.admin.dao;

import com.music.admin.formBean.UserFormBean;
import com.music.admin.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by donald.tang on 2019/7/31.
 */
public class UserDao {

    public UserFormBean getUser(String account, String password){

        UserFormBean g = null;

        Connection conn = null;
        PreparedStatement ptmt = null;
        ResultSet rs = null;

        try {
            //获取连接
            conn = DbUtil.getConnection();
            //sql, 每行加空格
            String sql = "select * from user where account = ? and password = ?";
            //预编译SQL，减少sql执行
            ptmt = conn.prepareStatement(sql);
            ptmt.setString(1,account);
            ptmt.setString(2,password);

            //执行
            rs = ptmt.executeQuery();
            while (rs.next()) {
                g = new UserFormBean();
                g.setAccount(rs.getString("account"));
                g.setPassword(rs.getString("password"));
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            DbUtil.close(rs,ptmt,conn);
        }
        return g;
    }
}
