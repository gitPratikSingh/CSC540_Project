package model;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import db.DBBuilder;

public class Car {

	/*Car
		(
		License_plate_number VARCHAR(50)
		Make CHAR(20)
		Year TIMESTAMP
		Model CHAR(20)
		Last_service_date CHAR(20)
		Last_service_type CHAR(20)
		Purchase_date TIMESTAMP
		Last_recorded_miles INTEGER
		PRIMARY KEY (License_plate_number)
		)
	 * 
	 * */

	
	public static void create(String License_plate_number, String Make, String Year, String Model, String Last_service_date, String Last_service_type, Timestamp Purchase_date, int Last_recorded_miles) {
		
		Statement stmt = null;
		
		Make = "'"+Make+"'";
		Year = "'"+Year+"'";
		Model = "'"+Model+"'";
		Last_service_date = "'"+Last_service_date+"'";
		Last_service_type = "'"+Last_service_type+"'";
		
		String sql =  "INSERT INTO Car VALUES("
				+ Make+","
				+ Year +","
				+ Model +","
				+ Last_service_date +","
				+ Last_service_type +","
				+ Purchase_date +","
				+ Last_recorded_miles 
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
	
	public static void delete(String License_plate_number) {
		
		Statement stmt = null;
		String sql =  "DELETE FROM Car WHERE License_plate_number = " + License_plate_number;
		
		try {	
			stmt = DBBuilder.getConnection().createStatement();
	        System.out.println(sql);
			stmt.executeUpdate(sql);
		} 
    	catch(Throwable e) {
	        e.printStackTrace();
	    }
		
	}

	public static void update(String License_plate_number, String Make, String Year, String Model, String Last_service_date, String Last_service_type, Timestamp Purchase_date, int Last_recorded_miles) {
		
		Statement stmt = null;
		Make = "'"+Make+"'";
		Year = "'"+Year+"'";
		Model = "'"+Model+"'";
		Last_service_date = "'"+Last_service_date+"'";
		Last_service_type = "'"+Last_service_type+"'";
		
		String sql =  "Update Car Set Make = " +Make+
				", Year = " +Year+
				", Model = " +Model+
				", Last_service_date = " +Last_service_date+
				", Last_service_type = " +Last_service_type+
				", Purchase_date = " +Purchase_date+
				", Last_recorded_miles = " +Last_recorded_miles+
				" WHERE License_plate_number = " + License_plate_number;;
		
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
