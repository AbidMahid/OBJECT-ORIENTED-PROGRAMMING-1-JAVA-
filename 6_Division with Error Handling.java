import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionHandlingDemo {
    
    public static double divideNumbers(int a, int b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero!");
        }
        return (double) a / b;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.print("Enter numerator: ");
            int num = scanner.nextInt();
            
            System.out.print("Enter denominator: ");
            int denom = scanner.nextInt();
            
            double result = divideNumbers(num, denom);
            System.out.println("Result: " + result);
            
        } catch (InputMismatchException e) {
            System.out.println("Error: Please enter valid numbers!");
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Operation completed.");
            scanner.close();
        }
        
        try {
            int[] arr = new int[5];
            arr[10] = 100; 
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array index error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("General error: " + e.getMessage());
        }
    }
}