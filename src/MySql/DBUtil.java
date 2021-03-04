package MySql;

import java.sql.*;

public class DBUtil {
    private static DBUtil db;

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    //public static final String URL = "jdbc:mysql://localhost:3308/ecukids_english?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    //public static final String USER = "root";
    //public static final String PASSWORD = "";

    private DBUtil(){

    }

    public static DBUtil getDBUtil(){
        if (db == null){
            db = new DBUtil();
        }
        return db;
    }

    public int executeUpdate(String sql){
        int result = -1;
        if (getConn() == null){
            return result;
        }
        try {
            ps = conn.prepareStatement(sql);
            result = ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public int executeUpdate(String sql, Object[] obj){
        int result = -1;
        if (getConn() == null){
            return result;
        }
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0;i<obj.length; i++){
                ps.setObject(i + 1,obj[i]);
            }
            result = ps.executeUpdate();
            close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public ResultSet executeQuery(String sql){
        if (getConn() == null) {
            return null;
        }
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet executeQuery(String sql,Object[] obj){
        if (getConn() == null) {
            return null;
        }
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i + 1, obj[i]);
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public boolean exeute(String sql){
        if (getConn() == null){
            return false;
        }
        try {
            Statement statement = conn.createStatement();
            statement.execute(sql);
            statement.close();
            return true;
        }catch (SQLException e){
            return false;
        }
    }

    private Connection getConn(){
        try {
            if (conn == null || conn.isClosed()){
//                Class.forName("com.mysql.cj.jdbc.Driver");
//                conn = DriverManager.getConnection(URL,USER,PASSWORD);
                Class.forName("org.sqlite.JDBC");
                conn = DriverManager.getConnection("jdbc:sqlite:ecukidsEnglish.db");
            }
        }catch (ClassNotFoundException e){
            System.out.println("jdbc driver is not found.");
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return conn;
    }

    public void close(){
        try {
            if (rs != null){
                rs.close();
            }
            if (ps != null){
                ps.close();
            }
            if (conn != null){
                conn.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
