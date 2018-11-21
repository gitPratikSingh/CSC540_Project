package model;

import helpers.ManagerHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import db.DBBuilder;

public class ServiceCenter {

	/*
	Service_Center
		(
		service_center_id INTEGER
		telephone VARCHAR(10)
		name VARCHAR(20)
		address VARCHAR(30)
		PRIMARY KEY(service_center_id )
		)

	 * 
	 * */
	public static String serviceNeeded = "";
	public static String serviceMake="";
	public static String serviceModel="";
	public static String partsRequired="";
	
	public static void main(String ars[]){
		for(Timestamp t: ServiceCenter.getNextTwoAvailableDates("S001", "XYZ-5643", 11111))
			System.out.println(t);
	}

	
	public static Timestamp[] getNextTwoAvailableDates(String service_center_id, String licensePlateNumber, int mileage){
		serviceNeeded= "";
		String[] makeModel = new String[2];
		String LAST_SERVICE_TYPE="";
		int LAST_RECORDED_MILES=0;
		Timestamp[] timestamps = new Timestamp[7];
		// there is a predetermined maintenance schedule based on the number of miles traveled since the last service
		// service A = 0-10K miles
		// service B = 10k-35k miles
		// service C = 35k-85k
		// service schedules rotate
		// ** these numbers are car make,model specific
		// select make,model, service_type, miles from basic_services where make='Honda' and model='Civic' and 
		// service_type is not null
		// group by make, model, service_type, miles
		
		ResultSet rs = Car.getDetails(licensePlateNumber);
		
		try {
			if(rs.next()){
				
				makeModel[0] = rs.getString("MAKE");
				makeModel[1] = rs.getString("MODEL");
				LAST_SERVICE_TYPE = rs.getString("LAST_SERVICE_TYPE");
				LAST_RECORDED_MILES = rs.getInt("LAST_RECORDED_MILES");
				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		String servicesMilesThresholdsQuery = "select service_type, miles from basic_services where make = '"+ makeModel[0] 
				+"' and model= '" + makeModel[1]+ "' and service_type is not null group by service_type, miles";
		
		int total = 0;
		int serviceA = 0, serviceB = 0, serviceC = 0;
		
		Statement stmt = null;
		try {
			stmt = DBBuilder.getConnection().createStatement();
			
	        //System.out.println(servicesMilesThresholdsQuery);
	        rs = stmt.executeQuery(servicesMilesThresholdsQuery);
	        while (rs.next()) {
	 	       String service_type = rs.getString("SERVICE_TYPE");
	 	       int miles = rs.getInt("MILES");
	 	       
	 	       if(service_type.equals("A")){
	 	    	  serviceA = miles;
	 	       }else if(service_type.equals("B")){
	 	    	  serviceB = miles;
	 	       }else{
	 	    	  serviceC = miles;
	 	       }
	 	    }
		} 
    	catch(Throwable e) {
	        e.printStackTrace();
	        DBBuilder.close();
	    }
		
		total = serviceA + serviceB + serviceC;
		
		int miles_travelled_since_last_service = 0; 
		
		miles_travelled_since_last_service = mileage - LAST_RECORDED_MILES;
		
		int serviceAmissed =0;
		int serviceBmissed =0;
		int serviceCmissed =0;
		
		if((LAST_SERVICE_TYPE==null || LAST_SERVICE_TYPE.equals("")) == false){
			if(LAST_SERVICE_TYPE.equalsIgnoreCase("A")){
				if(serviceB >= miles_travelled_since_last_service){
					serviceNeeded = "B";
				}else{
					serviceBmissed = 1;
					if((serviceB + serviceC) < miles_travelled_since_last_service){
						serviceCmissed = 1;
					}
					serviceNeeded = "C";
				}
			}else if(LAST_SERVICE_TYPE.equalsIgnoreCase("B")){
				serviceNeeded = "C";
				if(serviceC < miles_travelled_since_last_service){
					serviceCmissed = 1;
				}
			}else if(LAST_SERVICE_TYPE.equalsIgnoreCase("C")){
			
				if(serviceA >= miles_travelled_since_last_service){
					serviceNeeded = "A";
				}else if(serviceA + serviceB >= miles_travelled_since_last_service){
					serviceNeeded = "B";
					serviceAmissed = 1;
				}else{
					serviceNeeded = "C";
					if(serviceA + serviceB + serviceC < miles_travelled_since_last_service){
						serviceCmissed = 1;
					}
				}
			}
		}
		else
		{
			
			if(serviceA >= miles_travelled_since_last_service){
				serviceNeeded = "A";
			}else if(serviceA + serviceB >= miles_travelled_since_last_service){
				serviceNeeded = "B";
				serviceAmissed = 1;
			}else {
				serviceNeeded = "C";
				if(serviceA + serviceB + serviceC < miles_travelled_since_last_service){
					serviceCmissed = 1;
				}
			}
			
		}
		
		// once the servicetype is decided, find the parts, quantity, and time info
		String newOrderQuery = "SELECT HAS_PARTS.part_id, HAS_PARTS.min_quantity_threshold, HAS_PARTS.min_order_threshold" + 
		" FROM BASIC_SERVICES" +
		" JOIN PARTS ON BASIC_SERVICES.part_name = PARTS.part_name AND BASIC_SERVICES.make = PARTS.make" +
		" JOIN HAS_PARTS ON HAS_PARTS.part_id = PARTS.part_id AND HAS_PARTS.service_center_id = '" + service_center_id +"'"+
		" WHERE BASIC_SERVICES.make= '"+ makeModel[0] +
		"' AND BASIC_SERVICES.model= '" + makeModel[1]+
		"' AND BASIC_SERVICES.service_type = '" + serviceNeeded +
		"' AND HAS_PARTS.current_quantity < BASIC_SERVICES.quantity";
				
		// see if the parts are available
		boolean partsNotFoundInInventory = false;
		Timestamp timestamp = new Timestamp(0);
		try {
			stmt = DBBuilder.getConnection().createStatement();
	        //System.out.println(newOrderQuery);
	        rs = stmt.executeQuery(newOrderQuery);
	        while (rs.next()) {
	 	       int PART_ID = rs.getInt("PART_ID");
	 	       int min_order_threshold = rs.getInt("min_order_threshold");
 	    	   partsNotFoundInInventory = true; 
	 	       // order missing parts if no previous orders found!
 	    	   if(Orders.orderExists(PART_ID, service_center_id) == false){
 	    		   Timestamp temp = newOrder(PART_ID, min_order_threshold, service_center_id);
 	    		   if(temp.after(timestamp)){
 	    			  timestamp = temp;
 	    		   }
 	    	   }
	 	    }
		} 
    	catch(Throwable e) {
	        e.printStackTrace();
	    }
		
		Timestamp[] timeslots = null;
		// if yes, then find the next available times
		if(partsNotFoundInInventory == false){
			// calculate the time required for service
			String serviceTimeQuery = 
					"SELECT SUM(TIME) as serviceTimeNeeded" + 
					" FROM BASIC_SERVICES" +
					" WHERE BASIC_SERVICES.make= '"+ makeModel[0] +
					"' AND BASIC_SERVICES.model= '" + makeModel[1]+
					"' AND BASIC_SERVICES.service_type = '" + serviceNeeded + "'";
			
			try {	
				stmt = DBBuilder.getConnection().createStatement();
		        //System.out.println(serviceTimeQuery);
		        rs = stmt.executeQuery(serviceTimeQuery);
		        
		        while (rs.next()) {
		 	       int serviceTime = rs.getInt("SERVICETIMENEEDED");
		 	       Timestamp[] tstamps = Timeslots.findTwoAvailableSlots(service_center_id, serviceTime);
		 	       timestamps[0] = tstamps[0];
		 	       timestamps[1] = tstamps[1];
		 	       timestamps[2] = tstamps[2];
		 	       timestamps[3] = tstamps[3];
		 	       timestamps[4] = null;
		 	       timestamps[5] = null;
		 	       timestamps[6] = null;
		 	       
		 	       if(serviceAmissed == 1){
		 	    	  timestamps[4] = new Timestamp(0);
		 	       }
		 	       if(serviceBmissed == 1){
		 	    	  timestamps[5] = new Timestamp(0);
		 	       }
		 	       if(serviceCmissed == 1){
		 	    	  timestamps[6] = new Timestamp(0);
		 	       }
		 	       
		 	       /* Todo
		 	        * Next, find the two earliest available date and time during which at least one mechanic is free 
		 	        * (or the requested mechanic is free if a preference is provided), and return those to the user. 
		 	        * Bear in mind that at no point can the total amount of time spent in servicing cars at a service 
		 	        * center be greater than 5.5 hours in a particular day. So you will have to find date/time accordingly.
		 	        * 
		 	        * */
		 	    }
			} 
	    	catch(Throwable e) {
		        e.printStackTrace();
		    }
		}else{
			//show a message to the user asking him to try again after a specific date 
			// (calculated based on when the order will be fulfilled).
			if(new Timestamp(0).equals(timestamp) == false){
				DateFormat format = new SimpleDateFormat( "yyyy-mm-dd h:mm a" );
				String str = format.format( timestamp );
				System.out.println("Please try after "+str);
			}
			
			
		}
		serviceMake = makeModel[0];
		serviceModel = makeModel[1];
		return timestamps;
	}
	
	public static String findByCity(String city)
	{
		Statement stmt = null;
		try {	
			stmt = DBBuilder.getConnection().createStatement();
			String sql = "SELECT service_centre_id, address from SERVICE_CENTER";
			
	        //System.out.println(sql);
	        ResultSet rs = stmt.executeQuery(sql);
	        while (rs.next()) {
	 	       String adr = rs.getString("ADDRESS");
	 	       String service_centre_id = rs.getString("SERVICE_CENTRE_ID");
	 	       if(adr.indexOf(city)!=-1){
	 	    	   return service_centre_id;
	 	       }
	 	    }
		} 
    	catch(Throwable e) {
	        e.printStackTrace();
	    }
		
		return "S0001";
	}
	
	public static void create(int service_center_id, String telephone, String name, String address) {
		
		Statement stmt = null;
		
		telephone = "'"+telephone+"'";
		name = "'"+name+"'";
		address = "'"+address+"'";
		
		String sql =  "INSERT INTO Service_Center VALUES("
				+ service_center_id+","
				+ telephone +","
				+ name +","
				+ address 
				+")"
				;
		
		try {	
			stmt = DBBuilder.getConnection().createStatement();
	        //System.out.println(sql);
			stmt.executeUpdate(sql);
		} 
    	catch(Throwable e) {
	        e.printStackTrace();
	    }
		
	}
	
	public static void delete(String service_center_id) {
		
		Statement stmt = null;
		String sql =  "DELETE FROM Service_Center WHERE service_center_id = " + "'"+service_center_id+"'";
		
		try {	
			stmt = DBBuilder.getConnection().createStatement();
	        //System.out.println(sql);
			stmt.executeUpdate(sql);
		} 
    	catch(Throwable e) {
	        e.printStackTrace();
	    }
		
	}

	public static void update(String service_center_id, String telephone, String name, String address) {
		
		Statement stmt = null;
		telephone = "'"+telephone+"'";
		name = "'"+name+"'";
		address = "'"+address+"'";
		service_center_id = "'"+service_center_id+"'";
		
		String sql =  "Update Service_Center Set telephone = " +telephone+
				", name = " +name+
				", address = " +address+
				" WHERE service_center_id = " + service_center_id;;
		
		try {	
			stmt = DBBuilder.getConnection().createStatement();
	        //System.out.println(sql);
			stmt.executeUpdate(sql);
		} 
		catch(Throwable e) {
	        e.printStackTrace();
	    }
	}

	public static Timestamp[] getNextTwoAvailableDatesForRepair(String service_center, String lplate, int mileage, ArrayList<String> partList, String mname) {
		ResultSet rs = Car.getDetails(lplate);
		String make="", model="";
		Timestamp[] timestamps = new Timestamp[7];
		try {
			if(rs.next()){
				
				make = rs.getString("MAKE");
				model = rs.getString("MODEL");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
				
		for(String part: partList){
			partsRequired = partsRequired+"'"+part+"',";
		}
		
		partsRequired = partsRequired.substring(0, partsRequired.length()-1);
		
		String newOrderQuery = "SELECT HAS_PARTS.part_id, HAS_PARTS.min_quantity_threshold, HAS_PARTS.min_order_threshold" + 
				" FROM BASIC_SERVICES" +
				" JOIN PARTS ON BASIC_SERVICES.part_name = PARTS.part_name AND BASIC_SERVICES.make = PARTS.make" +
				" JOIN HAS_PARTS ON HAS_PARTS.part_id = PARTS.part_id AND HAS_PARTS.service_center_id = '" + service_center +"'"+
				" WHERE BASIC_SERVICES.make= '"+ make +
				"' AND BASIC_SERVICES.model= '" + model+
				"' AND HAS_PARTS.current_quantity < BASIC_SERVICES.quantity"
				+ " AND BASIC_SERVICES.SERVICE_NAME IN("+partsRequired+")";
		
		Statement stmt = null;
		boolean partsNotFoundInInventory = false;
		Timestamp timestamp = new Timestamp(0);
		try {
			stmt = DBBuilder.getConnection().createStatement();
	        //System.out.println(newOrderQuery);
	        rs = stmt.executeQuery(newOrderQuery);
	        while (rs.next()) {
	 	       int PART_ID = rs.getInt("PART_ID");
	 	       int min_order_threshold = rs.getInt("min_order_threshold");
 	    	   partsNotFoundInInventory = true; 
		 	       
		 	       // order missing parts if no previous orders found!
		 	      if(Orders.orderExists(PART_ID, service_center) == false){
	 	    		   Timestamp temp = newOrder(PART_ID, min_order_threshold, service_center);
	 	    		   if(temp.after(timestamp)){
	 	    			  timestamp = temp;
	 	    		   }
	 	    	   }
			}
		} 
		catch(Throwable e) {
	        e.printStackTrace();
	    }
		
		Timestamp[] timeslots = null;
		// if yes, then find the next available times
		if(partsNotFoundInInventory == false){
			// calculate the time required for service
			String serviceTimeQuery = 
					"SELECT SUM(TIME) as serviceTimeNeeded" + 
					" FROM BASIC_SERVICES" +
					" WHERE BASIC_SERVICES.make= '"+ make +
					"' AND BASIC_SERVICES.model= '" + model+
					"' AND BASIC_SERVICES.SERVICE_NAME IN("+partsRequired+")";
			
			try {	
				stmt = DBBuilder.getConnection().createStatement();
		       // System.out.println(serviceTimeQuery);
		        rs = stmt.executeQuery(serviceTimeQuery);
		        
		        while (rs.next()) {
		 	       int serviceTime = rs.getInt("SERVICETIMENEEDED");
		 	       Timestamp[] tstamps = Timeslots.findTwoAvailableRepairSlots(service_center, serviceTime);
		 	       timestamps[0] = tstamps[0];
		 	       timestamps[1] = tstamps[1];
		 	       timestamps[2] = tstamps[2];
		 	       timestamps[3] = tstamps[3];
		 	       timestamps[4] = null;
		 	       timestamps[5] = null;
		 	       timestamps[6] = null;
		 	       
		 	      
		 	       
		 	       /* Todo
		 	        * 
		 	        * Next, find the two earliest available date and time during which at least one mechanic is free 
		 	        * (or the requested mechanic is free if a preference is provided), and return those to the user. 
		 	        * Bear in mind that at no point can the total amount of time spent in servicing cars at a service 
		 	        * center be greater than 5.5 hours in a particular day. So you will have to find date/time accordingly.
		 	        * 
		 	        * */
		 	    }
			} 
	    	catch(Throwable e) {
		        e.printStackTrace();
		    }
			
		}else{
			//show a message to the user asking him to try again after a specific date 
			// (calculated based on when the order will be fulfilled).
			
			if(new Timestamp(0).equals(timestamp) == false){
				DateFormat format = new SimpleDateFormat( "yyyy-mm-dd h:mm a" );
				String str = format.format( timestamp );
				System.out.println("Please try after "+str);
			}
			
		}
		
		serviceMake = make;
		serviceModel = model;
			
		return timestamps;
	}


	public static Timestamp[] getRescheduledNextTwoAvailableDates(int serviceId, String serviceType) {
		
		serviceNeeded = "";
		String[] makeModel = new String[2];
		String LAST_SERVICE_TYPE="";
		int LAST_RECORDED_MILES=0;
		Timestamp[] timestamps = new Timestamp[7];
		// there is a predetermined maintenance schedule based on the number of miles traveled since the last service
		// service A = 0-10K miles
		// service B = 10k-35k miles
		// service C = 35k-85k
		// service schedules rotate
		// ** these numbers are car make,model specific
		// select make,model, service_type, miles from basic_services where make='Honda' and model='Civic' and 
		// service_type is not null
		// group by make, model, service_type, miles
		String sql =  "Select BOOKED.License_Plate_Number, BOOKED.SERVICE_CENTER_ID from BOOKED WHERE BOOKED.appointment_id = "+ serviceId;
		String licensePlateNumber = "", service_center_id = "";
		try {	
			Statement stmt = DBBuilder.getConnection().createStatement();
	        //System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				licensePlateNumber = rs.getString(1);
				service_center_id = rs.getString(2);
			}
		} 
    	catch(Throwable e) {
	        e.printStackTrace();
	    }
		
		ResultSet rs = Car.getDetails(licensePlateNumber);
		
		try {
			if(rs.next()){
				
				makeModel[0] = rs.getString("MAKE");
				makeModel[1] = rs.getString("MODEL");
				LAST_SERVICE_TYPE = rs.getString("LAST_SERVICE_TYPE");
				LAST_RECORDED_MILES = rs.getInt("LAST_RECORDED_MILES");
				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		serviceNeeded = serviceType;
		
		// once the servicetype is decided, find the parts, quantity, and time info
		String newOrderQuery = "SELECT HAS_PARTS.part_id, HAS_PARTS.min_quantity_threshold, HAS_PARTS.min_order_threshold" + 
		" FROM BASIC_SERVICES" +
		" JOIN PARTS ON BASIC_SERVICES.part_name = PARTS.part_name AND BASIC_SERVICES.make = PARTS.make" +
		" JOIN HAS_PARTS ON HAS_PARTS.part_id = PARTS.part_id AND HAS_PARTS.service_center_id = '" + service_center_id +"'"+
		" WHERE BASIC_SERVICES.make= '"+ makeModel[0] +
		"' AND BASIC_SERVICES.model= '" + makeModel[1]+
		"' AND BASIC_SERVICES.service_type = '" + serviceNeeded +
		"' AND HAS_PARTS.current_quantity < BASIC_SERVICES.quantity";
		
		
		// see if the parts are available
		boolean partsNotFoundInInventory = false;
		Statement stmt;
		try {
			stmt = DBBuilder.getConnection().createStatement();
	        //System.out.println(newOrderQuery);
	        rs = stmt.executeQuery(newOrderQuery);
	        while (rs.next()) {
	 	       String service_type = rs.getString("PART_ID");
	 	       if(service_type != null || service_type.equals(""))
	 	    	   partsNotFoundInInventory = true; 
	 	       
	 	       // order missing parts if no previous orders found!
	 	       // todo
	 	    }
		} 
    	catch(Throwable e) {
	        e.printStackTrace();
	    }
		
		Timestamp[] timeslots = null;
		
		serviceMake = makeModel[0];
		serviceModel = makeModel[1];
		return timestamps;
		
	}
	
	public static Timestamp newOrder(int part_id, int quantity, String from_service_center_id)
	{
			
			//other variable for the auto populate 
			String distributor_id=null;
			int delivery_window = -1;
		
			
			String query= "SELECT DISTRIBUTOR_SUPPLIES_PARTS.DISTRIBUTOR_ID,DISTRIBUTOR_SUPPLIES_PARTS.DELIVERY_WINDOW FROM DISTRIBUTOR_SUPPLIES_PARTS "
			+" WHERE PART_ID = "+part_id+" AND DELIVERY_WINDOW IS NOT NULL";
			
			
			ResultSet rs;
			Statement stmt;
			try {
				stmt = DBBuilder.getConnection().createStatement();
			
				rs= stmt.executeQuery(query);
				if(rs.next()) {
					distributor_id =rs.getString("DISTRIBUTOR_ID");
					delivery_window =rs.getInt("DELIVERY_WINDOW");
				}
			 }
			 catch (SQLException e) {
					e.printStackTrace();
			 }
			
			
			String to_service_center_id = ManagerHelper.serviceCenter(part_id, from_service_center_id);
			if(!(to_service_center_id.equals(" ")))
			{
				distributor_id = to_service_center_id;
				delivery_window = 1;
			}
			
			 			
			
			if (delivery_window ==-1)
			{return new Timestamp(0);}
			/*Details such as cost, order date, etc. should be automatically calculated and
 			the order status must be set as “pending”.After placing the
			order, show a confirmation message with the order ID*/
			
			//function to create the order 
			String status = "pending:"+ distributor_id;
			Timestamp order_date = ManagerHelper.getCurrentTimestamp();
			
			Timestamp arrival_date = ManagerHelper.addDate(order_date, delivery_window);
			status = status+":"+arrival_date.toString();
	
			Orders.create(from_service_center_id, order_date, part_id, quantity, status);
			
			return arrival_date;
	}


	public static Timestamp[] getRepairRescheduledNextTwoAvailableDates(int serviceId, String sERVICE_TYPE) {
		// TODO Auto-generated method stub
		return null;
	}
}
