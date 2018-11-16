package controller;
import java.util.Scanner;
import model.Employees;
import model.Users;
import model.Payrolls;


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
		System.out.println("11.View Customer Profile");
		System.out.println("12.Sign out");
		
		
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
			viewCustomerProfile();
			break;
		case 12:
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
	public static void viewCustomerProfile()
	{
		System.out.println("Enter Customer ID");
		int customer_id = reader.nextInt();
		
		//retrieve the customer details"
		
		System.out.println("1.Go back");
		
		int n = reader.nextInt();
		if (n==1)
		{
			return;
		}
		
	}
	
	public static void invoices()
	{
		/* display these 
		Service ID
		B. Customer Name
		C. Service Start
		Date/TimeD. Service End
		Date/Time
		E. Licence Plate
		F. Service Type
		G. Mechanic Name
		H. Parts Used in
		service with cost
		of each part
		I. Total labor hours
		J. Labor wages per
		hour
		K. Total Service
		Cost
		*/
		
		
		System.out.println("1.Go Back");
		int n = reader.nextInt();
		
		//return if 1. 
		if(n==1)
			return;
	}
	public static void serviceHistory()
	{
		System.out.println("Service History");
		/* display all the services of the car 
		Service ID
		Customer Name
		License Plate
		Service Type
		Mechanic Name
		Service Start
		Date/Time
		G. Service End
		Date/Time
		(expected or
		actual)
		H. Service Status
		(Pending,
		Ongoing, or
		Complete)
*/
		
		System.out.println("1.Go Back");
		int n = reader.nextInt();
		
		//return if 1. 
		if(n==1)
			return;
		
	}
	public static void carServiceDetails()
	{
		System.out.println("Car Service Details ");
		//dislay all cars with details of service 
		/*

Make
Model
Year
Service A:
a. Miles
b. Months
c. Parts List
E. Service B
a. Miles
b. Months
c. Additional
Parts
F. Service C
a. Miles
b. Months
c. Additional
Parts
		 */
		
		System.out.println("1.Go Back");
		int n = reader.nextInt();
		
		//return if 1. 
		if(n==1)
			return;
		
		
	}
	
	public static void newCarModel()
	{
		System.out.println("New Car Model");
/*
		Make
		Model
		Year
		Service A:
		a. Miles
		b. Months
		c. Parts List
		E. Service B
		a. Miles
		b. Months
		c. Additional
		Parts
		F. Service C
		a. Miles
		b. Months
		c. Additional
		Parts
	*/	
		System.out.println("Enter the Car Make");
		String car_make = reader.nextLine();
		
		System.out.println("Enter the Model");
		String car_model = reader.nextLine();
		
		System.out.println("Enter the year ");
		int car_year = reader.nextInt();
		
		System.out.println("Enter the Service type (A,B or C");
	    String car_service_type = reader.nextLine();
	    
	    
	    System.out.println("enter the list of part IDs (not more than 100 at a time) ");
	    int part_ids[] = null; 
	    int count=0;
	    while (true) {
	    	
	    part_ids[count]= reader.nextInt();
	    
	    System.out.println("Enter another part ID, press -1 to quit ");
	    if (part_ids[count]==-1)
	    	break;
	    count++;
	    }
	    System.out.println("printing the list of parts");
	    
	    
		
		System.out.println("1.Add Car");
		System.out.println("2.Go Backn");
		int n = reader.nextInt();
		if(n==1)
		{
			System.out.println("Adding the car");
			 //create the part list models etc complex method dddd__________________
			System.out.println("car added successfully");
			//
			
		}
		if(n==2)
		{
			return;
		}
		
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
		
		
		
		System.out.println("Please enter Employee ID");
		int employee_id =reader.nextInt();
		
		//get the details of payroll for the employee 
		
		//format the output
		System.out.println("Pay roll details of employee ");
		System.out.println("Paycheck date  Pay_period  Employee_id  Employee_name, compensation");
		
		
		System.out.println("1.GoBack");
		int n = reader.nextInt();
		
		if (n==1)
			return;
	
		//System.out.println(x);
	}
	
	public static void addNewEmployee()
	{
			//randomly generate the employee ID and set default password as 12345678
		    int employee_id =helper.generateRandom();	
		
		    System.out.println("Employee ID is generated as "+employee_id);
		 	System.out.println("His default password is 12345678");
		    System.out.println("Please enter the remaining credential");
		 
			//read input from manager for creating employee
			
			System.out.println("A.Name: ");
			String name = reader.nextLine();
		
			System.out.println("B.Address");
			String address=reader.nextLine();
			
			System.out.println("C.Email Address");
			String email_address=reader.nextLine();
		
			System.out.println("D.Phone Number");
			String phone_number=reader.nextLine();
			
			System.out.println("E.Role");
			String role=reader.nextLine();
			
			System.out.println("F.Start Date");
			String start_date=reader.nextLine();
			
			System.out.println("F.Compensation (in $)");
			Float compensation= reader.nextFloat();
			
		
			System.out.println("1.Add");
			System.out.println("2.Go back");
		
			int n = reader.nextInt();
			if (n==1) { 
		    // check if role matches
			if(helper.isValidRole(role)) {
		    
		    // check if the service center already has the employee 
			
			//create the employee ID
			
				
			//Debug- need to add exception for wrong credential
			Users.create(email_address,name,address,phone_number);	
			Employees.create(employee_id, email_address, role);
			
			if(role=="receptionist")
			{
				//create payroll 
			}
			else if(role=="mechanic")
			{
				//create payroll
			}
				
				
			System.out.println( "employee is created with ID"+employee_id);
			}
			else 
			{
				
				System.out.println("Invalid Role");
			}
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
		while(true) {
		System.out.println("1.Edit My profile");
		System.out.println("2.Go Back");
		
		int n = reader.nextInt();
	
		if(n==2)
			return;
		if(n==1) {
			
			editProfile();		
		 }
		}
	}
	
	public static void editProfile() 
	{
		System.out.println("Choose (1-4) to edit");
		System.out.println("1.emailaddress");
		System.out.println("2.phone number");
		System.out.println("3.Compensation");
		
		//cannot edit employee ID, 
		//String.format("%05d", yournumber);
		System.out.println("1.write the change");
		System.out.println("2.Go Back");
		
		int n = reader.nextInt();
		
		if(n==1)
		{
			System.out.println("Sucessfully edited");
		}
		if(n==2)return;
		
		//action go back to default page
	}
	

	
	
	
	public static void main(String [] args) {
	  defaultPage();
		
		
	}


	
	
}
