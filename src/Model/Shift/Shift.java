package Model.Shift;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.DBConnection.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Model.Food_Product.Food;
import Model.Staff_Manager.*;

public class Shift {
	private int ShiftID;
	private Staff staff = new Staff();
	private int  WorkingTimeStart;
	private int endWork; 
	private String accID;
	private ArrayList<Shift> listShift;
	private String dateWork;
	
	public String getDateWork() {
		return dateWork;
	}

	public void setDateWork(String dateWork) {
		this.dateWork = dateWork;
	}

	public Shift() {
		this.listShift = new ArrayList<Shift>();
	}
	
	public int getShiftID() {
		return ShiftID;
	}

	public void setShiftID(int shiftID) {
		ShiftID = shiftID;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public int getWorkingTimeStart() {
		return WorkingTimeStart;
	}

	public void setWorkingTimeStart(int workingTimeStart) {
		WorkingTimeStart = workingTimeStart;
	}

	public int getEndWork() {
		return endWork;
	}

	public void setEndWork(int endWork) {
		this.endWork = endWork;
	}

	public String getAccID() {
		return accID;
	}

	public void setAccID(String accID) {
		this.accID = accID;
	}

	public ArrayList<Shift> getListShift() {
		return listShift;
	}

	public void setListShift(ArrayList<Shift> listShift) {
		this.listShift = listShift;
	}


	
	public Shift(int shift, String nameStaff, int end, int start, String acc, String datework) {
		this.ShiftID = shift;
		this.staff.setStaffName(nameStaff);
		this.endWork = end;
		this.WorkingTimeStart = start;
		this.accID = acc;
		this.dateWork = datework;
	}
	
	public boolean deleteShift(int s) {
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			try {
				
				String deleteString = "{call removeShift(?)}";
				
				CallableStatement cstmt = DBConnection.connection.prepareCall(deleteString);
				cstmt.setInt(1, s);
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
	
	public boolean addNewShift(Shift a) {
		String querySql = "{call addShiftAndShiftDetail(?, ?, ?, ?, ?)}";
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)){
			try {
				System.out.println("time: " + a.getDateWork());
				CallableStatement cstmt = DBConnection.connection.prepareCall(querySql);
				cstmt.setString(1, a.getAccID());
				cstmt.setInt(2, a.getWorkingTimeStart());
				cstmt.setInt(3, a.getEndWork());
				cstmt.setString(4, a.getStaff().getStaffName());
				cstmt.setString(5, a.getDateWork());
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
	
	public boolean loadShift() {
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			try {
				String sp_load = "{call loadShift}";
				Statement statement = DBConnection.connection.createStatement();
				ResultSet rs = statement.executeQuery(sp_load);
				while (rs.next()) {
					int fid = rs.getInt("ShiftID");
					String fidman = rs.getString("AccountManagerID");
					int fp = rs.getInt("WorkingTimeStart");
					int ft = rs.getInt("WorkingTimeEnd");
					String fq = rs.getString("StaffName");
					String dateWork = rs.getString("dateWorking");
					Shift f = new Shift(fid,fq, ft, fp, fidman, dateWork);
					this.listShift.add(f);
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


//	public static void addShift(ArrayList<Shift> list, Shift st, ArrayList<Staff> listStaff) throws ParseException, SQLException {
//		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
//			try {
//				if (checkID(list, st.getShiftID()) == false) {
//					String cmd1 = "INSERT INTO Shift VALUES('" + st.getShiftID() + "','" + st.getAccountManager() + "','" + st.getWorkingTimeStart() + "','"
//							+ st.getWorkingTimeEnd() + "')";
//					Statement stm1 = DBConnection.connection.createStatement();
//					stm1.executeUpdate(cmd1);
//					DBConnection.connection.commit();
//					stm1.close();
//					
//					System.out.println("Successfully added new shift!");
//					System.out.println("Add list staff to the new shift: ");
//					for(int i = 0; i < listStaff.size(); i++) {
//						String cmd2 = "INSERT INTO ShiftDetail VALUES('" + st.getShiftID() + "','" + listStaff.get(i).getStaffID() + "')'"; 
//						Statement stm2 = DBConnection.connection.createStatement();
//						stm2.executeUpdate(cmd2);
//						DBConnection.connection.commit();
//						stm2.close();
//					}											
//				}
//				else
//					System.out.println("Try again! ");
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		} else
//			System.out.println("Something went wrong!!!");
//	}
	

//	public static void editShift(ArrayList<Shift> list, Shift st, ArrayList<Staff> listStaff) throws SQLException {
//		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
//			if(checkID(list, st.getShiftID())) {
//				String cmd1 = "UPDATE Shift SET ShiftID = '" + st.getShiftID() + "', AccountManagerID = '"
//						+ st.getAccountManager() + "', WorkingTimeStart = '" + st.getWorkingTimeStart() + "', WorkingTimeEnd ='"
//						+ st.getWorkingTimeEnd() +"' Where ID ='" + st.getShiftID() + "'";
//				Statement stm1 = DBConnection.connection.createStatement();
//				stm1.executeUpdate(cmd1);
//				DBConnection.connection.commit();
//				stm1.close();
//				
//				System.out.println("Edit list of staff");
//				for(int i = 0; i < listStaff.size(); i++) {
//					String cmd2 = "UPDATE ShiftDetail SET ShiftID = '" + st.getShiftID() + "','" + listStaff.get(i).getStaffID() + "')'"; 
//					Statement stm2 = DBConnection.connection.createStatement();
//					stm2.executeUpdate(cmd2);
//					DBConnection.connection.commit();
//					stm2.close();
//				}			
//			}
//			else
//				System.out.println("Something went wrong!!!");
//		}
//		else
//			System.out.println("Something went wrong!!!");
//		
//	}
//	
//	public static void removeShift(ArrayList<Shift> list, Shift st) {
//		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
//			try {
//				if (checkID(list, st.getShiftID())) {
//					String cmd1 = "DELETE FROM ShiftDetail WHERE ID = '" + st.getShiftID() + "'";
//					Statement stm1 = DBConnection.connection.createStatement();
//					stm1.executeUpdate(cmd1);
//					DBConnection.connection.commit();
//					stm1.close();
//					
//					String cmd2 = "DELETE FROM Shift WHERE ID = '" + st.getShiftID() + "'";
//					Statement stm2 = DBConnection.connection.createStatement();
//					stm2.executeUpdate(cmd2);
//					DBConnection.connection.commit();
//					stm2.close();
//					
//				} else
//					System.out.println("This shift does not exist! ");
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		} else
//			System.out.println("Something went wrong!!!");
//	}

}
