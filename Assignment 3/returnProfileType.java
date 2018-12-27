
	public class returnProfileType {

		private int numberOfProfs;
		private double[][] profile;
		private String letters;
		private String sequence1;
		private String sequence2;

		public int getNumberOfProfs() {
			return numberOfProfs;
		}

		public double[][] getProfile() {
			return profile;
		}

		public String getLetters() {
			return letters;
		}

		public void setNumberOfProfs(int numberOfProfs) {
			this.numberOfProfs = numberOfProfs;
		}

		public void setProfile(double[][] profile) {
			this.profile = profile;
		}

		public void setLetters(String letters) {
			this.letters = letters;
		}

		returnProfileType(int numberOfProfs, double[][] profile, String letters,String sequence1,String sequence2) {
			this.numberOfProfs = numberOfProfs;
			this.profile = profile;
			this.letters = letters;
			this.sequence1 = sequence1;
			this.sequence2 = sequence2;

		}

		public String getSequence1() {
			return sequence1;
		}

		public void setSequence1(String sequence1) {
			this.sequence1 = sequence1;
		}

		public String getSequence2() {
			return sequence2;
		}

		public void setSequence2(String sequence2) {
			this.sequence2 = sequence2;
		}

	}
