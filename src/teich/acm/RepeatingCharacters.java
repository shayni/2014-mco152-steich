package teich.acm;

import java.util.Scanner;

public class RepeatingCharacters {

	public String getNewString(int numTimes, String s) {
		StringBuilder builder = new StringBuilder();

		for (int c = 0; c < s.length(); c++) {
			char letter = s.charAt(c);
			for (int i = 0; i < numTimes; i++) {
				builder.append(letter);
			}
		}
		return builder.toString();
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("How many sets of strings would you like to change?");
		int numValues = input.nextInt();
		RepeatingCharacters repeat = new RepeatingCharacters();
		int numData = 0;
		System.out
				.println("Please enter the number of the data set, how many times you would like the characters to repeat, and the string (up to 20 characters) you would like to change");
		while (numValues != numData) {
			numData = input.nextInt();
			int numRepeats = input.nextInt();
			String s = input.next();
			System.out.println(numData + " " + repeat.getNewString(numRepeats, s));
		}
	}
}
