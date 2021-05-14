package View.Frame;

import javax.swing.*;
import javax.swing.border.*;

import Controller.ControlMenuTable.ControllerMenu;
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
	private JPanel nameTablePanel;
	private JPanel listFoodPanel;
	private JLabel tableName;
	private ControllerPanel controllerPanel;
	private ControllerMenu controllerMenu;
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
		controllerPanel = new ControllerPanel(this);
		controllerPanel.setEventButton(tablesButton, tablesButton.getText());
		
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
		controllerPanel.setEventButton(homeButton, "Home");
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
		JPanel listMenuJPanel = new JPanel();
		JPanel coverPanel = new JPanel();
		listMenuJPanel.setLayout(new BorderLayout(0, 0));
		coverPanel.setPreferredSize(new Dimension(leftSide, heightMain + 500));
		listMenuJPanel.add(coverPanel, BorderLayout.CENTER);
		JScrollPane sp = new JScrollPane(coverPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		listMenuJPanel.add(sp);
		mainPanel.add(listMenuJPanel, BorderLayout.CENTER);
		
		listFoodPanel = new JPanel();
		controllerMenu = new ControllerMenu(listFoodPanel);
		controllerMenu.loadListMenu(coverPanel);

		nameTablePanel = new JPanel();
		tableName = new JLabel("Table 1");
		nameTablePanel.add(tableName);
		
		listFoodPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		sideFramePanel.add(nameTablePanel, BorderLayout.NORTH);
		sideFramePanel.add(listFoodPanel, BorderLayout.CENTER);
		setVisible(true);
	}
}
