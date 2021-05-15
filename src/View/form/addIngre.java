package View.form;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;

import Controller.DBConnection.*;
import Controller.MenuAndProduct.ManageMenuAndProduct;
import net.miginfocom.swing.MigLayout;

public class addIngre extends JFrame implements ActionListener, ItemListener{
	
	public String type;
	
	ManageMenuAndProduct controllerIngre = new ManageMenuAndProduct(this);
    public ArrayList<String> ingreList = new ArrayList<>();
    public JCheckBox boxes[];
    public ArrayList<String> listOption = new ArrayList<String>();
    
    private JPanel mainFramePanel;
	private JPanel title;
	private JLabel formTitle;
    private JPanel content;
	private JPanel buttonField;
	private JButton saveBtn;
	private JButton cancelBtn;
	private static ArrayList<String> checkedList = new ArrayList<>();
    
    public addIngre(String type) {
    	
    	this.type = type;
    	
    	mainFramePanel = new JPanel();
		mainFramePanel.setLayout(new FlowLayout(1, 0, 0));
		mainFramePanel.setBackground(Color.white);
		
		content = new JPanel(new GridLayout(20, 2));
    	content.setBorder(new EmptyBorder(5,5,5,5));
    	content.setPreferredSize(new Dimension(680, 600));
    	content.setBackground(Color.white);
    	
    	controllerIngre.loadIngredient();
    	
        boxes = new JCheckBox[ingreList.size()];

        for (int i = 0; i < ingreList.size(); i++) {
            createrCheckBox(i);
            
        }

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(750, 730);
        setTitle("Vlastna Pizza");
		
		buttonField = new JPanel(new MigLayout("align 50%"));
		buttonField.setPreferredSize(new Dimension(screenSize.width - 200, 100));
		buttonField.setBackground(Color.white);

		saveBtn = new JButton("Save");
		saveBtn.setBackground(Color.PINK);
		saveBtn.setForeground(Color.BLACK);
		saveBtn.setPreferredSize(new Dimension(60, 20));
		saveBtn.setFont(saveBtn.getFont().deriveFont(Font.PLAIN, 14));

		cancelBtn = new JButton("Cancel");
		cancelBtn.setBackground(Color.BLACK);
		cancelBtn.setForeground(Color.WHITE);
		cancelBtn.setPreferredSize(new Dimension(60, 20));
		cancelBtn.setFont(saveBtn.getFont().deriveFont(Font.PLAIN, 14));
		
		saveBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		
		buttonField.add(saveBtn);
		buttonField.add(Box.createHorizontalStrut(150));
		buttonField.add(cancelBtn);
	    
		mainFramePanel.add(content);
		mainFramePanel.add(buttonField);
		
		setContentPane(mainFramePanel);
		
		setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void createrCheckBox(int i) {
        boxes[i] = new JCheckBox(ingreList.get(i));
        boxes[i].setBorder(new EmptyBorder(0,0,0,0));
        boxes[i].setBackground(Color.white);
        content.add(boxes[i]);
        boxes[i].addItemListener(this);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String com = e.getActionCommand().toString();
		if(com.equals("Save")) {
			
			if(this.type == "edit") {
				controllerIngre.passDataToEditFood(this.listOption);
			}else {
				controllerIngre.passDataToFormFood(this.listOption);
			}
			//controllerIngre.passDataToEditFood(this.listOption);
			this.dispose();
		}
		else if(com.equals("Cancel")) {
			this.dispose();
		}
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
		String str = "";
		JCheckBox cb = (JCheckBox) e.getItem();
		
		this.listOption.add(cb.getText());
		
	}

    }