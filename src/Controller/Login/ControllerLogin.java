package Controller.Login;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Controller.DBConnection.DBConnection;
import Model.Staff_Manager.AccountManager;
import View.Frame.MainFrame;
import View.form.loginForm;

public class ControllerLogin {

	private loginForm loginUI ;

	private AccountManager acc;
	
	public ControllerLogin(loginForm a) {
		this.loginUI = a;
		this.acc = new AccountManager();
	}
	
	public void checkLogin(String username, String pass) {
		

		if(username.isEmpty() || pass.isEmpty()) 		{
			JOptionPane.showMessageDialog(null, "All fields are required");
			System.out.println("Try again");
		}else {
			if(this.acc.logIn(username, pass)) {
				JOptionPane.showMessageDialog(null, "Login success");
				AccountManager.checkLogin++;
				for(int i = 0; i < this.acc.getListAcc().size(); i++) {
					AccountManager.managerID = this.acc.getListAcc().get(i).getAccID();
					AccountManager.usernameManager = this.acc.getListAcc().get(i).getUsername();

				}
				this.loginUI.dispose();
				
			}else {
				JOptionPane.showMessageDialog(null, "Incorrect Username Or Password", "Login Failed", 2);
			}
		}
		
			
		
	}
}
