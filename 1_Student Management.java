class Student {
    private String name;
    private int id;
    private double gpa;
    
    public Student(String name, int id, double gpa) {
        this.name = name;
        this.id = id;
        this.gpa = gpa;
    }
    
    public String getName() { return name; }
    public int getId() { return id; }
    public double getGpa() { return gpa; }
    
    public void setGpa(double gpa) {
        if (gpa >= 0.0 && gpa <= 4.0) {
            this.gpa = gpa;
        } else {
            System.out.println("Invalid GPA!");
        }
    }
    
    public void displayInfo() {
        System.out.println("Name: " + name + ", ID: " + id + ", GPA: " + gpa);
    }
}

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student("John Doe", 101, 3.5);
        s1.displayInfo();
        s1.setGpa(3.8);
        s1.displayInfo();
    }
}