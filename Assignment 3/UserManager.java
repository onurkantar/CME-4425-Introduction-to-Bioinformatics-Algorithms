import java.util.ArrayList;
import java.util.Scanner;

public class UserManager {
	
	static int numberOfSequences;
	
	public UserManager() {
		// TODO Auto-generated constructor stub
	}
	
	
	public static ArrayList<String> getUserInputs() {
		
		Scanner scanner4input = new Scanner(System.in);
		
		System.out.println("Please Enter Your Number of Sequences\n");
		
		int NOS = scanner4input.nextInt();
		
		System.out.println("Please Enter Your Inputs\n");
		
		ArrayList<String> inputs = new ArrayList<>();
		
		numberOfSequences = NOS;
		
		while(NOS != 0) {
			inputs.add(scanner4input.next());
			NOS -- ;
		}
		
		
		return inputs;
	}

	public static int getUserPenalty() {
		
		Scanner scanner4penalty = new Scanner(System.in);
		
		System.out.println("Please Enter The Gap Penalty\n");
		
		try {
			
			return Integer.parseInt(scanner4penalty.nextLine());

		} catch (Exception e) {
			
			System.out.println("Please Enter Your Gap Penalty As An Integer !\n");
			
		}
		
		return (Integer) null;
		
	}
	

}
