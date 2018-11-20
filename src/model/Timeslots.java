package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import db.DBBuilder;

public class Timeslots {

	public static Timestamp[] ret = new Timestamp[4];
	
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
	
public static void create(String service_center_id, Timestamp start_time, Timestamp end_time, String status) {
				
		PreparedStatement cursor;
		try {
			cursor = DBBuilder.getConnection()
					.prepareStatement("INSERT INTO Timeslots VALUES (?, ?, ?, ?)");
			
			 cursor.setString(1, service_center_id);
			 cursor.setTimestamp(2, start_time);
			 cursor.setTimestamp(3, end_time);
			 cursor.setString(4, status);
			 cursor.executeUpdate();
 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void delete(int service_center_id, Timestamp start_time) {
		
		Statement stmt = null;
		
		String sql =  "DELETE FROM Timeslots WHERE order_id = "+ service_center_id
				+ " AND start_time = "+ start_time;
		
		try {	
			stmt = DBBuilder.getConnection().createStatement();
	        //System.out.println(sql);
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
	        //System.out.println(sql);
			stmt.executeUpdate(sql);
		} 
		catch(Throwable e) {
	        e.printStackTrace();
	    }	
	}

	public static Timestamp[] findTwoAvailableSlots(String service_center_id, int serviceTimeMins) {
		
		String query = "SELECT MAX(tm1.end_time) as lastBookedSlot FROM timeslots tm1 WHERE service_center_id = "+"'"+service_center_id+"'";
		// 1 slot from lastBookedSlot+1 to lastBookedSlot + serviceTime
		// 2 slot from lastBookedSlot + serviceTime +1, to lastBookedSlot + serviceTime + serviceTime
		Timestamp lastBookedSlot = null;
		try {	
			Statement stmt = DBBuilder.getConnection().createStatement();
	        //System.out.println(query);
	        ResultSet rs = stmt.executeQuery(query);
	        
	        while (rs.next()) {
	 	      lastBookedSlot = rs.getTimestamp("lastBookedSlot");
	 	    }
		} 
    	catch(Throwable e) {
	        e.printStackTrace();
	    }

		if(lastBookedSlot == null){
			lastBookedSlot = new Timestamp(new Date().getTime());
		}
		
		
		// 5.5 hours logic = 330 mins
		DateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );
		String str = format.format( lastBookedSlot );
		
		String scheduledMaintenanceMinsQuery = "SELECT SUM(round((cast(tm1.end_time as date) - cast( tm1.start_time as date))* 24 * 60)) as maintenance_minutes "
				+ " FROM timeslots tm1 WHERE tm1.status like 'maintenance%' AND TO_CHAR(tm1.end_time, 'YYYY-MM-DD') ="  +"'"+ str +"'"
				+ " GROUP BY TO_CHAR(tm1.end_time, 'YYYY-MM-DD') "; 
		
		int scheduledMaintenanceMins =0;
		try {	
			Statement stmt = DBBuilder.getConnection().createStatement();
	        //System.out.println(scheduledMaintenanceMinsQuery);
	        ResultSet rs = stmt.executeQuery(scheduledMaintenanceMinsQuery);
	        
	        while (rs.next()) {
	        	scheduledMaintenanceMins = rs.getInt("maintenance_minutes");
	 	    }
		} 
    	catch(Throwable e) {
	        e.printStackTrace();
	    }
		
		//System.out.println("Booked mins: "+scheduledMaintenanceMins);
		if(scheduledMaintenanceMins + serviceTimeMins > 330){
			// cannot schedule more maintenance this day, try next day
			lastBookedSlot.setDate(lastBookedSlot.getDate()+1);
			lastBookedSlot.setHours(0);
			lastBookedSlot.setMinutes(0);
			lastBookedSlot.setSeconds(0);
			lastBookedSlot.setNanos(0);
		}
		
		// see if the mechanic is available
		
		
		Timestamp nextSlotStart = null, nextSlotStop = null;
		
		
		nextSlotStart = new Timestamp(lastBookedSlot.getTime() + 1000);
		nextSlotStop = new Timestamp(nextSlotStart.getTime() + 1000*60*serviceTimeMins);
		
		ret[0] = new Timestamp(nextSlotStart.getTime());
		ret[1] = new Timestamp(nextSlotStop.getTime());
		
		nextSlotStart = new Timestamp(nextSlotStop.getTime() + 1000);
		nextSlotStop = new Timestamp(nextSlotStart.getTime() + 1000*60*serviceTimeMins);
		
		ret[2] = new Timestamp(nextSlotStart.getTime());
		ret[3] = new Timestamp(nextSlotStop.getTime());
		
		return ret;
	}
	
	public static void main(String args[]){
		Timeslots.create("S001", new Timestamp(0), new Timestamp(1), "maintenancene");
	}

	public static Timestamp[] findTwoAvailableRepairSlots(String service_center_id, int serviceTimeMins) {
		String query = "SELECT MAX(tm1.end_time) as lastBookedSlot FROM timeslots tm1 WHERE service_center_id = "+"'"+service_center_id+"'";
		// 1 slot from lastBookedSlot+1 to lastBookedSlot + serviceTime
		// 2 slot from lastBookedSlot + serviceTime +1, to lastBookedSlot + serviceTime + serviceTime
		Timestamp lastBookedSlot = null;
		try {	
			Statement stmt = DBBuilder.getConnection().createStatement();
	        //System.out.println(query);
	        ResultSet rs = stmt.executeQuery(query);
	        
	        while (rs.next()) {
	 	      lastBookedSlot = rs.getTimestamp("lastBookedSlot");
	 	    }
		} 
    	catch(Throwable e) {
	        e.printStackTrace();
	    }

		if(lastBookedSlot == null){
			lastBookedSlot = new Timestamp(new Date().getTime());
		}
		
		// see if the mechanic is available
		// to do
		Timestamp nextSlotStart = null, nextSlotStop = null;
		
		nextSlotStart = new Timestamp(lastBookedSlot.getTime() + 1000);
		nextSlotStop = new Timestamp(nextSlotStart.getTime() + 1000*60*serviceTimeMins);
		
		ret[0] = new Timestamp(nextSlotStart.getTime());
		ret[1] = new Timestamp(nextSlotStop.getTime());
		
		nextSlotStart = new Timestamp(nextSlotStop.getTime() + 1000);
		nextSlotStop = new Timestamp(nextSlotStart.getTime() + 1000*60*serviceTimeMins);
		
		ret[2] = new Timestamp(nextSlotStart.getTime());
		ret[3] = new Timestamp(nextSlotStop.getTime());
		
		return ret;
	}
}
