
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {
		int ans = -1;
		if(word2.length()==0)
			ans = word1.length();
		else if(word1.length()==0)
			ans = word2.length();
		else if(word1.charAt(0) == word2.charAt(0))
			ans = levenshtein(tail(word1), tail(word2));
		else
			ans = 1 + Math.min(Math.min(levenshtein(tail(word1), word2),  levenshtein(word1, tail(word2))),  levenshtein(tail(word1), tail(word2)));
		return ans;
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);
		for(int i=0 ; i<dictionary.length ; i++)
			dictionary[i] = in.readLine();
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		word.toLowerCase();
		String newWord = word;
		int min = threshold + 1;
		for(int i=0 ; i<dictionary.length ; i++)
			if(levenshtein(word, dictionary[i])<min) {
				min = levenshtein(word, dictionary[i]);
				newWord = dictionary[i];
			}
		return newWord;
	}

}
