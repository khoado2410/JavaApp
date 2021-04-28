package Food_Product;

import java.util.ArrayList;

public class Menu {
	
	private ArrayList<Food> Menu;
	
	public ArrayList<Food> getMenu() {
		return Menu;
	}

	public void setMenu(ArrayList<Food> menu) {
		Menu = menu;
	}

	public void showMenu() {
		System.out.println("Show menu");
	}
	
	
}
