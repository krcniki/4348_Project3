
import java.io.*;

public class Main {
    private static void printMenu() {
        System.out.println("Commands:");
        System.out.println("create - Create a new index file");
        System.out.println("open   - Open an existing index file");
        System.out.println("insert - Insert a key/value pair into the index");
        System.out.println("search - Search for a key in the index");
        System.out.println("load   - Load key/value pairs from a file");
        System.out.println("print  - Print all key/value pairs in the index");
        System.out.println("extract - Extract all key/value pairs to a file");
        System.out.println("quit   - Exit the program");
    }

    public static void main(String[] args) {
        BTree currentBTree = null;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                printMenu();
                System.out.print("Enter command: ");
                String command = reader.readLine().trim().toLowerCase();

                switch (command) {
                    case "create":
                        System.out.print("Enter filename: ");
                        String createFileName = reader.readLine();
                        if (FileManager.createIndexFile(createFileName)) {
                            currentBTree = new BTree(Constants.MIN_DEGREE);
                        }
                        break;
                    case "open":
                        System.out.print("Enter filename: ");
                        String openFileName = reader.readLine();
                        if (FileManager.openIndexFile(openFileName)) {
                            currentBTree = new BTree(Constants.MIN_DEGREE);
                        }
                        break;
                    case "insert":
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
                        if (currentBTree != null) {
                            System.out.print("Enter key: ");
                            int searchKey = Integer.parseInt(reader.readLine());
                            currentBTree.search(searchKey);
                        } else {
                            System.out.println("No index file is open.");
                        }
                        break;
                    case "load":
                        if (currentBTree != null) {
                            System.out.print("Enter filename to load: ");
                            String loadFileName = reader.readLine();
                            currentBTree.loadFromFile(loadFileName);
                        } else {
                            System.out.println("No index file is open.");
                        }
                        break;
                    case "print":
                        if (currentBTree != null) {
                            currentBTree.printTree();
                        } else {
                            System.out.println("No index file is open.");
                        }
                        break;
                    case "extract":
                        if (currentBTree != null) {
                            System.out.print("Enter filename to extract to: ");
                            String extractFileName = reader.readLine();
                            currentBTree.extractToFile(extractFileName);
                        } else {
                            System.out.println("No index file is open.");
                        }
                        break;
                    case "quit":
                        System.out.println("Program exited.");
                        return;
                    default:
                        System.out.println("Invalid command.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading input: " + e.getMessage());
        }
    }
}
