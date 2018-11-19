package com.thoughtworks.homework;

import java.util.HashSet;
import java.util.Set;

/**
 * @program: guide-to-the-galaxy
 * @description: RomanValidator
 * @author: smallsoup
 * @create: 2018-11-15 23:37
 **/

public class RomanValidator {

    private RomanValidator() {

    }

    /**
     * The symbols "I", "X", "C", and "M" can be repeated three times in succession, but no more
     */
    private static final Set<Character> THREE_TIMES_REPEATED_CHARACTERS = new HashSet<>();

    /**
     * "V", "L", and "D" can never be subtracted.
     */
    private static final Set<Character> NOT_SUBTRACTED_CHARACTERS = new HashSet<>();

    static {
        THREE_TIMES_REPEATED_CHARACTERS.add('I');
        THREE_TIMES_REPEATED_CHARACTERS.add('X');
        THREE_TIMES_REPEATED_CHARACTERS.add('C');
        THREE_TIMES_REPEATED_CHARACTERS.add('M');

        NOT_SUBTRACTED_CHARACTERS.add('V');
        NOT_SUBTRACTED_CHARACTERS.add('L');
        NOT_SUBTRACTED_CHARACTERS.add('D');
        NOT_SUBTRACTED_CHARACTERS.add('M');
    }

    /**
     * Validate the input RomanNumber and return it's integer value if valid, else return -1
     */
    public static int validateRoman(String romanNumber) throws Exception {
        char[] charArray = romanNumber.toCharArray();
        char previousChar = ' ';

        int characterRepeatCount = 1;
        int total = 0;
        int previousCharacterOrdinal = Integer.MAX_VALUE;
        int currentCharacterOrdinal;

        for (int i = 0; i < charArray.length; i++) {
            char currentChar = charArray[i];
            int currentRomanCharNumericValue = RomanNumbers.valueOf(String.valueOf(currentChar)).getValue();

            if (previousChar != ' ') {
                previousCharacterOrdinal = RomanNumbers.valueOf(String.valueOf(previousChar)).ordinal();
            }
            currentCharacterOrdinal = RomanNumbers.valueOf(String.valueOf(currentChar)).ordinal();

            //They may appear four times if the third and fourth are separated by a smaller value, such as XXXIX
            if (currentChar == previousChar && ++characterRepeatCount < 4 && THREE_TIMES_REPEATED_CHARACTERS.contains(currentChar)) {
                total += currentRomanCharNumericValue;
            } else if (characterRepeatCount > 3) {
                total = -1;
            } else if (currentChar == previousChar && !THREE_TIMES_REPEATED_CHARACTERS.contains(currentChar)) {
                total = -1;
            } else if (previousCharacterOrdinal < currentCharacterOrdinal && !NOT_SUBTRACTED_CHARACTERS.contains(previousChar)) {
                int previousRomanCharNumericValue = RomanNumbers.valueOf(String.valueOf(previousChar)).getValue();
                if (previousCharacterOrdinal + 2 >= currentCharacterOrdinal) {
                    total += currentRomanCharNumericValue - (2 * previousRomanCharNumericValue);
                    characterRepeatCount = 1;
                } else {
                    total = -1;
                }
            } else if (previousCharacterOrdinal < currentCharacterOrdinal && NOT_SUBTRACTED_CHARACTERS.contains(previousChar)) {
                total = -1;
            } else {
                characterRepeatCount = 1;
                total += currentRomanCharNumericValue;
            }
            previousChar = currentChar;
            if (total == -1) {
                break;
            }
        }
        return total;
    }
}
