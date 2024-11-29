import java.util.ArrayList;

// Class representing a block in the B-Tree
public class BTreeBlock {
    public byte[] blockData; // Raw data of the block (not used directly in this example)
    private ArrayList<Long> keys; // List of keys in the block
    private ArrayList<String> values; // List of corresponding values

    // Constructor to initialize a block with raw data
    public BTreeBlock(byte[] blockData) {
        this.blockData = blockData;
        this.keys = new ArrayList<>(); // Initialize the keys list
        this.values = new ArrayList<>(); // Initialize the values list
        // Initialization from blockData would typically parse and populate keys and values
    }

    // Method to insert a key-value pair into the block
    public void insertKey(long key, String value) {
        int index = findInsertIndex(key); // Find the correct position for the key
        keys.add(index, key); // Insert the key at the calculated index
        values.add(index, value); // Insert the corresponding value at the same index
    }

    // Method to check if the block is full
    public boolean isFull() {
        return keys.size() >= 3; // Assuming a maximum of 3 keys per block
    }

    // Private helper method to find the correct index to insert the key
    private int findInsertIndex(long key) {
        int low = 0, high = keys.size() - 1;
        while (low <= high) { // Binary search to find the correct position
            int mid = (low + high) / 2;
            if (keys.get(mid) < key) {
                low = mid + 1; // Narrow search to the upper half
            } else if (keys.get(mid) > key) {
                high = mid - 1; // Narrow search to the lower half
            } else {
                return mid; // Key already exists; return the current index
            }
        }
        return low; // Return the position where the key should be inserted
    }

    // Method to check if the block is a leaf node
    public boolean isLeaf() {
        return true; // Simplification: Assume all blocks are leaf nodes for now
    }

    // Method to get the child block ID for internal nodes (not used in this implementation)
    public long getChildBlockID(long key) {
        return 0; // Placeholder for actual child block retrieval logic
    }
}
