package model;

import java.sql.ResultSet;
import java.sql.Statement;

import db.DBBuilder;

public class HasServices {

	public static void create(int service_center_id, String make, String model, String service_type) {
		
		Statement stmt = null;
		

		make = "'"+make+"'";
		model = "'"+model+"'";
		service_type = "'"+service_type+"'";
		
		String sql =  "INSERT INTO Has_Services VALUES("
				+ service_center_id+","
				+ make +","
				+ model +","
				+ service_type +")"
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
	
	public static void delete(int service_centre_id, String make, String model, String service_type) {
		
		Statement stmt = null;
		make = "'"+make+"'";
		model = "'"+model+"'";
		service_type = "'"+service_type+"'";
		
		String sql =  "DELETE FROM Has_Services WHERE service_centre_id = " + service_centre_id
				+ " AND make = "+ make
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
