package Frame;

import java.awt.*;
import javax.swing.table.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;


public class DetailBillUI extends JFrame{
	private JFrame jfrm;
	private JPanel navbar;
	private JButton buttonRevenue;
	private JButton buttonSpending;
	private JPanel top;
	private JPanel left;
	private JLabel icon;
	private JLabel jlb_staff;
	private JPanel center;
	private JPanel right;
	private JPanel content;
	private JLabel add;
	private JLabel edit;
	private ImageIcon _remove;
	private JLabel remove;
	private JPanel fonc;
	private JScrollPane scrollPane;
	private JPanel totalBill;
	private JLabel total;
	
	public DetailBillUI() {
		jfrm = new JFrame("Manager Staff");
		jfrm.setVisible(true);
		jfrm.setExtendedState(jfrm.MAXIMIZED_BOTH);
		
		
		navbar = new JPanel();
		navbar.setPreferredSize(new Dimension(100, 70));

		buttonRevenue = new JButton("<html><span style='font-size:20px'>Revenue</span></html>");
		buttonRevenue.setBackground(new Color(255, 192, 203));
				
		buttonSpending = new JButton("<html><span style='font-size:20px'>Spending</span></html>");
		buttonSpending.setBackground(new Color(0, 0, 0));
		buttonSpending.setForeground(new Color(255, 192, 203));
		
		navbar.setLayout(new GridLayout(1, 2));
		navbar.add(buttonRevenue);
		navbar.add(buttonSpending);
		
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
		ImageIcon a = new ImageIcon(Staff_ManagerStaffUI.class.getResource("/images/baseline_house_white_24dp.png"));
		icon.setIcon(a);
		
		jlb_staff = new JLabel("<html><span style='font-size:25px'>List of spending</span></html>");
		
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
		ImageIcon ad = new ImageIcon(Staff_ManagerStaffUI.class.getResource("/images/add.png"));
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
				ImageIcon _edit = new ImageIcon(Staff_ManagerStaffUI.class.getResource("/images/pencil.png"));
				edit.setIcon(_edit);
				
				_remove = new ImageIcon(Staff_ManagerStaffUI.class.getResource("/images/delete.png"));
				remove = new JLabel();
				remove.setIcon(_remove);
				
				fonc = new JPanel();
				SpringLayout springlayout2 = new SpringLayout();
				fonc.setLayout(springlayout2);
				springlayout2.putConstraint(SpringLayout.WEST, edit, 120, SpringLayout.WEST, fonc);
				springlayout2.putConstraint(SpringLayout.SOUTH, edit, -15, SpringLayout.SOUTH, fonc);
				springlayout2.putConstraint(SpringLayout.WEST, remove, 150, SpringLayout.WEST, fonc);
				springlayout2.putConstraint(SpringLayout.SOUTH, remove, -15, SpringLayout.SOUTH, fonc);
				fonc.add(edit);
				fonc.add(remove);
				String[] columnNames = {"IDFood", "Food", "Price", "Amount", "Total"};
				
				Object[][] data = {
						{
							"F001", "Fried Chicken", "8$", "1", "8$",
						},
						{
							"F002", "Salmon and Chips", "12$", "1", "12$",
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
				scrollPane = new JScrollPane(table);
				scrollPane.setPreferredSize(new Dimension(500, 100));
				table.setFillsViewportHeight(true);
				table.setRowHeight(60);
				DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
				centerRenderer.setHorizontalAlignment( JLabel.CENTER );
				for(int i = 0; i < 5; i++) {
					table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
				}
				
				//table.getColumnModel().getColumn(4).setCellRenderer(new JPanelImage());
			
				
				table.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
				table.getTableHeader().setPreferredSize(new Dimension(100, 60));
				table.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
				content.add(table.getTableHeader(), BorderLayout.PAGE_START);
				content.add(scrollPane, BorderLayout.CENTER);
				
				
				totalBill = new JPanel();
				total = new JLabel("Total: 20$");
				total.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
				totalBill.setPreferredSize(new Dimension(100, 200));
				totalBill.setBackground(new Color(255, 255, 255));
				
				totalBill.add(total);
				content.add(totalBill, BorderLayout.PAGE_END);
				jfrm.setLayout(new BoxLayout(jfrm.getContentPane(), BoxLayout.Y_AXIS));
				jfrm.add(navbar);
				jfrm.add(top);
				jfrm.add(content);
				
		
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new DetailBillUI();
					} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
