package Food_Product;

import java.util.ArrayList;
import java.util.Scanner;


public class Product {
	private String nameProduct;
	private int price;
	private int amount;
	public String getNameProduct() {
		return nameProduct;
	}
	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public void NhapThongTinProduct() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Nhap ten san pham: ");
		this.nameProduct = scan.nextLine();
		System.out.println("Nhap price: ");
		this.price = scan.nextInt();
		System.out.println("Nhap khoi luong: ");
		this.amount = scan.nextInt();
	}
	
	public void XuatThongTinProduct() {
		System.out.println("Ten san pham: " + this.nameProduct);
		System.out.println("Gia tien: " + this.price);
		System.out.println("Khoi luong: " + this.amount);
	}
	
}
