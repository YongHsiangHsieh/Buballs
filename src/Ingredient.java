import java.io.Serializable;
import java.time.LocalDate;

/**
 * Represents an ingredient used in the food stall.
 * Implements Serializable to allow saving and loading ingredient objects.
 */
public class Ingredient implements Serializable {
    private final String name;
    private final int ID;
    private int quantity;
    private final LocalDate date;

    /**
     * Constructs a new Ingredient object.
     *
     * @param name     the name of the ingredient
     * @param ID       the unique identifier for the ingredient
     * @param quantity the quantity of the ingredient
     * @param date     the date when the ingredient was added
     */
    public Ingredient(String name, int ID, int quantity, LocalDate date) {
        this.name = name;
        this.ID = ID;
        this.quantity = quantity;
        this.date = date;
    }

    /**
     * Returns a string representation of the ingredient.
     *
     * @return a formatted string describing the ingredient
     */

    @Override
    public String toString() {
        return "Stock %d -> %s, %d(Q), %s(D)".formatted(ID,name,quantity,date);
    }

    /**
     * Returns the unique identifier of the ingredient.
     *
     * @return the ID of the ingredient
     */
    public int getID() {
        return ID;
    }

    /**
     * Returns the quantity of the ingredient.
     *
     * @return the quantity of the ingredient
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the ingredient.
     *
     * @param quantity the new quantity of the ingredient
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns the name of the ingredient.
     *
     * @return the name of the ingredient
     */
    public String getName() {
        return name;
    }
}
