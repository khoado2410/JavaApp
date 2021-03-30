package Shift;

import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Date;
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
	
	public Date readDate(String input) throws ParseException {
		Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        return (Date) sdf.parse(input);
	}
	
	public void inputListShift() throws ParseException {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter shift ID: ");
		this.ShiftID = sc.nextLine();
		
		System.out.println("Enter list of staffs: ");
		System.out.println("How many staffs?");
		int n = sc.nextInt();
		for(int i = 0; i < n; i++) {
			this.ListStaff.get(i).inputStaff();
		}
		
		System.out.println("Working time starts from: ");
		String startingTime = sc.nextLine();
		this.WorkingTimeStart = readDate(startingTime);
		String endingTime = sc.nextLine();
		this.WorkingTimeEnd = readDate(endingTime);
		
	}
	public void printListShift() {
		System.out.println("List Shift ");
		
		System.out.println("Shift ID: " + this.getShiftID());
		
		System.out.println("List staff: ");
		for(int i = 0; i < this.ListStaff.size(); i++)
			this.ListStaff.get(i).printStaff();
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Working time starts from: " + formatter.format(this.getWorkingTimeStart()));
		System.out.println("Working time ends at: " + formatter.format(this.getWorkingTimeEnd()));		
	}
	
}
