package EnglishView.view;

import MySql.Dao;
import MySql.base.baseDao;
import MySql.dao.userDao;
import MySql.model.userModel;

import javax.swing.*;
import java.awt.*;

/*
注册界面
 */
public class SiginView extends JFrame {
    private JPanel PCenter,PSouth;
    private JComboBox userType;
    private JTextField userName,userId,userSex,userTel;
    private JPasswordField passwordField;
    private JLabel siginState;
    private JButton siginB,cancelB;

    public SiginView(){
        init();
    }

    private void init(){
        this.setTitle("用户注册");
        this.setIconImage(new ImageIcon("libs/注册.png").getImage());

        PCenter = new JPanel();
        PCenter.setLayout(new GridLayout(7,2));
        PCenter.add(new JLabel("用户类型:",SwingConstants.CENTER));
        userType = new JComboBox();
        userType.addItem("teacher");
        userType.addItem("student");
        PCenter.add(userType);
        PCenter.add(new JLabel("用户编号:",SwingConstants.CENTER));
        userId = new JTextField();
        PCenter.add(userId);
        PCenter.add(new JLabel("用户密码:",SwingConstants.CENTER));
        passwordField = new JPasswordField();
        PCenter.add(passwordField);
        PCenter.add(new JLabel("用户姓名:",SwingConstants.CENTER));
        userName = new JTextField();
        PCenter.add(userName);
        PCenter.add(new JLabel("用户性别:",SwingConstants.CENTER));
        userSex = new JTextField();
        PCenter.add(userSex);
        PCenter.add(new JLabel("用户电话:",SwingConstants.CENTER));
        userTel = new JTextField();
        PCenter.add(userTel);
        PCenter.add(new JLabel("--------------------------------------------"));
        siginState = new JLabel("--------------------------------------------");
        PCenter.add(siginState);

        PSouth = new JPanel();
        PSouth.setLayout(new GridLayout(1,2));
        siginB = new JButton("注册");
        siginB.addActionListener((e) -> {
            if (check()){
                userModel um = new userModel();
                buildUser(um);
                boolean isSuccess = ((userDao) baseDao.getAbilityDao(Dao.userDao)).add(um);
                if (isSuccess){
                    setEmpty();
                    siginState.setText("注册成功！快去登录吧！");
                    //dispose();
                }else {
                    siginState.setText("注册失败！");
                }
            }else {
                siginState.setText("请输入完整！");
            }
        });
        PSouth.add(siginB);
        cancelB = new JButton("取消");
        cancelB.addActionListener((e) -> {
            dispose();
        });
        PSouth.add(cancelB);

        this.add(PCenter,BorderLayout.CENTER);
        this.add(PSouth,BorderLayout.SOUTH);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setBounds(450, 250, 375, 300);
        this.setResizable(false);
        this.setVisible(true);
    }

    private boolean check(){
        boolean result = false;
        if ("".equals(userId.getText()) || "".equals(userName.getText())||
        "".equals(userSex.getText()) || "".equals(userTel.getText())){
            return result;
        }else {
            result = true;
        }
        return result;
    }

    private void buildUser(userModel um){
        um.setUtype(userType.getSelectedItem().toString());
        um.setUid(userId.getText());
        um.setUpassword(String.valueOf(passwordField.getPassword()));
        um.setUname(userName.getText());
        um.setUsex(userSex.getText());
        um.setUtel(userTel.getText());
    }

    private void setEmpty(){
        userId.setText("");
        userName.setText("");
        userSex.setText("");
        userTel.setText("");
    }
}
