package Food_Product;

import java.util.ArrayList;
import java.util.Scanner;

public class Food {
	private String FoodID;
	private String nameFood;
	private String foodType;
	private int QuantityOfStock;
	private int price;
	private ArrayList<Product> ingredient;
	
	public Food(String id, String name, int price, String type, int quantity) {
		this.FoodID = id;
		this.nameFood = name;
		this.price = price;
		this.foodType = type;
		this.QuantityOfStock = quantity;
	}
	
	public int getQuantityOfStock() {
		return QuantityOfStock;
	}
	public void setQuantityOfStock(int quantityOfStock) {
		QuantityOfStock = quantityOfStock;
	}
	public String getFoodID() {
		return FoodID;
	}
	public void setFoodID(String foodID) {
		FoodID = foodID;
	}
	public String getNameFood() {
		return nameFood;
	}
	public void setNameFood(String nameFood) {
		this.nameFood = nameFood;
	}
	public String getFoodType() {
		return foodType;
	}
	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public ArrayList<Product> getIngredient() {
		return ingredient;
	}
	public void setIngredient(ArrayList<Product> ingredient) {
		this.ingredient = ingredient;
	}
	
	public void NhapThongTinMonAn() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Nhap ten mon an: ");
		this.nameFood = scan.nextLine();
		System.out.println("Nhap loai mon an: ");
		this.foodType = scan.nextLine();
		System.out.println("Nhap gia tien: ");
		this.price = scan.nextInt();
		System.out.println("Nhap so thanh phan: ");
		int n = scan.nextInt();
		this.ingredient = new ArrayList<Product>(n);
		for(int i = 0; i < n; i++) {
			Product a = new Product();
			a.NhapThongTinProduct();
			this.ingredient.add(a);
		}
		
	}
	
	public void XuatThongTinMonAn() {
		System.out.println("Ten mon an: " + this.nameFood);
		System.out.println("Loai mon an: " + this.foodType);
		System.out.println("Gia tien: " + this.price);
		System.out.println("Thanh phan cua mon an: ");
		for(int i = 0; i < this.ingredient.size(); i++) {
			this.ingredient.get(i).XuatThongTinProduct();
		}
	}
	
	
	
}
