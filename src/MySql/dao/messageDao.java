package MySql.dao;

import MySql.base.baseDao;
import MySql.model.messageModel;
import MySql.model.resultMode;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class messageDao extends MySql.base.baseDao {
    private static messageDao md = null;

    public static synchronized messageDao getInstance(){
        if (md == null){
            md = new messageDao();
        }
        return md;
    }

    //add
    public boolean add(messageModel mm){
        boolean result = false;
        if (mm == null){
            return result;
        }
        try {
            String sql = "insert into message(datetime,people,content,tid) values(?,?,?,?)";
            String[] param = {mm.getDatetime(),mm.getPeople(),mm.getContent(),mm.getTid()};
            if (db.executeUpdate(sql,param) == 1){
                result = true;
            }
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
        List<messageModel> stus = new ArrayList<messageModel>();
        int i = 0;
        String sql = "select * from message where tid like ?";
        String[] param1 = {"%"+tid+"%"};
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
    private void buildList(ResultSet rs, List<messageModel> list, int i) throws SQLException {
        messageModel stu = new messageModel();
        stu.setId(i + 1);
        stu.setDatetime(rs.getString("datetime"));
        stu.setPeople(rs.getString("people"));
        stu.setContent(rs.getString("content"));
        stu.setTid(rs.getString("tid"));
        list.add(stu);
    }

    //
    private void buildRedult(String[][] result,List<messageModel> stus,int j){
        messageModel stu = stus.get(j);
        result[j][0] = String.valueOf(stu.getId());
        result[j][1] = stu.getDatetime();
        result[j][2] = stu.getPeople();
        result[j][3] = stu.getContent();
        result[j][4] = stu.getTid();
    }
}
