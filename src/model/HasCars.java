package model;

import java.sql.Statement;

import db.DBBuilder;

public class HasCars {

	public static void create(String customerId, String licensePlateNumber){
		
		Statement stmt = null;
		licensePlateNumber = "'"+licensePlateNumber+"'";
		
		try {	
			stmt = DBBuilder.getConnection().createStatement();
			String sql =  "INSERT INTO Has_Cars VALUES("
					+ licensePlateNumber+","
					+ customerId +")";
			
			stmt.executeUpdate(sql);
		} 
    	catch(Throwable e) {
	        e.printStackTrace();
	    }
		
	}
}
