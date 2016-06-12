package com.application.package1;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


class MainFrame extends JFrame {

	MainFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		

		Toolkit kit = Toolkit.getDefaultToolkit();

		Image img = kit.getImage("D:/workspace/Application/src/com/application/package1/mainFrame.png");
		setIconImage(img);
		setTitle("English");
		pack();
		
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(10, 10));
		panel.setBackground(Color.WHITE);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setSize(StartFrame.screenWidth, 30);
//		getContentPane().add(menuBar);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(0, 0, StartFrame.screenWidth, 100);
		textArea.setBackground(Color.RED);
		getContentPane().add(textArea);

		
		JMenu menu = new JMenu("Lessons");
		menu.setMnemonic(KeyEvent.VK_A);
		
		menu.getAccessibleContext().setAccessibleDescription("File menu");
		
		JMenuItem menuItem1 = new JMenuItem("Open");
		menuItem1.setAccelerator(KeyStroke.getKeyStroke('O',KeyEvent.CTRL_MASK));
		menuItem1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showInputDialog("Hallo");
				
			}
		});
		
		
		JMenuItem menuItem2 = new JMenuItem("Edit");
		JMenuItem menuItem3 = new JMenuItem("Save");
		JMenuItem menuItem4 = new JMenuItem("Save As");
		JMenuItem menuItem5 = new JMenuItem("Exit");
		
		menu.add(menuItem1);
		menu.addSeparator();
		menu.add(menuItem2);
		menu.addSeparator();
		menu.add(menuItem3);
		menu.addSeparator();
		menu.add(menuItem4);
		menu.addSeparator();
		menu.add(menuItem5);
		
		menuBar.add(menu);

		JMenu menu2 = new JMenu("Home tasks");
		menu2.setMnemonic(KeyEvent.VK_B);
		menu2.getAccessibleContext().setAccessibleDescription("Edit menu");
		menuBar.add(menu2);
		
		
		JMenu menu3 = new JMenu("Help");
		menu3.setMnemonic(KeyEvent.VK_C);
		menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
		menuBar.add(menu3);
		
		
		
		setJMenuBar(menuBar);
		add(panel);
		setVisible(true);
		pack();
		setExtendedState(MAXIMIZED_BOTH);
	}

}
