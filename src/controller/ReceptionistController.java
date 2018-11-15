

package controller;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.Statement;

import db.DBBuilder;

public class ReceptionistController {
	
	public String ReceptionistID;
	
	ReceptionistController(String EmployeeID){
		ReceptionistID = EmployeeID;
	}
	

	public static void main(String[] args) {
		System.out.println("Started!");
		ReceptionistController receptionist = new ReceptionistController("ABBC");
		receptionist.landing_page();
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
					this.show_service_history();
					break;
			case "3": System.out.println("3");
					this.schedule_service();
					break;
			case "4": System.out.println("4");
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
		switch (response) {
		case "1": this.view_profile();
				  return;
		case "2": this.update_profile();
				  break;
		case "3": return;
		
		}
	}
	
	public void view_profile() {
		String email;
		email = this.getEmail();
		System.out.println("viewed the profile"+email);
	}
	
	public void update_profile() {
		System.out.println("updated the profile");
		
	}
	
	private String getEmail(){
		String email;
		email = "";
		try {
			Statement stmt = null;
			stmt = DBBuilder.getConnection().createStatement();
			String sql = "insert into Users values('test1@gmail.com','mark','raleigh','33441') ";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			System.out.println("Added into Users table..");
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

