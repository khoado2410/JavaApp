package Controller.ControlMenuTable;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import Model.Food_Product.Bill;
import Model.Food_Product.Table;

public class ControllerTable implements ActionListener {
	private JPanel listTablePanel;
	private JLabel numUsedTableLabel;
	private ArrayList<Table> listTable;
	private ControllerBill controllerBill;
	private JPanel listFoodInBillPanel;
	private JLabel idTable;
	public ControllerTable(JLabel usedTableLabel, JPanel liTablePanel, JLabel idTable, JPanel liFIBPanel) {
		listTablePanel = liTablePanel;
		listTablePanel.setLayout(new FlowLayout(FlowLayout.LEADING, 15, 5));
		numUsedTableLabel = usedTableLabel;
		this.idTable = idTable;
		listFoodInBillPanel = liFIBPanel;
		Table table = new Table();
		listTable = table.loadTableFromDB();
		numUsedTableLabel.setText("Used " + countTableInUse() + "/" + Integer.toString(listTable.size()));
	}

	public void loadListTable() {
		for (Table t: listTable) {
			JPanel tablePanel = new JPanel();
			tablePanel.setLayout(new BorderLayout());
			tablePanel.setPreferredSize(new Dimension(100, 100));
			JButton resButton = new JButton();
			ImageIcon resIcon = new ImageIcon("./src/Icon/restaurant.png");
			resButton.setIcon(resIcon);
			resButton.setBackground(Color.WHITE);
			resButton.addActionListener(this);
			resButton.setActionCommand(t.getIdTable());
			JLabel nameTableLabel = new JLabel("", SwingConstants.CENTER);
			tablePanel.add(resButton, BorderLayout.CENTER);
			tablePanel.add(nameTableLabel, BorderLayout.SOUTH);
			nameTableLabel.setText("Table " + t.getIdTable());
			listTablePanel.add(tablePanel);
			listTablePanel.revalidate();
			listTablePanel.repaint();
		}
	}

	public int countTableInUse() {
		int count = 0;
		for (Table t: listTable) {
			if (t.getStatus() != 0)
				count++;
		}
		return count;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Bill b = new Bill(listTable.get(Integer.parseInt(e.getActionCommand().trim()) - 1).getIdBill());
		controllerBill = new ControllerBill(b, listFoodInBillPanel, listTable.get(Integer.parseInt(e.getActionCommand().trim()) - 1));
		controllerBill.loadBill();
		idTable.setText("Table " + e.getActionCommand());
	}
}
