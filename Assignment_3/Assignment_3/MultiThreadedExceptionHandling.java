import java.util.Random;
import java.util.Scanner;

class Task implements Runnable {
    private String taskName;
    
    public Task(String taskName) {
        this.taskName = taskName;
    }
    
    @Override
    public void run() {
        try {
            Random rand = new Random();
            int duration = rand.nextInt(5000); // Random sleep time up to 5 seconds
            System.out.println(taskName + " is running...");
            Thread.sleep(duration);
            
            if (rand.nextBoolean()) { // Randomly throw an exception
                throw new RuntimeException("Exception occurred in " + taskName);
            }
            
            System.out.println(taskName + " completed successfully.");
        } catch (InterruptedException e) {
            System.out.println(taskName + " was interrupted.");
        } catch (RuntimeException e) {
            System.out.println("Error in " + taskName + ": " + e.getMessage());
        }
    }
}

public class MultiThreadedExceptionHandling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of tasks to run: ");
        int numTasks = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        Thread[] threads = new Thread[numTasks];
        
        for (int i = 0; i < numTasks; i++) {
            System.out.print("Enter name for task " + (i + 1) + ": ");
            String taskName = scanner.nextLine();
            threads[i] = new Thread(new Task(taskName));
        }
        
        for (Thread thread : threads) {
            thread.start();
        }
        
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Main thread interrupted.");
            }
        }
        
        System.out.println("All tasks completed.");
        scanner.close();
    }
}