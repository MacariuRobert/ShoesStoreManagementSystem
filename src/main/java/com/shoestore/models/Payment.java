package com.shoestore.models;
import java.time.LocalDateTime;
public class Payment {
    private Long id;
    private Long orderId;
    private String paymentMethod;
    private LocalDateTime paymentDate;
    private Double amount;
    private String status;
    private String transactionId;
    //default Constructor
    public Payment() {}
    //constructor
    public Payment(Long id, Long orderId, String paymentMethod, LocalDateTime paymentDate, Double amount, String status, String transactionId) {
        this.id = id;
        this.orderId = orderId;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.status = status;
        this.transactionId = transactionId;
    }
    //getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }
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
    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", paymentDate=" + paymentDate +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", transactionId='" + transactionId + '\'' +
                '}';
    }
}
