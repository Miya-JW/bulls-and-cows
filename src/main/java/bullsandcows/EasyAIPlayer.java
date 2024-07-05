package bullsandcows;

public class EasyAIPlayer extends AIPlayer {
    public EasyAIPlayer(Game game) {
        super(game, "EASY");
    }

    @Override
    public String makeGuess() {
        return Utility.generateRandomCode();
    }
}
