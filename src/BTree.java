import java.io.*;

// Class representing a B-Tree
public class BTree {
    private BTreeNode root; // Root node of the B-Tree
    public int degree; // Degree of the B-Tree

    // Constructor to initialize the B-Tree with a specific degree
    public BTree(int degree) {
        this.root = new BTreeNode(true); // Initialize the root as a leaf node
        this.degree = degree;
    }

    // Method to insert a key-value pair into the B-Tree
    public void insert(int key, int value) {
        System.out.println("Inserting key: " + key + ", value: " + value);
        if (root.keys.contains(key)) { // Check for duplicate keys
            System.out.println("Error: Key " + key + " already exists.");
            return;
        }
        root.keys.add(key); // Add the key to the root node
        root.values.add(value); // Add the value to the root node
    }

    // Method to search for a key in the B-Tree
    public void search(int key) {
        System.out.println("Searching for key: " + key);
        if (root.keys.contains(key)) { // Check if the key exists in the root node
            int index = root.keys.indexOf(key); // Get the index of the key
            System.out.println("Key found: " + key + ", Value: " + root.values.get(index));
        } else {
            System.out.println("Key " + key + " not found."); // Key not found
        }
    }

    // Method to print the contents of the B-Tree
    public void printTree() {
        System.out.println("B-Tree contents:");
        for (int i = 0; i < root.keys.size(); i++) { // Iterate through all keys and values
            System.out.println("Key: " + root.keys.get(i) + ", Value: " + root.values.get(i));
        }
    }

    // Method to extract the contents of the B-Tree to a file
    public void extractToFile(String filename) {
        System.out.println("Extracting B-Tree contents to " + filename + "...");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < root.keys.size(); i++) { // Write each key-value pair to the file
                writer.write(root.keys.get(i) + "," + root.values.get(i));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage()); // Handle file writing errors
        }
    }

    // Method to load key-value pairs from a file into the B-Tree
    public void loadFromFile(String filename) {
        System.out.println("Loading key/value pairs from " + filename + "...");
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 2) {
                    System.out.println("Skipping invalid line: " + line);
                    continue; // Skip lines that don't have exactly 2 parts
                }
                try {
                    int key = Integer.parseInt(parts[0]);
                    int value = Integer.parseInt(parts[1]);
                    insert(key, value); // Insert the valid key-value pair
                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid key/value pair: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File " + filename + " not found.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    
}
