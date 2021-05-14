package Model.Food_Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Controller.DBConnection.DBConnection;

public class FoodType {
	private String FoodTypeID;
	private String FoodTypeName;

	public FoodType() {
		
	}
	
	public FoodType(String fid, String fn) {
		this.FoodTypeID = fid;
		this.FoodTypeName = fn;
	}

	public String getFoodTypeID() {
		return FoodTypeID;
	}

	public void setFoodTypeID(String foodTypeID) {
		FoodTypeID = foodTypeID;
	}

	public String getFoodTypeName() {
		return FoodTypeName;
	}

	public void setFoodTypeName(String foodTypeName) {
		FoodTypeName = foodTypeName;
	}

	public ArrayList<FoodType> getListFoodTypeFromDB() {
		ArrayList<FoodType> res = new ArrayList<>();
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			try {
				String sp_load = "{call sp_loadfoodtype}";
				Statement statement = DBConnection.connection.createStatement();
				ResultSet rs = statement.executeQuery(sp_load);
				while (rs.next()) {
					String fid = rs.getString("FoodTypeID");
					String fn = rs.getString("FoodTypeName");
					FoodType f = new FoodType(fid, fn);
					res.add(f);
				}
				statement.close();
			} catch (SQLException e) {
				System.out.println("Cannot load menu: " + e);
			}
		} else {
			System.out.println("Something went wrong!!!");
		}
		return res;
	}
}
