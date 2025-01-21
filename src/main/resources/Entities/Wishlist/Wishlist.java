import java.time.LocalDate;

public class Wishlist {
    private Long id;
    private Customer customer;
    private Shoe shoe;
    private LocalDate addedDate;

    //constructor, getters and setters
    public Wishlist(Long id, Customer customer, Shoe shoe, LocalDate addedDate) {
        this.id = id;
        this.customer = customer;
        this.shoe = shoe;
        this.addedDate = addedDate;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public Shoe getShoe() { return shoe; }
    public void setShoe(Shoe shoe) { this.shoe = shoe; }

    public LocalDate getAddedDate() { return addedDate; }
    public void setAddedDate(LocalDate addedDate) { this.addedDate = addedDate; }
}
