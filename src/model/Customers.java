package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import db.DBBuilder;

public class Customers {
	public static String TableName = "Customer";
	
	public static final String RALEIGH="Raleigh";
	public static final String CHARLOTTE="Charlotte";
	
	public static String getCity(String email)
	{
		Statement stmt = null;
		email = "'"+email+"'";
		try {	
			stmt = DBBuilder.getConnection().createStatement();
			String sql = "SELECT address from USERS WHERE email="+email;
			
	        System.out.println(sql);
	        ResultSet rs = stmt.executeQuery(sql);
	        if (rs.next()) {
	 	       String adr = rs.getString("ADDRESS");
	 	       if(adr.indexOf(RALEIGH)!=-1){
	 	    	   return RALEIGH;
	 	       }else{
	 	    	   return CHARLOTTE;
	 	       }
	 	    }
		} 
    	catch(Throwable e) {
	        e.printStackTrace();
	    }
		
		return TableName;
	}
	
	public static void create(
			int customer_id, 
			String email)
	{
		
		Statement stmt = null;
		try {	
			stmt = DBBuilder.getConnection().createStatement();
			String sql = "INSERT INTO "+TableName+" VALUES("+customer_id+",'"
			+email+"')"
			;
			
	        System.out.println(sql);
			stmt.executeUpdate(sql);
		} 
    	catch(Throwable e) {
	        e.printStackTrace();
	    }
	}
	/*
public static void update(
			String email,
			String column_name,
			String string_value)
		{
	Statement stmt = null;
	
	String sql = "UPDATE "+TableName+" SET "+column_name+" = '"+string_value+"' WHERE email = '"+email+"'";
	
	try {	
		stmt = DBBuilder.getConnection().createStatement();
        System.out.println(sql);
		stmt.executeUpdate(sql);
	} 
	catch(Throwable e) {
        e.printStackTrace();
    }			
}
		*/

public static String delete(String email) {
		
		Statement stmt = null;
		String sql =  "DELETE FROM "+TableName+" WHERE email = '"+email+"'";
	
		try {	
			stmt = DBBuilder.getConnection().createStatement();
	        System.out.println(sql);
			stmt.executeUpdate(sql);
		} 
    	catch(Throwable e) {
	        e.printStackTrace();
	    }
		//return whatever is associated to
		return email;

	}
/*
public static void main(String [] args)
{
   // Customer.create(999, "ren@1243.com");
   Customer.delete("ren@1243.com");
}*/


}



	


