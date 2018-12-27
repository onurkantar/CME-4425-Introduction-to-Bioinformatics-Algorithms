package counting;

import java.io.BufferedReader;
import java.util.Scanner;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Question_6 {
	
	private static HttpURLConnection con;

	public static void main(String[] args) throws MalformedURLException, ProtocolException, IOException  {
		// TODO Auto-generated method stub
		String userInput = getUserInput();
		String inputs[] = userInput.split(",");
		int index = 0;
		
		while(inputs.length > index) {
		String fasta = getFasta("https://www.uniprot.org/uniprot/" + inputs[index] + ".fasta");

		boolean isFirst = true;
		String userOutput = "";
		
        int firstPointer = 0;
        while(firstPointer <= fasta.length()-4) {
        	if(fasta.substring(firstPointer, firstPointer+1).equals("N")) {
        		
        		if(!fasta.substring(firstPointer+1,firstPointer+2).equals("P")) {
        			
        			if(fasta.substring(firstPointer+2,firstPointer+3).equals("S") || fasta.substring(firstPointer+2,firstPointer+3).equals("T") ) {
        				
        				if(!fasta.substring(firstPointer+3,firstPointer+4).equals("P") ) {
        					
        					if (isFirst) {
        						userOutput +=(firstPointer+1);
        						isFirst = false;
							} else {
        						userOutput += " " + (firstPointer+1);

							}
        					
        					
        				}
        				
        			}
        			
        		}
        		
        	}
        	
        	firstPointer++;
        	
        }
        if(!userOutput.equals("")) {
        	
        	System.out.println(inputs[index]);
        	System.out.println(userOutput);
        	
        }
        index++;
		}
	}
	
	
	@SuppressWarnings("unused")
	private static String getFasta(String fastaUrl) throws MalformedURLException,
    ProtocolException, IOException {
		
		String url = fastaUrl;
		 
	 	String fastaOutput = "";
        try {

            URL myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();

            con.setRequestMethod("GET");

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {

                String line;
                int firstLineByPass = 0;

                while ((line = in.readLine()) != null) {
                	if(firstLineByPass != 0) {
                		fastaOutput += line;           		
                	}
                	firstLineByPass++;
                }
            }

        } finally {
            
            con.disconnect();
        }
	

		
		return fastaOutput;
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
