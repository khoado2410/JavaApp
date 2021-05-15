package Controller.ControlMenuTable;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

import Model.Food_Product.*;

public class ControllerBill implements ActionListener {
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
	private ControllerBill cb;

	public ControllerBill(Bill b, Food f, JPanel p) {
		this.bill = b;
		this.food = f;
		this.billPanel = p;
		singlePrice = new JLabel(Integer.toString(food.getPrice()));
		totalPrice = new JLabel(Integer.toString(food.getPrice() * bill.getListFood().get(food)));
		quantityLabel = new JLabel(Integer.toString(bill.getListFood().get(food)));
	}

	public ControllerBill(Bill b, JPanel p, Table t) {
		this.billPanel = p;
		this.table = t;
		this.bill = b;
		menu = new Menu();
		menu.loadFoodFromDB();
		listFoods = menu.getMenu();
	}

	public void loadBill() {
		billPanel.removeAll();
		billPanel.revalidate();
		billPanel.repaint();
		HashMap<String, Integer> listFood = new HashMap<>();
		listFood = bill.loadListFoodInBill(table.getIdBill());
		for (String t : listFood.keySet()) {
			for (Food f : listFoods) {
				if (f.getNameFood().equals(t)) {
					bill.addFood(f, 1);
					cb = new ControllerBill(bill, f, billPanel);
					cb.addToBill();
				}
			}
		}
	}

	public void addToBill() {
		foodPanel = new JPanel();
		foodPanel.setPreferredSize(new Dimension(500, 60));
		foodPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 5));
		JButton trashButton = new JButton(new ImageIcon("./src/Icon/Trash.png"));
		trashButton.addActionListener(this);
		trashButton.setActionCommand("Delete");
		JLabel nameFood = new JLabel(food.getNameFood().trim());
		JButton minusButton = new JButton(new ImageIcon("./src/Icon/Minus.png"));
		minusButton.addActionListener(this);
		minusButton.setActionCommand("Minus");
		JButton plusButton = new JButton(new ImageIcon("./src/Icon/Plus.png"));
		plusButton.addActionListener(this);
		plusButton.setActionCommand("Plus");
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

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Minus": {
			bill.updateQuantity(food, -1);
			if (bill.getQuantityFoodInBill(food) == 0) {
				bill.removeFood(food);
				billPanel.remove(foodPanel);
				billPanel.revalidate();
				billPanel.repaint();
			} else {
				singlePrice.setText(Integer.toString(food.getPrice()));
				totalPrice.setText(Integer.toString(food.getPrice() * bill.getListFood().get(food)));
				quantityLabel.setText(Integer.toString(bill.getListFood().get(food)));
			}
			break;
		}
		case "Plus": {
			bill.updateQuantity(food, 1);
			singlePrice.setText(Integer.toString(food.getPrice()));
			totalPrice.setText(Integer.toString(food.getPrice() * bill.getListFood().get(food)));
			quantityLabel.setText(Integer.toString(bill.getListFood().get(food)));
			break;
		}
		case "Delete": {
			billPanel.remove(foodPanel);
			billPanel.revalidate();
			billPanel.repaint();
			bill.removeFood(food);
			break;
		}
		}
	}
}
