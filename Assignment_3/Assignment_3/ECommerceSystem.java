// 3. Multi-Catch Block: E-Commerce System
import java.util.InputMismatchException;
import java.util.Scanner;

class InvalidProductException extends Exception {
    public InvalidProductException(String message) {
        super(message);
    }
}

public class ECommerceSystem {
    public static void processOrder(String product, int quantity) throws InvalidProductException {
        if (product == null || product.isEmpty()) {
            throw new InvalidProductException("Invalid product name!");
        }
        if (quantity <= 0) {
            throw new InvalidProductException("Quantity must be greater than zero!");
        }
        System.out.println("Order placed successfully: " + quantity + " x " + product);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.print("Enter product name: ");
            String product = scanner.nextLine();
            
            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();
            
            processOrder(product, quantity);
        } catch (InvalidProductException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input! Please enter a valid quantity.");
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        } finally {
            scanner.close();
            System.out.println("Thank you for shopping with us!");
        }
    }
}
