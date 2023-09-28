import java.io.Serializable;

/**
 * Enum to represent the different membership statuses.
 */
enum MembershipStatus {
    BUBBLE, BOBA;
}

/**
 * Interface for Membership that includes a method for calculating discount rates.
 */
interface Membership {
    double getDiscountRate();
}

/**
 * Represents a Customer with a membership status, implementing the Membership interface and Serializable.
 */
public class Customer implements Membership, Serializable {
    private final String name;
    private String email;
    private MembershipStatus membershipStatus;
    private final int ID;

    /**
     * Constructor for the Customer class.
     *
     * @param name             the name of the customer
     * @param email            the email of the customer
     * @param membershipStatus the membership status of the customer
     * @param ID               the ID of the customer
     */
    public Customer(String name, String email, MembershipStatus membershipStatus, int ID) {
        this.name = name;
        this.email = email;
        this.membershipStatus = membershipStatus;
        this.ID = ID;
    }

    /**
     * Retrieves the discount rate based on the customer's membership status.
     *
     * @return the discount rate for the customer
     */
    @Override
    public double getDiscountRate() {
        return switch (membershipStatus) {
            case BUBBLE -> 0.9;
            case BOBA -> 0.8;
        };
    }

    /**
     * Getter for the membershipStatus.
     *
     * @return the membership status of the customer
     */
    public MembershipStatus getMembershipStatus() {
        return membershipStatus;
    }

    /**
     * Setter for the membershipStatus.
     *
     * @param membershipStatus the membership status to set for the customer
     */
    public void setMembershipStatus(MembershipStatus membershipStatus) {
        this.membershipStatus = membershipStatus;
    }

    /**
     * Returns a string representation of the Customer object.
     *
     * @return a formatted string with the customer's name, ID, email, and membership status
     */
    @Override
    public String toString() {
        return "Customer %s, ID: %d, Email: %s, Membership status: %s".formatted(name, ID, email, membershipStatus.name());
    }

    /**
     * Getter for the name.
     *
     * @return the name of the customer
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the ID.
     *
     * @return the ID of the customer
     */
    public int getID() {
        return ID;
    }

    /**
     * Setter for the email.
     *
     * @param email the email to set for the customer
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
