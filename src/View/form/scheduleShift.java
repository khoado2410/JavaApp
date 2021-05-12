package View.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import net.miginfocom.swing.MigLayout;

public class scheduleShift extends JFrame{
	private JFrame jfrm;
	private JPanel screen;
	private JPanel contain;
	private JLabel theme; 
	private JLabel _date;
	private JTextField date;
	private JLabel shift;
	private JComboBox _shift;
	private JTextField search;
	
	public scheduleShift() {
		jfrm = new JFrame("Schedule Shift");
		jfrm.setVisible(true);
		jfrm.setBackground(Color.white);
		jfrm.setExtendedState(jfrm.MAXIMIZED_BOTH);
		
		screen = new JPanel();
		screen.setBackground(new Color(255, 255, 255));
		
		contain = new JPanel();
		contain.setPreferredSize(new Dimension(600, 500));
		contain.setBackground(new Color(255, 255, 255));
		
		
		SpringLayout springlayout = new SpringLayout();
		screen.setLayout(springlayout);
		springlayout.putConstraint(SpringLayout.WEST, contain, 450, SpringLayout.WEST, screen);
		springlayout.putConstraint(SpringLayout.NORTH, contain, 100, SpringLayout.NORTH, screen);
		screen.add(contain);
		
		
		theme = new JLabel("<html><span style='font-size:20px; font-weight:bold'>Schedule a shift</span></html>");
		_date = new JLabel("Date");
		_date.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		_date.setForeground(Color.gray);
		
		date = new JTextField(15);
		date.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		date.setFont(date.getFont().deriveFont(Font.PLAIN, 20));
		date.setText("01/05/2021");
		
		shift = new JLabel("Shift");
		shift.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		shift.setForeground(Color.gray);
		
		String chooses[] = {
				"7h-11h", "11h-15h", "15h-19h", "19h-23h",
		};
		_shift = new JComboBox(chooses);
		_shift.setFont(date.getFont().deriveFont(Font.PLAIN, 20));
		_shift.setPreferredSize(new Dimension(250, 30));
		_shift.setBackground(Color.white);
		
		search = new JTextField(20);
		search.setText("Search by staff's ID");
		search.setPreferredSize(new Dimension(0, 50));
		search.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		search.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		
		String[] columnNames = {"Staff's ID", "Staff's Name", "Department"};
		
		Object[][] data = {
				{
					"NV1", "Nguyen Nhat Minh", "Cashier"
				},
					
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
		scrollPane.setPreferredSize(new Dimension(500, 98));
		table.setFillsViewportHeight(true);
		table.setRowHeight(54);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		for(int i = 0; i < 3; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		
		table.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		table.getTableHeader().setPreferredSize(new Dimension(400, 40));
		table.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		
		
		SpringLayout sprintlayout_contain = new SpringLayout();
		contain.setLayout(sprintlayout_contain);
		sprintlayout_contain.putConstraint(SpringLayout.WEST, theme, 100, SpringLayout.WEST, contain);
		sprintlayout_contain.putConstraint(SpringLayout.NORTH, theme, 50, SpringLayout.NORTH, contain);
		
		//date
		sprintlayout_contain.putConstraint(SpringLayout.WEST, _date, 90, SpringLayout.WEST, contain);
		sprintlayout_contain.putConstraint(SpringLayout.NORTH, _date, 120, SpringLayout.NORTH, contain);
		
		sprintlayout_contain.putConstraint(SpringLayout.WEST, date, 170, SpringLayout.WEST, contain);
		sprintlayout_contain.putConstraint(SpringLayout.NORTH, date, 120, SpringLayout.NORTH, contain);
		
		// _shift
		sprintlayout_contain.putConstraint(SpringLayout.WEST, shift, 90, SpringLayout.WEST, contain);
		sprintlayout_contain.putConstraint(SpringLayout.NORTH, shift, 180, SpringLayout.NORTH, contain);
		
		sprintlayout_contain.putConstraint(SpringLayout.WEST, _shift, 170, SpringLayout.WEST, contain);
		sprintlayout_contain.putConstraint(SpringLayout.NORTH, _shift, 180, SpringLayout.NORTH, contain);
		// search
		sprintlayout_contain.putConstraint(SpringLayout.WEST, search, 90, SpringLayout.WEST, contain);
		sprintlayout_contain.putConstraint(SpringLayout.NORTH, search, 230, SpringLayout.NORTH, contain);
		
		//table
		sprintlayout_contain.putConstraint(SpringLayout.WEST, table.getTableHeader(), 80, SpringLayout.WEST, contain);
		sprintlayout_contain.putConstraint(SpringLayout.NORTH, table.getTableHeader(), 200, SpringLayout.NORTH, contain);
		
		sprintlayout_contain.putConstraint(SpringLayout.WEST, scrollPane, 80, SpringLayout.WEST, contain);
		sprintlayout_contain.putConstraint(SpringLayout.NORTH, scrollPane, 330, SpringLayout.NORTH, contain);
		
		contain.add(scrollPane, BorderLayout.CENTER);
		contain.add(table.getTableHeader());
		contain.add(search);
		contain.add(shift);
		contain.add(_shift);
		contain.add(_date);
		contain.add(date);
		contain.add(theme);
		
		
		
		
		jfrm.add(screen);
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new scheduleShift();
			}
		}); 
	}
	
}
