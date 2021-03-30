package Shift;

import java.util.ArrayList;

public class TimeSheet {
	private ArrayList<Shift> ListShift;

	public ArrayList<Shift> getListShift() {
		return ListShift;
	}

	public void setListShift(ArrayList<Shift> listShift) {
		ListShift = listShift;
	}

	public void printListTimeSheet() {
		System.out.println("List time sheet");
	}

}
