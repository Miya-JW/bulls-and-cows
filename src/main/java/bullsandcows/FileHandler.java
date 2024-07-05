package bullsandcows;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {


    public static List<String> readGuessFromFile(String filename) {
        List<String> guesses = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                guesses.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return guesses;
    }

    public static void writeGameResultToFile(List<String> gameData, String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : gameData) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Done！");
    }
}
