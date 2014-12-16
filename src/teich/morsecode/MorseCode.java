package teich.morsecode;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class MorseCode {

	private Map<String, String> toMorseMap;
	private Map<String, String> toPlainTextMap;

	public MorseCode() {
		String[] letters = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
				"S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", " " };
		String[] morse = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--",
				"-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", "-----",
				".----", "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.", "/" };

		toMorseMap = new HashMap<String, String>();
		toPlainTextMap = new HashMap<String, String>();

		for (int i = 0; i < letters.length; i++) {
			toMorseMap.put(letters[i], morse[i]);
			toPlainTextMap.put(morse[i], letters[i]);
		}
	}

	public String toMorseCode(String plain) {
		StringBuilder morse = new StringBuilder();
		String upper = plain.toUpperCase();

		for (int i = 0; i < upper.length(); i++) {
			String t = String.valueOf(upper.charAt(i));
			morse.append(toMorseMap.get(t));
			if (i != plain.length() - 1) {
				morse.append(" ");
			}
		}
		return morse.toString();
	}

	public String toPlainText(String msg) {
		StringBuilder b = new StringBuilder();

		StringTokenizer t = new StringTokenizer(msg);

		while (t.hasMoreElements()) {
			String token = t.nextToken();
			b.append(toPlainTextMap.get(token));
		}

		return b.toString();
	}
}
