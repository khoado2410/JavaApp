package Model.Food_Product;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Controller.DBConnection.DBConnection;

public class Menu {

	private ArrayList<Food> menu;

	public Menu() {
		this.menu = new ArrayList<>();
	}

	public ArrayList<Food> getMenu() {
		return menu;
	}

	public void setMenu(ArrayList<Food> m) {
		menu = m;
	}

	public void showMenu() {
		System.out.println("Show menu");
	}

	public boolean addNewFood(Food a) {
		String sql = "INSERT INTO Menu(FoodId, Name, Price, FoodtypeID, QuantityOfStock)" + "VALUES(?,?,?,?,?)";
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			try {

				PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
				ps.setString(1, a.getFoodID());
				ps.setString(2, a.getNameFood());
				ps.setInt(3, a.getPrice());
				ps.setString(4, a.getFoodType());
				ps.setInt(5, a.getQuantityOfStock());
				ps.executeUpdate();

				return true;
			} catch (SQLException e) {
				System.out.println("Cannot insert food to menu: " + e);
				return false;
			}
		} else {
			System.out.println("Something went wrong!!!");
			return false;
		}

	}

	public boolean editFoodFromDB(Food s, int price) {
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			try {
				String updateString = "Update Menu set Price = '" + price + "' where FoodID = '" + s.getFoodID() + "'";
				Statement statement = DBConnection.connection.createStatement();
				statement.executeUpdate(updateString);
				DBConnection.connection.commit();
				statement.close();
				return true;
			} catch (SQLException e) {
				System.out.println("Cannot update food: " + e);
				return false;
			}
		} else {
			System.out.println("Something went wrong!!!");
			return false;
		}
	}

	static boolean deleteFoodFromMenu(Food s) {
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			try {
				String deleteString = "Delete from Menu where FoodID = '" + s.getFoodID() + "'";
				Statement statement = DBConnection.connection.createStatement();
				statement.executeUpdate(deleteString);
				DBConnection.connection.commit();
				statement.close();
				return true;
			} catch (SQLException e) {
				System.out.println("Cannot delete food: " + e);
				return false;
			}
		} else {
			System.out.println("Something went wrong!!!");
			return false;
		}
	}

	public boolean SearchFoodfromMenu(String s) {
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			try {
				String searchString = "Select * from Menu where Name = '" + s + "'";
				Statement statement = DBConnection.connection.createStatement();
				ResultSet rs = statement.executeQuery(searchString);
				System.out.println(rs);
				statement.close();
				return true;
			} catch (SQLException e) {
				System.out.println("Cannot search food: " + e);
				return false;
			}
		} else {
			System.out.println("Something went wrong!!!");
			return false;
		}
	}

	public boolean loadFoodFromDB() {
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			try {
				String sp_load = "{call sp_loadmenu}";
				Statement statement = DBConnection.connection.createStatement();
				ResultSet rs = statement.executeQuery(sp_load);
				while (rs.next()) {
					String fid = rs.getString("FoodID");
					String fn = rs.getString("Name");
					int fp = rs.getInt("Price");
					String ft = rs.getString("FoodTypeID");
					int fq = rs.getInt("QuantityOfStock");
					String fi = rs.getString("ImageFood");
					Food f = new Food(fid, fn, fp, ft, fq, fi);
					this.menu.add(f);
				}
				statement.close();
				return true;
			} catch (SQLException e) {
				System.out.println("Cannot load menu: " + e);
				return false;
			}
		} else {
			System.out.println("Something went wrong!!!");
			return false;
		}
	}

	public boolean loadFoodByTypeFood(String type) {
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			try {
				String sp_load = "{call sp_loadmenubytype(?)}";
				CallableStatement statement = DBConnection.connection.prepareCall(sp_load);
				statement.setString(1, type);
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					String fid = rs.getString("FoodID");
					String fn = rs.getString("Name");
					int fp = rs.getInt("Price");
					String ft = rs.getString("FoodTypeID");
					int fq = rs.getInt("QuantityOfStock");
					String fi = rs.getString("ImageFood");
					Food f = new Food(fid, fn, fp, ft, fq, fi);
					this.menu.add(f);
				}
				statement.close();
				return true;
			} catch (SQLException e) {
				System.out.println("Cannot load type food!!!: " + e);
				return false;
			}
		} else {
			System.out.println("Something went wrong!!!");
			return false;
		}
	}

	public ArrayList<Food> getListFoodByType(String type) {
		ArrayList<Food> res = new ArrayList<>();
		for (Food f : menu) {
			if (f.getFoodType().equals(type)) {
				res.add(f);
			}
		}
		return res;
	}

	public static void main(String[] args) {
		Menu m = new Menu();
		if (m.loadFoodByTypeFood("Appetizers")) {
			ArrayList<Food> a = m.getMenu();
			for (Food f : a) {
				System.out.println(f.getNameFood());
			}
		}
	}
}
