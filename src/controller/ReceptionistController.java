

package controller;

import java.sql.Connection;

import model.Car;
import model.HasCars;
import model.HasParts;
import model.Parts;
import model.ServiceCenter;
import model.Users;
import model.Employees;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import db.DBBuilder;

public class ReceptionistController {
	
	private String username;
	private String employee_id;
	private Scanner scanner = new Scanner(System.in);
	
	ReceptionistController(String email_id){
		this.username = email_id;
		this.employee_id = this.getEmployee_id();
	}
	

	public static void main(String[] args) {
		System.out.println("Started!");
		ReceptionistController receptionist = new ReceptionistController("chris@gmail.com");
		receptionist.landing_page();
	}
	
	public String getEmployee_id() {
		System.out.println("getting employee_id");
		String query = "SELECT EMPLOYEE_ID FROM EMPLOYEE WHERE email = '"+this.username+"'";
		Statement stmt;
		String employee_id = "";
		try {
			stmt = DBBuilder.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
			       employee_id = rs.getString("EMPLOYEE_ID");
			    }
			    
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return employee_id;
	}
	
	public void landing_page() {
		while (true) {
			System.out.println("1. Profile");
			System.out.println("2. View Customer Profile");
			System.out.println("3. Register Car");
			System.out.println("4. Service History");
			System.out.println("5. Schedule Service");
			System.out.println("6. Reschedule Service");
			System.out.println("7. Invoices");
			System.out.println("8. Daily Task-Update Inventory");
			System.out.println("9. Daily Task-Record Deliveries");
			System.out.println("10.Logout ");
			Scanner conn = new Scanner(System.in);
			String choice = conn.nextLine();
			switch(choice) {
			case "1": System.out.println("1");
					this.get_profile();
					break;
			case "2": System.out.println("2");
					this.get_customer_profile();
					break;
			case "3": System.out.println("3");
					this.schedule_service();
					break;
			case "4": System.out.println("4");
					this.viewServiceHistory();
					break;
			case "5": System.out.println("5");
					break;
			case "6": System.out.println("6");
					break;
			case "7": System.out.println("7");
					break;
			}
			
		}
	}
	
	public void get_profile() {
		System.out.println("1.View Profile");
		System.out.println("2.Update Profile");
		System.out.println("3.Go Back");
		Scanner reply = new Scanner(System.in);
		String response = reply.nextLine();
		while (true) {
		switch (response) {
		case "1": this.view_profile();
				  break;
		case "2": this.update_profile();
				  break;
		case "3": this.landing_page();
		
		}
	}
	}
	public void view_profile() {
		/*
		A. Employee ID
		B. Name
		C. Address
		D. Email Address
		E. Phone Number
		F. Service Center
		G. Role
		H. Start Date
		I. Compensation ($)
		J. Compensation
		Frequency
		*/
		
		
		String query1 = "SELECT EMPLOYEE.EMPLOYEE_ID, USERS.EMAIL, USERS.NAME, USERS.ADDRESS, USERS.PHONE, EMPLOYEE.START_DATE, EMPLOYEE.MONTHLY_PAY, EMPLOYEE.HOURLY_PAY,has_receptionist.service_center_id,EMPLOYEE.role"
			+"			FROM EMPLOYEE INNER JOIN HAS_RECEPTIONIST" 
			+"			ON EMPLOYEE.EMPLOYEE_ID = HAS_RECEPTIONIST.EMPLOYEE_ID AND EMPLOYEE.EMPLOYEE_ID = '"+this.employee_id+"'"
			+"			INNER JOIN USERS ON USERS.EMAIL = EMPLOYEE.EMAIL  AND EMPLOYEE.EMPLOYEE_ID ='"+this.employee_id+"'";
		
		
		
			Statement stmt;
			String employee_id = "";
			try {
				stmt = DBBuilder.getConnection().createStatement();
				ResultSet rs = stmt.executeQuery(query1);
								
				while (rs.next()) {
				   employee_id = rs.getString("EMPLOYEE_ID");
				   String email = rs.getString("EMAIL");
				   String name = rs.getString("NAME");
				   String adr = rs.getString("ADDRESS");
				   String phn = rs.getString("PHONE");
				   String strdate = rs.getString("START_DATE");
				   String Monthly_Pay = rs.getString("Monthly_Pay");
				   String Hourly_pay = rs.getString("Hourly_pay");
				   String sid = rs.getString("SERVICE_CENTER_ID");
				   String role = rs.getString("ROLE");
				   String compensation="";
				   String freq="";
				   if (Monthly_Pay != null && !Monthly_Pay.isEmpty()) {
					   compensation = Monthly_Pay;
					   freq = "Monthly";
				   }
				   else if (Hourly_pay != null && !Hourly_pay.isEmpty()) {
					   compensation = Hourly_pay;
					   freq = "Hourly";
				   }
			
				   System.out.println("\n\n");
				   System.out.println("Employee_id : "+employee_id);
				   System.out.println("Name : "+name);
				   System.out.println("Address : "+adr);
				   System.out.println("Email Address: "+email);
				   System.out.println("Phone : "+phn);
				   System.out.println("Service Center : "+sid);
				   System.out.println("Role : "+role);
				   System.out.println("Start date : "+strdate);
				   System.out.println("Compensation : "+compensation);
				   System.out.println("Frequency : "+freq);
				   System.out.println("\n\n");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		System.out.println("1. Go back");
		Scanner reply = new Scanner(System.in);
		String response = reply.nextLine();
		if (response.equals("1")){
			this.get_profile();
		}
		
	}
	
	public void update_profile() {
		/*
		 * 	1. Name
		 * 	2. Address
		 * 	3. Email Address
		 *	4. Phone Number 
		 *	5. Password
		 *	6. Go Back
		 * */
		System.out.println("Please enter which field you want to update:");
		System.out.println("1.Name");
		System.out.println("2.Address");
		System.out.println("3.Email Address");
		System.out.println("4.Phone Number");
		System.out.println("5.Password");
		System.out.println("6.Go Back");
		
		String reply = this.scanner.nextLine();
		switch (reply) {
		case "1": updateProfile("Name");
				 break;
		case "2":updateProfile("Address");
				 break;
		case "3":updateProfile("Email Address");
				 break;
		case "4":updateProfile("Phone Number");
				break;
		case "5": updateProfile("Password");
				break;
		case "6": this.get_profile();
		}
	}
	
	
	private void updateProfile( String field) {
			
		
		if (!field.equals("Email Address")){
		System.out.println("\n Please enter the new "+field);
		String input = scanner.nextLine();
		input = "'"+input+"'";
		String query = "UPDATE USERS set "+field+" =" + input+" WHERE email = '"+this.username+"'";
		
		try {
			Statement stmt = DBBuilder.getConnection().createStatement();
			stmt.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("\n Profile updated successfully\n");
	}
		else {
			System.out.println("\n Please enter the new "+field);
			String new_email = scanner.nextLine();
			String query1 = "select * from users where email ='"+this.username+"'";
			Statement stmt;
			try {
				stmt = DBBuilder.getConnection().createStatement();
				ResultSet rs = stmt.executeQuery(query1);
								
				while (rs.next()) {
				   String name = rs.getString("name");
				   String address = rs.getString("address");
				   String phone = rs.getString("phone");
				   String password = rs.getString("password");
				   String query2 = "INSERT INTO users VALUES ('"+new_email+"','"+name+"','"+address+"','"+phone+"','"+password+"')";
				   String query3 = "Update Employee set email = '"+new_email+"' where employee_id = '"+11111+"'";
				   String query4 = "Delete from users where email = '"+this.username+"'";
				   stmt.executeUpdate(query2);
				   stmt.executeUpdate(query3);
				   stmt.executeUpdate(query4);
				   this.username = new_email;}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("\n Profile updated successfully\n");
			
			
			}
	
	}
	
	public void get_customer_profile(){
		String username;
		System.out.println("Enter the email of the customer:");
		username = this.scanner.nextLine();
		String query = "SELECT * FROM CUSTOMER WHERE email = '"+username+"'";
		Statement stmt;
		String customerid = "";
		try {
			stmt = DBBuilder.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
						
			while (rs.next()) {
			   customerid = rs.getString("CUSTOMER_ID");
			   String email = rs.getString("EMAIL");
			   String name = rs.getString("NAME");
			   String adr = rs.getString("ADDRESS");
			   String phn = rs.getString("PHONE");
			   System.out.println("Customer_ID :"+customerid);
			   System.out.println("Email Address :"+email);
			   System.out.println("Name :"+name);
			   System.out.println("Address :"+adr);
			   System.out.println("Phone :"+phn);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		customerid = "'"+customerid+"'";
		
		query = "SELECT CAR.* FROM HAS_CARS JOIN CAR on(car.license_plate_number = has_cars.license_plate_number) WHERE customer_id ="+customerid;
		try {
			stmt = DBBuilder.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("LICENSE_PLATE_NUMBER,			MAKE, 			YEAR,			MODEL,			LAST_SERVICE_DATE,			LAST_SERVICE_TYPE, 			PURCHASE_DATE, 			LAST_RECORDED_MILES, 			SERVICE_CENTER_ID");
			
			while (rs.next()) {
			   System.out.println(rs.getString("LICENSE_PLATE_NUMBER")+"		"+rs.getString("MAKE")+"		"+rs.getString("YEAR")+"		"+rs.getString("MODEL")+"		"+rs.getString("LAST_SERVICE_DATE")+"		"+rs.getString("LAST_SERVICE_TYPE")+"		"+rs.getString("PURCHASE_DATE")+"		"+rs.getString("LAST_RECORDED_MILES")+"		"+rs.getString("SERVICE_CENTER_ID")+"		");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		/*
		A. CustomerID
		B. Name
		C. Address
		D. EmailAddress
		E. PhoneNumber
		F. List of All Cars
		(and their details)
		*/
			
		System.out.print("\n1. Go Back\n");
		String response;
		response = this.scanner.nextLine();
		switch(response){
			case "1": this.landing_page();
		}
		}
		
	
	
	private void viewServiceHistory() {
	/*  
	 * Display
	 *  A. ServiceID
		B. LicensePlate
		C. ServiceType
		D. MechanicName
		E. ServiceStart
		Date/Time
		F. Service End
		Date/Time (expected or actual)
		G. Service Status (Pending,
		Ongoing, or Complete)
	 * */
	
	String query = "SELECT APPOINTMENT.appointment_id, BOOKED.license_plate_number, APPOINTMENT.service_type, USERS.name as MechanicName, "
			+ "		TIMESLOTS.start_time, TIMESLOTS.end_time, APPOINTMENT.status FROM BOOKED"
			+ "		JOIN APPOINTMENT ON BOOKED.appointment_id = APPOINTMENT.appointment_id "
			+ "		LEFT JOIN EMPLOYEE ON EMPLOYEE.employee_id = APPOINTMENT.preferred_mechanic "
			+ "		LEFT JOIN USERS ON EMPLOYEE.email = USERS.email "
			+ "		JOIN TIMESLOTS ON TIMESLOTS.service_center_id = BOOKED.service_center_id AND TIMESLOTS.timeslots_id = APPOINTMENT.timeslots_id	";
			//+ "		WHERE customer_id = " + this.customerId; 
	
	
	Statement stmt;
	String customerid = "";
	try {
		stmt = DBBuilder.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		System.out.println("ServiceID"
				+ "		LicensePlate"
				+ "		ServiceType"
				+ "		MechanicName"
				+ "		ServiceStart Time"
				+ "		Service End Time"
				+ "		Service Status");
	    
	    while (rs.next()) {
	       System.out.println(rs.getString("APPOINTMENT_ID")+"		"
	    		   +rs.getString("LICENSE_PLATE_NUMBER")+"		"
	    		   +rs.getString("SERVICE_TYPE")+"		"
	    		   +rs.getString("NAME")+"		"
	    		   +rs.getString("MECHANICNAME")+"		"
	    		   +rs.getString("START_TIME")+"		"
	    		   +rs.getString("END_TIME")+"		"
	    		   +rs.getString("STATUS"));
	    }
	    
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	System.out.println("1. â€‹Go Back");
	String response = this.scanner.nextLine();
	switch(response) {
	case "1": this.landing_page();
			  break;
	}
	/*
	switch (checkNumericalInput(1, 1)) {
	case -1:
		viewServiceHistory();
		break;
	case 1:
		serviceMenu();
		break;
	default:
		break;
	}
	*/
	
}
	
	
	
	
	private String getEmail(){
		String email;
		email = "";
		try {
			Statement stmt = null;
			stmt = DBBuilder.getConnection().createStatement();
			//String sql = "insert into Users values('test1@gmail.com','mark','raleigh','33441') ";
			//System.out.println(sql);
			//stmt.executeUpdate(sql);
			//System.out.println("Added into Users table..");
			ResultSet rs = stmt.executeQuery("select * from Users");
			rs.next();
			email = rs.getString(1);
		} 
    	catch(Throwable e) {
	        e.printStackTrace();
	    }
		return email;
		
	}
	
	public void register_intro() {
			System.out.println("1.Register");
			System.out.println("2.Cancel");
			Scanner reply = new Scanner(System.in);
			String response = reply.nextLine();
			switch (response) {
			case "1": this.register_car();
						break;
			case "2": return;
			}
			
		}

	public void register_car() {
		String email = "";
		String license_plate_number = "";
		String purchase_date = "";
		String make = "";
		String model = "";
		String year = "";
		String mileage = "";
		String service_date = "";
		Scanner abhi = new Scanner(System.in);
		while (email.equals("")){
		System.out.println("A. Customer email address :(Mandatory)");
	    email = abhi.nextLine();
		}
		while (license_plate_number.equals("")){
		System.out.println("B. License plate :(Mandatory)");
		license_plate_number = abhi.nextLine();
		}
		while (purchase_date.equals("")) {
		System.out.println("C. Purchase date :(Mandatory)");
		purchase_date = abhi.nextLine();
		}
		while (make.equals("")) {
		System.out.println("D. Make :(Mandatory)");
		make = abhi.nextLine();
		}
		while (model.equals("")) {
		System.out.println("E. Model :(Mandatory)");
		model = abhi.nextLine();
		}
		while (year.equals("")) {
		System.out.println("F. Year :(Mandatory)");
		year = abhi.nextLine();
		}
		while (mileage.equals("")) {
		System.out.println("G. Current mileage :(Mandatory)");
		mileage = abhi.nextLine();
		}
		System.out.println("H. Last service date :");
		service_date = abhi.nextLine();
		abhi.close();
		System.out.println(email);
		System.out.println(license_plate_number);
		System.out.println(purchase_date);
		System.out.println(make);
		System.out.println(model);
		System.out.println(year);
		System.out.println(mileage);
		System.out.println(service_date);
		
		/*
		 * customer_id = 'select max(customer_id)+1 from Customer '
		 * insert into USERS(email,name,)
		 *insert into CUSTOMER values (customer_id,)
		 *
		 *
		 */
	}
	
	public void show_service_history() {
		System.out.println("Enter customer's email address:");
		String email = "";
		Scanner conn = new Scanner(System.in);
		email = conn.nextLine();
	}
	
	public void schedule_service() {
		System.out.println("1.Schedule Maintenance");
		System.out.println("2.Schedule Repair");
		System.out.println("3.Go Back");
		Scanner conn = new Scanner(System.in);
		String choice = conn.nextLine();
		switch (choice){
		case "1": System.out.println("Maintenance scheduled!");
				break;
		case "2": System.out.println("Repair Scheduled");
				break;
		case "3":return;
		
		}
	}
}
