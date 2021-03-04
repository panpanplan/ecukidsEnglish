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
 * 教师添加课程界面
 */
public class AddClass extends JFrame {

    private JPanel jPanelCenter,jPanelSouth;
    private JButton addButton,exitButton;
    private JTextField classId,classname,classDescb,classTid;
    private JLabel addState;

    public AddClass(){
        init();
    }

    private void init(){
        setTitle("添加课程");

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
        addState = new JLabel("------------------------------------------------");
        jPanelCenter.add(addState);

        jPanelSouth = new JPanel();
        jPanelSouth.setLayout(new GridLayout(1,2));
        addButton = new JButton("添加");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check()){
                    classModel cm = new classModel();
                    buildClass(cm);
                    boolean isSuccess = ((classDao) baseDao.getAbilityDao(Dao.classDao)).add(cm);
                    if (isSuccess){
                        setEmpty();
                        String[][] result = ((classDao)baseDao.getAbilityDao(Dao.classDao))
                                .list(cm.getTid());//
                        tMainView.inittTable(tMainView.tTable,result);
                        addState.setText("添加成功！");
                    }else {
                        addState.setText("添加失败！");
                    }
                }else {
                    addState.setText("请输入完整！");
                }
            }
        });
        jPanelSouth.add(addButton);
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
