package EnglishView.view.student;

import MySql.AppConstants;
import MySql.Dao;
import MySql.base.baseDao;
import MySql.dao.resultDao;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;

/**
 * 查看成绩界面
 */
public class foundResultView extends JFrame {
    private JPanel CPanel;
    private JTable cTable;
    private JScrollPane jScrollPane;
    private DefaultTableModel myTableModel;

    private String[] column = {"id","课程名","学生名","分数","学生编号"};

    public foundResultView(){
        init();
    }

    private void init(){
        setTitle("成绩查询");
        CPanel = new JPanel();
        CPanel.setLayout(new GridLayout(1,1));
        String[][] result = ((resultDao) baseDao.getAbilityDao(Dao.resultDao)).list(AppConstants.userID);
        myTableModel = new DefaultTableModel(result,column);
        cTable = new JTable(myTableModel);
        DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
        cr.setHorizontalAlignment(JLabel.CENTER);
        cTable.setDefaultRenderer(Object.class,cr);
        initcTable(cTable,result);
        jScrollPane = new JScrollPane(cTable);
        CPanel.add(jScrollPane);

        this.add(CPanel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setBounds(450,250,700,500);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void initcTable(JTable jTable,String[][] result){
        ((DefaultTableModel) jTable.getModel()).setDataVector(result,column);
        jTable.setRowHeight(20);
        TableColumn firsetColumn = jTable.getColumnModel().getColumn(0);
        firsetColumn.setPreferredWidth(100);
        firsetColumn.setMaxWidth(100);
        firsetColumn.setMinWidth(100);
        TableColumn secondColumn = jTable.getColumnModel().getColumn(1);
        secondColumn.setPreferredWidth(200);
        secondColumn.setMaxWidth(200);
        secondColumn.setMinWidth(200);
        TableColumn thirdColumn = jTable.getColumnModel().getColumn(2);
        thirdColumn.setPreferredWidth(200);
        thirdColumn.setMaxWidth(200);
        thirdColumn.setMinWidth(200);
        TableColumn fourthColumn = jTable.getColumnModel().getColumn(3);
        fourthColumn.setPreferredWidth(100);
        fourthColumn.setMaxWidth(100);
        fourthColumn.setMinWidth(100);
        TableColumn fivethColumn = jTable.getColumnModel().getColumn(4);
        fivethColumn.setPreferredWidth(100);
        fivethColumn.setMaxWidth(100);
        fivethColumn.setMinWidth(100);
    }
}
