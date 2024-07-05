package bullsandcows;


import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Locale;

public class Game {
    private ResourceBundle message;
    private Player player;
    private AIPlayer aiPlayer;


    final private int ROUND_COUNT = 7;
    private boolean isGameOver = false;
    private String difficultyLevel;
    private String playMode;
    private String playGuessResult;
    public String aiGuessResult;

    private List<String> autoGuesses = null;
    private List<String> gameData = new ArrayList<>();
    private String resultFileName;

    public Game(Player player, AIPlayer aiPlayer, Locale locale) {
        this.message = ResourceBundle.getBundle("messages", Locale.US);
        this.player = player;
        this.aiPlayer = aiPlayer;

    }

    public static void main(String[] args) {
        Locale locale = Locale.getDefault();
        Player player = new Player();
        AIPlayer aiPlayer = null;
        Game game = new Game(player, aiPlayer, locale);
        game.start();
    }

    /**
     * Let the player select the AI difficulty level.
     * Get a secret code from AIPlayer for user to guess.
     * Print prompt to get user's secret code.
     * Let the user choose to enter the guess manually or read from a file.
     * Play the game within the number of game rounds.
     * When one side wins, print the victory message, and end the game.
     * If no correct guess is made within the game rounds, print the ending message.
     */
    public void start() {
        gameData.add("Bulls & Cows game result.");
        selectDifficultyLevel();
        // Select AI difficulty level
        initializeAIPlayer();
        // Start the game loop
        gameLoop();

    }

    // Let player select the AI difficulty level.
    private void selectDifficultyLevel() {
        System.out.println(message.getString("CHOSE_DIFFICULTY_LEVEL"));
        difficultyLevel = Keyboard.readInput().toUpperCase();
        if (!difficultyLevel.equals("EASY")
            && !difficultyLevel.equals("MEDIUM")
            && !difficultyLevel.equals("HARD")) {
            System.out.println("Invalid difficulty level!");
            selectDifficultyLevel();
        }
    }

    private void initializeAIPlayer() {
        switch (this.difficultyLevel) {
            case "EASY":
                this.aiPlayer = new EasyAIPlayer(this);
                break;
            case "MEDIUM":
                this.aiPlayer = new MediumAIPlayer(this);
                break;
            case "HARD":
                this.aiPlayer = new HardAIPlayer(this);
                break;
            default:
                throw new IllegalArgumentException("Unknown difficulty level: " + this.difficultyLevel);
        }
    }

    private void gameLoop() {
        selectPlayMode();
        generateAICode();
        getPlayerSecretCode();

        for (int i = 0; i < ROUND_COUNT && !isGameOver; i++) {
            gameData.add(message.getString("DELIMITER"));
            gameData.add("Turn " + (i + 1) + " :");
            System.out.println(message.getString("DELIMITER"));
            if (playMode.equals("1")) {
                // Manual input mode:
                playGuessResult = getManualGuessResult();
            } else {
                // Auto input mode.
                playGuessResult = getAutoGuessResult();
            }

            System.out.println(playGuessResult);
            if (isGameOver) {
                System.out.println(message.getString("USER_WIN"));
                gameData.add(message.getString("USER_WIN"));
                askForSavingFile();
                return;
            }
            aiGuessResult = getAIGuessResult();
            System.out.println(aiGuessResult);
            if (isGameOver) {
                System.out.println(message.getString("COMPUTER_WIN"));
                gameData.add(message.getString("COMPUTER_WIN"));
                askForSavingFile();
            }
        }
        // After completing the match round.
        if (!isGameOver) {
            printEndMessage();
            askForSavingFile();
            isGameOver = true;
        }
    }

    // Choose manual or automatic input.
    private String selectPlayMode() {
        System.out.println(message.getString("DELIMITER"));
        System.out.println(message.getString("PLAYER_MODE"));
        String userMode = Keyboard.readInput();
        if (userMode.equals("1")) {
            playMode = "1";
        } else if (userMode.equals("2")) {
            playMode = "2";
            System.out.println(message.getString("DELIMITER"));
            System.out.println(message.getString("ENTER_FILE_NAME"));
            autoGuesses = player.getCodeFromFile();
        } else {
            System.out.println("Invalid input");
            selectPlayMode();
        }
        return playMode;
    }

    // Get a secret code from AIPlayer for user to guess.
    private void generateAICode() {
        String aiCode = aiPlayer.generateCode();
        gameData.add("Computer's code: " + aiCode);
    }

    //Get a secret code from Player for AI to guess.
    private void getPlayerSecretCode() {
        System.out.println(message.getString("DELIMITER"));
        System.out.println(message.getString("START_MESSAGE"));
        String playerCode = player.generateCode();
        gameData.add("Your code: " + playerCode);
    }

    // Asking to save the game result
    private void askForSavingFile() {
        System.out.println(message.getString("DELIMITER"));
        System.out.println(message.getString("SAVE_FILE"));
        String input = Keyboard.readInput();
        if (input.toUpperCase().equals("SAVE")) {
            System.out.println(message.getString("DELIMITER"));
            System.out.println("Please enter a file name: ");
            String fileName = Keyboard.readInput();
            System.out.println(message.getString("DELIMITER"));
            resultFileName = fileName + ".txt";
            FileHandler.writeGameResultToFile(gameData, resultFileName);
        } else return;
    }

    // Get guessing result.(Player-manual-input)
    private String getManualGuessResult() {
        System.out.print(message.getString("SHOW_USER_GUESS"));
        String playerGuess = player.makeGuess();
        String aiCode = aiPlayer.getAicode();
        String result = getResult(playerGuess, aiCode);
        gameData.add("You guessed " + playerGuess + ". " + result);
        return result;
    }

    // Get guessing result.(Player-auto-input)
    private String getAutoGuessResult() {
        // First, write the numbers from the file into a list.
        // Read the first number.
        String guess = "";
        if (!autoGuesses.isEmpty()) {
            guess = autoGuesses.get(0);
            autoGuesses.remove(0);
            System.out.print(message.getString("SHOW_USER_GUESS"));
            System.out.println(guess);
        } else {
            // After the content of the list run out, switch to manual input for guess.
            System.out.print(message.getString("SHOW_USER_GUESS"));
            guess = player.makeGuess();
        }
        String aiCode = aiPlayer.getAicode();
        String result = getResult(guess, aiCode);
        gameData.add("You guessed " + guess + ". " + result);
        return result;
    }

    // Get guessing result.(AI)
    private String getAIGuessResult() {
        String aiGuess = aiPlayer.makeGuess();
        System.out.println(message.getString("SHOW_COMPUTER_GUESS") + aiGuess);
        String result = getResult(aiGuess, player.getCode());
        gameData.add("Computer guessed " + aiGuess + ". " + result);
        return result;
    }

    // Generate the guessing result.
    private String getResult(String guess, String code) {
        return message.getString("SHOW_RESULT") + getBulls(guess, code) + " and " + getCows(guess, code);
    }

    // Calculate the number of Bulls.
    private String getBulls(String guess, String code) {
        String bull;
        int bullCounter = 0;
        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) == code.charAt(i)) {
                bullCounter++;
            }
        }
        if (bullCounter == 1) {
            bull = Integer.toString(bullCounter) + " bull";
        } else {
            bull = Integer.toString(bullCounter) + " bulls";
        }
        if (bullCounter == 4) {
            isGameOver = true;
        }
        return bull;
    }

    // Calculate the number of Cows.
    private String getCows(String guess, String code) {
        String cow;
        int cowCounter = 0;
        for (int i = 0; i < guess.length(); i++) {
            for (int j = 0; j < code.length(); j++) {
                if (i != j) {
                    if (guess.charAt(i) == code.charAt(j)) {
                        cowCounter++;
                    }
                }
            }
        }
        if (cowCounter == 1) {
            cow = Integer.toString(cowCounter) + " cow";
        } else {
            cow = Integer.toString(cowCounter) + " cows";
        }
        return cow;
    }

    // Print the ending message when no one wins. And show the secret code of AI
    // player.
    private void printEndMessage() {
        System.out.println(message.getString("END_MESSAGE"));
        gameData.add(message.getString("END_MESSAGE"));
    }
}
