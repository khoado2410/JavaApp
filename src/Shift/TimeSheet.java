package Shift;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class TimeSheet {
	private ArrayList<Shift> ListShift;

	public ArrayList<Shift> getListShift() {
		return ListShift;
	}

	public void setListShift(ArrayList<Shift> listShift) {
		ListShift = listShift;
	}

	public void inputTimeSheet() throws ParseException {
		System.out.println("Enter the number of shifts: ");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for(int i = 0; i < n; i++)
			this.ListShift.get(i).inputShift();		
	}
	
	public void printTimeSheet() {
		System.out.println("List time sheet");
		for(int i = 0; i < this.ListShift.size(); i++)
			this.ListShift.get(i).printShift();
	}	
	
}
