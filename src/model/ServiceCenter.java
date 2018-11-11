package model;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import db.DBBuilder;

public class ServiceCenter {

	/*
	Service_Centre
		(
		service_centre_id INTEGER
		telephone VARCHAR(10)
		name VARCHAR(20)
		address VARCHAR(30)
		PRIMARY KEY(service_centre_id )
		)

	 * 
	 * */

	
	public static void create(int service_centre_id, String telephone, String name, String address) {
		
		Statement stmt = null;
		
		telephone = "'"+telephone+"'";
		name = "'"+name+"'";
		address = "'"+address+"'";
		
		String sql =  "INSERT INTO Service_Centre VALUES("
				+ service_centre_id+","
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
	
	public static void delete(int service_centre_id) {
		
		Statement stmt = null;
		String sql =  "DELETE FROM Service_Centre WHERE service_centre_id = " + service_centre_id;
		
		try {	
			stmt = DBBuilder.getConnection().createStatement();
	        System.out.println(sql);
			stmt.executeUpdate(sql);
		} 
    	catch(Throwable e) {
	        e.printStackTrace();
	    }
		
	}

	public static void update(int service_centre_id, String telephone, String name, String address) {
		
		Statement stmt = null;
		telephone = "'"+telephone+"'";
		name = "'"+name+"'";
		address = "'"+address+"'";
		
		String sql =  "Update Service_Centre Set telephone = " +telephone+
				", name = " +name+
				", address = " +address+
				" WHERE service_centre_id = " + service_centre_id;;
		
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
