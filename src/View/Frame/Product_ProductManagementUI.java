package View.Frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.table.*;

import Controller.MenuAndProduct.ManageMenuAndProduct;
import Controller.PanelChange.ControllerPanel;
import Model.Food_Product.Food;
import Model.Food_Product.Product;
import View.form.addFoodForm;
import View.form.addProductForm;
import View.form.editFoodForm;
import View.form.editProductForm;

import javax.swing.*;
import javax.swing.border.Border;


public class Product_ProductManagementUI extends JPanel implements ActionListener{
	
	public JPanel top;
	public JPanel content;
	public static JButton edit;
	public static JButton delete;
	public static JTable mytable = new JTable();
	public DefaultTableModel defaultModel = new DefaultTableModel();
	public ManageMenuAndProduct controller = new ManageMenuAndProduct(this);
	public static ArrayList<Product> listProduct;
	public JButton add;
	private ControllerPanel controllerPanel;
	
	
	private JPanel navbar;
	private JButton buttonMenu; 
	private JButton buttonProduct;
	private JPanel left;
	private JLabel jlb1;
	private JButton icon;
	private ImageIcon a;
	private JLabel jlb_staff;
	private JPanel center; 
	private JPanel right; 
	private ImageIcon ad;
	
	
	
	public static void updateProduct(ArrayList<Product> lst) {
		DefaultTableModel model = (DefaultTableModel)mytable.getModel();
		model.setNumRows(0);
		for(Product food : lst) {
			model.addRow(new Object[] {food.getProductID(), food.getNameProduct(), food.getMass(), 
									food.getPrice(), edit, delete});
		}
	}

	public Product_ProductManagementUI() {
		controllerPanel = new ControllerPanel(this);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		navbar = new JPanel();
		navbar.setPreferredSize(new Dimension(100, 70));

		buttonMenu = new JButton("<html><span style='font-size:20px'>Menu Management</span></html>");
		buttonMenu.setBackground(new Color(255, 192, 203));
		

		buttonProduct = new JButton("<html><span style='font-size:20px'>Product Management</span></html>");
		buttonProduct.setBackground(new Color(0, 0, 0));
		buttonProduct.setForeground(new Color(255, 192, 203));
		
		this.controllerPanel.setEventButton(buttonMenu, "MenuManagement");
		
		
		navbar.setLayout(new GridLayout(1, 2));
		navbar.add(buttonMenu);
		navbar.add(buttonProduct);
		
		top = new JPanel();
		top.setPreferredSize(new Dimension(100, 100));
		top.setBackground(new Color(255, 255, 255));
		top.setLayout(new GridLayout(1, 3));
		
		
		left = new JPanel();
		left.setBackground(new Color(255, 255, 255));
		jlb1 = new JLabel("Staff");
		
		icon = new JButton();
		icon.setOpaque(true);
		icon.setBackground(new Color(0, 0, 0));
		a = new ImageIcon(Staff_ManagerStaffUI.class.getResource("/images/baseline_house_white_24dp.png"));
		icon.setIcon(a);
		
		controllerPanel.setEventButton(icon, "Home");
		
		
		jlb_staff = new JLabel("<html><span style='font-size:25px'>Products</span></html>");
		
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
		add = new JButton("<html><span style='font-size:18px'>Add item</span></html>");
		add.setOpaque(true);
		ad = new ImageIcon(Staff_ManagerStaffUI.class.getResource("/images/add.png"));
		add.setIcon(ad);
		add.setActionCommand("add");
		add.addActionListener(this);
		
		SpringLayout springlayout1 = new SpringLayout();
		right.setLayout(springlayout1);
		springlayout1.putConstraint(SpringLayout.WEST, add, 200, SpringLayout.WEST, right);
		springlayout1.putConstraint(SpringLayout.SOUTH, add, -30 , SpringLayout.SOUTH, right);
		right.add(add);
		
		top.add(left);
		top.add(center);
		top.add(right);


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
				ImageIcon _edit = new ImageIcon(Staff_ManagerStaffUI.class.getResource("/images/pencil.png"));
				ImageIcon _remove = new ImageIcon(Staff_ManagerStaffUI.class.getResource("/images/delete.png"));
				
				_edit = new ImageIcon(Staff_ManagerStaffUI.class.getResource("/images/pencil.png"));

				_remove = new ImageIcon(Staff_ManagerStaffUI.class.getResource("/images/delete.png"));

			
				edit = new JButton();
				edit.setIcon(_edit);
				
				delete = new JButton();
				delete.setIcon(_remove);
				
				defaultModel = new DefaultTableModel();
				mytable.setModel(defaultModel);
				
				defaultModel.addColumn("Product ID");
				defaultModel.addColumn("Product Name");
				defaultModel.addColumn("Mass");
				defaultModel.addColumn("Price");
				defaultModel.addColumn("Edit");
				defaultModel.addColumn("Remove");
				
				listProduct = new ArrayList<Product>();
				listProduct = this.controller.getListProduct();
				
				for(Product product : listProduct) {
					defaultModel.addRow(new Object[] {product.getProductID(), product.getNameProduct(), product.getMass(), 
											product.getPrice(), edit, delete});
				}
				

			     TableColumn column = mytable.getColumnModel().getColumn(0);
			     column.setMinWidth(100);
			     column.setMaxWidth(150);
			     column.setPreferredWidth(150);
			     
			     TableColumn column6 = mytable.getColumnModel().getColumn(2);
			     column6.setMinWidth(100);
			     column6.setMaxWidth(200);
			     column6.setPreferredWidth(200);
			    
			     
			     TableColumn column5 = mytable.getColumnModel().getColumn(3);
			     column5.setMinWidth(100);
			     column5.setMaxWidth(200);
			     column5.setPreferredWidth(200);

			     TableColumn column1 = mytable.getColumnModel().getColumn(4);
			     column1.setMinWidth(100);
			     column1.setMaxWidth(150);
			     column1.setPreferredWidth(150);
			     
			     TableColumn column2 = mytable.getColumnModel().getColumn(5);
			     column2.setMinWidth(100);
			     column2.setMaxWidth(150);
			     column2.setPreferredWidth(150);
	    
			       				
				mytable.setRowHeight(50);
				
				JScrollPane scrollPane = new JScrollPane(mytable);
				scrollPane.setPreferredSize(new Dimension(400, 100));
				
				DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
				centerRenderer.setHorizontalAlignment( JLabel.CENTER );
				DefaultTableCellRenderer bgcolor = new DefaultTableCellRenderer();
				bgcolor.setBackground(Color.white);
				
				for(int i = 0; i < 6; i++) {
					mytable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
				}				
				
				mytable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);				
				mytable.getColumnModel().getColumn(4).setCellRenderer(new JPanelImage());
				mytable.getColumnModel().getColumn(5).setCellRenderer(new JPanelImage());
				
				mytable.setFillsViewportHeight(true);
				mytable.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
				mytable.getTableHeader().setPreferredSize(new Dimension(50, 50));
				
				mytable.addMouseListener(new java.awt.event.MouseAdapter() {
				    @Override
				    public void mouseClicked(java.awt.event.MouseEvent evt) {
				        int col = mytable.getSelectedColumn();
				        int row = mytable.getSelectedRow();
				        String id = (mytable.getModel().getValueAt(row, 0)).toString();
				        if (col == 4) {
				        	
				        	String name = defaultModel.getValueAt(row, 1).toString().trim();
				        	String mass = defaultModel.getValueAt(row,  2).toString();
				        	String price = defaultModel.getValueAt(row,  3).toString();
				        	String type = "edit";
				        	editProductForm fc = new editProductForm(id, name, mass, price, type);
				        	
				        }
				        else if(col == 5) {
				        	ManageMenuAndProduct.removeRowOfProduct(id);
				        	
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
			
		}else if(s.equals("Product Management")) {
			//this.menuAndProduct.changePanelProduct();
		}else if(s.equals("edit")) {
			//JOptionPane.showConfirmDialog(this, "AA");
		}else if(s.equals("delete")) {
			
		}else if(s.equals("add")) {
			
			addProductForm formProduct = new addProductForm();

	}
	
	}	
	
}
