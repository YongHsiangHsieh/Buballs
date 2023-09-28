import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the ordering system for a store, allowing customers to order products,
 * modify orders, and complete the checkout process.
 */
public class OrderingSystem {
    private final List<Product> orderList = new ArrayList<>();

    /**
     * Constructs a new OrderingSystem object.
     */
    public OrderingSystem() {
    }

    /**
     * Returns true if the order list is empty, otherwise false.
     *
     * @return true if the order list is empty, otherwise false
     */
    public boolean isOrderListEmpty() {
        return orderList.isEmpty();
    }

    /**
     * Adds a product to the order list based on the given product code.
     *
     * @param product the product code (1 for BubbleTea, 2 for SweetPotatoBall)
     */
    public void order(int product) {
        switch (product) {
            case 1 -> {
                orderList.add(new BubbleTea(getSize(), getTeaType()));
            }
            case 2 -> {
                orderList.add(new SweetPotatoBall(getSize(), getFlavor()));
            }
            default -> PrintUtility.errorMessage();
        }
    }

    /**
     * Lists the products in the current order.
     */
    public void listOrder() {
        for (int i = 0; i < orderList.size(); i++) {
            System.out.print((i + 1) + " -> " + orderList.get(i));
        }
    }

    /**
     * Allows the user to modify the current order.
     */
    public void modifyOrder() {
        listOrder();
        System.out.println("Type in the index to modify (by number)");
        modifyOrderList(EasyScanner.getIndexFromLists(orderList.size()));
    }

    /**
     * Modifies the order list based on the given index.
     *
     * @param index the index of the order list to modify
     */
    private void modifyOrderList(int index) {
        while (true) {
            System.out.println("""
                    Choose one to do (by number)
                    1 -> Remove
                    2 -> Change size
                    3 -> Change flavor""");
            PrintUtility.printDashes(80);
            int action = EasyScanner.readValidInt();
            switch (action) {
                case 1 -> {
                    orderList.remove(index);
                    return;
                }
                case 2 -> {
                    orderList.get(index).setSize(getSize());
                    return;
                }
                case 3 -> {
                    if (orderList.get(index) instanceof BubbleTea tea) {
                        tea.setTeaType(getTeaType());
                    } else {
                        ((SweetPotatoBall) orderList.get(index)).setFlavor(getFlavor());
                    }
                    return;
                }
                default -> PrintUtility.errorMessage();
            }
        }
    }

    /**
     * Clears the current order list.
     */
    public void clearList() {
        orderList.clear();
    }

    /**
     * Completes the checkout process and displays the total price of the order.
     *
     * @param rate the conversion rate for the total price
     */
    public void checkout(double rate) {
        System.out.println();
        listOrder();
        System.out.print(PrintUtility.waves(100));
        System.out.printf("Total: $%.2f%n", getTotalPrice(rate));
    }

    /**
     * Creates an Order object from the current order list.
     *
     * @param customer the customer placing the order
     * @return an Order object with the current order list
     */
    public Order makeOrder(Customer customer) {
        List<Product> copiedOrderList = new ArrayList<>(orderList.size());
        for (Product product : orderList) {
            copiedOrderList.add(product.clone());
        }
        return new Order(customer, copiedOrderList);
    }

    /**
     * Creates a SaleRecord object from the current order list.
     *
     * @param rate the conversion rate for the total price
     * @return a SaleRecord object with the current order list
     */
    public SaleRecord makeSaleRecord(double rate) {
        List<Product> copiedOrderList = new ArrayList<>(orderList.size());
        for (Product product : orderList) {
            copiedOrderList.add(product.clone());
        }
        return new SaleRecord(copiedOrderList, copiedOrderList.size(), LocalDate.now(), getTotalPrice(rate), PrintUtility.getRandomInteger(10, 1000));
    }


    /**
     * Returns a list of strings representing the ingredients used in the current order list.
     *
     * @return a list of strings representing the ingredients used
     */
    public List<String> ingredientsUsed() {
        List<String> ingredients = new ArrayList<>((orderList.size()) * 2);
        for (var e : orderList) {
            if (e instanceof BubbleTea bubbleTea) {
                ingredients.add("Bubble");
                ingredients.add(bubbleTea.getTeaType().name());
            } else {
                ingredients.add("Sweet Potato");
                ingredients.add(((SweetPotatoBall) e).getFlavor().name());
            }
        }
        return ingredients;
    }

    /**
     * Calculates the total price of the current order list.
     *
     * @param rate the conversion rate for the total price
     * @return the total price of the current order list
     */
    private double getTotalPrice(double rate) {
        double total = 0;
        for (var e : orderList) {
            total += e.calculatePrice();
        }
        return total * rate;
    }

    /**
     * Prompts the user to choose a tea type and returns the selected type.
     *
     * @return the selected TeaType
     */
    private TeaType getTeaType() {
        while (true) {
            System.out.println("""
                    What type of tea would you like (by number)
                    1 -> Black Tea
                    2 -> Green Tea""");
            PrintUtility.printDashes(80);
            int teaType = EasyScanner.readValidInt();
            switch (teaType) {
                case 1 -> {
                    return TeaType.BLACK_TEA;
                }
                case 2 -> {
                    return TeaType.GREEN_TEA;
                }
                default -> PrintUtility.errorMessage();
            }
        }
    }

    /**
     * Prompts the user to choose a product size and returns the selected size.
     *
     * @return the selected ProductSize
     */
    private ProductSize getSize() {
        while (true) {
            System.out.println("""
                    Choose size (by number)
                    1 -> Large
                    2 -> Medium
                    3 -> Small""");
            PrintUtility.printDashes(80);
            int size = EasyScanner.readValidInt();
            switch (size) {
                case 1 -> {
                    return ProductSize.LARGE;
                }
                case 2 -> {
                    return ProductSize.MEDIUM;
                }
                case 3 -> {
                    return ProductSize.SMALL;
                }
                default -> PrintUtility.errorMessage();
            }
        }
    }

    /**
     * Prompts the user to choose a flavor and returns the selected flavor.
     *
     * @return the selected Flavor
     */
    private Flavor getFlavor() {
        while (true) {
            System.out.println("""
                    What flavor would you like (by number)
                    1 -> Plum powder
                    2 -> Sesame
                    3 -> Chocolate""");
            PrintUtility.printDashes(80);
            int flavor = EasyScanner.readValidInt();
            switch (flavor) {
                case 1 -> {
                    return Flavor.PLUM_POWDER;
                }
                case 2 -> {
                    return Flavor.SESAME;
                }
                case 3 -> {
                    return Flavor.CHOCOLATE;
                }
                default -> PrintUtility.errorMessage();
            }
        }
    }
}
