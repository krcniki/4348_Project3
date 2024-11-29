
import java.io.*;
import java.nio.ByteBuffer;

public class FileManager {
    public static boolean createIndexFile(String filename) {
        File file = new File(filename);
        if (file.exists()) {
            System.out.print(filename + " already exists. Overwrite? (yes/no): ");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                String overwrite = reader.readLine().trim().toLowerCase();
                if (!"yes".equals(overwrite)) {
                    System.out.println("Operation aborted.");
                    return false;
                }
            } catch (IOException e) {
                System.out.println("Error reading input: " + e.getMessage());
                return false;
            }
        }
        try (FileOutputStream fos = new FileOutputStream(file)) {
            ByteBuffer buffer = ByteBuffer.allocate(Constants.BLOCK_SIZE);
            buffer.put(Constants.MAGIC_NUMBER);
            buffer.putLong(0); // Placeholder for start position
            buffer.putLong(1); // Placeholder for end position
            fos.write(buffer.array());
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
            return false;
        }
        System.out.println(filename + " created successfully.");
        return true;
    }

    public static boolean openIndexFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println(filename + " does not exist.");
            return false;
        }
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] header = new byte[Constants.BLOCK_SIZE];
            if (fis.read(header) != Constants.BLOCK_SIZE || 
                !new String(header, 0, 8).equals(new String(Constants.MAGIC_NUMBER))) {
                System.out.println(filename + " is not a valid index file.");
                return false;
            }
        } catch (IOException e) {
            System.out.println("Error opening file: " + e.getMessage());
            return false;
        }
        System.out.println(filename + " opened successfully.");
        return true;
    }
}
