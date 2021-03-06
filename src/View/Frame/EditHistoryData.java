package View.Frame;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import Controller.EditHistoryData.ControllerEditHistory;
import Controller.MenuAndProduct.ManageMenuAndProduct;
import Controller.PanelChange.ControllerPanel;
import Model.Food_Product.Product;
import Model.History.EditHistory;




public class EditHistoryData extends JPanel{
	
	JPanel panelSpending = new JPanel();

	public JPanel top;
	public JPanel content;
	public static JButton edit;
	public static JButton delete;
	public static JTable mytable = new JTable();
	public DefaultTableModel defaultModel = new DefaultTableModel();
	public static ArrayList<EditHistory> listHistory;
	public JButton add;
	private ControllerPanel controllerPanel;
	public ControllerPanel control = new ControllerPanel(this);
	
	private ControllerEditHistory controllerHistory = new ControllerEditHistory(this);
	
	
	public EditHistoryData() {
		
		listHistory = new ArrayList<EditHistory>();
		listHistory = this.controllerHistory.loadListHistory();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		
		JPanel navbar = new JPanel();
		navbar.setPreferredSize(new Dimension(100, 70));

		JLabel abc = new JLabel("<html><span style='font-size:25px'>List of edithistoryData</span></html>");
		JLabel acd = new JLabel();
		JLabel aaa = new JLabel();

		navbar.setLayout(new GridLayout(1, 3));
		navbar.add(acd);
		navbar.add(abc);
		navbar.add(aaa);
		
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
		
		
		SpringLayout springlayout = new SpringLayout();
		left.setLayout(springlayout);
		springlayout.putConstraint(SpringLayout.WEST, icon, 0, SpringLayout.WEST, left);
		springlayout.putConstraint(SpringLayout.NORTH, icon, 0, SpringLayout.NORTH, left);
		left.add(icon);
		
		
		
		
		JPanel center = new JPanel();
		center.setBackground(new Color(255, 255, 255));
		
		JPanel right = new JPanel();
		right.setBackground(new Color(255, 255, 255));
		JLabel add = new JLabel("<html><span style='font-size:18px'></span></html>");
		add.setOpaque(true);
		
		//add.setText("Total of spending: " + this.controller.sumSpending(listProduct));
		add.setHorizontalAlignment(JLabel.CENTER);
		add.setFont(add.getFont().deriveFont(Font.PLAIN, 25));
		
		
		SpringLayout springlayout1 = new SpringLayout();
		right.setLayout(springlayout1);
		springlayout1.putConstraint(SpringLayout.WEST, add, 150, SpringLayout.WEST, right);
		springlayout1.putConstraint(SpringLayout.SOUTH, add, -30 , SpringLayout.SOUTH, right);
		right.add(add);
		
		top.add(left);
		top.add(center);
		top.add(right);
		
				JPanel content = new JPanel();
				content.setLayout(new BorderLayout());
				content.setPreferredSize(new Dimension(100, 400));
			
				// JLabel chua 2 icon
				defaultModel = new DefaultTableModel();
				mytable.setModel(defaultModel);
				
				defaultModel.addColumn("EditHistoryID");
				defaultModel.addColumn("AccountManagerID");
				defaultModel.addColumn("TimeEdit");
				defaultModel.addColumn("EditHistoryType");
				defaultModel.addColumn("EditHistoryData");
				
				for(EditHistory product : listHistory) {
					defaultModel.addRow(new Object[] {product.getEditHistoryID(), product.getAccountManagerID(), product.getTimeEdit(), 
											product.getEditHistoryType(), product.getEditHistoryData()});
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
				
				mytable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);				
				
				
				mytable.setFillsViewportHeight(true);
				mytable.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
				mytable.getTableHeader().setPreferredSize(new Dimension(50, 50));
				
				content.add(scrollPane, BorderLayout.CENTER);
	
				setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
				add(navbar);
				add(top);
				add(content);		
				setVisible(true);
		
	}
	
	
	

}
