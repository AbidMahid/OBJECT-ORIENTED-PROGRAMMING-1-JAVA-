import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String email;
    private Date enrollmentDate;
    
    public Student(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.enrollmentDate = new Date();
    }
    
    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public Date getEnrollmentDate() { return enrollmentDate; }
    
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return String.format("ID: %d, Name: %s, Email: %s, Enrolled: %s",
            id, name, email, sdf.format(enrollmentDate));
    }
}

class StudentManager {
    private List<Student> students = new ArrayList<>();
    private static final String FILE_NAME = "students.dat";
    
    // Add student
    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student added successfully!");
    }
    
    // Remove student
    public void removeStudent(int id) {
        students.removeIf(s -> s.getId() == id);
        System.out.println("Student removed if existed");
    }
    
    // Search student
    public Student searchStudent(int id) {
        return students.stream()
            .filter(s -> s.getId() == id)
            .findFirst()
            .orElse(null);
    }
    
    // List all students
    public void listAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found");
        } else {
            students.forEach(System.out::println);
        }
    }
    
    // Save to file
    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
            System.out.println("Data saved to file");
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }
    
    // Load from file
    @SuppressWarnings("unchecked")
    public void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            students = (List<Student>) ois.readObject();
            System.out.println("Data loaded from file");
        } catch (FileNotFoundException e) {
            System.out.println("No saved data found");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading: " + e.getMessage());
        }
    }
}

public class StudentManagementSystem {
    private static StudentManager manager = new StudentManager();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        manager.loadFromFile();
        
        while (true) {
            System.out.println("\n=== Student Management System ===");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. List All Students");
            System.out.println("5. Save and Exit");
            System.out.print("Choose option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    removeStudent();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    manager.listAllStudents();
                    break;
                case 5:
                    manager.saveToFile();
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option");
            }
        }
    }
    
    private static void addStudent() {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        
        manager.addStudent(new Student(id, name, email));
    }
    
    private static void removeStudent() {
        System.out.print("Enter ID to remove: ");
        int id = scanner.nextInt();
        manager.removeStudent(id);
    }
    
    private static void searchStudent() {
        System.out.print("Enter ID to search: ");
        int id = scanner.nextInt();
        Student student = manager.searchStudent(id);
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student not found");
        }
    }
}