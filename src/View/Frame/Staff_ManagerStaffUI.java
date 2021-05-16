package View.Frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.table.*;

import Controller.ManageStaff.ControllerManageStaff;
import Controller.MenuAndProduct.ManageMenuAndProduct;
import Controller.PanelChange.ControllerPanel;
import Model.Food_Product.Product;
import Model.Staff_Manager.Staff;
import View.form.addProductForm;
import View.form.addStaffForm;
import View.form.editProductForm;
import View.form.editStaffForm;


import javax.swing.*;


public class Staff_ManagerStaffUI extends JPanel implements ActionListener{
	
	private ControllerManageStaff controllerStaff = new ControllerManageStaff(this);
	private ControllerPanel controller;
	
	JPanel panelManageStaff = new JPanel();
	public static JButton remove;
	public static JButton edit;
	public static JButton delete;
	public static JTable mytable = new JTable();
	public DefaultTableModel defaultModel = new DefaultTableModel();
	
	public static ArrayList<Staff> listStaff;
	
	public static void addRowToTable(Object[] dataRow) {
		DefaultTableModel model = (DefaultTableModel)mytable.getModel();
		//DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		//centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		//DefaultTableCellRenderer bgcolor = new DefaultTableCellRenderer();
		//bgcolor.setBackground(Color.white);
		
		//for(int i = 0; i < 8; i++) {
			//mytable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		//}	
		model.addRow(dataRow);
	}
	
	public static void updateStaff(ArrayList<Staff> lst) {
		DefaultTableModel model = (DefaultTableModel)mytable.getModel();
		model.setNumRows(0);
		ImageIcon _edit = new ImageIcon(Staff_ManagerStaffUI.class.getResource("/images/pencil.png"));
		edit.setIcon(_edit);
		for(Staff s : lst) {
			model.addRow(new Object[] {s.getStaffID(), s.getStaffName(), s.getAddress(), 
					s.getGender(), s.getDateOfBirth(), s.getSalary(), s.getPoint(), edit, remove});
		}
	}
	
	
	public Staff_ManagerStaffUI() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		JPanel navbar = new JPanel();
		navbar.setPreferredSize(new Dimension(100, 70));
		
		JButton buttonStaff = new JButton("<html><span style='font-size:20px'>Staff</span></html>");
		buttonStaff.setBackground(new Color(255, 192, 203));
		JButton buttonTimekeeping = new JButton("<html><span style='font-size:20px'>TimeKeeping</span></html>");
		buttonTimekeeping.setBackground(new Color(0, 0, 0));
		buttonTimekeeping.setForeground(new Color(255, 192, 203));
		
		JButton buttonPayroll = new JButton("<html><span style='font-size:20px'>Payroll</span></html>");
		buttonPayroll.setBackground(new Color(0, 0, 0));
		buttonPayroll.setForeground(new Color(255, 192, 203));

		
		buttonStaff.setActionCommand("Staff");
		buttonTimekeeping.setActionCommand("Timekeeping");
		buttonPayroll.setActionCommand("Payroll");
		buttonStaff.addActionListener(this);
		buttonTimekeeping.addActionListener(this);
		buttonPayroll.addActionListener(this);
		
		navbar.setLayout(new GridLayout(1, 3));
		navbar.add(buttonStaff);
		navbar.add(buttonTimekeeping);
		navbar.add(buttonPayroll);
		
		// TOP
		JPanel top = new JPanel();
		top.setPreferredSize(new Dimension(100, 100));
		top.setBackground(new Color(255, 255, 255));
		top.setLayout(new GridLayout(1, 3));
		
		
		JPanel left = new JPanel();
		left.setBackground(new Color(255, 255, 255));
		JLabel jlb1 = new JLabel("Staff");
		
		JButton icon = new JButton();
		icon.setOpaque(true);
		icon.setBackground(new Color(0, 0, 0));
		ImageIcon a = new ImageIcon(Staff_ManagerStaffUI.class.getResource("/images/baseline_house_white_24dp.png"));
		icon.setIcon(a);
		icon.setActionCommand("home");
		controller = new ControllerPanel(this);
		controller.setEventButton(icon, "Home");
		controller.setEventButton(buttonPayroll, "buttonPayroll");
		controller.setEventButton(buttonTimekeeping, "buttonTimekeeping");
		
		JLabel jlb_staff = new JLabel("<html><span style='font-size:25px'>Staff</span></html>");
		
		SpringLayout springlayout = new SpringLayout();
		left.setLayout(springlayout);
		springlayout.putConstraint(SpringLayout.WEST, icon, 0, SpringLayout.WEST, left);
		springlayout.putConstraint(SpringLayout.NORTH, icon, 0, SpringLayout.NORTH, left);
		left.add(icon);
		
		springlayout.putConstraint(SpringLayout.WEST, jlb_staff, 170, SpringLayout.WEST, left);
		springlayout.putConstraint(SpringLayout.SOUTH, jlb_staff, -40, SpringLayout.SOUTH, left);

		left.add(jlb_staff);
		
		
		JPanel center = new JPanel();
		center.setBackground(new Color(255, 255, 255));
		
		JPanel right = new JPanel();
		right.setBackground(new Color(255, 255, 255));
		JButton add = new JButton("<html><span style='font-size:18px'>Add staff</span></html>");
		add.setOpaque(true);
		ImageIcon ad = new ImageIcon(Staff_ManagerStaffUI.class.getResource("/images/add.png"));
		add.setIcon(ad);
		add.setActionCommand("addStaff");
		add.addActionListener(this);
		
		
		SpringLayout springlayout1 = new SpringLayout();
		right.setLayout(springlayout1);
		springlayout1.putConstraint(SpringLayout.WEST, add, 270, SpringLayout.WEST, right);
		springlayout1.putConstraint(SpringLayout.SOUTH, add, -30 , SpringLayout.SOUTH, right);
		right.add(add);
		
		top.add(left);
		top.add(center);
		top.add(right);
		
		// CONTENT
		JPanel content = new JPanel();
		content.setLayout(new BorderLayout());
		content.setPreferredSize(new Dimension(100, 400));
		
		class JPanelImage implements TableCellRenderer{

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				return (Component)value;
			}
			
		}
		
		// JLabel chua 2 icon
		edit = new JButton();
		ImageIcon _edit = new ImageIcon(Staff_ManagerStaffUI.class.getResource("/images/pencil.png"));
		edit.setIcon(_edit);
		
		ImageIcon _remove = new ImageIcon(Staff_ManagerStaffUI.class.getResource("/images/delete.png"));
		remove = new JButton();
		remove.setIcon(_remove);
		
		
		defaultModel = new DefaultTableModel();
		mytable.setModel(defaultModel);
		
		defaultModel.addColumn("ID");
		defaultModel.addColumn("Name");
		defaultModel.addColumn("Address");
		defaultModel.addColumn("Gender");
		defaultModel.addColumn("Date of birth");
		defaultModel.addColumn("Salary");
		defaultModel.addColumn("Point");
		defaultModel.addColumn("Edit");
		defaultModel.addColumn("Remove");
		
		listStaff = new ArrayList<Staff>();
		listStaff = this.controllerStaff.loadStaff();
		
		for(Staff s : listStaff) {
			defaultModel.addRow(new Object[] {s.getStaffID(), s.getStaffName(), s.getAddress(), 
									s.getGender(), s.getDateOfBirth(), s.getSalary(), s.getPoint(), edit, remove});
		}
		

	     TableColumn column = mytable.getColumnModel().getColumn(0);
	     column.setMinWidth(100);
	     column.setMaxWidth(110);
	     column.setPreferredWidth(110);
	
	    
	     
	     TableColumn column5 = mytable.getColumnModel().getColumn(3);
	     column5.setMinWidth(100);
	     column5.setMaxWidth(100);
	     column5.setPreferredWidth(100);

	     TableColumn column1 = mytable.getColumnModel().getColumn(4);
	     column1.setMinWidth(100);
	     column1.setMaxWidth(150);
	     column1.setPreferredWidth(150);
	     
	     TableColumn column2 = mytable.getColumnModel().getColumn(5);
	     column2.setMinWidth(100);
	     column2.setMaxWidth(100);
	     column2.setPreferredWidth(100);
	     
	     TableColumn column6 = mytable.getColumnModel().getColumn(6);
	     column6.setMinWidth(100);
	     column6.setMaxWidth(100);
	     column6.setPreferredWidth(100);
	     
	     TableColumn column9 = mytable.getColumnModel().getColumn(7);
	     column9.setMinWidth(100);
	     column9.setMaxWidth(100);
	     column9.setPreferredWidth(100);
	     
	     TableColumn column10 = mytable.getColumnModel().getColumn(8);
	     column10.setMinWidth(100);
	     column10.setMaxWidth(100);
	     column10.setPreferredWidth(100);

	     
	       				
		mytable.setRowHeight(50);
		
		JScrollPane scrollPane = new JScrollPane(mytable);
		scrollPane.setPreferredSize(new Dimension(400, 100));
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		DefaultTableCellRenderer bgcolor = new DefaultTableCellRenderer();
		bgcolor.setBackground(Color.white);
		
		for(int i = 0; i < 8; i++) {
			mytable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}				
		
		//mytable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);				
		mytable.getColumnModel().getColumn(7).setCellRenderer(new JPanelImage());
		mytable.getColumnModel().getColumn(8).setCellRenderer(new JPanelImage());
		
		mytable.setFillsViewportHeight(true);
		mytable.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		mytable.getTableHeader().setPreferredSize(new Dimension(50, 50));
		
		mytable.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int col = mytable.getSelectedColumn();
		        int row = mytable.getSelectedRow();
		        String id = (mytable.getModel().getValueAt(row, 0)).toString();
		        if (col == 7) {
		        	
		        	String name = defaultModel.getValueAt(row, 1).toString().trim();
		        	//String mass = defaultModel.getValueAt(row,  2).toString();
		        	String address = defaultModel.getValueAt(row,  2).toString();
		        	String gender = defaultModel.getValueAt(row, 3).toString();
		        	String dob = defaultModel.getValueAt(row, 4).toString();
		        	String sal = defaultModel.getValueAt(row, 5).toString();
		        	String point = defaultModel.getValueAt(row, 6).toString();
		        	
		        	String type = "edit";
		        	//Date date1=(Date) new SimpleDateFormat("MM/dd/yyyy").parse(dob);  
		        	editStaffForm fc = new editStaffForm(id, name, address, gender, dob, sal, point);
		        	
		        }
		        else if(col == 8) {
		        		ControllerManageStaff.removeRowOfStaff(id);
		        }
		    }
		});
		
		content.add(scrollPane, BorderLayout.CENTER);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(navbar);
		add(top);
		add(content);
		setVisible(true);
		

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		
		
		if(s.equals("Menu Management")) {
		
			//this.menuAndProduct.changePanelMenu();
			
		}else if(s.equals("home")) {
			//this.menuAndProduct.changePanelProduct();
		}else if(s.equals("edit")) {
			//JOptionPane.showConfirmDialog(this, "AA");
		}else if(s.equals("delete")) {
			
		}else if(s.equals("addStaff")) {
			addStaffForm form = new addStaffForm();
			
			
	}
		
	}
	

}
