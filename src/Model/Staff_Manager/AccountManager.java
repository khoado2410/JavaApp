package Model.Staff_Manager;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Dictionary;
import java.util.Enumeration;
import Model.Food_Product.*;

import Controller.DBConnection.*;;

public class AccountManager {
	private String accID;
	private String username;
	private String password;
	public String getAccID() {
		return accID;
	}
	public void setAccID(String accID) {
		this.accID = accID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void logIn(String username, String pass) {
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			try {
				String searchString = "Select * from AccountManager where Username = '" + username +"' and Password ='"+ pass+"'";
				Statement statement =  DBConnection.connection.createStatement();
				ResultSet rs = statement.executeQuery(searchString);
				if(rs.wasNull()) {
					System.out.println("Ten dang nhap hoac mat khau khong hop le!");
				}else {
					System.out.println("Dang nhap thanh cong!");
				}
				
				statement.close();
			} catch (SQLException e) {
				System.out.println("Cannot search food: " + e);
			}
		}
		else {
			System.out.println("Something went wrong!!!");
		}
	}
	
	public boolean ChooseTable(Table a) {
		String sql= "INSERT INTO [Table](TableID, TableStatus, NumClients)"
	               + "VALUES(?,?,?)";
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			try {
				
				PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
	            ps.setString(1, a.getIdTable());
	            ps.setInt(2, a.isStatus());
	            ps.setInt(3, a.getNumClients());
	            ps.executeUpdate();
	            
				return true;
			}
			catch (SQLException e) {
				System.out.println("Cannot insert table: " + e);
				return false;
			}
		}
		else {
			System.out.println("Something went wrong!!!");
			return false;
		}
	}
	public void ChooseTablee(Table a) {
		if (ChooseTable(a)) {
			System.out.println("Add table successfully!!!");
		}
		else {
			System.out.println("Failed to add table!!!");
		}
	}
	
	public boolean OrderFood(Bill b, Dictionary listFood) {
		String sql= "INSERT INTO Bill(BillID, StaffName, Payment, BillStatus, TableID, CheckInHour, CheckOutHour, AccountManagerID)"
	               + "VALUES(?,?,?,?,?,?,?,?)";
		String sql_addFood= "INSERT INTO BillDetail(BillID, FoodID, Quantity)"
	               + "VALUES(?,?,?)";
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			try {
				
				// thêm bill
				PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
	            ps.setString(1, b.getBillID());
	            ps.setString(2, b.getStaffName());
	            ps.setInt(3, b.getPayment());
	            ps.setInt(4, b.getStatus());
	            ps.setString(5,  b.getTableID());
	            ps.setString(6, b.getCheckInHour());
	            ps.setString(7, b.getCheckOutHour());
	            ps.setString(8, b.getAccManagerID());
	            
	            // thêm chi tiết bill
	            ps.executeUpdate();
	            PreparedStatement ps1 = DBConnection.connection.prepareStatement(sql_addFood);
				Enumeration enu = listFood.keys();
			    Enumeration fo = listFood.elements();
			    while (enu.hasMoreElements()) {
			    	ps1.setString(1, b.getBillID());
			    	ps1.setString(2, fo.nextElement().toString());
			    	ps1.setString(3, enu.nextElement().toString());
			    	ps1.executeUpdate();
			    }
				return true;
			}
			catch (SQLException e) {
				System.out.println("Cannot insert Bill: " + e);
				return false;
			}
		}
		else {
			System.out.println("Something went wrong!!!");
			return false;
		}
	}
	
	public boolean printBill(Bill a, String BillID) {
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			try {
				String searchString = "Select * from Bill where BillID = '" + BillID +"'";
				Statement statement =  DBConnection.connection.createStatement();
				ResultSet rs = statement.executeQuery(searchString);
				while(rs.next()) {
					String id = rs.getString(1);
					String staffName = rs.getString(2);
					int payment = rs.getInt(3);
					int status = rs.getInt(4);
					String tableID = rs.getString(5);
					String hourIn = rs.getString(6);
					String hourOut = rs.getString(7);
					String AccountManager = rs.getString(8);
					System.out.println("ID: " + id);
					System.out.println("Staff name: " + staffName);
					System.out.println("Payment: " + payment);
					System.out.println("Bill status: " + status);
					System.out.println("TableID " + tableID);
					System.out.println("CheckInHour: " + hourIn);
					System.out.println("CheckOutHour: " + hourOut);
					System.out.println("AccountManagerID: " + AccountManager);
				}
				statement.close();
				return true;
			} catch (SQLException e) {
				System.out.println("Cannot search food: " + e);
				return false;
			}
		}
		else {
			System.out.println("Something went wrong!!!");
			return false;
		}
	}
	//hello
	
}

