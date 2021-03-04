package EnglishView.view.student;

import MySql.Dao;
import MySql.base.baseDao;
import MySql.dao.selectclassDao;
import MySql.model.selectclassModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 添加选课界面
 */
public class addSelectClass extends JFrame {
    private JPanel CPanel,SPanel;

    private JTextField cid;
    private JLabel addState;

    private JButton bAdd,canceld;

    public addSelectClass(){
        init();
    }

    private void init(){
        setTitle("添加选课");
        CPanel = new JPanel();
        CPanel.setLayout(new GridLayout(2,2));
        CPanel.add(new JLabel("课程编号:",SwingConstants.CENTER));
        cid = new JTextField();
        CPanel.add(cid);
        CPanel.add(new JLabel("------------------------------------------------"));
        addState = new JLabel("------------------------------------------------");
        CPanel.add(addState);

        SPanel = new JPanel();
        SPanel.setLayout(new GridLayout(1,2));
        bAdd = new JButton("添加课程");
        bAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check()){
                    //首先判断选课表里有没有要选的课
                    boolean isSelect = ((selectclassDao) baseDao.getAbilityDao(Dao.selectclassDao))
                            .pQueryByCid(cid.getText());
                    if (isSelect){
                        System.out.println("此课程已选");
                        addState.setText("此课程已选");
                    }else {
                        //然后查询课程表有没有要选的课
                        selectclassModel scm = ((selectclassDao)baseDao.getAbilityDao(Dao.selectclassDao))
                                .pQuetyByCid(cid.getText());
                        //System.out.println(scm.getCid()+scm.getCname()+scm.getCdescb()+scm.getSid());
                        //然后进行添加选课
                        boolean isSuccess = ((selectclassDao) baseDao.getAbilityDao(Dao.selectclassDao))
                                .add(scm);
                        //添加成功 刷新选课列表
                        if (isSuccess){
                            setEmpty();
                            String[][] result = ((selectclassDao)baseDao.getAbilityDao(Dao.selectclassDao))
                                    .list(scm.getSid());
                            sMainView.initsTable(sMainView.sTable,result);
                            addState.setText("添加成功！");
                        }else {
                            addState.setText("添加失败！");
                        }
                    }
                }else {
                    addState.setText("请输入完整！");
                }
            }
        });
        SPanel.add(bAdd);
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

    private void buildSelectClass(selectclassModel scm){
        scm.setCid(cid.getText());
    }

    private void setEmpty(){
        cid.setText("");
    }
}
