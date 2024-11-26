import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileHandler {

    // Method to create a new file or overwrite an existing one
    public static void createFile(String fileName) {
        File file = new File(fileName);
        
        if (file.exists()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("File already exists. Do you want to overwrite it? (yes/no): ");
            String response = scanner.nextLine().toLowerCase();
            
            if (!response.equals("yes")) {
                System.out.println("File creation aborted.");
                return;
            }
        }

        try {
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            raf.writeBytes("4337PRJ3");  // Magic number
            raf.writeLong(0);            // Root block ID (0 means empty)
            raf.writeLong(0);            // Next block ID (starting at 0)
            byte[] zeroes = new byte[496];  // Remaining space in header block
            raf.write(zeroes);
            raf.close();
            System.out.println("File created successfully: " + fileName);
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    // Method to open an existing index file
    public static boolean openFile(String fileName) {
        File file = new File(fileName);

        // Check if the file exists
        if (!file.exists()) {
            System.out.println("Error: File does not exist.");
            return false;
        }

        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            byte[] magicNumberBytes = new byte[8];
            raf.read(magicNumberBytes);  // Read the magic number

            // Convert to string to check if it matches the expected magic number
            String magicNumber = new String(magicNumberBytes);
            if (!magicNumber.equals("4337PRJ3")) {
                System.out.println("Error: Invalid magic number.");
                return false;
            }

            // If we reach here, the file is valid and has the correct magic number
            System.out.println("File opened successfully: " + fileName);
            return true;  // Return true to indicate the file is open and valid

        } catch (IOException e) {
            System.out.println("Error opening file: " + e.getMessage());
            return false;
        }
    }
}
