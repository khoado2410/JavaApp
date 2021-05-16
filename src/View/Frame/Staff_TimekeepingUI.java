package View.Frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.table.*;

import javax.swing.*;
import javax.swing.border.Border;

import Controller.ManageStaff.ControllerManageStaff;
import Controller.PanelChange.ControllerPanel;
import Model.Food_Product.Product;
import Model.Shift.Shift;
import Model.Staff_Manager.Staff;
import View.form.editStaffForm;
import View.form.scheduleShift;


public class Staff_TimekeepingUI extends JPanel implements ActionListener{
	
	JPanel panelTime = new JPanel();
	
	JPanel panelManageStaff = new JPanel();
	public static JButton remove;
	public static JButton edit;
	public static JButton delete;
	public static JTable mytable = new JTable();
	public DefaultTableModel defaultModel = new DefaultTableModel();
	
	public static ArrayList<Shift> listShift;
	
	
	ControllerPanel controller;
	ControllerManageStaff control = new ControllerManageStaff(this);
	
	public static void refreshTable(ArrayList<Shift> lst) {
		DefaultTableModel model = (DefaultTableModel)mytable.getModel();
		model.setNumRows(0);
		for(Shift food : lst) {
			model.addRow(new Object[] {food.getShiftID(), food.getStaff().getStaffName(), food.getWorkingTimeStart(),
					food.getEndWork(), food.getDateWork(), remove});		}
	}
	
	public Staff_TimekeepingUI() {
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
	
		JPanel navbar = new JPanel();
		navbar.setPreferredSize(new Dimension(100, 70));

		JButton buttonStaff = new JButton("<html><span style='font-size:20px'>Staff</span></html>");
		buttonStaff.setBackground(new Color(0, 0, 0));
		buttonStaff.setForeground(new Color(255, 192, 203));

		JButton buttonTimekeeping = new JButton("<html><span style='font-size:20px'>TimeKeeping</span></html>");
		buttonTimekeeping.setBackground(new Color(255, 192, 203));

		JButton buttonPayroll = new JButton("<html><span style='font-size:20px'>Payroll</span></html>");
		buttonPayroll.setBackground(new Color(0, 0, 0));
		buttonPayroll.setForeground(new Color(255, 192, 203));

		navbar.setLayout(new GridLayout(1, 3));
		navbar.add(buttonStaff);
		navbar.add(buttonTimekeeping);
		navbar.add(buttonPayroll);

//		// TOP
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

		controller = new ControllerPanel(this);
		
		controller.setEventButton(icon, "Home");
		controller.setEventButton(buttonStaff, "Staff");
		controller.setEventButton(buttonPayroll, "buttonPayroll");
			
		JLabel jlb_staff = new JLabel("<html><span style='font-size:25px'>Timekeeping</span></html>");

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
		JButton add = new JButton("<html><span style='font-size:18px; color:white'>Schedule a shift</span></html>");
		add.setBackground(new Color(0, 0, 0));
		add.setOpaque(true);
		
		add.setActionCommand("add");
		add.addActionListener(this);

		SpringLayout springlayout1 = new SpringLayout();
		right.setLayout(springlayout1);
		springlayout1.putConstraint(SpringLayout.WEST, add, 200, SpringLayout.WEST, right);
		springlayout1.putConstraint(SpringLayout.SOUTH, add, -30, SpringLayout.SOUTH, right);
		right.add(add);

		top.add(left);
		top.add(center);
		top.add(right);
//

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
			
			defaultModel.addColumn("Shift ID");
			defaultModel.addColumn("Staff Name");
			defaultModel.addColumn("WorkingTimeStart");
			defaultModel.addColumn("WorkingTimeEnd");
			defaultModel.addColumn("DateWorking");
			defaultModel.addColumn("Remove");
			
			listShift = new ArrayList<Shift>();
			listShift = this.control.getListShift();
			
			for(Shift s : listShift) {
				defaultModel.addRow(new Object[] {s.getShiftID(), s.getStaff().getStaffName(), s.getWorkingTimeStart(),
						s.getEndWork(), s.getDateWork(),remove});
			}
			
		       				
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
			        if (col == 5) {
			        	
			        	
			        	ControllerManageStaff.RemoveTimekeeping(id);
			        	
			        }
			       
			    }
			});
			
			content.add(scrollPane, BorderLayout.CENTER);

		
	
	//	content.add(scrollPane, BorderLayout.CENTER);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(navbar);
		add(top);
		add(content);
	
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String com = e.getActionCommand().toString();
		
		if(com.equals("add")) {
			scheduleShift ab = new scheduleShift();
		}
	}

}
