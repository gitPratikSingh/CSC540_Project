

package controller;

import java.sql.Connection;

import model.Car;
import model.HasCars;
import model.HasParts;
import model.Parts;

import model.ServiceCenter;
import model.Users;
import model.Employees;
import model.Customers;
import java.text.DateFormat;
import model.Booked;
import model.Appointment;
import model.Timeslots;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
		System.out.println("Receptionist Landing Page");
		ReceptionistController receptionist = new ReceptionistController("chris@gmail.com");
		receptionist.landing_page();
	}
	
	public String getEmployee_id() {
		//System.out.println("getting employee_id");
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
					this.registerCarMenu();
					break;
			case "4": System.out.println("4");
					this.viewServiceHistory();
					break;
			case "5": System.out.println("5");
					this.scheduleService();
					break;
			case "6": System.out.println("6");
					break;
			case "7": System.out.println("7");
					this.generateinvoice();
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
		
	private ArrayList<String> get_BSS_for_repair(String repair, String model, String make) {
		String query = "select Basic_services.service_name from Basic_services"
		+" join Repairs on Basic_services.BASIC_SERVICE_ID = repairs.BASIC_SERVICE_ID"
		+" where model='"+model+"' AND make='"+make+"' AND repair_name='"+repair+"'";
		System.out.println(query);
		ArrayList<String> bss = new ArrayList<String>();
		Statement stmt;
		try {
			stmt = DBBuilder.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
		    while (rs.next()) {
		    	bss.add(rs.getString("SERVICE_NAME"));
		    	}
		    }catch (SQLException e) {
				e.printStackTrace();
			}
		return bss;
	}
	
	
	
	private ArrayList<String> get_BSS(String Service_type, String make, String model) {
		String query = "select service_name from Basic_services"
		+" where model='"+model+"' AND make='"+make+"' AND service_type='"+Service_type+"'";
		//System.out.println("Inside get_BSS");
		//System.out.println(query);
		ArrayList<String> bss = new ArrayList<String>();
		Statement stmt;
		try {
			stmt = DBBuilder.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			//System.out.println("Inside try of get_BSS");
		    while (rs.next()) {
		    	bss.add(rs.getString("SERVICE_NAME"));
		    	}
		    }catch (SQLException e) {
				e.printStackTrace();
			}
		return bss;
	}
	
	
	private HashMap<String,String> get_parts_details(String part,String make,String model) {
		
		String query= "select unit_price,warranty from parts where part_name = '"+part+"' and make = '"+make+"'";
		//System.out.println("Inside get_parts_details");
		//System.out.println(query);
		Statement stmt;
		String unit_price = "";
		String warranty = "";
		HashMap<String,String> Dict = new HashMap<String,String>();
		try {
			stmt = DBBuilder.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
		    while (rs.next()) {
		    	unit_price = rs.getString("unit_price");
		    	warranty = rs.getString("warranty");
		    	
		    }
		}
		    catch (SQLException e) {
				e.printStackTrace();
			}
		Dict.put("unit_price", unit_price);
    	Dict.put("warranty", warranty);
    	return Dict;
    	
		    }
	
	
	
	
	private HashMap<String,String> get_Bss_details(String item,String make,String model) {
		String query = "select charge, time , part_name , quantity from Basic_services " + 
				"where service_name = '"+item+"' AND make = '"+make+"' AND model = '"+model+"'";
		//System.out.println("Inside get_BSS_details");
		//System.out.println(query);
		Statement stmt;
		String charge = "";
		String time = "";
		String part_name = "";
		String quantity = "";
		HashMap<String,String> Dict = new HashMap<String,String>();
		try {
			stmt = DBBuilder.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
		    while (rs.next()) {
		    	charge = rs.getString("charge");
		    	time = rs.getString("time");
		    	part_name = rs.getString("part_name");
		    	quantity = rs.getString("quantity");
		    	
		    }
		}
		    catch (SQLException e) {
				e.printStackTrace();
			}
		Dict.put("charge", charge);
    	Dict.put("time", time);
    	Dict.put("part_name", part_name);
    	Dict.put("quantity", quantity);
    	return Dict;
    	
		    }
			
		
	private Float getdiagfee(String repair_name) {
		String query = "select diagnostic_fee from repairs where repair_name = '"+repair_name+"'";
		Float fees = 0f;
		Statement stmt;
		try {
			stmt = DBBuilder.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
		    while (rs.next()) {
		    	fees = Float.parseFloat(rs.getString("diagonistic_fee"));
		    }
		}
		catch (SQLException e) {
			e.printStackTrace();}
		return fees;
		
	}
	
	private void generateinvoice() {
		System.out.println("Enter customer email address");
		String email = scanner.nextLine();
		String customer_id = getcustomerid(email);
		System.out.println("customer_id :"+customer_id);
		
		
		String query = "SELECT APPOINTMENT.appointment_id, BOOKED.license_plate_number, car.make, car.model,APPOINTMENT.service_type, USERS.name as MechanicName, "
				+ "		TIMESLOTS.start_time, TIMESLOTS.end_time, APPOINTMENT.status FROM BOOKED"
				+ "		JOIN APPOINTMENT ON BOOKED.appointment_id = APPOINTMENT.appointment_id "
				+ "		LEFT JOIN EMPLOYEE ON EMPLOYEE.employee_id = APPOINTMENT.preferred_mechanic "
				+ "		LEFT JOIN USERS ON EMPLOYEE.email = USERS.email "
				+ "     JOIN CAR ON BOOKED.license_plate_number = CAR.license_plate_number"
				+ "		JOIN TIMESLOTS ON TIMESLOTS.service_center_id = BOOKED.service_center_id AND TIMESLOTS.start_time = APPOINTMENT.start_time	"
				+ "		WHERE customer_id = " + customer_id + " and APPOINTMENT.status = 'completed'" ; 
		
		//System.out.println(query);
		
		Statement stmt;
		try {
			stmt = DBBuilder.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("Query sent");
		    while (rs.next()) {
		    	HashMap<String,String> Labour_charge = new HashMap<String,String>();
		    	HashMap<String,String> Part_charge = new HashMap<String,String>();
		    	HashMap<String,String> Warranty = new HashMap<String,String>();
		    	HashMap<String,String> BSS_DETAILS = new HashMap<String,String>();
		    	HashMap<String,String> PART_DETAILS = new HashMap<String,String>();
		    	HashMap<String,Float> TOTAL_COST = new HashMap<String,Float>();
		    	HashMap<String,Float> Labour_hours = new HashMap<String,Float>();
		    	ArrayList<String> BSS_LIST = new ArrayList<String>();
		    	HashMap<String,String> Part_name = new HashMap<String,String>();
		    	String make = rs.getString("make");
		    	String model = rs.getString("model");
		    	String service_id = rs.getString("APPOINTMENT_ID");
		    	String license_plate = rs.getString("LICENSE_PLATE_NUMBER");
		    	String service_type = rs.getString("SERVICE_TYPE");
		    	String mechanic_name = rs.getString("MECHANICNAME");
		    	String service_start_time = rs.getString("START_TIME");
		    	String service_end_time = rs.getString("END_TIME");
		    	String service_status = rs.getString("STATUS");
		    	//System.out.println("make: "+make);
		    	//System.out.println("model: "+model);
		    	if (service_type.equals("A") || service_type.equals("B") || service_type.equals("C")) {
		    	switch(service_type) {
		    	case "C": BSS_LIST.addAll(get_BSS("C",make,model));
		    	case "B": BSS_LIST.addAll(get_BSS("B",make,model));
		    	case "A": BSS_LIST.addAll(get_BSS("A",make,model));
		    			 break;
		    	}}
		    	else {
		    		BSS_LIST.addAll(get_BSS_for_repair(service_type,make,model));
		    	}
		    	System.out.println("BSS_LIST: "+BSS_LIST);
		    	System.out.println("A.Service ID : "+service_id);
		    	System.out.println("B.Service Start Date/Time : "+service_start_time);
		    	System.out.println("C.Service End Date/Time : "+service_end_time);
		    	System.out.println("D.Licence Plate : "+license_plate);
		    	System.out.println("E.Service Type: "+service_type);
		    	System.out.println("F.Mechanic Name: "+mechanic_name);
		    	//System.out.println("Getting other info");
		    	for (String t: BSS_LIST) {
		    		BSS_DETAILS = get_Bss_details(t,make,model);
		    		String part_name = BSS_DETAILS.get("part_name");
		            PART_DETAILS = get_parts_details(part_name,make,model);
		            Part_name.put(t, BSS_DETAILS.get("part_name"));
		            Float service_charge = Float.parseFloat(BSS_DETAILS.get("charge")) * (Float.parseFloat(BSS_DETAILS.get("time")))/60 ;
		    		Labour_charge.put(t,(""+service_charge));
		    		Labour_hours.put(t,(Float.parseFloat(BSS_DETAILS.get("time"))/60));
		    		//System.out.println("BSS_DETAILS- quantity : "+BSS_DETAILS.get("quantity"));
		    		//System.out.println("PART_DETAILS - UNITPRICE :"+PART_DETAILS.get("unit_price"));
		    		Float part_charges = Float.parseFloat(BSS_DETAILS.get("quantity")) * Float.parseFloat(PART_DETAILS.get("unit_price"));
		    		Part_charge.put(t,(""+part_charges));
		    		//System.out.println("Part-details hashmap:"+PART_DETAILS);
		    		String temp = PART_DETAILS.get("warranty");
		    		int warranty;
		    		warranty = (temp == null)? 0:Integer.parseInt(temp);
		    		Warranty.put(t,(""+warranty));
		    		TOTAL_COST.put(t, (service_charge + part_charges));	
		    	}
		    	Float labour_charge = 0f;
		    	Float labour_hours = 0f;
		    	Float total_cost = 0f;
		    	System.out.println("G.Parts used in service with cost of each part :");
		    	for (String t: BSS_LIST) {
		    		System.out.println("Part : "+Part_name.get(t));
		    		System.out.println("Cost : "+Part_charge.get(t));
		    		System.out.print("\n\n");
		    	}
		    	for (String t: BSS_LIST) {
		    		labour_hours = labour_hours + Labour_hours.get(t);  
		    	}
		    	
		    	System.out.println("H.Total labor hours: "+labour_hours);
		    	for (String t: BSS_LIST) {
		    		labour_charge = labour_charge + Float.parseFloat(Labour_charge.get(t));  
		    	}
		    	System.out.println("I.labor wages per hour: "+(labour_charge/labour_hours));
		    	for (String t: BSS_LIST) {
		    		total_cost = total_cost + TOTAL_COST.get(t);  
		    	}
		    	if (!(service_type.equals("A") || service_type.equals("B") || service_type.equals("C"))) {
		    		total_cost = total_cost + getdiagfee(service_type);
		    	}
		    	System.out.println("J.Total Service Cost: "+total_cost);
		    	System.out.println("\n\n");
		    	
		    	System.out.println("1.Go Back");
		    	String response = scanner.nextLine();
		    	switch(response) {
		    	case "1":this.landing_page();
		    	}
		    }
		    	
		} catch (SQLException e) {
			e.printStackTrace();}
		}
		
		
				
		
		//Populate BSS 
		/* select service_name from Basic_services
			where model ='Corolla' AND make ='Toyota' AND service_type='A'
			select service_name from Basic_services
			where model ='Corolla' AND make ='Toyota' AND service_type='B'
			select service_name from Basic_services
			where model ='Corolla' AND make ='Toyota' AND service_type='C'
			
			for item in BSS:
				select charge, time , part_name , quantity from Basic_service
				where service_name = item AND make = make AND model = model
				Labour_charge[item] = charge * float(time/60)
				select unit_price,warranty from parts where part_name = part_name and make = make and model = model
				Parts_charge[item] =  price * float(time/60)
				warranty[item] = warranty
		*/
	
	
	
	private void viewServiceHistory() {
		
		System.out.println("Enter customer email address");
		String email = scanner.nextLine();
		String customer_id = getcustomerid(email);
		
		
		String query = "SELECT APPOINTMENT.appointment_id, BOOKED.license_plate_number, APPOINTMENT.service_type, USERS.name as MechanicName, "
				+ "		TIMESLOTS.start_time, TIMESLOTS.end_time, APPOINTMENT.status FROM BOOKED"
				+ "		JOIN APPOINTMENT ON BOOKED.appointment_id = APPOINTMENT.appointment_id "
				+ "		LEFT JOIN EMPLOYEE ON EMPLOYEE.employee_id = APPOINTMENT.preferred_mechanic "
				+ "		LEFT JOIN USERS ON EMPLOYEE.email = USERS.email "
				+ "		JOIN TIMESLOTS ON TIMESLOTS.service_center_id = BOOKED.service_center_id AND TIMESLOTS.start_time = APPOINTMENT.start_time	"
				+ "		WHERE customer_id = " + customer_id; 
		
		//System.out.println(query);
		
		Statement stmt;
		try {
			stmt = DBBuilder.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
		    while (rs.next()) {
		    	System.out.println("");
		    	System.out.println("A.ServiceID: "+rs.getString("APPOINTMENT_ID"));
		    	System.out.println("B.LicensePlate: "+rs.getString("LICENSE_PLATE_NUMBER"));
		    	System.out.println("C.ServiceType: "+rs.getString("SERVICE_TYPE"));
		    	System.out.println("D.MechanicName: "+rs.getString("MECHANICNAME"));
		    	System.out.println("E.ServiceStart Time: "+rs.getString("START_TIME"));
		    	System.out.println("F.Service End Time: "+rs.getString("END_TIME"));
		    	System.out.println("G.Service Status: "+rs.getString("STATUS"));
		    	System.out.println("");
		    	
		    }
		    
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	System.out.println("1. ​Go Back");
	String response = scanner.nextLine();
	switch (response){
	case "1": this.landing_page();
			  break;
	}
	}
	
	private String getcustomerid(String email) {
		String query = "SELECT * FROM CUSTOMER WHERE email = '"+email+"'";
		Statement stmt;
		String customerid = "";
		try {
			stmt = DBBuilder.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
		    while (rs.next()) {
		       customerid = rs.getString("CUSTOMER_ID");
		    }
		    
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
		return customerid;
	}
	

	private void registerCarMenu() {
		
		
		String lplate, make, model, year;
		String pdate = null, last_service_date = null;
		int mileage;
		System.out.println("Please enter customer's email address:");
		String username = scanner.nextLine();
		
		
		while(true){
		System.out.print("\n Please enter the Licence plate number\n");
		lplate = scanner.nextLine();
		
		if (lplate == null || lplate.equals("") == false)
			break;
		}
		
		while(true){
		System.out.print("\n Please enter the Purchasedate in yyyy-mm-dd format \n");
		pdate = scanner.nextLine();
		if (pdate == null || pdate.equals("") == false)
			break;
		}
		
		System.out.print("\n Please enter the make\n");
		make = scanner.nextLine();
		
		System.out.print("\n Please enter the model\n");
		model = scanner.nextLine();
		
		System.out.print("\n Please enter the year\n");
		year = scanner.nextLine();
		
		System.out.print("\n Please enter the Current mileage\n");
		
		try{
			mileage = Integer.parseInt(scanner.nextLine());
		}catch(NumberFormatException n){
			mileage = 0;
		}		

		System.out.print("\n Please enter the Last Service Date in yyyy-mm-dd format\n");
		last_service_date = scanner.nextLine();
        

		System.out.println("\n1.Register");
		System.out.println("2.Cancel");
		String response = scanner.nextLine();
		switch(response) {
		case "1": registerCar(username, lplate, pdate, make, model, year, mileage, last_service_date);
				  break;
		case "2": this.landing_page();
				  break;
		
		}
		
	}

	private void registerCar(String username, String lplate, String pdate, String make, String model, String year, int mileage, String last_service_date) {
		
		Car.create(lplate, make, year, model, last_service_date, null, pdate, mileage, null);
		String customer_id = this.getcustomerid(username);
		HasCars.create(customer_id, lplate);
		System.out.println("\nCar has been registered\n");

		
	}
	
	private void scheduleService() {
		
		String lplate = "";
		String username ="";
		
		System.out.print("\n Please enter the Email address of the customer\n");
		username = scanner.nextLine();
		
		
		while(true){
		System.out.print("\n Please enter the Licence plate number\n");
		lplate = scanner.nextLine();
		if ((lplate == null || lplate.equals("")) == false)
			break;
		}
		
		int mileage = 0;
		while(true){
		System.out.print("\n Please enter the Current Mileage\n");
		String str_milage = scanner.nextLine();
		if ((str_milage == null || str_milage.equals("")) == false)
			{
				mileage = Integer.parseInt(str_milage);
				break;
			}
		}
		
		System.out.print("\n Please enter the Mechanic Name\n");
		String mname = scanner.nextLine();
		
		System.out.println("1. Schedule Maintenance");
		System.out.println("2. Schedule Repair");
		System.out.println("3. Go Back");
		
		String response = scanner.nextLine();
		switch(response) {
		case "1": scheduleMaintenance(username,lplate, mileage, mname);
				  break;
		//case "2": scheduleRepair(lplate, mileage, mname);
		//		  break;
		case "3": this.landing_page();
				  break;
		}
		}
		

	private void scheduleMaintenance(String username,String lplate, int mileage, String mname) {
		
		System.out.println("1.Find Service Date");
		System.out.println("2.Go Back");
		
		String response = scanner.nextLine();
		
		switch (response) {
		case "1":
			findNextAvailableTwoServiceDates(username,lplate, mileage, mname);
			break;
		case "2":
			scheduleService();
			break;
		default:
			break;
		}
		
	}
	
	private void findNextAvailableTwoServiceDates(String username,String lplate, int mileage, String mname) {
		
		
		String city = Customers.getCity(this.username);
		String service_center = ServiceCenter.findByCity(city);
		
		Timestamp[] availableTimeslots = ServiceCenter.getNextTwoAvailableDates(service_center, lplate, mileage);
		System.out.println("1.Schedule on Date");
		System.out.println("2.Go Back");
		String response = scanner.nextLine();
		switch(response) {
		case "1": PickTheDate(username,availableTimeslots, lplate, service_center, mname);
				  break;
		case "2": scheduleService();
				  break;}
		}
		
	private void PickTheDate(String username,Timestamp[] availableTimeslots, String lplate, String service_center, String mname) {
		System.out.println("Available on the folloing date and time");
		DateFormat format = new SimpleDateFormat( "yyyy-dd-MM h:mm a" );
		String str1 = format.format( availableTimeslots[0] );
		String str2 = format.format( availableTimeslots[2] );
		
		System.out.println(str1);
		System.out.println(str2);
		
		System.out.println("Select 1 for the first date, 2 for the last date\n");
		String response = scanner.nextLine();
		
		switch (response) {
		case "1":
			scheduleTheDate(username,lplate, service_center, availableTimeslots[0], availableTimeslots[1], availableTimeslots[4], availableTimeslots[5], availableTimeslots[6],  mname);
			break;
		case "2":
			scheduleTheDate(username,lplate, service_center, availableTimeslots[2], availableTimeslots[3], availableTimeslots[4], availableTimeslots[5], availableTimeslots[6],  mname);
			break;
		default:
			break;
		}
	}
	
	private void scheduleTheDate(String username,String lplate, String service_center, Timestamp start_time, Timestamp end_time, Timestamp serviceAmissed, Timestamp serviceBmissed, Timestamp serviceCmissed, String mname) {
		String missedServices ="";
		if(serviceAmissed != null){
			missedServices = missedServices + "A";
		}
		
		if(serviceBmissed != null){
			missedServices = missedServices + "B";
		}
		
		if(serviceCmissed != null){
			missedServices = missedServices + "C";
		}
		
		Timeslots.create(service_center, start_time, end_time, "maintenance:"+ missedServices);
		int app_id = Appointment.create("pending", start_time, ServiceCenter.serviceNeeded, mname);
		String customerId = this.getcustomerid(username);
		Booked.create(customerId, service_center, app_id, lplate);
		
		DateFormat format = new SimpleDateFormat( "yyyy-mm-dd h:mm a" );
		String str = format.format( start_time );
		System.out.println("\nService booked on "+str);
		this.landing_page();
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
