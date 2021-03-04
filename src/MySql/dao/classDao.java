package MySql.dao;

import MySql.base.baseDao;
import MySql.model.classModel;
import com.mysql.cj.log.Log;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
* 课程增删改查
* */
public class classDao extends baseDao {
    private static classDao cd = null;

    public static synchronized classDao getInstance(){
        if (cd == null){
            cd = new classDao();
        }
        return cd;
    }

    //add
    public boolean add(classModel stu){
        boolean result = false;
        if (stu == null){
            return result;
        }
        try {
            //check
            if (queryByCid(stu.getCid()) == 1){
                return result;
            }
            //insert
            String sql = "insert into class(cid,cname,cdescb,tid) values(?,?,?,?)";
            String[] param = {stu.getCid(),stu.getCname(),stu.getDescb(),stu.getTid()};
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

    //delete
    public boolean delete(classModel stu){
        boolean result = false;
        if (stu == null){
            return result;
        }
        String sql = "delete from class where cid=?";
        String[] param = {stu.getCid()};
        int rowCount = db.executeUpdate(sql,param);
        if (rowCount == 1){
            result = true;
        }
        destroy();
        return result;
    }

    //update
    public boolean update(classModel stu){
        boolean result = false;
        if (stu == null){
            return result;
        }
        try {
            //check
            if (queryByCid(stu.getCid()) == 0){
                return result;
            }
            //update
            String sql = "update class set cid=?,cname=?,cdescb=?,tid=? where cid=?";
            String[] param = {stu.getCid(),stu.getCname(),stu.getDescb(),stu.getTid(),stu.getCid()};
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

    //query by tid
    public String[][] list(String tid){
        String[][] result = null;
        if ("".equals(tid) || tid == null){
            return result;
        }
        List<classModel> stus = new ArrayList<classModel>();
        int i = 0;
        //int beginNum = (pageNum - 1) * 15;
        String sql = "select * from class where tid like ?";
        //Integer[] param = {beginNum,15};
        String[] param1 = {"%"+tid+"%"};
        rs = db.executeQuery(sql,param1);
        try {
            while (rs.next()){
                buildList(rs,stus,i);
                i++;
                //System.out.println(rs.getString("cid"));
            }
            if (stus.size() > 0){
                result = new String[stus.size()][5];
                for (int j = 0;j<stus.size();j++){
                    buildRedult(result,stus,j);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            destroy();
        }
        return result;
    }

    //query by all
    public String[][] list(){
        String[][] result = null;
        List<classModel> stus = new ArrayList<classModel>();
        int i = 0;
        //int beginNum = (pageNum - 1) * 15;
        String sql = "select * from class";
        //Integer[] param = {beginNum,15};
        //String[] param1 = {"%"+tid+"%"};
        rs = db.executeQuery(sql);
        try {
            while (rs.next()){
                buildList(rs,stus,i);
                i++;
                //System.out.println(rs.getString("cid"));
            }
            if (stus.size() > 0){
                result = new String[stus.size()][5];
                for (int j = 0;j<stus.size();j++){
                    buildRedult(result,stus,j);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            destroy();
        }
        return result;
    }

    //
    private void buildList(ResultSet rs, List<classModel> list,int i) throws SQLException{
        classModel stu = new classModel();
        stu.setId(i + 1);
        //stu.setId(rs.getInt("id"));
        stu.setCid(rs.getString("cid"));
        stu.setCname(rs.getString("cname"));
        stu.setDescb(rs.getString("cdescb"));
        stu.setTid(rs.getString("tid"));
        list.add(stu);
    }

    //
    private void buildRedult(String[][] result,List<classModel> stus,int j){
        classModel stu = stus.get(j);
        result[j][0] = String.valueOf(stu.getId());
        result[j][1] = stu.getCid();
        result[j][2] = stu.getCname();
        result[j][3] = stu.getDescb();
        result[j][4] = stu.getTid();
    }

    //通过id查询
    private int queryByCid(String cid) throws SQLException{
        int result = 0;
        if ("".equals(cid) || cid == null){
            return result;
        }
        String checksql = "select * from class where cid=?";
        String[] checkParam = { cid };
        rs = db.executeQuery(checksql,checkParam);
        if (rs.next()){
            result = 1;
        }
        return result;
    }


}
