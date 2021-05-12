package Frame;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TableFrame extends JFrame {
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
	private JPanel tablePanel;
	private JPanel nameTablePanel;
	private JPanel listFoodPanel;
	private JPanel foodPanel;
	private JLabel tableName;

	public TableFrame() {
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Restaurant Management System");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setExtendedState(JFrame.MAXIMIZED_BOTH);

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
		numTableUsedLabel = new JLabel("Use 0/0");
		tableUsedAreaJPanel.add(numTableUsedLabel);
		listTableJPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));

		for (int i = 0; i < 20; i++) {
			tablePanel = new JPanel();
			tablePanel.setLayout(new BorderLayout());
			tablePanel.setPreferredSize(new Dimension(80, 80));
			JButton resButton = new JButton();
			ImageIcon resIcon = new ImageIcon("./src/Icon/restaurant.png");
			resButton.setIcon(resIcon);
			resButton.setBackground(Color.WHITE);
			JLabel nameTableLabel = new JLabel("", SwingConstants.CENTER);
			tablePanel.add(resButton, BorderLayout.CENTER);
			tablePanel.add(nameTableLabel, BorderLayout.SOUTH);
			nameTableLabel.setText("Table " + (i + 1));
			listTableJPanel.add(tablePanel);
		}

		nameTablePanel = new JPanel();
		tableName = new JLabel("Table 1");
		nameTablePanel.add(tableName);
		listFoodPanel = new JPanel();
		listFoodPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		for (int i = 0; i < 3; i++) {
			foodPanel = new JPanel();
			foodPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
			JButton trashButton = new JButton(new ImageIcon("./src/Icon/Trash.png"));
			JLabel idLabel = new JLabel(Integer.toString(i + 1) + ".");
			JLabel nameFood = new JLabel("Fried Chicken");
			JButton minusButton = new JButton(new ImageIcon("./src/Icon/Minus.png"));
			JLabel quantityLabel = new JLabel("1");
			JButton plusButton = new JButton(new ImageIcon("./src/Icon/Plus.png"));
			JLabel singlePrice = new JLabel("12$");
			JLabel totalPrice = new JLabel("12$");
			foodPanel.add(trashButton);
			foodPanel.add(idLabel);
			foodPanel.add(nameFood);
			foodPanel.add(minusButton);
			foodPanel.add(quantityLabel);
			foodPanel.add(plusButton);
			foodPanel.add(singlePrice);
			foodPanel.add(totalPrice);
			listFoodPanel.add(foodPanel);
		}

		sideFramePanel.add(nameTablePanel, BorderLayout.NORTH);
		sideFramePanel.add(listFoodPanel, BorderLayout.CENTER);
		setVisible(true);
	}
<<<<<<< HEAD
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new TableFrame();
					} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
=======
	public static void main(String[] args) {
		new TableFrame();
>>>>>>> c408d838404aacb74d2fe54da9f0bf0cad00b426
	}
}

