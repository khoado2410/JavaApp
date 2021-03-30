package Main;

import Staff_Manager.Manager_Budget;
import Staff_Manager.Manager_Menu;
import Staff_Manager.Manager_Staff;
import Staff_Manager.Staff;

public class Main {
	public static void main(String[] args)
	        throws java.io.IOException{
				System.out.println("Mot so chuc nang DEMO");
	            System.out.println("Help on: \n\t1. Them nhan vien\n\t2. Them mon an \n\t3. Tong doanh thu \n\t4. Tong chi tieu\nEnter: \n(press q to quit!)");
	            char check, ignore, choose;
	            do{
	                choose = (char) System.in.read();
	                do{
	                    ignore = (char)System.in.read();
	                }while(ignore != '\n');
	                switch(choose){
	                    case '1':
	                    	Manager_Staff manager_Staff = new Manager_Staff();
	                        Staff staff = new Staff("S10000", "Khoa Do Minh", "10/24/2000", "HCM", "Male", 20, 10);
	                        manager_Staff.addStaff(staff);
	                        break;
	                    case '2': 
	                        Manager_Menu a = new Manager_Menu();
	                        a.addNewFood();
	                        break;
	                    case '3':
	                    	Manager_Budget b = new Manager_Budget();
	                    	b.sumBudget();
	                    	break;
	                    case '4':
	                    	Manager_Budget c = new Manager_Budget();
	                    	c.sumSpending();
	                    	break;
	                    case 'q':
	                        System.out.println("Quit!");
	                        break;                  
	                    default:
	                        System.out.println("Selection not found.");
	                        break;
	                }
	                check = choose;
	            }while(check != 'q');
	    }
}
