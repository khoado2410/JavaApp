package History;

public class EditHistoryData {
	private String EditHistoryDataID;
	private String EditHistoryDataName;
	
	public EditHistoryData(String editHistoryDataID, String editHistoryDataName) {
		EditHistoryDataID = editHistoryDataID;
		EditHistoryDataName = editHistoryDataName;
	}
	public String getEditHistoryDataID() {
		return EditHistoryDataID;
	}
	public void setEditHistoryDataID(String editHistoryDataID) {
		EditHistoryDataID = editHistoryDataID;
	}
	public String getEditHistoryDataName() {
		return EditHistoryDataName;
	}
	public void setEditHistoryDataName(String editHistoryDataName) {
		EditHistoryDataName = editHistoryDataName;
	}
}
