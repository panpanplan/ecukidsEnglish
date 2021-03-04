package EnglishView.view.student;

import MySql.Dao;
import MySql.base.baseDao;
import MySql.dao.messageDao;
import MySql.model.messageModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 添加留言界面
 */
public class addMessageView extends JFrame {
    private JPanel CPanel,SPanel;

    private JTextField people,content,tid;

    private JLabel mState;

    private JButton bMessage,canceld;

    public addMessageView(){
        init();
    }

    private void init(){
        setTitle("给教师留言");
        CPanel = new JPanel();
        CPanel.setLayout(new GridLayout(4,2));
        CPanel.add(new JLabel("留言人:",SwingConstants.CENTER));
        people = new JTextField();
        CPanel.add(people);
        CPanel.add(new JLabel("留言内容:",SwingConstants.CENTER));
        content = new JTextField();
        CPanel.add(content);
        CPanel.add(new JLabel("教师编号:",SwingConstants.CENTER));
        tid = new JTextField();
        CPanel.add(tid);
        CPanel.add(new JLabel("------------------------------------------------"));
        mState = new JLabel("------------------------------------------------");
        CPanel.add(mState);

        SPanel = new JPanel();
        SPanel.setLayout(new GridLayout(1,2));
        bMessage = new JButton("留言");
        bMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check()){
                    messageModel mm = new messageModel();
                    buildQiandao(mm);
                    boolean isSuccess = ((messageDao) baseDao.getAbilityDao(Dao.messageDao)).add(mm);
                    if (isSuccess){
                        setEmpty();
                        mState.setText("留言成功!通知教师查看吧！");
                    }else {
                        mState.setText("留言失败！");
                    }
                }else {
                    mState.setText("请输入完整！");
                }
            }
        });
        SPanel.add(bMessage);
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
        if ("".equals(people.getText()) || "".equals(content.getText()) || "".equals(tid.getText())){
            return result;
        }else {
            result = true;
        }
        return result;
    }

    private void buildQiandao(messageModel mm){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        mm.setDatetime(df.format(new Date()));
        mm.setPeople(people.getText());
        mm.setContent(content.getText());
        mm.setTid(tid.getText());
    }

    private void setEmpty(){
        people.setText("");
        content.setText("");
        tid.setText("");
    }
}
