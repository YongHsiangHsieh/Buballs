import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an inventory of ingredients for the food stall.
 * Implements Serializable to allow saving and loading inventory objects.
 */
public class Inventory implements Serializable {
    private final List<Ingredient> inventory = new ArrayList<>();

    /**
     * Constructs a new Inventory object.
     */
    public Inventory() {
    }

    /**
     * Adds an ingredient to the inventory.
     *
     * @param item the ingredient to be added
     */
    public void addStock(Ingredient item) {
        inventory.add(item);
    }

    /**
     * Removes an ingredient from the inventory.
     *
     * @param item the ingredient to be removed
     */
    public void removeStock(Ingredient item) {
        inventory.remove(item);
    }

    /**
     * Displays the current inventory stocktake.
     */
    public void stocktake() {
        for (var item : inventory) {
            System.out.println(item);
        }
    }

    /**
     * Finds an ingredient in the inventory by its ID.
     *
     * @param ID the unique identifier of the ingredient
     * @return the ingredient with the specified ID or null if not found
     */
    public Ingredient findItemByID(int ID) {
        for (var item : inventory) {
            if (ID == item.getID()) {
                return item;
            }
        }
        return null;
    }

    /**
     * Checks if the inventory is empty.
     *
     * @return true if the inventory is empty, false otherwise
     */
    public boolean isInventoryEmpty() {
        return inventory.isEmpty();
    }

    /**
     * Takes an order from the inventory, updating the stock of each ingredient.
     *
     * @param listOfIngredients a list of ingredient names in the order
     */
    public void takeOrderFromInventory(List<String> listOfIngredients) {
        for (var ingredient : listOfIngredients) {
            for (var item : inventory) {
                if (item.getName().equalsIgnoreCase(ingredient)) {
                    int currentQuantity = item.getQuantity();
                    if (currentQuantity == 1) {
                        removeStock(item);
                    } else {
                        item.setQuantity(currentQuantity - 1);
                    }
                }
            }
        }
    }
}
