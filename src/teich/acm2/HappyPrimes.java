package teich.acm2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HappyPrimes {

	private int numDatasets;
	private boolean happyPrime;
	private int[] datasets;

	public HappyPrimes(int numDatasets) {
		this.numDatasets = numDatasets;
		happyPrime = false;
		datasets = new int[numDatasets];
	}

	public boolean isHappyPrime(int num) {
		List<Integer> digits = new ArrayList<Integer>();
		List<Integer> sums = new ArrayList<Integer>();
		if (num == 1) {
			return happyPrime;
		}
		if (isPrime(num)) {
			int sum = 0;
			while (!happyPrime) {
				while (num > 0) {
					int digit = num % 10;
					digits.add(digit * digit);
					num /= 10;
				}
				for (Integer i : digits) {
					sum += i;
				}

				if (sums.contains(sum)) {
					happyPrime = false;
					return happyPrime;
				} else {
					sums.add(sum);
				}
				digits.removeAll(digits);
				if (sum == 1) {
					happyPrime = true;
				} else {
					happyPrime = false;
				}
				num = sum;
				sum = 0;
			}

		} else {
			happyPrime = false;
		}
		return happyPrime;
	}

	private boolean isPrime(int num) {
		boolean prime = true;
		for (int i = 2; i < Math.sqrt(num); i++) {
			if (num % i == 0) {
				prime = false;
				return prime;
			}
		}
		return prime;
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("How many values would you like to test?");
		int numValues = input.nextInt();
		HappyPrimes hp = new HappyPrimes(numValues);
		int numData = 0;
		System.out
				.println("Please enter the number of the data set and the value you would like to test separted by a space");
		while (numValues != numData) {
			numData = input.nextInt();
			int value = input.nextInt();
			System.out.println(numData + " " + value + " " + hp.isHappyPrime(value));
		}
	}
}
