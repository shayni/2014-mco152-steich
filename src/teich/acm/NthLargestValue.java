package teich.acm;

import java.util.Scanner;

public class NthLargestValue {

	private int[] values;

	public NthLargestValue() {
		values = new int[10];
	}

	public int getNthLargestValue(int[] datasets) {
		values = datasets;
		boolean swapped;
		do {
			swapped = false;
			for (int i = 0; i < 9; i++) {
				if (values[i] > values[i + 1]) {
					int temp = values[i];
					values[i] = values[i + 1];
					values[i + 1] = temp;
					swapped = true;
				}
			}
		} while (swapped);
		return values[7];
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int[] values = new int[10];
		System.out.println("How many sets of values would you like to test?");
		int numValues = input.nextInt();
		NthLargestValue largest = new NthLargestValue();
		int numData = 0;
		System.out
				.println("Please enter the number of the data set and the set of values you would like to test separted by a space");
		while (numValues != numData) {
			numData = input.nextInt();
			for (int i = 0; i < 10; i++) {
				values[i] = input.nextInt();
			}
			System.out.println(numData + " " + largest.getNthLargestValue(values));
		}
	}
}
