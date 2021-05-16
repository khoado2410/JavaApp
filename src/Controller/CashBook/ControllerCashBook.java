package Controller.CashBook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import Model.Food_Product.Bill;
import Model.Food_Product.Food;
import Model.Staff_Manager.Staff;
import View.Frame.*;

public class ControllerCashBook {
	
	private RevenueUI revenueUI;
	private Bill  billModel;
	private DetailBill detailBillUI;
	
	public ControllerCashBook(DetailBill a) {
		this.detailBillUI = a;
		this.billModel = new Bill();
	}
	
	
	public ControllerCashBook(RevenueUI a) {
		this.revenueUI = a;
		this.billModel = new Bill();
	}
	
	public ArrayList<Bill> loadAllBill() {
		ArrayList<Bill> listBill = new ArrayList<Bill>();
		if(this.billModel.loadAllBill()) {
			listBill = this.billModel.getListBill();
		}
		return listBill;
	}
	
	public void loadDetailBill(String id) {
		Bill bill = new Bill();
		if(this.billModel.getInfoBill(id)) {
			ArrayList<Bill> lst = this.billModel.getListBill();
			for(Bill a: lst) {
				this.detailBillUI.billIDField.setText(a.getBillID());;
				this.detailBillUI.staffNameField.setText(a.getStaffName());
				this.detailBillUI.checkIn.setText(a.getCheckInHour());
				this.detailBillUI.checkOut.setText(a.getCheckOutHour());
			}
		}
    }
	
	public ArrayList<Food> loadFoodForDetailBill(String id){
		ArrayList<Food> li = new ArrayList<Food>();
		System.out.println("DDDD: " + id);
		if(this.billModel.getFoodForDetail(id)) {
			li = this.billModel.getListFoodforDetailBill(); 
		}
		return li;
	}
		
}
	
	
	

