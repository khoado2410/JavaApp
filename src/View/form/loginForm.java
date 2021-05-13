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
import net.miginfocom.swing.MigLayout;

public class loginForm extends JFrame{
	private JPanel mainFramePanel;
	private JPanel title;
	JLabel formTitle;
	private JPanel formContent;
	private JLabel username;
	private JTextField usernameField;
	private JLabel pass;
	private JPasswordField passField;
	private JPanel buttonField;
	private JButton loginBtn;
	
	public loginForm() {
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
		formContent.setPreferredSize(new Dimension(screenSize.width - 200, screenSize.height - 500));
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
		buttonField.setPreferredSize(new Dimension(screenSize.width - 200, 100));
		buttonField.setBackground(Color.white);
		
		loginBtn = new JButton("Login");
		loginBtn.setBackground(Color.BLACK);
		loginBtn.setForeground(Color.PINK);
		loginBtn.setPreferredSize(new Dimension(100, 40));
		loginBtn.setFont(loginBtn.getFont().deriveFont(Font.PLAIN, 20));
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = String.valueOf(usernameField.getText());
				String pass = String.valueOf(passField.getPassword());				
				if(checkUser(username, pass)) {
					JOptionPane.showMessageDialog(null, "Successfully!");
				}
				else
					JOptionPane.showMessageDialog(null, "Try again!");
			}
		});
		
		buttonField.add(loginBtn);
		
		mainFramePanel.add(title);
		mainFramePanel.add(formContent);
		mainFramePanel.add(buttonField);
		setVisible(true);
	}
	
	public static boolean checkUser(String username, String pass) {
		boolean flag = false;
		if(username.isEmpty() || pass.isEmpty()) 		{
			JOptionPane.showMessageDialog(null, "All fields are required");
			System.out.println("Try again");
		}
			
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			
			try {
			   String query = "Select Username, Password from AccountManager WHERE Username=? and Password =?";
			   PreparedStatement ps =  DBConnection.connection.prepareStatement(query);
			   ps.setString(1, username);
			   ps.setString(2, pass);
			   ResultSet rs = ps.executeQuery();
			   
			   if(!rs.next()){
				   flag = false;
				   JOptionPane.showMessageDialog(null, "Incorrect Username Or Password", "Login Failed", 2);
		            ps.close();
		            rs.close();
		       }
			   
			   else {
				   flag = true;
				   System.out.println("Succeeded");
			   }
			   
			   rs.close();
	       	   DBConnection.connection.close();
	       	   
			} catch (SQLException e) {
	            e.printStackTrace();	            
	        }       	             
		}
		else {
			System.out.println("Something went wrong!!!");
		}
		return flag;	
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new loginForm();
			}
		}); 
	}

}