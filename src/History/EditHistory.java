package History;

import java.sql.Date;

public class EditHistory {
	private String EditHistoryID;
	private String AccountManagerID;
	private Date TimeEdit;
	private String EditHistoryDataID;
	private String EditHistoryTypeID;
	
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
	public Date getTimeEdit() {
		return TimeEdit;
	}
	public void setTimeEdit(Date timeEdit) {
		TimeEdit = timeEdit;
	}
	public String getEditHistoryDataID() {
		return EditHistoryDataID;
	}
	public void setEditHistoryDataID(String editHistoryDataID) {
		EditHistoryDataID = editHistoryDataID;
	}
	public String getEditHistoryTypeID() {
		return EditHistoryTypeID;
	}
	public void setEditHistoryTypeID(String editHistoryTypeID) {
		EditHistoryTypeID = editHistoryTypeID;
	}
	public EditHistory(String editHistoryID, String accountManagerID, Date timeEdit, String editHistoryDataID,
			String editHistoryTypeID) {
		EditHistoryID = editHistoryID;
		AccountManagerID = accountManagerID;
		TimeEdit = timeEdit;
		EditHistoryDataID = editHistoryDataID;
		EditHistoryTypeID = editHistoryTypeID;
	}
}
