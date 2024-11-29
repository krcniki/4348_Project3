import java.util.ArrayList;

// Class representing a node in the B-Tree
public class BTreeNode {
    public boolean isLeaf; // Flag to indicate if the node is a leaf
    public ArrayList<Integer> keys; // List of keys stored in the node
    public ArrayList<Integer> values; // List of values corresponding to the keys
    public ArrayList<BTreeNode> children; // List of child nodes (only used for non-leaf nodes)

    // Constructor to initialize a node
    public BTreeNode(boolean isLeaf) {
        this.isLeaf = isLeaf; // Set whether the node is a leaf or not
        this.keys = new ArrayList<>(); // Initialize the keys list
        this.values = new ArrayList<>(); // Initialize the values list
        this.children = new ArrayList<>(); // Initialize the children list (empty by default)
    }
}
