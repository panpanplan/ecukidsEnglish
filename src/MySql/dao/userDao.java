package MySql.dao;

import MySql.AppConstants;
import MySql.base.baseDao;
import MySql.model.classModel;
import MySql.model.userModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class userDao extends baseDao {
    private static userDao ud = null;

    public static synchronized userDao getInstance(){
        if (ud == null){
            ud = new userDao();
        }
        return ud;
    }

    //add
    public boolean add(userModel stu){
        boolean result = false;
        if (stu == null){
            return result;
        }
        try {
            if (queryByuid(stu.getUid()) == 1){
                return result;
            }
            String sql = "insert into user(utype,uid,upassword,uname,usex,utel) values(?,?,?,?,?,?)";
            String[] param = {stu.getUtype(),stu.getUid(),stu.getUpassword(),
                              stu.getUname(),stu.getUsex(),stu.getUtel()};
            if (db.executeUpdate(sql,param) == 1){
                result = true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            destroy();
        }
        return result;
    }

    //updatePassword
    public boolean updatePassword(userModel stu){
        boolean result = false;
        if (stu == null){
            return result;
        }
        try {
            if (queryByuid(stu.getUid()) == 0){
                return result;
            }
            String sql = "update user set upassword=? where uid=?";
            String[] param = {stu.getUpassword(),stu.getUid()};
            int rowCount = db.executeUpdate(sql,param);
            if (rowCount == 1){
                result = true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            destroy();
        }
        return result;
    }

    //用户登录
    public boolean queryForLogin(String usertype,String username,String password){
        boolean result = false;
        if (username.length() == 0 || password.length() == 0 || usertype.length() ==0){
            return result;
        }
        String sql = "select * from user where utype=? and uid=? and upassword=?";
        String[] param = { usertype,username,password };
        rs = db.executeQuery(sql,param);
        try {
            if (rs.next()){
                result = true;
                AppConstants.userType = rs.getString("utype");
                AppConstants.userID = rs.getString("uid");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            destroy();
        }
        return result;
    }

    //为主界面刷新数据
    public userModel queryforMain(String usertype,String userid){
        userModel userModel = new userModel();
        if (usertype.length() == 0||userid.length() == 0){
            return userModel;
        }
        String sql = "select * from user where utype=? and uid=?";
        String[] param = {usertype,userid};
        rs = db.executeQuery(sql,param);
        try {
            if (rs.next()){
                userModel.setId(rs.getInt("id"));
                userModel.setUtype(rs.getString("utype"));
                userModel.setUid(rs.getString("uid"));
                userModel.setUpassword(rs.getString("upassword"));
                userModel.setUname(rs.getString("uname"));
                userModel.setUsex(rs.getString("usex"));
                userModel.setUtel(rs.getString("utel"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return userModel;
    }

    public boolean update(userModel um){
        boolean result = false;
        if (um == null){
            return result;
        }
        try {
            //check
            if (queryByuid(um.getUid()) == 0){
                return result;
            }
            //update
            String sql = "update user set uname=?,usex=?,utel=? where uid=?";
            String[] param = {um.getUname(),um.getUsex(),um.getUtel(),um.getUid()};
            int rowCount = db.executeUpdate(sql,param);
            if (rowCount == 1){
                result = true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            destroy();
        }
        return result;
    }

    private int queryByuid(String uid) throws SQLException{
        int result = 0;
        if ("".equals(uid) || uid == null){
            return result;
        }
        String checksql = "select * from user where uid=?";
        String[] checkParam = { uid };
        rs = db.executeQuery(checksql,checkParam);
        if (rs.next()){
            result = 1;
        }
        return result;
    }

}
