package MySql.base;

import MySql.DBUtil;
import MySql.Dao;
import MySql.dao.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class baseDao {
    protected final DBUtil db = DBUtil.getDBUtil();
    protected ResultSet rs;
    private static baseDao baseDao;

    public baseDao(){
        init();
    }

    public void init(){

    }

    public static synchronized baseDao getAbilityDao(Dao dao){
        switch (dao){
            case userDao:
                if (baseDao == null||baseDao.getClass() != userDao.class){
                    baseDao = userDao.getInstance();
                }
                break;
            case classDao:
                if (baseDao == null||baseDao.getClass() != classDao.class){
                    baseDao = classDao.getInstance();
                }
                break;
            case messageDao:
                if (baseDao == null || baseDao.getClass() != messageDao.class){
                    baseDao = messageDao.getInstance();
                }
                break;
            case qiandaoDao:
                if (baseDao == null || baseDao.getClass()!= qiandaoDao.class){
                    baseDao = qiandaoDao.getInstance();
                }
                break;
            case resultDao:
                if (baseDao == null || baseDao.getClass() != resultDao.class){
                    baseDao = resultDao.getInstance();
                }
                break;
            case selectclassDao:
                if (baseDao == null||baseDao.getClass() != selectclassDao.class){
                    baseDao = selectclassDao.getInstance();
                }
                break;
            default:
                break;
        }
        return baseDao;
    }

    protected void destroy(){
        try {
            if (rs != null){
                rs.close();
            }
        }catch (SQLException se){
            se.printStackTrace();
        }
    }
}
