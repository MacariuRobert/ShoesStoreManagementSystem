public class Admin {
    private Long id;
    private String username;
    private String password;

    //constructor, getters and setters
    public Admin(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    //methods
    public void manageInventory(Shoe shoe) {
        System.out.println("Managing inventory for shoe: " + shoe.getName());
    }

    public void generateReports() {
        System.out.println("Generating reports...");
    }
}
