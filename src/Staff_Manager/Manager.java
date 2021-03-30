
package Staff_Manager;

public class Manager {
	protected String AccountManagerID;
	protected String Username;
	protected String Password;

	public String getAccountManagerID() {
		return AccountManagerID;
	}

	public void setAccountManagerID(String accountManagerID) {
		AccountManagerID = accountManagerID;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public void logIn() {
		System.out.println("Log in thanh cong");
	}

	public void logOut() {
		System.out.println("Log out thanh cong");
	}

	public void chooseTable() {
		System.out.println("Chon ban thanh cong !");
	}

	public void orderFood() {
		System.out.println("Order Food thanh cong");
	}

	public void orderTable() {
		System.out.println("Order Table thanh cong");
	}

	public void printBill() {
		System.out.println("Print Bill thanh cong");
	}

	public void payBill() {
		System.out.println("Pay bill thanh cong");
	}
}
