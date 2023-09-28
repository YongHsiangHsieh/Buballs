import java.io.Serializable;

/**
 * An abstract class representing a product. Provides a base for various product types.
 */
public abstract class Product implements Serializable, Cloneable {
    private final String name;
    private final double price;
    private ProductSize size;

    /**
     * Constructs a new Product with the given name, price, and size.
     *
     * @param name  the name of the product
     * @param price the base price of the product
     * @param size  the size of the product
     */
    public Product(String name, double price, ProductSize size) {
        this.name = name;
        this.price = price;
        this.size = size;
    }

    /**
     * Calculates the final price of the product based on its size and other properties.
     *
     * @return the final price of the product
     */
    public abstract double calculatePrice();

    /**
     * Creates a deep copy of the current product.
     *
     * @return a deep copy of the current product
     */
    public abstract Product clone();

    /**
     * Retrieves the name of the product.
     *
     * @return the name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the size of the product.
     *
     * @return the size of the product
     */
    public ProductSize getSize() {
        return size;
    }

    /**
     * Sets the size of the product.
     *
     * @param size the new size of the product
     */
    public void setSize(ProductSize size) {
        this.size = size;
    }

    /**
     * Provides a string representation of the product, including its name, size, and price.
     *
     * @return a string representation of the product
     */
    public String toString() {
        return "%s %s with %s for $%.1f %n";
    }
}
