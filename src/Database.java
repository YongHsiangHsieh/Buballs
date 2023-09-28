import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a database storing customers, orders, and sale records.
 */
public class Database implements Serializable {
    private final List<Customer> customers = new ArrayList<>();
    private final List<Order> orders = new ArrayList<>();
    private final List<SaleRecord> saleRecords = new LinkedList<>();

    /**
     * Constructs an empty Database object.
     */

    public Database() {
    }

    // ---------- Customer ----------

    /**
     * Checks if the customer list is empty.
     *
     * @return true if the customer list is empty, false otherwise
     */
    public boolean isCustomerListEmpty() {
        return customers.isEmpty();
    }

    /**
     * Adds a new customer to the database.
     *
     * @param customer the Customer object to be added
     */
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    /**
     * Removes a customer from the database by index.
     *
     * @param customerIndex the index of the customer to be removed
     */
    public void removeCustomer(int customerIndex) {
        customers.remove(customerIndex);
    }

    /**
     * Removes a customer from the database.
     *
     * @param customer the Customer object to be removed
     */

    public void removeCustomer(Customer customer) {
        customers.remove(customer);
    }

    /**
     * Updates the membership status of a customer by index.
     *
     * @param customerIndex the index of the customer
     * @param status        the new membership status
     */
    public void updateMembershipStatus(int customerIndex, MembershipStatus status) {
        customers.get(customerIndex).setMembershipStatus(status);
    }

    /**
     * Updates the membership status of a customer.
     *
     * @param customer the Customer object
     * @param status   the new membership status
     */
    public void updateMembershipStatus(Customer customer, MembershipStatus status) {
        for (Customer currentCustomer : customers) {
            if (currentCustomer.equals(customer)) {
                currentCustomer.setMembershipStatus(status);
                break;
            }
        }
    }


    /**
     * Updates the email of a customer by index.
     *
     * @param customerIndex the index of the customer
     * @param newEmail      the new email
     */
    public void updateCustomerEmail(int customerIndex, String newEmail) {
        customers.get(customerIndex).setEmail(newEmail);
    }

    /**
     * Updates the email of a customer.
     *
     * @param customer the Customer object
     * @param newEmail the new email
     */
    public void updateCustomerEmail(Customer customer, String newEmail) {
        for (Customer currentCustomer : customers) {
            if (currentCustomer.equals(customer)) {
                currentCustomer.setEmail(newEmail);
                break;
            }
        }
    }

    /**
     * Lists all customers in the database.
     */
    public void listAllCustomers() {
        int i = 1;
        for (Customer customer : customers) {
            System.out.println(i + " -> " + customer);
            i++;
        }
    }

    /**
     * Checks if a customer with the specified ID and name exists in the database.
     *
     * @param ID   the customer's ID
     * @param name the customer's name
     * @return true if the customer exists, false otherwise
     */
    public boolean isCustomerByIDAndName(int ID, String name) {
        for (var e : customers) {
            if ((e.getID() == ID) && (e.getName().equalsIgnoreCase(name))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves a customer with the specified ID and name from the database.
     *
     * @param ID   the customer's ID
     * @param name the customer's name
     * @return the Customer object if found, null otherwise
     */
    public Customer getCustomerByIDAndName(int ID, String name) {
        for (var e : customers) {
            if ((e.getID() == ID) && (e.getName().equalsIgnoreCase(name))) {
                return e;
            }
        }
        return null;
    }

    /**
     * Returns the size of the customer list.
     *
     * @return the number of customers in the list
     */
    public int getCustomerListSize() {
        return customers.size();
    }

    // ---------- Customers + orders ----------

    /**
     * Retrieves a customer's order history as a formatted string.
     *
     * @param ID   the ID of the customer
     * @param name the name of the customer
     * @return a formatted string containing the customer's order history
     */
    public String getCustomerOrderHistory(int ID, String name) {
        StringBuilder allOrders = new StringBuilder();
        Customer customer = getCustomerByIDAndName(ID, name);
        for (var e : orders) {
            if (e.getCustomer() == null) {
                continue;
            }
            if (e.getCustomer().equals(customer)) {
                allOrders.append(PrintUtility.dashes(50)).append(e.printProductsOfOrder());
            }
        }
        return "Customer %s, %d order history: %n%s".formatted(customer.getName()
                , customer.getID(), allOrders);
    }

    // ---------- Orders ----------

    /**
     * Checks if the order database is empty.
     *
     * @return true if the order database is empty, false otherwise
     */
    public boolean isOrderDatabaseEmpty() {
        return orders.isEmpty();
    }

    /**
     * Adds a new order to the database.
     *
     * @param order the Order object to be added
     */
    public void addOrder(Order order) {
        orders.add(order);
    }

    /**
     * Removes an order from the database by index.
     *
     * @param orderIndex the index of the order to be removed
     */
    public void removeOrder(int orderIndex) {
        orders.remove(orderIndex);
    }

    /**
     * Prints the history of all orders in the database.
     */
    public void printHistoryOfOrders() {
        int i = 1;
        for (Order order : orders) {
            String indentedProducts = order.printProductsOfOrder().replace("\n", "\n    ");
            System.out.printf("%d ->%n    %s%n", i, indentedProducts);
            System.out.println(PrintUtility.waves(50));
            i++;
        }
    }

    /**
     * Returns the size of the order list.
     *
     * @return the number of orders in the list
     */
    public int getOrderListSize() {
        return orders.size();
    }

    // ---------- SaleRecords ----------

    /**
     * Checks if the sale record list is empty.
     *
     * @return true if the sale record list is empty, false otherwise
     */

    public boolean isSaleRecordEmpty() {
        return saleRecords.isEmpty();
    }

    /**
     * Adds a new sale record to the database.
     *
     * @param record the SaleRecord object to be added
     */
    public void addSaleRecord(SaleRecord record) {
        saleRecords.add(record);
    }

    /**
     * Removes a sale record from the database.
     *
     * @param record the SaleRecord object to be removed
     */
    public void removeSaleRecord(SaleRecord record) {
        saleRecords.remove(record);
    }

    /**
     * Prints the history of all sale records in the database.
     */
    public void printHistoryOfSaleRecords() {
        for (var e : saleRecords) {
            System.out.println(e);
        }
    }

    /**
     * Retrieves a sale record with the specified ID from the database.
     *
     * @param ID the sale record's ID
     * @return the SaleRecord object if found, null otherwise
     */
    public SaleRecord getRecordByID(int ID) {
        for (var e : saleRecords) {
            if (e.ID() == ID) {
                return e;
            }
        }
        return null;
    }
}
