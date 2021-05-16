package View.Frame;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.table.*;

import javax.swing.*;
import javax.swing.border.Border;

import Controller.CashBook.ControllerCashBook;
import Controller.MenuAndProduct.ManageMenuAndProduct;
import Model.Food_Product.Bill;
import Model.Food_Product.Food;
import Model.Food_Product.Product;
import View.form.editProductForm;
import net.miginfocom.swing.MigLayout;


public class DetailBill extends JFrame{
	
	JFrame jfm = new JFrame();
	public static JButton edit;
	public static JButton remove;
	public static JTable mytable = new JTable();
	public DefaultTableModel defaultModel;
	public static ArrayList<Bill> listBill;
	
	public JLabel billIDField = new JLabel();
	public JLabel  staffNameField = new JLabel();
	public JLabel checkIn = new JLabel();
	public JLabel checkOut = new JLabel();
	public JLabel totalBill = new JLabel();
	
	private ControllerCashBook con;
	
	public static void refreshBill(ArrayList<Food> lst) {
		DefaultTableModel model = (DefaultTableModel)mytable.getModel();
		model.setNumRows(0);
		for(Food food : lst) {
			model.addRow(new Object[] {food.getFoodID(), food.getNameFood(), food.getPrice(), food.getQuantityOfStock()});
		}
	}
	
	
	public DetailBill(String id) {
		con = new ControllerCashBook(this); 
		con.loadDetailBill(id);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		jfm = new JFrame();
		jfm.setSize(700, 500);
		//jfm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfm.setLocationRelativeTo(null);
		jfm.setTitle("Detail Bill");
		
		JPanel formContent = new JPanel(new MigLayout("align 50%"));
		formContent.setPreferredSize(new Dimension(screenSize.width - 200, 200));
		formContent.setBackground(Color.white);
		
		JLabel billID = new JLabel("Bill's ID");
		billID.setHorizontalAlignment(JLabel.LEFT);
		billID.setFont(billID.getFont().deriveFont(Font.PLAIN, 20));

		billIDField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		billIDField.setFont(billIDField.getFont().deriveFont(Font.PLAIN, 20));
		
		
		JLabel BillName = new JLabel("Staff Name: ");
		BillName.setHorizontalAlignment(JLabel.LEFT);
		BillName.setFont(BillName.getFont().deriveFont(Font.PLAIN, 20));

		staffNameField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		staffNameField.setFont(staffNameField.getFont().deriveFont(Font.PLAIN, 20));
		
		JLabel checkInLabel = new JLabel("Check In Hour: ");
		checkInLabel.setHorizontalAlignment(JLabel.LEFT);
		checkInLabel.setFont(checkInLabel.getFont().deriveFont(Font.PLAIN, 20));

		checkIn.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		checkIn.setFont(checkIn.getFont().deriveFont(Font.PLAIN, 20));
		
		JLabel checkOutLabel = new JLabel("Check Out Hour: ");
		checkOutLabel.setHorizontalAlignment(JLabel.LEFT);
		checkOutLabel.setFont(checkOutLabel.getFont().deriveFont(Font.PLAIN, 20));

		checkOut.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		checkOut.setFont(checkOut.getFont().deriveFont(Font.PLAIN, 20));
		
		checkOutLabel.setHorizontalAlignment(JLabel.LEFT);
		checkOutLabel.setFont(checkOutLabel.getFont().deriveFont(Font.PLAIN, 20));
		formContent.add(billID);
		formContent.add(billIDField, "wrap 30");
		formContent.add(BillName);
		formContent.add(staffNameField, "wrap 30");
		formContent.add(checkInLabel);
		formContent.add(checkIn, "wrap 30");
		formContent.add(checkOutLabel);
		formContent.add(checkOut, "wrap 30");

		
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
				
				defaultModel.addColumn("FoodID");
				defaultModel.addColumn("Food");
				defaultModel.addColumn("Price");
				defaultModel.addColumn("Amount");
				
				
				ArrayList<Food> lis = new ArrayList<Food>();
				lis = this.con.loadFoodForDetailBill(id);
	
				for(Food food : lis) {
					defaultModel.addRow(new Object[] {food.getFoodID(), food.getNameFood(), food.getPrice(), food.getQuantityOfStock()});
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

				mytable.setFillsViewportHeight(true);
				mytable.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
				mytable.getTableHeader().setPreferredSize(new Dimension(50, 50));
				
				
				content.add(scrollPane, BorderLayout.CENTER);
				
				jfm.setLayout(new BoxLayout(jfm.getContentPane(), BoxLayout.Y_AXIS));
				jfm.add(formContent);
				
				jfm.add(content);
				jfm.setVisible(true);
	}
	
	public static void main(String args[]) {
		//DetailBill a = new DetailBill();
	}
	
}
