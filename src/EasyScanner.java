import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * EasyScanner is an interface that simplifies the use of the Scanner class
 * for reading input from the user.
 */
public interface EasyScanner {
    Scanner sc = new Scanner(System.in);

    /**
     * Reads an integer from the user's input.
     *
     * @return the integer entered by the user
     */
    static int nextInt() {
        return sc.nextInt();
    }

    /**
     * Reads a string from the user's input.
     *
     * @return the string entered by the user
     */

    static String nextString() {
        return sc.nextLine();
    }

    /**
     * Reads a valid positive integer from the user's input.
     * Continuously prompts the user until a valid input is entered.
     *
     * @return the valid positive integer entered by the user
     */
    static int readValidInt() {
        while (true) {
            int input = nextInt();
            sc.nextLine();
            try {
                if (input >= 0) {
                    return input;
                } else {
                    System.out.println("Invalid input. Please enter a positive integer.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                sc.nextLine();
            }
        }
    }

    /**
     * Reads an index from the user's input and checks if it is within the range
     * of the given size. Continuously prompts the user until a valid input is entered.
     *
     * @param size the maximum size of the list
     * @return the valid index entered by the user
     */
    static int getIndexFromLists(int size) {
        while (true) {
            int index = (EasyScanner.readValidInt()) - 1;
            if (index >= 0 && index < size) {
                return index;
            }
            PrintUtility.invalidMessage();
        }
    }
}
