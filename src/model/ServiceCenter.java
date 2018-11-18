package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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

	public static Date[] getNextTwoAvailableDates(String service_center_id, String licensePlateNumber){
		Date[] ret = new Date[2];
		String[] makeModel = new String[2];
		String LAST_SERVICE_TYPE="";
		int LAST_RECORDED_MILES=0;
		
		// there is a predetermined maintenance schedule based on the number of miles traveled since the last service
		// service A = 0-10K miles
		// service B = 10k-35k miles
		// service C = 35k-85k
		// service schedules rotate
		// ** these numbers are car make,model specific
		// select make,model, service_type, miles from basic_services where make='Honda' and model='Civic' and 
		// service_type is not null
		// group by make, model, service_type, miles
		
		ResultSet rs = Car.getMakeModel(licensePlateNumber);
		
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
		
		String servicesMilesThresholdsQuery = "select service_type, miles from basic_services where make = "+ makeModel[0] 
				+" and model=" + makeModel[1]+ " and service_type is not null group by service_type, miles";
		
		int total = 0;
		int serviceA = 0, serviceB = 0, serviceC = 0;
		
		Statement stmt = null;
		try {	
			stmt = DBBuilder.getConnection().createStatement();
			
	        System.out.println(servicesMilesThresholdsQuery);
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
	    }
		
		total = serviceA + serviceB + serviceC;
		String serviceNeeded = "";
		
		if (LAST_SERVICE_TYPE == null || LAST_SERVICE_TYPE.equals("")){
			// no last service info found
			int reminder = LAST_RECORDED_MILES%total;
			
			if(serviceA >= reminder){
				serviceNeeded = "A";
			}else if(serviceB >= reminder) {
				serviceNeeded = "B";
			}else {
				serviceNeeded = "C";
			}
		}
		
		// once the servicetype is decided, find the parts, quantity, and time info
		// see if the parts are available
		// if yes, then find the next available times
		// if no, order the missing parts
		// Todo
		
		return null;
	}
	
	public static String findByCity(String city)
	{
		Statement stmt = null;
		try {	
			stmt = DBBuilder.getConnection().createStatement();
			String sql = "SELECT service_centre_id, address from SERVICE_CENTER";
			
	        System.out.println(sql);
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
		
		return "S0000";
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
	        System.out.println(sql);
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
	        System.out.println(sql);
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
	        System.out.println(sql);
			stmt.executeUpdate(sql);
		} 
		catch(Throwable e) {
	        e.printStackTrace();
	    }
	}
}
