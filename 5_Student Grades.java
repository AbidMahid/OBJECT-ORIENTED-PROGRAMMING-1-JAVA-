import java.util.*;

public class ArraysAndCollectionsDemo {
    public static void main(String[] args) {
        int[] scores = {85, 92, 78, 90, 88};
        System.out.println("Scores: " + Arrays.toString(scores));
        
        ArrayList<String> students = new ArrayList<>();
        students.add("Alice");
        students.add("Bob");
        students.add("Charlie");
        
        System.out.println("Students: " + students);
        students.remove("Bob");
        System.out.println("After removal: " + students);
        
        HashMap<String, Integer> gradeMap = new HashMap<>();
        gradeMap.put("Alice", 85);
        gradeMap.put("Bob", 92);
        gradeMap.put("Charlie", 78);
        
        System.out.println("\nGrades:");
        for (Map.Entry<String, Integer> entry : gradeMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        
        Arrays.sort(scores);
        System.out.println("\nSorted scores: " + Arrays.toString(scores));
        
        int index = Arrays.binarySearch(scores, 90);
        System.out.println("Score 90 found at index: " + index);
    }
}