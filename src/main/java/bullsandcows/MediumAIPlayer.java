package bullsandcows;

import java.util.ArrayList;
import java.util.List;

public class MediumAIPlayer extends AIPlayer {
    private List<String> triedGuesses = new ArrayList<>();

    public MediumAIPlayer(Game game) {
        super(game, "MEDIUM");
    }

    @Override
    public String makeGuess() {
        String guess;
        do {
            guess = Utility.generateRandomCode();
        } while (triedGuesses.contains(guess));
        triedGuesses.add(guess);
        return guess;
    }
}