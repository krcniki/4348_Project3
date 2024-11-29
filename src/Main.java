import java.io.*;

// Main class to serve as the entry point for the B-Tree management program
public class Main {

    // Method to print the menu options to the console
    private static void printMenu() {
        System.out.println("Commands:");
        System.out.println("create  - Create a new index file");
        System.out.println("open    - Open an existing index file");
        System.out.println("insert  - Insert a key/value pair into the index");
        System.out.println("search  - Search for a key in the index");
        System.out.println("load    - Load key/value pairs from a file");
        System.out.println("print   - Print all key/value pairs in the index");
        System.out.println("extract - Extract all key/value pairs to a file");
        System.out.println("quit    - Exit the program");
    }

    // Main method to handle user interactions and B-Tree operations
    public static void main(String[] args) {
        BTree currentBTree = null; // Variable to hold the current B-Tree instance

        // Try-with-resources block for reading user input
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                printMenu(); // Display the menu options
                System.out.print("Enter command: ");
                String command = reader.readLine().trim().toLowerCase(); // Read and normalize user input

                // Handle user commands using a switch statement
                switch (command) {
                    case "create":
                        // Create a new index file
                        System.out.print("Enter filename: ");
                        String createFileName = reader.readLine();
                        if (FileManager.createIndexFile(createFileName)) {
                            currentBTree = new BTree(Constants.MIN_DEGREE); // Initialize a new B-Tree
                        }
                        break;
                    case "open":
                        // Open an existing index file
                        System.out.print("Enter filename: ");
                        String openFileName = reader.readLine();
                        if (FileManager.openIndexFile(openFileName)) {
                            currentBTree = new BTree(Constants.MIN_DEGREE); // Load a new B-Tree instance
                        }
                        break;
                    case "insert":
                        // Insert a key/value pair into the B-Tree
                        if (currentBTree != null) {
                            System.out.print("Enter key: ");
                            int key = Integer.parseInt(reader.readLine());
                            System.out.print("Enter value: ");
                            int value = Integer.parseInt(reader.readLine());
                            currentBTree.insert(key, value);
                        } else {
                            System.out.println("No index file is open.");
                        }
                        break;
                    case "search":
                        // Search for a key in the B-Tree
                        if (currentBTree != null) {
                            System.out.print("Enter key: ");
                            int searchKey = Integer.parseInt(reader.readLine());
                            currentBTree.search(searchKey);
                        } else {
                            System.out.println("No index file is open.");
                        }
                        break;
                    case "load":
                        // Load key/value pairs from a file into the B-Tree
                        if (currentBTree != null) {
                            System.out.print("Enter filename to load: ");
                            String loadFileName = reader.readLine();
                            currentBTree.loadFromFile(loadFileName);
                        } else {
                            System.out.println("No index file is open.");
                        }
                        break;
                    case "print":
                        // Print all key/value pairs in the B-Tree
                        if (currentBTree != null) {
                            currentBTree.printTree();
                        } else {
                            System.out.println("No index file is open.");
                        }
                        break;
                    case "extract":
                        // Extract all key/value pairs from the B-Tree to a file
                        if (currentBTree != null) {
                            System.out.print("Enter filename to extract to: ");
                            String extractFileName = reader.readLine();
                            currentBTree.extractToFile(extractFileName);
                        } else {
                            System.out.println("No index file is open.");
                        }
                        break;
                    case "quit":
                        // Exit the program with a 2-second delay
                        System.out.println("Exiting ...");
                        try {
                            Thread.sleep(2000); // Pause for 2 seconds
                        } catch (InterruptedException e) {
                            System.out.println("Error during exit delay: " + e.getMessage());
                        }
                        System.out.println("Program exited.");
                        return; // End the loop and terminate the program
                    default:
                        // Handle invalid commands
                        System.out.println("Invalid command.");
                }
            }
        } catch (IOException e) {
            // Handle input/output errors gracefully
            System.out.println("Error reading input: " + e.getMessage());
        }
    }
}
