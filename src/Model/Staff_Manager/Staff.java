package Model.Staff_Manager;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Statement;

import Controller.DBConnection.DBConnection;

public class Staff {
	private String StaffID;
	private String StaffName;
	private String DateOfBirth;
	private String Gender;
	private int Salary;
	private int Point;
	private String Address;
	public Staff() {
		this.Salary = 0;
		this.Point = 0;
	}
	public Staff(String id, String sname, String dob, String addr, String gen, int sal, int p) {
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
	
	public  boolean editStaffFromDB(Staff s, String name) {
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			try {
				String updateString = "Update Staff set StaffName = '" + name + 
									  "' where StaffID = " + s.getStaffID();
				Statement statement =  DBConnection.connection.createStatement();
				statement.executeUpdate(updateString);
				DBConnection.connection.commit();
				statement.close();
				return true;
			} catch (SQLException e) {
				System.out.println("Cannot edit staff: " + e);
				return false;
			}
		}
		else {
			System.out.println("Something went wrong!!!");
			return false;
		}
	}
	
	public boolean deleteStaffFromDB(Staff s) {
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			try {
				String deleteString = "Delete from Staff where StaffID = " + s.getStaffID();
				Statement statement =  DBConnection.connection.createStatement();
				statement.executeUpdate(deleteString);
				DBConnection.connection.commit();
				statement.close();
				return true;
			} catch (SQLException e) {
				System.out.println("Cannot delete staff: " + e);
				return false;
			}
		}
		else {
			System.out.println("Something went wrong!!!");
			return false;
		}
	}
	
	public void deleteStaff(Staff s) {
		if (deleteStaffFromDB(s)) {
			System.out.println("Detele staff successfully!!!");
		}
		else {
			System.out.println("Failed to delete staff!!!");
		}
	}
	public void sortStaffPoint() {
		System.out.println("Sort success!");
	}
}
