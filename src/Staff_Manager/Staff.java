package Staff_Manager;

import java.util.Scanner;

public class Staff {
	protected String ID;
	protected String name;
	protected String birthDay;
	protected String address;
	protected String type = "0";

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void inputStaff() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter ID: ");
		this.ID = scan.nextLine();
		System.out.println("Enter name: ");
		this.name = scan.nextLine();
		System.out.println("Enter date of birth: ");
		this.birthDay = scan.nextLine();
		System.out.println("Enter address: ");
		this.address = scan.nextLine();
	}

	public void printStaff() {
		System.out.println("ID: " + this.ID);
		System.out.println("Name: " + this.name);
		System.out.println("Birthday: " + this.birthDay);
		System.out.println("Address: " + this.address);
		System.out.println("Type: Staff");
	}

	public void logIn() {
		System.out.println("Logged in successfully!");
	}

	public void logOut() {
		System.out.println("Logged out successfully!");
	}

	public void chooseTable() {
		System.out.println("Chose table successfully!");
	}

	public void orderFood() {
		System.out.println("Ordered food successfully!");
	}

	public void orderTable() {
		System.out.println("Ordered table successfully!");
	}

	public void printBill() {
		System.out.println("Print Bill successfully!");
	}

	public void payBill() {
		System.out.println("Successful payment!");
	}

}
