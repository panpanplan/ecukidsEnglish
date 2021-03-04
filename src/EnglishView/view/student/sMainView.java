package EnglishView.view.student;

import EnglishView.view.LoginView;
import MySql.AppConstants;
import MySql.Dao;
import MySql.base.baseDao;
import MySql.dao.selectclassDao;
import MySql.dao.userDao;
import MySql.model.userModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class sMainView extends JFrame {
    private JPanel NPanel,CPanel,SPanel;
    public static JTable sTable;
    private JScrollPane jScrollPane;
    private DefaultTableModel myTableModel;

    private JLabel utype,uid;
    public static JLabel uname,usex,utel;
    private JButton updateInfo;

    private JButton blookResult,bQiandao,bMessage,changeUser;

    private JButton addSelectClass,deleteSelectClass,refreshSelectClass;

    public static String[] column = {"id","课程编号","课程名称","课程描述","学生编号"};

    public sMainView(){
        init();
    }

    private void init(){
        userDao userd = (userDao) baseDao.getAbilityDao(Dao.userDao);
        userModel userm = userd.queryforMain(AppConstants.userType,AppConstants.userID);
        setTitle("学生信息管理系统-----ecukidsEnglish");
        setIconImage(new ImageIcon("libs/学生.png").getImage());
        //North--------------------------------------
        NPanel = new JPanel();
        NPanel.setLayout(new GridLayout(3,6));
        NPanel.add(new JLabel("用户类型:",SwingConstants.CENTER));
        utype = new JLabel(userm.getUtype(),SwingConstants.CENTER);
        NPanel.add(utype);
        NPanel.add(new JLabel("用户编号:",SwingConstants.CENTER));
        uid = new JLabel(userm.getUid(),SwingConstants.CENTER);
        NPanel.add(uid);
        NPanel.add(new JLabel("用户姓名:",SwingConstants.CENTER));
        uname = new JLabel(userm.getUname(),SwingConstants.CENTER);
        NPanel.add(uname);
        NPanel.add(new JLabel("用户性别:",SwingConstants.CENTER));
        usex = new JLabel(userm.getUsex(),SwingConstants.CENTER);
        NPanel.add(usex);
        NPanel.add(new JLabel("用户电话:",SwingConstants.CENTER));
        utel = new JLabel(userm.getUtel(),SwingConstants.CENTER);
        NPanel.add(utel);
        updateInfo = new JButton("修改信息");
        updateInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateStudentInfo();
            }
        });
        NPanel.add(updateInfo);
        NPanel.add(new JLabel("好好学习天天向上",SwingConstants.CENTER));
        NPanel.add(new JLabel("选课管理",SwingConstants.CENTER));
        addSelectClass = new JButton("添加选课");
        addSelectClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new addSelectClass();
            }
        });
        NPanel.add(addSelectClass);
        deleteSelectClass = new JButton("取消选课");
        deleteSelectClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new deleteSelectClass();
            }
        });
        NPanel.add(deleteSelectClass);
        refreshSelectClass = new JButton("查询课程");
        refreshSelectClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new foundClass();
            }
        });
        NPanel.add(refreshSelectClass);
        NPanel.add(new JLabel("已选课程↓",SwingConstants.CENTER));
        NPanel.add(new JLabel("已选课程↓",SwingConstants.CENTER));


        //Center-------------------------------------
        CPanel = new JPanel();
        CPanel.setLayout(new GridLayout(1,1));
        String[][] result = ((selectclassDao) baseDao.getAbilityDao(Dao.selectclassDao)).list(userm.getUid());
        myTableModel = new DefaultTableModel(result,column);
        sTable = new JTable(myTableModel);
        DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
        cr.setHorizontalAlignment(JLabel.CENTER);
        sTable.setDefaultRenderer(Object.class,cr);
        initsTable(sTable,result);
        jScrollPane = new JScrollPane(sTable);
        CPanel.add(jScrollPane);

        //South--------------------------------------
        SPanel = new JPanel();
        SPanel.setLayout(new GridLayout(1,3));
        blookResult = new JButton("查询成绩");
        blookResult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new foundResultView();
            }
        });
        SPanel.add(blookResult);
        bQiandao = new JButton("签到");
        bQiandao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new qiandaoView();
            }
        });
        SPanel.add(bQiandao);
        bMessage = new JButton("留言");
        bMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new addMessageView();
            }
        });
        SPanel.add(bMessage);
        changeUser = new JButton("切换用户");
        changeUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginView();
            }
        });
        SPanel.add(changeUser);

        this.add(NPanel,BorderLayout.NORTH);
        this.add(CPanel,BorderLayout.CENTER);
        this.add(SPanel,BorderLayout.SOUTH);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setBounds(450,250,800,600);
        this.setResizable(false);
        this.setVisible(true);

    }

    public static void initsTable(JTable jTable,String[][] result){
        ((DefaultTableModel) jTable.getModel()).setDataVector(result,column);
        jTable.setRowHeight(20);
        TableColumn firsetColumn = jTable.getColumnModel().getColumn(0);
        firsetColumn.setPreferredWidth(90);
        firsetColumn.setMaxWidth(90);
        firsetColumn.setMinWidth(90);
        TableColumn secondColumn = jTable.getColumnModel().getColumn(1);
        secondColumn.setPreferredWidth(100);
        secondColumn.setMaxWidth(100);
        secondColumn.setMinWidth(100);
        TableColumn thirdColumn = jTable.getColumnModel().getColumn(2);
        thirdColumn.setPreferredWidth(200);
        thirdColumn.setMaxWidth(200);
        thirdColumn.setMinWidth(200);
        TableColumn fourthColumn = jTable.getColumnModel().getColumn(3);
        fourthColumn.setPreferredWidth(300);
        fourthColumn.setMaxWidth(300);
        fourthColumn.setMinWidth(300);
        TableColumn fivethColumn = jTable.getColumnModel().getColumn(4);
        fivethColumn.setPreferredWidth(90);
        fivethColumn.setMaxWidth(90);
        fivethColumn.setMinWidth(90);
    }
}
