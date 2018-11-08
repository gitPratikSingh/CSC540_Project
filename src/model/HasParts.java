package model;

import java.sql.Connection;
import java.sql.Statement;

import db.DBBuilder;

public class HasParts {

	public static int delete(int service_center_id, int part_id) {
		
		Statement stmt = null;
		String sql =  "DELETE FROM Has_Parts WHERE service_center_id = " + service_center_id
				+"AND part_id = " + part_id
				;
		
		try {	
			stmt = DBBuilder.getConnection().createStatement();
	        System.out.println(sql);
			stmt.executeUpdate(sql);
		} 
    	catch(Throwable e) {
	        e.printStackTrace();
	    }
		
		return part_id;

	}

	public static void update(int service_center_id, int part_id, 
			int current_quantity,
	        int min_quantity_threshold,
	        int min_order_threshold ) {
		
		Statement stmt = null;
		
		
		String sql =  "Update Has_Parts Set current_quantity = " +current_quantity+
				", min_quantity_threshold = " +min_quantity_threshold+
				", min_order_threshold = " +min_order_threshold+
				"WHERE service_center_id = " +service_center_id
				+"AND part_id = " + part_id
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

	
	public static void create(int service_center_id, int part_id, int current_quantity, int min_quantity_threshold,
			int min_order_threshold){
		
		Statement stmt = null;
		try {	
			stmt = DBBuilder.getConnection().createStatement();
			String sql =  "INSERT INTO Has_Parts VALUES("
					+service_center_id+","
					+ part_id +","
					+ current_quantity +","
					+ min_quantity_threshold +","
					+ min_order_threshold +")"
					;
			
	        System.out.println(sql);
			stmt.executeUpdate(sql);
		} 
    	catch(Throwable e) {
	        e.printStackTrace();
	    }
		
	}
}
