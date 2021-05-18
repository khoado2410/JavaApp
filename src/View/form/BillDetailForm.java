package View.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import Controller.ControlMenuTable.ControllerBill;
import Model.Staff_Manager.Staff;
import View.Frame.TableFrame;
import net.miginfocom.swing.MigLayout;

public class BillDetailForm extends JFrame implements ActionListener {
	private JPanel mainFramePanel;
	private JPanel title;
	private JLabel formTitle;
	private JPanel formContent;
	private JLabel billID;
	private JLabel billIDField;
	private JLabel foodName;
	private JTextArea foodNameField;
	private JLabel numFood;
	private JLabel numFoodField;
	private JLabel staffName;
	private JComboBox staffNameField;
	private JLabel rate;
	private JComboBox rateField;
	private JPanel buttonField;
	private JButton saveBtn;
	private JButton cancelBtn;
	private ControllerBill cb;
	public BillDetailForm(ControllerBill cb) {
		this.cb = cb;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(700, 700);
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
		formTitle = new JLabel("Bill detail");
		formTitle.setHorizontalAlignment(JLabel.LEFT);
		formTitle.setFont(title.getFont().deriveFont(Font.BOLD, 30));
		title.add(formTitle);
		title.setBackground(Color.white);

		formContent = new JPanel(new MigLayout("align 50%"));
		formContent.setPreferredSize(new Dimension(screenSize.width - 200, screenSize.height - 400));
		formContent.setBackground(Color.white);
		
		billID = new JLabel(cb.getBill().getBillID());
		billID.setHorizontalAlignment(JLabel.LEFT);
		billID.setFont(billID.getFont().deriveFont(Font.PLAIN, 20));
		
		billIDField = new JLabel("This is the content");
		billIDField.setHorizontalAlignment(JLabel.LEFT);
		billIDField.setFont(billIDField.getFont().deriveFont(Font.PLAIN, 20));
		
		foodName = new JLabel("List of dishes: ");
		foodName.setHorizontalAlignment(JLabel.LEFT);
		foodName.setFont(foodName.getFont().deriveFont(Font.PLAIN, 20));
		
		
		JPanel containerField = new JPanel(); 
		containerField.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0)); 
		foodNameField = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(foodNameField);
		scrollPane.setPreferredSize(new Dimension(330, 100));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		containerField.add(scrollPane); 
		foodNameField.setFont(foodNameField.getFont().deriveFont(Font.PLAIN, 20));
		containerField.setBackground(Color.red);
			
		numFood = new JLabel("The number of dishes: ");
		numFood.setHorizontalAlignment(JLabel.LEFT);
		numFood.setFont(numFood.getFont().deriveFont(Font.PLAIN, 20));
		
		numFoodField = new JLabel("This is the content");
		numFoodField.setHorizontalAlignment(JLabel.LEFT);
		numFoodField.setFont(numFoodField.getFont().deriveFont(Font.PLAIN, 20));
		
		staffName = new JLabel("Staff's name: ");
		staffName.setHorizontalAlignment(JLabel.LEFT);
		staffName.setFont(staffName.getFont().deriveFont(Font.PLAIN, 20));

		Staff s = new Staff();
		s.loadStaff();
		ArrayList<String> listNameStaff = new ArrayList<>();
		for (Staff k: s.getListStaff())
				listNameStaff.add(k.getStaffName());
		staffNameField = new JComboBox(listNameStaff.toArray());
		staffNameField.setPreferredSize(new Dimension(330, 40));
		staffNameField.setFont(staffNameField.getFont().deriveFont(Font.PLAIN, 20));
				
		rate = new JLabel("Rating: ");
		rate.setHorizontalAlignment(JLabel.LEFT);
		rate.setFont(rate.getFont().deriveFont(Font.PLAIN, 20));

		String temp[] = {"1", "2", "3", "4", "5"};
		rateField = new JComboBox(temp);
		rateField.setPreferredSize(new Dimension(330, 40));
		rateField.setFont(rateField.getFont().deriveFont(Font.PLAIN, 20));
		
		formContent.add(billID);
		formContent.add(billIDField, "wrap 30");
		formContent.add(foodName);
		formContent.add(containerField, "wrap 30");
		formContent.add(numFood);
		formContent.add(numFoodField, "wrap 30");
		formContent.add(staffName);
		formContent.add(staffNameField, "wrap 30");
		formContent.add(rate);
		formContent.add(rateField, "wrap 30");
		
		buttonField = new JPanel(new MigLayout("align 50%"));
		buttonField.setPreferredSize(new Dimension(screenSize.width - 200, 100));
		buttonField.setBackground(Color.white);

		saveBtn = new JButton("Pay");
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
		switch(e.getActionCommand()) {
		case "Pay":
			cb.saveBillToDB(staffNameField.getSelectedItem().toString(), Integer.parseInt(rateField.getSelectedItem().toString()));
			this.dispose();
			new TableFrame();
			break;
		case "Cancel":
			this.dispose();
			break;
		}
		
	}
}

