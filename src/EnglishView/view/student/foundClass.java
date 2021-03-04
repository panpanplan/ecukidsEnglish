package EnglishView.view.student;

import MySql.Dao;
import MySql.base.baseDao;
import MySql.dao.classDao;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;

/**
 * 可选课程查询界面
 */
public class foundClass extends JFrame {
    private JPanel CPanel;
    private JTable cTable;
    private JScrollPane jScrollPane;
    private DefaultTableModel myTableModel;

    private String[] column = {"id","课程号","课程名","描述","教师号"};

    public foundClass(){
        init();
    }

    private void init(){
        setTitle("课程查询");
        CPanel = new JPanel();
        CPanel.setLayout(new GridLayout(1,1));
        String[][] result = ((classDao) baseDao.getAbilityDao(Dao.classDao)).list();
        myTableModel = new DefaultTableModel(result,column);
        cTable = new JTable(myTableModel);
        DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
        cr.setHorizontalAlignment(JLabel.CENTER);
        cTable.setDefaultRenderer(Object.class,cr);
        initcTable(cTable,result);
        jScrollPane = new JScrollPane(cTable);
        CPanel.add(jScrollPane);

        this.add(CPanel,BorderLayout.CENTER);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setBounds(450,250,700,500);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void initcTable(JTable jTable,String[][] result){
        ((DefaultTableModel) jTable.getModel()).setDataVector(result,column);
        jTable.setRowHeight(20);
        TableColumn firsetColumn = jTable.getColumnModel().getColumn(0);
        firsetColumn.setPreferredWidth(50);
        firsetColumn.setMaxWidth(50);
        firsetColumn.setMinWidth(50);
        TableColumn secondColumn = jTable.getColumnModel().getColumn(1);
        secondColumn.setPreferredWidth(100);
        secondColumn.setMaxWidth(100);
        secondColumn.setMinWidth(100);
        TableColumn thirdColumn = jTable.getColumnModel().getColumn(2);
        thirdColumn.setPreferredWidth(200);
        thirdColumn.setMaxWidth(200);
        thirdColumn.setMinWidth(200);
        TableColumn fourthColumn = jTable.getColumnModel().getColumn(3);
        fourthColumn.setPreferredWidth(280);
        fourthColumn.setMaxWidth(280);
        fourthColumn.setMinWidth(280);
        TableColumn fivethColumn = jTable.getColumnModel().getColumn(4);
        fivethColumn.setPreferredWidth(50);
        fivethColumn.setMaxWidth(50);
        fivethColumn.setMinWidth(50);
    }


}
