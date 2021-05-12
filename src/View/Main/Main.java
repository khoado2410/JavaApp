//package Main;
//
//import java.util.Dictionary;
//import java.util.Hashtable;
//import Food_Product.Bill;
//import Food_Product.Food;
//import Food_Product.Table;
//import Staff_Manager.Manager_Budget;
//import Staff_Manager.Manager_Menu;
//import Staff_Manager.Manager_Staff;
//import Staff_Manager.Staff;
//import Staff_Manager.AccountManager;
//
//public class Main {
////	public static void main(String[] args)
////	        throws java.io.IOException{
////				
////				System.out.println("Mot so chuc nang DEMO");
////	            System.out.println("Help on: \n\t1. Them nhan vien\n\t2. Them mon an \n\t3. Tong doanh thu \n\t4. Tong chi tieu\n\t5. Chinh sua food\n\t6. Xoa food\n\t7. Tim kiem\n\t8. Dang nhap \n\t9. Choose table\n\tm. Order_ThemBill\n\tk.Print Bill\nEnter: \n(press q to quit!)");
////	            char check, ignore, choose;
////	            do{
////	                choose = (char) System.in.read();
////	                do{
////	                    ignore = (char)System.in.read();
////	                }while(ignore != '\n');
////	                switch(choose){
////	                    case '1':
////	                    	Manager_Staff manager_Staff = new Manager_Staff();
////	                        Staff staff = new Staff("S10000", "Khoa Do Minh", "10/24/2000", "HCM", "Male", 20, 10);
////	                        manager_Staff.addStaff(staff);
////	                        break;
////	                    case '2': 
////	                        Manager_Menu a = new Manager_Menu();
////	                        a.addFood(new Food("F2", "Vang", 80000, "1", 3));
////	                        break;
////	                    case '3':
////	                    	Manager_Budget b = new Manager_Budget();
////	                    	b.sumBudget();
////	                    	break;
////	                    case '4':
////	                    	Manager_Budget c = new Manager_Budget();
////	                    	c.sumSpending();
////	                    	break;
////	                    case '5':
////	                    	Manager_Menu edit = new Manager_Menu();
////	                        edit.editFood(new Food("F1", "BeefSteak", 100000, "1", 3), 100000);
////	                        break;
////	                    case '6':
////	                    	Manager_Menu del = new Manager_Menu();
////	                        del.deleteFood(new Food("F1", "BeefSteak", 100000, "1", 3));
////	                        break;
////	                    case '7':
////	                    	Manager_Menu se = new Manager_Menu();
////	                        se.SearchFoodfromMenu("Vang");
////	                        break;
////	                    case '8':
////	                    	AccountManager acc = new AccountManager();
////	                        acc.logIn("manager001", "11111111");
////	                        break;
////	                    case '9':
////	                    	AccountManager choosse = new AccountManager();
////	                        choosse.ChooseTablee(new Table("T2", 1, 4));
////	                        break;
////	                    case 'm':
////	                    	Dictionary<String, String> listFood = new Hashtable<String, String>();
////
////	        				listFood.put("3", "F0001");
////	        			    listFood.put("2", "F0002");
////	        			    listFood.put("4", "F0003");
////	        			  
////	        			    AccountManager d = new AccountManager();
////	        			    d.OrderFood(new Bill("B2", "S001", 100000, 1, "T1", "1998-02-18", "1998-02-18", "M001"), listFood);
////	        			    break;
////	                    case 'k':
////	                    	AccountManager k = new AccountManager();
////	                    	Bill f = new Bill();
////	                    	k.printBill(f, "B1");
////	                        break;
////	                    case 'q':
////	                        System.out.println("Quit!");
////	                        break;                  
////	                    default:
////	                        System.out.println("Selection not found.");
////	                        break;
////	                }
////	                check = choose;
////	            }while(check != 'q');
////	    }
//}
