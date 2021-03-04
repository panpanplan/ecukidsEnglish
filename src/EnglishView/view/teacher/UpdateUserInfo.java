package EnglishView.view.teacher;

import MySql.AppConstants;
import MySql.Dao;
import MySql.base.baseDao;
import MySql.dao.userDao;
import MySql.model.userModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 信息修改界面
 */
public class UpdateUserInfo extends JFrame {
    private JPanel CPanel,SPanel;

    private JTextField userSex,userName,userTel;
    private JLabel updateState;

    private JButton update,canceld;

    public UpdateUserInfo(){
        init();
    }

    private void init(){
        setTitle("修改用户信息");
        CPanel = new JPanel();
        CPanel.setLayout(new GridLayout(4,2));
        CPanel.add(new JLabel("用户姓名:",SwingConstants.CENTER));
        userName = new JTextField();
        CPanel.add(userName);
        CPanel.add(new JLabel("用户性别:",SwingConstants.CENTER));
        userSex = new JTextField();
        CPanel.add(userSex);
        CPanel.add(new JLabel("用户电话:",SwingConstants.CENTER));
        userTel = new JTextField();
        CPanel.add(userTel);
        CPanel.add(new JLabel("------------------------------------------------",SwingConstants.CENTER));
        updateState = new JLabel("------------------------------------------------");
        CPanel.add(updateState);

        SPanel = new JPanel();
        SPanel.setLayout(new GridLayout(1,2));
        update = new JButton("修改");
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check()){
                    userModel um = new userModel();
                    buildUser(um);
                    boolean isSuccess = ((userDao) baseDao.getAbilityDao(Dao.userDao)).update(um);
                    if (isSuccess){
                        Empty();
                        um = ((userDao)baseDao.getAbilityDao(Dao.userDao))
                                .queryforMain(AppConstants.userType,AppConstants.userID);
                        tMainView.uname.setText(um.getUname());
                        tMainView.usex.setText(um.getUsex());
                        tMainView.utel.setText(um.getUtel());
                        updateState.setText("修改成功！");
                    }else {
                        updateState.setText("修改失败！");
                        System.out.println("修改失败！");
                    }
                }else {
                    updateState.setText("请输入完整！");
                }
            }
        });
        SPanel.add(update);
        canceld = new JButton("退出");
        canceld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        SPanel.add(canceld);

        this.add(CPanel,BorderLayout.CENTER);
        this.add(SPanel,BorderLayout.SOUTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(470,300,400,200);
        setResizable(false);
        setVisible(true);
    }

    private boolean check(){
        boolean result = false;
        if ("".equals(userName.getText()) || "".equals(userSex.getText()) || "".equals(userTel.getText())){
            return result;
        }else {
            result = true;
        }
        return result;
    }

    private void buildUser(userModel um){
        um.setUid(AppConstants.userID);
        um.setUname(userName.getText());
        um.setUsex(userSex.getText());
        um.setUtel(userTel.getText());
    }

    private void Empty(){
        userName.setText("");
        userSex.setText("");
        userTel.setText("");
    }
}
