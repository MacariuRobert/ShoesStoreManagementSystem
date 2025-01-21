
import java.time.LocalDateTime;

public class Review {
    private Long id;
    private Customer customer;
    private Shoe shoe;
    private Integer rating;
    private String comment;
    private LocalDateTime timestamp;

    //constructor, getters and setters
    public Review(Long id, Customer customer, Shoe shoe, Integer rating, String comment, LocalDateTime timestamp) {
        this.id = id;
        this.customer = customer;
        this.shoe = shoe;
        this.rating = rating;
        this.comment = comment;
        this.timestamp = timestamp;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public Shoe getShoe() { return shoe; }
    public void setShoe(Shoe shoe) { this.shoe = shoe; }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    //method
    public boolean isVerifiedPurchase() {
        return true;
    }
}
