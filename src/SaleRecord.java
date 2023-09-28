import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * A record representing a sale made in the ordering system.
 */
public record SaleRecord(List<Product> products, int quantity, LocalDate currentDate, double totalPrice, int ID) implements Serializable {

    /**
     * Returns a string representation of the sale record.
     *
     * @return a formatted string with the ID, date, quantity, total price, and list of products in the sale record
     */
    @Override
    public String toString() {
        return "ID:%d -> %s, %d(Quantity), $%.2f(Price): %n%s".formatted(ID(),currentDate(),quantity(),totalPrice(),getProductList());
    }

    /**
     * Returns a string representation of the list of products in the sale record.
     *
     * @return a formatted string with each product in the list of products, indented with a tab character
     */
    private String getProductList(){
        StringBuilder list = new StringBuilder();
        for (var e : products){
            list.append("\t").append(e);
        }
        return list.toString();
    }
}
