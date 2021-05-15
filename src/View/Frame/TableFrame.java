package View.Frame;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Controller.ControlMenuTable.ControllerTable;
import Controller.PanelChange.ControllerPanel;

public class TableFrame extends JPanel {
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
	private JLabel numTableUsedLabel;
	private JPanel nameTablePanel;
	private JPanel listFoodPanel;
	private JLabel tableName;
	private ControllerPanel controller;
	private ControllerTable controllerTable;
	private JPanel coverPanel;
	public TableFrame() {
		setLayout(new BorderLayout());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

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
		tablesButton.setBackground(Color.PINK);
		tablesButton.setFont(tablesButton.getFont().deriveFont(Font.BOLD, 20));

		menuButton = new JButton("Menu");
		menuButton.setBackground(Color.BLACK);
		menuButton.setForeground(Color.PINK);
		menuButton.setFont(tablesButton.getFont().deriveFont(Font.BOLD, 20));
		controller = new ControllerPanel(this);
		controller.setEventButton(menuButton, menuButton.getText());
		
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
		typePanel.setLayout(new BorderLayout());
		JLabel type = new JLabel();
		type.setBorder(new EmptyBorder(0, 50, 10, 0));
		type.setText("All");
		type.setFont(type.getFont().deriveFont(Font.BOLD, 18));
		typePanel.add(type, BorderLayout.CENTER);
		homeButtonArea.setLayout(new GridLayout(2, 1));
		homeButtonArea.add(homeButton);
		homeButtonArea.setPreferredSize(new Dimension(50, heightSub));
		subPanel.add(homeButtonArea, BorderLayout.WEST);
		subPanel.add(typePanel, BorderLayout.CENTER);

		mainPanel.setLayout(new BorderLayout(0, 0));
		JPanel tableUsedAreaJPanel = new JPanel();
		JPanel listTableJPanel = new JPanel();
		mainPanel.add(tableUsedAreaJPanel, BorderLayout.NORTH);
		mainPanel.add(listTableJPanel, BorderLayout.CENTER);
		
		numTableUsedLabel = new JLabel();
		tableUsedAreaJPanel.add(numTableUsedLabel);
		listTableJPanel.setLayout(new BorderLayout(0, 0));
		coverPanel = new JPanel();
		coverPanel.setPreferredSize(new Dimension(leftSide, heightMain + 500));
		JScrollPane sp = new JScrollPane(coverPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		listTableJPanel.add(sp, BorderLayout.CENTER);
		
		nameTablePanel = new JPanel();
		listFoodPanel = new JPanel();
		tableName = new JLabel();
		
		controllerTable = new ControllerTable(numTableUsedLabel, coverPanel, tableName, listFoodPanel);
		controllerTable.loadListTable();
		
		nameTablePanel.add(tableName);
		
		listFoodPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		sideFramePanel.add(nameTablePanel, BorderLayout.NORTH);
		sideFramePanel.add(listFoodPanel, BorderLayout.CENTER);
		setVisible(true);
	}
}
