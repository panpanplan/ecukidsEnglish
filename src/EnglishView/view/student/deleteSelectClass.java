package EnglishView.view.student;

import MySql.AppConstants;
import MySql.Dao;
import MySql.base.baseDao;
import MySql.dao.selectclassDao;
import MySql.model.selectclassModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 删除选课界面
 */
public class deleteSelectClass extends JFrame {
    private JPanel CPanel,SPanel;

    private JTextField cid;
    private JLabel deleteState;

    private JButton delete,canceld;

    public deleteSelectClass(){
        init();
    }

    private void init(){
        setTitle("删除选课");
        CPanel = new JPanel();
        CPanel.setLayout(new GridLayout(2,2));
        CPanel.add(new JLabel("课程编号:",SwingConstants.CENTER));
        cid = new JTextField();
        CPanel.add(cid);
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
                    selectclassModel scm = new selectclassModel();
                    buildDSC(scm);
                    boolean isSuccess = ((selectclassDao) baseDao.getAbilityDao(Dao.selectclassDao))
                            .delete(scm);
                    if (isSuccess){
                        Empty();
                        String[][] result = ((selectclassDao)baseDao.getAbilityDao(Dao.selectclassDao))
                                .list(scm.getSid());
                        sMainView.initsTable(sMainView.sTable,result);
                        System.out.println("删除成功！");
                        deleteState.setText("删除成功！");
                    }else {
                        System.out.println("删除失败！");
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
        if ("".equals(cid.getText())){
            return result;
        }else {
            result = true;
        }
        return result;
    }

    private void buildDSC(selectclassModel scm){
        scm.setCid(cid.getText());
        scm.setSid(AppConstants.userID);
    }

    private void Empty(){
        cid.setText("");
    }
}
