import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command;
        boolean fileOpened = false;
        BTree bTree = null;  // Reference to the BTree instance
        
        System.out.println("Welcome to the Index File Manager!");

        while (true) {
            System.out.println("\nCommands:");
            System.out.println("create  - Create a new index file");
            System.out.println("open    - Open an existing index file");
            System.out.println("insert  - Insert a key-value pair into the index file");
            System.out.println("quit    - Exit the program");
            System.out.print("Enter command: ");
            
            command = scanner.nextLine().toLowerCase();
            
            if (command.equals("create")) {
                System.out.print("Enter the file name to create: ");
                String fileName = scanner.nextLine();
                FileHandler.createFile(fileName);
                fileOpened = false;  // After create, no file is open
                bTree = null;  // No tree is loaded
            } else if (command.equals("open")) {
                System.out.print("Enter the file name to open: ");
                String fileName = scanner.nextLine();
                if (FileHandler.openFile(fileName)) {
                    try {
                        bTree = new BTree(new RandomAccessFile(fileName, "rw"));
                        fileOpened = true;
                    } catch (FileNotFoundException e) {
                        System.out.println("Error: File not found. Please check the file path.");
                    } catch (Exception e) {
                        System.out.println("Error opening file: " + e.getMessage());
                    }
                }
            } else if (command.equals("insert") && fileOpened) {
                System.out.print("Enter key to insert: ");
                long key = Long.parseLong(scanner.nextLine());
                System.out.print("Enter value to insert: ");
                String value = scanner.nextLine();
                bTree.insertKey(key, value);
                System.out.println("Key-value pair inserted.");
            } else if (command.equals("quit")) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Unknown command or no file opened. Please try again.");
            }
        }

        scanner.close();
    }
}
