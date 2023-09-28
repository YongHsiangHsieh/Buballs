import java.io.Serializable;

enum Flavor {
    PLUM_POWDER, SESAME, CHOCOLATE
}

/**
 * A class representing a sweet potato ball product, which extends the abstract Product class and is Serializable.
 */
public class SweetPotatoBall extends Product implements Serializable {

   private Flavor flavor;

    /**
     * Constructor for creating a new sweet potato ball with the specified size and flavor.
     * @param size the size of the sweet potato ball (large, medium, or small)
     * @param flavor the flavor of the sweet potato ball (plum powder, sesame, or chocolate)
     */
    public SweetPotatoBall(ProductSize size, Flavor flavor) {
        super("SweetPotatoBall", 2, size);
        this.flavor = flavor;
    }

    /**
     * Returns the flavor of this sweet potato ball.
     * @return the flavor of this sweet potato ball
     */
    public Flavor getFlavor() {
        return flavor;
    }

    /**
     * Calculates and returns the price of this sweet potato ball based on its size.
     * @return the price of this sweet potato ball
     */
    @Override
    public double calculatePrice() {
        return switch (getSize()){
            case LARGE -> 2;
            case MEDIUM -> 1.5;
            case SMALL -> 1;
        };
    }

    /**
     * Returns a deep copy of this sweet potato ball.
     * @return a deep copy of this sweet potato ball
     */
    @Override
    public Product clone() {
        return new SweetPotatoBall(getSize(), this.flavor);
    }

    /**
     * Returns a String representation of this sweet potato ball, including its size, name, flavor, and price.
     * @return a String representation of this sweet potato ball
     */
    @Override
    public String toString() {
        return super.toString().formatted(getSize(),getName(),flavor,calculatePrice());
    }

    /**
     * Sets the flavor of this sweet potato ball to the specified flavor.
     * @param flavor the new flavor of this sweet potato ball
     */
    public void setFlavor(Flavor flavor) {
        this.flavor = flavor;
    }
}
