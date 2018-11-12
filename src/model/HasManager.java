package model;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import db.DBBuilder;

public class HasManager {

	 public static void create(int service_centre_id, int employee_id){
		 Statement stmt = null;
		 String sql =  "INSERT INTO Has_Manager VALUES("
				+ service_centre_id+","
				+ employee_id +")"
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
	
	public static void delete(int service_centre_id, int employee_id) {
		Statement stmt = null;
		 String sql =  "DELETE FROM Has_Manager WHERE service_centre_id ="
				+ service_centre_id+" AND employee_id ="
				+ employee_id ;
		
		
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