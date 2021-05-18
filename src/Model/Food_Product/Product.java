package Model.Food_Product;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Controller.DBConnection.DBConnection;

public class Product {
	
	private String productID;
	private String nameProduct;
	private int price;
	private int mass;
	private ArrayList<Product> listProduct;
	
	public Product() {
		this.listProduct = new ArrayList<Product>();
	}
	
	public Product(String id) {
		this.productID = id;
	}
	
	public Product(String id, String name, int mass) {
		this.productID = id;
		this.nameProduct = name;
		this.mass = mass;
	}
	
	public Product(String id, String name, int price, int mass) {
		this.productID = id;
		this.nameProduct = name;
		this.price = price;
		this.mass = mass;
	}
	
	public Product(String id, int mass) {
		this.productID = id;
		this.mass = mass;
	}
	
	public int getMass() {
		return mass;
	}



	public void setMass(int mass) {
		this.mass = mass;
	}



	public ArrayList<Product> getListProduct() {
		return listProduct;
	}



	public void setListProduct(ArrayList<Product> listProduct) {
		this.listProduct = listProduct;
	}



	public String getNameProduct() {
		return nameProduct;
	}
	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String s) {
		this.productID = s;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	//public boolean 
	public boolean getIngredientForFood(String idFood) {
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			try {
				String sp_load = "{call getIngredientOfFood(?)}";
				PreparedStatement pstatement = DBConnection.connection.prepareStatement(sp_load);
				 pstatement.setString(1, idFood);
				 ResultSet rs = pstatement.executeQuery();
				while (rs.next()) {
					String fid = rs.getString("ProductID");
					String pname = rs.getString("ProductName");
					int mass = rs.getInt("Mass");
					Product f = new Product(fid, pname, mass);
					this.listProduct.add(f);
				}
				pstatement.close();
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
	
	public boolean loadProductFromDB() {
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			try {
				String sp_load = "SELECT * from Product";
				Statement statement = DBConnection.connection.createStatement();
				ResultSet rs = statement.executeQuery(sp_load);
				while (rs.next()) {
					String fid = rs.getString("ProductID").trim();
					String fn = rs.getString("ProductName").trim();
					int fp = rs.getInt("Mass");
					int ft = rs.getInt("Price");
					
					Product f = new Product(fid, fn, ft, fp);
					this.listProduct.add(f);
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
	
	public void addIngredient(String foodName, String productName, int mass) {
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			try {
				String query = "{call addIngredient(?, ?, ?)}";
				CallableStatement cstmt = DBConnection.connection.prepareCall(query);

				cstmt.setString(1, foodName);
				cstmt.setString(2, productName);
				cstmt.setInt(3, mass);

				cstmt.execute();
				cstmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("Failed");
		}
	}
	
	public String getIDProductMax() {
		String id = "";
		
		String cmd1 = "SELECT ProductID FROM Product WHERE ProductID = (SELECT max(ProductID) FROM Product)";
		
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			try {
				PreparedStatement ps = DBConnection.connection.prepareStatement(cmd1);
				ResultSet results = ps.executeQuery();
				while(results.next()) {
	     	   		id = results.getString("ProductID");
	     	   		DBConnection.connection.commit();
	     	   	}
				results.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		return id;
	}
	
	public boolean deleteIngredient(String idFood, String productID) {
		
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			try {
				
				String deleteString = "{call removeIngredient(?, ?)}";
				
				CallableStatement cstmt = DBConnection.connection.prepareCall(deleteString);
				cstmt.setString(1, idFood);
				cstmt.setString(2, productID);
				cstmt.executeUpdate();

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
	
	public boolean addNewProduct(Product a) {
		String querySql = "{call addProduct(?, ?, ?, ?)}";
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)){
			try {
				CallableStatement cstmt = DBConnection.connection.prepareCall(querySql);				
				cstmt.setString(1, a.getProductID());
				cstmt.setString(2, a.getNameProduct());
				cstmt.setInt(3, a.getMass());
				cstmt.setInt(4, a.getPrice());
				cstmt.executeUpdate();

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
	
	public boolean updateProduct(Product a) {
		String querySql = "{call editProduct(?, ?, ?, ?)}";
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)){
			try {
				CallableStatement cstmt = DBConnection.connection.prepareCall(querySql);
				
				cstmt.setString(1, a.getProductID());
				cstmt.setString(2, a.getNameProduct());
				cstmt.setInt(3, a.getMass());
				cstmt.setInt(4, a.getPrice());
	
				cstmt.executeUpdate();

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
	
	public  boolean deleteProduct(Product s) {
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			try {
				
				String deleteString = "{call removeProduct(?)}";
				
				CallableStatement cstmt = DBConnection.connection.prepareCall(deleteString);
				cstmt.setString(1, s.getProductID());
				cstmt.executeUpdate();

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
	
	public boolean updateMassInDB(Food a, boolean flag) {
		a.loadListIngredient();
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			try {		
				String sp_upd = "{call sp_updateMassProduct(?, ?)}";
				CallableStatement cstmt = DBConnection.connection.prepareCall(sp_upd);
				if (flag) {
					for (Product p : a.getIngredient()) {
						cstmt.setString(1, p.getProductID());
						cstmt.setInt(2, 1);
						cstmt.executeUpdate();
					}
				}
				else {
					for (Product p : a.getIngredient()) {
						cstmt.setString(1, p.getProductID());
						cstmt.setInt(2, 0);
						cstmt.executeUpdate();
					}
				}
				cstmt.close();
				return true;
			} catch (SQLException e) {
				System.out.println("Cannot update product mass: " + e);
				return false;
			}
		} else {
			System.out.println("Something went wrong!!!");
			return false;
		}
	}
}
