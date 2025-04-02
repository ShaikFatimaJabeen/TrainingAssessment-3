import java.io.*;
import java.util.Scanner;

class FileProcessingException extends Exception {
    public FileProcessingException(String message) {
        super(message);
    }
}

public class FileProcessor {
    public static void readFile(String filePath) throws FileProcessingException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            System.out.println("File Content:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            throw new FileProcessingException("Error: File not found at " + filePath);
        } catch (IOException e) {
            throw new FileProcessingException("Error reading file: " + e.getMessage());
        }
    }

    public static void writeFile(String filePath, String content) throws FileProcessingException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(content);
            writer.newLine();
            System.out.println("Content written to file successfully.");
        } catch (IOException e) {
            throw new FileProcessingException("Error writing to file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter file path: ");
        String filePath = scanner.nextLine();
        
        System.out.println("Choose an option:\n1. Read File\n2. Write to File");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            if (choice == 1) {
                readFile(filePath);
            } else if (choice == 2) {
                System.out.print("Enter content to write: ");
                String content = scanner.nextLine();
                writeFile(filePath, content);
            } else {
                System.out.println("Invalid choice.");
            }
        } catch (FileProcessingException e) {
            System.out.println(e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
