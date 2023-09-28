/**
 * Main class of the Buballs program, which allows users to interact with various sub-systems.
 */

public class Buballs {
    /**
     * Entry point of the Buballs program.
     *
     * @param args command line arguments (not used in this program)
     */
    public static void main(String[] args) {
        BuballsSystem waterfordBuballs = new BuballsSystem();
        boolean isRunning = true;

        while (isRunning) {
            // Print the menu options
            System.out.println("""
                    
                    Choose one to go (by number)
                    1 -> Ordering
                    2 -> Orders database
                    3 -> Customer system
                    4 -> Inventory
                    5 -> Sale records
                    0 -> Quit""");
            PrintUtility.printDashes(80);
            // Read the user's choice and execute the corresponding action
            switch (EasyScanner.readValidInt()) {
                case 1 -> waterfordBuballs.ordering();
                case 2 -> waterfordBuballs.ordersDatabase();
                case 3 -> waterfordBuballs.customerSystem();
                case 4 -> waterfordBuballs.inventory();
                case 5 -> waterfordBuballs.saleRecordSystem();
                case 0 -> {
                    isRunning = false;
                }
                default -> PrintUtility.errorMessage();
            }
        }
        System.out.println("See ye next time!");
    }
}