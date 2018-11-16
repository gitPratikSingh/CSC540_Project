package model;

import java.sql.Statement;

import db.DBBuilder;

public class hasPay {
	
	
	public static String TableName = "has_pay";
	/*
Has_pay
(
Payroll_id​​ Varchar(50)
Employee_id​​ Varchar(50)
PRIMARY KEY(Payroll_ID,Employee_ID)
FOREIGN KEY(Payroll_ID) references Payroll
FOREIGN KEY(Employee_ID) references Employee
)
   */
	public static void create(
			int payroll_id, 
			int employee_id 
			)
	{
		
		Statement stmt = null;
		try {	
			stmt = DBBuilder.getConnection().createStatement();
			String sql = "INSERT INTO "+TableName+" VALUES ("+payroll_id+","
			+employee_id+")"
			;
			
	        System.out.println(sql);
			stmt.executeUpdate(sql);
		} 
    	catch(Throwable e) {
	        e.printStackTrace();
	    }
	}
	//basically to change only employee ID
public static void update(
		int payroll_id, 
		
		String column_name,
		int string_value	)
		{
	Statement stmt = null;
	
	
	String sql = "UPDATE "+TableName+" SET "+column_name+" = "+string_value+" WHERE payroll_id = "+payroll_id;
	
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

/*public static void main(String [] args)
	{
		//MonthlyPayrolls.create(54,"January",10000);
		//Payrolls.update(55, "payroll_type", "hourly");
		//Payrolls.delete(55);
		hasPay.create(54, 540);

	}
	*/

}
