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
	
	public void NhapThongTinNhanVien() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Nhap ID: ");
		this.ID =  scan.nextLine();
		System.out.println("Nhap ho ten: ");
		this.name = scan.nextLine();
		System.out.println("Nhap birthday: ");
		this.birthDay = scan.nextLine();
		System.out.println("Nhap dia chi: ");
		this.address = scan.nextLine();
	}
	
	public void XuatThongTin() {
		System.out.println("ID: " + this.ID);
		System.out.println("Name: " + this.name);
		System.out.println("Birthday: " + this.birthDay);
		System.out.println("Address: " + this.address);
		System.out.println("Type: NhanVien");
	}
	
	public void logIn() {
		System.out.println("Log in thanh cong");
	}
	
	public void logOut() {
		System.out.println("Log out thanh cong");
	}
	
	public void	chooseTable() {
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