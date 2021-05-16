package Controller.PanelChange;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Controller.ControlMenuTable.ControllerBill;
import Controller.ControlMenuTable.ControllerTable;
import View.Frame.*;

public class ControllerPanel implements ActionListener{
	private String nameAction;
	private JPanel main;
	private JPanel billPanel;
	private JLabel idTable;
	private ControllerBill controllerBill;
	private ControllerTable controllerTable;
	public ControllerBill getControllerBill() {
		return controllerBill;
	}
	public void setControllerBill(ControllerBill controllerBill, ControllerTable controllerTable, JPanel billPanel, JLabel idTable) {
		this.controllerBill = controllerBill;
		this.controllerTable = controllerTable;
		this.billPanel = billPanel;
		this.idTable	= idTable;
	}
	public ControllerPanel(JPanel p) {
		main = p;
	}
	public ControllerPanel(JPanel p, ControllerBill cb) {
		main = p;
		this.controllerBill = cb;
	}
	public void setEventButton(JButton jb, String na) {
		this.nameAction = na;
		jb.addActionListener(this);
		jb.setActionCommand(nameAction);
	}
	public void setPanel(JPanel jp) {
		 main.removeAll();
		 main.add(jp);
		 main.repaint();
		 main.revalidate();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Cashier": {
			setPanel(new TableFrame());
			break;
		}
		case "Menu": {
			setPanel(new MenuFrame(controllerBill, controllerTable, billPanel, idTable));
			break;
		}
		case "Tables":
			controllerBill.createBill();
			setPanel(new TableFrame());
			break;
		case "Home":
			setPanel(new MainFrame());
			break;
		case "Staff": {
			System.out.println("STaf uii");
			setPanel(new Staff_ManagerStaffUI());
			break;
		}
		case "Stock": {
			setPanel(new Product_MenuManagementUI());
			break;
		
			}
		case "buttonPayroll": {
			setPanel(new Staff_PayrollUI());
			break;
		
			}
		case "buttonTimekeeping": {
			setPanel(new Staff_TimekeepingUI());
			break;
		
			}
		case "Cash Book": {
			setPanel(new RevenueUI());
			break;
		
			}
		case "buttonSpending": {
			setPanel(new SpendingUI());
			break;
		
			}

		}}
}


