public class OrderItem {
    private Long id;
    private Shoe shoe;
    private Integer size;
    private Integer quantity;
    private Double price;

    //constructor, getters and setters
    public OrderItem(Long id, Shoe shoe, Integer size, Integer quantity, Double price) {
        this.id = id;
        this.shoe = shoe;
        this.size = size;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Shoe getShoe() { return shoe; }
    public void setShoe(Shoe shoe) { this.shoe = shoe; }

    public Integer getSize() { return size; }
    public void setSize(Integer size) { this.size = size; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    //method
    public Double calculateSubtotal() {
        return this.price * this.quantity;
    }
}
