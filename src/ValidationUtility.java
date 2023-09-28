/**
 * A utility class with static methods to validate name and email strings.
 */
public class ValidationUtility {

    /**
     * Validates a name string.
     *
     * @param name the string to be validated
     * @return true if the string is not null and matches the regex pattern "^[a-zA-Z\\s]{1,50}$", false otherwise
     */
    public static boolean isValidName(String name) {
        return name != null && name.matches("^[a-zA-Z\\s]{1,50}$");
    }

    /**
     * Validates an email string.
     *
     * @param email the string to be validated
     * @return true if the string is not null and contains the "@" character, false otherwise
     */
    public static boolean isValidEmail(String email) {
        return email != null && email.contains("@");
    }
}
