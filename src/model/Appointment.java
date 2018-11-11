package model;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import db.DBBuilder;

public class Appointment {

	/*
	{
		Appointment_ID  VARCHAR(20)
		status VARCHAR(20)
		preferred_mechanic VARCHAR(20)
		PRIMARY_KEY(Appointment_ID)
		}


	 * 
	 * */

	
	public static void create(int appointment_id, String status, String preferred_mechanic) {
		
		Statement stmt = null;
		
		status = "'"+status+"'";
		preferred_mechanic = "'"+preferred_mechanic+"'";
		
		String sql =  "INSERT INTO Appointment VALUES("
				+ appointment_id+","
				+ status +","
				+ preferred_mechanic 
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
	
	public static void delete(int appointment_id) {
		
		Statement stmt = null;
		String sql =  "DELETE FROM Appointment WHERE appointment_id = " + appointment_id;
		
		try {	
			stmt = DBBuilder.getConnection().createStatement();
	        System.out.println(sql);
			stmt.executeUpdate(sql);
		} 
    	catch(Throwable e) {
	        e.printStackTrace();
	    }
		
	}

	public static void update(int appointment_id, String status, String preferred_mechanic) {
		
		Statement stmt = null;
		status = "'"+status+"'";
		preferred_mechanic = "'"+preferred_mechanic+"'";
		
		String sql =  "Update Appointment Set status = " +status+
				", preferred_mechanic = " +preferred_mechanic+
				" WHERE appointment_id = " + appointment_id;;
		
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
