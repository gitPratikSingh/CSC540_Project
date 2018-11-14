package controller;
import java.util.Scanner;

public class ManagerController {
	
	//Using single scanner to read from entire class
	static Scanner reader = new Scanner(System.in);
	
	public static void defaultPage()
	{
		while(true){
		System.out.println("Please enter number between 1 to 10 ");
		System.out.println("1.ViewProfile ");
		System.out.println("2.Add New Employee");
		System.out.println("3.Payroll");
		System.out.println("4.Inventory");
		System.out.println("5.Notification");
		System.out.println("6.Orders");
		System.out.println("7.New Car Model");
		System.out.println("8.Car Service Details");
		System.out.println("9.Service History");
		System.out.println("10.Invoices");
		System.out.println("11.Sign out");
		
		//take input from the user 
		int n = reader.nextInt();
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
			break;
		case 4:
			inventory();
			break;
		case 5:
			notification();
			break;
		case 6:
			orders();
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
			//close reader 
			reader.close();
			return;
		default:
			
			System.out.println("invalid input");
		}
		//
		}
	
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
	public static void orders()
	{
		while(true) {
		System.out.println("1.Order History ");
		System.out.println("2.New order");
		System.out.println("3.Go Back");
		
		int n = reader.nextInt();
		if(n==1)
		{
			orderHistory();
		}
		if(n==2)
		{
			newOrder();
		}
		if(n==3)
		{
			return;
		}
	  }
	}
	public static void orderHistory()
	{/*display these 
		Order ID
		Date
		Part Name
		Supplier Name
		Purchaser Name
		Quantity
		Unit Price
		Total Cost
		Order Status
		*/
		
		//provide option to go back
		System.out.println("1.Go Back");
		
		int n = reader.nextInt();
		if (n==2)
		{
			
		}
		
	}
	public static void newOrder()
	{
		
	   while(true) {	
		System.out.println("1.Place Order");
		System.out.println("2.Go Back");
		int n = reader.nextInt();
		if (n==1) {
			System.out.println("Placing an order enter the follwing details");
			
			System.out.println("Part ID ");
			int part_id = reader.nextInt();
			
			System.out.println("Quantity");
			int quantity = reader.nextInt();
			
			
			/*Details such as cost, order date, etc. should be automatically calculated and
 the order status must be set as “pending”.After placing the
order, show a confirmation message with the order ID*/
			//function to create the order 
			
			//function to read the order that is just created 
			
			//function to calculate estimated date 
			
			System.out.println("order successfull");
			System.out.println("Order ID ");
			System.out.println("estimated date");
			return;
		}
		if(n==2) {
			return;
		}
	  }
	  
	}
	
	public static void notification()
	{
		while(true) {
		//display these 
		/*
		A. Notification ID
		B. Notification
		Date/Time
		C. Order ID
		D. Supplier Name
		Choose 2 to go back.
		Choose 2 to go back
		to ​ Manager:
		Landing page
		27E. Expected
		Delivery Date
		F. Delayed by (# of
		days)
		*/
		
		//while(true) {
		
		System.out.println("1.Order ID");
		System.out.println("2.Go Back");
		int n = reader.nextInt();
		
		if(n==1)
			notificationDetailsPage();//int order ID 
		if(n==2)
			return;
		}
		//}
	}
	
	public static void notificationDetailsPage()
	{
	/*
		Order ID
		Date
		Part Name
		Supplier Name
		Purchaser Name
		Quantity
		Unit Price
		Total Cost
		Order Status
		*/
		
		System.out.println("1.Go back");
		int n = reader.nextInt();
		if (n==1 )
		{
			return;
		}
	}
	
	public static void inventory()
	{	
		//get inventory details by query
	    /*
		Part ID
		Part Name
		Quantity
		Unit Price
		Minimum
		Quantity
		Threshold
		F. Minimum Order
		Threshold
		*/
		//display each of the parts in the inventory
		System.out.println("Inventory details");
	
		System.out.println("1 Goback ");
		
		int n = reader.nextInt();
		if (n==1)
			return;
	}
	public static void payRoll()
	{
		
		/*A.Paycheck date,Pay period,Employee ID,Employee Name,Compensation ($)
Compensation,Frequency,(monthly/hourly), Units (# of,hours/days),Earnings
(Current),Earnings(Year-to-date)*/
		
		System.out.println("Please enter Employee Payroll");
		int employee_id =reader.nextInt();
		
		//get the details of payroll for the employee 
		
		//format the output
		System.out.println("Pay roll details of employee ");
		
		
		
		System.out.println("1.GoBack");
		int n = reader.nextInt();
		
		if (n==1)
			return;
	
		//System.out.println(x);
	}
	
	public static void addNewEmployee()
	{
		System.out.println("New Employee");
		System.out.println("1.Add");
		System.out.println("2.Go back");
	
		int n = reader.nextInt();
		
		
		if (n==1) {
			//randomly generate the employee ID and set defaulyt password as 12345678
			System.out.println("New Employee ID is Random number ");
			System.out.println("defauly password is 12345678");
			
			//read input from manager for creating employee
			System.out.println("Enter the following employee details");
			System.out.println("A.Name");
			String name = reader.nextLine();
			System.out.println("B.Address");
			String address=reader.nextLine();	
			System.out.println("C.Email Address");
			String Emailaddress=reader.nextLine();
			System.out.println("D.Phone Number");
			String phoneNumber=reader.nextLine();
			System.out.println("E.Role");
			String role=reader.nextLine();
			System.out.println("F.Start Date");
			String startDate=reader.nextLine();
			System.out.println("F.Compensation (in $)");
			Float compensation= reader.nextFloat();
			
			
			//create the employee 
			System.out.println( "employee is created");
			
			//go back to landing page 
			return;
			
		}
		if (n==2)//go back
		{
			return;
		}
			
	}
	

	public static void viewProfile()
	{
		//display the current profile 
		
		//employee ID, phone number Email address, compensation, service centre 
		
		System.out.println("1.Edit My profile");
		System.out.println("2.Go Back");
		
		int n = reader.nextInt();
	
		if(n==2)
			return;
		if(n==1) {
			System.out.println("Please edit");
				
		}
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
