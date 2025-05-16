// This class represents a product in the inventory management system.
// It contains attributes like id, name, price, and stock.
// It also provides methods to get and set these attributes, as well as a method to display product details.
public class Product {

    private final int id; // Unique identifier for the product
    private String name; // Name of the product
    private double price; // Price of the product
    private int stock; // Available stock of the product

    // Constructor to initialize a new product with given id, name, price, and stock
    public Product(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    // Getters and Setters for the product attributes
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Product name cannot be null or empty.");
        this.name = name;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative.");
        this.price = price;
    }

    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        if (stock < 0) throw new IllegalArgumentException("Stock cannot be negative.");
        this.stock = stock;
    }

    // Method to display product details
    public void displayProduct() {
        System.out.println("Product ID: " + id);
        System.out.println("Product Name: " + name);
        System.out.println("Product Price: Rs." + price);
        System.out.println("Product Stock: " + stock);
        System.out.println("------------------------------\n");
    }
}
