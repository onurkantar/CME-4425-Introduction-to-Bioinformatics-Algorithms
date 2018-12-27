import java.lang.management.GarbageCollectorMXBean;
import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// BLOSUM62Matrix bm62 = new BLOSUM62Matrix();

		Alignment al = new Alignment();
		al.getGapPenalty();
		ArrayList<String> tempSeq = new ArrayList<>();
		tempSeq = UserManager.getUserInputs();
		String[] sequences = new String[tempSeq.size()];

		for (int i = 0; i < sequences.length; i++) {
			sequences[i] = tempSeq.get(i);
		}

		double[][] similarityMatrix = new double[sequences.length][sequences.length];

		ArrayList<Integer> processedOnes = new ArrayList<Integer>();


		if (UserManager.numberOfSequences == 2) {
			similarityMatrix = al.generateSimilarityMatrix(sequences);

			String[] retVals = al.globalAlign(sequences[0], sequences[1]);

			System.out.println("Guide Tree : ");
			System.out.println("Sequence 1 -----");
			System.out.println("Sequence 2 -----");

			System.out.println();
			System.out.println();

			System.out.println("Sequence 1 : " + retVals[0]);
			System.out.println("Sequence 2 : " + retVals[1]);

		}

		else if (UserManager.numberOfSequences == 3) {
			similarityMatrix = al.generateSimilarityMatrix(sequences);

			int[] couples = al.decideCouples(similarityMatrix);
			processedOnes.add(couples[0]);
			processedOnes.add(couples[1]);
			returnCreateProfile rcp;

			rcp = al.createProfile(sequences[couples[0]], sequences[couples[1]]);

			String outer = "";
			int index = -1;

			if (couples[0] + couples[1] == 3) {

				outer = sequences[0];
				index = 1;
			}

			else if (couples[0] + couples[1] == 1) {

				outer = sequences[2];
				index = 3;
			}

			else {

				outer = sequences[1];
				index = 2;
			}

			returnProfileType rtp = al.profile2sequenceAlignment(2, rcp.profile, rcp.letters, outer);

			System.out.println("Guide Tree : ");
			System.out.println("Sequence " + (couples[0] + 1) + " -----");
			System.out.println("Sequence " + (couples[1] + 1) + " ----------");
			System.out.println("Sequence " + index + " ----------");

		}

		else if (UserManager.numberOfSequences == 4) {

			similarityMatrix = al.generateSimilarityMatrix(sequences);
			System.out.println("Guide Tree : ");
			int[] couples = al.decideCouples(similarityMatrix);
			processedOnes.add(couples[0]);
			processedOnes.add(couples[1]);
			System.out.println("Sequence : " + couples[0] + " -----");
			System.out.println("Sequence : " + couples[1] + " ----------");
			returnCreateProfile rcp;

			rcp = al.createProfile(sequences[couples[0]], sequences[couples[1]]);// first two are alligned

			String[] firstSeqs = new String[sequences.length - 2];

			int q = 0;
			for (int i = 0; i < sequences.length; i++) {// finding who are not alligned

				if (!processedOnes.contains(i)) {

					firstSeqs[q] = sequences[i];
					q++;
				}

			}

			similarityMatrix = al.profileSequenceSimilarityMatrix(rcp.profile, firstSeqs, 2, rcp.letters);
			couples = al.decideCouples(similarityMatrix);// deciding next 2
			processedOnes.clear();
			processedOnes.add(couples[0]);
			processedOnes.add(couples[1]);

			boolean seq1 = false;
			int sq1 = 0;
			boolean seq2 = false;
			int sq2 = 0;

			for (int i = 0; i < firstSeqs.length; i++) {// deciding which one is sequence

				if (firstSeqs[i].equals(sequences[couples[0]])) {
					seq1 = true;
					sq1 = i;
					processedOnes.remove(0);
					processedOnes.remove(0);

					for (int j = 0; j < sequences.length; j++) {
						if (firstSeqs[i].equals(sequences[j])) {
							System.out.println("Sequence : " + j + " ---------------");
							processedOnes.add(i);

						}
					}
				}

				else if (firstSeqs[i].equals(sequences[couples[1]])) {
					seq2 = true;
					sq2 = i;
					processedOnes.remove(0);
					processedOnes.remove(0);

					for (int j = 0; j < sequences.length; j++) {
						if (firstSeqs[i].equals(sequences[j])) {
							System.out.println("Sequence : " + j + " ---------------");
							processedOnes.add(i);

						}
					}

				}

			}

			int nop = 2;
			returnProfileType rtp = null;

			if (seq1) {

				rtp = al.profile2sequenceAlignment(nop, rcp.profile, rcp.letters, firstSeqs[sq1]);
				nop++;
			}

			if (seq2) {

				rtp = al.profile2sequenceAlignment(nop, rcp.profile, rcp.letters, firstSeqs[sq2]);
				nop++;
			}

			String[] newSeqs = new String[firstSeqs.length - 1];

			q = 0;
			for (int i = 0; i < firstSeqs.length; i++) {// deciding which one is not alligned

				if (!processedOnes.contains(i)) {

					newSeqs[q] = firstSeqs[i];
					q++;
				}

			}

			similarityMatrix = al.profileSequenceSimilarityMatrix(rtp.getProfile(), newSeqs, 3, rtp.getLetters());
			couples = al.decideCouples(similarityMatrix);// deciding next 2
			processedOnes.clear();
			processedOnes.add(couples[0]);
			processedOnes.add(couples[1]);

			seq1 = false;
			sq1 = 0;
			seq2 = false;
			sq2 = 0;

			for (int i = 0; i < sequences.length; i++) {// deciding which one is sequence

				if (newSeqs[0].equals(sequences[i])) {
					seq1 = true;
					sq1 = i;
					processedOnes.remove(0);
					System.out.println("Sequence : " + i + " --------------------");

				}

				else if (newSeqs[0].equals(sequences[i])) {
					seq2 = true;
					sq2 = i;
					processedOnes.remove(0);
					System.out.println("Sequence : " + i + " --------------------");

				}

			}

			if (seq1) {

				rtp = al.profile2sequenceAlignment(3, rtp.getProfile(), rtp.getLetters(), newSeqs[0]);
				nop++;
			}

			else if (seq2) {

				rtp = al.profile2sequenceAlignment(3, rtp.getProfile(), rtp.getLetters(), newSeqs[0]);
				nop++;
			}
			System.out.println("Completed");
		}

		else if (UserManager.numberOfSequences == 5) {

			similarityMatrix = al.generateSimilarityMatrix(sequences);
			System.out.println("Guide Tree : ");
			int[] couples = al.decideCouples(similarityMatrix);
			processedOnes.add(couples[0]);
			processedOnes.add(couples[1]);
			System.out.println("Sequence : " + couples[0] + " -----");
			System.out.println("Sequence : " + couples[1] + " ----------");
			returnCreateProfile rcp;

			rcp = al.createProfile(sequences[couples[0]], sequences[couples[1]]);// first two are alligned

			String[] firstSeqs = new String[sequences.length - 2];

			int q = 0;
			for (int i = 0; i < sequences.length; i++) {// finding who are not alligned

				if (!processedOnes.contains(i)) {

					firstSeqs[q] = sequences[i];
					q++;
				}

			}

			similarityMatrix = al.profileSequenceSimilarityMatrix(rcp.profile, firstSeqs, 2, rcp.letters);
			couples = al.decideCouples(similarityMatrix);// deciding next 2
			processedOnes.clear();
			processedOnes.add(couples[0]);
			processedOnes.add(couples[1]);

			boolean seq1 = false;
			int sq1 = 0;
			boolean seq2 = false;
			int sq2 = 0;

			for (int i = 0; i < firstSeqs.length; i++) {// deciding which one is sequence

				if (firstSeqs[i].equals(sequences[couples[0]+1])) {
					seq1 = true;
					sq1 = i;
					processedOnes.remove(0);
					processedOnes.remove(0);

					for (int j = 0; j < sequences.length; j++) {
						if (firstSeqs[i].equals(sequences[j])) {
							System.out.println("Sequence : " + j + " ---------------");
							processedOnes.add(i);

						}
					}
					
					break;
				}

				else if (firstSeqs[i].equals(sequences[couples[1]+1])) {
					seq2 = true;
					sq2 = i;
					processedOnes.remove(0);
					processedOnes.remove(0);

					for (int j = 0; j < sequences.length; j++) {
						if (firstSeqs[i].equals(sequences[j])) {
							System.out.println("Sequence : " + j + " ---------------");
							processedOnes.add(i);

						}
					}
					
					break;

				}

			}

			int nop = 2;
			returnProfileType rtp = null;

			if (seq1) {

				rtp = al.profile2sequenceAlignment(nop, rcp.profile, rcp.letters, firstSeqs[sq1]);
				nop++;
			}

			if (seq2) {

				rtp = al.profile2sequenceAlignment(nop, rcp.profile, rcp.letters, firstSeqs[sq2]);
				nop++;
			}

			String[] newSeqs = new String[firstSeqs.length - 1];

			q = 0;
			for (int i = 0; i < firstSeqs.length; i++) {// deciding which one is not alligned

				if (!processedOnes.contains(i)) {

					newSeqs[q] = firstSeqs[i];
					q++;
				}

			}

			similarityMatrix = al.profileSequenceSimilarityMatrix(rtp.getProfile(), newSeqs, 3, rtp.getLetters());
			couples = al.decideCouples(similarityMatrix);// deciding next 2
			processedOnes.clear();
			processedOnes.add(couples[0]);
			processedOnes.add(couples[1]);

			seq1 = false;
			sq1 = 0;
			seq2 = false;
			sq2 = 0;

			for (int i = 0; i < sequences.length; i++) {// deciding which one is sequence

				for (int j = 0; j < newSeqs.length; j++) {

					if (newSeqs[j].equals(sequences[i])) {
						seq1 = true;
						sq1 = j;
						processedOnes.remove(0);
						System.out.println("Sequence : " + i + " --------------------");

					}

					else if (newSeqs[j].equals(sequences[i])) {
						seq2 = true;
						sq2 = j;
						processedOnes.remove(0);
						System.out.println("Sequence : " + i + " --------------------");

					}

				}
				
				if(seq1||seq2) {
					break;
					
				}

			}

			if (seq1) {

				rtp = al.profile2sequenceAlignment(3, rtp.getProfile(), rtp.getLetters(), newSeqs[sq1]);
				nop++;
			}

			else if (seq2) {

				rtp = al.profile2sequenceAlignment(3, rtp.getProfile(), rtp.getLetters(), newSeqs[sq2]);
				nop++;
			}
			
			int rest;
			if(sq1+1 == 2) {
				rest = 0;
				
			}
			else {
				
				rest = 1;
			}
			
			
			
			for (int i = 0; i < sequences.length; i++) {
					if(newSeqs[rest].equals(sequences[i])) {
						System.out.println("Sequence : " + i + " -------------------------");
					
				}
			}
			
			System.out.println("Completed");

		}

	}
}
