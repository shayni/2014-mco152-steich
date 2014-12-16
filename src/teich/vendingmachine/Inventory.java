package teich.vendingmachine;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Inventory {

	private Map<String, Item> inventory;

	public Inventory() {
		inventory = new HashMap<String, Item>();
	}

	public void load(String inventoryFilename) throws IOException {
		Scanner file = new Scanner(new File("./" + inventoryFilename));

		while (file.hasNextLine()) {
			String line = file.nextLine();
			StringTokenizer t = new StringTokenizer(line, ",");
			String key = t.nextToken();
			String name = t.nextToken();
			double price = Double.valueOf(t.nextToken());
			int qnty = Integer.valueOf(t.nextToken());
			inventory.put(key, new Item(key, name, price, qnty));
		}
	}

	/**
	 * 
	 * @param code
	 * @return the item or null if an item with that code doesn't exist
	 */
	public Item get(String code) {
		if (inventory.containsKey(code)) {
			return inventory.get(code);
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @param item
	 *            to add
	 */
	public void add(Item item) {
		inventory.put(item.getCode(), item);
	}

	/**
	 * Removes one from quantity of the specified item
	 * 
	 * @param code
	 */
	public void removeOne(String code) {
		Item i = get(code);
		int qnty = i.getQuantity();
		i.setQuantity(qnty - 1);
	}

	/**
	 * 
	 * @param code
	 * @return true if the Item exists and there is at least one quantity,
	 *         otherwise false.
	 */
	public boolean isEmpty(String code) {
		return !(inventory.containsKey(code)) || inventory.get(code).getQuantity() < 1;
	}

	/**
	 * Lists the items in the inventory one per line in the format code name @
	 * price x quantity\n
	 */
	public String toString() {
		StringBuilder build = new StringBuilder();
		for (String key : inventory.keySet()) {
			build.append(key);
			build.append(" ");
			Item item = inventory.get(key);
			build.append(item.getName());
			build.append(" @ ");
			build.append(item.getPrice());
			build.append(" x ");
			build.append(item.getQuantity());
			build.append("\n");
		}
		return build.toString();
	}

}
