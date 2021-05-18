package View.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import Controller.CashBook.ControllerCashBook;
import Controller.ManageStaff.ControllerManageStaff;
import Model.Food_Product.Food;
import View.Frame.Staff_ManagerStaffUI;
import net.miginfocom.swing.MigLayout;

public class scheduleShift extends JFrame implements ActionListener{
	
	private ControllerManageStaff controllerStaff = new ControllerManageStaff(this);
	
	private JPanel mainFramePanel;
	private JPanel title;
	JLabel formTitle;
	private JPanel formContent;
	private JLabel staffName;
	public JComboBox staffNameField;
	private JLabel hourstartLabel;
	public JComboBox hourStart;
	private JLabel hoursEndLabel;
	private JLabel manageID;
	public JComboBox manageAccount;
	
	private JLabel dateWorkingLabel;
	public JLabel dateworkField;
	
	public JComboBox hourEnd;
	
	private JPanel buttonField;
	
	private JButton saveBtn;
	private JButton cancelBtn;
	

	
	public scheduleShift() {
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
		formTitle = new JLabel("Add new shift for staff");
		formTitle.setHorizontalAlignment(JLabel.LEFT);
		formTitle.setFont(title.getFont().deriveFont(Font.BOLD, 30));
		title.add(formTitle);
		title.setBackground(Color.white);
		
		formContent = new JPanel(new MigLayout("align 50%"));
		formContent.setPreferredSize(new Dimension(screenSize.width - 200, screenSize.height - 400));
		formContent.setBackground(Color.white);

		this.dateWorkingLabel = new JLabel("Date working: ");
		this.dateWorkingLabel.setHorizontalAlignment(JLabel.LEFT);
		this.dateWorkingLabel.setFont(this.dateWorkingLabel.getFont().deriveFont(Font.PLAIN, 20));		
		
		this.dateworkField = new JLabel();
		this.dateworkField.setPreferredSize(new Dimension(330, 40));
		this.dateworkField.setFont(this.dateworkField.getFont().deriveFont(Font.PLAIN, 20));
		String querySql = "{call addEditHistory(?, ?, ?, ?)}";
		java.util.Date date= java.util.Calendar.getInstance().getTime(); 
		DateFormat dateFormatMDY = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		String vDateYMD = dateFormatMDY.format(date);
		this.dateworkField.setText(vDateYMD);
		
		this.manageID = new JLabel("Manage ID: ");
		this.manageID.setHorizontalAlignment(JLabel.LEFT);
		this.manageID.setFont(this.manageID.getFont().deriveFont(Font.PLAIN, 20));		
		
		this.manageAccount = new JComboBox();
		this.manageAccount.setPreferredSize(new Dimension(330, 40));
		this.manageAccount.setFont(this.manageAccount.getFont().deriveFont(Font.PLAIN, 20));
		
		this.controllerStaff.getIDAccToCombobox();
		
		staffName = new JLabel("Staff Name: ");
		staffName.setHorizontalAlignment(JLabel.LEFT);
		staffName.setFont(staffName.getFont().deriveFont(Font.PLAIN, 20));		
		
		staffNameField = new JComboBox();
		staffNameField.setPreferredSize(new Dimension(330, 40));
		staffNameField.setFont(staffNameField.getFont().deriveFont(Font.PLAIN, 20));
		
		this.controllerStaff.getAllNameStaffToCombobox();
		
		
		this.hourstartLabel = new JLabel("Hour start: ");
		hourstartLabel.setHorizontalAlignment(JLabel.LEFT);
		hourstartLabel.setFont(hourstartLabel.getFont().deriveFont(Font.PLAIN, 20));	
		
		this.hourStart = new JComboBox();
		this.hourStart.setPreferredSize(new Dimension(330, 40));
		this.hourStart.setFont(this.hourStart.getFont().deriveFont(Font.PLAIN, 20));
		
		for(int i = 0; i < 24; i++) {
			this.hourStart.addItem(i);
		}
		
		this.hoursEndLabel = new JLabel("Hour end: ");
		this.hoursEndLabel.setHorizontalAlignment(JLabel.LEFT);
		this.hoursEndLabel.setFont(this.hoursEndLabel.getFont().deriveFont(Font.PLAIN, 20));		
		
		this.hourEnd = new JComboBox();
		this.hourEnd.setPreferredSize(new Dimension(330, 40));
		this.hourEnd.setFont(this.hourEnd.getFont().deriveFont(Font.PLAIN, 20));
		
		for(int i = 0; i < 24; i++) {
			this.hourEnd.addItem(i);
		}
		
		formContent.add(this.dateWorkingLabel);
		formContent.add(this.dateworkField, "wrap 30");
		
		formContent.add(this.manageID);
		formContent.add(this.manageAccount, "wrap 30");
		
		formContent.add(this.staffName);
		formContent.add(this.staffNameField, "wrap 30");
		
		formContent.add(this.hourstartLabel);
		formContent.add(this.hourStart, "wrap 30");
		
		formContent.add(this.hoursEndLabel);
		formContent.add(this.hourEnd, "wrap 30");
		
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new scheduleShift();
			}
		}); 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String com = e.getActionCommand().toString();
		if(com.equals("Cancel")) {
			this.dispose();
		}
		else if (com.equals("Save")){
			
			this.controllerStaff.addNewShift();
			
		}
	}
	
}
