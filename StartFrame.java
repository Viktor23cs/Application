package com.application.package1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.sun.xml.internal.ws.util.StringUtils;

class StartFrame extends JFrame {

	static int screenWidth;
	static int screenHeight;

	public StartFrame() {

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();

		screenWidth = screenSize.width;
		screenHeight = screenSize.height;

		setSize(screenWidth / 3, screenHeight / 3);
		setLocation(screenWidth / 3, screenHeight / 3);
		setResizable(false);

		Image img = kit.getImage("D:/workspace/Application/src/com/application/package1/mainFrame.png");
		setIconImage(img);
		setTitle("English");

		JPanel startPanel = new JPanel();
		startPanel.setBackground(Color.WHITE);
		startPanel.setLayout(null);

		JLabel lbl = new JLabel("Login:");
		lbl.setBounds(90, 60, 100, 20);
		lbl.setHorizontalAlignment(SwingConstants.RIGHT);
		startPanel.add(lbl);

		JLabel lbl2 = new JLabel("Password:");
		lbl2.setBounds(90, 90, 100, 20);
		lbl2.setHorizontalAlignment(SwingConstants.RIGHT);
		startPanel.add(lbl2);

		JTextField txt = new JTextField();
		txt.setBounds(200, 60, 100, 20);
		startPanel.add(txt);

		JPasswordField txt2 = new JPasswordField();
		txt2.setBounds(200, 90, 100, 20);
		txt2.setEchoChar('*');
		startPanel.add(txt2);

		JButton enterButton = new JButton("Enter");
		enterButton.setBounds(210, 140, 80, 30);
		startPanel.add(enterButton);

		JButton signUpButton = new JButton("Sign Up");
		signUpButton.setBounds(100, 140, 80, 30);
		startPanel.add(signUpButton);

		JLabel alarmLbl = new JLabel("Incorrect login or password!");
		alarmLbl.setBounds(140, 30, 200, 20);
		alarmLbl.setForeground(Color.RED);
		alarmLbl.setVisible(false);
		startPanel.add(alarmLbl);

		add(startPanel);

		signUpButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startPanel.setVisible(false);
				SignUpPanel signUpPanel = new SignUpPanel(startPanel);
				add(signUpPanel);
			}
		});

		enterButton.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {

				try {

					String login = txt.getText().trim();
					String password = txt2.getText().trim();

					String sql = "SELECT Login, Password FROM Table1 WHERE Login = '" + login + "' and Password = '"
							+ password + "'";

					Database.r = Database.s.executeQuery(sql);

					int count = 0;
					while (Database.r.next()) {
						count++;
					}

					if (count == 1) {
						Database.s.close();
						Database.c.close();
						dispose();
						MainFrame mainFrame = new MainFrame();

					} else if (count == 0) {
						JOptionPane.showMessageDialog(null, "Incorrect login or password!");
						alarmLbl.setVisible(true);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Shit happens!");
					ex.printStackTrace();
				}
			}

		});
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {

					UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}
				StartFrame startFrame = new StartFrame();
				startFrame.setDefaultCloseOperation(startFrame.EXIT_ON_CLOSE);
				startFrame.setVisible(true);

				try {
					Database db = new Database();
				} catch (ClassNotFoundException e) {
					JOptionPane.showMessageDialog(null, "An Error ocures!");
					;

				} catch (SQLException e) {

					e.printStackTrace();
				}

			}
		});

	}

}
