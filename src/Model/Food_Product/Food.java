package Model.Food_Product;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Controller.DBConnection.DBConnection;

public class Food {
	private String FoodID;
	private String nameFood;
	private String foodType;
	private String foodTypeName;
	private int QuantityOfStock;
	private int price;
	private String ImageFood;
	private ArrayList<Product> ingredient;
	
	public String getFoodTypeName() {
		return foodTypeName;
	}

	public void setFoodTypeName(String foodTypeName) {
		this.foodTypeName = foodTypeName;
	}

	public Food(String id, String name, int price, String type, int quantity, String img, String foodTypeName){
		this.FoodID = id;
		this.nameFood = name;
		this.price = price;
		this.foodType = type;
		this.QuantityOfStock = quantity;
		this.setImageFood(img);
		this.foodTypeName = foodTypeName;
	}
	
	public Food(String id, String name, int price, int quantity) {
		this.FoodID = id;
		this.nameFood = name;
		this.price = price;
		this.QuantityOfStock = quantity;
	}
	
	public Food(String id) {
		this.FoodID = id;
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
	
	public boolean loadListIngredient() {
		this.ingredient = new ArrayList<>();
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			try {
				String sp_load = "{call sp_loadlistingredient(?)}";
				CallableStatement s = DBConnection.connection.prepareCall(sp_load);
				s.setString(1, this.FoodID);
				ResultSet rs = s.executeQuery();
				while (rs.next()) {
					String pid = rs.getString("ProductID");
					int mass = rs.getInt("Mass");
					this.ingredient.add(new Product(pid, mass));
				}
				s.close();
				return true;
			} catch (SQLException e) {
				System.out.println("Cannot update bill:: " + e);
				return true;
			}
		} else {
			System.out.println("Something went wrong!!!");
			return false;
		}
	}
	public boolean checkIngredientMass() {
		this.loadListIngredient();
		Product listProduct = new Product();
		listProduct.loadProductFromDB();
		for (Product p: this.ingredient) {
			for (Product q: listProduct.getListProduct()) {
				if (p.getProductID().trim().equals(q.getProductID().trim()) && p.getMass() > q.getMass()) {
					return false;
				}
			}
		}
		return true;
	}
}
