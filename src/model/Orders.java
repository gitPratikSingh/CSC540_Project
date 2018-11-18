package model;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import db.DBBuilder;

public class Orders {

	/*
	 * Orders
		{
		service_center_id INTEGER,
		order_id INTEGER,
		order_date TIMESTAMP,
		part_id INTEGER,
		quantity INTEGER,
		status VARCHAR(50),
		PRIMARY KEY (order_id) 
		FOREIGN KEY(service_center_id)REFERENCES Service_Center,
		FOREIGN KEY (part_id) REFERENCES Parts  
		}

	 * */
	
	private static int getNextId(){
		int order_id = 0;
		try {
			Statement stmt = null;
			stmt = DBBuilder.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select Orders_Seq.nextval from dual");
			rs.next();
			order_id = rs.getInt(1);
		} 
    	catch(Throwable e) {
	        e.printStackTrace();
	    }
		return order_id;
		
	}
	
	public static void create(String service_center_id, Timestamp order_date, int part_id, int quantity, String status) {
		
		Statement stmt = null;
	
		status = "'"+status+"'";
		service_center_id= "'"+service_center_id+"'";
		String sql =  "INSERT INTO Orders VALUES("
				+ service_center_id +","
				+ getNextId() + "," 
				+"CURRENT_TIMESTAMP,"
				+part_id+","
				+quantity+","
				+ status
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
	
	public static void delete(int order_id) {
		
		Statement stmt = null;
		
		String sql =  "DELETE FROM Orders WHERE order_id = "+ order_id;
		
		try {	
			stmt = DBBuilder.getConnection().createStatement();
	        System.out.println(sql);
			stmt.executeUpdate(sql);
		}
    	catch(Throwable e) {
	        e.printStackTrace();
	    }

	}
	
	public static void update(int order_id, int service_center_id, Timestamp order_date, int part_id, int quantity, String status) {
		
		Statement stmt = null;
		status = "'"+status+"'";
		
		String sql =  "Update Orders Set service_center_id = " +service_center_id+
				", order_date = " +order_date+
				", part_id = " +part_id+
				", quantity = " +quantity+
				", status = " +status+
				"WHERE order_id = " + order_id;
		
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
