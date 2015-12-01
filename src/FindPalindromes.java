import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;


public class FindPalindromes {
	
	private Map<String, Integer> palindromes = new TreeMap<>(new Comparator<String>() {
		public int compare(String o1, String o2) {
			int difference = o2.length() - o1.length();
			return difference != 0 ? difference : o1.compareTo(o2);
		}
	});
	
	public static void main(String[] args) {
		if (args.length > 0) {
			String text = args[0];
			System.out.println("Input: " + text);
			new FindPalindromes().findPalindromes(text);
		} else {
			System.err.println("Please specify text to find palindromes in as a command line argument.");
		}
	}
	
	private void findPalindromes(String text) {
		for (int size = text.length(); size > 1; size--) {
			for (int start = 0; start < text.length() - (size - 1) && palindromes.size() < 3; start++) {
				int end = isAlreadyFound(start);
				if (end != 0) {
					start = end;
					continue;
				}
				StringBuilder chars = new StringBuilder(size);
				chars.append(text.substring(start, start + size));
				String sequence = chars.toString();
				if (!palindromes.containsKey(sequence) && sequence.equalsIgnoreCase(chars.reverse().toString())) {
					palindromes.put(sequence, start);
				}
			}
		}
		if (palindromes.size() < 1) {
			System.out.println("No palindromes found.");
			return;
		}
		for (Map.Entry<String, Integer> palindrome : palindromes.entrySet()) {
			System.out.printf("Text: %s, Index: %d, Length: %d%n", palindrome.getKey(), palindrome.getValue(), palindrome.getKey().length());
		}
	}
	
	private int isAlreadyFound(int start) {
		for (Map.Entry<String, Integer> palindrome : palindromes.entrySet()) {
			int foundStart = palindrome.getValue();
			int foundEnd = foundStart + palindrome.getKey().length();
			if (start > foundStart && start < foundEnd) {
				return foundEnd;
			}
		}
		return 0;
	}
	
}
