package MySql.dao;

import MySql.AppConstants;
import MySql.base.baseDao;
import MySql.model.selectclassModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class selectclassDao extends MySql.base.baseDao {
    private static selectclassDao scd = null;

    public static synchronized selectclassDao getInstance(){
        if (scd == null){
            scd = new selectclassDao();
        }
        return scd;
    }

    //add
    public boolean add(selectclassModel scm){
        boolean result = false;
        if (scm == null){
            return result;
        }
        try {
            //check
            if (queryByCid(scm.getCid()) == 1){
                return result;
            }
            //insert
            String sql = "insert into selectclass(cid,cname,cdescb,sid) values(?,?,?,?)";
            String[] param = {scm.getCid(),scm.getCname(),scm.getCdescb(),scm.getSid()};
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
    public boolean delete(selectclassModel scm){
        boolean result = false;
        if (scm == null){
            return result;
        }
        String sql = "delete from selectclass where cid=?";
        String[] param = {scm.getCid()};
        int rowCount = db.executeUpdate(sql,param);
        if (rowCount == 1){
            result = true;
        }
        destroy();
        return result;
    }

    //update
    public boolean update(selectclassModel scm){
        boolean result = false;
        if (scm == null){
            return result;
        }
        try {
            //check
            if (queryByCid(scm.getCid())==0){
                return result;
            }
            //update
            String sql = "update selectclass set cid=?,cname=?,cdescb=?,sid=? where cid=?";
            String[] param = {scm.getCid(),scm.getCname(),scm.getCdescb(),scm.getSid(),scm.getCid()};
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

    //query
    public String[][] list(String sid){
        String[][] result = null;
        if ("".equals(sid) || sid == null ){
            return result;
        }
        List<selectclassModel> stus = new ArrayList<selectclassModel>();
        int i = 0;
        //
        String sql = "select * from selectclass where sid like ?";
        String[] param = {"%"+sid+"%"};
        rs = db.executeQuery(sql,param);
        try {
            while (rs.next()){
                buildList(rs,stus,i);
                i++;
            }
            if (stus.size() > 0){
                result = new String[stus.size()][5];
                for (int j = 0;j<stus.size();j++){
                    buildResult(result,stus,j);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            destroy();
        }
        return result;
    }

    //p
    private void buildList(ResultSet rs,List<selectclassModel> list,int i) throws SQLException{
        selectclassModel stu = new selectclassModel();
        stu.setId(i+1);
        stu.setCid(rs.getString("cid"));
        stu.setCname(rs.getString("cname"));
        stu.setCdescb(rs.getString("cdescb"));
        stu.setSid(rs.getString("sid"));
        list.add(stu);
    }

    private void buildResult(String[][] result,List<selectclassModel> stus,int j){
        selectclassModel scm = stus.get(j);
        result[j][0] = String.valueOf(scm.getId());
        result[j][1] = scm.getCid();
        result[j][2] = scm.getCname();
        result[j][3] = scm.getCdescb();
        result[j][4] = scm.getSid();
    }

    //通过id查询
    private int queryByCid(String cid) throws SQLException {
        int result = 0;
        if ("".equals(cid) || cid == null){
            return result;
        }
        String checksql = "select * from selectclass where cid=?";
        String[] checkParam = { cid };
        rs = db.executeQuery(checksql,checkParam);
        if (rs.next()){
            result = 1;
        }
        return result;
    }

    public boolean pQueryByCid(String cid){
        boolean result = false;
        if ("".equals(cid) || cid == null){
            return result;
        }
        String checksql = "select * from selectclass where cid=?";
        String[] checkParam = {cid};
        try {
            rs = db.executeQuery(checksql,checkParam);
            if (rs.next()){
                result = true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            destroy();
        }
        return result;
    }

    //查询课程
    public selectclassModel pQuetyByCid(String cid){
        selectclassModel scm = new selectclassModel();
        if ("".equals(cid) || cid == null){
            return scm;
        }
        String sql = "select * from class where cid=?";
        String[] param = {cid};
        try {
            rs = db.executeQuery(sql,param);
            if (rs.next()){
                scm.setCid(rs.getString("cid"));
                scm.setCname(rs.getString("cname"));
                scm.setCdescb(rs.getString("cdescb"));
                scm.setSid(AppConstants.userID);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            destroy();
        }
        return scm;
    }
}
