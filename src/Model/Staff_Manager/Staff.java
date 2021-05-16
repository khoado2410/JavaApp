package Model.Staff_Manager;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Controller.DBConnection.DBConnection;
import Model.Food_Product.Food;

public class Staff {
	private String StaffID;
	private String StaffName;
	private String DateOfBirth;
	private String Gender;
	private int Salary;
	private int Point;
	private String Address;
	private String hourWorking;
	
	public String getHourWorking() {
		return hourWorking;
	}
	public void setHourWorking(String hourWorking) {
		this.hourWorking = hourWorking;
	}


	private ArrayList<Staff> listStaff;
	
	public ArrayList<Staff> getListStaff() {
		return listStaff;
	}
	public void setListStaff(ArrayList<Staff> listStaff) {
		this.listStaff = listStaff;
	}
	
	public Staff(String id) {
		this.StaffID = id;
	}
	
	public Staff() {
		this.Salary = 0;
		this.Point = 0;
		this.listStaff = new ArrayList<Staff>();
	}
	public Staff(String id, String sname, String dob, String gen, String addr, int sal, int p) {
		this.StaffID = id;
		this.StaffName = sname;
		this.DateOfBirth = dob;
		this.Gender = gen;
		this.Salary = sal;
		this.Address = addr;
		this.Point = p;
	}
	public String getStaffID() {
		return StaffID;
	}
	public void setStaffID(String staffID) {
		StaffID = staffID;
	}
	public String getStaffName() {
		return StaffName;
	}
	public void setStaffName(String staffName) {
		StaffName = staffName;
	}
	public String getDateOfBirth() {
		return DateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public int getSalary() {
		return Salary;
	}
	public void setSalary(int salary) {
		Salary = salary;
	}
	public int getPoint() {
		return Point;
	}
	public void setPoint(int point) {
		Point = point;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	
	public boolean loadStaff() {
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			try {
				String sp_load = "{call loadStaff}";
				Statement statement = DBConnection.connection.createStatement();
				ResultSet rs = statement.executeQuery(sp_load);
				while (rs.next()) {
					String fid = rs.getString("StaffID");
					String fn = rs.getString("StaffName");
					String fp = rs.getString("DateOfBirth");
					String ft = rs.getString("Gender");
					String add = rs.getString("Address");
					int sal = rs.getInt("Salary");
					int ffn = rs.getInt("Point");
					Staff f = new Staff(fid, fn, fp, ft, add, sal, ffn);
					this.listStaff.add(f);
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
	
	public String getIDMax() {
		String id = "";

		String cmd1 = "SELECT StaffID FROM Staff WHERE StaffID = (SELECT max(StaffID) FROM Staff)";

		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			String temp = "";
			int length = 0;
			try {
				PreparedStatement ps = DBConnection.connection.prepareStatement(cmd1);
				ResultSet results = ps.executeQuery();
				while (results.next()) {
					id = results.getString("StaffID");
					
					DBConnection.connection.commit();
				}
				results.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return id;
	}
	
	public boolean addStaffToDB(Staff s) {
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			try {
				String storeProcedure = "{call sp_insertStaff(?,?,?,?,?,?,?)}";
				CallableStatement callableStatement = DBConnection.connection.prepareCall(storeProcedure);
				callableStatement.setString(1, s.getStaffID());
				callableStatement.setString(2, s.getStaffName());
				callableStatement.setString(3, s.getDateOfBirth());
				callableStatement.setString(4, s.getGender());
				callableStatement.setString(5, s.getAddress());
				callableStatement.setInt(6, s.getSalary());
				callableStatement.setInt(7, s.getPoint());
				callableStatement.execute();
				DBConnection.connection.commit();
				callableStatement.close();
				return true;
			}
			catch (SQLException e) {
				System.out.println("Cannot insert staff: " + e);
				return false;
			}
		}
		else {
			System.out.println("Something went wrong!!!");
			return false;
		}
	}
	
	public  boolean editStaffFromDB(Staff s) {
		String querySql = "{call editStaff(?, ?, ?, ?, ?, ?, ?)}";
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)){
			try {
				CallableStatement cstmt = DBConnection.connection.prepareCall(querySql);
				
				cstmt.setString(1, s.getStaffID());
				cstmt.setString(2, s.getStaffName());
				cstmt.setString(3, s.getDateOfBirth());
				cstmt.setString(4, s.getGender());
				cstmt.setString(5, s.getAddress());
				cstmt.setInt(6, s.getSalary());
				cstmt.setInt(7, s.getPoint());
				
				cstmt.executeUpdate();

				return true;
			} catch (SQLException e) {
				System.out.println("Cannot insert food to menu: " + e);
				return false;
			}
		} else {
			System.out.println("Something went wrong!!!");
			return false;
		}
	}
	
	public boolean deleteStaffFromDB(Staff s) {
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			try {
				
				String deleteString = "{call removeStaff(?)}";
				
				CallableStatement cstmt = DBConnection.connection.prepareCall(deleteString);
				cstmt.setString(1, s.getStaffID());
				cstmt.executeUpdate();

				return true;
			} catch (SQLException e) {
				System.out.println("Cannot delete food: " + e);
				return false;
			}
		} else {
			System.out.println("Something went wrong!!!");
			return false;
		}
	}
	
	
	public void sortStaffPoint() {
		System.out.println("Sort success!");
	}
}
