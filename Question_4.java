package counting;

import java.util.Scanner;

public class Question_4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String userInput = getUserInput();
		String t = getUserInput();
		userInput = userInput.toLowerCase();
		t = t.toLowerCase();
		String userOutput = "";
		
		int firstPointer = 0;
		
		while((firstPointer+(t.length()-1)) < userInput.length()) {
			
			
				
			if(userInput.substring(firstPointer, firstPointer+t.length()).equals(t)) {
						
						userOutput += " " + (firstPointer+1);
			}
				
					firstPointer += 1;
			
			
			
		}
		
		System.out.println("\n " + userOutput);
		
		
		
		
		
		
		

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
