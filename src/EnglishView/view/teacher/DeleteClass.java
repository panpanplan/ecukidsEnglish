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
 * 教师删除课程界面
 */
public class DeleteClass extends JFrame {
    private JPanel CPanel,SPanel;

    private JTextField classId,classTid;

    private JLabel deleteState;

    private JButton delete,canceld;

    public DeleteClass(){
        init();
    }

    private void init(){
        setTitle("删除课程");
        CPanel = new JPanel();
        CPanel.setLayout(new GridLayout(3,2));
        CPanel.add(new JLabel("课程编号:",SwingConstants.CENTER));
        classId = new JTextField();
        CPanel.add(classId);
        CPanel.add(new JLabel("教师编号:",SwingConstants.CENTER));
        classTid = new JTextField();
        CPanel.add(classTid);
        CPanel.add(new JLabel("------------------------------------------------"));
        deleteState = new JLabel("------------------------------------------------");
        CPanel.add(deleteState);

        SPanel = new JPanel();
        SPanel.setLayout(new GridLayout(1,2));
        delete = new JButton("删除");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check()){
                    classModel cm = new classModel();
                    buildClass(cm);
                    boolean isSuccess = ((classDao) baseDao.getAbilityDao(Dao.classDao)).delete(cm);
                    if (isSuccess){
                        setEmpty();
                        String[][] result = ((classDao) baseDao.getAbilityDao(Dao.classDao)).list(cm.getTid());
                        tMainView.inittTable(tMainView.tTable,result);
                        deleteState.setText("删除成功！");
                    }else {
                        deleteState.setText("删除失败！");
                    }
                }else {
                    deleteState.setText("请输入完整！");
                }
            }
        });
        SPanel.add(delete);
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
        setBounds(470,300,400,150);
        setResizable(false);
        setVisible(true);
    }

    private boolean check(){
        boolean result = false;
        if ("".equals(classId.getText()) || "".equals(classTid.getText())){
            return result;
        }else {
            result = true;
        }
        return result;
    }

    private void buildClass(classModel stu){
        stu.setCid(classId.getText());
        stu.setTid(classTid.getText());
    }

    private void setEmpty(){
        classId.setText("");
        classTid.setText("");
    }
}
