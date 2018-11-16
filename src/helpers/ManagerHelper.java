package helpers;


import java.util.Random;
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
			
			
		
	


		
		
		
		
/*		public static void main(String [] arg)
		{
			
		
			System.out.println(isValidRole("manager"));
			
			createManager("testManager@123", "testname" ,"testAddress","12345678","9999999999",
				999999999, (float) 999.99,"S004",9999,"january" );
	
			
			
			
		}
	*/	
		
		
		
		
}



