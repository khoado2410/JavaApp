package DBConnection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	public static final String DB_URL = "jdbc:sqlserver://localhost;databaseName=RestaurantManagement;integratedSecurity=true";
	static final String USER = "";
	static final String PASS = "";
	public static Connection connection;
	//connect DB
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
			connection = DriverManager.getConnection(db_url);
			System.out.println("Connected to Database");
			return true;
		} catch (SQLException e) {
			System.out.println("Connect Failed: " + e);
			return false;
		}
	}
}