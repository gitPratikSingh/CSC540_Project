package controller;
import java.util.Scanner;

public class ManagerController {
	
	public static void defaultPage()
	{
		System.out.println("Please enter number between 1 to 10 ");
		System.out.println("1.ViewProfile ");
		System.out.println("2.Add New Employee");
		System.out.println("3.Payroll");
		System.out.println("4.Inventory");
		System.out.println("5.Notification");
		System.out.println("6.Order History");
		System.out.println("7.New Car Model");
		System.out.println("8.Car Service Details");
		System.out.println("9.Service History");
		System.out.println("10.Invoices");
		System.out.println("11.Sign out");
		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();
		reader.close();
		
		switch (n)
		{
		case 1:
			viewProfile();
		break;	
		case 2:
			addNewEmployee();
		break;
		case 3:
			payRoll();
		case 4:
			inventory();
		break;
		case 5:
			notification();
		break;
		case 6:
			orderHistory();
		break;
		case 7:
			newCarModel();
		break;
		case 8:
			carServiceDetails();
		break;
		case 9:
			serviceHistory();
		break;
		case 10:
			invoices();
		break;
		case 11:
			System.out.println("Signing off");
			//return;
		default:
			System.out.println("invalid input");
		
		
		
		}
		//
	
	}
	public static void invoices()
	{
		System.out.println("Invoices");
		System.out.println("1.View Invoices");
		System.out.println("2.Go back");
		
	}
	public static void serviceHistory()
	{
		System.out.println("Service History");
	}
	public static void carServiceDetails()
	{
		System.out.println("Car Service Details ");
	}
	
	public static void newCarModel()
	{
		System.out.println("New Car Model");
	}
	public static void orderHistory()
	{
		System.out.println("Order History ");
	}
	public static void notification()
	{
		System.out.println("Notifications");
		System.out.println("1.Display Notification");
		System.out.println("2.Go Back");
		
	}
	
	public static void inventory()
	{
		System.out.println("Inventory");
		System.out.println("1.Inventory details");
		System.out.println("2 Goback ");
	}
	public static void payRoll()
	{
		System.out.println("Employee Payroll");
		//System.out.println(x);
	}
	
	public static void addNewEmployee()
	{
		System.out.println("New Employee");
		System.out.println("1.Add");
		System.out.println("2.Go back");
		
		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();
		reader.close();
		
		switch(n) 
		{
		case 1:
			//take input and put it into argument of create function 
			System.out.println("Enter the following employee details");
			System.out.println("A.Name");
			Scanner readerEmployee = new Scanner(System.in);
			int name = readerEmployee.nextInt();
			
			System.out.println("B.Address");
			String address=readerEmployee.next(pattern);
					
			System.out.println("C.Email Address");
			System.out.println("D.Phone Number");
			System.out.println("E.Role");
			System.out.println("F.Start Date");
			System.out.println("F.Compensation (in $)");
			
			break;
		case 2:
			
			break;
		default:
			
			break;
		}
		
	}
	

	public static void viewProfile()
	{
		System.out.println("1.Edit My profile");
		System.out.println("2.Go Back");
		
		
	}
	
	public static void editProfile() 
	{
		System.out.println("2.Go Back");
		
		
	
		//action go back to default page
	}
	
	public static void main(String [] args) {
	  defaultPage();
		
		
	}


	
	
}
