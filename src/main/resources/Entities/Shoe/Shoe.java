import java.util.List;
import java.util.Map;

public class Shoe {
    private Long id;
    private String name;
    private String brand;
    private Double price;
    private List<Integer> sizeOptions;
    private Map<Integer, Integer> stock;
    private String category;

    //constructor, getters and setters
    public Shoe(Long id, String name, String brand, Double price, List<Integer> sizeOptions, Map<Integer, Integer> stock, String category) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.sizeOptions = sizeOptions;
        this.stock = stock;
        this.category = category;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public List<Integer> getSizeOptions() { return sizeOptions; }
    public void setSizeOptions(List<Integer> sizeOptions) { this.sizeOptions = sizeOptions; }

    public Map<Integer, Integer> getStock() { return stock; }
    public void setStock(Map<Integer, Integer> stock) { this.stock = stock; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    //methods
    public void updateStock(Integer size, Integer quantity) {
        stock.put(size, quantity);
    }

    public void applyDiscount(Double discount) {
        if (discount > 0 && discount <= this.price) {
            this.price -= discount;
        }
    }
}
