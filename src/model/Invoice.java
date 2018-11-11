package model;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import db.DBBuilder;

public class Invoice {

	/*
	Invoice
		{
		Invoice_ID  INTEGER
		Price FLOAT
		PRIMARY_KEY(Invoice_ID)
		}



	 * 
	 * */

	
	public static void create(int invoice_id, String price) {
		
		Statement stmt = null;
		
		price = "'"+price+"'";
		
		
		String sql =  "INSERT INTO Invoice VALUES("
				+ invoice_id+","
				+ price 
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
	
	public static void delete(int invoice_id) {
		
		Statement stmt = null;
		String sql =  "DELETE FROM Invoice WHERE invoice_id = " + invoice_id;
		
		try {	
			stmt = DBBuilder.getConnection().createStatement();
	        System.out.println(sql);
			stmt.executeUpdate(sql);
		} 
    	catch(Throwable e) {
	        e.printStackTrace();
	    }
		
	}

	public static void update(int invoice_id, String price) {
		
		Statement stmt = null;
		price = "'"+price+"'";
		
		String sql =  "Update Invoice Set price = " +price+
				" WHERE invoice_id = " + invoice_id;;
		
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
