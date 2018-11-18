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

	
	public static void create(String License_plate_number, String Make, String Year, String Model, String Last_service_date, String Last_service_type, String Purchase_date, int Last_recorded_miles, String serviceCenterId) {
		
		Statement stmt = null;
		
		PreparedStatement cursor;
		try {
			cursor = DBBuilder.getConnection()
					.prepareStatement("INSERT INTO Car VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			
			java.util.Date ldate = null, pdate = null;
			java.sql.Date lsqlDate = null, psqlDate = null;
			
			if(Last_service_date!=null)
				try {
					ldate = new SimpleDateFormat("yyyy-mm-dd").parse(Last_service_date);
					lsqlDate = new java.sql.Date(ldate.getTime());	
				} catch (ParseException e) {
					e.printStackTrace();
				}
			
			if(Purchase_date!=null)
				try {
					pdate = new SimpleDateFormat("yyyy-mm-dd").parse(Purchase_date);
					psqlDate = new java.sql.Date(pdate.getTime());	
				} catch (ParseException e) {
					e.printStackTrace();
				}		
			
			 cursor.setString(1, License_plate_number);
			 cursor.setString(2, Make);
			 cursor.setInt(3, Integer.parseInt(Year));
			 cursor.setString(4, Model);
			 cursor.setDate(5, lsqlDate);
			 cursor.setString(6, Last_service_type);
			 cursor.setDate(7, psqlDate);
			 cursor.setInt(8, Last_recorded_miles);
			 cursor.setString(9, serviceCenterId);			 
			 cursor.executeUpdate();
 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	
	
public static ResultSet getMakeModel(String License_plate_number){
		
		Statement stmt = null;
		String sql =  "SELECT make, model FROM Car WHERE License_plate_number = " + License_plate_number;
		
		try {	
			stmt = DBBuilder.getConnection().createStatement();
	        System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			return rs;
			
		} 
    	catch(Throwable e) {
	        e.printStackTrace();
	    }
		
		return null;
		
	}
	
	
	
	public static void main(String args[]){
		
		Car.create("xu-ks-3232", "Make", "2012", "Model", "2012-09-18", null, "2012-09-08", 1234, null);
		
	}
}
