import java.util.Scanner;

public class InventoryManagementSystem {

    public static void main(String[] args) {
        System.out.println("Welcome to the Inventory Management System!");

        ProductManager productManager = new ProductManager();
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Delete Product");
            System.out.println("4. View Product");
            System.out.println("5. View All Products");
            System.out.println("6. Search Product by Name");
            System.out.println("7. Search Product by ID");
            System.out.println("8. Sort Product by Price");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> addProduct(productManager, scanner);
                case 2 -> updateProduct(productManager, scanner);
                case 3 -> deleteProduct(productManager, scanner);
                case 4 -> viewProduct(productManager, scanner);
                case 5 -> viewAllProducts(productManager);
                case 6 -> searchProductByName(productManager, scanner);
                case 7 -> searchProductById(productManager, scanner);
                case 8 -> sortProductByPrice(productManager, scanner);
                case 0 -> System.out.println("Exiting the system...");
                default -> System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 8);
    }

    public static void addProduct(ProductManager productManager, Scanner scanner) {
        System.out.print("Enter product name: ");
        String name = scanner.next();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter product stock: ");
        int stock = scanner.nextInt();

        productManager.addProduct(name, price, stock);
    }

    public static void updateProduct(ProductManager productManager, Scanner scanner) {
        System.out.print("Enter product ID to update: ");
        int id = scanner.nextInt();
        System.out.print("Enter new product name: ");
        String name = scanner.next();
        System.out.print("Enter new product price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter new product stock: ");
        int stock = scanner.nextInt();

        productManager.updateProduct(id, name, price, stock);
    }

    public static void deleteProduct(ProductManager productManager, Scanner scanner) {
        System.out.print("Enter product ID to delete: ");
        int id = scanner.nextInt();

        productManager.deleteProduct(id);
    }

    public static void viewProduct(ProductManager productManager, Scanner scanner) {
        System.out.print("Enter product ID to view: ");
        int id = scanner.nextInt();

        productManager.viewProduct(id);
    }

    public static void viewAllProducts(ProductManager productManager) {
        productManager.viewAllProducts();
    }

    public static void searchProductByName(ProductManager productManager, Scanner scanner) {
        System.out.print("Enter product name to search: ");
        String name = scanner.next();

        productManager.searchProductByName(name);
    }

    public static void searchProductById(ProductManager productManager, Scanner scanner) {
        System.out.print("Enter product ID to search: ");
        int id = scanner.nextInt();

        productManager.searchProductById(id);
    }

    public static void sortProductByPrice(ProductManager productManager, Scanner scanner) {
        boolean ascending;
        System.out.print("Sort in ascending order? || Sort in descending order? (a/d) : ");
        char order = scanner.next().charAt(0);
        if (order == 'a' || order == 'A') {
            ascending = true;
        } else if (order == 'd' || order == 'D') {
            ascending = false;
        } else {
            System.out.println("Invalid choice! Defaulting to ascending order.");
            ascending = true;
        }
        productManager.sortProductsByPrice(ascending);
    }
}