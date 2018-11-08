package model;

import java.sql.Statement;

import db.DBBuilder;

public class SupportedServices {

	public static void create(String make, String model, String service_type, int year, String month, int miles) {
		
		Statement stmt = null;
		

		make = "'"+make+"'";
		model = "'"+model+"'";
		service_type = "'"+service_type+"'";
		month = "'"+month+"'";
		
		String sql =  "INSERT INTO Supported_Services VALUES("
				+ make +","
				+ model +","
				+ service_type +"," 
				+ year +"," 
				+ month +"," 
				+ miles
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
	
	public static void delete(String make, String model, String service_type) {
		
		Statement stmt = null;
		make = "'"+make+"'";
		model = "'"+model+"'";
		service_type = "'"+service_type+"'";
		
		String sql =  "DELETE FROM Supported_Services WHERE make = "+ make
				+ " AND model = "+ model
				+ " AND service_type = "+ service_type;
		
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
