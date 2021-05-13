package Controller.ControlMenuTable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Model.Food_Product.*;

public class ControllerMenu implements ActionListener{
	private Menu menu;
	private JPanel p;
	private int count = 1;
	public ControllerMenu(JPanel fpanel) {
		menu = new Menu();
		menu.loadFoodFromDB();
		p = fpanel;
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
		for (Food f: menu.getMenu()) {
			if (f.getNameFood().equals(e.getActionCommand())) {
				JPanel foodPanel = new JPanel();
				foodPanel.setPreferredSize(new Dimension(500, 60));
				foodPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 5));
				JButton trashButton = new JButton(new ImageIcon("./src/Icon/Trash.png"));
				JLabel idLabel = new JLabel(Integer.toString(count) + ".");
				JLabel nameFood = new JLabel(f.getNameFood().trim());
				JButton minusButton = new JButton(new ImageIcon("./src/Icon/Minus.png"));
				JLabel quantityLabel = new JLabel("1");
				JButton plusButton = new JButton(new ImageIcon("./src/Icon/Plus.png"));
				JLabel singlePrice = new JLabel(Integer.toString(f.getPrice()));
				JLabel totalPrice = new JLabel(Integer.toString(f.getPrice()));
				foodPanel.add(trashButton);
				foodPanel.add(idLabel);
				foodPanel.add(nameFood);
				foodPanel.add(minusButton);
				foodPanel.add(quantityLabel);
				foodPanel.add(plusButton);
				foodPanel.add(singlePrice);
				foodPanel.add(totalPrice);
				p.add(foodPanel);
				p.revalidate();
				p.repaint();
				count++;
			}
		}
	}
}
