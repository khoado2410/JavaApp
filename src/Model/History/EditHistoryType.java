package Model.History;

public class EditHistoryType {
	private String EditHistoryTypeID;
	private String EditHistoryTypeName;
	
	public EditHistoryType(String editHistoryTypeID, String editHistoryTypeName) {
		EditHistoryTypeID = editHistoryTypeID;
		EditHistoryTypeName = editHistoryTypeName;
	}
	public String getEditHistoryTypeID() {
		return EditHistoryTypeID;
	}

	public void setEditHistoryTypeID(String editHistoryTypeID) {
		EditHistoryTypeID = editHistoryTypeID;
	}

	public String getEditHistoryTypeName() {
		return EditHistoryTypeName;
	}

	public void setEditHistoryTypeName(String editHistoryTypeName) {
		EditHistoryTypeName = editHistoryTypeName;
	}
}
