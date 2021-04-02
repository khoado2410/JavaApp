package Staff_Manager;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Statement;

import DBConnection.DBConnection;


public class Manager_Staff extends AccountManager{
	static boolean addStaffToDB(Staff s) {
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
	public void addStaff(Staff s) {
		if (addStaffToDB(s)) {
			System.out.println("Add staff successfully!!!");
		}
		else {
			System.out.println("Failed to add staff!!!");
		}
	}
	
	static boolean editStaffFromDB(Staff s, String name) {
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
				System.out.println("Cannot delete staff: " + e);
				return false;
			}
		}
		else {
			System.out.println("Something went wrong!!!");
			return false;
		}
	}
	
	public void editStaff(Staff s, String name) {
		if (editStaffFromDB(s, name)) {
			System.out.println("Update staff successfully!!!");
		}
		else {
			System.out.println("Failed to update staff!!!");
		}
	}
	
	static boolean deleteStaffFromDB(Staff s) {
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
	
	public void editTimeSheet() {
		System.out.println("Edit time sheet thanh cong ");
	}
	
	public void sortStaffPoint() {
		System.out.println("Sort success!");
	}
}
