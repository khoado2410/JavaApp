package Controller.ControlMenuTable;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import Controller.PanelChange.ControllerPanel;
import Model.Food_Product.Bill;
import Model.Food_Product.Food;
import Model.Food_Product.Table;

public class ControllerTable implements ActionListener {
	private JPanel listTablePanel;
	private JLabel numUsedTableLabel;
	private ArrayList<Table> listTable;
	private ControllerBill controllerBill;
	private JPanel listFoodInBillPanel;
	private JLabel idTable;
	private Bill bill;
	private ControllerPanel controllerPanel;
	private int temp;
	public ControllerTable(JLabel usedTableLabel, JPanel liTablePanel, JLabel idTable, JPanel liFIBPanel, ControllerPanel cp) {
		listTablePanel = liTablePanel;
		listTablePanel.setLayout(new FlowLayout(FlowLayout.LEADING, 15, 5));
		numUsedTableLabel = usedTableLabel;
		this.idTable = idTable;
		listFoodInBillPanel = liFIBPanel;
		Table table = new Table();
		listTable = table.loadTableFromDB();
		numUsedTableLabel.setText("Used " + countTableInUse() + "/" + Integer.toString(listTable.size()));
		controllerPanel = cp;
		bill = new Bill();
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
			if (t.getStatus() == 0)
				count++;
		}
		return count;
	}
	public Bill getBillFromTable() {
		return this.bill;
	}
	
	public void updateTableStatus() {
		if (bill.getListFood().size() != 0) {
			listTable.get(Integer.parseInt(bill.getTableID()) - 1).updateTableToDB(bill);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		bill = new Bill(listTable.get(Integer.parseInt(e.getActionCommand().trim()) - 1).getIdBill());
		controllerBill = new ControllerBill(bill, listFoodInBillPanel, listTable.get(Integer.parseInt(e.getActionCommand().trim()) - 1));
		controllerBill.loadBill();
		controllerPanel.setControllerBill(controllerBill, this, listFoodInBillPanel, idTable);
		idTable.setText("Table " + e.getActionCommand());
	}
}
