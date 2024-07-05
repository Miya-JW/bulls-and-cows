package bullsandcows;

public class Utility {


    // Generate a four-digit random number with distinct digit for each position.
    public static String generateRandomCode() {

        int randomNumber = 1000 + ((int) (Math.random() * 10000) % 9000);
        String randomCode = Integer.toString(randomNumber);
        while (hasRepeatingDigits(randomCode)) {
            return generateRandomCode();
        }
        return randomCode;
    }

    //Verify if there are any repeating digits in each position of the number.
    private static boolean hasRepeatingDigits(String randomCode) {
        for (int i = 0; i < randomCode.length() - 1; i++) {
            for (int j = i + 1; j < randomCode.length(); j++) {
                if (randomCode.charAt(i) == randomCode.charAt(j)) {
                    return true;
                }
            }
        }
        return false;
    }

    //Validate the code to ensure it is valid, avoiding non-numeric entries and repeated digits.
    public static boolean validateGuess(String string) {
        if (string.length() != 4) {
            return false;
        } else {
            for (char c : string.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
                if (hasRepeatingDigits(string)) {
                    return false;
                }
            }
        }
        return true;
    }
}
