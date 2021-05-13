package Controller.MenuAndProduct;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JButton;

import View.Frame.Product_MenuManagementUI;
import View.Frame.Product_ProductManagementUI;

public class ManageMenuAndProduct {
	
	private Product_MenuManagementUI productAndMenu;
	
	private Product_ProductManagementUI product = new Product_ProductManagementUI();
	
	public ManageMenuAndProduct(Product_MenuManagementUI a){
		this.productAndMenu = a;
		
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
}
