package Staff_Manager;

public class Staff {
	private String StaffID;
	private String StaffName;
	private String DateOfBirth;
	private String Gender;
	private int Salary;
	private int Point;
	private String Address;
	public Staff() {
		this.Salary = 0;
		this.Point = 0;
	}
	public Staff(String id, String sname, String dob, String addr, String gen, int sal, int p) {
		this.StaffID = id;
		this.StaffName = sname;
		this.DateOfBirth = dob;
		this.Gender = gen;
		this.Salary = sal;
		this.Address = addr;
		this.Point = p;
	}
	public String getStaffID() {
		return StaffID;
	}
	public void setStaffID(String staffID) {
		StaffID = staffID;
	}
	public String getStaffName() {
		return StaffName;
	}
	public void setStaffName(String staffName) {
		StaffName = staffName;
	}
	public String getDateOfBirth() {
		return DateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public int getSalary() {
		return Salary;
	}
	public void setSalary(int salary) {
		Salary = salary;
	}
	public int getPoint() {
		return Point;
	}
	public void setPoint(int point) {
		Point = point;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
}
