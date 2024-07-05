package bullsandcows;

import java.util.ArrayList;
import java.util.List;

public class HardAIPlayer extends AIPlayer {
    private List<String> possibleGuesses = new ArrayList<>();
    private String aiGuess;

    public HardAIPlayer(Game game) {
        super(game, "HARD");
    }

    @Override
    public String makeGuess() {
        // System.out.println("list size is " + possibleGuesses.size()); //print list
        // size------------------------------------

        // 1st round: Initialize the possible guesses list
        // Pick random number for the first guess.
        if (possibleGuesses.isEmpty()) {
            possibleGuesses = initializePossibleGuesses();
        } else {
            // Modify the list based on the results of the last round:
            // 1.Get the code guessed by AI last time.
            String code = aiGuess;
            // System.out.println("code is " + code); //print
            // code--------------------------------------

            // 2.Get the result of the AI's last guess.
            String result = game.aiGuessResult;
            // System.out.println(result);//print the result of AI's last
            // guess---------------------------------
            int bIndex = result.indexOf("b");
            int cIndex = result.indexOf("c");
            int targetBulls = result.charAt(bIndex - 2) - '0';
            int targetCows = result.charAt(cIndex - 2) - '0';

            /*
             * 3.Use the code guessed by the AI last time as the target code,
             * compare the numbers in the list with it,
             * and remove those numbers form the list that do not yield the same number of
             * bulls or cows.
             */

            for (int i = possibleGuesses.size() - 1; i >= 0; i--) {
                String guess = possibleGuesses.get(i);
                int bulls = getBulls(code, guess);
                int cows = getCows(code, guess);
                if (bulls != targetBulls || cows != targetCows) {
                    possibleGuesses.remove(i);
                }
            }
        }

        // 4. Get a random member of the list.
        int index = (int) (Math.random() * possibleGuesses.size());
        aiGuess = possibleGuesses.get(index);

        return aiGuess;
    }

    private ArrayList<String> initializePossibleGuesses() {
        ArrayList<String> arrayList = new ArrayList<>();
        String num;
        for (int i = 0123; i <= 9876; i++) {
            if (i < 999) {
                num = "0" + Integer.toString(i);
            } else {
                num = Integer.toString(i);
            }
            if (Utility.validateGuess(num)) {
                arrayList.add(num);
            }
        }
        return arrayList;
    }

    //Calculate the number of Bulls.
    private int getBulls(String guess, String code) {
        int bull = 0;
        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) == code.charAt(i)) {
                bull++;
            }
        }
        return bull;
    }

    //Calculate the number of Cows.
    private int getCows(String guess, String code) {
        int cow = 0;
        for (int i = 0; i < guess.length(); i++) {
            for (int j = 0; j < code.length(); j++) {
                if (i != j) {
                    if (guess.charAt(i) == code.charAt(j)) {
                        cow++;
                    }
                }
            }
        }
        return cow;
    }
}
