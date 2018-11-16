package model;

import java.sql.Statement;

import db.DBBuilder;

public class Employees {
	public static String TableName = "Employee";
	
	/*
	 * Employee
{
employee_Id​​ INTEGER
email ​ CHAR(20)
Role CHAR(20)
PRIMARY KEY (​ Employee_id, Email​​ )
FOREIGN KEY(Email)REFERENCES User
}
	*/
	
	public static void create(
			int employee_id,
			String email,
			String role)
	{
		
		Statement stmt = null;
		try {	
			stmt = DBBuilder.getConnection().createStatement();
			String sql = "INSERT INTO "+TableName+" VALUES ("+employee_id+",'"
			+email+"','"+role+"')"
			;
			
	        System.out.println(sql);
			stmt.executeUpdate(sql);
		} 
    	catch(Throwable e) {
	        e.printStackTrace();
	    }
	}
	
public static void update(
		    int employee_id,	
		    String email,
			String column_name,
			String string_value)
//only role and value
		{
	Statement stmt = null;
	
	String sql = "UPDATE "+TableName+" SET "+column_name+" = '"+string_value+"' WHERE email = '"+email+"'"
	+"AND employee_id = "+employee_id;
	
	try {	
		stmt = DBBuilder.getConnection().createStatement();
        System.out.println(sql);
		stmt.executeUpdate(sql);
	} 
	catch(Throwable e) {
        e.printStackTrace();
    }
			
}
		
public static String delete(int employee_id,String email) {
		
		Statement stmt = null;
		String sql =  "DELETE FROM "+TableName+" WHERE email = '"+email+"'"+"AND employee_id="+employee_id;
		
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

	

/*public static void main(String [] args)
{
	Employees.create(540,"test_manager@123","manager");
	//Employees.update(55,"ren@124.com", "role", "receptionist");
	//Employees.delete(55, "ren@124.com");
}
	
*/
}
