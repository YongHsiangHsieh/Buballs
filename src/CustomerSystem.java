/**
 * Represents a customer management system that allows creation of customer objects and obtaining customer details.
 */
public class CustomerSystem {

    /**
     * Constructor for the CustomerSystem class.
     */
    public CustomerSystem() {
    }

    /**
     * Creates a new customer object with the given name and ID.
     *
     * @param name the name of the customer
     * @param ID   the ID of the customer
     * @return the new customer object
     */
    public Customer createCustomer(String name, int ID) {
        return new Customer(name, getEmail(), getStatus(), ID);
    }

    /**
     * Prompts the user for a valid customer name and returns it.
     *
     * @return the entered valid customer name
     */
    public String getCustomerName() {
        while (true) {
            System.out.println("Type in your name:");
            String name = EasyScanner.nextString();
            if (ValidationUtility.isValidName(name)) {
                return name;
            } else {
                PrintUtility.invalidMessage();
            }
        }
    }

    /**
     * Prompts the user for a valid customer ID and returns it.
     *
     * @return the entered valid customer ID
     */
    public int getCustomerID() {
        while (true) {
            System.out.println("Type in your ID:");
            int ID = EasyScanner.readValidInt();
            if ((ID >= 100) && (ID < 10000)) {
                return ID;
            }
            PrintUtility.invalidMessage();
        }
    }

    /**
     * Prompts the user for a valid email and returns it.
     *
     * @return the entered valid email
     */
    public String getEmail() {
        while (true) {
            System.out.println("Enter email:");
            String email = EasyScanner.nextString();
            if (ValidationUtility.isValidEmail(email)) {
                return email;
            }
            PrintUtility.invalidMessage();
        }
    }

    /**
     * Prompts the user to choose a membership status and returns the selected status.
     *
     * @return the selected membership status
     */
    public MembershipStatus getStatus() {
        while (true) {
            System.out.println("""
                    Choose one membership status (by number)
                    1 -> Boba (20% off every order)
                    2 -> Bubble (10% off every order)""");
            PrintUtility.printDashes(80);
            int status = EasyScanner.readValidInt();
            switch (status) {
                case 1 -> {
                    return MembershipStatus.BOBA;
                }

                case 2 -> {
                    return MembershipStatus.BUBBLE;
                }

                default -> PrintUtility.errorMessage();
            }
        }
    }
}
