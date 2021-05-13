package Controller.ControlMenuTable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.*;

import Model.Food_Product.*;

public class ControllerMenu {
	private Menu menu;
	public ControllerMenu() {
		menu = new Menu();
		menu.loadFoodFromDB();
	}
	public void loadListMenu(JPanel jp) {
		if(menu.loadFoodFromDB()) {
			for (Food f: menu.getMenu()) {
				JPanel menuPanel = new JPanel();
				menuPanel.setLayout(new BorderLayout());
				menuPanel.setPreferredSize(new Dimension(150, 150));
				JButton resButton = new JButton();
				ImageIcon resIcon = new ImageIcon(
						new ImageIcon(f.getImageFood()).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
				resButton.setIcon(resIcon);
				resButton.setBackground(Color.WHITE);
				JLabel nameTableLabel = new JLabel("<html>" + f.getNameFood() + "<br/>" + f.getPrice() + "$</html>", SwingConstants.CENTER);
				menuPanel.add(resButton, BorderLayout.CENTER);
				menuPanel.add(nameTableLabel, BorderLayout.SOUTH);
				jp.add(menuPanel);
			}
		}
	}
}
