package Controller.ControlMenuTable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

import Model.Food_Product.*;

public class ControllerMenu implements ActionListener, ItemListener {
	private Menu menu;
	private Menu menuSearchByType;
	private ArrayList<FoodType> listFoodType;
	private FoodType foodType;
	private Bill bill;
	private JPanel p;
	private JPanel coverPanel;
	private ControllerBill controllerBill;
	private ControllerTable controllerTable;
	JComboBox<String> jcb;

	public ControllerMenu(JPanel cv, JPanel fpanel, JComboBox<String> cb, ControllerBill conb, ControllerTable ct) {
		menu = new Menu();
		menu.loadFoodFromDB();
		menuSearchByType = menu;
		foodType = new FoodType();
		listFoodType = foodType.getListFoodTypeFromDB();
		p = fpanel;
		jcb = cb;
		coverPanel = cv;
		cv.setLayout(new FlowLayout(FlowLayout.LEADING, 15, 5));
		controllerBill = conb;
		controllerBill.setBillPanel(fpanel);
		this.controllerTable = ct;
		this.bill = controllerBill.getBill();
	}

	public void loadListMenu() {
		for (Food f : menuSearchByType.getMenu()) {
			JPanel menuPanel = new JPanel();
			menuPanel.setLayout(new BorderLayout());
			menuPanel.setPreferredSize(new Dimension(150, 150));
			JButton resButton = new JButton();
			ImageIcon resIcon = new ImageIcon(
					new ImageIcon(f.getImageFood()).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
			resButton.setIcon(resIcon);
			resButton.setBackground(Color.WHITE);
			resButton.addActionListener(this);
			resButton.setActionCommand(f.getNameFood());
			JLabel nameTableLabel = new JLabel("<html>" + f.getNameFood() + "<br/>" + f.getPrice() + "$</html>",
					SwingConstants.CENTER);
			menuPanel.add(resButton, BorderLayout.CENTER);
			menuPanel.add(nameTableLabel, BorderLayout.SOUTH);
			coverPanel.add(menuPanel);
			coverPanel.revalidate();
			coverPanel.repaint();
		}
	}

	public void loadTypeFood() {
		jcb.addItem("All");
		for (FoodType ft : listFoodType) {
			jcb.addItem(ft.getFoodTypeName());
		}
		jcb.addItemListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		for (Food f: bill.getListFood().keySet())
			if (!f.getNameFood().equals(e.getActionCommand()))
				continue;
			else
				return;
		for (Food f: menu.getMenu())
			if (f.getNameFood().equals(e.getActionCommand())) {
				if (bill.addFood(f, 1)) {
					bill.updateProduct(f, true);
					controllerBill.addToBill(f);
				}
				else {
					JOptionPane.showMessageDialog(null, "The ingredients doesn't enough to order");
				}
			}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == jcb) {
			String resType = "";
			if (jcb.getSelectedItem().equals("All")) {
				menuSearchByType = menu;
			} else {
				for (FoodType ft : listFoodType) {
					if (ft.getFoodTypeName().equals(jcb.getSelectedItem())) {
						resType = ft.getFoodTypeID();
						break;
					}
				}
				ArrayList<Food> res = menu.getListFoodByType(resType);
				menuSearchByType = new Menu(res);
			}
			coverPanel.removeAll();
			coverPanel.revalidate();
			coverPanel.repaint();
			loadListMenu();
		}
	}
}
