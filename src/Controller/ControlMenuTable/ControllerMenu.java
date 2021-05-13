package Controller.ControlMenuTable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Model.Food_Product.*;

public class ControllerMenu implements ActionListener {
	private Menu menu;
	private Bill bill;
	private JPanel p;
	private ControllerBill controllerBill;

	public ControllerMenu(JPanel fpanel) {
		menu = new Menu();
		menu.loadFoodFromDB();
		p = fpanel;
		bill = new Bill();
	}

	public void loadListMenu(JPanel jp) {
		for (Food f : menu.getMenu()) {
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
			jp.add(menuPanel);
			jp.revalidate();
			jp.repaint();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (Food f : menu.getMenu()) {
			if (f.getNameFood().equals(e.getActionCommand())) {
				if (!bill.getListFood().containsKey(f)) {
					bill.addFood(f, 1);
					controllerBill = new ControllerBill(bill, f, p);
					controllerBill.addToBill();
				}
			}
		}
	}
}
