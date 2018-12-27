package counting;

import java.util.Scanner;

public class Question_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String userInput = "";
		int aCounter = 0;
		int tCounter = 0;
		int gCounter = 0;
		int cCounter = 0;
		int userInputLength = 0;
		boolean errorFlag = false;

		userInput = getUserInput();
		userInputLength = userInput.length();
		int stringCursor = 1;
		userInput = userInput.toLowerCase();

		while (userInputLength != 0) {

			switch (userInput.substring(stringCursor - 1, stringCursor)) {
			case "a":
				aCounter += 1;
				break;

			case "t":
				tCounter += 1;
				break;

			case "c":
				cCounter += 1;
				break;

			case "g":
				gCounter += 1;
				break;

			default:
				errorFlag = true;
				break;
			}

			stringCursor += 1;
			userInputLength -= 1;

			if (errorFlag) {
				System.out.println("\nWrong nucleotide entry , terminating !\n");
				break;

			}
		}

		if (!errorFlag) {
			printCounts(aCounter, tCounter, gCounter, cCounter);
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

	private static void printCounts(int aCounter, int tCounter, int gCounter, int cCounter) {

		System.out.println("\nAdenine  Count : " + aCounter);
		System.out.println("\nCytosine Count : " + cCounter);
		System.out.println("\nGuanine Count : " + gCounter);
		System.out.println("\nThymine Count : " + tCounter);

	}

}
