package Shift;

import java.util.ArrayList;
import java.sql.Date;
import java.text.SimpleDateFormat;

import Staff_Manager.Staff;

public class Shift {
	private String ShiftID;
	private Date TimeWork;
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

	public Date getTimeWork() {
		return TimeWork;
	}

	public void setTimeWork(Date timeWork) {
		TimeWork = timeWork;
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

	public void printListShift() {
		System.out.println("List Shift ");
		
		System.out.println("Shift ID: " + this.getShiftID());
		
		System.out.println("List staff: ");
		for(int i = 0; i < this.ListStaff.size(); i++)
			this.ListStaff.get(i).printStaff();
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Working time start from: " + formatter.format(this.getWorkingTimeStart()));
		System.out.println("Working time end at: " + formatter.format(this.getWorkingTimeEnd()));
		System.out.println("Duration: " + formatter.format(this.getTimeWork()));
		
	}
	
}
