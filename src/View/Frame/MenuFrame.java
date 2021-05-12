package View.Frame;

import javax.swing.*;
import javax.swing.border.*;

import Controller.PanelChange.ControllerPanel;

import java.awt.*;

public class MenuFrame extends JPanel {
	private JPanel mainFramePanel;
	private JPanel sideFramePanel;
	private JPanel navbarPanel;
	private JPanel subPanel;
	private JPanel mainPanel;
	private JPanel payPanel;
	private JButton tablesButton;
	private JButton menuButton;
	private JButton payButton;
	private JButton homeButton;
	private JComboBox<String> listTypeFood;
	private JPanel menuPanel;
	private JPanel nameTablePanel;
	private JPanel listFoodPanel;
	private JPanel foodPanel;
	private JLabel tableName;
	private ControllerPanel controller;
	
	public MenuFrame() {
		setLayout(new BorderLayout());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize);
		int leftSide = screenSize.width * 2 / 3;
		int heightNav = screenSize.height * 1 / 8;
		int heightSub = heightNav;
		int heightMain = screenSize.height - heightNav - heightSub;

		mainFramePanel = new JPanel();
		mainFramePanel.setPreferredSize(new Dimension(leftSide, screenSize.height));
		sideFramePanel = new JPanel();
		sideFramePanel.setPreferredSize(new Dimension(screenSize.width - leftSide, screenSize.height));

		add(mainFramePanel, BorderLayout.CENTER);
		add(sideFramePanel, BorderLayout.EAST);

		mainFramePanel.setLayout(new FlowLayout(1, 0, 0));

		navbarPanel = new JPanel();
		navbarPanel.setPreferredSize(new Dimension(leftSide, heightNav));
		navbarPanel.setLayout(new GridLayout(1, 2));

		tablesButton = new JButton("Tables");
		tablesButton.setBackground(Color.BLACK);
		tablesButton.setForeground(Color.PINK);
		tablesButton.setFont(tablesButton.getFont().deriveFont(Font.BOLD, 20));
		controller = new ControllerPanel(this);
		controller.setEventButton(tablesButton, tablesButton.getText());
		
		menuButton = new JButton("Menu");
		menuButton.setBackground(Color.PINK);
		menuButton.setFont(tablesButton.getFont().deriveFont(Font.BOLD, 20));

		navbarPanel.add(tablesButton);
		navbarPanel.add(menuButton);

		mainFramePanel.add(navbarPanel);

		subPanel = new JPanel();
		subPanel.setLayout(new BorderLayout(0, 0));
		subPanel.setPreferredSize(new Dimension(leftSide, heightSub));
		mainFramePanel.add(subPanel);

		mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(leftSide, heightMain));
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		mainFramePanel.add(mainPanel);

		sideFramePanel.setLayout(new BorderLayout(0, 0));

		payPanel = new JPanel();
		payPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		sideFramePanel.add(payPanel, BorderLayout.CENTER);

		payButton = new JButton("Pay");
		payButton.setBackground(Color.BLACK);
		payButton.setForeground(Color.PINK);
		payButton.setFont(payButton.getFont().deriveFont(Font.BOLD, 20));
		payButton.setPreferredSize(new Dimension(screenSize.width - leftSide, 100));
		sideFramePanel.add(payButton, BorderLayout.SOUTH);

		ImageIcon homeIcon = new ImageIcon("./src/Icon/Home.png");
		homeButton = new JButton(homeIcon);
		controller.setEventButton(homeButton, "Home");
		homeButton.setPreferredSize(new Dimension(50, 50));
		homeButton.setBackground(Color.BLACK);
		JPanel homeButtonArea = new JPanel();
		JPanel typePanel = new JPanel();
		typePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel type = new JLabel();
		typePanel.setBorder(new EmptyBorder(10, 50, 10, 0));
		type.setText("Type Food: ");
		type.setFont(type.getFont().deriveFont(Font.BOLD, 18));
		typePanel.add(type);
		listTypeFood = new JComboBox<>();
		listTypeFood.setPreferredSize(new Dimension(300, 20));
		typePanel.add(listTypeFood);
		homeButtonArea.setLayout(new GridLayout(2, 1));
		homeButtonArea.add(homeButton);
		homeButtonArea.setPreferredSize(new Dimension(50, heightSub));
		subPanel.add(homeButtonArea, BorderLayout.WEST);
		subPanel.add(typePanel, BorderLayout.CENTER);

		mainPanel.setLayout(new BorderLayout(0, 0));
		JPanel tableUsedAreaJPanel = new JPanel();
		JPanel listMenuJPanel = new JPanel();
		mainPanel.add(tableUsedAreaJPanel, BorderLayout.NORTH);
		mainPanel.add(listMenuJPanel, BorderLayout.CENTER);
		listMenuJPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));

		menuPanel = new JPanel();
		menuPanel.setLayout(new BorderLayout());
		menuPanel.setPreferredSize(new Dimension(150, 150));
		JButton resButton = new JButton();
		ImageIcon resIcon = new ImageIcon(
				new ImageIcon("./src/foodImg/1.jpg").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
		resButton.setIcon(resIcon);
		resButton.setBackground(Color.WHITE);
		JLabel nameTableLabel = new JLabel("<html>ShellFish Flatter<br/>155$</html>", SwingConstants.CENTER);
		menuPanel.add(resButton, BorderLayout.CENTER);
		menuPanel.add(nameTableLabel, BorderLayout.SOUTH);
		listMenuJPanel.add(menuPanel);

		menuPanel = new JPanel();
		menuPanel.setLayout(new BorderLayout());
		menuPanel.setPreferredSize(new Dimension(150, 150));
		resButton = new JButton();
		resIcon = new ImageIcon(
				new ImageIcon("./src/foodImg/2.jpg").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
		resButton.setIcon(resIcon);
		resButton.setBackground(Color.WHITE);
		nameTableLabel = new JLabel("<html>Scrimp Cocktail<br/>21$</html>", SwingConstants.CENTER);
		menuPanel.add(resButton, BorderLayout.CENTER);
		menuPanel.add(nameTableLabel, BorderLayout.SOUTH);
		listMenuJPanel.add(menuPanel);

		menuPanel = new JPanel();
		menuPanel.setLayout(new BorderLayout());
		menuPanel.setPreferredSize(new Dimension(150, 150));
		resButton = new JButton();
		resIcon = new ImageIcon(
				new ImageIcon("./src/foodImg/3.jpg").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
		resButton.setIcon(resIcon);
		resButton.setBackground(Color.WHITE);
		nameTableLabel = new JLabel("<html>Golden Osetra<br/>320$</html>", SwingConstants.CENTER);
		menuPanel.add(resButton, BorderLayout.CENTER);
		menuPanel.add(nameTableLabel, BorderLayout.SOUTH);
		listMenuJPanel.add(menuPanel);

		menuPanel = new JPanel();
		menuPanel.setLayout(new BorderLayout());
		menuPanel.setPreferredSize(new Dimension(150, 150));
		resButton = new JButton();
		resIcon = new ImageIcon(
				new ImageIcon("./src/foodImg/4.jpg").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
		resButton.setIcon(resIcon);
		resButton.setBackground(Color.WHITE);
		nameTableLabel = new JLabel("<html>Osetra<br/>220$</html>", SwingConstants.CENTER);
		menuPanel.add(resButton, BorderLayout.CENTER);
		menuPanel.add(nameTableLabel, BorderLayout.SOUTH);
		listMenuJPanel.add(menuPanel);

		menuPanel = new JPanel();
		menuPanel.setLayout(new BorderLayout());
		menuPanel.setPreferredSize(new Dimension(150, 150));
		resButton = new JButton();
		resIcon = new ImageIcon(
				new ImageIcon("./src/foodImg/5.jpg").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
		resButton.setIcon(resIcon);
		resButton.setBackground(Color.WHITE);
		nameTableLabel = new JLabel("<html>Blue Crab Soup<br/>18$</html>", SwingConstants.CENTER);
		menuPanel.add(resButton, BorderLayout.CENTER);
		menuPanel.add(nameTableLabel, BorderLayout.SOUTH);
		listMenuJPanel.add(menuPanel);

		nameTablePanel = new JPanel();
		tableName = new JLabel("Table 1");
		nameTablePanel.add(tableName);
		listFoodPanel = new JPanel();
		listFoodPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
		listFoodPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		foodPanel = new JPanel();
		foodPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
		JButton trashButton = new JButton(new ImageIcon("./src/Icon/Trash.png"));
		JLabel idLabel = new JLabel("1.");
		JLabel nameFood = new JLabel("Blue Crab Soup");
		JButton minusButton = new JButton(new ImageIcon("./src/Icon/Minus.png"));
		JLabel quantityLabel = new JLabel("1");
		JButton plusButton = new JButton(new ImageIcon("./src/Icon/Plus.png"));
		JLabel singlePrice = new JLabel("18$");
		JLabel totalPrice = new JLabel("18$");
		foodPanel.add(trashButton);
		foodPanel.add(idLabel);
		foodPanel.add(nameFood);
		foodPanel.add(minusButton);
		foodPanel.add(quantityLabel);
		foodPanel.add(plusButton);
		foodPanel.add(singlePrice);
		foodPanel.add(totalPrice);
		listFoodPanel.add(foodPanel);
		
		foodPanel = new JPanel();
		foodPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
		trashButton = new JButton(new ImageIcon("./src/Icon/Trash.png"));
		idLabel = new JLabel("2.");
		nameFood = new JLabel("ShellFish Flatter");
		minusButton = new JButton(new ImageIcon("./src/Icon/Minus.png"));
		quantityLabel = new JLabel("1");
		plusButton = new JButton(new ImageIcon("./src/Icon/Plus.png"));
		singlePrice = new JLabel("155$");
		totalPrice = new JLabel("155$");
		foodPanel.add(trashButton);
		foodPanel.add(idLabel);
		foodPanel.add(nameFood);
		foodPanel.add(minusButton);
		foodPanel.add(quantityLabel);
		foodPanel.add(plusButton);
		foodPanel.add(singlePrice);
		foodPanel.add(totalPrice);
		listFoodPanel.add(foodPanel);
		
		sideFramePanel.add(nameTablePanel, BorderLayout.NORTH);
		sideFramePanel.add(listFoodPanel, BorderLayout.CENTER);
		setVisible(true);
	}
}
