import java.lang.Math;
import java.util.ArrayList;

public class Alignment {

	UserManager um = new UserManager();

	BLOSUM62Matrix bm62 = new BLOSUM62Matrix();

	int gapPenalty = Integer.MIN_VALUE;

	public Alignment() {
		// TODO Auto-generated constructor stub

	}

	public void getGapPenalty() {

		while (gapPenalty == Integer.MIN_VALUE) {

			gapPenalty = UserManager.getUserPenalty();
		}

	}

	public double calculateSimilarity(String s, String t) { // assume that their length are equal

		double matchNumber = 0;
		double count = 0;

		for (int i = 0; i < s.length(); i++) {

			count++;

			if (s.substring(i, i + 1).equals(t.substring(i, i + 1))) {

				matchNumber++;

			}

		}

		return (matchNumber / count);

	}

	public double calculateProfile2SeqSimilarity(double[][] profile, String sequence, String profileLetters) {

		double similarity = 0.0;
		double count = 0.0;

		for (int i = 0; i < sequence.length(); i++) {
			for (int j = 0; j < profile.length; j++) {

				if (sequence.substring(i, i + 1).equals(profileLetters.substring(j, j + 1))) {

					similarity += profile[j][i];

				}

			}

			count += 1;

		}

		return similarity / count;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public returnProfileType profile2sequenceAlignment(int numberOfProfs, double[][] profile1, String p1Letters,
			String sequence) {

		DPTable dptable = new DPTable(sequence.length(), profile1[1].length);

		DPNode initialNode = new DPNode(0.0, null);// DPNode[value , from[]];

		dptable.setTableRC(0, 0, initialNode);

		for (int i = 0; i < sequence.length() + 1; i++) {
			for (int j = 0; j < profile1[1].length + 1; j++) {

				if (i == 0 && j == 0) {
				}

				else {

					if (i != 0 && j == 0) {// first column

						DPNode newNode = new DPNode((double) dptable.getDpNode(i - 1, j).getValue() + gapPenalty,
								DPNode.From.UP);
						dptable.setTableRC(i, j, newNode);

					}

					else if (j != 0 && i == 0) {// first row

						DPNode newNode = new DPNode((double) dptable.getDpNode(i, j - 1).getValue() + gapPenalty,
								DPNode.From.LEFT);
						dptable.setTableRC(i, j, newNode);

					}

					else {// somewhere in the middle

						double totalDiagonalScore = 0;

						for (int k = 0; k < profile1.length; k++) {

							totalDiagonalScore += (bm62.getMMScore(sequence.substring(i - 1, i),
									p1Letters.substring(k, k + 1)) * profile1[k][j - 1]);

						}
						totalDiagonalScore = (double) dptable.getDpNode(i - 1, j - 1).getValue() + totalDiagonalScore;
						double leftScore = (double) dptable.getDpNode(i, j - 1).getValue() + gapPenalty;
						double upScore = (double) dptable.getDpNode(i - 1, j).getValue() + gapPenalty;

						double max = Math.max(totalDiagonalScore, leftScore);
						max = Math.max(max, upScore);

						if (max == totalDiagonalScore) {
							@SuppressWarnings("unchecked")
							DPNode newNode = new DPNode(totalDiagonalScore, DPNode.From.DIAGONAL);
							dptable.setTableRC(i, j, newNode);

							if (max == leftScore) {

								newNode.addFrom(DPNode.From.LEFT);
							}

							if (max == upScore) {

								newNode.addFrom(DPNode.From.UP);
							}

						}

						else if (max == leftScore) {

							DPNode newNode = new DPNode(leftScore, DPNode.From.LEFT);
							dptable.setTableRC(i, j, newNode);

							if (max == totalDiagonalScore) {

								newNode.addFrom(DPNode.From.DIAGONAL);
							}

							if (max == upScore) {

								newNode.addFrom(DPNode.From.UP);
							}

						}

						else if (max == upScore) {

							DPNode newNode = new DPNode(upScore, DPNode.From.UP);
							dptable.setTableRC(i, j, newNode);

							if (max == totalDiagonalScore) {

								newNode.addFrom(DPNode.From.DIAGONAL);
							}

							if (max == leftScore) {

								newNode.addFrom(DPNode.From.LEFT);
							}

						}

					}
				}

			}

		}

		// BACKTRACKING

		DPNode lastNode = dptable.getDpNode(sequence.length(), profile1[1].length);

		String sequence1 = "";
		String sequence2 = "";

		int len1 = sequence.length();
		int len2 = profile1[1].length;

		while (!lastNode.equals(initialNode)) {

			if (lastNode.getFrom().get(0).equals(DPNode.From.DIAGONAL)) {

				sequence1 = sequence.substring(len1 - 1, len1) + sequence1;
				sequence2 = len2 + sequence2;
				len1--;
				len2--;
				lastNode = dptable.getDpNode(len1, len2);

			} else if (lastNode.getFrom().get(0).equals(DPNode.From.UP)) {

				sequence2 = "*" + sequence2;
				sequence1 = sequence.substring(len1 - 1, len1) + sequence1;
				len1--;
				lastNode = dptable.getDpNode(len1, len2);

			}

			else if (lastNode.getFrom().get(0).equals(DPNode.From.LEFT)) {

				sequence1 = "*" + sequence1;
				sequence2 = len2 + sequence2;
				len2--;
				lastNode = dptable.getDpNode(len1, len2);

			}

		}

		// adding new letters to profile.

		String newletter = "";

		boolean flag = false;

		for (int i = 0; i < sequence1.length(); i++) {

			for (int j = 0; j < p1Letters.length(); j++) {

				if (sequence1.substring(i, i + 1).equals(p1Letters.substring(j, j + 1))) {

					flag = true;

				}

			}

			if (!flag) {

				newletter = newletter + sequence1.substring(i, i + 1);

			}

			flag = false;

		}

		p1Letters = p1Letters + newletter;

		double[][] newprofile = new double[p1Letters.length()][sequence2.length()];
		ArrayList<Integer> starPositions = new ArrayList<Integer>();
		// int seq2index = 0;
		for (int i = 0; i < profile1[1].length; i++) {
			if (sequence2.substring(i, i + 1).equals("*")) {

				starPositions.add(i);
			}
			for (int j = 0; j < profile1.length; j++) {

				if (sequence2.substring(i, i + 1).equals("*") && (i != profile1[1].length)) {

					newprofile[j][i + 1] = profile1[j][i] * ((double) numberOfProfs / ((double) numberOfProfs + 1));

				}

				else {

					newprofile[j][i] = profile1[j][i] * ((double) numberOfProfs / ((double) numberOfProfs + 1));
				}

			}

		}

		for (int i = 0; i < sequence1.length(); i++) {
			for (int j = 0; j < p1Letters.length(); j++) {

				if (sequence1.substring(i, i + 1).equals(p1Letters.substring(j, j + 1))) {

					newprofile[j][i] += (double) 1 / (numberOfProfs + 1);

				}

			}

		}

		for (int i = 0; i < newprofile.length; i++) {

			if (i < sequence2.length()) {

				if (sequence2.substring(i, i + 1).equals("*")) {

					for (int j = 0; j < starPositions.size(); j++) {

						newprofile[i][starPositions.get(j)] = (double) numberOfProfs / (numberOfProfs + 1);

					}

				}

			}

		}

		returnProfileType rpt = new returnProfileType(numberOfProfs + 1, newprofile, p1Letters, sequence1, sequence2);

		return rpt;

	}

	@SuppressWarnings("unchecked")
	public String[] globalAlign(String s, String t) {

		DPTable dptable = new DPTable(s.length(), t.length());

		DPNode initialNode = new DPNode(0, null);// DPNode[value , from[]];

		dptable.setTableRC(0, 0, initialNode);

		for (int i = 0; i < s.length() + 1; i++) {
			for (int j = 0; j < t.length() + 1; j++) {
				if (i == 0 && j == 0) {
				}

				else {

					if (i != 0 && j == 0) {// first column

						DPNode newNode = new DPNode((int) dptable.getDpNode(i - 1, j).getValue() + gapPenalty,
								DPNode.From.UP);
						dptable.setTableRC(i, j, newNode);

					}

					else if (j != 0 && i == 0) {// first row

						DPNode newNode = new DPNode((int) dptable.getDpNode(i, j - 1).getValue() + gapPenalty,
								DPNode.From.LEFT);
						dptable.setTableRC(i, j, newNode);

					}

					else {// somewhere in the middle

						int diagonalScore = bm62.getMMScore(s.substring(i - 1, i), t.substring(j - 1, j));
						diagonalScore = (int) dptable.getDpNode(i - 1, j - 1).getValue() + diagonalScore;
						int leftScore = (int) dptable.getDpNode(i, j - 1).getValue() + gapPenalty;
						int upScore = (int) dptable.getDpNode(i - 1, j).getValue() + gapPenalty;

						int max = Math.max(diagonalScore, leftScore);
						max = Math.max(max, upScore);

						if (max == diagonalScore) {
							DPNode newNode = new DPNode(diagonalScore, DPNode.From.DIAGONAL);
							dptable.setTableRC(i, j, newNode);

							if (max == leftScore) {

								newNode.addFrom(DPNode.From.LEFT);
							}

							if (max == upScore) {

								newNode.addFrom(DPNode.From.UP);
							}

						}

						else if (max == leftScore) {

							DPNode newNode = new DPNode(leftScore, DPNode.From.LEFT);
							dptable.setTableRC(i, j, newNode);

							if (max == diagonalScore) {

								newNode.addFrom(DPNode.From.DIAGONAL);
							}

							if (max == upScore) {

								newNode.addFrom(DPNode.From.UP);
							}

						}

						else if (max == upScore) {

							DPNode newNode = new DPNode(upScore, DPNode.From.UP);
							dptable.setTableRC(i, j, newNode);

							if (max == diagonalScore) {

								newNode.addFrom(DPNode.From.DIAGONAL);
							}

							if (max == leftScore) {

								newNode.addFrom(DPNode.From.LEFT);
							}

						}

					}

				}
			}
		} // FOR SONU BURASI

		// BACKTRACKING

		DPNode lastNode = dptable.getDpNode(s.length(), t.length());

		String sequence1 = "";
		String sequence2 = "";

		int len1 = s.length();
		int len2 = t.length();

		while (!lastNode.equals(initialNode)) {

			if (lastNode.getFrom().get(0).equals(DPNode.From.DIAGONAL)) {

				sequence1 = s.substring(len1 - 1, len1) + sequence1;
				sequence2 = t.substring(len2 - 1, len2) + sequence2;
				len1--;
				len2--;
				lastNode = dptable.getDpNode(len1, len2);

			} else if (lastNode.getFrom().get(0).equals(DPNode.From.UP)) {

				sequence2 = "-" + sequence2;
				sequence1 = s.substring(len1 - 1, len1) + sequence1;
				len1--;
				lastNode = dptable.getDpNode(len1, len2);

			}

			else if (lastNode.getFrom().get(0).equals(DPNode.From.LEFT)) {

				sequence1 = "-" + sequence1;
				sequence2 = t.substring(len2 - 1, len2) + sequence2;
				len2--;
				lastNode = dptable.getDpNode(len1, len2);

			}

		}

		String[] retVal = new String[2];

		retVal[0] = sequence1;
		retVal[1] = sequence2;

		return retVal;

	}

	public returnCreateProfile createProfile(String s, String t) {

		String[] retVals = globalAlign(s, t);

		String firstRet = retVals[0];
		String secondRet = retVals[1];
		
		String p1letters = "";

		for (int i = 0; i < firstRet.length(); i++) {
			if (firstRet.substring(i, i + 1).equals(secondRet.substring(i, i + 1))) {

				if (!p1letters.contains(firstRet.substring(i, i + 1))) {

					p1letters += firstRet.substring(i, i + 1);

				}

			}

			else {
				if (!p1letters.contains(firstRet.substring(i, i + 1))) {

					p1letters += firstRet.substring(i, i + 1);

				}
				if (!p1letters.contains(secondRet.substring(i, i + 1))) {

					p1letters += secondRet.substring(i, i + 1);

				}

			}
			

		}

		

		if (firstRet.contains("*") || secondRet.contains("*")) {

			p1letters += "*";

		}

		
		
		double[][] newprofile = new double[p1letters.length()][retVals[0].length()];

		for (int i = 0; i < newprofile.length; i++) {
			for (int j = 0; j < retVals[0].length(); j++) {

				if (firstRet.substring(j, j + 1).equals(p1letters.substring(i, i + 1))) {

					newprofile[i][j] += (double) 1 / 2;

				}

				if (secondRet.substring(j, j + 1).equals(p1letters.substring(i, i + 1))) {

					newprofile[i][j] += (double) 1 / 2;

				}

			}
		}

		returnCreateProfile rcp = new returnCreateProfile(p1letters, newprofile);

		return rcp;
	}

	public double[][] profileSequenceSimilarityMatrix(double[][] profile, String[] sequences, int numberOfProfs,
			String profileLetters) {

		double[][] similarityMatrix = new double[sequences.length][sequences.length + 1];
		double similarity = 0.0;
		int nop = numberOfProfs;
		for (int i = 0; i < similarityMatrix.length; i++) {
			for (int j = 0; j < similarityMatrix[0].length; j++) {
				if (j > i) {

					if (i == 0) {

						returnProfileType rpt = profile2sequenceAlignment(nop, profile, profileLetters,
								sequences[j - 1]);
						similarity = calculateProfile2SeqSimilarity(rpt.getProfile(), rpt.getSequence1(),
								rpt.getLetters());
						nop++;

						similarityMatrix[i][j] = similarity;

					}

					else {

						String[] temparr = new String[2];
						temparr = globalAlign(sequences[i - 1], sequences[j - 1]);
						similarity = calculateSimilarity(temparr[0], temparr[1]);
						similarityMatrix[i][j] = similarity;

					}
				}
			}
		}

		return similarityMatrix;

	}

	public double[][] generateSimilarityMatrix(String[] sequences) {

		double[][] similarityMatrix = new double[sequences.length][sequences.length];

		for (int i = 0; i < sequences.length - 1; i++) {

			for (int j = 0; j < sequences.length; j++) {

				if (j > i) {

					System.out.print("Similarity between Sequence " + (i + 1) + " and Sequence " + (j + 1) + " : ");
					String[] temparr = new String[2];
					temparr = globalAlign(sequences[i], sequences[j]);
					double similarity = calculateSimilarity(temparr[0], temparr[1]);
					similarityMatrix[i][j] = similarity;
					System.out.println(similarity);
				}
			}
		}

		return similarityMatrix;
	}

	public int[] decideCouples(double[][] similarity_matrix) {

		int x = 0, y = 0;
		double max = 0.0;

		for (int i = 0; i < similarity_matrix.length - 1; i++) {
			for (int j = 0; j < similarity_matrix[0].length; j++) {

				if (j > i) {

					if (similarity_matrix[i][j] > max) {

						max = similarity_matrix[i][j];
						y = i;
						x = j;
					}
				}

			}
		}

		int[] retVal = { y, x };

		return retVal;
	}

	public void drawGuideTree(double[][] similarity_matrix, String[] sequences) {

		int x = 0, y = 0;
		double max = 0.0;

		for (int i = 0; i < similarity_matrix.length - 1; i++) {
			for (int j = 0; j < similarity_matrix[0].length; j++) {

				if (j > i) {

					if (similarity_matrix[i][j] > max) {

						max = similarity_matrix[i][j];
						y = i;
						x = j;
					}
				}

			}
		}
		System.out.println("Guide Tree :");
		System.out.println(y + "-----");
		System.out.println(x + "-----");

	}

}
