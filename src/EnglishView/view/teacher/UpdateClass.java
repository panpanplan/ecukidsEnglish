package EnglishView.view.teacher;

import MySql.Dao;
import MySql.base.baseDao;
import MySql.dao.classDao;
import MySql.model.classModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 课程修改界面
 */
public class UpdateClass extends JFrame {
    private JPanel jPanelCenter,jPanelSouth;
    private JButton updateButton,exitButton;
    private JTextField classId,classname,classDescb,classTid;
    private JLabel updateState;

    public UpdateClass(){
        init();
    }

    private void init(){
        setTitle("修改课程");

        jPanelCenter = new JPanel();
        jPanelCenter.setLayout(new GridLayout(5,2));
        jPanelCenter.add(new JLabel("课程编号:",SwingConstants.CENTER));
        classId = new JTextField();
        jPanelCenter.add(classId);
        jPanelCenter.add(new JLabel("课程名字:",SwingConstants.CENTER));
        classname = new JTextField();
        jPanelCenter.add(classname);
        jPanelCenter.add(new JLabel("课程描述:",SwingConstants.CENTER));
        classDescb = new JTextField();
        jPanelCenter.add(classDescb);
        jPanelCenter.add(new JLabel("教师编号:",SwingConstants.CENTER));
        classTid = new JTextField();
        jPanelCenter.add(classTid);
        jPanelCenter.add(new JLabel("------------------------------------------------"));
        updateState = new JLabel("------------------------------------------------");
        jPanelCenter.add(updateState);

        jPanelSouth = new JPanel();
        jPanelSouth.setLayout(new GridLayout(1,2));
        updateButton = new JButton("修改");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check()){
                    classModel cm = new classModel();
                    buildClass(cm);
                    boolean isSuccess = ((classDao) baseDao.getAbilityDao(Dao.classDao)).update(cm);
                    if (isSuccess){
                        setEmpty();
                        String[][] result = ((classDao) baseDao.getAbilityDao(Dao.classDao)).list(cm.getTid());//主界面显示要求是同一个教师
                        tMainView.inittTable(tMainView.tTable,result);
                        updateState.setText("修改成功！");
                    }else {
                        updateState.setText("修改失败！");
                    }
                }else {
                    updateState.setText("请输入完整！");
                }
            }
        });
        jPanelSouth.add(updateButton);
        exitButton = new JButton("退出");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        jPanelSouth.add(exitButton);

        this.add(jPanelCenter,BorderLayout.CENTER);
        this.add(jPanelSouth,BorderLayout.SOUTH);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(470,300,400,200);
        setResizable(false);
        setVisible(true);
    }

    private boolean check(){
        boolean result = false;
        if ("".equals(classId.getText()) || "".equals(classname.getText())
                || "".equals(classDescb.getText()) || "".equals(classTid.getText())){
            return result;
        }else {
            result = true;
        }
        return result;
    }

    private void buildClass(classModel cm){
        cm.setCid(classId.getText());
        cm.setCname(classname.getText());
        cm.setDescb(classDescb.getText());
        cm.setTid(classTid.getText());
    }

    private void setEmpty(){
        classId.setText("");
        classname.setText("");
        classDescb.setText("");
        classTid.setText("");
    }
}
