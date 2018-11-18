package controller;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Scanner;

import db.DBBuilder;
import helpers.ManagerHelper;
import helpers.Constants;
import model.Employees;
import model.HasMechanic;
import model.HasReceptionist;
import model.HourlyPayrolls;
import model.MonthlyPayrolls;
import model.Orders;
import model.Users;
import model.hasPay;
import model.Payrolls;


public class ManagerController {
	
	//Using single scanner to read from entire class
	static Scanner reader = new Scanner(System.in);
	
	//default SID 
	public static String ManagerSid = "S0003";
	public static String ManagerEmployeeId =" ";
	public static String ManagerEmailId="";
	public static String CurrentDate ="01/01/2018";

	private static String query;
	
	public static void setManager(String service_center_id, String email, String employee_id)
	{
		ManagerSid = service_center_id;
		ManagerEmployeeId=employee_id;
		ManagerEmailId=email;	
	
	}
	
	
	public static void defaultPage()
	{
		while(true){
		System.out.println("Please enter number between 1 to 10 ");
		System.out.println("1.Profile ");
		System.out.println("2.View Customer Profile ");
		System.out.println("3.Add New Employee ");
		System.out.println("4.Payroll ");
		System.out.println("5.Inventory ");
		System.out.println("6.Orders ");
		System.out.println("7.Notification ");
		System.out.println("8.New Car Model ");
		System.out.println("9.Car Service Details ");
		System.out.println("10.Service History ");
		System.out.println("11.Invoices ");
		System.out.println("12.Logout ");
		
		
		//take input from the user 
		int n = reader.nextInt();
		switch (n)
		{
		case 1:
			viewProfile();
			break;	
		case 2:
			viewCustomerProfile();
		
			break;
		case 3:
			addNewEmployee();
		
			break;
		case 4:
			payRoll();
			break;
		case 5:
			inventory();
		
			break;
		case 6:
	
			orders();
			break;
		case 7:
			notification();

			break;
		case 8:
			newCarModel();
	
			break;
		case 9:
			carServiceDetails();
			break;
		case 10:
			serviceHistory();
			break;
		case 11:
			invoices();
	
			break;
		case 12:
			System.out.println("Signing off");
			//close rea 
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
		System.out.println("Enter the Car Make (nissan honda or )");
		String car_make = reader.nextLine();
		
		System.out.println("Enter the Model");
		String car_model = reader.nextLine();
		
		System.out.println("Enter the year ");
		int car_year = reader.nextInt();
		
		System.out.println("Enter the Service type (service_a,service_b or service_c");
	    String car_service_type = reader.nextLine();
	    
	    
	    System.out.println("enter the list of part IDs ");
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
	    for(int i=0; i<count;i++)
	    {
	    	System.out.println(part_ids[i]);
	    }
	    
		
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
		
			System.out.println("Placing an order, please enter the follwing details");
			
			System.out.println("Part ID ");
			int part_id = reader.nextInt();
			
			System.out.println("Quantity");
			int quantity = reader.nextInt();
			
			//other variable for the autopopulate 
			String distributor_id=null;
			int delivery_window = -1;
		
			
			String query= "SELECT DISTRIBUTOR_SUPPLIES_PARTS.DISTRIBUTOR_ID,DISTRIBUTOR_SUPPLIES_PARTS.DELIVERY_WINDOW FROM DISTRIBUTOR_SUPPLIES_PARTS "
			
			+" WHERE PART_ID = "+part_id+" AND DELIVERY_WINDOW IS NOT NULL";
			
			ResultSet rs;
			Statement stmt;
			try {
				stmt = DBBuilder.getConnection().createStatement();
			
				rs= stmt.executeQuery(query);
				rs.next();
				distributor_id =rs.getString("DISTRIBUTOR_ID");
			
				delivery_window =rs.getInt("DELIVERY_WINDOW");
				//get the result of the query 
				
			    }
			 catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			System.out.println("selecting distributor "+ distributor_id+" delivery window = "+delivery_window);
			
			
			
			/*Details such as cost, order date, etc. should be automatically calculated and
 			the order status must be set as “pending”.After placing the
			order, show a confirmation message with the order ID*/
			System.out.println("1.Place Order ");
			System.out.println("2.Go back");
			
			//function to create the order 
			int n = reader.nextInt();
			String service_center_id = ManagerSid;
	
			String status = "pending:"+ distributor_id;
			Timestamp order_date= ManagerHelper.getCurrentTimestamp();
		
	
			if (n==1)
			{
				
				Orders.create(service_center_id, order_date, part_id, quantity, status);
				
				System.out.println("order successfull");
				return;
			}
	
			if(n==2)
			{
				return;
			}

			//function to calculate estimated date   
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
		System.out.println("Enter the Order ID ");
		String order_id = reader.nextLine();
		
		String query ="SELECT ORDERS.ORDER_ID, ORDERS.ORDER_DATE,PARTS.PART_NAME,ORDERS.SERVICE_CENTER_ID,ORDERS.QUANTITY,PARTS.UNIT_PRICE,PARTS.UNIT_PRICE*ORDERS.QUANTITY AS TOTAL_COST,ORDERS.STATUS "
		+"FROM ORDERS "
		+" JOIN PARTS ON ORDERS.PART_ID = PARTS.PART_ID "
		+" WHERE ORDER_ID = "+order_id;
		
		
		
		Statement stmt;
		ResultSet rs ;
		
		try {
			stmt = DBBuilder.getConnection().createStatement();
		
		rs= stmt.executeQuery(query);
		//get the result of the query 
		
	
		
	    while (rs.next()) {
		       System.out.println(rs.getInt("ORDER_ID")+"		"
		    		   +rs.getString("ORDER_DATE")+"		"
		    		   +rs.getString("PART_NAME")+"		"
		    		   +rs.getString("SERVICE_CENTER_ID")+"		"
		    		   +rs.getInt("QUANTITY")+"		"
		    		   +rs.getFloat("UNIT_PRICE")+"		"
		    		   +rs.getFloat("TOTAL_COST")+"		"
		    		   +rs.getString("STATUS")+"		"
		    		   
		    		   );
		    }
		
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		System.out.println("Part ID  Part_Name Quantity Unit_price Minimum_quantity Threshold_Minimim_order");
	
		
		String query ="SELECT HAS_PARTS.PART_ID, PARTS.PART_NAME, HAS_PARTS.CURRENT_QUANTITY,"
				+ "	HAS_PARTS.MIN_QUANTITY_THRESHOLD, HAS_PARTS.MIN_ORDER_THRESHOLD FROM HAS_PARTS "
				+ "JOIN PARTS ON HAS_PARTS.PART_ID = PARTS.PART_ID "
				+ "WHERE SERVICE_CENTER_ID = '"+ManagerSid+"'";
		
		//System.out.println(query);
		Statement stmt;
		ResultSet rs ;
		
		try {
			stmt = DBBuilder.getConnection().createStatement();
		
		rs= stmt.executeQuery(query);
		//get the result of the query 
		
	
		
	    while (rs.next()) {
		       System.out.println(rs.getString("PART_ID")+"		"
		    		   +rs.getString("PART_NAME")+"		"
		    		   +rs.getString("CURRENT_QUANTITY")+"		"
		    		   +rs.getString("MIN_QUANTITY_THRESHOLD")+"		"
		    		   +rs.getString("MIN_ORDER_THRESHOLD")+"		");
		    }
	    
	    
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		    int employee_id =ManagerHelper.generateRandom();	
		    String password = "12345678";
		
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
			
			System.out.println("F.Start Date in format mm/dd/yyyy");
			String start_date=reader.nextLine();
			
			System.out.println("F.Compensation (in $)");
			Float compensation= reader.nextFloat();
			
		
			System.out.println("1.Add");
			System.out.println("2.Go back");
			
			
			String start_month="january";
			int payroll_id = 500;
			int n = reader.nextInt();
			if (n==1) { 
		    // check if role matches
			if(ManagerHelper.isValidRole(role)) {
				
			//Debug- need to add exception for wrong credential
			
			if(role=="receptionist")
			{
				
				Users.create(email_address, name, address, phone_number, password);
				Employees.create(employee_id, email_address, Constants.RECEPTIONIST);
				Payrolls.create(payroll_id, Constants.MONTHLY_PAYROLL);
				//need to iterate the months for aggrigate bill 
				
				MonthlyPayrolls.create(payroll_id, start_month, compensation);
				hasPay.create(payroll_id, employee_id);
				//add check if receptionist is already there for the service centre ???
				
				HasReceptionist.create(ManagerSid, employee_id);
				
			}
			else if(role=="mechanic")
			{
			
				Users.create(email_address, name, address, phone_number, password);
				Employees.create(employee_id, email_address, Constants.MECHANIC);
				Payrolls.create(payroll_id, Constants.HOURLY_PAYROLL);
				
				//add check if receptionist is already there for the service centre ???
				HourlyPayrolls.create(payroll_id, compensation,0 ); 
				hasPay.create(payroll_id, employee_id);
				
				HasMechanic.create(ManagerSid, employee_id);
				
				//run compensation till date with paycheck date.
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
		//String.format("%05d", your number);
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
