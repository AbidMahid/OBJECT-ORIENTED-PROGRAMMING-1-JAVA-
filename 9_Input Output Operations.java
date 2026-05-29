import java.io.*;
import java.util.Scanner;

public class FileIODemo {
    private static final String FILE_NAME = "students.txt";
    
    public static void writeToFile(String data) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(data);
            bw.newLine();
            System.out.println("Data written successfully");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    
    public static void readFromFile() {
        try (FileReader fr = new FileReader(FILE_NAME);
             BufferedReader br = new BufferedReader(fr)) {
            
            String line;
            System.out.println("\nFile Contents:");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    
    public static void copyFile(String source, String destination) {
        try (FileInputStream fis = new FileInputStream(source);
             FileOutputStream fos = new FileOutputStream(destination);
             BufferedInputStream bis = new BufferedInputStream(fis);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            System.out.println("File copied successfully!");
        } catch (IOException e) {
            System.out.println("Error copying file: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter grade: ");
        String grade = scanner.nextLine();
        
        writeToFile("Student: " + name + ", Grade: " + grade);
        
        readFromFile();
        
        copyFile(FILE_NAME, FILE_NAME + ".backup");
        
        scanner.close();
    }
}