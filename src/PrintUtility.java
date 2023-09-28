import java.util.Random;

public class PrintUtility {
    /**
     * Returns a string with a specified number of dashes followed by a newline character.
     *
     * @param times the number of dashes to include in the string
     * @return a string with the specified number of dashes followed by a newline character
     */
    public static String dashes(int times) {
        return "-".repeat(times) + "\n";
    }

    /**
     * Prints a specified number of dashes followed by a newline character.
     *
     * @param times the number of dashes to print
     */
    public static void printDashes(int times) {
        System.out.println("-".repeat(times));
    }

    /**
     * Returns a string with a specified number of waves (~) followed by a newline character.
     *
     * @param times the number of waves to include in the string
     * @return a string with the specified number of waves followed by a newline character
     */
    public static String waves(int times) {
        return "~".repeat(times) + "\n";
    }

    /**
     * Prints an error message when the user makes an incorrect choice.
     */
    public static void errorMessage() {
        System.out.println("Error, choose again!");
    }

    /**
     * Prints an error message when the user provides an invalid input.
     */
    public static void invalidMessage() {
        System.out.println("Error, invalid input!");
    }

    /**
     * Generates a random integer within the specified range.
     *
     * @param start the starting value of the range (inclusive)
     * @param end   the ending value of the range (exclusive)
     * @return a random integer within the specified range
     */
    public static int getRandomInteger(int start, int end) {
        Random random = new Random();
        return random.nextInt((end - start)) + start;
    }

    /**
     * Prints a message when the list is empty.
     */
    public static void emptyList() {
        System.out.println("Sorry, it's empty!");
    }
}


