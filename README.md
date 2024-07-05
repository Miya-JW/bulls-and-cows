# Bulls and Cows Game

## Description
Bulls and Cows is a classic code-breaking game where one player (the codemaker) thinks of a secret code and the other player (the codebreaker) tries to guess it within a certain number of turns. Each guess is met with feedback in terms of "bulls" and "cows", where bulls indicate correct digits in the correct positions and cows indicate correct digits in the wrong positions.

This implementation allows players to choose between playing against an AI and manually entering guesses, with three levels of difficulty for the AI.

## Technologies Used
- Java
- Gradle (for building and dependencies management)

## Key Features

### Dynamic Difficulty Levels
- **Three Levels of AI Difficulty**: Engage with the AI in Easy, Medium, or Hard modes, each offering a different level of challenge to accommodate all types of players from beginners to advanced.

### Automatic Guessing Mode
In Automatic Guessing Mode, players can preload their guesses into a document before starting the game. This mode is perfect for those who prefer to strategize their moves ahead of time. The game automatically reads these guesses and competes against the AI, providing a hands-off approach to see how well your pre-planned strategy holds up.

### Game Results Saving
Post-game, players have the option to save the complete game results to a local document. This feature is especially useful for those looking to analyze their strategies or revisit the gameplay to learn from past moves. Whether you're refining your tactics or just want to record your victories, this feature ensures you have all the details at your fingertips.

### Enhanced Gameplay Experience
- **Strategize and Plan**: Utilize the Automatic Guessing Mode to plan your entire game strategy in advance, and watch how your plans unfold against the AI without manual inputs.
- **Record and Reflect**: Save your game results to reflect on your decisions and outcomes, which is perfect for enhancing your strategies or sharing your experiences with the gaming community.

### Advanced AI in Hard Mode
Experience the pinnacle of challenge in Hard Mode, where our sophisticated AI is designed to decipher any player's code within 7 rounds. This mode tests your ability to craft complex codes that can withstand the AI's advanced algorithms.

## Highlight Features
- **Intelligent Guessing Algorithm**: In Hard Mode, the AI uses a refined algorithm to make educated guesses that dynamically adapt based on player feedback, aiming to crack codes swiftly and efficiently.
- **Optimized for Challenge**: Hard Mode challenges players to think more deeply about their code selections, providing a rigorous puzzle-solving experience.
- **Consistent Performance**: The AI is designed to ensure consistency in guessing correctly within 7 turns, showcasing the effectiveness and intelligence of our game mechanics.

Our game is crafted to ensure that every player can enjoy a personalized and engaging experience, tailored to both casual gamers and serious strategists alike.
## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites
- Java JDK 17
- Gradle 7.3 or higher

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/Miya-JW/bulls-and-cows.git
   ```
2.	Navigate to the project directory:
   ```bash
  cd bulls-and-cows
   ```
## Running the Game

### Using an IDE (Recommended for this project)
To run the game, it's recommended to use an Integrated Development Environment (IDE) such as IntelliJ IDEA, which should be configured to use JDK 17. Here are the steps to run the project within the IDE:

1. **Open the Project**:
   - Open IntelliJ IDEA.
   - Select `File > Open` from the menu.
   - Navigate to the directory where you cloned or downloaded the project.
   - Select the project folder and click `Open`.

2. **Configure the Project**:
   - Make sure the project is set to use JDK 17. You can check and set this in `File > Project Structure > Project`.
   - IntelliJ IDEA should automatically recognize the Gradle build file (`build.gradle`).

3. **Run the Game**:
   - Navigate to the `src/main/java` directory in the Project Explorer.
   - Locate the `Game.java` file (or whichever class contains the `main` method).
   - Right-click on the file and select `Run 'Game.main()'` to start the game.


### How to Play

1.	Start the game. 
2. ect the difficulty level of the AI or choose manual input.
3.	If playing against the AI, enter your guesses based on feedback provided after each guess.
4.	If playing in manual input mode, input the guesses directly or load them from a file.