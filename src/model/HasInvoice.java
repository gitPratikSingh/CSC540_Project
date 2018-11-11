package model;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import db.DBBuilder;

public class HasInvoice {

	/*
	
	{
		Appointment_ID  VARCHAR(20)
		Invoice_ID  INTEGER
		PRIMARY_KEY(Appointment_ID)
		FOREIGN_KEY(Appointment_ID) REFERENCES Appointment
		FOREIGN_KEY(Invoice_ID) REFERENCES Invoice
		}

	*/

	 public static void create(int appointment_id, int invoice_id){
		 Statement stmt = null;
		 String sql =  "INSERT INTO Has_Invoice VALUES("
				+ appointment_id+","
				+ invoice_id +")"
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
	
	public static void delete(int appointment_id, int invoice_id) {
		Statement stmt = null;
		 String sql =  "DELETE FROM Has_Invoice WHERE appointment_id ="
				+ appointment_id+" AND invoice_id ="
				+ invoice_id ;
		
		
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