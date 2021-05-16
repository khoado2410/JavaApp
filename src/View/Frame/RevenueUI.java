package View.Frame;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.table.*;

import javax.swing.*;
import javax.swing.border.Border;

import Controller.CashBook.ControllerCashBook;
import Controller.MenuAndProduct.ManageMenuAndProduct;
import Controller.PanelChange.ControllerPanel;
import Model.Food_Product.Bill;
import Model.Food_Product.Product;
import View.form.editProductForm;


public class RevenueUI extends JPanel{
	
	JPanel panelRevenue = new JPanel();
	public static JButton edit;
	public static JButton remove;
	public JTable mytable = new JTable();
	public DefaultTableModel defaultModel;
	public static ArrayList<Bill> listBill;
	public ControllerPanel control = new ControllerPanel(this);
	
	private ControllerCashBook con = new ControllerCashBook(this); 
	
	public RevenueUI() {
	
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		listBill = new ArrayList<Bill>();
		listBill = this.con.loadAllBill();
		
		JPanel navbar = new JPanel();
		navbar.setPreferredSize(new Dimension(100, 70));

		JButton buttonRevenue = new JButton("<html><span style='font-size:20px'>Revenue</span></html>");
		buttonRevenue.setBackground(new Color(255, 192, 203));
		
		JButton buttonSpending = new JButton("<html><span style='font-size:20px'>Spending</span></html>");
		buttonSpending.setBackground(new Color(0, 0, 0));
		buttonSpending.setForeground(new Color(255, 192, 203));
		
		control.setEventButton(buttonSpending, "buttonSpending");
		
		
		
		navbar.setLayout(new GridLayout(1, 2));
		navbar.add(buttonRevenue);
		navbar.add(buttonSpending);
		
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
		
		control.setEventButton(icon, "Home");
		
		JLabel jlb_staff = new JLabel("<html><span style='font-size:25px'>List of bill</span></html>");
		
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
		JLabel add = new JLabel("<html><span style='font-size:18px'></span></html>");
		add.setOpaque(true);
		
		int sum = 0;
		for(Bill bi: listBill) {
			sum += (bi.getPayment());
		}
		
		add.setText("Total of Bill: " + sum);
		add.setHorizontalAlignment(JLabel.CENTER);
		add.setFont(add.getFont().deriveFont(Font.PLAIN, 25));
		
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
				
				defaultModel.addColumn("IDBill");
				defaultModel.addColumn("Total");
				defaultModel.addColumn("Manager");
				defaultModel.addColumn("Edit");
				
	
				
				for(Bill product : listBill) {
					defaultModel.addRow(new Object[] {product.getBillID(), product.getPayment(), product.getAccManagerID(), 
											edit, remove});
				}
					       				
				mytable.setRowHeight(50);
				
				JScrollPane scrollPane = new JScrollPane(mytable);
				scrollPane.setPreferredSize(new Dimension(400, 100));
				
				DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
				centerRenderer.setHorizontalAlignment( JLabel.CENTER );
				DefaultTableCellRenderer bgcolor = new DefaultTableCellRenderer();
				bgcolor.setBackground(Color.white);
				
				for(int i = 0; i < 4; i++) {
					mytable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
				}				
							
				mytable.getColumnModel().getColumn(3).setCellRenderer(new JPanelImage());
				
				mytable.setFillsViewportHeight(true);
				mytable.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
				mytable.getTableHeader().setPreferredSize(new Dimension(50, 50));
				
				mytable.addMouseListener(new java.awt.event.MouseAdapter() {
				    @Override
				    public void mouseClicked(java.awt.event.MouseEvent evt) {
				        int col = mytable.getSelectedColumn();
				        int row = mytable.getSelectedRow();
				        String id = (mytable.getModel().getValueAt(row, 0)).toString();
				        if (col == 3) {
				        	
				        	DetailBill detaiBill = new DetailBill(id);
				        	
				        }
				        else if(col == 4) {
				       
				        	//ManageMenuAndProduct.removeRowOfProduct(id);
				        	
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
	
}
