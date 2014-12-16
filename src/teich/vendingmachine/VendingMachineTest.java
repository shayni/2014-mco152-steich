package teich.vendingmachine;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class VendingMachineTest {
	private Inventory inventory;

	public VendingMachineTest() {
		inventory = new Inventory();
		try {
			inventory.load("./inventory.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testPay() {

		Money bank = new Money(10, 10, 10, 10);
		VendingMachine vendingMachine = new VendingMachine(inventory, bank);
		Assert.assertEquals(2.15, vendingMachine.pay(new Money(1, 4, 1, 1)), 0);
	}

	@Test
	public void testBuy() throws CodeNotFoundException, NotEnoughPaidException,
			NotEnoughChangeException {
		// successful transaction(paid returns 0, correct change, reduce
		// quantity by 1)
		// successful transaction with a limited amount of change(ex: only
		// nickles)
		// Test CodeNotFoundException
		// test NotEnoughPaidException
		// test notEnoughChangeException
		// test notEnoughChangeException(bank only has $1 bills)

		Money bank = new Money(10, 10, 10, 10);
		VendingMachine machine = new VendingMachine(inventory, bank);

		machine.pay(new Money(1, 2, 1, 0));
		Money change = machine.buy("A01");
		Assert.assertEquals(0, machine.getPaid());
		Assert.assertEquals(.05, change.getTotal(), 0);
		Assert.assertEquals(4, machine.getInventory().get("A01").getQuantity());

	}

	@Test
	public void testBuy2() throws CodeNotFoundException,
			NotEnoughPaidException, NotEnoughChangeException {

		Money bank = new Money(0, 0, 0, 10);
		VendingMachine vending = new VendingMachine(inventory, bank);
		vending.pay(new Money(1, 2, 1, 0));

		Money change = vending.buy("A01");
		Assert.assertEquals(0, vending.getPaid());
		Assert.assertEquals(.05, change.getTotal(), 0);
		Assert.assertEquals(4, vending.getInventory().get("A01").getQuantity());

	}

	@Test
	public void testBuyThrowsCodeNotFoundException()
			throws NotEnoughPaidException, NotEnoughChangeException {

		Money bank = new Money(10, 10, 10, 10);
		VendingMachine vending = new VendingMachine(inventory, bank);
		try {
			vending.buy("A02");
			Assert.fail("CodeNotFoundException should be thrown here");
		} catch (CodeNotFoundException e) {

		}
	}

	@Test
	public void testBuyThrowsNotEnoughPaidException()
			throws CodeNotFoundException, NotEnoughChangeException {

		Money bank = new Money(10, 10, 10, 10);
		VendingMachine vending = new VendingMachine(inventory, bank);
		vending.pay(new Money(1, 0, 0, 0));
		try {
			vending.buy("A01");
			Assert.fail("NotEnoughPaidException should be thrown here");
		} catch (NotEnoughPaidException e) {

		}
	}

	@Test
	public void testBuyThrowsNotEnoughChangeException()
			throws CodeNotFoundException, NotEnoughPaidException {

		Money bank = new Money(0, 0, 0, 0);
		VendingMachine vending = new VendingMachine(inventory, bank);
		vending.pay(new Money(1, 2, 1, 0));
		try {
			vending.buy("A01");
			Assert.fail("NotEnoughChangeException should be thrown here");
		} catch (NotEnoughChangeException e) {

		}
	}
}
