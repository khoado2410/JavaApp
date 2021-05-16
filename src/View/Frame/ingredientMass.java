package View.Frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


public class ingredientMass extends JFrame implements ActionListener{
	
	private ManageMenuAndProduct controllProduct = new ManageMenuAndProduct(this);
	public String type;
	JFrame jfm = new JFrame();
	public static JButton edit;
	public static JButton remove;
	public static JTable mytable = new JTable();
	public DefaultTableModel defaultModel;
	public static ArrayList<Product> listProduct;
	public static ArrayList<Product> lstIngredientOld;
	
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

	
	public ingredientMass(ArrayList<String> list, String idFood, String type) {
		
		this.type = type;
		
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		jfm = new JFrame();
		jfm.setSize(700, 500);
		//jfm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfm.setLocationRelativeTo(null);
		jfm.setTitle("Detail Bill");
		
		JPanel formContent = new JPanel(new MigLayout("align 50%"));
		formContent.setPreferredSize(new Dimension(screenSize.width - 200, 200));
		formContent.setBackground(Color.white);
		
		JLabel idFoodLabel  = new JLabel("Food's ID");
		idFoodLabel.setHorizontalAlignment(JLabel.LEFT);
		idFoodLabel.setFont(idFoodLabel.getFont().deriveFont(Font.PLAIN, 20));

		billIDField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		billIDField.setFont(billIDField.getFont().deriveFont(Font.PLAIN, 20));
		billIDField.setText(idFood);
		
		JLabel billID = new JLabel("Enter mass of ingredient");
		billID.setHorizontalAlignment(JLabel.LEFT);
		billID.setFont(billID.getFont().deriveFont(Font.PLAIN, 20));
		
		JLabel content12 = new JLabel("						");
		content12.setHorizontalAlignment(JLabel.LEFT);
		content12.setFont(content12.getFont().deriveFont(Font.PLAIN, 20));

		
		formContent.add(idFoodLabel);
		formContent.add(billIDField, "wrap 30");
		
		formContent.add(billID);
		formContent.add(content12, "wrap 30");
		

		
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
				
				defaultModel.addColumn("Ingredient");
				defaultModel.addColumn("Mass");
				
				if(type == "add") {
					for(String d : list) {
						defaultModel.addRow(new Object[] 
									{d, 0});
					}
				}else {
					listProduct = new ArrayList<Product>();
					lstIngredientOld = listProduct;
					listProduct	= this.controllProduct.getListProductForFood(idFood);
					for(Product d : listProduct) {
						defaultModel.addRow(new Object[] 
									{d.getNameProduct(), d.getMass()});
					}
				}

					       				
				mytable.setRowHeight(50);
				
				JScrollPane scrollPane = new JScrollPane(mytable);
				scrollPane.setPreferredSize(new Dimension(400, 100));
				
				DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
				centerRenderer.setHorizontalAlignment( JLabel.CENTER );
				DefaultTableCellRenderer bgcolor = new DefaultTableCellRenderer();
				bgcolor.setBackground(Color.white);
				
				for(int i = 0; i < 2; i++) {
					mytable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
				}				

				mytable.setFillsViewportHeight(true);
				mytable.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
				mytable.getTableHeader().setPreferredSize(new Dimension(50, 50));
				content.add(scrollPane, BorderLayout.CENTER);
				
				JPanel buttonField = new JPanel(new MigLayout("align 50%"));
				buttonField.setPreferredSize(new Dimension(screenSize.width - 200, 100));
				buttonField.setBackground(Color.white);

				JButton saveBtn = new JButton("Save");
				saveBtn.setBackground(Color.PINK);
				saveBtn.setForeground(Color.BLACK);
				saveBtn.setPreferredSize(new Dimension(100, 40));
				saveBtn.setFont(saveBtn.getFont().deriveFont(Font.PLAIN, 20));
				
				saveBtn.addActionListener(this);

				JButton cancelBtn = new JButton("Cancel");
				cancelBtn.setBackground(Color.BLACK);
				cancelBtn.setForeground(Color.WHITE);
				cancelBtn.setPreferredSize(new Dimension(100, 40));
				cancelBtn.setFont(saveBtn.getFont().deriveFont(Font.PLAIN, 20));
				
				cancelBtn.addActionListener(this);
				
				buttonField.add(saveBtn);
				buttonField.add(Box.createHorizontalStrut(150));
				buttonField.add(cancelBtn);
				
				jfm.setLayout(new BoxLayout(jfm.getContentPane(), BoxLayout.Y_AXIS));
				jfm.add(formContent);
				jfm.add(content);
				jfm.add(buttonField);
				jfm.setVisible(true);
	}
	
	public static void main(String args[]) {
		//ingredientMass a = new ingredientMass();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String com = e.getActionCommand().toString();
		
		if(com.equals("Save")) {
			ArrayList<Integer> massList = new ArrayList<Integer>();
			ArrayList<String> nameFood = new ArrayList<String>();
			
			DefaultTableModel model = (DefaultTableModel)mytable.getModel();
			for(int i = 0; i < model.getRowCount(); i++) {
				nameFood.add(model.getValueAt(i, 0).toString());
			}
			for(int i = 0; i < model.getRowCount(); i++) {
				massList.add(Integer.parseInt(model.getValueAt(i, 1).toString()));
			}
			if(this.type == "add") {
	
				this.controllProduct.addIngredient(nameFood, massList, this.billIDField.getText());
				this.jfm.dispose();
			}else {

				this.controllProduct.updateIngredient(listProduct, massList, this.billIDField.getText());
				this.jfm.dispose();
			}
			
		}else if(com.equals("Cancel")) {
			this.jfm.dispose();
		}
		
	}
	
}
