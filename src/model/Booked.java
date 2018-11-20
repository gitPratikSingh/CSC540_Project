package model;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import db.DBBuilder;

public class Booked {

	 public static void create(String customer_id, String service_centre_id, int appointments_id, String lplate){
		 Statement stmt = null;
		 String sql =  "INSERT INTO Booked VALUES("
				+ "'"+customer_id+"'"+","
				+ "'"+service_centre_id+"'"+","
				+ appointments_id + ","
				+ "'"+lplate+"'"
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
	
	public static void delete(int customer_id, int service_centre_id, int appointments_id) {
		Statement stmt = null;
		 String sql =  "DELETE FROM Booked WHERE customer_id ="
				+ customer_id+" AND service_centre_id ="
				+ service_centre_id+" AND appointments_id ="
				+ appointments_id ;
		
		
		try {	
			stmt = DBBuilder.getConnection().createStatement();
	        //System.out.println(sql);
			stmt.executeUpdate(sql);
		} 
    	catch(Throwable e) {
	        e.printStackTrace();
	    }
	}
}