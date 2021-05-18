package Controller.EditHistoryData;

import java.util.ArrayList;

import Model.Food_Product.Food;
import Model.History.EditHistory;
import View.Frame.EditHistoryData;

public class ControllerEditHistory {
	
	private EditHistoryData historyUI;
	
	private EditHistory editHistoryModel;
	
	public ControllerEditHistory(EditHistoryData editHistoryData) {
		this.historyUI = editHistoryData;
		this.editHistoryModel = new EditHistory();
	}
	
	
	public ArrayList<EditHistory> loadListHistory(){
		ArrayList<EditHistory> listFood = new ArrayList<EditHistory>();
		if(this.editHistoryModel.loadEditHistory()) {
			listFood = this.editHistoryModel.getListHistory();
		}
		
		return listFood;
	}
	
}
