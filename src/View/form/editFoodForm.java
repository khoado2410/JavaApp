package View.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

public class editFoodForm extends JFrame{
	private JPanel mainFramePanel;
	private JPanel title;
	JLabel formTitle;
	private JPanel formContent;
	private JLabel foodID;
	private JTextField foodIDField;
	private JLabel foodName;
	private JTextField foodNameField;
	private JLabel amount;
	private JTextField amountField;
	private JLabel ingredient;
	private JComboBox ingredientField;
	private JButton addIngredient;
	private JPanel buttonField;
	private JButton saveBtn;
	private JButton cancelBtn;
	
	public editFoodForm() {
		setSize(700, 700);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Restaurant Management System");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		mainFramePanel = new JPanel();
		add(mainFramePanel, BorderLayout.CENTER);
		mainFramePanel.setLayout(new FlowLayout(1, 0, 0));
		mainFramePanel.setBackground(Color.white);
		
		title = new JPanel();
		title.setPreferredSize(new Dimension(screenSize.width - 200, 100));
		title.setLayout(new FlowLayout(FlowLayout.CENTER, 250, 40));
		formTitle = new JLabel("Edit food");
		formTitle.setHorizontalAlignment(JLabel.LEFT);
		formTitle.setFont(title.getFont().deriveFont(Font.BOLD, 30));
		title.add(formTitle);
		title.setBackground(Color.white);
		
		formContent = new JPanel(new MigLayout("align 50%"));
		formContent.setPreferredSize(new Dimension(screenSize.width - 200, screenSize.height - 500));
		formContent.setBackground(Color.white);
		
		foodID = new JLabel("Product's ID");
		foodID.setHorizontalAlignment(JLabel.LEFT);
		foodID.setFont(foodID.getFont().deriveFont(Font.PLAIN, 20));
		
		foodIDField = new JTextField(20);
		foodIDField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		foodIDField.setFont(foodIDField.getFont().deriveFont(Font.PLAIN, 20));
		
		foodName = new JLabel("Product's name");
		foodName.setHorizontalAlignment(JLabel.LEFT);
		foodName.setFont(foodName.getFont().deriveFont(Font.PLAIN, 20));
		
		foodNameField = new JTextField(20);
		foodNameField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		foodNameField.setFont(foodNameField.getFont().deriveFont(Font.PLAIN, 20));
		
		amount = new JLabel("Mass");
		amount.setHorizontalAlignment(JLabel.LEFT);
		amount.setFont(amount.getFont().deriveFont(Font.PLAIN, 20));
		
		amountField = new JTextField(20);
		amountField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		amountField.setFont(foodIDField.getFont().deriveFont(Font.PLAIN, 20));
		
		ingredient = new JLabel("Ingredient");
		ingredient.setHorizontalAlignment(JLabel.LEFT);
		ingredient.setFont(ingredient.getFont().deriveFont(Font.PLAIN, 20));
		
		ingredientField = new JComboBox();
		ingredientField.setPreferredSize(new Dimension(330, 40));
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
		formContent.add(amount);
		formContent.add(amountField, "wrap 30");
		formContent.add(ingredient);
		formContent.add(ingredientField, "split");
		formContent.add(addIngredient);
		
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
		setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new editFoodForm();
			}
		}); 
	}
	
}
