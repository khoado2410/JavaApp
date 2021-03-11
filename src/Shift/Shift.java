package Shift;

import java.util.ArrayList;
import java.sql.Date;
import Staff_Manager.Staff;

public class Shift {
	private Date TimeWork;
	private ArrayList<Staff> ListStaff;
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
	
	public void printListShift() {
		System.out.println("List Shift ");
	}
}
