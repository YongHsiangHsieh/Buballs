import java.io.Serializable;
import java.util.List;

/**
 * Represents a customer order consisting of a list of products.
 * Implements Serializable to allow saving and loading order objects.
 */
public class Order implements Serializable {
    private final Customer customer;
    private final List<Product> products;

    /**
     * Constructs a new Order object.
     *
     * @param customer the customer who placed the order
     * @param products the list of products in the order
     */
    public Order(Customer customer, List<Product> products) {
        this.customer = customer;
        this.products = products;
    }

    /**
     * Returns a formatted string of the products in the order.
     *
     * @return a formatted string of the products in the order
     */
    public String printProductsOfOrder() {
        StringBuilder orderDetails = new StringBuilder();
        for (int i = 0; i < products.size(); i++) {
            orderDetails.append("%d -> %s".formatted((i+1), products.get(i)));
        }
        return orderDetails.toString();
    }

    /**
     * Returns the Customer object associated with the order.
     *
     * @return the Customer object associated with the order
     */
    public Customer getCustomer() {
        return customer;
    }
}
