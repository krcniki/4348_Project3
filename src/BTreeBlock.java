import java.nio.ByteBuffer;
import java.util.ArrayList;

public class BTreeBlock {
    private byte[] blockData;
    private ArrayList<Long> keys;
    private ArrayList<String> values;
    
    public BTreeBlock(byte[] blockData) {
        this.blockData = blockData;
        this.keys = new ArrayList<>();
        this.values = new ArrayList<>();
        // Initialize keys and values from blockData
    }

    // Method to insert a key-value pair into the block
    public void insertKey(long key, String value) {
        int index = findInsertIndex(key);
        keys.add(index, key);
        values.add(index, value);
    }

    // Method to check if the block is full (max 3 keys per block in this case)
    public boolean isFull() {
        return keys.size() >= 3;  // Assuming each block can hold 3 keys max
    }

    // Method to find the correct index to insert the key
    private int findInsertIndex(long key) {
        int low = 0, high = keys.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (keys.get(mid) < key) {
                low = mid + 1;
            } else if (keys.get(mid) > key) {
                high = mid - 1;
            } else {
                return mid;  // Key already exists, insert at this index
            }
        }
        return low;
    }

    // Method to check if the block is a leaf node (for simplicity, assume true here)
    public boolean isLeaf() {
        return true;  // Simplification: Assume all blocks are leaf nodes for now
    }

    // Get child block ID for internal nodes (not needed for this block, so return 0)
    public long getChildBlockID(long key) {
        return 0;  // Placeholder for actual child block retrieval logic
    }
}
