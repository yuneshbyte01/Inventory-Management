import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

// ProductManager class to manage product operations
// This class handles adding, updating, deleting, viewing, and searching products.
// It also manages the loading and saving of product data to a file.
public class ProductManager {

    private final ArrayList<Product> productList; // List to store products
    private static final String FILE_NAME = "src/product.txt"; // File to store product data

    public ProductManager() {
        productList = new ArrayList<>();
        loadProductsFromFile();
    }

    // Method to generate a unique product ID
    public int generateUniqueId() {
        int lastId = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 0) {
                    int id = Integer.parseInt(data[0]);
                    lastId = Math.max(lastId, id);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No product file found. Starting fresh.");
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading ID from file: " + e.getMessage());
        }

        for (Product product : productList) {
            lastId = Math.max(lastId, product.getId());
        }

        return lastId + 1;
    }

    // Method to add a new product
    public void addProduct(String name, double price, int stock) {
        int productID = generateUniqueId();
        Product newProduct = new Product(productID, name, price, stock);
        productList.add(newProduct);
        saveProductsToFile();
        System.out.println("Product added successfully!\n");
    }

    // Method to update an existing product
    public void updateProduct(int id, String name, double price, int stock) {
        for (Product product : productList) {
            if (product.getId() == id) {
                product.setName(name);
                product.setPrice(price);
                product.setStock(stock);
                saveProductsToFile();
                System.out.println("Product updated successfully!");
                return;
            }
        }
        System.out.println("Product not found!");
    }

    // Method to delete a product
    public void deleteProduct(int id) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == id) {
                productList.remove(i);
                saveProductsToFile();
                System.out.println("Product deleted successfully!");
                return;
            }
        }
        System.out.println("Product not found!");
    }

    // Method to view a product by ID
    public void viewProduct(int id) {
        System.out.println("Product Details:");
        System.out.println("------------------------------\n");
        for (Product product : productList) {
            if (product.getId() == id) {
                product.displayProduct();
                return;
            }
        }
        System.out.println("Product not found!");
    }

    // Method to view all products
    public void viewAllProducts() {
        System.out.println("All Products:");
        System.out.println("------------------------------\n");
        if (productList.isEmpty()) {
            System.out.println("No products available.");
        } else {
            for (Product product : productList) {
                product.displayProduct();
            }
        }
    }

    // Method to search for a product by name
    public void searchProductByName(String name) {
        boolean found = false;
        for (Product product : productList) {
            if (product.getName().equalsIgnoreCase(name)) {
                product.displayProduct();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Product not found!");
        }
    }

    // Method to search for a product by ID
    public void searchProductById(int id) {
        for (Product product : productList) {
            if (product.getId() == id) {
                product.displayProduct();
                return;
            }
        }
        System.out.println("Product not found!");
    }

    // Method to sort products by price
    public void sortProductsByPrice(boolean ascending) {
        productList.sort((p1, p2) -> {
            if (ascending) {
                return Double.compare(p1.getPrice(), p2.getPrice());
            } else {
                return Double.compare(p2.getPrice(), p1.getPrice());
            }
        });
        System.out.println("Products sorted by price " + (ascending ? "ascending" : "descending") + " order.");
    }

    // Method to save products to a file
    public void saveProductsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Product product : productList) {
                writer.write(product.getId() + "," + product.getName() + "," + product.getPrice() + "," + product.getStock());
                writer.newLine();
            }
            System.out.println("Products saved to file successfully!");
        } catch (Exception e) {
            System.out.println("Error saving products to file: " + e.getMessage());
        }
    }

    // Method to load products from a file
    public void loadProductsFromFile() {
        Path path = Paths.get(FILE_NAME);
        if (!Files.exists(path)) {
            System.out.println("Product file does not exist. Starting with empty list.");
            return;
        }

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    int stock = Integer.parseInt(parts[3]);
                    productList.add(new Product(id, name, price, stock));
                }
            }
            System.out.println("Products loaded from file successfully!");
        } catch (Exception e) {
            System.out.println("Error loading products from file: " + e.getMessage());
        }
    }
}
