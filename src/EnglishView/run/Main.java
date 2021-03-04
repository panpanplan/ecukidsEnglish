package EnglishView.run;

import EnglishView.view.LoginView;
import MySql.DBUtil;

public class Main {
    public static void initDB(){
        DBUtil dbUtil = DBUtil.getDBUtil();

        //查询是否初始化
        if (dbUtil.exeute("select 1 from user")){
            return;
        }

        //第一次启动需要创建默认表
        dbUtil.exeute("create table if not exists user(id INTEGER PRIMARY KEY   AUTOINCREMENT,"+//用户表
                "utype varchar(32),"+
                "uid varchar(32),"+
                "upassword varchar(32),"+
                "uname varchar(32),"+
                "usex varchar(32),"+
                "utel varchar(32))");
        dbUtil.exeute("create table if not exists class(id INTEGER PRIMARY KEY   AUTOINCREMENT,"+//课程表
                "cid varchar(32),"+
                "cname varchar(32),"+
                "cdescb varchar(32),"+
                "tid varchar(32))");
        dbUtil.exeute("create table if not exists selectclass(id INTEGER PRIMARY KEY   AUTOINCREMENT,"+//选课表
                "cid varchar(32),"+
                "cname varchar(32),"+
                "cdescb varchar(32),"+
                "sid varchar(32))");
        dbUtil.exeute("create table if not exists qiandao(id INTEGER PRIMARY KEY   AUTOINCREMENT,"+//签到表
                "datetime varchar(32),"+
                "people varchar(32),"+
                "content varchar(32))");
        dbUtil.exeute("create table if not exists message(id INTEGER PRIMARY KEY   AUTOINCREMENT,"+//留言表
                "datetime varchar(32),"+
                "people varchar(32),"+
                "content varchar(32),"+
                "tid varchar(32))");
        dbUtil.exeute("create table if not exists result(id INTEGER PRIMARY KEY   AUTOINCREMENT,"+//成绩表
                "cname varchar(32),"+
                "sname varchar(32),"+
                "result varchar(32),"+
                "sid varchar(32))");
        dbUtil.exeute("insert into user(id,utype,uid,upassword,uname,usex,utel)" +//默认教师
                " values(1,'teacher','0','0','杜甫','男','10086')");//默认教师用户
        dbUtil.exeute("insert into user(id,utype,uid,upassword,uname,usex,utel)" +//默认学生
                " values(2,'student','1','1','李白','男','10010')");//默认学生用户
        dbUtil.exeute("insert into class(id,cid,cname,cdescb,tid)"+//默认课程
                " values(1,'11','English','学习英语','0')");
        dbUtil.exeute("insert into selectclass(id,cid,cname,cdescb,sid)"+//默认选课
                " values(1,'11','English','学习英语','1')");
        dbUtil.exeute("insert into qiandao(id,datetime,people,content)" +//默认签到
                " values(1,'2020-2-12 16:30:25','007','007签到')");
        dbUtil.exeute("insert into message(id,datetime,people,content,tid)"+//默认留言
                " values(1,'2020-2-12 16:30:25','007','007留言','0')");
        dbUtil.exeute("insert into result(id,cname,sname,result,sid)"+//默认成绩
                " values(1,'English','李白','60','1')");
    }

    public static void main(String[] args){
        initDB();//初始化数据库
        new LoginView();//进入登录界面
        DBUtil.getDBUtil().close();//清理工作
    }
}
