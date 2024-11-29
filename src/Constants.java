// Class to define constants used across the B-Tree implementation
public class Constants {
    // A unique identifier for files created or managed by the B-Tree (magic number)
    public static final byte[] MAGIC_NUMBER = "4337PRJ3".getBytes();
    
    // The size of a block in bytes, used for file storage and management
    public static final int BLOCK_SIZE = 512;
    
    // The minimum degree of the B-Tree, determining its branching factor
    public static final int MIN_DEGREE = 10;
}
