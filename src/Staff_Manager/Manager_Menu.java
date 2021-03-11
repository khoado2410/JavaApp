package Staff_Manager;

import java.util.ArrayList;
import Food_Product.Food;
import java.util.Scanner;

public class Manager_Menu extends Manager{
	private String type = "2";
	public void addNewFood() {
		ArrayList<Food> ds_monan;
		Scanner scan = new Scanner(System.in);
		System.out.println("Nhap thong tin mon an moi can them vao: ");
		ds_monan = new ArrayList<Food>(1);
		for(int i = 0; i < 1; i++) {
			Food a = new Food();
			a.NhapThongTinMonAn();
			ds_monan.add(a);
		}
		System.out.println("Add new food thanh cong");
		System.out.println("Thong tin mon an moi them vao: ");
		for(int i = 0; i < 1; i++) {
			ds_monan.get(i).XuatThongTinMonAn();
		}
		
	}
	
	public void editFood() {
		System.out.println("Edit food thanh cong");
	}
	
	public void deleteFood() {
		System.out.println("Delete food");
	}
	
	public void searchFood() {
		System.out.println("Search food thanh cong");
	}
}
