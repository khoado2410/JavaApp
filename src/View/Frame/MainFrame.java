package View.Frame;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Controller.PanelChange.ControllerPanel;

public class MainFrame extends JPanel {
	public JPanel main;
	private JPanel mainPanel;
	private ControllerPanel controller;
	
	public static int use = 0;
	
	public MainFrame() {
		setLayout(new BorderLayout());
		controller = new ControllerPanel(this);
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(57, 60, 73));
		mainPanel.setBorder(new EmptyBorder(300, 500, 300, 500));
		mainPanel.setLayout(new GridLayout(3, 2, 20, 20));	
		for (int i = 0; i< 6; i++) {
			JButton jbtn = new JButton();
			jbtn.setForeground(Color.WHITE);
			jbtn.setBackground(Color.BLACK);
			switch(i) {
				case 0:
					jbtn.setIcon(new ImageIcon("./src/Icon/Stock.png"));
					jbtn.setText("Stock");
					controller.setEventButton(jbtn, jbtn.getText());	
					break;
				case 1:
					jbtn.setIcon(new ImageIcon("./src/Icon/Cashier.png"));
					jbtn.setText("Cashier");
					controller.setEventButton(jbtn, jbtn.getText());
					break;
				case 2:
					jbtn.setIcon(new ImageIcon("./src/Icon/CashBook.png"));
					jbtn.setText("Cash Book");
					controller.setEventButton(jbtn, jbtn.getText());
					break;
				case 3:
					jbtn.setIcon(new ImageIcon("./src/Icon/Staff.png"));
					jbtn.setText("Staff");
					controller.setEventButton(jbtn, jbtn.getText());
					break;
				case 4:
					jbtn.setIcon(new ImageIcon("./src/Icon/Login.png"));
					jbtn.setText("Login");
					controller.setEventButton(jbtn, jbtn.getText());
					break;
				case 5:
					jbtn.setIcon(new ImageIcon("./src/Icon/Deal.png"));
					jbtn.setText("Deal");
					break;
			}
			
			
			mainPanel.add(jbtn);
		}
		add(mainPanel, BorderLayout.CENTER);
		setVisible(true);;
	}
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.setLayout(new BorderLayout());
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setTitle("Restaurant Management System");
		jf.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jf.add(new MainFrame());
		jf.setVisible(true);
	}
}
