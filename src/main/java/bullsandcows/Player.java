package bullsandcows;

import bullsandcows.Keyboard;

import java.util.List;

public class Player {

    private String code;
    private String playerGuess;
    List<String> guesses;

    // Obtain the user's secret code by reading user input and validating its
    // validity.
    public String generateCode() {
        code = Keyboard.readInput();
        if (!Utility.validateGuess(code)) {
            System.out.println("Invalid code. Please enter again:");
            return this.generateCode();
        }
        return code;
    }

    public List<String> getCodeFromFile() {

        String fileName = Keyboard.readInput();
        if (fileName.toUpperCase().equals("MERRY")) {
            guesses = FileHandler.readGuessFromFile("doc/MERRY.txt");
        } else if (fileName.toUpperCase().equals("CHRISTMAS")) {
            guesses = FileHandler.readGuessFromFile("doc/CHRISTMAS.txt");
        } else {
            System.out.println("Invalid input.Please enter again: ");
            getCodeFromFile();
        }
        return guesses;
    }

    // Obtain the user's guess code by reading user input and validating its
    // validity.
    public String makeGuess() {
        playerGuess = Keyboard.readInput();
        if (!Utility.validateGuess(playerGuess)) {
            System.out.println("Invalid code. Please enter again: ");
            return makeGuess();
        } else
            return playerGuess;
    }

    public String getCode() {
        return code;
    }

}
