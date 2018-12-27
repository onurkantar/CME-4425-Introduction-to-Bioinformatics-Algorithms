package counting;

import java.util.Scanner;

public class Question_3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String userInput = getUserInput();
		userInput = userInput.toLowerCase();
		String userOutput = "";
		boolean errorFlag = false;
		int inputCursor = 1;
		while(userInput.length() != (inputCursor-1)) {
			String changeChar = userInput.substring(inputCursor-1,inputCursor);
		switch (userInput.substring(inputCursor-1, inputCursor)) {
		case "a":
			changeChar = changeChar.replace("a", "t");
			break;

		case "t":
			changeChar = changeChar.replace("t", "a");
			break;

		case "g":
			changeChar = changeChar.replace("g", "c");
			break;

		case "c":
			changeChar = changeChar.replace("c", "g");
			break;

		default:
			errorFlag = true;
			break;
		}
		
		if(errorFlag) {
			System.out.println("\nWrong input !\n");
			
		}
		
		changeChar = changeChar.toUpperCase();
		userOutput = changeChar + userOutput.substring(0);
		inputCursor+= 1;
		
	}

		if(!errorFlag) {
			
			System.out.println("\nThe output sequence is : \n"+userOutput);
			
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

}
