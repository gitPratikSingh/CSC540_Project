package model;

import java.sql.Statement;
import java.sql.Timestamp;

import db.DBBuilder;

public class Timeslots {

	/*Timeslots
	{
	service_center_id INTEGER,
	start_time TIMESTAMP,
	end_time TIMESTAMP,
	status VARCHAR(50),
	PRIMARY_KEY(service_center_id, start_time),
	FOREIGN KEY(service_centre_id)REFERENCES Service_Center
	}
	 * 
	 * */
	
public static void create(int service_center_id, Timestamp start_time, Timestamp end_time, String status) {
		
		Statement stmt = null;
	
		status = "'"+status+"'";
		
		String sql =  "INSERT INTO Timeslots VALUES("
				+ service_center_id +","
				+ start_time +","
				+ end_time +","
				+ status
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
	
	public static void delete(int service_center_id, Timestamp start_time) {
		
		Statement stmt = null;
		
		String sql =  "DELETE FROM Timeslots WHERE order_id = "+ service_center_id
				+ " AND start_time = "+ start_time;
		
		try {	
			stmt = DBBuilder.getConnection().createStatement();
	        System.out.println(sql);
			stmt.executeUpdate(sql);
		}
    	catch(Throwable e) {
	        e.printStackTrace();
	    }

	}
	
	public static void update(int service_center_id, Timestamp start_time, String status) {
		
		Statement stmt = null;
		status = "'"+status+"'";
		
		String sql =  "Update Timeslots Set service_center_id = " +service_center_id+
				", start_time = " +start_time+
				", status = " +status+
				" WHERE order_id = "+ service_center_id + 
				" AND start_time = "+ start_time;
		
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
