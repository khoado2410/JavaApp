package Shift;

import java.util.ArrayList;
import java.util.Scanner;

import DBConnection.DBConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Staff_Manager.Staff;

public class Shift {
	private String ShiftID;
	private ArrayList<Staff> ListStaff;
	private Date WorkingTimeStart;
	
	public String getAccountManager() {
		return accountManager;
	}

	public void setAccountManager(String accountManager) {
		this.accountManager = accountManager;
	}

	private Date WorkingTimeEnd;
	private String accountManager;

	public Date getWorkingTimeEnd() {
		return WorkingTimeEnd;
	}

	public void setWorkingTimeEnd(Date workingTimeEnd) {
		WorkingTimeEnd = workingTimeEnd;
	}

	public String getShiftID() {
		return ShiftID;
	}

	public void setShiftID(String shiftID) {
		ShiftID = shiftID;
	}

	public ArrayList<Staff> getListStaff() {
		return ListStaff;
	}

	public void setListStaff(ArrayList<Staff> listStaff) {
		ListStaff = listStaff;
	}

	public Date getWorkingTimeStart() {
		return WorkingTimeStart;
	}

	public void setWorkingTimeStart(Date workingTimeStart) {
		WorkingTimeStart = workingTimeStart;
	}

	public static Date readDate(String input) throws ParseException {
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		return (Date) sdf.parse(input);
	}

	public void inputShift() throws ParseException {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter shift ID: ");
		this.ShiftID = sc.nextLine();

		System.out.println("Enter list of staffs: ");
		System.out.println("How many staffs?");
		int n = sc.nextInt();
		/*
		 * for ( i = 0; i < n; i++) { this.ListStaff.get(i).inputStaff(); }
		 */

		System.out.println("Working time starts from: ");
		String startingTime = sc.nextLine();
		this.WorkingTimeStart = readDate(startingTime);
		String endingTime = sc.nextLine();
		this.WorkingTimeEnd = readDate(endingTime);
	}

	public void printShift() {
		System.out.println("List Shift ");

		System.out.println("Shift ID: " + this.getShiftID());

		System.out.println("List staff: ");
		/*
		 * for ( i = 0; i < this.ListStaff.size(); i++)
		 * this.ListStaff.get(i).printStaff();
		 */

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Working time starts from: " + formatter.format(this.getWorkingTimeStart()));
		System.out.println("Working time ends at: " + formatter.format(this.getWorkingTimeEnd()));
	}

	public static boolean checkID(ArrayList<Shift> list, String id) {
		if (list.size() > 0) {
			for (Shift st : list) {
				if (st.getShiftID().compareTo(id) == 0)
					return true;
			}
		}
		return false;
	}

	public static void addShift(ArrayList<Shift> list, Shift st, ArrayList<Staff> listStaff) throws ParseException, SQLException {
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			try {
				if (checkID(list, st.getShiftID()) == false) {
					String cmd1 = "INSERT INTO Shift VALUES('" + st.getShiftID() + "','" + st.getAccountManager() + "','" + st.getWorkingTimeStart() + "','"
							+ st.getWorkingTimeEnd() + "')";
					Statement stm1 = DBConnection.connection.createStatement();
					stm1.executeUpdate(cmd1);
					DBConnection.connection.commit();
					stm1.close();
					
					System.out.println("Successfully added new shift!");
					System.out.println("Add list staff to the new shift: ");
					for(int i = 0; i < listStaff.size(); i++) {
						String cmd2 = "INSERT INTO ShiftDetail VALUES('" + st.getShiftID() + "','" + listStaff.get(i).getStaffID() + "')'"; 
						Statement stm2 = DBConnection.connection.createStatement();
						stm2.executeUpdate(cmd2);
						DBConnection.connection.commit();
						stm2.close();
					}											
				}
				else
					System.out.println("Try again! ");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else
			System.out.println("Something went wrong!!!");
	}
	

	public static void editShift(ArrayList<Shift> list, Shift st, ArrayList<Staff> listStaff) throws SQLException {
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			if(checkID(list, st.getShiftID())) {
				String cmd1 = "UPDATE Shift SET ShiftID = '" + st.getShiftID() + "', AccountManagerID = '"
						+ st.getAccountManager() + "', WorkingTimeStart = '" + st.getWorkingTimeStart() + "', WorkingTimeEnd ='"
						+ st.getWorkingTimeEnd() +"' Where ID ='" + st.getShiftID() + "'";
				Statement stm1 = DBConnection.connection.createStatement();
				stm1.executeUpdate(cmd1);
				DBConnection.connection.commit();
				stm1.close();
				
				System.out.println("Edit list of staff");
				for(int i = 0; i < listStaff.size(); i++) {
					String cmd2 = "UPDATE ShiftDetail SET ShiftID = '" + st.getShiftID() + "','" + listStaff.get(i).getStaffID() + "')'"; 
					Statement stm2 = DBConnection.connection.createStatement();
					stm2.executeUpdate(cmd2);
					DBConnection.connection.commit();
					stm2.close();
				}			
			}
			else
				System.out.println("Something went wrong!!!");
		}
		else
			System.out.println("Something went wrong!!!");
		
	}
	
	public static void removeShift(ArrayList<Shift> list, Shift st) {
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			try {
				if (checkID(list, st.getShiftID())) {
					String cmd1 = "DELETE FROM ShiftDetail WHERE ID = '" + st.getShiftID() + "'";
					Statement stm1 = DBConnection.connection.createStatement();
					stm1.executeUpdate(cmd1);
					DBConnection.connection.commit();
					stm1.close();
					
					String cmd2 = "DELETE FROM Shift WHERE ID = '" + st.getShiftID() + "'";
					Statement stm2 = DBConnection.connection.createStatement();
					stm2.executeUpdate(cmd2);
					DBConnection.connection.commit();
					stm2.close();
					
				} else
					System.out.println("This shift does not exist! ");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else
			System.out.println("Something went wrong!!!");
	}

}
