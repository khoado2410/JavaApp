package View.form;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

import Controller.DBConnection.*;
import Controller.Login.ControllerLogin;
import net.miginfocom.swing.MigLayout;

public class loginForm extends JFrame implements ActionListener{
	
	private JPanel mainFramePanel;
	private JPanel title;
	JLabel formTitle;
	private JPanel formContent;
	private JLabel username;
	public JTextField usernameField;
	private JLabel pass;
	public JPasswordField passField;
	private JPanel buttonField;
	private JButton loginBtn;
	
	private ControllerLogin control = new ControllerLogin(this);
	
	public loginForm () {
		setSize(700, 700);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Restaurant Management System");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		mainFramePanel = new JPanel();
		add(mainFramePanel, BorderLayout.CENTER);
		mainFramePanel.setLayout(new FlowLayout(1, 0, 0));
		mainFramePanel.setBackground(Color.white);
		
		title = new JPanel();
		title.setPreferredSize(new Dimension(screenSize.width - 200, 100));
		title.setLayout(new FlowLayout(FlowLayout.CENTER, 250, 40));
		formTitle = new JLabel("Login");
		formTitle.setHorizontalAlignment(JLabel.LEFT);
		formTitle.setFont(title.getFont().deriveFont(Font.BOLD, 30));
		title.add(formTitle);
		title.setBackground(Color.white);
		
		formContent = new JPanel(new MigLayout("align 50%"));
		formContent.setPreferredSize(new Dimension(screenSize.width - 200, 300));
		formContent.setBackground(Color.white);
		
		username = new JLabel("Username");
		username.setHorizontalAlignment(JLabel.LEFT);
		username.setFont(username.getFont().deriveFont(Font.PLAIN, 20));
		
		usernameField = new JTextField(20);
		usernameField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		usernameField.setFont(usernameField.getFont().deriveFont(Font.PLAIN, 20));
		
		pass = new JLabel("Password");
		pass.setHorizontalAlignment(JLabel.LEFT);
		pass.setFont(pass.getFont().deriveFont(Font.PLAIN, 20));
		
		passField = new JPasswordField(20);
		passField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		passField.setFont(passField.getFont().deriveFont(Font.PLAIN, 20));

		formContent.add(username);
		formContent.add(usernameField, "wrap 50");
		formContent.add(pass);
		formContent.add(passField);
		
		buttonField = new JPanel(new MigLayout("align 50%"));
		//buttonField.setPreferredSize(new Dimension(screenSize.width - 200, 100));
		buttonField.setBackground(Color.white);
		
		loginBtn = new JButton("Login");
		loginBtn.setBackground(Color.PINK);
		loginBtn.setForeground(Color.BLACK);
		//loginBtn.setPreferredSize(new Dimension(100, 40));
		loginBtn.setFont(loginBtn.getFont().deriveFont(Font.PLAIN, 20));
		loginBtn.addActionListener(this);
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setBackground(Color.BLACK);
		cancelBtn.setForeground(Color.WHITE);
		//cancelBtn.setPreferredSize(new Dimension(100, 40));
		cancelBtn.setFont(cancelBtn.getFont().deriveFont(Font.PLAIN, 20));
		cancelBtn.addActionListener(this);
		
		buttonField.add(cancelBtn);
		buttonField.add(Box.createHorizontalStrut(150));
		buttonField.add(loginBtn);
		
		mainFramePanel.add(title);
		mainFramePanel.add(formContent);
		mainFramePanel.add(buttonField);
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new loginForm();
			}
		}); 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String com = e.getActionCommand().toString();
		if(com.equals("Login")) {
			this.control.checkLogin(this.usernameField.getText(), this.passField.getText());
		}if(com.equals("Cancel")) {
			this.dispose();
		}
		
	}

}
