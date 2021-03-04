package EnglishView.view.teacher;

import MySql.Dao;
import MySql.base.baseDao;
import MySql.dao.qiandaoDao;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;

/**
 * 签到查询界面
 */
public class foundQiandaoView extends JFrame {
    private JPanel CPanel;
    private JTable cTable;
    private JScrollPane jScrollPane;
    private DefaultTableModel myTableModel;

    private String[] column = {"id","签到时间","签到人","签到内容"};

    public foundQiandaoView(){
        init();
    }

    private void init(){
        setTitle("查看签到");
        CPanel = new JPanel();
        CPanel.setLayout(new GridLayout(1,1));
        String[][] result = ((qiandaoDao) baseDao.getAbilityDao(Dao.qiandaoDao)).list();
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
        this.setBounds(450,250,650,500);
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
        secondColumn.setPreferredWidth(150);
        secondColumn.setMaxWidth(150);
        secondColumn.setMinWidth(150);
        TableColumn thirdColumn = jTable.getColumnModel().getColumn(2);
        thirdColumn.setPreferredWidth(150);
        thirdColumn.setMaxWidth(150);
        thirdColumn.setMinWidth(150);
        TableColumn fourthColumn = jTable.getColumnModel().getColumn(3);
        fourthColumn.setPreferredWidth(280);
        fourthColumn.setMaxWidth(280);
        fourthColumn.setMinWidth(280);
    }
}
