package teich.vendingmachine;

import org.junit.Assert;
import org.junit.Test;

public class MoneyTest {

	@Test
	public void testAdd() {
		Money money = new Money(10, 10, 10, 10);
		money.add(new Money(5, 5, 5, 5));
		Assert.assertEquals(15, money.getNumDollars());
		Assert.assertEquals(15, money.getNumQuarters());
		Assert.assertEquals(15, money.getNumDimes());
		Assert.assertEquals(15, money.getNumNickles());
	}

	@Test
	public void testRemoveThrowsNotEnoughChangeException() {// removes correct
															// change
		Money money = new Money(0, 0, 0, 0);
		try {
			money.remove(1.00);
			Assert.fail("NotEnoughChangeException should be thrown here");
		} catch (NotEnoughChangeException e) {
			// it should go here
		}
	}

	@Test
	public void testRemove() {
		Money money1 = new Money(10, 10, 10, 10);
		try {
			money1.remove(1.00);
			Assert.assertEquals(9, money1.getNumDollars());
			Assert.assertEquals(10, money1.getNumQuarters());
			Assert.assertEquals(10, money1.getNumDimes());
			Assert.assertEquals(10, money1.getNumNickles());
		} catch (NotEnoughChangeException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetTotal() {
		Money money = new Money(1, 1, 1, 1);
		Assert.assertEquals(1.40, money.getTotal(), .01);
	}
}
