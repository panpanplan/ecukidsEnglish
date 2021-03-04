package EnglishView.view.student;

import MySql.Dao;
import MySql.base.baseDao;
import MySql.dao.qiandaoDao;
import MySql.model.qiandaoModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 添加签到界面
 */
public class qiandaoView extends JFrame {
    private JPanel CPanel,SPanel;

    private JTextField people,content;

    private JLabel qState;

    private JButton bQiandao,canceld;

    public qiandaoView(){
        init();
    }

    private void init(){
        setTitle("签到");
        CPanel = new JPanel();
        CPanel.setLayout(new GridLayout(3,2));
        CPanel.add(new JLabel("签到人:",SwingConstants.CENTER));
        people = new JTextField();
        CPanel.add(people);
        CPanel.add(new JLabel("签到内容:",SwingConstants.CENTER));
        content = new JTextField();
        CPanel.add(content);
        CPanel.add(new JLabel("------------------------------------------------"));
        qState = new JLabel("------------------------------------------------");
        CPanel.add(qState);

        SPanel = new JPanel();
        SPanel.setLayout(new GridLayout(1,2));
        bQiandao = new JButton("签到");
        bQiandao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check()){
                    qiandaoModel qm = new qiandaoModel();
                    buildQiandao(qm);
                    boolean isSuccess = ((qiandaoDao) baseDao.getAbilityDao(Dao.qiandaoDao)).add(qm);
                    if (isSuccess){
                        setEmpty();
                        System.out.println("签到成功！");
                        qState.setText("签到成功！");
                    }else {
                        System.out.println("签到失败！");
                        qState.setText("签到失败！");
                    }
                }else {
                    qState.setText("请输入完整！");
                }
            }
        });
        SPanel.add(bQiandao);
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
        setBounds(470,300,400,180);
        setResizable(false);
        setVisible(true);
    }

    private boolean check(){
        boolean result = false;
        if ("".equals(people.getText()) || "".equals(content.getText())){
            return result;
        }else {
            result = true;
        }
        return result;
    }

    private void buildQiandao(qiandaoModel qm){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        qm.setDatetime(df.format(new Date()));
        qm.setPeople(people.getText());
        qm.setContent(content.getText());
    }

    private void setEmpty(){
        people.setText("");
        content.setText("");
    }
}
