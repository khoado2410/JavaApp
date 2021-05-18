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
import Model.History.EditHistory;
import Model.Staff_Manager.AccountManager;
import View.Frame.Product_MenuManagementUI;
import View.Frame.Product_ProductManagementUI;
import View.Frame.SpendingUI;
import View.Frame.Staff_ManagerStaffUI;
import View.Frame.ingredientMass;
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
	
	private SpendingUI spendUI;
	
	private ingredientMass massDetailUi;
	
	private static EditHistory editHistoryModel = new EditHistory();
	
	public ManageMenuAndProduct(SpendingUI spendUI) {
		this.spendUI = spendUI;
		this.productModel = new Product();
	}
	
	public int sumSpending(ArrayList<Product> listProduct) {
		int sum = 0;
		
		for(Product pri: listProduct) {
			sum += pri.getPrice();
		}
		return sum;
	}

	
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
	
	public ManageMenuAndProduct(ingredientMass m) {
		this.massDetailUi = m;
		this.productModel = new Product();
	}

	public void addIngredient(ArrayList<String> nameFood, ArrayList<Integer> mass, String id) {
			for(int i = 0; i < nameFood.size(); i++) {
				Product a = new Product();
				a.addIngredient(id, nameFood.get(i), mass.get(i));
			}
			JOptionPane.showMessageDialog(this.massDetailUi, "Thêm thành phần thành công");
	}
	
	public ArrayList<Product> getListProductForFood(String id){
		ArrayList<Product> listProduct = new ArrayList<Product>();
		if(this.productModel.getIngredientForFood(id)) {
			listProduct = this.productModel.getListProduct();
		}
		return listProduct;
	}
	
	public boolean removeIngredienttFromFood(ArrayList<Product> lstOld, String id) {
		for(Product temp : lstOld) {
			this.productModel.deleteIngredient(id, temp.getProductID());
		}
		return true;
	}
	
	public void updateIngredient(ArrayList<Product> lstOld, ArrayList<Integer> mass, String id) {
		if(this.removeIngredienttFromFood(lstOld, id)) {
			
			for(int i = 0; i < lstOld.size(); i++) {
				Product a = new Product();
				a.addIngredient(id, lstOld.get(i).getNameProduct(), mass.get(i));
				
			}
			
		}
	}
	
	
	public void viewIngredient(String text) {
		
		ArrayList<String> lst = new ArrayList<String>();
		ingredientMass ingredientDetail = new ingredientMass(lst , text, "edit");
		
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
	
	public void passDataToFormFood(ArrayList<String> list, String id) {
		
		ingredientMass ingredientDetail = new ingredientMass(list, id, "add");
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
			EditHistory edit123 = new EditHistory(AccountManager.managerID, "Update", "Food");
			this.editHistoryModel.addEditHistory(edit123);
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
			EditHistory edit123 = new EditHistory(AccountManager.managerID, "Add", "Food");
			this.editHistoryModel.addEditHistory(edit123);
		
			ArrayList<Food> listFoodFromMenu = this.loadFoodFromMenu();
			Product_MenuManagementUI.updateMenu(listFoodFromMenu);
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
				EditHistory edit123 = new EditHistory(AccountManager.managerID, "Remove", "Food");
				editHistoryModel.addEditHistory(edit123);
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
			EditHistory edit123 = new EditHistory(AccountManager.managerID, "Add", "Product");
			this.editHistoryModel.addEditHistory(edit123);
			
			ArrayList<Product> listProductFromMenu = this.getListProduct();
			Product_ProductManagementUI.updateProduct(listProductFromMenu);
			
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
	
	public boolean updateProduct() {
		
		String productID = this.editProductUI.productIDField.getText();
		String productName = this.editProductUI.productNameField.getText();

		int price = 0;
		try {
			if (isNumeric(this.editProductUI.priceField.getText()))
				price = Integer.parseInt(this.editProductUI.priceField.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Error");
		}

		int mass = Integer.parseInt(this.editProductUI.massField.getText());
		
		Product newProduct = new Product(productID, productName, mass, price);
		
		boolean check = this.productModel.updateProduct(newProduct);
		
		if(check == true) {
			EditHistory edit123 = new EditHistory(AccountManager.managerID, "Update", "Product");
			this.editHistoryModel.addEditHistory(edit123);
			ArrayList<Product> li = this.getListProduct();
			Product_ProductManagementUI.updateProduct(li);
			
			JOptionPane.showMessageDialog(this.editProductUI, "Chỉnh sửa " + newProduct.getNameProduct() + " thành công!");	
			this.editProductUI.dispose();
			return true;
				
			}else {
				JOptionPane.showMessageDialog(this.editForm, "Thêm thất bại!");
				this.editProductUI.dispose();
				return false;
			}
		
	}
	
	public static void removeRowOfProduct(String id) {
		
		Product a = new Product();
		int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa không?",
                null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
		
		if(input == 0) {
			Product food = new Product(id);
			boolean check = a.deleteProduct(food);
			if(check) {
				ArrayList<Product> listProduct = new ArrayList<Product>();
				if(a.loadProductFromDB()) {
					listProduct = a.getListProduct();
				}
				EditHistory edit123 = new EditHistory(AccountManager.managerID, "Remove", "Product");
				editHistoryModel.addEditHistory(edit123);
				Product_ProductManagementUI.updateProduct(listProduct);
			}
		}
		
	}
	
	
}
