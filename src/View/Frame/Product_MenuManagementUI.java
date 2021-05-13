package View.Frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import Controller.MenuAndProduct.ManageMenuAndProduct;


public class Product_MenuManagementUI extends JPanel implements ActionListener{
	
	ManageMenuAndProduct menuAndProduct = new ManageMenuAndProduct(this);
	public JPanel content;
	public JPanel top;
	public JButton buttonMenu;
	public JButton buttonProduct;
	
	private JPanel navbar;
	private JPanel left;
	private JLabel jlb1;
	private JLabel icon;
	private ImageIcon a;
	private JLabel jlb_staff;
	private JPanel center;
	private JPanel right;
	private JLabel add;
	private ImageIcon ad; 
	private JLabel edit; 
	private ImageIcon _edit;
	private ImageIcon _remove; 
	private JLabel remove;
	private JPanel fonc;
	
	public Product_MenuManagementUI() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		
		navbar = new JPanel();
		navbar.setPreferredSize(new Dimension(100, 70));

		buttonMenu = new JButton("<html><span style='font-size:20px'>Menu Management</span></html>");
		buttonMenu.setBackground(new Color(255, 192, 203));
		

		buttonProduct = new JButton("<html><span style='font-size:20px'>Product Management</span></html>");
		buttonProduct.setBackground(new Color(0, 0, 0));
		buttonProduct.setForeground(new Color(255, 192, 203));
		
		
		buttonMenu.setActionCommand("Menu Management");
		buttonProduct.setActionCommand("Product Management");
		
		buttonMenu.addActionListener(this);
		buttonProduct.addActionListener(this);
		
		navbar.setLayout(new GridLayout(1, 2));
		navbar.add(buttonMenu);
		navbar.add(buttonProduct);
		
		// TOP
		top = new JPanel();
		top.setPreferredSize(new Dimension(100, 100));
		top.setBackground(new Color(255, 255, 255));
		top.setLayout(new GridLayout(1, 3));
		
		
		left = new JPanel();
		left.setBackground(new Color(255, 255, 255));
		JLabel jlb1 = new JLabel("Staff");
		
		icon = new JLabel();
		icon.setOpaque(true);
		icon.setBackground(new Color(0, 0, 0));
		a = new ImageIcon(Staff_ManagerStaffUI.class.getResource("/images/baseline_house_white_24dp.png"));
		icon.setIcon(a);
		
		jlb_staff = new JLabel("<html><span style='font-size:25px'>Menu</span></html>");
		
		SpringLayout springlayout = new SpringLayout();
		left.setLayout(springlayout);
		springlayout.putConstraint(SpringLayout.WEST, icon, 0, SpringLayout.WEST, left);
		springlayout.putConstraint(SpringLayout.NORTH, icon, 0, SpringLayout.NORTH, left);
		left.add(icon);
		
		springlayout.putConstraint(SpringLayout.WEST, jlb_staff, 170, SpringLayout.WEST, left);
		springlayout.putConstraint(SpringLayout.SOUTH, jlb_staff, -40, SpringLayout.SOUTH, left);

		left.add(jlb_staff);
		
		
		center = new JPanel();
		center.setBackground(new Color(255, 255, 255));
		
		right = new JPanel();
		right.setBackground(new Color(255, 255, 255));
		add = new JLabel("<html><span style='font-size:18px'>Add item</span></html>");
		add.setOpaque(true);
		ad = new ImageIcon(Staff_ManagerStaffUI.class.getResource("/images/add.png"));
		add.setIcon(ad);
		
		SpringLayout springlayout1 = new SpringLayout();
		right.setLayout(springlayout1);
		springlayout1.putConstraint(SpringLayout.WEST, add, 200, SpringLayout.WEST, right);
		springlayout1.putConstraint(SpringLayout.SOUTH, add, -30 , SpringLayout.SOUTH, right);
		right.add(add);
		
		top.add(left);
		top.add(center);
		top.add(right);
		
		// CONTENT
		// CONTENT
				content = new JPanel();
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
				edit = new JLabel();
				_edit = new ImageIcon(Staff_ManagerStaffUI.class.getResource("/images/pencil.png"));
				edit.setIcon(_edit);
				
				_remove = new ImageIcon(Staff_ManagerStaffUI.class.getResource("/images/delete.png"));
				remove = new JLabel();
				remove.setIcon(_remove);
				
				fonc = new JPanel();
				SpringLayout springlayout2 = new SpringLayout();
				fonc.setLayout(springlayout2);
				springlayout2.putConstraint(SpringLayout.WEST, edit, 130, SpringLayout.WEST, fonc);
				springlayout2.putConstraint(SpringLayout.SOUTH, edit, -15, SpringLayout.SOUTH, fonc);
				springlayout2.putConstraint(SpringLayout.WEST, remove, 160, SpringLayout.WEST, fonc);
				springlayout2.putConstraint(SpringLayout.SOUTH, remove, -15, SpringLayout.SOUTH, fonc);
				fonc.add(edit);
				fonc.add(remove);
				String[] columnNames = {"ID Food", "Name", "Amount", ""};
				
				Object[][] data = {
						{
							"F001", "Fried Chicken", "15", fonc,
						},
						{
							"F002", "Beafsteak", "20", fonc,
						},
						{
							"F003", "Fish and Chips", "8", fonc,
						},
						{
							"F004", "Fried Chicken", "15", fonc,
						}		
				};
						
				class MyJTable extends JTable{
					MyJTable(Object[][] data, String[] columnNames){
						super(data, columnNames);
					}
					public java.awt.Component prepareRenderer
					(javax.swing.table.TableCellRenderer rendrer, int row, int col){
						Component comp = super.prepareRenderer(rendrer, row, col);
						if(row % 2 == 0 && !isCellSelected(row, col)) {
							comp.setBackground(new Color(196, 196, 196));
						}else if(!isCellSelected(row, col)) {
							comp.setBackground(new Color(169, 169, 169));
							
						}else {
							comp.setBackground(Color.black);
						}
						return comp;
					}
				}
				MyJTable table = new MyJTable(data, columnNames);
				JScrollPane scrollPane = new JScrollPane(table);
				scrollPane.setPreferredSize(new Dimension(500, 100));
				table.setFillsViewportHeight(true);
				table.setRowHeight(60);
				DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
				centerRenderer.setHorizontalAlignment( JLabel.CENTER );
				for(int i = 0; i < 4; i++) {
					table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
				}
				
				table.getColumnModel().getColumn(3).setCellRenderer(new JPanelImage());
			//	table.getColumnModel().getColumn(7).setWidth();
				
				table.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
				table.getTableHeader().setPreferredSize(new Dimension(100, 60));
				table.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
				content.add(table.getTableHeader(), BorderLayout.PAGE_START);
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
			
			this.menuAndProduct.changePanelMenu();
		}else if(s.equals("Product Management")) {
			
			this.menuAndProduct.changePanelProduct();
			
		}
		
	}

}
