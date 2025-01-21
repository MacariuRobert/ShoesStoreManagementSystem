import java.time.LocalDateTime;

public class Payment {
    private Long id;
    private Order order;
    private String paymentMethod;
    private LocalDateTime paymentDate;
    private Double amount;
    private String status;
    private String transactionId;

    //constructor, getters and setters
    public Payment(Long id, Order order, String paymentMethod, LocalDateTime paymentDate, Double amount, String status, String transactionId) {
        this.id = id;
        this.order = order;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.status = status;
        this.transactionId = transactionId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public LocalDateTime getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDateTime paymentDate) { this.paymentDate = paymentDate; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }

    //methods
    public void validatePayment() {
        System.out.println("Validating payment...");
    }

    public void processRefund() {
        System.out.println("Processing refund...");
    }

    public void updateStatus(String status) {
        this.status = status;
    }
}
