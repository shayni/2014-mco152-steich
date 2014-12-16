package teich.scrabble;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class ScrabbleDictionary {

	private Set<String> dictionary;

	public ScrabbleDictionary() throws FileNotFoundException {
		dictionary = new HashSet<String>();
		Scanner words = new Scanner(new File("./OWL.txt"));
		String temp;

		while (words.hasNextLine()) {
			temp = words.nextLine();
			StringTokenizer token = new StringTokenizer(temp, " ");
			dictionary.add(token.nextToken());
		}
	}

	public boolean contains(String word) {
		if (word == null) {
			return false;
		}
		String upperCase = word.toUpperCase();
		return dictionary.contains(upperCase);
	}

	public static void main(String[] args) {
		ScrabbleDictionary scrabble;
		try {
			scrabble = new ScrabbleDictionary();
			long startTime = System.currentTimeMillis();
			for (int i = 0; i < 1000000; i++) {
				scrabble.contains("word");
			}
			long endTime = System.currentTimeMillis();
			System.out.println(endTime - startTime);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
