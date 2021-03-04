package EnglishView.view.teacher;

import MySql.AppConstants;
import MySql.Dao;
import MySql.base.baseDao;
import MySql.dao.messageDao;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;

/**
 * 查看留言界面
 */
public class foundMessageView extends JFrame {
    private JPanel CPanel;
    private JTable cTable;
    private JScrollPane jScrollPane;
    private DefaultTableModel myTableModel;

    private String[] column = {"id","留言时间","留言人","留言内容","教师编号"};

    public foundMessageView(){
        init();
    }

    private void init(){
        setTitle("查看留言");
        CPanel = new JPanel();
        CPanel.setLayout(new GridLayout(1,1));
        String[][] result = ((messageDao) baseDao.getAbilityDao(Dao.messageDao)).list(AppConstants.userID);
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
        thirdColumn.setPreferredWidth(100);
        thirdColumn.setMaxWidth(100);
        thirdColumn.setMinWidth(100);
        TableColumn fourthColumn = jTable.getColumnModel().getColumn(3);
        fourthColumn.setPreferredWidth(200);
        fourthColumn.setMaxWidth(200);
        fourthColumn.setMinWidth(200);
        TableColumn fivethColumn = jTable.getColumnModel().getColumn(4);
        fivethColumn.setPreferredWidth(100);
        fivethColumn.setMaxWidth(100);
        fivethColumn.setMinWidth(100);
    }
}
