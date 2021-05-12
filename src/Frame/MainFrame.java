package Frame;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {
	private JPanel mainPanel;
	public MainFrame() {
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Restaurant Management System");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
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
					break;
				case 1:
					jbtn.setIcon(new ImageIcon("./src/Icon/Cashier.png"));
					jbtn.setText("Cashier");
					break;
				case 2:
					jbtn.setIcon(new ImageIcon("./src/Icon/CashBook.png"));
					jbtn.setText("Cash Book");
					break;
				case 3:
					jbtn.setIcon(new ImageIcon("./src/Icon/Staff.png"));
					jbtn.setText("Staff");
					break;
				case 4:
					jbtn.setIcon(new ImageIcon("./src/Icon/Login.png"));
					jbtn.setText("Login");
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
		new MainFrame();
	}
}
