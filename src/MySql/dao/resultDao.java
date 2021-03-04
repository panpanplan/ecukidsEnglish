package MySql.dao;

import MySql.model.resultMode;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class resultDao extends MySql.base.baseDao {
    private static resultDao rd = null;

    public static synchronized resultDao getInstance(){
        if (rd == null){
            rd = new resultDao();
        }
        return rd;
    }

    //add
    public boolean add(resultMode rm){
        boolean result = false;
        if (rm == null){
            return result;
        }
        try {
            String sql = "insert into result(cname,sname,result,sid) values(?,?,?,?)";
            String[] param = {rm.getCname(),rm.getSname(),rm.getResult(),rm.getSid()};
            if (db.executeUpdate(sql,param) == 1){
                result = true;
            }
        }finally {
            destroy();
        }
        return result;
    }

    //query by tid
    public String[][] list(String sid){
        String[][] result = null;
        if ("".equals(sid) || sid == null){
            return result;
        }
        List<resultMode> stus = new ArrayList<resultMode>();
        int i = 0;
        String sql = "select * from result where sid like ?";
        String[] param1 = {"%"+sid+"%"};
        rs = db.executeQuery(sql,param1);
        try {
            while (rs.next()){
                buildList(rs,stus,i);
                i++;
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
    private void buildList(ResultSet rs, List<resultMode> list, int i) throws SQLException{
        resultMode stu = new resultMode();
        stu.setId(i + 1);
        stu.setCname(rs.getString("cname"));
        stu.setSname(rs.getString("sname"));
        stu.setResult(rs.getString("result"));
        stu.setSid(rs.getString("sid"));
        list.add(stu);
    }

    //
    private void buildRedult(String[][] result,List<resultMode> stus,int j){
        resultMode stu = stus.get(j);
        result[j][0] = String.valueOf(stu.getId());
        result[j][1] = stu.getCname();
        result[j][2] = stu.getSname();
        result[j][3] = stu.getResult();
        result[j][4] = stu.getSid();
    }
}
