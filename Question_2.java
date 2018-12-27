package counting;

import java.util.Scanner;

public class Question_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String userInput = getUserInput();
		userInput = userInput.toLowerCase();
		
		if(!userInput.isEmpty()) {
			System.out.println("MAHMUT");
			userInput = userInput.replace('t', 'u');
			userInput = userInput.toUpperCase();
			printScreen(userInput);
		}
		else {
			System.out.println("\nPlease enter a string !\n");
		}
		
		

	}
	
	private static String getUserInput() {
		String retVal = "";
		Scanner sc = new Scanner(System.in);
		System.out.println("\nPlease Enter The DNA Sequence\n");
		System.out.println("\nDNA Sequence : ");
		retVal = sc.next();
		return retVal;
	}
	
	private static void printScreen(String userOutput) {
		System.out.println("\nTransformed as : \n");
		System.out.println(userOutput);
	}

}
