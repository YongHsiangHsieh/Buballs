import java.io.*;
import java.time.LocalDate;

/**
 * FoodStall is a class that manages the entire system of a food stall.
 * It includes features such as ordering, customer management, inventory management, and sales records.
 */
public class FoodStall implements Serializable {
    // Class variables
    private Database database = new Database();
    private final OrderingSystem ordering = new OrderingSystem();
    private final CustomerSystem customerSystem = new CustomerSystem();
    private Inventory inventory = new Inventory();

    public FoodStall() {
    }

    // ---------- Ordering ----------

    /**
     * Handles the ordering process, allowing the user to order food items.
     */
    public void order() {
        System.out.println("""
                What would you like (by number)
                1 -> Bubble tea
                2 -> Sweet potato balls""");
        PrintUtility.printDashes(80);
        int food = EasyScanner.readValidInt();
        ordering.order(food);
    }

    /**
     * Lists all the current orders.
     */
    public void listOrder() {
        if (ordering.isOrderListEmpty()) {
            PrintUtility.emptyList();
        } else {
            ordering.listOrder();
        }
    }

    /**
     * Allows the user to modify their current orders.
     */
    public void modifyOrder() {
        if (ordering.isOrderListEmpty()) {
            PrintUtility.emptyList();
        } else {
            ordering.modifyOrder();
        }
    }

    /**
     * Handles the checkout process, applying discounts for registered customers and clearing the order list.
     */
    public void checkout() {
        if (ordering.isOrderListEmpty()) {
            System.out.println("Good bye!");
        } else {
            Customer customer = promptForCustomer();
            handleCheckout(customer);
            inventory.takeOrderFromInventory(ordering.ingredientsUsed());
            ordering.clearList();
        }
    }

    // Private ordering-related methods...
    private Customer promptForCustomer() {
        System.out.println("""
                Are you a customer (by number, any number other than 1, will be considered No)
                1 -> Yes (with discounts)
                2 -> No""");
        PrintUtility.printDashes(80);
        int member = EasyScanner.readValidInt();

        if (member == 1) {
            String name = customerSystem.getCustomerName();
            int ID = customerSystem.getCustomerID();
            if (isCustomer(name, ID)) {
                return database.getCustomerByIDAndName(ID, name);
            } else {
                System.out.println("Sorry, you are not a member");
            }
        }
        return null;
    }

    private void handleCheckout(Customer customer) {
        double rate = (customer != null) ? customer.getDiscountRate() : 1;
        Order order = ordering.makeOrder(customer);
        SaleRecord record = ordering.makeSaleRecord(rate);
        database.addOrder(order);
        database.addSaleRecord(record);
        ordering.checkout(rate);

        if (customer != null) {
            System.out.printf("%s customer: %s, See you next time!%n", customer.getMembershipStatus(), customer.getName());
        }
    }


    // ---------- Orders Database ----------

    /**
     * Prints the order history.
     */
    public void printOrders() {
        if (database.isOrderDatabaseEmpty()) {
            PrintUtility.emptyList();
        } else {
            database.printHistoryOfOrders();
        }
    }

    /**
     * Removes an order from the order history.
     */
    public void removeOrders() {
        if (database.isOrderDatabaseEmpty()) {
            PrintUtility.emptyList();
        } else {
            System.out.println("Which order would you like to remove (by number)");
            database.removeOrder(getOrderIndex());
        }
    }

    // Private orders database-related methods...

    private int getOrderIndex() {
        database.printHistoryOfOrders();
        return EasyScanner.getIndexFromLists(database.getOrderListSize());
    }


// ---------- Customer ----------

    /**
     * Creates a new customer and adds them to the database.
     */
    public void createCustomer() {
        System.out.println("Let's become one of us in just 2 min");
        String name = customerSystem.getCustomerName();
        int ID = PrintUtility.getRandomInteger(100, 10000);
        if (isCustomer(name, ID)) {
            System.out.println("You are already part of us");
        } else {
            database.addCustomer(customerSystem.createCustomer(name, ID));
            System.out.println("Time to crack bubble tea and sweet potato balls !!!");
        }
    }

    /**
     * Lists all registered customers.
     */
    public void listAllCustomers() {
        if (database.isCustomerListEmpty()) {
            PrintUtility.emptyList();
        } else {
            database.listAllCustomers();
        }
    }

    /**
     * Modifies a customer's information.
     */
    public void modifyCustomer() {
        if (database.isCustomerListEmpty()) {
            PrintUtility.emptyList();
        } else {
            System.out.println("""
                    How would you like to modify the customer (by number)
                    1 -> By ID and Name
                    2 -> By Index""");
            PrintUtility.printDashes(80);
            while (true) {
                switch (EasyScanner.readValidInt()) {
                    case 1 -> {
                        modificationOfCustomerByNameAndID();
                        return;
                    }
                    case 2 -> {
                        modificationOfCustomerByIndex();
                        return;
                    }
                    default -> PrintUtility.errorMessage();
                }
            }
        }
    }

    /**
     * Retrieves the order history for a specific customer.
     */
    public void getCustomerOrderHistory() {
        String name = customerSystem.getCustomerName();
        int ID = customerSystem.getCustomerID();
        if (isCustomer(name, ID)) {
            System.out.println(database.getCustomerOrderHistory(ID, name));
        } else {
            System.out.println("Sorry, you are not a member");
        }
    }

    // Private customer-related methods...
    private void modificationOfCustomerByNameAndID() {
        System.out.println("Type in name and ID for searching");
        String name = customerSystem.getCustomerName();
        int ID = customerSystem.getCustomerID();

        if (isCustomer(name, ID)) {
            Customer customer = database.getCustomerByIDAndName(ID, name);
            while (true) {
                System.out.println("""
                        What would like to do (by number)
                        1 -> Remove
                        2 -> Change Email
                        3 -> Change membership status""");
                PrintUtility.printDashes(80);
                int action = EasyScanner.readValidInt();
                switch (action) {
                    case 1 -> {
                        database.removeCustomer(customer);
                        return;
                    }
                    case 2 -> {
                        database.updateCustomerEmail(customer, customerSystem.getEmail());
                        return;
                    }
                    case 3 -> {
                        database.updateMembershipStatus(customer, customerSystem.getStatus());
                        return;
                    }
                    default -> PrintUtility.errorMessage();
                }
            }
        } else {
            System.out.println("Sorry can't find the customer, please use index searching");
            modificationOfCustomerByIndex();
        }
    }

    private void modificationOfCustomerByIndex() {
        database.listAllCustomers();
        System.out.println("Which customer would you like to modify (by number)");
        int customerIndex = EasyScanner.getIndexFromLists(database.getCustomerListSize());
        while (true) {
            System.out.println("""
                    What would like to do (by number)
                    1 -> Remove
                    2 -> Change Email
                    3 -> Change membership status""");
            PrintUtility.printDashes(80);
            int action = EasyScanner.readValidInt();
            switch (action) {
                case 1 -> {
                    database.removeCustomer(customerIndex);
                    return;
                }
                case 2 -> {
                    database.updateCustomerEmail(customerIndex, customerSystem.getEmail());
                    return;
                }
                case 3 -> {
                    database.updateMembershipStatus(customerIndex, customerSystem.getStatus());
                    return;
                }
                default -> PrintUtility.errorMessage();
            }
        }
    }

    private boolean isCustomer(String name, int ID) {
        return database.isCustomerByIDAndName(ID, name);
    }

    // ---------- Inventory ----------

    /**
     * Adds an item to the inventory.
     */
    public void addInventory() {
        while (true) {
            System.out.println("""
                    What to add (by number)
                    1 -> Tea
                    2 -> Seasoning powder
                    3 -> Sweet potato
                    4 -> Bubbles
                    5 -> Others""");
            PrintUtility.printDashes(80);
            switch (EasyScanner.readValidInt()) {
                case 1 -> {
                    addTea();
                    return;
                }
                case 2 -> {
                    addSeasoningPowder();
                    return;
                }
                case 3 -> {
                    addStuff("Sweet potato");
                    return;
                }
                case 4 -> {
                    addStuff("Bubbles");
                    return;
                }
                case 5 -> {
                    while (true) {
                        System.out.println("Type in what you want to add");
                        String name = EasyScanner.nextString();
                        if (ValidationUtility.isValidName(name)) {
                            addStuff(name);
                            return;
                        } else {
                            PrintUtility.invalidMessage();
                        }
                    }
                }
                default -> PrintUtility.errorMessage();
            }
        }
    }

    /**
     * Lists all items in the inventory.
     */
    public void listInventory() {
        if (inventory.isInventoryEmpty()) {
            PrintUtility.emptyList();
        } else {
            inventory.stocktake();
        }
    }

    /**
     * Modifies an item in the inventory.
     */
    public void modifyInventory() {
        if (inventory.isInventoryEmpty()) {
            PrintUtility.emptyList();
        } else {
            System.out.println("Which one to modify (by ID)");
            inventory.stocktake();
            Ingredient item = getIngredientByID();
            while (true) {
                System.out.println("""
                        What would want to do (by number)
                        1 -> Remove
                        2 -> Change quantity""");
                PrintUtility.printDashes(80);
                switch (EasyScanner.readValidInt()) {
                    case 1 -> {
                        inventory.removeStock(item);
                        return;
                    }
                    case 2 -> {
                        System.out.println("Type in new quantity:");
                        while (true) {
                            int newQuantity = EasyScanner.readValidInt();
                            if (newQuantity > 1000000) {
                                PrintUtility.invalidMessage();
                            } else {
                                item.setQuantity(newQuantity);
                                return;
                            }
                        }
                    }
                    default -> PrintUtility.errorMessage();
                }
            }
        }
    }

    // Private inventory-related methods...
    private Ingredient getIngredientByID() {
        while (true) {
            System.out.println("Type in ID:");
            int ID = EasyScanner.readValidInt();
            Ingredient item = inventory.findItemByID(ID);
            if (item == null) {
                PrintUtility.invalidMessage();
            } else {
                return item;
            }
        }
    }

    private void addTea() {
        while (true) {
            System.out.println("""
                    Which you to add (by number)
                    1 -> Black tea
                    2 -> Green tea""");
            PrintUtility.printDashes(80);
            switch (EasyScanner.readValidInt()) {
                case 1 -> {
                    addStuff(TeaType.BLACK_TEA.name());
                    return;
                }
                case 2 -> {
                    addStuff(TeaType.GREEN_TEA.name());
                    return;
                }
                default -> PrintUtility.errorMessage();
            }
        }
    }

    private void addSeasoningPowder() {
        while (true) {
            System.out.println("""
                    Which you to add (by number)
                    1 -> Plum powder
                    2 -> Sesame
                    3 -> Chocolate""");
            PrintUtility.printDashes(80);
            switch (EasyScanner.readValidInt()) {
                case 1 -> {
                    addStuff(Flavor.PLUM_POWDER.name());
                    return;
                }
                case 2 -> {
                    addStuff(Flavor.SESAME.name());
                    return;
                }
                case 3 -> {
                    addStuff(Flavor.CHOCOLATE.name());
                    return;
                }
                default -> PrintUtility.errorMessage();
            }
        }
    }

    private void addStuff(String name) {
        System.out.print("Type in quantity: ");
        inventory.addStock(new Ingredient(name, PrintUtility.getRandomInteger(1, 1000), EasyScanner.readValidInt(), LocalDate.now()));
    }


    // ---------- SaleRecord ----------

    /**
     * Prints the sales records.
     */
    public void printSaleRecords() {
        if (database.isSaleRecordEmpty()) {
            PrintUtility.emptyList();
        } else {
            database.printHistoryOfSaleRecords();
        }
    }

    /**
     * Removes a sales record.
     */
    public void removeRecord() {
        if (database.isSaleRecordEmpty()) {
            PrintUtility.emptyList();
        } else {
            System.out.println("Which one to remove (by ID)");
            database.printHistoryOfSaleRecords();
            database.removeSaleRecord(getRecordByID());
        }
    }

    // Private sales record-related methods...

    private SaleRecord getRecordByID() {
        while (true) {
            System.out.println("Type in ID:");
            int ID = EasyScanner.readValidInt();
            SaleRecord record = database.getRecordByID(ID);
            if (record == null) {
                PrintUtility.invalidMessage();
            } else {
                return record;
            }
        }
    }

    // ----------------------------------------------------------
    /**
     * Saves the database and inventory data to a file.
     */
    public void saveData() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.xml"))) {
            out.writeObject(database);
            out.writeObject(inventory);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    /**
     * Loads the database and inventory data from a file.
     */
    public void loadData() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.xml"))) {
            database = (Database) in.readObject();
            inventory = (Inventory) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}
