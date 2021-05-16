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
import Controller.MenuAndProduct.ManageMenuAndProduct;
import Model.Food_Product.Food;
import View.Frame.Product_MenuManagementUI;

public class addFoodForm extends JFrame implements ActionListener {
	
	public ManageMenuAndProduct controllerAddFood = new ManageMenuAndProduct(this);
	public static ArrayList<String> listFoodName = new ArrayList<String>();
	
	public JTextField foodIDField;
	public JButton imageBtn;
	public JTextField foodNameField;
	public JTextField priceField;
	public JComboBox foodTypeField;
	public JTextField quantityField;
	public static JLabel ingres;
	public static JLabel ingresL;
	
	public static JScrollPane jresult;
	private JPanel mainFramePanel;
	private JPanel title;
	
	JLabel formTitle;
	private JPanel formContent;
	private JLabel foodID;
	private JLabel foodName;
	
	
	private JLabel price;
	private JLabel foodType;
	private JLabel ingredient;
	private JComboBox ingredientField;
	private JLabel quantity;
	private JLabel image;
	private JButton addIngredient;
	private JPanel buttonField;
	private JButton saveBtn;
	private JButton cancelBtn;
	public String imgPath;
	
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
		String text = this.controllerAddFood.getIDFoodMax();
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
		
		this.controllerAddFood.getAllNameFoodTypeToCombobox();

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
		
		imageBtn.addActionListener(this);

		ingredient = new JLabel("Ingredient");
		ingredient.setHorizontalAlignment(JLabel.LEFT);
		ingredient.setFont(ingredient.getFont().deriveFont(Font.PLAIN, 20));

		ingres = new JLabel("List of ingredients");
		ingres.setHorizontalAlignment(JLabel.CENTER);
		ingres.setFont(ingres.getFont().deriveFont(Font.PLAIN, 20));
		
		ingresL = new JLabel();
		ingresL.setHorizontalAlignment(JLabel.CENTER);
		ingresL.setFont(ingres.getFont().deriveFont(Font.PLAIN, 20));
		
		jresult = new JScrollPane(ingresL, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jresult.setPreferredSize(new Dimension(340, 200));
		
//		ingredientField = new JComboBox();
//		ingredientField.setPreferredSize(new Dimension(330, 40));
//		ingredientField.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXXXXXX");
//		ingredientField.setFont(ingredient.getFont().deriveFont(Font.PLAIN, 20));

		
		addIngredient = new JButton("Choose ingredient");
		addIngredient.setPreferredSize(new Dimension(330, 40));
		addIngredient.setFont(imageBtn.getFont().deriveFont(Font.PLAIN, 20));
		addIngredient.setMaximumSize(new Dimension(330, 40));
		
		addIngredient.addActionListener(this);

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
		
		buttonField = new JPanel(new MigLayout("align 50%"));
		buttonField.setPreferredSize(new Dimension(screenSize.width - 200, 100));
		buttonField.setBackground(Color.white);

		saveBtn = new JButton("Save");
		saveBtn.setBackground(Color.PINK);
		saveBtn.setForeground(Color.BLACK);
		saveBtn.setPreferredSize(new Dimension(100, 40));
		saveBtn.setFont(saveBtn.getFont().deriveFont(Font.PLAIN, 20));
		
		saveBtn.addActionListener(this);

		cancelBtn = new JButton("Cancel");
		cancelBtn.setBackground(Color.BLACK);
		cancelBtn.setForeground(Color.WHITE);
		cancelBtn.setPreferredSize(new Dimension(100, 40));
		cancelBtn.setFont(saveBtn.getFont().deriveFont(Font.PLAIN, 20));
		
		cancelBtn.addActionListener(this);
		
		buttonField.add(saveBtn);
		buttonField.add(Box.createHorizontalStrut(150));
		buttonField.add(cancelBtn);

		mainFramePanel.add(title);
		mainFramePanel.add(formContent);
		mainFramePanel.add(buttonField);

		
		setVisible(true);
	}
	
	public static void ListIngredient(ArrayList<String> a) {
		listFoodName = a;
		String list = "";
		for(String s: a) {
			list+=s+", ";
		}
		ingresL.setText(list);
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
		
		String com = e.getActionCommand().toString();
		if(com.equals("Save")) {
			if(this.controllerAddFood.controllerAddNewFood()) {
				
				JOptionPane.showMessageDialog(this, "Vui lòng thêm thành phần cho món ăn");
				//if(input == 0) {
					addIngre ingre = new addIngre("edit", this.foodIDField.getText());
			//	}
				
			}
						
		}
		else if(com.equals("Cancel")) {
			this.dispose();
		}else if(com.equals("Choose a image")) {
			controllerAddFood.OpenFileImage();
		}else if(com.equals("Choose ingredient")) {
			//addIngre ingre = new addIngre("add");
			
		}
	}

	private addIngre addIngre(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}