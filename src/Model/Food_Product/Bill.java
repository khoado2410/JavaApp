package Model.Food_Product;

import java.util.HashMap;

public class Bill {
	private String BillID;
	private String StaffName;
	private int payment;
	private int status;
	private String TableID;
	private String CheckInHour;
	private String CheckOutHour;
	private String accManagerID;
	HashMap<Food, Integer> listFood = new HashMap<>();

	public HashMap<Food, Integer> getListFood() {
		return listFood;
	}

	public void setListFood(HashMap<Food, Integer> listFood) {
		this.listFood = listFood;
	}

	public Bill() {

	}

	public Bill(String billID, String staffName, int payment, int status, String tableID, String checkInHour,
			String checkOutHour, String accManagerID, HashMap<Food, Integer> lf) {
		super();
		BillID = billID;
		StaffName = staffName;
		this.payment = payment;
		this.status = status;
		TableID = tableID;
		CheckInHour = checkInHour;
		CheckOutHour = checkOutHour;
		this.accManagerID = accManagerID;
		listFood = lf;
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

	public void sumBill() {
		System.out.println("Sum bill: 1.000.000");
	}

	public void addFood(Food a, int q) {
		if (this.listFood.containsKey(a))
			this.updateQuantity(a, 1);
		else
			this.listFood.put(a, q);
	}

	public void updateQuantity(Food a, int t) {
		this.listFood.put(a, this.listFood.get(a) + t);
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
}
