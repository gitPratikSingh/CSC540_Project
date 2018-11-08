package model;

import java.sql.ResultSet;
import java.sql.Statement;

import db.DBBuilder;

public class ServiceCenter {
	
	private static int getNextId(){
		int service_center_id = 0;
		try {
			Statement stmt = null;
			stmt = DBBuilder.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select Service_Center_Seq.nextval from dual");
			rs.next();
			service_center_id = rs.getInt(1);
		} 
    	catch(Throwable e) {
	        e.printStackTrace();
	    }
		return service_center_id;
		
	}
	
	public static int create(String telephone, String name, String address){
		
		Statement stmt = null;
		int service_center_id =getNextId();
		telephone = "'"+telephone+"'";
		name = "'"+name+"'";
		address = "'"+address+"'";
		
		String sql =  "INSERT INTO Service_Center(service_center_id, telephone, name, address) VALUES("
				+ service_center_id+","
				+ telephone +","
				+ name +","
				+ address +")"
				;
		
		try {	
			stmt = DBBuilder.getConnection().createStatement();
	        System.out.println(sql);
			stmt.executeUpdate(sql);
		} 
    	catch(Throwable e) {
	        e.printStackTrace();
	    }
		return service_center_id;
		
	}

}
