package Food_Product;

import java.sql.Date;
import java.util.ArrayList;

public class OrderProduct {
	private String OrderID;
	private String ManagerID;
	private int TotalCost;
	private Date DateImport;
	private ArrayList<Product> listOrderProduct;
	public OrderProduct(String orderID, String managerID, int totalCost, Date dateImport, ArrayList<Product> listOrderProduct) {
		OrderID = orderID;
		ManagerID = managerID;
		TotalCost = totalCost;
		DateImport = dateImport;
		this.setListOrderProduct(listOrderProduct);
	}
	public String getOrderID() {
		return OrderID;
	}
	public void setOrderID(String orderID) {
		OrderID = orderID;
	}
	public String getManagerID() {
		return ManagerID;
	}
	public void setManagerID(String managerID) {
		ManagerID = managerID;
	}
	public int getTotalCost() {
		return TotalCost;
	}
	public void setTotalCost(int totalCost) {
		TotalCost = totalCost;
	}
	public Date getDateImport() {
		return DateImport;
	}
	public void setDateImport(Date dateImport) {
		DateImport = dateImport;
	}
	public ArrayList<Product> getListOrderProduct() {
		return listOrderProduct;
	}
	public void setListOrderProduct(ArrayList<Product> listOrderProduct) {
		this.listOrderProduct = listOrderProduct;
	}
}
