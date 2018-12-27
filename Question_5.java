package counting;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Question_5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String userInput = getUserInput();
		userInput = userInput.toUpperCase();
		String userOutput = "";
		
		Map aminoacids = new HashMap();
		aminoacids = fillTable(aminoacids);
		
		int firstPointer = 0;
		
		while(firstPointer < userInput.length()) {
			if(aminoacids.get(userInput.substring(firstPointer, firstPointer+3)).equals("STOP")) {
				break;
			}
			userOutput = userOutput + aminoacids.get(userInput.substring(firstPointer, firstPointer+3));
			firstPointer += 3;
		}
		
		System.out.println("\n" + userOutput);
		
		

	}
	
	@SuppressWarnings("unchecked")
	private static Map fillTable(Map aminoacids) {
		
		aminoacids.put("UUU", "F");
		aminoacids.put("UUC", "F");
		aminoacids.put("UUA", "L");
		aminoacids.put("UUG", "L");
		aminoacids.put("UCU", "S");
		aminoacids.put("UCC", "S");
		aminoacids.put("UCA", "S");
		aminoacids.put("UCG", "S");
		aminoacids.put("UAU", "Y");
		aminoacids.put("UAC", "Y");
		aminoacids.put("UAA", "STOP");
		aminoacids.put("UAG", "STOP");
		aminoacids.put("UGU", "C");
		aminoacids.put("UGC", "C");
		aminoacids.put("UGA", "STOP");
		aminoacids.put("UGG", "W");
		
		aminoacids.put("CUU", "L");
		aminoacids.put("CUC", "L");
		aminoacids.put("CUA", "L");
		aminoacids.put("CUG", "L");
		aminoacids.put("CCU", "P");
		aminoacids.put("CCC", "P");
		aminoacids.put("CCA", "P");
		aminoacids.put("CCG", "P");
		aminoacids.put("CAU", "H");
		aminoacids.put("CAC", "H");
		aminoacids.put("CAA", "Q");
		aminoacids.put("CAG", "Q");
		aminoacids.put("CGU", "R");
		aminoacids.put("CGC", "R");
		aminoacids.put("CGA", "R");
		aminoacids.put("CGG", "R");
		
		aminoacids.put("AUU", "I");
		aminoacids.put("AUC", "I");
		aminoacids.put("AUA", "I");
		aminoacids.put("AUG", "M");
		aminoacids.put("ACU", "T");
		aminoacids.put("ACC", "T");
		aminoacids.put("ACA", "T");
		aminoacids.put("ACG", "T");
		aminoacids.put("AAU", "N");
		aminoacids.put("AAC", "N");
		aminoacids.put("AAA", "K");
		aminoacids.put("AAG", "K");
		aminoacids.put("AGU", "S");
		aminoacids.put("AGC", "S");
		aminoacids.put("AGA", "R");
		aminoacids.put("AGG", "R");
		
		aminoacids.put("GUU", "V");
		aminoacids.put("GUC", "V");
		aminoacids.put("GUA", "V");
		aminoacids.put("GUG", "V");
		aminoacids.put("GCU", "A");
		aminoacids.put("GCC", "A");
		aminoacids.put("GCA", "A");
		aminoacids.put("GCG", "A");
		aminoacids.put("GAU", "D");
		aminoacids.put("GAC", "D");
		aminoacids.put("GAA", "E");
		aminoacids.put("GAG", "E");
		aminoacids.put("GGU", "G");
		aminoacids.put("GGC", "G");
		aminoacids.put("GGA", "G");
		aminoacids.put("GGG", "G");
	
		
		return aminoacids;
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
