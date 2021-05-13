package View.Frame;

import java.awt.*;
import javax.swing.table.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;


public class Staff_TimekeepingUI extends JFrame{
	
	JPanel panelTime = new JPanel();
	
	public Staff_TimekeepingUI() {
		super("Timekeeping");
		
		

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

		SpringLayout springlayout1 = new SpringLayout();
		right.setLayout(springlayout1);
		springlayout1.putConstraint(SpringLayout.WEST, add, 200, SpringLayout.WEST, right);
		springlayout1.putConstraint(SpringLayout.SOUTH, add, -30, SpringLayout.SOUTH, right);
		right.add(add);

		top.add(left);
		top.add(center);
		top.add(right);

		// CONTENT
		JPanel content = new JPanel();
		content.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 0));
		content.setPreferredSize(new Dimension(100, 400));
		content.setBackground(new Color(255, 255, 255));

		Border blackline = BorderFactory.createLineBorder(Color.black);
		JPanel shift = new JPanel();
		JPanel con_shift = new JPanel();
		con_shift.setPreferredSize(new Dimension(198, 100));
		con_shift.setBackground(new Color(255, 255, 255));
		con_shift.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		JLabel _shift = new JLabel("<html><span style='font-size:20px'>Shift</span></html>");

		con_shift.add(_shift);

		shift.setBackground(Color.white);
		shift.setPreferredSize(new Dimension(200, 300));
		shift.setBorder(blackline);
		SpringLayout springlayout_shift = new SpringLayout();
		con_shift.setLayout(springlayout_shift);
		springlayout_shift.putConstraint(SpringLayout.WEST, _shift, 60, SpringLayout.WEST, con_shift);
		springlayout_shift.putConstraint(SpringLayout.SOUTH, _shift, -40, SpringLayout.SOUTH, con_shift);
		shift.add(con_shift);

		// shift.add(_shift);

		JPanel time = new JPanel();
		// time.setBackground(Color.black);
		time.setBackground(new Color(255, 255, 255));
		time.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		time.setPreferredSize(new Dimension(1000, 300));
		time.setBorder(blackline);
		JPanel calen = new JPanel();
		calen.setPreferredSize(new Dimension(998, 50));
		calen.setBackground(new Color(255, 255, 255));
		calen.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		JLabel _calen = new JLabel("<html><span style='font-size:20px'>February, 2021</span></html>");
		calen.add(_calen);
		time.add(calen);

		String[] columnNames = { "Mon 22", "Tue 23", "Web 24", "Thu 25", "Fri 26", "Sat 27", "Sun 28" };

		Object[][] data = { { "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "" },
				{ "", "", "", "", "", "", "" } };

		class MyJTable extends JTable {
			MyJTable(Object[][] data, String[] columnNames) {
				super(data, columnNames);
			}

			public java.awt.Component prepareRenderer(javax.swing.table.TableCellRenderer rendrer, int row, int col) {
				Component comp = super.prepareRenderer(rendrer, row, col);
				if (row % 2 == 0 && !isCellSelected(row, col)) {
					comp.setBackground(new Color(196, 196, 196));
				} else if (!isCellSelected(row, col)) {
					comp.setBackground(new Color(169, 169, 169));

				} else {
					comp.setBackground(Color.black);
				}
				return comp;
			}
		}
		MyJTable table = new MyJTable(data, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(998, 500));
		table.setFillsViewportHeight(true);
		table.setRowHeight(70);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		table.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		table.getTableHeader().setPreferredSize(new Dimension(900, 50));
		table.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

		JPanel _table = new JPanel();
		_table.setPreferredSize(new Dimension(1000, 400));
		_table.add(table.getTableHeader());
		_table.add(scrollPane);
		time.add(_table);
		content.add(shift);
		content.add(time);

	
		this.panelTime.setLayout(new BoxLayout(this.panelTime, BoxLayout.Y_AXIS));
		this.panelTime.add(navbar);
		this.panelTime.add(top);
		this.panelTime.add(content);
		
		add(this.panelTime);
		
		setExtendedState(MAXIMIZED_BOTH);
		
		

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Staff_TimekeepingUI().setVisible(true);;
					} catch (Exception e) {

					e.printStackTrace();
				}
			}
		});
	}

}
