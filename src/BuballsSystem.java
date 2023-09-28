/**
 * BuballsSystem is the main class to interact with the FoodStall.
 * It provides various sub-systems for users to manage orders, customers, inventory, and sale records.
 */
public class BuballsSystem {
    private final FoodStall foodStall = new FoodStall();

    /**
     * Constructor for BuballsSystem.
     * Loads data for the FoodStall.
     */
    public BuballsSystem() {
        foodStall.loadData();
    }

    /**
     * Handles ordering related operations.
     */
    public void ordering() {
        while (true) {
            System.out.println("""
                                        
                    What would you like (by number)
                    1 -> Order
                    2 -> List orders
                    3 -> Modify order
                    4 -> Check out""");
            PrintUtility.printDashes(80);
            switch (EasyScanner.readValidInt()) {
                case 1 -> foodStall.order();
                case 2 -> foodStall.listOrder();
                case 3 -> foodStall.modifyOrder();
                case 4 -> {
                    foodStall.checkout();
                    foodStall.saveData();
                    return;
                }
                default -> PrintUtility.errorMessage();
            }
        }
    }

    /**
     * Handles orders database related operations.
     */
    public void ordersDatabase() {
        while (true) {
            System.out.println("""
                                        
                    What would you like (by number)
                    1 -> List orders history
                    2 -> Remove orders
                    0 -> Quit""");
            PrintUtility.printDashes(80);
            switch (EasyScanner.readValidInt()) {
                case 1 -> foodStall.printOrders();
                case 2 -> foodStall.removeOrders();
                case 0 -> {
                    foodStall.saveData();
                    return;
                }
                default -> PrintUtility.errorMessage();
            }
        }
    }

    /**
     * Handles customer related operations.
     */
    public void customerSystem() {
        while (true) {
            System.out.println("""
                                        
                    What would you like (by number)
                    1 -> Create a customer
                    2 -> List all customers
                    3 -> Modify customers
                    4 -> List a customer orders' history
                    0 -> Quit""");
            PrintUtility.printDashes(80);
            switch (EasyScanner.readValidInt()) {
                case 1 -> foodStall.createCustomer();
                case 2 -> foodStall.listAllCustomers();
                case 3 -> foodStall.modifyCustomer();
                case 4 -> foodStall.getCustomerOrderHistory();
                case 0 -> {
                    foodStall.saveData();
                    return;
                }
                default -> PrintUtility.errorMessage();
            }
        }
    }

    /**
     * Handles inventory related operations.
     */
    public void inventory() {
        while (true) {
            System.out.println("""
                                        
                    What would you like (by number)
                    1 -> Add stock
                    2 -> Stock take
                    3 -> Modify stock
                    0 -> Quit""");
            PrintUtility.printDashes(80);
            switch (EasyScanner.readValidInt()) {
                case 1 -> foodStall.addInventory();
                case 2 -> foodStall.listInventory();
                case 3 -> foodStall.modifyInventory();
                case 0 -> {
                    foodStall.saveData();
                    return;
                }
                default -> PrintUtility.errorMessage();
            }
        }
    }


    /**
     * Handles sale record related operations.
     */
    public void saleRecordSystem() {
        while (true) {
            System.out.println("""
                                        
                    What would you like (by number)
                    1 -> Print sale records
                    2 -> Remove sale records
                    0 -> Quit""");
            PrintUtility.printDashes(80);
            switch (EasyScanner.readValidInt()) {
                case 1 -> foodStall.printSaleRecords();
                case 2 -> foodStall.removeRecord();
                case 0 -> {
                    foodStall.saveData();
                    return;
                }
                default -> PrintUtility.errorMessage();
            }
        }
    }
}
