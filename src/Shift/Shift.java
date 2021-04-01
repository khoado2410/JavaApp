package Shift;

import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Staff_Manager.Staff;

public class Shift {
	private String ShiftID;
	private ArrayList<Staff> ListStaff;
	private Date WorkingTimeStart;
	private Date WorkingTimeEnd;

	public Shift(String shiftID, ArrayList<Staff> listStaff, Date workingTimeStart, Date workingTimeEnd) {
		super();
		ShiftID = shiftID;
		ListStaff = listStaff;
		WorkingTimeStart = workingTimeStart;
		WorkingTimeEnd = workingTimeEnd;
	}

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

	public static void addShiftCmd(ArrayList<Shift> list) throws ParseException, SQLException {
		String id, startingTime, endingTime;
		String cmd = "";

		String usernameDB = "han";
		String passwordDB = "666";
		String connectionUrl = "jdbc:sqlserver://LAPTOP-IQKFBUD7\\HAN181;databaseName=RestaurantManagement";
		try (Connection connection = DriverManager.getConnection(connectionUrl, usernameDB, passwordDB);) {
			PreparedStatement ps;

			Scanner sc = new Scanner(System.in);
			System.out.println("Enter shift ID: ");
			id = sc.nextLine();
			System.out.println("Working time starts from: ");
			startingTime = sc.nextLine();
			Date workingTimeStart = readDate(startingTime);
			endingTime = sc.nextLine();
			Date workingTimeEnd = readDate(endingTime);

			ArrayList<Staff> l = new ArrayList<>();
			System.out.println("Enter list of staffs: ");
			System.out.println("How many staffs?");
			int n = sc.nextInt();
			/*
			 * for ( i = 0; i < n; i++) { l.get(i).inputStaff(); }
			 */

			if (checkID(list, id) == false) {
				Shift st = new Shift(id, l, workingTimeStart, workingTimeEnd);
				for (int i = 0; i < l.size(); i++) {
					cmd = "INSERT INTO Shift VALUES('" + st.getShiftID() + "','" + st + "','"
							+ st.getListStaff().get(i) + "','" + st.getWorkingTimeStart() + "','"
							+ st.getWorkingTimeEnd() + "')";
					ps = connection.prepareStatement(cmd);
					ps.executeUpdate();
				}

			} else
				System.out.println("Try again! ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void removeShiftCmd(ArrayList<Shift> list) {
		String cmd = "";
		String usernameDB = "han";
		String passwordDB = "666";
		String connectionUrl = "jdbc:sqlserver://LAPTOP-IQKFBUD7\\HAN181;databaseName=RestaurantManagement";
		try (Connection connection = DriverManager.getConnection(connectionUrl, usernameDB, passwordDB);) {
			PreparedStatement ps;
			System.out.println("Enter ID of shift need to remove: ");
			Scanner sc = new Scanner(System.in);
			String id = sc.nextLine();
			if(checkID(list, id)) {
				cmd = "DELETE FROM Shift WHERE ID = '" + id + "'";
				ps = connection.prepareStatement(cmd);
	     		ps.executeUpdate();
	     		System.out.println("Deleted successfully! ");
			}
			else
				System.out.println("This shift does not exist! ");
		} catch (SQLException e) {
			e.printStackTrace();	            
		}
	}

}
