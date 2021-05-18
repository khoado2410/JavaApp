package Controller.ControlMenuTable;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

import Model.Food_Product.*;

public class ControllerBill {
	private Bill bill;
	private Food food;
	private JLabel singlePrice;
	private JLabel totalPrice;
	private JLabel quantityLabel;
	private JPanel billPanel;
	private JPanel foodPanel;
	private Table table;
	private ArrayList<Food> listFoods;
	private Menu menu;

	/*
	 * public ControllerBill(Bill b, Food f, JPanel p) { this.bill = b; this.food =
	 * f; this.billPanel = p; singlePrice = new
	 * JLabel(Integer.toString(food.getPrice())); totalPrice = new
	 * JLabel(Integer.toString(food.getPrice() * bill.getListFood().get(food)));
	 * quantityLabel = new JLabel(Integer.toString(bill.getListFood().get(food))); }
	 */

	public ControllerBill(Bill b, JPanel p, Table t) {
		this.billPanel = p;
		this.table = t;
		this.bill = b;
		this.bill.setTableID(t.getIdTable());
		menu = new Menu();
		menu.loadFoodFromDB();
		listFoods = menu.getMenu();
	}

	public void setBillPanel(JPanel billPanel) {
		this.billPanel = billPanel;
	}

	public void setControllerBill(Bill b, Food f, JPanel p) {
		this.bill = b;
		this.food = f;
		this.billPanel = p;
		singlePrice = new JLabel(Integer.toString(food.getPrice()));
		totalPrice = new JLabel(Integer.toString(food.getPrice() * bill.getListFood().get(food)));
		quantityLabel = new JLabel(Integer.toString(bill.getListFood().get(food)));
	}

	public void setBill(Bill b) {
		this.bill = b;
	}

	public Bill getBill() {
		return this.bill;
	}

	public void loadBill() {
		billPanel.removeAll();
		billPanel.revalidate();
		billPanel.repaint();
		for (Food food : bill.getListFood().keySet())
			addToBill(food);
	}

	public void addToBill(Food food) {
		foodPanel = new JPanel();
		foodPanel.setPreferredSize(new Dimension(500, 60));
		foodPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 5));
		singlePrice = new JLabel(Integer.toString(food.getPrice()));
		totalPrice = new JLabel(Integer.toString(food.getPrice() * bill.getListFood().get(food)));
		quantityLabel = new JLabel(Integer.toString(bill.getListFood().get(food)));
		FoodUpdate fupd = new FoodUpdate(food, bill, billPanel, foodPanel, quantityLabel, singlePrice, totalPrice);
		JButton trashButton = new JButton(new ImageIcon("./src/Icon/Trash.png"));
		fupd.updateFoodAction(trashButton, "Delete");
		JLabel nameFood = new JLabel(food.getNameFood().trim());
		JButton minusButton = new JButton(new ImageIcon("./src/Icon/Minus.png"));
		fupd.updateFoodAction(minusButton, "Minus");
		JButton plusButton = new JButton(new ImageIcon("./src/Icon/Plus.png"));
		fupd.updateFoodAction(plusButton, "Plus");
		foodPanel.add(trashButton);
		foodPanel.add(nameFood);
		foodPanel.add(minusButton);
		foodPanel.add(quantityLabel);
		foodPanel.add(plusButton);
		foodPanel.add(singlePrice);
		foodPanel.add(totalPrice);
		billPanel.add(foodPanel);
		billPanel.revalidate();
		billPanel.repaint();
	}
	
	public void createBill() {
		if (table.getStatus() == 1)
			bill.createBillInDB();
		else {
			bill.updateBillDetail();
		}
	}
}

class FoodUpdate implements ActionListener {
	private Food f;
	private Bill b;
	private JPanel billPanel;
	private JPanel foodPanel;
	private JLabel singlePrice;
	private JLabel totalPrice;
	private JLabel quantityLabel;

	public FoodUpdate(Food food, Bill bill, JPanel bP, JPanel fP, JLabel q, JLabel s, JLabel t) {
		this.f = food;
		this.b = bill;
		this.billPanel = bP;
		this.foodPanel = fP;
		this.quantityLabel = q;
		this.singlePrice = s;
		this.totalPrice = t;
	}

	public void updateFoodAction(JButton jbtn, String na) {
		jbtn.addActionListener(this);
		jbtn.setActionCommand(na);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Minus": {
			b.updateQuantity(f, -1);
			if (b.getQuantityFoodInBill(f) == 0) {
				b.removeFood(f);
				billPanel.remove(foodPanel);
				billPanel.revalidate();
				billPanel.repaint();
			} else {
				singlePrice.setText(Integer.toString(f.getPrice()));
				totalPrice.setText(Integer.toString(f.getPrice() * b.getListFood().get(f)));
				quantityLabel.setText(Integer.toString(b.getListFood().get(f)));
			}
			break;
		}
		case "Plus": {
			b.updateQuantity(f, 1);
			singlePrice.setText(Integer.toString(f.getPrice()));
			totalPrice.setText(Integer.toString(f.getPrice() * b.getListFood().get(f)));
			quantityLabel.setText(Integer.toString(b.getListFood().get(f)));
			break;
		}
		case "Delete": {
			billPanel.remove(foodPanel);
			billPanel.revalidate();
			billPanel.repaint();
			b.removeFood(f);
			break;
		}
		}
	}
}
