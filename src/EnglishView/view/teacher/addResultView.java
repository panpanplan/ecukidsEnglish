package EnglishView.view.teacher;

import MySql.Dao;
import MySql.base.baseDao;
import MySql.dao.resultDao;
import MySql.model.resultMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 发布成绩界面
 */
public class addResultView extends JFrame {
    private JPanel CPanel,SPanel;

    private JTextField cname,sname,num,sid;

    private JLabel rState;

    private JButton bResult,canceld;

    public addResultView(){
        setTitle("发布学生成绩");
        CPanel = new JPanel();
        CPanel.setLayout(new GridLayout(5,2));
        CPanel.add(new JLabel("课程名称:",SwingConstants.CENTER));
        cname = new JTextField();
        CPanel.add(cname);
        CPanel.add(new JLabel("学生名称:",SwingConstants.CENTER));
        sname = new JTextField();
        CPanel.add(sname);
        CPanel.add(new JLabel("分数:",SwingConstants.CENTER));
        num = new JTextField();
        CPanel.add(num);
        CPanel.add(new JLabel("学生编号:",SwingConstants.CENTER));
        sid = new JTextField();
        CPanel.add(sid);
        CPanel.add(new JLabel("------------------------------------------------"));
        rState = new JLabel("------------------------------------------------");
        CPanel.add(rState);

        SPanel = new JPanel();
        SPanel.setLayout(new GridLayout(1,2));
        bResult = new JButton("发布");
        bResult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check()){
                    resultMode rm = new resultMode();
                    buildResult(rm);
                    boolean isSuccess = ((resultDao) baseDao.getAbilityDao(Dao.resultDao)).add(rm);
                    if (isSuccess){
                        setEmpty();
                        rState.setText("成绩发布成功！通知学生查看吧！");
                    }else {
                        rState.setText("发布失败！");
                    }
                }else {
                    rState.setText("请输入完整！");
                }
            }
        });
        SPanel.add(bResult);
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
        setBounds(470,300,400,220);
        setResizable(false);
        setVisible(true);
    }

    private boolean check(){
        boolean result = false;
        if ("".equals(cname.getText()) || "".equals(sname.getText())
        || "".equals(num.getText()) || "".equals(sid.getText())){
            return result;
        }else {
            result = true;
        }
        return result;
    }

    private void buildResult(resultMode rm){
        rm.setCname(cname.getText());
        rm.setSname(sname.getText());
        rm.setResult(num.getText());
        rm.setSid(sid.getText());
    }

    private void setEmpty(){
        cname.setText("");
        sname.setText("");
        num.setText("");
        sid.setText("");
    }
}
