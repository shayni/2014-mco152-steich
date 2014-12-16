package teich.morsecode;

import org.junit.Assert;
import org.junit.Test;

public class MorseCodeTest {

	@Test
	public void testToMorseCode() {
		MorseCode code = new MorseCode();
		String morse = code.toMorseCode("abcdefghijklmnopqrstuvwxyz012345678 9");
		String expected = ".- -... -.-. -.. . ..-. --. .... .. .--- -.- .-.. -- -. --- .--. --.- .-. ... - ..- ...- .-- -..- -.-- --.. ----- .---- ..--- ...-- ....- ..... -.... --... ---.. / ----.";
		Assert.assertEquals(expected, morse);
	}

	@Test
	public void testToPlainText() {
		MorseCode code = new MorseCode();
		String plain = code
				.toPlainText(".- -... -.-. -.. . ..-. --. .... .. .--- -.- .-.. -- -. --- .--. --.- .-. ... - ..- ...- .-- -..- -.-- --.. ----- .---- ..--- ...-- ....- ..... -.... --... ---.. / ----.");
		String expected = "ABCDEFGHIJKLMNOPQRSTUVWXYZ012345678 9";
		Assert.assertEquals(expected, plain);
	}

}
