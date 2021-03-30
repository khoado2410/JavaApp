package Staff_Manager;

import java.util.ArrayList;
import java.util.Scanner;


public class Manager_Staff extends Manager{
	private String type = "1";
	
	public void addStaff() {
		ArrayList<Staff> ds_nhanvien;
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of stalves: ");
		int n = scan.nextInt();
		ds_nhanvien = new ArrayList<Staff>(n);
		for(int i = 0; i < n; i++) {
			Staff a = new Staff();
			a.inputStaff();
			ds_nhanvien.add(a);
		}
		
		System.out.println("List of staff: ");
		for(int i = 0; i < n; i++) {
			ds_nhanvien.get(i).printStaff();
		}
		
		System.out.println("Addded new staff successfully!"); 
		
	}
	
	public void editStaff() {
		System.out.println("Edited staff successfully!");
	}
	
	public void deleteStaff() {
		System.out.println("Deleted staff successfully!");
	}
	
	public void editTimeSheet() {
		System.out.println("Edited timesheet successfully! ");
	}
	
	public void sortStaffPoint() {
		System.out.println("Sort staff by point successfully!");
	}
}
