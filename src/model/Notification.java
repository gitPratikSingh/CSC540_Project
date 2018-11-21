package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import db.DBBuilder;

public class Notification {

	/*
	{
		NOTIFICATION_ID
		SERVICE_CENTER_ID
		MESSAGE
		NOTIF_DATE


	 * 
	 * */

	
	public static int create(String SERVICE_CENTER_ID, String MESSAGE) {
		
		Statement stmt = null;
		int not_id =0;
				
		String sql =  "Select max(NOTIFICATION_ID) from NOTIFICATIONS";
		
		try {	
			stmt = DBBuilder.getConnection().createStatement();
	        //System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				not_id = rs.getInt(1);
			}
		} 
    	catch(Throwable e) {
	        e.printStackTrace();
	    }
		
		
		PreparedStatement cursor;
		try {
			cursor = DBBuilder.getConnection()
					.prepareStatement("INSERT INTO NOTIFICATIONS VALUES (?, ?, ?, ?)");	
			
			 cursor.setInt(1, not_id+1);
			 cursor.setString(2, SERVICE_CENTER_ID);
			 cursor.setDate(4, (java.sql.Date) new Date());
			 cursor.setString(3, MESSAGE);
			 cursor.executeUpdate();
 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return not_id+1;
	}
	
	public static void delete(int appointment_id) {
		
		Statement stmt = null;
		String sql =  "DELETE FROM Appointment WHERE appointment_id = " + appointment_id;
		
		try {	
			stmt = DBBuilder.getConnection().createStatement();
	        //System.out.println(sql);
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
	       // System.out.println(sql);
			stmt.executeUpdate(sql);
		} 
		catch(Throwable e) {
	        e.printStackTrace();
	    }
	}
}
