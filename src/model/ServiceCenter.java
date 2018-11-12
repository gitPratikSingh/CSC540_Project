package model;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import db.DBBuilder;

public class ServiceCenter {

	/*
	Service_Center
		(
		service_center_id INTEGER
		telephone VARCHAR(10)
		name VARCHAR(20)
		address VARCHAR(30)
		PRIMARY KEY(service_center_id )
		)

	 * 
	 * */

	
	public static void create(int service_center_id, String telephone, String name, String address) {
		
		Statement stmt = null;
		
		telephone = "'"+telephone+"'";
		name = "'"+name+"'";
		address = "'"+address+"'";
		
		String sql =  "INSERT INTO Service_Center VALUES("
				+ service_center_id+","
				+ telephone +","
				+ name +","
				+ address 
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
	
	public static void delete(int service_center_id) {
		
		Statement stmt = null;
		String sql =  "DELETE FROM Service_Center WHERE service_center_id = " + service_center_id;
		
		try {	
			stmt = DBBuilder.getConnection().createStatement();
	        System.out.println(sql);
			stmt.executeUpdate(sql);
		} 
    	catch(Throwable e) {
	        e.printStackTrace();
	    }
		
	}

	public static void update(int service_center_id, String telephone, String name, String address) {
		
		Statement stmt = null;
		telephone = "'"+telephone+"'";
		name = "'"+name+"'";
		address = "'"+address+"'";
		
		String sql =  "Update Service_Center Set telephone = " +telephone+
				", name = " +name+
				", address = " +address+
				" WHERE service_center_id = " + service_center_id;;
		
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
