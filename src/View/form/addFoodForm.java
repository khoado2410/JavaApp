package View.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import net.miginfocom.swing.MigLayout;
import Controller.DBConnection.DBConnection;

public class addFoodForm extends JFrame implements ActionListener {
	private JPanel mainFramePanel;
	private JPanel title;
	JLabel formTitle;
	private JPanel formContent;
	private JLabel foodID;
	private JTextField foodIDField;
	private JLabel foodName;
	private JTextField foodNameField;
	private JLabel price;
	private JTextField priceField;
	private JLabel foodType;
	private JComboBox foodTypeField;
	private JLabel ingredient;
	private JComboBox ingredientField;
	private JLabel quantity;
	private JTextField quantityField;
	private JLabel image;
	private JButton imageBtn;
	private JButton addIngredient;
	private JPanel buttonField;
	private JButton saveBtn;
	private JButton cancelBtn;
	private String imgPath;
	private ArrayList<String> ingredientList;

	public addFoodForm() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(700, screenSize.height);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setTitle("Restaurant Management System");

		mainFramePanel = new JPanel();
		add(mainFramePanel, BorderLayout.CENTER);
		mainFramePanel.setLayout(new FlowLayout(1, 0, 0));
		mainFramePanel.setBackground(Color.white);

		title = new JPanel();
		title.setPreferredSize(new Dimension(screenSize.width - 200, 100));
		title.setLayout(new FlowLayout(FlowLayout.CENTER, 250, 40));
		formTitle = new JLabel("Add new food");
		formTitle.setHorizontalAlignment(JLabel.LEFT);
		formTitle.setFont(title.getFont().deriveFont(Font.BOLD, 30));
		title.add(formTitle);
		title.setBackground(Color.white);

		formContent = new JPanel(new MigLayout("align 50%"));
		formContent.setPreferredSize(new Dimension(screenSize.width - 200, screenSize.height - 250));
		formContent.setBackground(Color.white);

		foodID = new JLabel("Food's ID");
		foodID.setHorizontalAlignment(JLabel.LEFT);
		foodID.setFont(foodID.getFont().deriveFont(Font.PLAIN, 20));

		foodIDField = new JTextField(20);
		foodIDField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		foodIDField.setFont(foodIDField.getFont().deriveFont(Font.PLAIN, 20));
		foodIDField.setEditable(false);
		String text = getFoodID();
		foodIDField.setText(text);

		foodName = new JLabel("Food's name");
		foodName.setHorizontalAlignment(JLabel.LEFT);
		foodName.setFont(foodName.getFont().deriveFont(Font.PLAIN, 20));

		foodNameField = new JTextField(20);
		foodNameField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		foodNameField.setFont(foodNameField.getFont().deriveFont(Font.PLAIN, 20));

		price = new JLabel("Price");
		price.setHorizontalAlignment(JLabel.LEFT);
		price.setFont(price.getFont().deriveFont(Font.PLAIN, 20));

		priceField = new JTextField(20);
		priceField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		priceField.setFont(priceField.getFont().deriveFont(Font.PLAIN, 20));

		foodType = new JLabel("Type of food");
		foodType.setHorizontalAlignment(JLabel.LEFT);
		foodType.setFont(foodType.getFont().deriveFont(Font.PLAIN, 20));

		foodTypeField = new JComboBox();
		foodTypeField.setPreferredSize(new Dimension(330, 40));
		foodTypeField.setFont(foodTypeField.getFont().deriveFont(Font.PLAIN, 20));

		quantity = new JLabel("Quantity");
		quantity.setHorizontalAlignment(JLabel.LEFT);
		quantity.setFont(price.getFont().deriveFont(Font.PLAIN, 20));

		quantityField = new JTextField(20);
		quantityField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		quantityField.setFont(quantityField.getFont().deriveFont(Font.PLAIN, 20));

		image = new JLabel("Image");
		image.setHorizontalAlignment(JLabel.LEFT);
		image.setFont(image.getFont().deriveFont(Font.PLAIN, 20));

		imageBtn = new JButton("Choose a image");
		imageBtn.setPreferredSize(new Dimension(330, 40));
		imageBtn.setFont(imageBtn.getFont().deriveFont(Font.PLAIN, 20));
		imageBtn.setMaximumSize(new Dimension(330, 40));

		ingredient = new JLabel("Ingredient");
		ingredient.setHorizontalAlignment(JLabel.LEFT);
		ingredient.setFont(ingredient.getFont().deriveFont(Font.PLAIN, 20));

		ingredientField = new JComboBox();
		ingredientField.setPreferredSize(new Dimension(330, 40));
		ingredientField.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXXXXXX");
		ingredientField.setFont(ingredient.getFont().deriveFont(Font.PLAIN, 20));

		addIngredient = new JButton("+");
		addIngredient.setBackground(Color.WHITE);
		addIngredient.setForeground(Color.BLACK);
		addIngredient.setPreferredSize(new Dimension(40, 40));
		addIngredient.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));
		addIngredient.setFont(addIngredient.getFont().deriveFont(Font.BOLD, 35));

		formContent.add(foodID);
		formContent.add(foodIDField, "wrap 30");
		formContent.add(foodName);
		formContent.add(foodNameField, "wrap 30");
		formContent.add(price);
		formContent.add(priceField, "wrap 30");
		formContent.add(foodType);
		formContent.add(foodTypeField, "wrap 30");
		formContent.add(quantity);
		formContent.add(quantityField, "wrap 30");
		formContent.add(image);
		formContent.add(imageBtn, "wrap 30");
		formContent.add(ingredient);
		formContent.add(ingredientField, "split");
		formContent.add(addIngredient, "wrap 30");

		buttonField = new JPanel(new MigLayout("align 50%"));
		buttonField.setPreferredSize(new Dimension(screenSize.width - 200, 100));
		buttonField.setBackground(Color.white);

		saveBtn = new JButton("Save");
		saveBtn.setBackground(Color.PINK);
		saveBtn.setForeground(Color.BLACK);
		saveBtn.setPreferredSize(new Dimension(100, 40));
		saveBtn.setFont(saveBtn.getFont().deriveFont(Font.PLAIN, 20));

		cancelBtn = new JButton("Cancel");
		cancelBtn.setBackground(Color.BLACK);
		cancelBtn.setForeground(Color.WHITE);
		cancelBtn.setPreferredSize(new Dimension(100, 40));
		cancelBtn.setFont(saveBtn.getFont().deriveFont(Font.PLAIN, 20));

		buttonField.add(saveBtn);
		buttonField.add(Box.createHorizontalStrut(150));
		buttonField.add(cancelBtn);

		mainFramePanel.add(title);
		mainFramePanel.add(formContent);
		mainFramePanel.add(buttonField);

		loadFoodType();
		loadIngredients(ingredientField);
		setUpButtonActions();

		setVisible(true);
	}

	public void setUpButtonActions() {
		ActionListener save = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				addFood();
				String foodID = foodIDField.getText();
				String productName = (String) ingredientField.getSelectedItem();
				System.out.println(foodID + " " + productName);
				addIngredient(foodID, productName);
				if (ingredientList.size() > 0) {
					for (int i = 0; i < ingredientList.size(); i++) {
						addIngredient(foodID, ingredientList.get(i));
					}
				}
				JOptionPane.showMessageDialog(null, "Success");
				dispose();
			}
		};

		ActionListener cancel = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				dispose();
			}
		};

		ActionListener chooseImg = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				JFileChooser f = new JFileChooser(FileSystemView.getFileSystemView());
				int r = f.showSaveDialog(null);
				if (r == JFileChooser.APPROVE_OPTION) {
					imgPath = f.getSelectedFile().getPath();
					imageBtn.setText(imgPath);
				}

			}
		};

		ActionListener add = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				JLabel newIngre = new JLabel("Ingredient");
				newIngre.setHorizontalAlignment(JLabel.LEFT);
				newIngre.setFont(newIngre.getFont().deriveFont(Font.PLAIN, 20));

				JComboBox newIngreField = new JComboBox();
				newIngreField.setPreferredSize(new Dimension(330, 40));
				newIngreField.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXXXXXX");
				newIngreField.setFont(newIngreField.getFont().deriveFont(Font.PLAIN, 20));
				loadIngredients(newIngreField);

				addIngredient = new JButton("+");
				addIngredient.setBackground(Color.WHITE);
				addIngredient.setForeground(Color.BLACK);
				addIngredient.setPreferredSize(new Dimension(40, 40));
				addIngredient.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));
				addIngredient.setFont(newIngre.getFont().deriveFont(Font.BOLD, 35));

				formContent.add(newIngre);
				formContent.add(newIngreField, "split");
				formContent.add(addIngredient, "wrap 30");

				mainFramePanel.add(title);
				mainFramePanel.add(formContent);
				mainFramePanel.add(buttonField);

				setUpButtonActions();

				ItemListener getItem = new ItemListener() {

					@Override
					public void itemStateChanged(ItemEvent e) {
						// TODO Auto-generated method stub
						ingredientList = new ArrayList<String>();

						if (e.getStateChange() == ItemEvent.SELECTED) {
							String ingre = (String) e.getItem();
							ingredientList.add(ingre);
						}

					}

				};

				newIngreField.addItemListener(getItem);

				setVisible(true);
			}
		};

		saveBtn.addActionListener(save);
		cancelBtn.addActionListener(cancel);
		imageBtn.addActionListener(chooseImg);
		addIngredient.addActionListener(add);

	}

	public void loadFoodType() {
		String cmd1 = "SELECT FoodTypeName from FoodType";
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {			try {
				PreparedStatement ps = DBConnection.connection.prepareStatement(cmd1);
				ResultSet results = ps.executeQuery();
				while (results.next()) {
					String foodTypeName = results.getString("FoodTypeName");
					foodTypeField.addItem(foodTypeName);
					DBConnection.connection.commit();
				}
				results.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void loadIngredients(JComboBox field) {
		String cmd1 = "SELECT ProductName from Product";
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {			
			try {
				PreparedStatement ps = DBConnection.connection.prepareStatement(cmd1);
				ResultSet results = ps.executeQuery();
				while (results.next()) {
					String ingre = results.getString("ProductName");
					field.addItem(ingre);
					DBConnection.connection.commit();
				}
				results.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public String getFoodID() {
		String id = "";

		String cmd1 = "SELECT FoodID FROM Menu WHERE FoodID = (SELECT max(FoodID) FROM Menu)";

		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			String temp = "";
			int length = 0;
			try {
				PreparedStatement ps = DBConnection.connection.prepareStatement(cmd1);
				ResultSet results = ps.executeQuery();
				while (results.next()) {
					temp = results.getString("FoodID");
					temp = temp.replaceAll("\\s+", "");
					length = temp.length();
					DBConnection.connection.commit();
				}
				results.close();
				int newTemp = Integer.parseInt(temp.replaceAll("\\D+", ""));
				newTemp++;
				String zero = "";
				System.out.println(temp.replaceAll("\\D+", "").length());
				for (int i = 0; i < length - 1 - String.valueOf(newTemp).length(); i++) {
					zero += "0";
				}
				id += "F" + zero + String.valueOf(newTemp);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return id;
	}

	public static boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			int d = Integer.parseInt(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public void addFood() {
		String foodID = foodIDField.getText();
		String foodName = foodNameField.getText();

		int price = 0;
		try {
			if (isNumeric(priceField.getText()))
				price = Integer.parseInt(priceField.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Error");
		}

		String foodType = (String) foodTypeField.getSelectedItem();
		int quantity = 0;
		try {
			if (isNumeric(quantityField.getText()))
				quantity = Integer.parseInt(quantityField.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Error");
		}

		String img = imgPath;

		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {			
			try {
				String query = "{call addFood(?, ?, ?, ?, ?, ?)}";
				CallableStatement cstmt = DBConnection.connection.prepareCall(query);

				cstmt.setString(1, foodID);
				cstmt.setString(2, foodName);
				cstmt.setInt(3, price);
				cstmt.setString(4, foodType);
				cstmt.setInt(5, quantity);
				cstmt.setString(6, img);

				cstmt.execute();
				cstmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("Failed");
		}
	}

	public void addIngredient(String foodID, String productName) {
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			try {
				String query = "{call addIngredient(?, ?)}";
				CallableStatement cstmt = DBConnection.connection.prepareCall(query);

				cstmt.setString(1, foodID);
				cstmt.setString(2, productName);

				cstmt.execute();
				cstmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("Failed");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new addFoodForm();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}


}