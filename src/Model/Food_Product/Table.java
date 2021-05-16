package Model.Food_Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Controller.DBConnection.DBConnection;

public class Table {
	private String idTable;
	private int numClients;
	private int status;
	private String idBill;
	public Table() {}
	public Table(String id, int num, int status, String billID) {
		this.idTable = id;
		this.numClients = num;
		this.status = status;
		this.idBill = billID;
	}
	
	public String getIdTable() {
		return idTable;
	}
	public void setIdTable(String idTable) {
		this.idTable = idTable;
	}
	public int getNumClients() {
		return numClients;
	}
	public void setNumClients(int numClients) {
		this.numClients = numClients;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getIdBill() {
		return idBill;
	}
	public void setIdBill(String idBill) {
		this.idBill = idBill;
	}
	public ArrayList<Table> loadTableFromDB() {
		ArrayList<Table> res = new ArrayList<>();
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			try {
				String sp_load = "{call sp_loadtable}";
				Statement statement = DBConnection.connection.createStatement();
				ResultSet rs = statement.executeQuery(sp_load);
				while (rs.next()) {
					String tid = rs.getString("TableID");
					int ts = rs.getInt("TableStatus");
					int numCli = rs.getInt("numClients");
					String bid = rs.getString("BillID");
					res.add(new Table(tid, ts, numCli, bid));
				}
				statement.close();
				return res;
			} catch (SQLException e) {
				System.out.println("Cannot load menu: " + e);
				return res;
			}
		} else {
			System.out.println("Something went wrong!!!");
			return res;
		}
	}
}
