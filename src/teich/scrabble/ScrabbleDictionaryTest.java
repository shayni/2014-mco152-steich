package teich.scrabble;

import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Test;

public class ScrabbleDictionaryTest {

	@Test
	public void testContainsTrue() {
		ScrabbleDictionary dictionary;
		try {
			dictionary = new ScrabbleDictionary();
			Assert.assertTrue(dictionary.contains("HELLO"));
			Assert.assertTrue(dictionary.contains("Hello"));
		} catch (FileNotFoundException e) {
			e.getMessage();
		}
	}

	@Test
	public void testContainsFalse() {
		ScrabbleDictionary dictionary;
		try {
			dictionary = new ScrabbleDictionary();
			Assert.assertFalse(dictionary.contains("ASDFGY"));
			Assert.assertFalse(dictionary.contains("Qwert;y"));
		} catch (FileNotFoundException e) {
			e.getMessage();
		}
	}

	@Test
	public void testContainsNull() {
		ScrabbleDictionary dictionary;
		try {
			dictionary = new ScrabbleDictionary();
			Assert.assertFalse(dictionary.contains(null));
		} catch (FileNotFoundException e) {
			e.getMessage();
		}
	}
}
