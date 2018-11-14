package model;

import java.sql.Statement;

import db.DBBuilder;

public class Payrolls {
	public static String TableName = "payroll";
	/*
Payroll_ID​​ Varchar(50)
Payroll_type Varchar(50)
PRIMARY KEY(Payroll_ID))
	 */
	
	public static void create(
			int payroll_id, 
			String payroll_type)
	{
		
		Statement stmt = null;
		try {	
			stmt = DBBuilder.getConnection().createStatement();
			String sql = "INSERT INTO "+TableName+" VALUES ("+payroll_id+",'"
			+payroll_type+"')"
			;
			
	        System.out.println(sql);
			stmt.executeUpdate(sql);
		} 
    	catch(Throwable e) {
	        e.printStackTrace();
	    }
	}
	//basically to change only role 
public static void update(
		int payroll_id, 
		String column_name,
		String string_value	)
		{
	Statement stmt = null;
	
	
	String sql = "UPDATE "+TableName+" SET "+column_name+" = '"+string_value+"' WHERE payroll_id = "+payroll_id;
	
	try {	
		stmt = DBBuilder.getConnection().createStatement();
        System.out.println(sql);
		stmt.executeUpdate(sql);
	} 
	catch(Throwable e) {
        e.printStackTrace();
    }
					
}
		
public static void delete(int payroll_id) {
		
		Statement stmt = null;
		String sql =  "DELETE FROM "+TableName+" WHERE payroll_id = "+payroll_id;
		
		try {	
			stmt = DBBuilder.getConnection().createStatement();
	        System.out.println(sql);
			stmt.executeUpdate(sql);
		} 
    	catch(Throwable e) {
	        e.printStackTrace();
	    }
		//return whatever is associated to
		

	}
/*
	public static void main(String [] args)
	{
		//Payrolls.create(55,"monthly");
		//Payrolls.update(55, "payroll_type", "hourly");
		Payrolls.delete(55);

	}
	*/

}
