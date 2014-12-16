package teich.acm2;

import java.util.Scanner;

public class RascalTriangle {

	public RascalTriangle() {

	}

	public int getValue(int row, int column) {
		return (row - column - 1) * (column - 1) + (row);
	}

	public static void main(String[] args) {
		RascalTriangle tri = new RascalTriangle();
		Scanner kybrd = new Scanner(System.in);
		System.out.println("How many values would you like to enter?");
		int numValues = kybrd.nextInt();
		System.out
				.println("Please enter the number of the data set, the row in the triangle and the index within the row.");
		int dataSet = 0;
		while (numValues != dataSet) {
			dataSet = kybrd.nextInt();
			int row = kybrd.nextInt();
			int column = kybrd.nextInt();
			System.out.println(dataSet + " " + tri.getValue(row, column));
		}
	}
}
