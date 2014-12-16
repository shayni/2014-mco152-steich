package teich.vendingmachine;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class InventoryTest {

	private Inventory inventory = new Inventory();

	@Test
	public void testLoad() throws IOException {
		inventory.load("inventory.txt");
	}

	@Test
	public void testRemoveOne() {
		try {
			inventory.load("inventory.txt");
			inventory.removeOne("B02");
			int actualQnty = inventory.get("B02").getQuantity();
			Assert.assertEquals(2, actualQnty, 0);
		} catch (IOException e) {
			e.getStackTrace();
		}
	}

	@Test
	public void testIsEmpty() {
		try {
			inventory.load("inventory.txt");
			boolean empty = inventory.isEmpty("B02");
			Assert.assertFalse(empty);
			inventory.removeOne("B02");
			inventory.removeOne("B02");
			inventory.removeOne("B02");
			boolean isempty = inventory.isEmpty("B02");
			Assert.assertTrue(isempty);
		} catch (IOException e) {
			e.getStackTrace();
		}
	}
}
