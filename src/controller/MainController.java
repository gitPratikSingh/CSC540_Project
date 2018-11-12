package controller;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import db.DBBuilder;

/**
 * Text GUI of project
 *
 */
public class MainController {

	private Scanner scanner = new Scanner(System.in);

	private final static String START_MENU = "----------\nSTART MENU\n----------\n1. Login\n2. SignUp\n3. Exit\n";
	private final static String LOGIN_MENU = "-----\nLOGIN MENU\n-----\n1. Sign-In\n2. Go Back\n";
	private final static String SIGNUP_MENU = "-----\nSIGNUP MENU\n-----\n1. Sign-Up\n2. Go Back\n";
	private final static String ENTER_EMAIL = "\nENTER USERNAME\n";
	private final static String ENTER_PASSWORD = "\nENTER PASSWORD\n";
	private final static String EXIT = "Exited Program\n";
	private static final String ENTER_NAME = "\nENTER NAME\n";;
	private static final String ENTER_ADDRESS = "\nENTER ADDRESS\n";;
	private static final String ENTER_PHONE = "\nENTER PHONE\n";;
	


	/**
	 * starts the program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		MainController textGUI = new MainController();
		textGUI.startMenu();
	}

	/**
	 * Where the program starts
	 */
	void startMenu() {

		System.out.print(START_MENU);

		switch (checkNumericalInput(1, 3)) {
		case -1:
			startMenu();
			break;
		case 1:
			loginMenu();
			break;
		case 2:
			signupMenu();
			break;
		case 3:
			exit();
			break;
		default:
			break;
		}

	}

	private int checkNumericalInput(int startNum, int endNum) {
		
		String input = scanner.nextLine();
		try {
			int inputNum = Integer.parseInt(input);
			if (inputNum >= startNum && inputNum <= endNum) {

				return inputNum;
			}
		} catch (Exception e) {
			System.out.printf("Please input a value between %d and %d inclusive.\n", startNum, endNum);
			return -1;
		}
		System.out.printf("Please input a value between %d and %d inclusive.\n", startNum, endNum);
		return -1;
	}

	
	private void loginMenu() {
		System.out.print(LOGIN_MENU);
	
		switch (checkNumericalInput(1, 2)) {
		case -1:
			loginMenu();
			break;
		case 1:
			login();
			break;
		case 2:
			startMenu();
			break;
		default:
			break;
		}
	}
	
	private void login() {
		System.out.print(ENTER_EMAIL);
		String username = scanner.nextLine();
		System.out.print(ENTER_PASSWORD);
		String password = scanner.nextLine();
				
		switch (validateCredentials(username, password)) {
		case "manager":
			managerMenu(username);
			break;
		case "receptionist":
			receptionistMenu(username);
			break;
		case "customer":
			customerMenu(username);
			break;
		case "":
			System.out.println("Invalid Crednatials!!\n Please try again!\n");
			startMenu();
			break;
		default:
			break;
		}
		
	}
	
	private void customerMenu(String username) {
		new CustomerController().customerMenu(this, username);
	}

	private void receptionistMenu(String username) {
		// TODO Auto-generated method stub
		
	}

	private void managerMenu(String username) {
		// TODO Auto-generated method stub
		
	}

	private String validateCredentials(String username, String password){
		username = "'"+username+"'";
		password = "'"+password+"'";
		
		String user_query = "SELECT count(*) FROM USERS WHERE email="+username +" AND password = "+password;
		String role_query = "SELECT role FROM EMPLOYEE WHERE email="+username;
		String customer_query = "SELECT count(*) FROM CUSTOMER WHERE email="+username;
		
		int userExists = 0;
		
		try {
			Statement stmt = null;
			stmt = DBBuilder.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(user_query);
			rs.next();
			userExists = rs.getInt(1);
		} 
    	catch(Throwable e) {
	        e.printStackTrace();
	    }
		
		String role ="";
		if (userExists == 1){
			try {
				Statement stmt = null;
				stmt = DBBuilder.getConnection().createStatement();
				ResultSet rs = stmt.executeQuery(role_query);
				
				if(rs.next())
					role = rs.getString(1);
				else
				{
					stmt = DBBuilder.getConnection().createStatement();
					rs = stmt.executeQuery(customer_query);
					
					if(rs.next())
						role = "customer";
				}
			} 
	    	catch(Throwable e) {
		        e.printStackTrace();
		    }
			
			return role;
		}else{
			return "";
		}
		
	}
	
	private void signupMenu() {
		
		System.out.print(ENTER_EMAIL);
		String email = scanner.nextLine();
		System.out.print(ENTER_PASSWORD);
		String password = scanner.nextLine();
		System.out.print(ENTER_NAME);
		String name = scanner.nextLine();
		System.out.print(ENTER_ADDRESS);
		String address = scanner.nextLine();
		System.out.print(ENTER_PHONE);
		String phone = scanner.nextLine();
		
		System.out.print(SIGNUP_MENU);
		
		switch (checkNumericalInput(1, 2)) {
		case -1:
			signupMenu();
			break;
		case 1:
			signup(email, password, name, address, phone);
			break;
		case 2:
			startMenu();
			break;
		default:
			break;
		}
		
	}

	
	private void signup(String email, String password, String name, String address, String phone) {
		// use user's model to store the data
		
		email = "'"+email+"'";
		password = "'"+password+"'";
		name = "'"+name+"'";
		address = "'"+address+"'";
		phone = "'"+phone+"'";
		
		Statement stmt = null;
		
		String sql =  "INSERT INTO USERS VALUES("
				+ email+","
				+ name +","
				+ address +","
				+ phone +","
				+ password 
				+")"
				;
		
		try {	
			stmt = DBBuilder.getConnection().createStatement();
	        System.out.println(sql);
			stmt.executeUpdate(sql);
		} 
    	catch(Throwable e) {
	        e.printStackTrace();
	    }
		
		System.out.println("User created Successfully!\n ");
		
		loginMenu();
		
	}

	private void exit() {
		System.out.print(EXIT);
		scanner.close();
		System.exit(0);
	}
}