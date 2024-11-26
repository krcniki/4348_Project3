import java.io.IOException;
import java.io.RandomAccessFile;

public class BTree {
    private RandomAccessFile file;
    private long rootNodeId;
    private long nextNodeId;

    public BTree(RandomAccessFile file) {
        this.file = file;
        this.rootNodeId = 0;
        this.nextNodeId = 1;
    }

    public void insertKey(long key, String value) {
        try {
            // Start insertion at the root
            Node rootNode = getNode(rootNodeId);
            if (rootNode.numKeys == 19) {
                // If root is full, split it
                Node newRoot = new Node(nextNodeId++, 0, new long[19], new String[19]);
                newRoot.numKeys = 1;
                newRoot.keys[0] = rootNode.keys[9];  // Middle key
                newRoot.values[0] = rootNode.values[9];
                newRoot.childPointers[0] = rootNode.nodeId;
                rootNode.parentNodeId = newRoot.nodeId;

                // Split the original root node
                splitNode(rootNode, newRoot);
                writeNode(newRoot);
                rootNodeId = newRoot.nodeId;
            } else {
                insertIntoNode(rootNode, key, value);
            }
        } catch (IOException e) {
            System.out.println("Error during insertion: " + e.getMessage());
        }
    }

    // Insert key-value pair into the node
    private void insertIntoNode(Node node, long key, String value) throws IOException {
        int i = (int) (node.numKeys - 1);  // Typecast long to int here
        while (i >= 0 && node.keys[i] > key) {
            node.keys[i + 1] = node.keys[i];
            node.values[i + 1] = node.values[i];
            i--;
        }
        node.keys[i + 1] = key;
        node.values[i + 1] = value;
        node.numKeys++;

        writeNode(node);
    }

    // Method to split a node when it is full
    private void splitNode(Node node, Node newRoot) throws IOException {
        int midIndex = (int) (node.numKeys / 2);  // Typecast long to int here
        long midKey = node.keys[midIndex];
        String midValue = node.values[midIndex];

        // Create a new node for the right side of the split
        Node newNode = new Node(nextNodeId++, node.parentNodeId, new long[19], new String[19]);
        newNode.numKeys = (int) (node.numKeys - midIndex - 1);  // Typecast long to int here

        // Copy keys and values to the new node
        for (int i = 0; i < newNode.numKeys; i++) {
            newNode.keys[i] = node.keys[midIndex + 1 + i];
            newNode.values[i] = node.values[midIndex + 1 + i];
        }

        // Adjust the original node
        node.numKeys = midIndex;

        // Write both nodes to the file
        writeNode(node);
        writeNode(newNode);

        // Promote the middle key to the parent node
        if (node.parentNodeId == 0) {
            // If the node is the root, we need to create a new root
            newRoot.numKeys = 1;
            newRoot.keys[0] = midKey;
            newRoot.values[0] = midValue;
            newRoot.childPointers[0] = node.nodeId;
            newRoot.childPointers[1] = newNode.nodeId;

            node.parentNodeId = newRoot.nodeId;
            newNode.parentNodeId = newRoot.nodeId;

            writeNode(newRoot);
        } else {
            // Insert the middle key into the parent node (not implemented yet)
            // This would be handled in a more complete implementation of the B-tree insert logic
        }
    }

    // Retrieve a node by its ID from the file
    private Node getNode(long nodeId) throws IOException {
        file.seek(nodeId * 512);
        long id = file.readLong();
        long parentId = file.readLong();
        long numKeys = file.readLong();
        long[] keys = new long[19];
        String[] values = new String[19];

        for (int i = 0; i < 19; i++) {
            keys[i] = file.readLong();
            values[i] = file.readUTF();
        }

        Node node = new Node(id, parentId, keys, values);
        node.numKeys = (int) numKeys;  // Typecast long to int here
        return node;
    }

    // Write a node back to the file
    private void writeNode(Node node) throws IOException {
        file.seek(node.nodeId * 512);
        file.writeLong(node.nodeId);
        file.writeLong(node.parentNodeId);
        file.writeLong(node.numKeys);

        for (int i = 0; i < 19; i++) {
            file.writeLong(node.keys[i]);
            file.writeUTF(node.values[i]);
        }
    }

    // Node class representing a node in the B-tree
    private class Node {
        long nodeId;
        long parentNodeId;
        long numKeys;
        long[] keys = new long[19];
        String[] values = new String[19];
        long[] childPointers = new long[20];  // 20 child pointers

        public Node(long nodeId, long parentNodeId, long[] keys, String[] values) {
            this.nodeId = nodeId;
            this.parentNodeId = parentNodeId;
            this.numKeys = 0;
            this.keys = keys;
            this.values = values;
            this.childPointers = new long[20];
        }
    }
}
