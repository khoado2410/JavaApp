package Frame;

import java.awt.*;
import javax.swing.table.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;


public class Staff_ManagerStaffUI extends JFrame{
	
	public Staff_ManagerStaffUI() {
		JFrame jfrm = new JFrame("Manager Staff");
		jfrm.setVisible(true);
		jfrm.setExtendedState(jfrm.MAXIMIZED_BOTH);
		
		
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
		
		JLabel icon = new JLabel();
		icon.setOpaque(true);
		icon.setBackground(new Color(0, 0, 0));
		ImageIcon a = new ImageIcon(Staff_ManagerStaffUI.class.getResource("/images/baseline_house_white_24dp.png"));
		icon.setIcon(a);
		
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
		JLabel add = new JLabel("<html><span style='font-size:18px'>Add staff</span></html>");
		add.setOpaque(true);
		ImageIcon ad = new ImageIcon(Staff_ManagerStaffUI.class.getResource("/images/add.png"));
		add.setIcon(ad);
		
		
		SpringLayout springlayout1 = new SpringLayout();
		right.setLayout(springlayout1);
		springlayout1.putConstraint(SpringLayout.WEST, add, 300, SpringLayout.WEST, right);
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
		JLabel edit = new JLabel();
		ImageIcon _edit = new ImageIcon(Staff_ManagerStaffUI.class.getResource("/images/pencil.png"));
		edit.setIcon(_edit);
		
		ImageIcon _remove = new ImageIcon(Staff_ManagerStaffUI.class.getResource("/images/delete.png"));
		JLabel remove = new JLabel();
		remove.setIcon(_remove);
		
		JPanel fonc = new JPanel();
		SpringLayout springlayout2 = new SpringLayout();
		fonc.setLayout(springlayout2);
		springlayout2.putConstraint(SpringLayout.WEST, edit, 60, SpringLayout.WEST, fonc);
		springlayout2.putConstraint(SpringLayout.SOUTH, edit, -15, SpringLayout.SOUTH, fonc);
		springlayout2.putConstraint(SpringLayout.WEST, remove, 90, SpringLayout.WEST, fonc);
		springlayout2.putConstraint(SpringLayout.SOUTH, remove, -15, SpringLayout.SOUTH, fonc);
		fonc.add(edit);
		fonc.add(remove);
		String[] columnNames = {"ID", "Name", "Address", "Gender", "Date of bỉthday", "Salary", "Point", ""};
		
		Object[][] data = {
				{
					"NV1", "Nguyen Nhat Minh", "12 Tran Binh Trong", "Male", "14/03/2000", "2000", "123", fonc,
				},
				{
					"NV2", "Nguyen Nhat Minh", "12 Tran Binh Trong", "Male", "14/03/2000", "2000", "123", fonc,
				},
				{
					"NV3", "Nguyen Nhat Minh", "12 Tran Binh Trong", "Male", "14/03/2000", "2000", "123", fonc, 
				},
				{
					"NV4", "Nguyen Nhat Minh", "12 Tran Binh Trong", "Male", "14/03/2000", "2000", "123", fonc,
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
		for(int i = 0; i < 8; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		
		table.getColumnModel().getColumn(7).setCellRenderer(new JPanelImage());
	//	table.getColumnModel().getColumn(7).setWidth();
		
		table.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		table.getTableHeader().setPreferredSize(new Dimension(100, 60));
		table.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		content.add(table.getTableHeader(), BorderLayout.PAGE_START);
		content.add(scrollPane, BorderLayout.CENTER);
		
	
		jfrm.setLayout(new BoxLayout(jfrm.getContentPane(), BoxLayout.Y_AXIS));
		jfrm.add(navbar);
		jfrm.add(top);
		jfrm.add(content);
		
		
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Staff_ManagerStaffUI();
					} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
