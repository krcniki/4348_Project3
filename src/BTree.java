
import java.io.*;
import java.util.*;

public class BTree {
    private BTreeNode root;
    private int degree;

    public BTree(int degree) {
        this.root = new BTreeNode(true);
        this.degree = degree;
    }

    public void insert(int key, int value) {
        System.out.println("Inserting key: " + key + ", value: " + value);
        if (root.keys.contains(key)) {
            System.out.println("Error: Key " + key + " already exists.");
            return;
        }
        root.keys.add(key);
        root.values.add(value);
    }

    public void search(int key) {
        System.out.println("Searching for key: " + key);
        if (root.keys.contains(key)) {
            int index = root.keys.indexOf(key);
            System.out.println("Key found: " + key + ", Value: " + root.values.get(index));
        } else {
            System.out.println("Key " + key + " not found.");
        }
    }

    public void printTree() {
        System.out.println("B-Tree contents:");
        for (int i = 0; i < root.keys.size(); i++) {
            System.out.println("Key: " + root.keys.get(i) + ", Value: " + root.values.get(i));
        }
    }

    public void extractToFile(String filename) {
        System.out.println("Extracting B-Tree contents to " + filename + "...");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < root.keys.size(); i++) {
                writer.write(root.keys.get(i) + "," + root.values.get(i));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void loadFromFile(String filename) {
        System.out.println("Loading key/value pairs from " + filename + "...");
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int key = Integer.parseInt(parts[0]);
                int value = Integer.parseInt(parts[1]);
                insert(key, value);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File " + filename + " not found.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
