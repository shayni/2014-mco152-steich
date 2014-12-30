package teich.acm;

import java.util.Scanner;

public class PenneyGame {

	private String[] sequence = { "TTT", "TTH", "THT", "THH", "HTT", "HTH", "HHT", "HHH" };
	private int[] numTimes;

	public PenneyGame() {
		numTimes = new int[8];
	}

	public int[] getNumSequences(String s) {
		numTimes = new int[8];
		for (int i = 0; i < s.length() - 2; i++) {
			String seq = s.substring(i, i + 3);
			for (int j = 0; j < 8; j++) {
				if (sequence[j].equalsIgnoreCase(seq)) {
					numTimes[j]++;
					break;
				}
			}
		}

		return numTimes;
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("How many sets of sequences would you like to count?");
		int numValues = input.nextInt();
		PenneyGame penney = new PenneyGame();
		int numData = 0;
		System.out.println("Please enter the number of the data set.");

		while (numValues != numData) {
			numData = input.nextInt();
			System.out.println("Enter the sequence you would like to count.");
			String sequence = input.next();
			int[] numSequences = penney.getNumSequences(sequence);
			System.out.print(numData);
			for (int k = 0; k < 8; k++) {
				System.out.print(" " + numSequences[k]);
			}
		}
	}
}
