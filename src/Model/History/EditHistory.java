package Model.History;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Controller.DBConnection.DBConnection;
import Model.Food_Product.Food;

public class EditHistory {
	private String EditHistoryID;
	private String AccountManagerID;
	private String TimeEdit;
	private String EditHistoryData;
	private String EditHistoryType;
	
	private ArrayList<EditHistory> listHistory;
	
	public EditHistory() {
		this.listHistory = new ArrayList<EditHistory>();
	}
	
	public ArrayList<EditHistory> getListHistory() {
		return listHistory;
	}
	public void setListHistory(ArrayList<EditHistory> listHistory) {
		this.listHistory = listHistory;
	}
	public String getEditHistoryID() {
		return EditHistoryID;
	}
	public void setEditHistoryID(String editHistoryID) {
		EditHistoryID = editHistoryID;
	}
	public String getAccountManagerID() {
		return AccountManagerID;
	}
	public void setAccountManagerID(String accountManagerID) {
		AccountManagerID = accountManagerID;
	}
	public String getTimeEdit() {
		return TimeEdit;
	}
	public void setTimeEdit(String timeEdit) {
		TimeEdit = timeEdit;
	}
	public String getEditHistoryData() {
		return EditHistoryData;
	}
	public void setEditHistoryData(String editHistoryData) {
		EditHistoryData = editHistoryData;
	}
	public String getEditHistoryType() {
		return EditHistoryType;
	}
	public void setEditHistoryType(String editHistoryType) {
		EditHistoryType = editHistoryType;
	}
	
	public EditHistory(String id, String idAcc, String timeEdit, String editType, String editData) {
		this.EditHistoryID = id;
		this.AccountManagerID = idAcc;
		this.TimeEdit = timeEdit;
		this.EditHistoryType = editType;
		this.EditHistoryData = editData;
	}
	
	public EditHistory(String idAcc, String timeEdit, String editType, String editData) {
		this.AccountManagerID = idAcc;
		this.TimeEdit = timeEdit;
		this.EditHistoryType = editType;
		this.EditHistoryData = editData;
	}
	

	public EditHistory(String idAcc,String editType, String editData) {
		this.AccountManagerID = idAcc;
		this.EditHistoryType = editType;
		this.EditHistoryData = editData;
	}
	
	public boolean addEditHistory(EditHistory a) {
		String querySql = "{call addEditHistory(?, ?, ?, ?)}";
		java.util.Date date= java.util.Calendar.getInstance().getTime(); 
		DateFormat dateFormatMDY = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		String vDateYMD = dateFormatMDY.format(date);
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)){
			try {
				CallableStatement cstmt = DBConnection.connection.prepareCall(querySql);
				
				cstmt.setString(1, a.getAccountManagerID());
				cstmt.setString(2, vDateYMD);
				cstmt.setString(3, a.getEditHistoryType());
				cstmt.setString(4, a.getEditHistoryData());
				cstmt.executeUpdate();

				return true;
			} catch (SQLException e) {
				System.out.println("Cannot insert food to menu: " + e);
				return false;
			}
		} else {
			System.out.println("Something went wrong!!!");
			return false;
		}
	}
	
	public boolean loadEditHistory() {
		if (DBConnection.loadDriver() && DBConnection.connectDatabase(DBConnection.DB_URL)) {
			try {
				String sp_load = "{call loadEditHistory}";
				CallableStatement statement = DBConnection.connection.prepareCall(sp_load);
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					String id = rs.getString("EditHistoryID");
					String accID = rs.getString("AccountManagerID");
					String editTime = rs.getString("TimeEdit");
					String editType = rs.getString("EditHistoryType");
					String editData = rs.getString("EditHistoryData");
					
					EditHistory b = new EditHistory(id, accID, editTime, editType, editData);
					this.listHistory.add(b);
				}
				statement.close();
				return true;
			} catch (SQLException e) {
				System.out.println("Cannot load type food!!!: " + e);
				return false;
			}
		} else {
			System.out.println("Something went wrong!!!");
			return false;
		}
	}
	
	
	
	public static void main(String args[]) {
		Date now = new Date(0);
		
		java.util.Date date= java.util.Calendar.getInstance().getTime(); 
		System.out.println(date);
		DateFormat dateFormatMDY = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		String vDateYMD = dateFormatMDY.format(date);
//		System.out.println(vDateYMD);
		EditHistory b = new EditHistory();
//		System.out.println("Result: " + b.addEditHistory(b));
//		
		ArrayList<EditHistory> listHis = new ArrayList<EditHistory>();
		if(b.loadEditHistory()) {
			listHis = b.getListHistory();
		}
		
		for(int i = 0; i < listHis.size(); i++) {
			System.out.println(listHis.get(i).getAccountManagerID());
		}
		//String _date = (String)date; 
		
	}
	
}
