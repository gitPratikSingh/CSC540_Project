package controller;

import java.util.Random;

public class helper {
	
	public static int generateRandom()
	{
		Random rand=new Random();
		int result =rand.nextInt(999999999)+1;
		
		return result;
	}
	public static boolean isValidRole(String role)
	{
	Boolean result =false ;
	String ROLES[]= {"receptionist","manager","mechanic"};
	for(int i=0 ;i<3;i++ )
	{
		if (ROLES[i]==role)
			result=true;
	}
	return result;
	}
	public static void main(String [] arg)
	{
		System.out.println(isValidRole("manager"));
	}
	
	
}
