package Food_Product;

import java.util.ArrayList;

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
}
