package bullsandcows;

public abstract class AIPlayer extends Player {
    protected String aiCode;
    protected String difficultyLevel;
    protected Game game;

    public AIPlayer(Game game, String difficultyLevel) {
        this.game = game;
        this.difficultyLevel = difficultyLevel;
        this.aiCode = Utility.generateRandomCode();
    }

    public abstract String makeGuess();

    public String generateCode() {
        aiCode = Utility.generateRandomCode();
        return aiCode;
    }

    public String getAicode() {
        return aiCode;
    }
}
