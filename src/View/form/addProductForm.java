package View.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import Controller.DBConnection.DBConnection;
import net.miginfocom.swing.MigLayout;

public class addProductForm extends JFrame{
	private JPanel mainFramePanel;
	private JPanel title;
	JLabel formTitle;
	private JPanel formContent;
	private JLabel productID;
	private JTextField productIDField;
	private JLabel productName;
	private JTextField productNameField;
	private JLabel mass;
	private JTextField massField;
	private JLabel price;
	private JTextField priceField;
	private JPanel buttonField;
	private JButton saveBtn;
	private JButton cancelBtn;
	
	public addProductForm() {
		setSize(700, 700);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Restaurant Management System");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		mainFramePanel = new JPanel();
		add(mainFramePanel, BorderLayout.CENTER);
		mainFramePanel.setLayout(new FlowLayout(1, 0, 0));
		mainFramePanel.setBackground(Color.white);
		
		title = new JPanel();
		title.setPreferredSize(new Dimension(screenSize.width - 200, 100));
		title.setLayout(new FlowLayout(FlowLayout.CENTER, 250, 40));
		formTitle = new JLabel("Add new product");
		formTitle.setHorizontalAlignment(JLabel.LEFT);
		formTitle.setFont(title.getFont().deriveFont(Font.BOLD, 30));
		title.add(formTitle);
		title.setBackground(Color.white);
		
		formContent = new JPanel(new MigLayout("align 50%"));
		formContent.setPreferredSize(new Dimension(screenSize.width - 200, screenSize.height - 500));
		formContent.setBackground(Color.white);
		
		productID = new JLabel("Product's ID");
		productID.setHorizontalAlignment(JLabel.LEFT);
		productID.setFont(productID.getFont().deriveFont(Font.PLAIN, 20));		
		
		productIDField = new JTextField(20);
		productIDField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		productIDField.setFont(productIDField.getFont().deriveFont(Font.PLAIN, 20));
		productIDField.setEditable(false);
		String text = getProductID();
		productIDField.setText(text);
		
		productName = new JLabel("Product's name");
		productName.setHorizontalAlignment(JLabel.LEFT);
		productName.setFont(productName.getFont().deriveFont(Font.PLAIN, 20));
		
		productNameField = new JTextField(20);
		productNameField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		productNameField.setFont(productNameField.getFont().deriveFont(Font.PLAIN, 20));
		
		mass = new JLabel("Mass");
		mass.setHorizontalAlignment(JLabel.LEFT);
		mass.setFont(mass.getFont().deriveFont(Font.PLAIN, 20));
		
		massField = new JTextField(20);
		massField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		massField.setFont(productIDField.getFont().deriveFont(Font.PLAIN, 20));
		
		price = new JLabel("Price");
		price.setHorizontalAlignment(JLabel.LEFT);
		price.setFont(price.getFont().deriveFont(Font.PLAIN, 20));
		
		priceField = new JTextField(20);
		priceField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		priceField.setFont(priceField.getFont().deriveFont(Font.PLAIN, 20));
		
		formContent.add(productID);
		formContent.add(productIDField, "wrap 30");
		formContent.add(productName);
		formContent.add(productNameField, "wrap 30");
		formContent.add(mass);
		formContent.add(massField, "wrap 30");
		formContent.add(price);
		formContent.add(priceField);
		
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
		
		setUpButtonActions();
		
		setVisible(true);
	}
	
	public void setUpButtonActions() {
		ActionListener save = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				addProduct();
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
		
		saveBtn.addActionListener(save);
		cancelBtn.addActionListener(cancel);	
		
	}	
	
	public String getProductID() {
		String id = "";
		
		String cmd1 = "SELECT ProductID FROM Product WHERE ProductID = (SELECT max(ProductID) FROM Product)";
		
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			String temp = "";
			int length = 0;
			try {
				PreparedStatement ps = DBConnection.connection.prepareStatement(cmd1);
				ResultSet results = ps.executeQuery();
				while(results.next()) {
	     	   		temp = results.getString("ProductID");
	     	   		temp = temp.replaceAll("\\s+", "");
	     	   		length = temp.length();
	     	   		DBConnection.connection.commit();
	     	   	}
				results.close();
				int newTemp = Integer.parseInt(temp.replaceAll("\\D+",""));
				newTemp++;
				String zero = "";
				for(int i = 0; i < length - 1 - String.valueOf(newTemp).length(); i++) {
					zero += "0";
				}
				id += "P" + zero + String.valueOf(newTemp);
			} catch (SQLException e){
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
	
	public void addProduct() {
		String productID = productIDField.getText();
		String productName = productNameField.getText();
		
		int mass = 0;
		try {
			if(isNumeric(priceField.getText()))
				mass = Integer.parseInt(massField.getText());
		} catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Error");
		}
				
		int price = 0;
		try {
			if(isNumeric(priceField.getText()))
				price = Integer.parseInt(priceField.getText());
		} catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Error");
		}
		
		
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			try {
				String query = "{call addProduct(?, ?, ?, ?)}";
				CallableStatement cstmt = DBConnection.connection.prepareCall(query);
				
				cstmt.setString(1, productID);
				cstmt.setString(2, productName);
				cstmt.setInt(3, mass);
				cstmt.setInt(4, price);
				
				cstmt.execute();
				cstmt.close();
		
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		else {
			System.out.println("Failed");
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new addProductForm();
			}
		}); 
	}
	
}
