import java.util.List;

public class Order {
    private Long id;
    private Customer customer;
    private List<OrderItem> orderItems;
    private Double totalPrice;
    private String status;
    private TrackingDetails trackingDetails;

    //constructor, getters and setters
    public Order(Long id, Customer customer, List<OrderItem> orderItems, String status, TrackingDetails trackingDetails) {
        this.id = id;
        this.customer = customer;
        this.orderItems = orderItems;
        this.status = status;
        this.trackingDetails = trackingDetails;
        calculateTotal();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public List<OrderItem> getOrderItems() { return orderItems; }
    public void setOrderItems(List<OrderItem> orderItems) { this.orderItems = orderItems; }

    public Double getTotalPrice() { return totalPrice; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public TrackingDetails getTrackingDetails() { return trackingDetails; }
    public void setTrackingDetails(TrackingDetails trackingDetails) { this.trackingDetails = trackingDetails; }

    //methods
    public void calculateTotal() {
        this.totalPrice = orderItems.stream()
                .mapToDouble(OrderItem::calculateSubtotal)
                .sum();
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }
}
