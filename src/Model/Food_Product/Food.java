package Model.Food_Product;

import java.util.ArrayList;

public class Food {
	private String FoodID;
	private String nameFood;
	private String foodType;
	private int QuantityOfStock;
	private int price;
	private String ImageFood;
	private ArrayList<Product> ingredient;

	public Food(String id, String name, int price, String type, int quantity, String img) {
		this.FoodID = id;
		this.nameFood = name;
		this.price = price;
		this.foodType = type;
		this.QuantityOfStock = quantity;
		this.setImageFood(img);
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

	public String getImageFood() {
		return ImageFood;
	}

	public void setImageFood(String imageFood) {
		ImageFood = imageFood;
	}
}
