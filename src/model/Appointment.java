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

	
	public static int create(String status, Timestamp START_TIME, String SERVICE_TYPE, String preferred_mechanic) {
		
		Statement stmt = null;
		int app_id =0;
				
		String sql =  "Select max(APPOINTMENT_ID) from APPOINTMENT";
		
		try {	
			stmt = DBBuilder.getConnection().createStatement();
	        //System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				app_id = rs.getInt(1);
			}
		} 
    	catch(Throwable e) {
	        e.printStackTrace();
	    }
		
		/*APPOINTMENT_ID
		STATUS
		START_TIME
		SERVICE_TYPE
		PREFERRED_MECHANIC
		*/
		
		sql =  "Select EMPLOYEE.employee_id from EMPLOYEE JOIN USERS ON EMPLOYEE.email = USERS.email WHERE USERS.NAME = '"+preferred_mechanic+"'";
		int preferred_mechanic_id =0;
		try {	
			stmt = DBBuilder.getConnection().createStatement();
	        //System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				preferred_mechanic_id = rs.getInt(1);
			}
		} 
    	catch(Throwable e) {
	        e.printStackTrace();
	    }
		
		PreparedStatement cursor;
		try {
			cursor = DBBuilder.getConnection()
					.prepareStatement("INSERT INTO APPOINTMENT VALUES (?, ?, ?, ?, ?)");	
			
			 cursor.setInt(1, app_id+1);
			 cursor.setString(2, status);
			 cursor.setTimestamp(3, START_TIME);
			 cursor.setString(4, SERVICE_TYPE);
			 cursor.setInt(5, preferred_mechanic_id);
			 
			 cursor.executeUpdate();
 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return app_id+1;
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
