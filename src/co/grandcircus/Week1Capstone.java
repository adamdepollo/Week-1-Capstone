package co.grandcircus;

import java.util.Scanner;

public class Week1Capstone {

	public static void main(String[] args) {
		// Declare variables and create scanner
		Scanner scnr = new Scanner(System.in);
		StringBuilder userEntry = new StringBuilder();
		StringBuilder[] entryArray;
		String cont = "Y";
		// Greet user
		System.out.println("Welcome to the Pig Latin Translator!");
		// Create a do while loop to iterate while the user says they want to continue
		do {
			System.out.println("Enter a sentence to translate:");
			userEntry.append(scnr.nextLine());
			// Call method to split the StringBuilder entered into an array of substrings
			entryArray = splitStringBuilderIntoSubstringArray(userEntry.append(' '));
			// Print out the translated Pig Latin using method getPigLatin
			System.out.println(getPigLatin(entryArray));
			// Reset the userEntry StringBuilder
			userEntry.replace(0, userEntry.length(), "");
			// Ask whether to continue
			System.out.println("Keep going? (y/n)");
			// Reset the cont value with the user's input
			cont = scnr.nextLine();
		} while (cont.equalsIgnoreCase("Y"));
		// Say goodbye and close the scanner
		System.out.println("Bye!");
		scnr.close();
	}

	// Create a method to split a StringBuilder into a StringBuilder[]
	public static StringBuilder[] splitStringBuilderIntoSubstringArray(StringBuilder userEntry) {
		// Create a new String[] from the StringBuilder entered split at punctuation and
		// spaces.
		String[] stringArray = userEntry.toString().split(
				" |((?<=,)|(?=,))|((?<=\\. )|(?=\\. ))|((?<=\\!)|(?=\\!))|((?<=\\?)|(?=\\?))|((?<=\\;)|(?=\\;))|((?<=\\:)|(?=\\:))");
		// create a counter to iterate through index locations in the for each loop
		int i = 0;
		// Create a StringBuilder[] with the same length as the new string[]
		StringBuilder[] newStringBuilderArray = new StringBuilder[stringArray.length];
		// Put the items in the string[] into the appropriate index location in the
		// StringBuilder[] (start with 0 and increase until the end of the array(
		for (String s : stringArray) {
			StringBuilder newStringBuilder = new StringBuilder(s);
			newStringBuilderArray[i] = newStringBuilder;
			i++;
		}
		// Return the new StringBuilder[]
		return newStringBuilderArray;
	}

	// Create a method to check for special characters for each item in
	// StringBuilder[]
	public static boolean checkSpecialChars(StringBuilder userEntry) {
		// Determine whether the item is punctuation (i.e., only 1 character)
		if (userEntry.length() > 1) {
			// If not, loop through each character in each item
			for (int x = 0; x < userEntry.length(); x++) {
				// Test for special chars using unicode value and return true if there are any
				if (((int) userEntry.charAt(x) > 0 && (int) userEntry.charAt(x) < 39)
						|| ((int) userEntry.charAt(x) > 39 && (int) userEntry.charAt(x) < 65)
						|| ((int) userEntry.charAt(x) > 90 && (int) userEntry.charAt(x) < 97)
						|| ((int) userEntry.charAt(x) > 122 && (int) userEntry.charAt(x) < 256)) {
					return true;
				}
			}
			return false;
		} else
			return false;
	}

	public static StringBuilder getPigLatin(StringBuilder[] entryArray) {
		StringBuilder newPigLatinPhrase = new StringBuilder("");
		// Loop through each item in the array
		for (StringBuilder s : entryArray) {
			// Determine whether the StringBuilder is null and, if so, just append to the
			// word
			if (s.toString().equals("")) {
				newPigLatinPhrase.append(s);
			}
			// Determine whether the StringBuilder is punctuation (using unicode value)
			else if (s.length() == 1 && (((int) s.charAt(0) >= 0 && (int) s.charAt(0) < 39)
					|| ((int) s.charAt(0) > 39 && (int) s.charAt(0) < 65)
					|| ((int) s.charAt(0) > 90 && (int) s.charAt(0) < 97)
					|| ((int) s.charAt(0) > 122 && (int) s.charAt(0) < 256))) {
				// If so, append the punctuation
				newPigLatinPhrase.append(s);
				// Remove the space before the punctuation
				newPigLatinPhrase.replace(newPigLatinPhrase.length() - 2, newPigLatinPhrase.length() - 1, "");
				// Add a space after the punctuation
				newPigLatinPhrase.append(" ");
			} else {
				// Check for whether the word includes special characters
				if (checkSpecialChars(s)) {
					// If so, just append the word to the translated phrase
					newPigLatinPhrase.append(s + " ");
				}
				// Determine whether the word starts with a vowel
				else if (checkVowel(s)) {
					// If so, call the method to edit a word starting with a vowel
					newPigLatinPhrase.append(editVowelWord(s) + " ");
				} else if (!checkVowel(s)) {
					// Call the method to edit a word starting with a consonant
					newPigLatinPhrase.append(editConsonantWord(s) + " ");
				}
			}
		}
		return newPigLatinPhrase;
	}

	// Create a method to determine whether a StringBuilder starts with a vowel

	public static boolean checkVowel(StringBuilder userEntry) {
		// Check whether the first char is a vowel
		if (userEntry.substring(0, 1).equalsIgnoreCase("a") || userEntry.substring(0, 1).equalsIgnoreCase("e")
				|| userEntry.substring(0, 1).equalsIgnoreCase("i") || userEntry.substring(0, 1).equalsIgnoreCase("o")
				|| userEntry.substring(0, 1).equalsIgnoreCase("u") || userEntry.substring(0, 1).equalsIgnoreCase("y")) {
			// If so, return true
			return true;
		}
		// Otherwise return false
		else
			return false;
	}

	// Create a method to edit StringBuilders that start with consonants
	public static StringBuilder editConsonantWord(StringBuilder userEntry) {
		// Check whether every letter in the StringBuilder is uppercase
		if (upperCaseCheck(userEntry)) {
			// If so, loop through each char in the StringBuilder
			for (int i = 0; i < userEntry.length(); i++) {
				// Find the first vowel
				if (userEntry.substring(i, i + 1).equalsIgnoreCase("a")
						|| userEntry.substring(i, i + 1).equalsIgnoreCase("e")
						|| userEntry.substring(i, i + 1).equalsIgnoreCase("i")
						|| userEntry.substring(i, i + 1).equalsIgnoreCase("o")
						|| userEntry.substring(i, i + 1).equalsIgnoreCase("u")
						|| userEntry.substring(i, i + 1).equalsIgnoreCase("y")) {
					// Append every consonant before the vowel + "AY" to the end of the word
					userEntry.append(userEntry.substring(0, i) + "AY");
					// And every letter up to the first vowel (i.e., replace the chars with the
					// vowel)
					userEntry.replace(0, i + 1, "" + userEntry.charAt(i));
					// Break the for loop
					break;
				}
			}
		} else {
			for (int i = 0; i < userEntry.length(); i++) {
				// If no, check for whether the first letter is uppercase using unicode value
				if (((int) userEntry.charAt(0) < 65) || ((int) userEntry.charAt(0) > 90)) {
					// Find the first vowel
					if (userEntry.charAt(i) == 'a' || userEntry.charAt(i) == 'e' || userEntry.charAt(i) == 'i'
							|| userEntry.charAt(i) == 'o' || userEntry.charAt(i) == 'u' || userEntry.charAt(i) == 'y') {
						// Append every consonant before the vowel + "AY" to the end of the word
						userEntry.append(userEntry.substring(0, i) + "ay");
						// And every letter up to the first vowel (i.e., replace the chars with the
						// vowel)
						userEntry.replace(0, i + 1, "" + userEntry.charAt(i));
						// Break the for loop
						break;
					}
				}
				// If the word does start with an uppercase, perform the same edits but change
				// the first char to lowercase before appending to the end of the word and
				// re-capitalize the new first character
				else {
					// Find the first vowel
					if (userEntry.charAt(i) == 'a' || userEntry.charAt(i) == 'e' || userEntry.charAt(i) == 'i'
							|| userEntry.charAt(i) == 'o' || userEntry.charAt(i) == 'u' || userEntry.charAt(i) == 'y') {
						// Append every consonant before the vowel + "AY" to the end of the word
						userEntry.append(userEntry.substring(0, i).toLowerCase() + "ay");
						// And every letter up to the first vowel (i.e., replace the chars with the
						// vowel)
						userEntry.replace(0, i + 1, "" + Character.toUpperCase(userEntry.charAt(i)));
						// Break the for loop
						break;
					}
				}
			}
		}
		// Return the edited StringBuilder
		return userEntry;
	}

	// Create a method to edit StringBuilders that begin with a vowel
	public static StringBuilder editVowelWord(StringBuilder userEntry) {
		// Create a copy of the entered StringBuilder to test case with
		// Check whether the entire original word entry was upper case
		if (upperCaseCheck(userEntry)) {
			// If so, append "WAY"
			userEntry.append("WAY");
		} else {
			// if not, append "way"
			userEntry.append("way");
		}
		return userEntry;
	}

	// Create a method to check whether an entire StringBuilder is in uppercase
	public static boolean upperCaseCheck(StringBuilder userEntry) {
		// Iterate through each char in the StringBuilder
		for (int i = 0; i < userEntry.length(); ++i) {
			// If any of them are lowercase (checked with unicode val), return false
			if (((int) userEntry.charAt(i) < 65) || ((int) userEntry.charAt(i) > 90)) {
				return false;
			}
		}
		// Otherwise return true
		return true;
	}
}
