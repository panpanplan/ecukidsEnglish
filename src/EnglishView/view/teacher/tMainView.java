package EnglishView.view.teacher;

import EnglishView.view.LoginView;
import MySql.AppConstants;
import MySql.Dao;
import MySql.base.baseDao;
import MySql.dao.classDao;
import MySql.dao.userDao;
import MySql.model.userModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class tMainView extends JFrame {
    private JPanel NPanel;
    private JPanel CPanel;
    private JPanel SPanel;
    public static JTable tTable;
    private JScrollPane jScrollPane;
    private DefaultTableModel myTableModel;

    private JLabel utype,uid;
    public static JLabel uname,usex,utel;
    private JButton updateInfo;

    private JButton fResult,cQiandao,cMessage,changeUser;

    private JButton cAdd,cUpdate,cDelete,cRefresh;

    public static String[] column = {"id","课程号","课程名","描述","教师号"};

    public tMainView(){
        init();
    }

    private void init(){
        userDao userDao = (userDao) baseDao.getAbilityDao(Dao.userDao);
        userModel userModel = userDao.queryforMain(AppConstants.userType,AppConstants.userID);
        this.setTitle("教师信息管理系统-----ecukidsEnglish");
        this.setIconImage(new ImageIcon("libs/教师节.png").getImage());
        //North--------------------------------------------------------
        NPanel = new JPanel();
        NPanel.setLayout(new GridLayout(3,6));
        //NPanel.add(new JLabel("用户信息",SwingConstants.CENTER));
        //NPanel.add(new JLabel(">>>>>>>",SwingConstants.CENTER));
        NPanel.add(new JLabel("用户类型:",SwingConstants.CENTER));
        utype = new JLabel(userModel.getUtype(),SwingConstants.CENTER);
        NPanel.add(utype);
        NPanel.add(new JLabel("用户编号:",SwingConstants.CENTER));
        uid = new JLabel(userModel.getUid(),SwingConstants.CENTER);
        NPanel.add(uid);
        NPanel.add(new JLabel("用户姓名:",SwingConstants.CENTER));
        uname = new JLabel(userModel.getUname(),SwingConstants.CENTER);
        NPanel.add(uname);
        NPanel.add(new JLabel("用户性别:",SwingConstants.CENTER));
        usex = new JLabel(userModel.getUsex(),SwingConstants.CENTER);
        NPanel.add(usex);
        NPanel.add(new JLabel("用户电话:",SwingConstants.CENTER));
        utel = new JLabel(userModel.getUtel(),SwingConstants.CENTER);
        NPanel.add(utel);
        updateInfo = new JButton("修改信息");
        updateInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateUserInfo();
            }
        });
        NPanel.add(updateInfo);
        NPanel.add(new JLabel("(*^_^*)",SwingConstants.CENTER));
        NPanel.add(new JLabel("课程管理",SwingConstants.CENTER));
        cAdd = new JButton("添加课程");
        cAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddClass();
            }
        });
        NPanel.add(cAdd);
        cUpdate = new JButton("修改课程");
        cUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateClass();
            }
        });
        NPanel.add(cUpdate);
        cDelete = new JButton("删除课程");
        cDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteClass();
            }
        });
        NPanel.add(cDelete);
        cRefresh = new JButton("刷新");
        cRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[][] result = ((classDao) baseDao.getAbilityDao(Dao.classDao)).list(userModel.getUid());
                inittTable(tTable,result);
            }
        });
        NPanel.add(cRefresh);
        NPanel.add(new JLabel("已有课程↓↓",SwingConstants.CENTER));


        //center-------------------------------------------------------
        CPanel = new JPanel();
        CPanel.setLayout(new GridLayout(1,1));
        String[][] result = ((classDao) baseDao.getAbilityDao(Dao.classDao)).list(userModel.getUid());
        myTableModel = new DefaultTableModel(result,column);
        tTable = new JTable(myTableModel);
        DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
        cr.setHorizontalAlignment(JLabel.CENTER);
        tTable.setDefaultRenderer(Object.class,cr);
        inittTable(tTable,result);
        jScrollPane = new JScrollPane(tTable);
        CPanel.add(jScrollPane);


        //south-------------------------------------------------------
        SPanel = new JPanel();
        SPanel.setLayout(new GridLayout(1,4));
        fResult = new JButton("发布成绩");
        fResult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new addResultView();
            }
        });
        SPanel.add(fResult);
        cQiandao = new JButton("查看签到");
        cQiandao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new foundQiandaoView();
            }
        });
        SPanel.add(cQiandao);
        cMessage = new JButton("查看留言");
        cMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new foundMessageView();
            }
        });
        SPanel.add(cMessage);
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

    public static void inittTable(JTable jTable,String[][] result){
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

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
