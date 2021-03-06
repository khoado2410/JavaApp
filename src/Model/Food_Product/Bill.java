package Model.Food_Product;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import Controller.DBConnection.DBConnection;

public class Bill {
	private String BillID;
	private String StaffName;
	private int payment;
	private int status;
	private String TableID;
	private String CheckInHour;
	private String CheckOutHour;
	private String accManagerID;

	ArrayList<Food> listFoodforDetailBill;

	public ArrayList<Food> getListFoodforDetailBill() {
		return listFoodforDetailBill;
	}

	public void setListFoodforDetailBill(ArrayList<Food> listFoodforDetailBill) {
		this.listFoodforDetailBill = listFoodforDetailBill;
	}

	HashMap<Food, Integer> listFood = new HashMap<>();

	ArrayList<Bill> listBill = new ArrayList<Bill>();

	public ArrayList<Bill> getListBill() {
		return listBill;
	}

	public void setListBill(ArrayList<Bill> listBill) {
		this.listBill = listBill;
	}

	public HashMap<Food, Integer> getListFood() {
		return listFood;
	}

	public void setListFood(HashMap<Food, Integer> listFood) {
		this.listFood = listFood;
	}

	public Bill() {
		this.listBill = new ArrayList<Bill>();
	}

	public Bill(String billid) {
		this.BillID = billid;
		HashMap<String, Integer> temp = loadListFoodInBill(billid);
		Menu m = new Menu();
		m.loadFoodFromDB();
		for (String t : temp.keySet()) {
			for (Food f : m.getMenu()) {
				if (f.getNameFood().equals(t)) {
					listFood.put(f, temp.get(t));
				}
			}
		}
	}

	public Bill(String billID, String staffName, int payment, int status, String tableID, String checkInHour,
			String checkOutHour, String accManagerID) {
		super();
		BillID = billID;
		StaffName = staffName;
		this.payment = payment;
		this.status = status;
		TableID = tableID;
		CheckInHour = checkInHour;
		CheckOutHour = checkOutHour;
		this.accManagerID = accManagerID;
	}

	public String getBillID() {
		return BillID;
	}

	public void setBillID(String billID) {
		BillID = billID;
	}

	public String getStaffName() {
		return StaffName;
	}

	public void setStaffName(String staffName) {
		StaffName = staffName;
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTableID() {
		return TableID;
	}

	public void setTableID(String tableID) {
		TableID = tableID;
	}

	public String getCheckInHour() {
		return CheckInHour;
	}

	public void setCheckInHour(String checkInHour) {
		CheckInHour = checkInHour;
	}

	public String getCheckOutHour() {
		return CheckOutHour;
	}

	public void setCheckOutHour(String checkOutHour) {
		CheckOutHour = checkOutHour;
	}

	public String getAccManagerID() {
		return accManagerID;
	}

	public void setAccManagerID(String accManagerID) {
		this.accManagerID = accManagerID;
	}

	public int sumBill() {
		int sum = 0;
		for (Food f : this.listFood.keySet()) {
			sum += f.getPrice() * this.listFood.get(f);
		}
		return sum;
	}

	public boolean addFood(Food a, int q) {
		if(a.checkIngredientMass())
			if (this.listFood.containsKey(a)) {
				this.listFood.put(a, this.listFood.get(a) + q);
				return true;
			} else {
				this.listFood.put(a, q);
				return true;
			}
		else {
			return false;
		}
	}

	public void updateProduct(Food a, boolean flag) {
		Product p = new Product();
		p.updateMassInDB(a, flag);
	}
	public void updateProductByDelete(Food a, int t) {
		for (int i = 0; i < t; i++)
			updateProduct(a, false);
	}
	
	public void removeFood(Food a) {
		if (this.listFood.containsKey(a))
			this.listFood.remove(a);
	}

	public int getQuantityFoodInBill(Food a) {
		if (this.listFood.containsKey(a))
			return this.listFood.get(a);
		else {
			return -1;
		}
	}

	public boolean loadAllBill() {
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			try {
				String sp_load = "{call loadAllBill}";
				Statement statement = DBConnection.connection.createStatement();
				ResultSet rs = statement.executeQuery(sp_load);
				while (rs.next()) {
					String fid = rs.getString("BillID");
					String fn = rs.getString("StaffName");
					int fp = rs.getInt("Payment");
					int ft = rs.getInt("BillStatus");
					String fq = rs.getString("TableID");
					String fi = rs.getString("CheckInHour");
					String ffn = rs.getString("CheckOutHour");
					String facc = rs.getString("AccountManagerID");
					Bill f = new Bill(fid, fn, fp, ft, fq, fi, ffn, facc);
					this.listBill.add(f);
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

	public boolean getFoodForDetail(String id) {
		this.listFoodforDetailBill = new ArrayList<Food>();
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			try {
				String sp_load = "{call sp_loadfoodforBillDetail(?)}";
				CallableStatement statement = DBConnection.connection.prepareCall(sp_load);
				statement.setString(1, id);

				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					String fid = rs.getString("FoodID");
					String name = rs.getString("Name");
					int price = rs.getInt("Price");
					int quantity = rs.getInt("Quantity");
					Food f = new Food(fid, name, price, quantity);
					this.listFoodforDetailBill.add(f);
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

	public boolean getInfoBill(String id) {
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			try {
				String sp_load = "{call getInfoBill(?)}";
				CallableStatement statement = DBConnection.connection.prepareCall(sp_load);
				statement.setString(1, id);
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					String fid = rs.getString("BillID");
					String fn = rs.getString("StaffName");
					int fp = rs.getInt("Payment");
					int ft = rs.getInt("BillStatus");
					String fq = rs.getString("TableID");
					String fi = rs.getString("CheckInHour");
					String ffn = rs.getString("CheckOutHour");
					String facc = rs.getString("AccountManagerID");
					Bill f = new Bill(fid, fn, fp, ft, fq, fi, ffn, facc);
					this.listBill.add(f);
					return true;
				}
				statement.close();
			} catch (SQLException e) {
				System.out.println("Cannot load menu: " + e);
				return false;
			}
		} else {
			System.out.println("Something went wrong!!!");
			return false;
		}
		return false;

	}

	public HashMap<String, Integer> loadListFoodInBill(String billID) {
		HashMap<String, Integer> res = new HashMap<>();
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			try {
				String sp_load = "{call sp_loadfoodinbill(?)}";
				CallableStatement statement = DBConnection.connection.prepareCall(sp_load);
				statement.setString(1, billID);
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					String sn = rs.getString("Name");
					int q = rs.getInt("Quantity");
					res.put(sn, q);
				}
				statement.close();
				return res;
			} catch (SQLException e) {
				System.out.println("Cannot load type food!!!: " + e);
				return res;
			}
		} else {
			System.out.println("Something went wrong!!!");
			return res;
		}
	}

	public boolean createBillInDB() {
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			try {
				String sp_update = "{call sp_createbill(?,?,?)}";
				CallableStatement statement = DBConnection.connection.prepareCall(sp_update);
				statement.setInt(1, sumBill());
				statement.setInt(2, 0);
				statement.setString(3, TableID);
				statement.executeUpdate();
				statement.close();
				String getBill = "{call sp_getBillIdFromTable(?)}";
				statement = DBConnection.connection.prepareCall(getBill);
				statement.setString(1, TableID);
				ResultSet rs =  statement.executeQuery();
				String billid = "";
				while (rs.next()) {
					billid = rs.getString("BillID");
				}
				statement.close();
				sp_update = "{call sp_insertBillDetail(?,?,?)}";
				statement = DBConnection.connection.prepareCall(sp_update);
				for (Food f : listFood.keySet()) {
					statement.setString(1, billid);
					statement.setString(2, f.getFoodID());
					statement.setInt(3, listFood.get(f));
					statement.executeUpdate();
				}
				statement.close();
				return true;
			} catch (SQLException e) {
				System.out.println("Cannot create bill:: " + e);
				return true;
			}
		} else {
			System.out.println("Something went wrong!!!");
			return false;
		}
	}

	public boolean updateBillDetail() {
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			try {
				String sp_del = "{call sp_delBillDetail(?)}";
				CallableStatement s = DBConnection.connection.prepareCall(sp_del);
				s.setString(1, this.BillID);
				s.executeUpdate();
				s.close();
				String sp_update = "{call sp_insertBillDetail(?,?,?)}";
				CallableStatement statement = DBConnection.connection.prepareCall(sp_update);
				for (Food f : listFood.keySet()) {
					statement.setString(1, this.BillID);
					statement.setString(2, f.getFoodID());
					statement.setInt(3, listFood.get(f));
					statement.executeUpdate();
				}
				statement.close();
				sp_update = "{call sp_updatebillpayment(?,?)}";
				statement = DBConnection.connection.prepareCall(sp_update);
				statement.setString(1, this.BillID);
				statement.setInt(2, sumBill());
				statement.executeUpdate();
				statement.close();
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
	public boolean saveBillInDB(String staffname, int point) {
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			try {
				String sp_update = "{call sp_updatebill(?,?,?,?,?)}";
				CallableStatement statement = DBConnection.connection.prepareCall(sp_update);
				statement.setString(1, this.getBillID());
				statement.setString(2, staffname);
				statement.setInt(3, 1);
				statement.setString(4, TableID);
				statement.setInt(5, point);
				statement.executeUpdate();
				return true;
			} catch (SQLException e) {
				System.out.println("Cannot save bill: " + e);
				return true;
			}
		} else {
			System.out.println("Something went wrong!!!");
			return false;
		}
	}
}
