package teich.acm2;

import java.util.Scanner;

public class MaxSum {

	private int max;

	public MaxSum(int max) {
		this.max = max;
	}

	public int getMaxSum(int[][] rect) {
		int maxSum = 0;
		for (int i = 0; i < max; i++) {
			for (int j = 0; j < rect[0].length; j++) {
				for (int k = i; k < max; k++) {
					for (int h = j; h < rect[0].length; h++) {
						int tempSum = 0;
						for (int s = i; s <= k; s++) {
							for (int t = j; t <= h; t++) {
								tempSum += rect[s][t];
							}
						}
						if (tempSum > maxSum) {
							maxSum = tempSum;
						}
					}
				}
			}
		}
		return maxSum;
	}

	public static void main(String[] args) {
		Scanner kybrd = new Scanner(System.in);
		System.out.println("What size would you like the square to be?");
		int size = kybrd.nextInt();
		MaxSum max = new MaxSum(size);
		int[][] square = new int[size][size];
		System.out.println("Please enter the values in the square separated by spaces");
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				square[i][j] = kybrd.nextInt();
			}
		}
		System.out.println(max.getMaxSum(square));
	}
}
