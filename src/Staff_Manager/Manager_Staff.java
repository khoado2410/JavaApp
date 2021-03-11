package Staff_Manager;

import java.util.ArrayList;
import java.util.Scanner;


public class Manager_Staff extends Manager{
	private String type = "1";
	
	public void addStaff() {
		ArrayList<Staff> ds_nhanvien;
		Scanner scan = new Scanner(System.in);
		System.out.println("Nhap so nhan vien can them vao: ");
		int n = scan.nextInt();
		ds_nhanvien = new ArrayList<Staff>(n);
		for(int i = 0; i < n; i++) {
			Staff a = new Staff();
			a.NhapThongTinNhanVien();
			ds_nhanvien.add(a);
		}
		
		System.out.println("Danh sach nhan vien: ");
		for(int i = 0; i < n; i++) {
			ds_nhanvien.get(i).XuatThongTin();
		}
		
		System.out.println("Add new staff thanh cong!!!");
		
	}
	
	public void editStaff() {
		System.out.println("edit staff thanh cong");
	}
	
	public void deleteStaff() {
		System.out.println("Delete staff thanh cong");
	}
	
	public void editTimeSheet() {
		System.out.println("Edit time sheet thanh cong ");
	}
	
	public void sortStaffPoint() {
		System.out.println("Sort success!");
	}
}
