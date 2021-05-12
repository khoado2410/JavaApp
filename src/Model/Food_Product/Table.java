package Model.Food_Product;

public class Table {
	private String idTable;
	private int numClients;
	private int status;
	
	public Table(String id, int num, int status) {
		this.idTable = id;
		this.numClients = num;
		this.status = status;
	}
	
	public String getIdTable() {
		return idTable;
	}
	public void setIdTable(String idTable) {
		this.idTable = idTable;
	}
	public int getNumClients() {
		return numClients;
	}
	public void setNumClients(int numClients) {
		this.numClients = numClients;
	}
	
	public int isStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
