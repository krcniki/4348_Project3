import java.io.*;
import java.nio.ByteBuffer;

// Class to manage file operations for the B-Tree index file
public class FileManager {

    // Method to create an index file with a predefined structure
    public static boolean createIndexFile(String filename) {
        File file = new File(filename);

        // Check if the file already exists
        if (file.exists()) {
            System.out.print(filename + " already exists. Overwrite? (yes/no): ");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                // Prompt user for overwrite confirmation
                String overwrite = reader.readLine().trim().toLowerCase();
                if (!"yes".equals(overwrite)) {
                    System.out.println("Operation aborted.");
                    return false; // Abort operation if user declines
                }
            } catch (IOException e) {
                System.out.println("Error reading input: " + e.getMessage());
                return false;
            }
        }

        // Create the file with a predefined header structure
        try (FileOutputStream fos = new FileOutputStream(file)) {
            // Allocate a buffer of BLOCK_SIZE bytes
            ByteBuffer buffer = ByteBuffer.allocate(Constants.BLOCK_SIZE);

            // Add MAGIC_NUMBER to the buffer to identify the file type
            buffer.put(Constants.MAGIC_NUMBER);

            // Add placeholders for start and end positions (e.g., root positions)
            buffer.putLong(0); // Placeholder for the starting block position
            buffer.putLong(1); // Placeholder for the ending block position

            // Write the buffer to the file
            fos.write(buffer.array());
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
            return false;
        }

        System.out.println(filename + " created successfully.");
        return true; // Return true if the file is created successfully
    }

    // Method to open and validate an index file
    public static boolean openIndexFile(String filename) {
        File file = new File(filename);

        // Check if the file exists
        if (!file.exists()) {
            System.out.println(filename + " does not exist.");
            return false;
        }

        // Open the file and validate its structure
        try (FileInputStream fis = new FileInputStream(file)) {
            // Read the first BLOCK_SIZE bytes (header)
            byte[] header = new byte[Constants.BLOCK_SIZE];
            if (fis.read(header) != Constants.BLOCK_SIZE || 
                !new String(header, 0, 8).equals(new String(Constants.MAGIC_NUMBER))) {
                // Check if the header contains the correct MAGIC_NUMBER
                System.out.println(filename + " is not a valid index file.");
                return false;
            }
        } catch (IOException e) {
            System.out.println("Error opening file: " + e.getMessage());
            return false;
        }

        System.out.println(filename + " opened successfully.");
        return true; // Return true if the file is valid and successfully opened
    }
}
