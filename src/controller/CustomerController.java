package controller;

import java.sql.Connection;

import model.Appointment;
import model.Booked;
import model.Car;
import model.Customers;
import model.HasCars;
import model.HasParts;
import model.Parts;
import model.ServiceCenter;
import model.Timeslots;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DBBuilder;

public class CustomerController {

	private Scanner scanner = new Scanner(System.in);
	
	private final static String TITLE = "----------\nCustomer: Landing\n";
	private final static String MENU = "-----\nMenu\n-----\n1. Profile\n2. Register Car\n3. Service\n4. Invoices\n5. Logout\n";
	private final static String PROFILE = "-----\nCustomer: Profile\n-----\n1. View Profile\n2. Update Profile\n3. Go-Back\n";
	private final static String UPDATE_PROFILE = "-----\nCustomer: Update Profile\n-----\n1. Name\n2. Address\n3. Phone Number\n4. Password\n5. Go Back\n";
	private final static String REGISTER_CAR_MENU = "-----\nCustomer: Register Car\n-----\n1. Register\n2. Cancel\n";
	private final static String SERVICES_MENU = "-----\nCustomer: Service\n-----\n1. View Service History\n2. Schedule Service\n3. Reschedule Service\n4. Go Back\n";
	private final static String SCHEDULE_MENU = "-----\nCustomer: Schedule Service\n-----\n1. Schedule Maintenance\n2. Schedule Repair\n3. Go Back\n";
	private final static String SCHEDULE_MAINTENANCE = "-----\nCustomer: Schedule Maintenance\n-----\n1. Find Service Date\n2. Go Back\n";
	private final static String SCHEDULE_MAINTENANCE_DATES = "-----\nCustomer: Schedule Maintenance\n-----\n1. Schedule on Date\n2. Go Back\n";
	private final static String SCHEDULE_REPAIR_MENU = "-----\nCustomer: Schedule Repair\n-----\n1. Engine knock\n2. Car drifts in a particular direction\n3. Battery does not hold charge\n4. Black/unclean exhaust\n5. A/C-Heater not working\n6. Headlamps/Tail lamps not working\n7. Check engine light\n8. Go Back\n";
	
	
	
	MainController textGUI;
	String customerId="";
	String username="";
	
	private void setCustomerId(String username){
		username = "'"+username+"'";
		String query = "SELECT * FROM CUSTOMER WHERE email = "+username;
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
	    
		this.customerId = customerid;
		
	}
	
	public void customerMenu(MainController textGUI, String username) {
		this.username = username;
		setCustomerId(username);
		this.textGUI = textGUI;
		System.out.print(TITLE);
		System.out.print(MENU);
		
		switch (checkNumericalInput(1, 5)) {
		case -1:
			customerMenu(textGUI, username);
			break;
		case 1:
			profileMenu(username);
			break;
		case 2:
			registerCarMenu(username);
			break;
		case 3:
			serviceMenu();
			break;
		case 4:
			invoicesMenu();
			break;
		case 5:
			logoutMenu();
		default:
			break;
		}
		
		
	}
	
private void logoutMenu() {
		// TODO Auto-generated method stub
		
}

private void invoicesMenu() {
		// TODO Auto-generated method stub
		
}

private void serviceMenu() {
	System.out.print(SERVICES_MENU);
	
	switch (checkNumericalInput(1, 4)) {
	case -1:
		serviceMenu();
		break;
	case 1:
		viewServiceHistory();
		break;
	case 2:
		scheduleService();
		break;
	case 3:
		reScheduleService();
		break;
	case 4:
		customerMenu(this.textGUI, this.username);
		break;
	default:
		break;
	}
		
}

private void reScheduleService() {
	// Display the following details for all upcoming services for this customer, followed by the menu
	System.out.print("\n All upcoming services");
	
	String query = "SELECT APPOINTMENT.appointment_id, BOOKED.license_plate_number, APPOINTMENT.service_type, USERS.name as MechanicName, "
			+ "		TIMESLOTS.start_time, TIMESLOTS.end_time, APPOINTMENT.status FROM BOOKED"
			+ "		JOIN APPOINTMENT ON BOOKED.appointment_id = APPOINTMENT.appointment_id "
			+ "		LEFT JOIN EMPLOYEE ON EMPLOYEE.employee_id = APPOINTMENT.preferred_mechanic "
			+ "		LEFT JOIN USERS ON EMPLOYEE.email = USERS.email "
			+ "		JOIN TIMESLOTS ON TIMESLOTS.service_center_id = BOOKED.service_center_id AND TIMESLOTS.start_time = APPOINTMENT.start_time	"
			+ "		WHERE APPOINTMENT.status='pending' AND customer_id = " + this.customerId
			+ " Order by APPOINTMENT.appointment_id desc"; 
	
	
	
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
				+ "		Service End Time");
	    
	    while (rs.next()) {
	       System.out.println(rs.getString("APPOINTMENT_ID")+"		"
	    		   +rs.getString("LICENSE_PLATE_NUMBER")+"		"
	    		   +rs.getString("SERVICE_TYPE")+"		"
	    		   +rs.getString("MECHANICNAME")+"		"
	    		   +rs.getString("START_TIME")+"		"
	    		   +rs.getString("END_TIME")+"		");
	    }
	    
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	System.out.print("\n1. Pick a service 2. Go Back");
	
	switch (checkNumericalInput(1, 2)) {
	case -1:
		reScheduleService();
		break;
	case 1:
		System.out.print("\nEnter the service Id\n");
		try{
			int serviceId = scanner.nextInt();
			reScheduleService(serviceId);
			
		}catch(Exception e){
			System.out.print("\nPlease enter a correct service Id\n");
			reScheduleService();
		}
		break;
	case 2:
		serviceMenu();
		break;
	default:
		break;
	}
	
}

private void reScheduleService(int serviceId) {
	// find two earliest available maintenance/repair dates that are at least one day after the current service date
	Timestamp[] availableTimeslots = null;
	// if serviceType = 'A'/'B'/'C'
	String sql =  "Select APPOINTMENT.START_TIME from APPOINTMENT WHERE APPOINTMENT.appointment_id = "+ serviceId;
	Timestamp ts = null;	
	try {	
		Statement stmt = DBBuilder.getConnection().createStatement();
        System.out.println(sql);
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()){
			ts = rs.getTimestamp(1);
		}
	} 
	catch(Throwable e) {
        e.printStackTrace();
    }
	
	sql =  "Select BOOKED.License_Plate_Number, BOOKED.SERVICE_CENTER_ID from BOOKED WHERE BOOKED.appointment_id = "+ serviceId;
	String licensePlateNumber = "", service_center_id = "";
	try {	
		Statement stmt = DBBuilder.getConnection().createStatement();
        System.out.println(sql);
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()){
			licensePlateNumber = rs.getString(1);
			service_center_id = rs.getString(2);
		}
	} 
	catch(Throwable e) {
        e.printStackTrace();
    }
	
	Timestamp te = Timeslots.getEndTime(service_center_id, ts);
	String status = Timeslots.getStatus(service_center_id, ts);
	
	Timestamp nts1 = new Timestamp(0);
	Timestamp nte1 = new Timestamp(0);
	
	nts1.setTime(ts.getTime() + (((24 * 60 * 60))* 1000));
	nte1.setTime(te.getTime() + (((24 * 60 * 60))* 1000));
	
	Timestamp nts2 = new Timestamp(0);
	Timestamp nte2 = new Timestamp(0);
	
	nts2.setTime(nts1.getTime() + (((1 * 60))* 1000));
	nte2.setTime(nte1.getTime() + (((1 * 60))* 1000));
	
	System.out.println("Available on the folloing date and time");
	DateFormat format = new SimpleDateFormat( "yyyy-dd-MM h:mm a" );
	String str1 = format.format( nts1 );
	String str2 = format.format( nts2 );
	
	System.out.println(str1);
	System.out.println(str2);
	
	System.out.println("Select 1 for the first date, 2 for the last date\n");
	
	switch (checkNumericalInput(1, 2)) {
	case 1:
		Timeslots.create(service_center_id, nts1, nte1, status);
		
		PreparedStatement cursor;
		try {
			cursor = DBBuilder.getConnection()
					.prepareStatement("UPDATE APPOINTMENT SET START_TIME = ? WHERE appointment_id = ?");	
			
			 cursor.setInt(2, serviceId);
			 cursor.setTimestamp(1, nts1);
			 cursor.executeUpdate();
			 
			 ///System.out.println("Service reschduled!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case 2:
		Timeslots.create(service_center_id, nts2, nte2, status);
		
		PreparedStatement cursor1;
		try {
			cursor1 = DBBuilder.getConnection()
					.prepareStatement("UPDATE APPOINTMENT SET START_TIME = ? WHERE appointment_id = ?");	
			
			 cursor1.setInt(2, serviceId);
			 cursor1.setTimestamp(1, nts2);
			 cursor1.executeUpdate();

			 //System.out.println("Service reschduled!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case -1:
		reScheduleService(serviceId);
		break;
	default:
		break;
	}
	
	serviceMenu();
}

private void scheduleService() {
	
	String lplate = "";
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
	
	System.out.print(SCHEDULE_MENU);
	
	switch (checkNumericalInput(1, 3)) {
	case -1:
		scheduleService();
		break;
	case 1:
		scheduleMaintenance(lplate, mileage, mname);
		break;
	case 2:
		scheduleRepair(lplate, mileage, mname);
		break;
	case 3:
		serviceMenu();
		break;
	default:
		break;
	}
	
}

private void scheduleRepair(String lplate, int mileage, String mname) {
	
	System.out.print(SCHEDULE_REPAIR_MENU);
	switch (checkNumericalInput(1, 8)) {
	case -1:
		scheduleRepair(lplate, mileage, mname);
		break;
	case 1:
		scheduleRepairType("Engine knock", lplate, mileage, mname);
		break;
	case 2:
		scheduleRepairType("Car drifts in a particular direction", lplate, mileage, mname);
		break;
	case 3:
		scheduleRepairType("Battery does not hold charge", lplate, mileage, mname);
		break;
	case 4:
		scheduleRepairType("Black/unclean exhaust", lplate, mileage, mname);
		break;
	case 5:
		scheduleRepairType("A/C-Heater not working", lplate, mileage, mname);
		break;
	case 6:
		scheduleRepairType("Headlamps/Tail lamps not working", lplate, mileage, mname);
		break;
	case 7:
		scheduleRepairType("Check engine", lplate, mileage, mname);
		break;
	case 8:
		serviceMenu();
		break;
	default:
		break;
	}
	
}

private void scheduleRepairType(String repairName, String lplate, int mileage, String mname) {
	/*
	 * create a diagnostic report showing the list of causes and parts needed to 
	 * resolve them based on the problem selected. This report is to be displayed next.
	 * 
	 * Display the diagnostic report and the two identified service dates 
	 * and mechanic name found based on the inputs in the previous page 
	 * to the user, followed by the menu.
	 * 
	 * 
 	1. Engine knock:
		a. Diagnostic: ​​Timing issue
		b. Diagnostic fee:​​ $75
		c. Service: ​​Drive belt replacement, Spark plugs replacement, Camshaft replacement, Valve replacement
	2. Car drifts in a particular direction
		a. Diagnostic: ​​Wheel alignment issue
		b. Diagnostic fee:​​ $50
		c. Service: ​​Wheel alignment
	3. Battery does not hold charge
		a. Diagnostic: ​​Battery needs replacement
		b. Diagnostic fee:​​ $25
		c. Service: ​​Battery replacement
	4. Black/unclean exhaust
		a. Diagnostic: ​​Bad catalytic convertor and filters
		b. Diagnostic fee:​​ $75
		c. Service: ​​Air filter change,​ ​​Oil filter change, Catalytic convertor replacement
	5. A/C-Heater not working
		a. Diagnostic: ​​Drive belt damaged, coolant not enough, weak battery
		b. Diagnostic fee:​​ $50
		c. Service: ​​Drive belt replacement, coolant recycle, Battery replacement
	6. Headlamps/Tail lamps not working
		a. Diagnostic: Light assembly damaged
		b. Diagnostic fee:​​ $30
		c. Service: ​​Headlights replacement, Tail lights replacement, Turn lights
		replacement
	7. Check
		a. Diagnostic: Gearbox and Torque convertor issue
		b. Diagnostic fee:​​ $100
		c. Service: ​​Piston replacement, Gear box repair, Camshaft replacement, Valve replacement

	 * */
	
	String daignosticReport = "";
	Timestamp[] Timestamps = null;
	ArrayList<String> list = new ArrayList<>();

	
	if(repairName.equalsIgnoreCase("Engine knock")){
		daignosticReport = "\n Diagnostic Report\n Caused By: ​​Timing issue\n Parts needed to be changed are: ​​Drive belt replacement, Spark plugs replacement, Camshaft replacement, Valve replacement";
		// todo // generate an invoice of Diagnostic fee:​​ $75
		list.add("Drive belt replacement");
		list.add("Spark plugs replacement");
		list.add("Camshaft replacement");
		list.add("Valve replacement");
		Timestamps = findNextAvailableTwoRepairDates(lplate, mileage, mname, list);
	}
	
	if(repairName.equalsIgnoreCase("Car drifts in a particular direction")){
		daignosticReport = "\n Diagnostic Report\n Caused By: ​​Wheel alignment issue\n Parts needed to be changed are: ​​Wheel alignment";
		// todo // generate an invoice of Diagnostic fee:​​ $50
		list.add("Wheel alignment");
		Timestamps = findNextAvailableTwoRepairDates(lplate, mileage, mname, list);
	}
	
	if(repairName.equalsIgnoreCase("Battery does not hold charge")){
		daignosticReport = "\n Diagnostic Report\n Caused By: ​​​​Battery needs replacement\n Parts needed to be changed are: ​​Battery replacement";
		// todo // generate an invoice of Diagnostic fee:​​ $25
		list.add("​​Battery replacement");
		Timestamps = findNextAvailableTwoRepairDates(lplate, mileage, mname, list);
	}
	
	if(repairName.equalsIgnoreCase("Black/unclean exhaust")){
		daignosticReport = "\n Diagnostic Report\n Caused By: ​​​​Bad catalytic convertor and filters\n Parts needed to be changed are: ​​Air filter change,​ ​​Oil filter change, Catalytic convertor replacement";
		// todo // generate an invoice of Diagnostic fee:​​ $75
		list.add("​​​​Air filter change");
		list.add("​​​​Oil filter change");
		list.add("Catalytic convertor replacement");
		Timestamps = findNextAvailableTwoRepairDates(lplate, mileage, mname, list);
	}
	
	if(repairName.equalsIgnoreCase("A/C-Heater not working")){
		daignosticReport = "\n Diagnostic Report\n Caused By: Drive belt damaged, coolant not enough, weak battery\n Parts needed to be changed are: ​​Air filter change,​ ​​Oil filter change, Catalytic convertor replacement";
		// todo // generate an invoice of Diagnostic fee:​​ $75
		list.add("​​​​Air filter change");
		list.add("​​​​Oil filter change");
		list.add("Catalytic convertor replacement");
		Timestamps = findNextAvailableTwoRepairDates(lplate, mileage, mname, list);
	}
	
	if(repairName.equalsIgnoreCase("Headlamps/Tail lamps not working")){
		daignosticReport = "\n Diagnostic Report\n Caused By: Light assembly damaged\n Parts needed to be changed are: ​​Headlights replacement, Tail lights replacement, Turn lights";
		// todo // generate an invoice of Diagnostic fee:​​ $75
		list.add("​​​​​​Headlights replacement");
		list.add("​​​​Tail lights replacement");
		list.add("Turn lights");
		Timestamps = findNextAvailableTwoRepairDates(lplate, mileage, mname, list);
	}
	
	if(repairName.equalsIgnoreCase("Check engine")){
		daignosticReport = "\n Diagnostic Report\n Caused By: Gearbox and Torque convertor issue\n Parts needed to be changed are: ​​Piston replacement, Gear box repair, Camshaft replacement, Valve replacement";
		// todo // generate an invoice of Diagnostic fee:​​ $75
		list.add("​​​​​​Piston replacement");
		list.add("​​​​Gear box repair");
		list.add("Camshaft replacement");
		list.add("Valve replacement");
		Timestamps = findNextAvailableTwoRepairDates(lplate, mileage, mname, list);
	}
	
	System.out.print(daignosticReport);
	System.out.print("\nTwo identified service dates are \n");
	
	DateFormat format = new SimpleDateFormat( "yyyy-dd-MM h:mm a" );
	String str1 = format.format( Timestamps[0] );
	String str2 = format.format( Timestamps[2] );
	
	System.out.println("\nSelect 1 for the first date, 2 for the second date\n");
	System.out.print("1. "+str1);
	System.out.print("\n2. "+str2);
	
	switch (checkNumericalInput(1, 2)) {
	case 1:
		scheduleTheRepairDate(lplate, Timestamps[0], Timestamps[1], mname);
		break;
	case 2:
		scheduleTheRepairDate(lplate, Timestamps[2], Timestamps[3],  mname);
		break;
	case -1:
		scheduleRepairType(lplate, repairName, mileage,  mname);
		break;
	default:
		break;
	}
	
}

private void scheduleTheRepairDate(String lplate, Timestamp start_time,Timestamp end_time, String mname) {
	String city = Customers.getCity(this.username);
	String service_center = ServiceCenter.findByCity(city);
	
	Timeslots.create(service_center, start_time, end_time, "repair");
	int app_id = Appointment.create("pending", start_time, "repair", mname);
	Booked.create(customerId, service_center, app_id, lplate);
	
	// remove the parts from the inventory
	
	int part_id = 0, current_quantity = 0;
	
	// once the servicetype is decided, find the parts, quantity, and time info
	String newOrderQuery = "SELECT HAS_PARTS.part_id, HAS_PARTS.min_quantity_threshold, HAS_PARTS.min_order_threshold" + 
			" FROM BASIC_SERVICES" +
			" JOIN PARTS ON BASIC_SERVICES.part_name = PARTS.part_name" +
			" JOIN HAS_PARTS ON HAS_PARTS.part_id = PARTS.part_id AND HAS_PARTS.service_center_id = '" + service_center +"'"+
			" WHERE BASIC_SERVICES.make= '"+ ServiceCenter.serviceMake +
			"' AND BASIC_SERVICES.model= '" + ServiceCenter.serviceModel+
			"' AND HAS_PARTS.current_quantity < BASIC_SERVICES.quantiy"
			+ " AND BASIC_SERVICES.SERVICE_NAME IN("+ServiceCenter.partsRequired+")";
		
		
		// see if the parts are available
		boolean partsNotFoundInInventory = false;
		try {
			Statement stmt = DBBuilder.getConnection().createStatement();
	        //System.out.println(newOrderQuery);
	        ResultSet rs = stmt.executeQuery(newOrderQuery);
	        while (rs.next()) {
	 	       int partId = rs.getInt("PART_ID");
	 	       int quantiy = rs.getInt("quantiy");
	 	       current_quantity = rs.getInt("current_quantity");
	 	       HasParts.update(service_center, partId, current_quantity - quantiy);
	 	       // order missing parts if no previous orders found!
	 	       // todo
	 	    }
		} 
		catch(Throwable e) {
	        e.printStackTrace();
	    }
	
	HasParts.update(service_center, part_id, current_quantity);
	
	DateFormat format = new SimpleDateFormat( "yyyy-mm-dd h:mm a" );
	String str = format.format( start_time );
	System.out.println("\nService booked on "+str);
	serviceMenu();
	
}

private Timestamp[] findNextAvailableTwoRepairDates(String lplate, int mileage, String mname, ArrayList<String> partList) {
	
	String city = Customers.getCity(this.username);
	String service_center = ServiceCenter.findByCity(city);
	
	Timestamp[] availableTimeslots = ServiceCenter.getNextTwoAvailableDatesForRepair(service_center, lplate, mileage, partList, mname);
	
	return availableTimeslots;
}

private void scheduleMaintenance(String lplate, int mileage, String mname) {
	
	System.out.print(SCHEDULE_MAINTENANCE);
	
	switch (checkNumericalInput(1, 2)) {
	case -1:
		scheduleMaintenance(lplate, mileage, mname);
		break;
	case 1:
		findNextAvailableTwoServiceDates(lplate, mileage, mname);
		break;
	case 2:
		serviceMenu();
		break;
	default:
		break;
	}
	
}

private void findNextAvailableTwoServiceDates(String lplate, int mileage, String mname) {
	
	// find the city of the customer
	// find the service center that is present in that city
	
	String city = Customers.getCity(this.username);
	String service_center = ServiceCenter.findByCity(city);
	
	Timestamp[] availableTimeslots = ServiceCenter.getNextTwoAvailableDates(service_center, lplate, mileage);
	System.out.println(SCHEDULE_MAINTENANCE_DATES);
	
	
	switch (checkNumericalInput(1, 2)) {
	case -1:
		findNextAvailableTwoServiceDates(lplate, mileage, mname);
		break;
	case 1:
		PickTheDate(availableTimeslots, lplate, service_center, mname);
		break;
	case 2:
		scheduleService();
		break;
	default:
		break;
	}
	
}

private void PickTheDate(Timestamp[] availableTimeslots, String lplate, String service_center, String mname) {
	System.out.println("Available on the folloing date and time");
	DateFormat format = new SimpleDateFormat( "yyyy-dd-MM h:mm a" );
	String str1 = format.format( availableTimeslots[0] );
	String str2 = format.format( availableTimeslots[2] );
	
	System.out.println(str1);
	System.out.println(str2);
	
	System.out.println("Select 1 for the first date, 2 for the last date\n");
	
	switch (checkNumericalInput(1, 2)) {
	case 1:
		scheduleTheDate(lplate, service_center, availableTimeslots[0], availableTimeslots[1], availableTimeslots[4], availableTimeslots[5], availableTimeslots[6],  mname);
		break;
	case 2:
		scheduleTheDate(lplate, service_center, availableTimeslots[2], availableTimeslots[3], availableTimeslots[4], availableTimeslots[5], availableTimeslots[6],  mname);
		break;
	case -1:
		PickTheDate(availableTimeslots, lplate, service_center,  mname);
		break;
	default:
		break;
	}
}

private void scheduleTheDate(String lplate, String service_center, Timestamp start_time, Timestamp end_time, Timestamp serviceAmissed, Timestamp serviceBmissed, Timestamp serviceCmissed, String mname) {
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
	Booked.create(customerId, service_center, app_id, lplate);
	
	// remove the parts from the inventory
	// todo in task
	
	// once the servicetype is decided, find the parts, quantity, and time info
	String newOrderQuery = "SELECT HAS_PARTS.part_id, BASIC_SERVICES.quantiy, HAS_PARTS.current_quantity" + 
	" FROM BASIC_SERVICES" +
	" JOIN PARTS ON BASIC_SERVICES.part_name = PARTS.part_name" +
	" JOIN HAS_PARTS ON HAS_PARTS.part_id = PARTS.part_id AND HAS_PARTS.service_center_id = '" + service_center +"'"+
	" WHERE BASIC_SERVICES.make= '"+ ServiceCenter.serviceMake +
	"' AND BASIC_SERVICES.model= '" +  ServiceCenter.serviceModel+
	"' AND BASIC_SERVICES.service_type = '" + ServiceCenter.serviceNeeded;
	
	
	// see if the parts are available
	boolean partsNotFoundInInventory = false;
	try {
		Statement stmt = DBBuilder.getConnection().createStatement();
        //System.out.println(newOrderQuery);
        ResultSet rs = stmt.executeQuery(newOrderQuery);
        while (rs.next()) {
 	       int partId = rs.getInt("PART_ID");
 	       int quantiy = rs.getInt("quantiy");
 	       int current_quantity = rs.getInt("current_quantity");
 	       HasParts.update(service_center, partId, current_quantity-quantiy);
 	       
 	       // order missing parts if no previous orders found!
 	       // todo
 	    }
	} 
	catch(Throwable e) {
        e.printStackTrace();
    }
	
	DateFormat format = new SimpleDateFormat( "yyyy-mm-dd h:mm a" );
	String str = format.format( start_time );
	System.out.println("\nService booked on "+str);
	serviceMenu();
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
			+ "		JOIN TIMESLOTS ON TIMESLOTS.service_center_id = BOOKED.service_center_id AND TIMESLOTS.start_time = APPOINTMENT.start_time	"
			+ "		WHERE customer_id = " + this.customerId
			+ " Order by APPOINTMENT.appointment_id desc"; 
	
	
	
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
	    		   +rs.getString("MECHANICNAME")+"		"
	    		   +rs.getString("START_TIME")+"		"
	    		   +rs.getString("END_TIME")+"		"
	    		   +rs.getString("STATUS"));
	    }
	    
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	System.out.println("1. ​Go Back");
	
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
	
}

private void registerCarMenu(String username) {
	
	
		String lplate, make, model, year;
		String pdate = null, last_service_date = null;
		int mileage;
		
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
        
		
		System.out.print(REGISTER_CAR_MENU);
		
		switch (checkNumericalInput(1, 2)) {
		case -1:
			registerCarMenu(username);
			break;
		case 1:
			registerCar(username, lplate, pdate, make, model, year, mileage, last_service_date);
			break;
		case 2:
			customerMenu(this.textGUI, username);
			break; 
		default:
			break;
		}
		
		
	}

private void registerCar(String username, String lplate, String pdate, String make, String model, String year, int mileage, String last_service_date) {
	
	Car.create(lplate, make, year, model, last_service_date, null, pdate, mileage, null);
	HasCars.create(this.customerId, lplate);
	
}

private void profileMenu(String username) {
	System.out.print(PROFILE);
	
	switch (checkNumericalInput(1, 3)) {
	case -1:
		profileMenu(username);
		break;
	case 1:
		viewProfile(username);
		break;
	case 2:
		updateProfile(username);
		break;
	case 3:
		customerMenu(this.textGUI, username);
		break;
	default:
		break;
	}
		
}

private void updateProfile(String username) {
	/*
	 * 	1. Name
	 * 	2. Address
	 *	3. Phone Number 
	 *	4. Password
	 *	5. Go Back
	 * */
	
	System.out.print(UPDATE_PROFILE);
	
	switch (checkNumericalInput(1, 5)) {
	case -1:
		updateProfile(username);
		break;
	case 1:
		updateProfile(username, "Name");
		break;
	case 2:
		updateProfile(username, "Address");
		break;
	case 3:
		updateProfile(username, "Phone");
		break;
	case 4:
		updateProfile(username, "Password");
		break;
	case 5:
		profileMenu(username);
		break;
	default:
		break;
	}
	
}

private void updateProfile(String username, String field) {

	System.out.print("\n Please enter the new "+field);
	String input = scanner.nextLine();
	input = "'"+input+"'";
	String query = "UPDATE CUSTOMER set "+field+" =" + input+" WHERE email = "+"'"+username+"'";
	
	try {
		Statement stmt = DBBuilder.getConnection().createStatement();
		stmt.executeQuery(query);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	System.out.print("\n Profile updated successfully\n");
	updateProfile(username);
}


private void viewProfile(String username) {
	
	username = "'"+username+"'";
	String query = "SELECT * FROM CUSTOMER WHERE email = "+username;
	Statement stmt;
	String customerid = "";
	try {
		stmt = DBBuilder.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		System.out.println("CUSTOMER_ID,			EMAIL,			NAME,			ADDRESS,			PHONE");
	    
	    while (rs.next()) {
	       customerid = rs.getString("CUSTOMER_ID");
	       String email = rs.getString("EMAIL");
	       String name = rs.getString("NAME");
	       String adr = rs.getString("ADDRESS");
	       String phn = rs.getString("PHONE");
	       System.out.println(customerid+"		"+email+"		"+name+"		"+adr+"		"+phn);
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
	
	switch (checkNumericalInput(1, 3)) {
	case -1:
		viewProfile(username);
		break;
	case 1:
		profileMenu(username);
		break;
	default:
		break;
	}
}

private int checkNumericalInput(int startNum, int endNum) {
		
		String input = scanner.nextLine();
		try {
			int inputNum = Integer.parseInt(input);
			if (inputNum >= startNum && inputNum <= endNum) {

				return inputNum;
			}
		} catch (Exception e) {
			System.out.printf("Please input a value between %d and %d inclusive.\n", startNum, endNum);
			return -1;
		}
		System.out.printf("Please input a value between %d and %d inclusive.\n", startNum, endNum);
		return -1;
	}


 public static void main(String args[]){
	 CustomerController customerController = new CustomerController();
	 customerController.customerId = "1001";
	 customerController.username = "ethanhunt@gmail.com";
	 customerController.scheduleRepair("XYZ-5643", 90455, "Dena Holmes" );
	 
 }

}
