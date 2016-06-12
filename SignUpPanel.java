package com.application.package1;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

class SignUpPanel extends JPanel {

	public SignUpPanel(JPanel startPanel) {

		setLayout(null);
		setBackground(Color.WHITE);

		JLabel signUpLogLabel = new JLabel("Create login:");
		JLabel signUpPassLabel = new JLabel("Create password:");
		JLabel signUpPassLabel2 = new JLabel("Confirm password:");

		JTextField signUpLogTextField = new JTextField();
		JPasswordField signUpPassTextField = new JPasswordField();
		JPasswordField signUpPassTextField2 = new JPasswordField();

		JButton cancelButton = new JButton("Cancel");
		JButton applyButton = new JButton("Apply");

		signUpLogLabel.setBounds(80, 50, 120, 20);
		signUpPassLabel.setBounds(80, 80, 120, 20);
		signUpPassLabel2.setBounds(80, 110, 120, 20);

		signUpLogTextField.setBounds(210, 50, 100, 20);
		signUpPassTextField.setBounds(210, 80, 100, 20);
		signUpPassTextField2.setBounds(210, 110, 100, 20);
		signUpPassTextField.setEchoChar('*');
		signUpPassTextField2.setEchoChar('*');
		cancelButton.setBounds(120, 160, 80, 30);
		applyButton.setBounds(210, 160, 80, 30);

		signUpLogLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		signUpPassLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		signUpPassLabel2.setHorizontalAlignment(SwingConstants.RIGHT);

		add(signUpLogLabel);
		add(signUpPassLabel);
		add(signUpPassLabel2);
		add(signUpLogTextField);
		add(signUpPassTextField);
		add(signUpPassTextField2);
		add(cancelButton);
		add(applyButton);

		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				startPanel.setVisible(true);

			}
		});

		applyButton.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				String login = signUpLogTextField.getText();

				String password = signUpPassTextField.getText();

				try {

					if (signUpPassTextField.getText().equals(signUpPassTextField2.getText())) {
						Database.r.moveToInsertRow();
						Database.r.updateString("Login", login);
						Database.r.updateString("Password", password);

						Database.r.insertRow();

						Database.s.close();
						Database.s.close();

						Database.s = Database.c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
								ResultSet.CONCUR_UPDATABLE);
						String sql = "SELECT * FROM Table1";
						Database.r = Database.s.executeQuery(sql);
						
					} else {
						JOptionPane.showMessageDialog(null, "Passwords entered are not equals!");
					}

				} catch (Exception ex) {

				}

				setVisible(false);
				startPanel.setVisible(true);
			}
		});

	}

}
