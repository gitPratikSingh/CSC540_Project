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
	
	public static boolean orderExists(int partId, String serviceCenterId){
		
		try {
			Statement stmt = null;
			stmt = DBBuilder.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM ORDERS WHERE part_id = '"+partId+"' AND service_center_id = "+"'"+serviceCenterId+"' "
					+ "AND status like 'pending%'");
			if(rs.next())
				return true;
		} 
    	catch(Throwable e) {
	        e.printStackTrace();
	    }
		return false;
		
	}
	
	
	public static void create(String service_center_id, Timestamp order_date, int part_id, int quantity, String status) {
		
		Statement stmt = null;
		ResultSet rs=null;
	
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
		//condition to decrement table 
		if(service_center_id.contains("S")) 
		{
		try {
		
			stmt = DBBuilder.getConnection().createStatement();
		    rs = stmt.executeQuery(
					"UPDATE HAS_PARTS SET CURRENT_QUANTITY=(CURRENT_QUANTITY-"+quantity+")"
					+" WHERE PART_ID = "+part_id+" AND SERVICE_CENTER_ID = '"+service_center_id+"'");
		}
	catch(Throwable e) {
        e.printStackTrace();}
		
		
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
	
public static void update(int order_id, String newStatus) {
		
		Statement stmt = null;
		
		String sql =  "SELECT * FROM Orders WHERE order_id = "+ order_id;
		String status = "pending,S0002,2018-11-21 12:10:16.984";
		int part_id =0, quantity = 0;
		String service_center_id = "S0001";
		try {	
			stmt = DBBuilder.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				status = rs.getString("status");
				part_id = rs.getInt("part_id");
				quantity = rs.getInt("quantity");
				service_center_id = rs.getString("service_center_id");
			}
		}
    	catch(Throwable e) {
	        e.printStackTrace();
	    }
		
		String finalStatus = newStatus+","+status.split(",")[1]+","+status.split(",")[2];
		
		sql =  "Update Orders Set status = '" +finalStatus+"'"+
				" WHERE order_id = " + order_id;
		
		try {	
			stmt = DBBuilder.getConnection().createStatement();
			stmt.executeUpdate(sql);
		} 
		catch(Throwable e) {
	        e.printStackTrace();
	    }
		
		if(part_id!=0){
			HasParts.incr(service_center_id, part_id, quantity);
		}
	}

public static void generateNotif(Timestamp curTime){
	 // go through all the pending orders
	 // split the status
	 // check if arrivaldate == today()
	 // generate the notif
	 
	 	Statement stmt = null;
		
		String sql =  "SELECT * FROM Orders WHERE status like 'pending%'";
		String status = "pending,S0002,2018-11-21 12:10:16.984";
		int part_id =0, quantity = 0;
		int order_id = 0;
		String service_center_id = "S0001";
		try {	
			stmt = DBBuilder.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				status = rs.getString("status");
				order_id = rs.getInt("order_id");
				service_center_id = rs.getString("service_center_id");
				Timestamp ts = Timestamp.valueOf(status.split(",")[2]);
				
				if(ts.before(curTime)){
					// delayed orders
					Orders.update(order_id, "delayed");
					Notification.create(service_center_id, "The order with ID "+order_id+" was delayed!");
					
				}
				
			}
		}
		catch(Throwable e) {
	        e.printStackTrace();
	    }
		
 }
}
