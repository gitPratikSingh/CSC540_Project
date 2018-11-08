package model;

import java.sql.ResultSet;
import java.sql.Statement;

import db.DBBuilder;

public class Parts {

	private static int getNextId(){
		int part_id = 0;
		try {
			Statement stmt = null;
			stmt = DBBuilder.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select Parts_Seq.nextval from dual");
			rs.next();
			part_id = rs.getInt(1);
		} 
    	catch(Throwable e) {
	        e.printStackTrace();
	    }
		return part_id;
		
	}
	
	public static int create(String part_name, String unit_price) {
		
		Statement stmt = null;
		int part_id = getNextId();

		part_name = "'"+part_name+"'";
		unit_price = "'"+unit_price+"'";
		
		String sql =  "INSERT INTO Parts VALUES("
				+ part_id+","
				+ part_name +","
				+ unit_price +")"
				;
		
		try {	
			stmt = DBBuilder.getConnection().createStatement();
	        System.out.println(sql);
			stmt.executeUpdate(sql);
		} 
    	catch(Throwable e) {
	        e.printStackTrace();
	    }
		
		return part_id;

	}
	
	public static int delete(int part_id) {
		
		Statement stmt = null;
		String sql =  "DELETE FROM Parts WHERE id = " + part_id;
		
		try {	
			stmt = DBBuilder.getConnection().createStatement();
	        System.out.println(sql);
			stmt.executeUpdate(sql);
		} 
    	catch(Throwable e) {
	        e.printStackTrace();
	    }
		
		return part_id;

	}

	public static void update(int part_id, String part_name, String unit_price) {
		
		Statement stmt = null;
		part_name = "'"+part_name+"'";
		unit_price = "'"+unit_price+"'";
		
		String sql =  "Update Parts Set part_name = " +part_name+
				", unit_price = " +unit_price+
				" WHERE id = " + part_id;
		
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
