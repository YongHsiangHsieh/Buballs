import java.io.Serializable;

/**
 * Enum to represent the different types of tea.
 */
enum TeaType {
    GREEN_TEA, BLACK_TEA
}

/**
 * Represents a BubbleTea product that extends the Product class and is serializable.
 */
public class BubbleTea extends Product implements Serializable {
    private TeaType teaType;

    /**
     * Constructor for the BubbleTea class.
     *
     * @param size    the size of the bubble tea
     * @param teaType the type of tea for the bubble tea
     */
    public BubbleTea(ProductSize size, TeaType teaType) {
        super("BubbleTea", 4.5, size);
        this.teaType = teaType;
    }

    /**
     * Getter for the teaType.
     *
     * @return the type of tea for the bubble tea
     */
    public TeaType getTeaType() {
        return teaType;
    }

    /**
     * Calculates the price of the bubble tea based on its size.
     *
     * @return the price of the bubble tea
     */
    @Override
    public double calculatePrice() {
        return switch (getSize()) {
            case LARGE -> 4.5;
            case MEDIUM -> 4;
            case SMALL -> 3;
        };
    }

    /**
     * Returns a string representation of the BubbleTea object.
     *
     * @return a formatted string with size, name, tea type, and price
     */
    @Override
    public String toString() {
        return super.toString().formatted(getSize(), getName(), teaType, calculatePrice());
    }

    /**
     * Setter for the teaType.
     *
     * @param teaType the type of tea to set for the bubble tea
     */
    public void setTeaType(TeaType teaType) {
        this.teaType = teaType;
    }

    /**
     * Clones the current BubbleTea object.
     *
     * @return a new BubbleTea object with the same size and tea type
     */
    @Override
    public BubbleTea clone() {
        return new BubbleTea(getSize(), this.teaType);
    }
}
