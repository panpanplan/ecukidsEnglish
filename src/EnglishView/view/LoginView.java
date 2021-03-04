
package EnglishView.view;


import EnglishView.view.student.sMainView;
import EnglishView.view.teacher.tMainView;
import MySql.Dao;
import MySql.base.baseDao;
import MySql.dao.userDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * 模块说明： 登录界面
 * 
 */
public class LoginView extends JFrame {

	private JPanel jPanelCenter, jPanelSouth;
	private JComboBox usertype;
	private JTextField username;
	private JPasswordField password;
	private JButton loginButton, resetButton,siginB,forgetB;

	public LoginView() {
		init();
	}

	private void init() {
		this.setTitle("登录");
		this.setIconImage(new ImageIcon("libs/英语.png").getImage());

		jPanelCenter = new JPanel();
		jPanelCenter.setLayout(new GridLayout(4, 2));
		jPanelCenter.add(new JLabel("用户类型:",SwingConstants.CENTER));
		usertype = new JComboBox();
		usertype.addItem("teacher");
		usertype.addItem("student");
		jPanelCenter.add(usertype);
		jPanelCenter.add(new JLabel("用户名:",SwingConstants.CENTER));
		username = new JTextField();
		jPanelCenter.add(username);
		jPanelCenter.add(new JLabel("密码:",SwingConstants.CENTER));
		password = new JPasswordField();
		// enter key listener
		password.addKeyListener(new LoginListener());
		jPanelCenter.add(password);
		jPanelCenter.add(new JLabel("--------------------------------------------"));
		jPanelCenter.add(new JLabel("--------------------------------------------"));

		jPanelSouth = new JPanel();
		jPanelSouth.setLayout(new GridLayout(2, 2));
		loginButton = new JButton("登录");
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				check();
			}
		});
		jPanelSouth.add(loginButton);
		resetButton = new JButton("重置");
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				username.setText("");
				password.setText("");
			}
		});
		jPanelSouth.add(resetButton);
		siginB = new JButton("注册");
		siginB.addActionListener((e) -> {
			//System.out.println("注册");
			new SiginView();
		});
		jPanelSouth.add(siginB);
		forgetB = new JButton("忘记密码？");
		forgetB.addActionListener((e) -> {
			//System.out.println("忘记密码");
			new FoundPasswordView();
		});
		jPanelSouth.add(forgetB);

		this.add(jPanelCenter, BorderLayout.CENTER);
		this.add(jPanelSouth, BorderLayout.SOUTH);

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setBounds(450, 250, 375, 200);
		this.setResizable(false);
		this.setVisible(true);
	}

	private class LoginListener extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				check();
			}
		}
	}

	private void check() {
		userDao adminDAO = (userDao) baseDao.getAbilityDao(Dao.userDao);
		if (adminDAO.queryForLogin(usertype.getSelectedItem().toString(),
				username.getText(), String.valueOf(password.getPassword()))) {
			dispose();//关闭窗口
			if (usertype.getSelectedItem().toString().equals("teacher")){
				System.out.println("教师登录");
				new tMainView();
			}else if (usertype.getSelectedItem().toString().equals("student")){
				System.out.println("学生登录");
				new sMainView();
			}
			System.out.println("登录成功");
		} else {
			System.out.println("登录失败");
			username.setText("");
			password.setText("");
		}
	}

}
