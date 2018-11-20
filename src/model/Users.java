package model;

import java.sql.Statement;

import db.DBBuilder;

public class Users {
	
	
		public static String TableName = "Users";
		
		
		public static void create(
				String email,
				String name,
				String address,
				String phone,
				String password)
		{
			
			Statement stmt = null;
			try {	
				stmt = DBBuilder.getConnection().createStatement();
				String sql = "INSERT INTO "+TableName+" VALUES ('"+email+"','"
				+name+"','"+address+"','"+phone+"','"+password+"')"
				;
				
		        //System.out.println(sql);
				stmt.executeUpdate(sql);
			} 
	    	catch(Throwable e) {
		        e.printStackTrace();
		    }
		}
		
	public static void update(
				String email,
				String column_name,
				String string_value)
			{
		Statement stmt = null;
		
		String sql = "UPDATE USERS SET "+column_name+" = '"+string_value+"' WHERE email = '"+email+"'";
		
		try {	
			stmt = DBBuilder.getConnection().createStatement();
	        //System.out.println(sql);
			stmt.executeUpdate(sql);
		} 
		catch(Throwable e) {
	        e.printStackTrace();
	    }
				
				
	}
			
			
		
	public static void update(
				String name,
				String address,
				String email, 
				String phone) {
			
			Statement stmt = null;
				
			String sql =  "Update "+TableName+" Set "
					+" name= "+"'"+name+"'"
					+", address = "+"'"+address+"'"
					+", phone = "+"'"+phone+"'"
					+" WHERE email = "+"'" +email+"'"
					;
			
			try {	
				stmt = DBBuilder.getConnection().createStatement();
		        //System.out.println(sql);
				stmt.executeUpdate(sql);
			} 
			catch(Throwable e) {
		        e.printStackTrace();
		    }	
		
		}
	

	public static String delete(String email) {
			
			Statement stmt = null;
			String sql =  "DELETE FROM "+TableName+" WHERE email = '"+email+"'";
			
			try {	
				stmt = DBBuilder.getConnection().createStatement();
		        //System.out.println(sql);
				stmt.executeUpdate(sql);
			} 
	    	catch(Throwable e) {
		        e.printStackTrace();
		    }
			//return whatever is associated to
			return email;

		}
	
/*	public static void main(String [] args)
	{
		Users.create("test_manager@123", "test_mgr", "test_Addr","919-867-9433","test123");
		
		
		//Customer.delete(55,"ren@124.com");
		
	}
	*/
	

}
