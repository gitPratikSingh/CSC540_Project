package helpers;


import java.util.Random;

import db.DBBuilder;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;  
import java.util.Date;  
import helpers.Constants;
import model.Users;
import model.hasPay;
import model.Employees;
import model.HasManager;
import model.HasMechanic;
import model.HourlyPayrolls;
import model.MonthlyPayrolls;
import model.Payrolls;
public class ManagerHelper {
	
	
	
		
		public static int generateRandom()
		{
			Random rand=new Random();
			int result =rand.nextInt(999999999)+1;
			
			return result;
		}
		public static boolean isValidRole(String role)
		{
		Boolean result =false ;
		String ROLES[]= {"receptionist","mechanic"};
		for(int i=0 ;i<2;i++ )
		{
			if (ROLES[i]==role)
				result=true;
		}
		return result;
		}
		
		//populate the previous payrolls if needed 
		
		//get a 9 digit random number with proper padded zero
		
		//
			
			
		public static Timestamp getCurrentTimestamp()
		{

			Timestamp time =new Timestamp(System.currentTimeMillis());
			return time;
		}
	
		public static boolean isReceptionistPresent(String ManagerSid)
		{
			boolean result= true ;
			try {
				Statement stmt = null;
				stmt = DBBuilder.getConnection().createStatement();
				ResultSet rs = stmt.executeQuery("SELECT EMPLOYEE_ID FROM HAS_RECEPTIONIST WHERE service_center_id ='"
						+ "ManagerSid"+"'");
				if(rs.next()==false)
				{
					result =false;
				}
				
				
			} 
	    	catch(Throwable e) {
		        e.printStackTrace();
		    }
			return result;
			
		}
		

		public static int getNextPayrollId(){
	
			
			
			int payroll_id = 0;
			try {
				Statement stmt = null;
				stmt = DBBuilder.getConnection().createStatement();
				ResultSet rs = stmt.executeQuery("SELECT MAX(PAYROLL_ID) AS PID FROM PAYROLL");
				if(rs.next()!=false)
				payroll_id = rs.getInt("PID");
				else 
				payroll_id= 0;
			} 
	    	catch(Throwable e) {
		        e.printStackTrace();
		    }
			return payroll_id+1;
			
			

		}
		
		
		
		public static void main(String [] arg)
		{
			
	// System.out.println(receptionistCheck("S0003"));
	 
	 //System.out.println(isValidRole("receptionist"));
			
			
		}
	
		
		
		
		
}



