/*
 * File: Wordle.java
 * -----------------
 * This module is the starter file for the Wordle assignment.
 */

import java.awt.*;
import java.util.Locale;

public class Wordle {

    private String correctWord;
    private int row;

    public void run() {
        gw = new WordleGWindow();
        row = 0;
        int correctWordIndex = (int) (Math.random() * WordleDictionary.FIVE_LETTER_WORDS.length);
        correctWord = (WordleDictionary.FIVE_LETTER_WORDS[correctWordIndex]).toUpperCase();
        gw.addEnterListener((s) -> enterAction(s));
    }

/*
 * Called when the user hits the RETURN key or clicks the ENTER button,
 * passing in the string of characters on the current row.
 */

    public void enterAction(String s) {
        boolean wordValid = false;
        for (int x = 0; x < WordleDictionary.FIVE_LETTER_WORDS.length; x++) {
            if (s.equalsIgnoreCase(WordleDictionary.FIVE_LETTER_WORDS[x])) {
                wordValid = true;
            }
        }
        if (!wordValid) {
            gw.showMessage("Not in word list");
        }
        else {
            for (int x = 0; x < s.length(); x++) {
                if (s.substring(x, x + 1).equals(correctWord.substring(x, x + 1))) { // If letter is in the correct position
                    gw.setSquareColor(row, x, WordleGWindow.CORRECT_COLOR);
                        gw.setKeyColor(s.substring(x, x + 1), WordleGWindow.CORRECT_COLOR);
                }
                else {
                    boolean letterCorrect = false;
                    for (int y = 0; y < correctWord.length(); y++) { // Check each letter of the word to see if the inputed letter exists in the word
                        if (s.substring(x, x + 1).equals(correctWord.substring(y, y + 1))) {
                            letterCorrect = true;
                        }
                    }
                    if (letterCorrect) { // If the letter is present in the word, but not in the correct position
                        gw.setSquareColor(row, x, WordleGWindow.PRESENT_COLOR);
                        if (gw.getKeyColor(s.substring(x, x + 1)) != WordleGWindow.CORRECT_COLOR) {
                            gw.setKeyColor(s.substring(x, x + 1), WordleGWindow.PRESENT_COLOR);
                        }
                    }
                    else { // If the letter is NOT in the word
                        gw.setSquareColor(row, x, WordleGWindow.MISSING_COLOR);
                        gw.setKeyColor(s.substring(x, x + 1), WordleGWindow.MISSING_COLOR);
                    }
                }
            }
        }
        if (wordValid) {
            if (s.equals(correctWord)) {
                gw.showMessage("You guessed the word!");
            }
            else {
                row++;
                gw.setCurrentRow(row);
            }
        }
    }

/* Startup code */

    public static void main(String[] args) {
        new Wordle().run();
    }

/* Private instance variables */

    private WordleGWindow gw;

}
