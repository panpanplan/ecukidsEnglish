package MySql.dao;

import MySql.base.baseDao;
import MySql.model.qiandaoModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 签到增删改查
 */
public class qiandaoDao extends baseDao {
    private static qiandaoDao qd = null;

    public static synchronized qiandaoDao getInstance(){
        if (qd == null){
            qd = new qiandaoDao();
        }
        return qd;
    }

    //add
    public boolean add(qiandaoModel qm){
        boolean result = false;
        if (qm == null){
            return result;
        }
        try {
            String sql = "insert into qiandao(datetime,people,content) values(?,?,?)";
            String[] param = {qm.getDatetime(),qm.getPeople(),qm.getContent()};
            if (db.executeUpdate(sql,param) == 1){
                result = true;
            }
        }finally {
            destroy();
        }
        return result;
    }

    //query by all
    public String[][] list(){
        String[][] result = null;
        List<qiandaoModel> stus = new ArrayList<>();
        int i = 0;
        String sql = "select * from qiandao";
        rs = db.executeQuery(sql);
        try {
            while (rs.next()){
                buildList(rs,stus,i);
                i++;
            }
            if (stus.size() > 0){
                result = new String[stus.size()][4];
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

    //
    private void buildList(ResultSet rs,List<qiandaoModel> list,int i) throws SQLException{
        qiandaoModel qm = new qiandaoModel();
        qm.setId(i+1);
        qm.setDatetime(rs.getString("datetime"));
        qm.setPeople(rs.getString("people"));
        qm.setContent(rs.getString("content"));
        list.add(qm);
    }

    //
    private void buildResult(String[][] result,List<qiandaoModel> stus,int j){
        qiandaoModel qm = stus.get(j);
        result[j][0] = String.valueOf(qm.getId());
        result[j][1] = qm.getDatetime();
        result[j][2] = qm.getPeople();
        result[j][3] = qm.getContent();
    }





}
