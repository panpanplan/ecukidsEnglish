package EnglishView.view;

import MySql.Dao;
import MySql.base.baseDao;
import MySql.dao.userDao;
import MySql.model.userModel;

import javax.swing.*;
import java.awt.*;

public class FoundPasswordView extends JFrame {
    private JPanel PCenter,PSouth;
    private JComboBox userType;
    private JTextField userID;
    private JPasswordField passwordField;
    private JLabel state;
    private JButton rePasswordB,cancelB;

    public FoundPasswordView(){
        init();
    }

    private void init(){
        this.setTitle("修改密码");

        PCenter = new JPanel();
        PCenter.setLayout(new GridLayout(4,2));
        PCenter.add(new JLabel("用户类型:",SwingConstants.CENTER));
        userType = new JComboBox();
        userType.addItem("teacher");
        userType.addItem("student");
        PCenter.add(userType);
        PCenter.add(new JLabel("用户编号:",SwingConstants.CENTER));
        userID = new JTextField();
        PCenter.add(userID);
        PCenter.add(new JLabel("用户密码:",SwingConstants.CENTER));
        passwordField = new JPasswordField();
        PCenter.add(passwordField);
        PCenter.add(new JLabel("--------------------------------------------"));
        state = new JLabel("--------------------------------------------");
        PCenter.add(state);

        PSouth = new JPanel();
        PSouth.setLayout(new GridLayout(1,2));
        rePasswordB = new JButton("修改");
        rePasswordB.addActionListener((e) -> {
            if(check()){
                userModel um = new userModel();
                buildUser(um);
                boolean isSuccess = ((userDao) baseDao.getAbilityDao(Dao.userDao)).updatePassword(um);
                if (isSuccess){
                    state.setText("修改成功！快去登录吧！");
                }else {
                    state.setText("修改失败！");
                }
            }else {
                state.setText("请输入完整！");
            }
        });
        PSouth.add(rePasswordB);
        cancelB = new JButton("取消");
        cancelB.addActionListener((e) -> {
            dispose();
        });
        PSouth.add(cancelB);

        this.add(PCenter,BorderLayout.CENTER);
        this.add(PSouth,BorderLayout.SOUTH);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setBounds(480, 280, 375, 180);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void buildUser(userModel um){
        um.setUid(userID.getText());
        um.setUpassword(String.valueOf(passwordField.getPassword()));
    }

    private boolean check(){
        boolean result = false;
        if ("".equals(userID.getText()) || "".equals(String.valueOf(passwordField.getPassword()))){
            return result;
        }else {
            result = true;
        }
        return result;
    }
}
