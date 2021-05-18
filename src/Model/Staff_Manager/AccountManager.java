package Model.Staff_Manager;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import Model.Food_Product.*;

import Controller.DBConnection.*;;

public class AccountManager {
	private String accID;
	private String username;
	private String password;
	
	public static int checkLogin = 0;
	public static String managerID;
	public static String usernameManager;
	
	private ArrayList<AccountManager> listAcc;
	
	public ArrayList<AccountManager> getListAcc() {
		return listAcc;
	}

	public void setListAcc(ArrayList<AccountManager> listAcc) {
		this.listAcc = listAcc;
	}

	public AccountManager() {
		this.listAcc = new ArrayList<AccountManager>();
;	}
	
	public AccountManager(String id, String user, String pass) {
		this.accID = id;
		this.username = user;
		this.password = pass;
	}
	
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
	
	public boolean loadAccount() {
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			try {
				String sp_load = "{call loadAcc}";
				Statement statement = DBConnection.connection.createStatement();
				ResultSet rs = statement.executeQuery(sp_load);
				while (rs.next()) {
					String fid = rs.getString("AccountManagerID");
					String fn = rs.getString("Username");
					String fp = rs.getString("Password");
					
					AccountManager f = new AccountManager(fid, fn, fp);
					this.listAcc.add(f);
				}
				statement.close();
				return true;
			} catch (SQLException e) {
				System.out.println("Cannot load menu: " + e);
				return false;
			}
		} else {
			System.out.println("Something went wrong!!!");
			return false;
		}
	}
	
	public boolean logIn(String username, String pass) {
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			try {
				 String query = "Select * from AccountManager WHERE Username=? and Password =?";
				   PreparedStatement ps =  DBConnection.connection.prepareStatement(query);
				   ps.setString(1, username);
				   ps.setString(2, pass);
				   ResultSet rs = ps.executeQuery();
				   int check = 0;
				   while (rs.next()) {
						//System.out.println("AAAA: " + rs.getString("AccountManagerID"));
						String fid = rs.getString("AccountManagerID");
						String fn = rs.getString("Username");
						String fp = rs.getString("Password");
						AccountManager f = new AccountManager(fid, fn, fp);
						this.listAcc.add(f);
						System.out.println(fid);
						//check++;
					}
				   ps.close();
				   if(this.listAcc.size() > 0)
					   return true;
				   else
				   		return false;

			} catch (SQLException e) {
	
				return false;
			}
		}
		else {
			System.out.println("Something went wrong!!!");
			return false;
		}
	}
	
	public boolean ChooseTable(Table a) {
		String sql= "INSERT INTO [Table](TableID, TableStatus, NumClients)"
	               + "VALUES(?,?,?)";
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			try {
				
				PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
	            ps.setString(1, a.getIdTable());
	            ps.setInt(2, a.getStatus());
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

