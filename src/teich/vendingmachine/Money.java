package teich.vendingmachine;

public class Money {

	private int numDollars;
	private int numQuarters;
	private int numDimes;
	private int numNickles;

	public Money() {

	}

	public Money(int numDollars, int numQuarters, int numDimes, int numNickles) {
		this.numDollars = numDollars;
		this.numQuarters = numQuarters;
		this.numDimes = numDimes;
		this.numNickles = numNickles;
	}

	public void add(Money money) {
		numDollars += money.getNumDollars();
		numQuarters += money.getNumQuarters();
		numDimes += money.getNumDimes();
		numNickles += money.getNumNickles();
	}

	public Money remove(double amount) throws NotEnoughChangeException {
		int qntyDollars = 0;
		int qntyQuarters = 0;
		int qntyDimes = 0;
		int qntyNickles = 0;
		while (amount > 0) {
			if (amount >= 1.00) {
				qntyDollars++;
				amount -= 1.00;
			} else if (amount >= .25) {
				qntyQuarters++;
				amount -= .25;
			} else if (amount >= .10) {
				qntyDimes++;
				amount -= .25;
			} else if (amount >= .05) {
				qntyNickles++;
				amount -= .05;
			}
		}
		if ((qntyDollars >= 1 && numDollars == 0) || (qntyQuarters >= 1 && numQuarters == 0)
				|| (qntyDimes >= 1 && numDimes == 0) || (qntyNickles >= 1 && numNickles == 0)) {
			throw new NotEnoughChangeException();
		} else {
			numDollars -= qntyDollars;
			numQuarters -= qntyQuarters;
			numDimes -= qntyDimes;
			numNickles -= qntyNickles;
			return new Money(numDollars, numQuarters, numDimes, numNickles);
		}
	}

	public double getTotal() {
		return numDollars + numQuarters * .25 + numDimes * .10 + numNickles * .05;
	}

	public int getNumDollars() {
		return numDollars;
	}

	public void setNumDollars(int numDollars) {
		this.numDollars = numDollars;
	}

	public int getNumQuarters() {
		return numQuarters;
	}

	public void setNumQuarters(int numQuarters) {
		this.numQuarters = numQuarters;
	}

	public int getNumNickles() {
		return numNickles;
	}

	public void setNumNickles(int numNickles) {
		this.numNickles = numNickles;
	}

	public int getNumDimes() {
		return numDimes;
	}

	public void setNumDimes(int numDimes) {
		this.numDimes = numDimes;
	}

}
