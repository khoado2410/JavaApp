package Controller.ManageStaff;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import Model.Food_Product.Food;
import Model.Food_Product.FoodType;
import Model.Food_Product.Menu;
import Model.Food_Product.Product;
import Model.Shift.Shift;
import Model.Staff_Manager.AccountManager;
import Model.Staff_Manager.Staff;
import View.Frame.Product_MenuManagementUI;
import View.Frame.Product_ProductManagementUI;
import View.Frame.Staff_ManagerStaffUI;
import View.Frame.Staff_TimekeepingUI;
import View.form.addStaffForm;
import View.form.editStaffForm;
import View.form.scheduleShift;

public class ControllerManageStaff {

	
	private Staff_ManagerStaffUI manageStaffUI;
	
	private addStaffForm addStaffUI;
	
	private Staff staffModel;
	
	private AccountManager accModel;
	
	private editStaffForm editStaffUI;
	
	private scheduleShift scheduleShiftUI;
	
	private Shift shiftModel;
	
	private Staff_TimekeepingUI timekeepingUI;
	
	// SCHEDULE SHIFT
	
//	public removeShift(String id) {
//		
//	}
	
	public ControllerManageStaff(Staff_TimekeepingUI staff_TimekeepingUI) {
		// TODO Auto-generated constructor stub
		this.timekeepingUI = staff_TimekeepingUI;
		this.shiftModel = new Shift();
	}
	
	public ArrayList<Shift> getListShift() {
		ArrayList<Shift> listFood = new ArrayList<Shift>();
		if(this.shiftModel.loadShift()) {
			listFood = this.shiftModel.getListShift();
		}
		return listFood;
	}
	
	public ControllerManageStaff(scheduleShift a) {
		this.scheduleShiftUI = a;
		this.staffModel = new Staff();
		this.accModel = new AccountManager();
		this.shiftModel = new Shift();
		}
	
	public void getIDAccToCombobox(){
		AccountManager a = new AccountManager();
		if(a.loadAccount()) {
			for(AccountManager b : a.getListAcc()) {
				this.scheduleShiftUI.manageAccount.addItem(b.getAccID());
			}
		}
	}
	
	public void getAllNameStaffToCombobox(){
		Staff a = new Staff();
		if(a.loadStaff()) {
			for(Staff b : a.getListStaff()) {
				this.scheduleShiftUI.staffNameField.addItem(b.getStaffName().trim());
			}
		}
	}
	
	public void addNewShift() {
		String acc = (String) this.scheduleShiftUI.manageAccount.getSelectedItem();
		String staffName = (String) this.scheduleShiftUI.staffNameField.getSelectedItem();
		int start = (Integer) this.scheduleShiftUI.hourStart.getSelectedItem();
		int end = (Integer) this.scheduleShiftUI.hourEnd.getSelectedItem();
		String datework = this.scheduleShiftUI.dateworkField.getText();
		
		//System.out.println("controller: " + datework);
		
		Shift sh = new Shift(0, staffName, end, start, acc, datework);
		
		boolean check = this.shiftModel.addNewShift(sh);
		
		if(check == true) {
			ImageIcon _edit = new ImageIcon(Staff_ManagerStaffUI.class.getResource("/images/pencil.png"));

			ImageIcon _remove = new ImageIcon(Staff_ManagerStaffUI.class.getResource("/images/delete.png"));
			
			JButton edit = new JButton();
			JButton remove = new JButton();
			
			edit.setIcon(_edit);
			remove.setIcon(_remove);
			
			Staff_TimekeepingUI.refreshTable(this.getListShift());
			
			this.scheduleShiftUI.dispose();
			
			JOptionPane.showMessageDialog(this.scheduleShiftUI, "Thêm " + sh.getAccID() + " thành công!");	
			//return true;
				
			}else {
				JOptionPane.showMessageDialog(this.scheduleShiftUI, "Thêm thất bại!");
				this.scheduleShiftUI.dispose();
				//return false;
			}
	 }
	
	public static void RemoveTimekeeping(String id) {
		int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa không?",
                null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
		
		if(input == 0) {
			Shift sh = new Shift();
			boolean check = sh.deleteShift(Integer.parseInt(id));
			if(check) {
				ArrayList<Shift> listShift = new ArrayList<Shift>();
				if(sh.loadShift()) {
					listShift = sh.getListShift();
				}
				Staff_TimekeepingUI.refreshTable(listShift);
			}
		}
	}
	
	
	// STAFF MANAGER
	
	public ControllerManageStaff(Staff_ManagerStaffUI a) {
		this.manageStaffUI = a;
		this.staffModel = new Staff();
		
	}
	
	public ArrayList<Staff> loadStaff() {
		ArrayList<Staff> listStaff = new ArrayList<Staff>();
		if(this.staffModel.loadStaff()) {
			listStaff = this.staffModel.getListStaff();
		}
		return listStaff;

	}
	
	public static void removeRowOfStaff(String id) {
		Staff a = new Staff();
		int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa không?",
                null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
		
		if(input == 0) {
			Staff food = new Staff(id);
			boolean check = a.deleteStaffFromDB(food);
			if(check) {
				ArrayList<Staff> listStaff = new ArrayList<Staff>();
				if(a.loadStaff()) {
					listStaff = a.getListStaff();
				}
				Staff_ManagerStaffUI.updateStaff(listStaff);
			}
		}
		
	}

	
	// FORM EDIT STAFF
	public ControllerManageStaff(editStaffForm a) {
		this.editStaffUI = a;
		this.staffModel = new Staff();
	}
	
	public boolean updatStaff() {
		
		String staffID = this.editStaffUI.staffIDField.getText();
		String staffName = this.editStaffUI.staffNameField.getText();
		String dob = this.editStaffUI.dateOfBirthField.getText();
		String gender = "";
		if(this.editStaffUI.maleCheck.isSelected())
			gender = "Male";
		else
			gender = "Female";
		String address = this.editStaffUI.addressField.getText();
		int salary = 0;
		try {
			if(isNumeric(this.editStaffUI.salaryField.getText()))
				salary = Integer.parseInt(this.editStaffUI.salaryField.getText());
		} catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Error");
		}
				
		int point = 0;
		try {
			if(isNumeric(this.editStaffUI.pointField.getText()))
				point = Integer.parseInt(this.editStaffUI.pointField.getText());
		} catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Error");
		}
		Staff newStaff = new Staff(staffID,  staffName, dob, gender, address, salary, point);
		
		boolean check = this.staffModel.editStaffFromDB(newStaff);
	
		if(check == true) {
					ArrayList<Staff> li = this.loadStaff();
					Staff_ManagerStaffUI.updateStaff(li);
					
					JOptionPane.showMessageDialog(this.editStaffUI, "Chỉnh sửa " + newStaff.getStaffName() + " thành công!");	
					this.editStaffUI.dispose();
					return true;
						
					}else {
						JOptionPane.showMessageDialog(this.editStaffUI, "Thêm thất bại!");
						this.editStaffUI.dispose();
						return false;
					}
	}
	
	
	// FORM ADD STAFF
	public ControllerManageStaff(addStaffForm a) {
		this.addStaffUI = a;
		this.staffModel = new Staff();
		
	}


	public String getIDStaffMax() {
		String id = "";
		int length = 0;
		String temp = this.staffModel.getIDMax();;
		temp = temp.replaceAll("\\s+", "");
		length = temp.length();
		
		int newTemp = Integer.parseInt(temp.replaceAll("\\D+", ""));
		newTemp++;
		String zero = "";
		for (int i = 0; i < length - 1 - String.valueOf(newTemp).length(); i++) {
			zero += "0";
		}
		id += "S" + zero + String.valueOf(newTemp);
		
		return id;
	}
	
		public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        int d = Integer.parseInt(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
		}
		
		 public boolean addNewStaff() {
				String staffID = this.addStaffUI.staffIDField.getText();
				String staffName = this.addStaffUI.staffNameField.getText();
				String dob = this.addStaffUI.dateOfBirthField.getText();
				String gender = "";
				if(this.addStaffUI.maleCheck.isSelected())
					gender = "Male";
				else
					gender = "Female";
				String address = this.addStaffUI.addressField.getText();
				int salary = 0;
				try {
					if(isNumeric(this.addStaffUI.salaryField.getText()))
						salary = Integer.parseInt(this.addStaffUI.salaryField.getText());
				} catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Error");
				}
						
				int point = 0;
				try {
					if(isNumeric(this.addStaffUI.pointField.getText()))
						point = Integer.parseInt(this.addStaffUI.pointField.getText());
				} catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Error");
				}
				Staff newStaff = new Staff(staffID,  staffName, dob, gender, address, salary, point);
				
				boolean check = this.staffModel.addStaffToDB(newStaff);
			
				if(check == true) {
				ImageIcon _edit = new ImageIcon(Staff_ManagerStaffUI.class.getResource("/images/pencil.png"));

				ImageIcon _remove = new ImageIcon(Staff_ManagerStaffUI.class.getResource("/images/delete.png"));
				
				JButton edit = new JButton();
				JButton remove = new JButton();
				
				edit.setIcon(_edit);
				remove.setIcon(_remove);
				Staff_ManagerStaffUI.addRowToTable(new Object[] {newStaff.getStaffID(), newStaff.getStaffName(), 
						newStaff.getAddress(), newStaff.getGender(), newStaff.getDateOfBirth(), 
						newStaff.getSalary(), newStaff.getPoint(), edit, remove});
				this.addStaffUI.dispose();
				
				JOptionPane.showMessageDialog(this.addStaffUI, "Thêm " + newStaff.getStaffName() + " thành công!");	
				return true;
					
				}else {
					JOptionPane.showMessageDialog(this.addStaffUI, "Thêm thất bại!");
					this.addStaffUI.dispose();
					return false;
				}
		 }
	
}
