package View.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Controller.ManageStaff.ControllerManageStaff;
import net.miginfocom.swing.MigLayout;

public class editStaffForm extends JFrame implements ActionListener{
	
	private ControllerManageStaff controllerStaff = new ControllerManageStaff(this);
	
	
	private JPanel mainFramePanel;
	private JPanel title;
	JLabel formTitle;
	private JPanel formContent;
	private JLabel staffID;
	public JTextField staffIDField;
	private JLabel staffName;
	public JTextField staffNameField;
	private JLabel dateOfBirth;
	public JTextField dateOfBirthField;
	private JLabel gender;
	private JLabel male;
	public JCheckBox maleCheck;
	private JLabel female;
	public JCheckBox femaleCheck;
	private JLabel address;
	public JTextField addressField;
	private JLabel salary;
	public JTextField salaryField;
	private JLabel point;
	public JTextField pointField;
	private JPanel buttonField;
	private JButton saveBtn;
	private JButton cancelBtn;
	
	public editStaffForm(String id, String name1, String add1, String gender1, String dob, String sal, String point1) {
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
		formTitle = new JLabel("Edit new staff");
		formTitle.setHorizontalAlignment(JLabel.LEFT);
		formTitle.setFont(title.getFont().deriveFont(Font.BOLD, 30));
		title.add(formTitle);
		title.setBackground(Color.white);
		
		formContent = new JPanel(new MigLayout("align 50%"));
		formContent.setPreferredSize(new Dimension(screenSize.width - 200, screenSize.height - 400));
		formContent.setBackground(Color.white);

		staffID = new JLabel("Staff's ID");
		staffID.setHorizontalAlignment(JLabel.LEFT);
		staffID.setFont(staffID.getFont().deriveFont(Font.PLAIN, 20));
	
		
		staffIDField = new JTextField(20);
		staffIDField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		staffIDField.setFont(staffIDField.getFont().deriveFont(Font.PLAIN, 20));
		staffIDField.setText(id);
		
		staffName = new JLabel("Staff's name");
		staffName.setHorizontalAlignment(JLabel.LEFT);
		staffName.setFont(staffName.getFont().deriveFont(Font.PLAIN, 20));
		
		staffNameField = new JTextField(20);
		staffNameField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		staffNameField.setFont(staffNameField.getFont().deriveFont(Font.PLAIN, 20));
		staffNameField.setText(name1);
		
		dateOfBirth = new JLabel("Date of birth");
		dateOfBirth.setHorizontalAlignment(JLabel.LEFT);
		dateOfBirth.setFont(dateOfBirth.getFont().deriveFont(Font.PLAIN, 20));
		
		dateOfBirthField = new JTextField(20);
		dateOfBirthField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		dateOfBirthField.setFont(dateOfBirthField.getFont().deriveFont(Font.PLAIN, 20));
		dateOfBirthField.setText(dob);
		
		gender = new JLabel("Gender");
		gender.setHorizontalAlignment(JLabel.LEFT);
		gender.setFont(gender.getFont().deriveFont(Font.PLAIN, 20));
		male = new JLabel("Male");
		male.setHorizontalAlignment(JLabel.LEFT);
		male.setFont(male.getFont().deriveFont(Font.PLAIN, 20));
		female = new JLabel("Female");
		female.setHorizontalAlignment(JLabel.LEFT);
		female.setFont(female.getFont().deriveFont(Font.PLAIN, 20));
		maleCheck = new JCheckBox();
		maleCheck.setBackground(Color.white);
		femaleCheck = new JCheckBox();
		femaleCheck.setBackground(Color.white);
		
		address = new JLabel("Address");
		address.setHorizontalAlignment(JLabel.LEFT);
		address.setFont(address.getFont().deriveFont(Font.PLAIN, 20));
		
		addressField = new JTextField(20);
		addressField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		addressField.setFont(addressField.getFont().deriveFont(Font.PLAIN, 20));
		addressField.setText(add1);
		
		salary = new JLabel("Salary");
		salary.setHorizontalAlignment(JLabel.LEFT);
		salary.setFont(salary.getFont().deriveFont(Font.PLAIN, 20));
		
		salaryField = new JTextField(20);
		salaryField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		salaryField.setFont(salaryField.getFont().deriveFont(Font.PLAIN, 20));
		salaryField.setText(sal);
		
		point = new JLabel("Point");
		point.setHorizontalAlignment(JLabel.LEFT);
		point.setFont(point.getFont().deriveFont(Font.PLAIN, 20));
		
		pointField = new JTextField(20);
		pointField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		pointField.setFont(pointField.getFont().deriveFont(Font.PLAIN, 20));
		pointField.setText(point1);
		
		formContent.add(staffID);
		formContent.add(staffIDField, "wrap 30");
		formContent.add(staffName);
		formContent.add(staffNameField, "wrap 30");
		formContent.add(dateOfBirth);
		formContent.add(dateOfBirthField, "wrap 30");
		formContent.add(gender);
		formContent.add(maleCheck, "split");
		formContent.add(male);
		formContent.add(femaleCheck);
		formContent.add(female, "wrap 30");
		formContent.add(address);
		formContent.add(addressField, "wrap 30");
		formContent.add(salary);
		formContent.add(salaryField, "wrap 30");
		formContent.add(point);
		formContent.add(pointField);
		
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

	@Override
	public void actionPerformed(ActionEvent e) {
			String com = e.getActionCommand().toString();
			if(com.equals("Save")) {
				this.controllerStaff.updatStaff();
				//this.controller.updateFood();
				//controllerAddFood.addProductAndFoodToFoodDetailAndMenu();
							
			}
			else if(com.equals("Cancel")) {
				this.dispose();
			}else if(com.equals("Choose a image")) {
				//this.controller.OpenFileImageEditForm();
				//controllerAddFood.OpenFileImage();
			}else if(com.equals("Choose ingredient")) {
		//		addIngre ingre = new addIngre("edit");
				
			}
		
	}
	
	


}
