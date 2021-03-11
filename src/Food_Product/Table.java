package Food_Product;

import java.sql.Date;

public class Table {
	private String idTable;
	private int numClients;
	private Date timeCheckIn;
	private Date timeCheckOut;
	private boolean status;
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
	public Date getTimeCheckIn() {
		return timeCheckIn;
	}
	public void setTimeCheckIn(Date timeCheckIn) {
		this.timeCheckIn = timeCheckIn;
	}
	public Date getTimeCheckOut() {
		return timeCheckOut;
	}
	public void setTimeCheckOut(Date timeCheckOut) {
		this.timeCheckOut = timeCheckOut;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}
