package model;

import java.sql.Statement;

import db.DBBuilder;

public class MonthlyPayrolls {
	public static String TableName = "Monthly_payroll";
	/*
	Payroll_ID​​ Varchar(50)
	Month Varchar(50)
	Monthly_salary INTEGER
	PRIMARY KEY(Payroll_ID)
	FOREIGN KEY(Payroll_ID) references Payroll
   */
	public static void create(
			int payroll_id, 
			String month,
			int monthly_salary 
			)
	{
		
		Statement stmt = null;
		try {	
			stmt = DBBuilder.getConnection().createStatement();
			String sql = "INSERT INTO "+TableName+" VALUES ("+payroll_id+",'"
			+month+"',"+monthly_salary+")"
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
		
public static int delete(int payroll_id) {
		
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

		return payroll_id;

	}
/*
	public static void main(String [] args)
	{
		MonthlyPayrolls.create(55,"January",10000);
		//Payrolls.update(55, "payroll_type", "hourly");
		//Payrolls.delete(55);

	}
	*/
	
	
}
