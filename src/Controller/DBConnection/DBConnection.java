package Controller.DBConnection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	//public static final String DB_URL = "jdbc:sqlserver://localhost:49702;databaseName=RestaurantManagement;integratedSecurity=true";
//	public static final String DB_URL = "jdbc:sqlserver://LAPTOP-IQKFBUD7\\HAN181;databaseName=RestaurantManagement";
	public static final String DB_URL = "jdbc:sqlserver://localhost:53155;databaseName=RestaurantManagement";
	static final String USER = "sa";
	static final String PASS = "123456";
	public static Connection connection;
//	connect DB
	public static boolean loadDriver() {
		try {
			Driver myDriver = new com.microsoft.sqlserver.jdbc.SQLServerDriver();
			DriverManager.registerDriver(myDriver);
			System.out.println("Loaded Driver");
			return true;
		} catch (SQLException e) {
			System.out.println("Error: Unable to load driver class:" + e);
			return false;
		}
	}
	public static boolean connectDatabase(String db_url) {
		try {
			connection = DriverManager.getConnection(db_url, USER, PASS);
			System.out.println("Connected to Database");
			return true;
		} catch (SQLException e) {
			System.out.println("Connect Failed: " + e);
			return false;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(DBConnection.connectDatabase(DB_URL));
	}
}