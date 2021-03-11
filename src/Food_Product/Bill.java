package Food_Product;

import Staff_Manager.Staff;
import java.util.ArrayList;
import java.util.Scanner;

public class Bill {
	private String idBill;
	private Staff server;
	private ArrayList<Food> listFood;
	private Table statusTable;
	private boolean statusBill;
	public String getIdBill() {
		return idBill;
	}
	public void setIdBill(String idBill) {
		this.idBill = idBill;
	}
	public Staff getServer() {
		return server;
	}
	public void setServer(Staff server) {
		this.server = server;
	}
	public ArrayList<Food> getListFood() {
		return listFood;
	}
	public void setListFood(ArrayList<Food> listFood) {
		this.listFood = listFood;
	}
	public Table getStatusTable() {
		return statusTable;
	}
	public void setStatusTable(Table statusTable) {
		this.statusTable = statusTable;
	}
	public boolean isStatusBill() {
		return statusBill;
	}
	public void setStatusBill(boolean statusBill) {
		this.statusBill = statusBill;
	}
	
	public void sumBill() {
		System.out.println("Sum bill: 1.000.000");
	}
	
}
