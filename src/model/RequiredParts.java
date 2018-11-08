package model;

import java.sql.Statement;

import db.DBBuilder;

public class RequiredParts {
	
	public static void create(int service_center_id, String make, String model, String service_type, int part_id) {
		
		Statement stmt = null;
		
		make = "'"+make+"'";
		model = "'"+model+"'";
		service_type = "'"+service_type+"'";
		
		String sql =  "INSERT INTO Required_Parts VALUES("
				+ service_center_id +","
				+ make +","
				+ model +","
				+ service_type +","  
				+ part_id
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
	
	public static void delete(int service_centre_id, String make, String model, String service_type, int part_id) {
		
		Statement stmt = null;
		make = "'"+make+"'";
		model = "'"+model+"'";
		service_type = "'"+service_type+"'";
		
		String sql =  "DELETE FROM Required_Parts WHERE make = "+ make
				+ " AND model = "+ model
				+ " AND service_centre_id = "+ service_centre_id
				+ " AND part_id = "+ part_id
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
