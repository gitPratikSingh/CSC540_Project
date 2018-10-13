package controller;

import java.util.Scanner;

/**
 * Text GUI of project
 *
 */
public class MainController {

	private Scanner scanner = new Scanner(System.in);

	private final static String START_MENU = "----------\nSTART MENU\n----------\n1. Login\n2. Exit\n";
	private final static String LOGIN = "-----\nLOGIN\n-----\n";
	private final static String EXIT = "Exited Program\n";
	

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
	private void startMenu() {

		System.out.print(START_MENU);

		switch (checkNumericalInput(1, 2)) {
		case -1:
			startMenu();
			break;
		case 1:
			login();
			break;
		case 2:
			exit();
			break;
		default:
			break;
		}

	}

	/**
	 * Checks if input is numerical and within provided range inclusively
	 * 
	 * @param startNum
	 *            Starting number
	 * @param endNum
	 *            Ending Number
	 * @return Whether or not the input is a number and within the given ranges
	 */
	private int checkNumericalInput(int startNum, int endNum) {
		String input = scanner.nextLine();
		try {
			int inputNum = Integer.parseInt(input);
			if (inputNum >= startNum && endNum <= endNum) {

				return inputNum;
			}
		} catch (Exception e) {
			System.out.printf("Please input a value between %d and %d inclusive.\n", startNum, endNum);
			return -1;
		}
		System.out.printf("Please input a value between %d and %d inclusive.\n", startNum, endNum);
		return -1;
	}

	
	private void login() {
		
	}

	
	/**
	 * Exits the program.
	 */
	private void exit() {
		System.out.print(EXIT);
		scanner.close();
		System.exit(0);
	}
}