package Controller.MenuAndProduct;

import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;

import Controller.DBConnection.DBConnection;
import Model.Food_Product.Food;
import Model.Food_Product.FoodType;
//import Model.Food_Product.*;
import Model.Food_Product.Menu;
import Model.Food_Product.Product;
import View.Frame.Product_MenuManagementUI;
import View.Frame.Product_ProductManagementUI;
import View.Frame.Staff_ManagerStaffUI;
import View.form.addFoodForm;
import View.form.addIngre;
import View.form.addProductForm;
import View.form.editFoodForm;
import View.form.editProductForm;

public class ManageMenuAndProduct {
	
	private Product_MenuManagementUI productAndMenu;
	
	private addFoodForm addFoodUI;
	
	private Product_ProductManagementUI product;
	
	private Menu menu;
	
	private addIngre ingre;
	
	private editFoodForm editForm;
	
	private Product productModel;
	
	private Product_ProductManagementUI productUI;
	
	private addProductForm addproductUI;
	
	private editProductForm editProductUI;
	
	public ManageMenuAndProduct(Product_ProductManagementUI a) {
		this.productUI = a;
		this.productModel = new Product();
	}
	
	public ManageMenuAndProduct(addIngre ingre) {
		this.ingre = ingre;
	}
	
	public void loadIngredient() {
		Product a = new Product();
		if(a.loadProductFromDB()) {
			for(Product b : a.getListProduct()) {
				this.ingre.ingreList.add(b.getNameProduct());
			}
		}	
	}
	
	public ManageMenuAndProduct(editFoodForm a) {
		this.editForm = a;
		this.menu = new Menu();
	}

	public ManageMenuAndProduct(addFoodForm a) {
		this.addFoodUI = a;
		this.menu = new Menu();
	}
	
	public ManageMenuAndProduct(Product_MenuManagementUI a){
		this.productAndMenu = a;
		this.product = new Product_ProductManagementUI();
		menu = new Menu();
	}
	

	public void changePanelProduct() {
		this.productAndMenu.content.setVisible(false);
		this.productAndMenu.top.setVisible(false);
		
		this.product.top.setVisible(true);
		this.product.content.setVisible(true);
		
		this.productAndMenu.add(product.top);
		this.productAndMenu.add(product.content);
		
		this.productAndMenu.buttonProduct.setBackground(new Color(255, 192, 203));
		this.productAndMenu.buttonProduct.setForeground(new Color(0, 0, 0));
		
		this.productAndMenu.buttonMenu.setBackground(new Color(0, 0, 0));
		this.productAndMenu.buttonMenu.setForeground(new Color(255, 192, 203));

	}
	
	public void changePanelMenu() {
		this.product.content.setVisible(false);
		this.product.top.setVisible(false);
		
		this.productAndMenu.top.setVisible(true);
		this.productAndMenu.content.setVisible(true);		
		
		this.productAndMenu.buttonMenu.setBackground(new Color(255, 192, 203));
		this.productAndMenu.buttonMenu.setForeground(new Color(0, 0, 0));
		
		this.productAndMenu.buttonProduct.setBackground(new Color(0, 0, 0));
		this.productAndMenu.buttonProduct.setForeground(new Color(255, 192, 203));

	}
	
	public ArrayList<Food> loadFoodFromMenu(){
		ArrayList<Food> listFood = new ArrayList<Food>();
		if(this.menu.loadFoodFromDB()) {
			listFood = this.menu.getMenu();
		}
		return listFood;
	}
	
	
	
	// ADD NEW FOOD
	
	public String getIDFoodMax() {
		Menu menu = new Menu();
		String id = "";
		int length = 0;
		String temp = menu.getIDMax();;
		temp = temp.replaceAll("\\s+", "");
		length = temp.length();
		
		int newTemp = Integer.parseInt(temp.replaceAll("\\D+", ""));
		newTemp++;
		String zero = "";
		for (int i = 0; i < length - 1 - String.valueOf(newTemp).length(); i++) {
			zero += "0";
		}
		id += "F" + zero + String.valueOf(newTemp);
		
		return id;
		
	}
	
	public void passDataToFormFood(ArrayList<String> list) {
		addFoodForm.ListIngredient(list);
	}
	
	public void passDataToEditFood(ArrayList<String> list) {
		this.editForm.ListIngredient(list);
	}
	
	public void openAddNewFoodUI() {
		this.addFoodUI = new addFoodForm();
		this.productAndMenu = new Product_MenuManagementUI();
	}
	
	public void OpenFileImage() {
		JFileChooser f = new JFileChooser(FileSystemView.getFileSystemView());
		int r = f.showSaveDialog(null);
		if (r == JFileChooser.APPROVE_OPTION) {
			this.addFoodUI.imgPath = f.getSelectedFile().getPath();
			this.addFoodUI.imageBtn.setText(this.addFoodUI.imgPath);
		}

	}
	
	public void OpenFileImageEditForm() {
		JFileChooser f = new JFileChooser(FileSystemView.getFileSystemView());
		int r = f.showSaveDialog(null);
		if (r == JFileChooser.APPROVE_OPTION) {
			this.editForm.imgPath = f.getSelectedFile().getPath();
			this.editForm.imageBtn.setText(this.editForm.imgPath);
		}

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
	
	
	public boolean updateFood() {
		
		String foodID = this.editForm.foodIDField.getText();
		String foodName = this.editForm.foodNameField.getText();

		int price = 0;
		try {
			if (isNumeric(this.editForm.priceField.getText()))
				price = Integer.parseInt(this.editForm.priceField.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Error");
		}

		String foodType = (String) this.editForm.foodTypeField.getSelectedItem();
		int quantity = 0;
		try {
			if (isNumeric(this.editForm.quantityField.getText()))
				quantity = Integer.parseInt(this.editForm.quantityField.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Error");
		}
		String img = this.editForm.imgPath;
		
		Food newFood = new Food(foodID, foodName, price, foodType, quantity, img, "");
		
		boolean check = this.menu.updateFood(newFood);
		
		if(check == true) {
			ArrayList<Food> li = this.loadFoodFromMenu();	
			Product_MenuManagementUI.updateMenu(li);
			
			JOptionPane.showMessageDialog(this.editForm, "Chỉnh sửa " + newFood.getNameFood() + " thành công!");	
			this.editForm.dispose();
			return true;
				
			}else {
				JOptionPane.showMessageDialog(this.editForm, "Thêm thất bại!");
				this.addFoodUI.dispose();
				return false;
			}
		
	}
	
	public boolean controllerAddNewFood() {
		
		ArrayList<Food> li = this.loadFoodFromMenu();
		
		
		String foodID = this.addFoodUI.foodIDField.getText();
		String foodName = this.addFoodUI.foodNameField.getText();

		int price = 0;
		try {
			if (isNumeric(this.addFoodUI.priceField.getText()))
				price = Integer.parseInt(this.addFoodUI.priceField.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Error");
		}

		String foodType = (String) this.addFoodUI.foodTypeField.getSelectedItem();
		int quantity = 0;
		try {
			if (isNumeric(this.addFoodUI.quantityField.getText()))
				quantity = Integer.parseInt(this.addFoodUI.quantityField.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Error");
		}
		String img = this.addFoodUI.imgPath;
		
		Food newFood = new Food(foodID, foodName, price, foodType, quantity, img, "");
				
		boolean check = this.menu.addNewFood(newFood);
	
		if(check == true) {
		ImageIcon _edit = new ImageIcon(Staff_ManagerStaffUI.class.getResource("/images/pencil.png"));

		ImageIcon _remove = new ImageIcon(Staff_ManagerStaffUI.class.getResource("/images/delete.png"));
		
		JButton edit = new JButton();
		JButton remove = new JButton();
		edit.setIcon(_edit);
		remove.setIcon(_remove);
		Product_MenuManagementUI.addRowToTable(new Object[] {newFood.getFoodID(), newFood.getNameFood(), newFood.getFoodTypeName(),
				newFood.getPrice(), newFood.getQuantityOfStock(), edit, remove});
		this.addFoodUI.dispose();
		
		JOptionPane.showMessageDialog(addFoodUI, "Thêm " + newFood.getNameFood() + " thành công!");	
		return true;
			
		}else {
			JOptionPane.showMessageDialog(addFoodUI, "Thêm thất bại!");
			this.addFoodUI.dispose();
			return false;
		}
		
	}
	
	public void getAllNameFoodTypeToCombobox(){
		
		FoodType a = new FoodType();
		for(FoodType b : a.getListFoodTypeFromDB()) {
			this.addFoodUI.foodTypeField.addItem(b.getFoodTypeName());
		}
		
	}
	
	public void getAllNameFoodTypeToComboboxEditForm(){
		
		FoodType a = new FoodType();
		for(FoodType b : a.getListFoodTypeFromDB()) {
			this.editForm.foodTypeField.addItem(b.getFoodTypeName());
		}
		
	}

	
	public void addProductAndFoodToFoodDetailAndMenu() {
		
		if(this.controllerAddNewFood()) {
			for(int i = 0; i < this.addFoodUI.listFoodName.size(); i++) {
				Product a = new Product();
				a.addIngredient(this.addFoodUI.foodIDField.getText(), this.addFoodUI.listFoodName.get(i));
			}
		}

		
	}
	
	public static void main(String[] args) {
		//ManageMenuAndProduct a = new ManageMenuAndProduct();

	}

	public static void removeRow(String id) {
		
		Menu a = new Menu();
		int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa không?",
                null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
		
		if(input == 0) {
			Food food = new Food(id);
			boolean check = a.deleteFoodFromMenu(food);
			if(check) {
				ArrayList<Food> listFood = new ArrayList<Food>();
				if(a.loadFoodFromDB()) {
					listFood = a.getMenu();
				}
				Product_MenuManagementUI.updateMenu(listFood);
			}
		}
		
	}
	
	// PRODUCT
	
	public ManageMenuAndProduct(addProductForm a) {
		this.addproductUI = a;
		this.productModel = new Product();
	}
	
	public ArrayList<Product> getListProduct(){
		ArrayList<Product> listProduct = new ArrayList<Product>();
		if(this.productModel.loadProductFromDB()) {
			listProduct = this.productModel.getListProduct();
		}
		return listProduct;
	}
	
	public String getIDProductMax() {
		Product product = new Product();
		String id = "";
		int length = 0;
		String temp = product.getIDProductMax();;
		temp = temp.replaceAll("\\s+", "");
		length = temp.length();
		
		int newTemp = Integer.parseInt(temp.replaceAll("\\D+", ""));
		newTemp++;
		String zero = "";
		for (int i = 0; i < length - 1 - String.valueOf(newTemp).length(); i++) {
			zero += "0";
		}
		id += "P" + zero + String.valueOf(newTemp);
		
		return id;
		
	}
	
		public boolean controllerAddNewProduct() {	

		String productID = this.addproductUI.productIDField.getText();
		String productName = this.addproductUI.productNameField.getText();
		
		int mass = 0;
		try {
			if(isNumeric(this.addproductUI.priceField.getText()))
				mass = Integer.parseInt(this.addproductUI.massField.getText());
		} catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Error");
		}
				
		int price = 0;
		try {
			if(isNumeric(this.addproductUI.priceField.getText()))
				price = Integer.parseInt(this.addproductUI.priceField.getText());
		} catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Error");
		}
		
		Product newProduct = new Product(productID, productName, mass, price);
				
		boolean check = this.productModel.addNewProduct(newProduct);
	
		if(check == true) {
			ImageIcon _edit = new ImageIcon(Staff_ManagerStaffUI.class.getResource("/images/pencil.png"));
	
			ImageIcon _remove = new ImageIcon(Staff_ManagerStaffUI.class.getResource("/images/delete.png"));
			
			JButton edit = new JButton();
			JButton remove = new JButton();
			edit.setIcon(_edit);
			remove.setIcon(_remove);
			
			Product_ProductManagementUI.addRowToTable(new Object[] {newProduct.getProductID(), newProduct.getNameProduct(), 
																newProduct.getMass(), newProduct.getPrice(), edit, remove});
			this.addproductUI.dispose();
			
			JOptionPane.showMessageDialog(this.addproductUI, "Thêm " + newProduct.getProductID() + " thành công!");	
			return true;
			
		}else {
			JOptionPane.showMessageDialog(this.addproductUI, "Thêm thất bại!");
			this.addproductUI.dispose();
			return false;
		}
		
	}
		
	// UPDATE
	public	ManageMenuAndProduct(editProductForm a) {
		this.editProductUI = a;
		this.productModel = new Product();
	}
	
	
	
}
