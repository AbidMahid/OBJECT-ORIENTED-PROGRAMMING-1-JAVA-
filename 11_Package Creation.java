package com.math;

public class Calculator {
    public static int add(int a, int b) {
        return a + b;
    }
    
    public static int subtract(int a, int b) {
        return a - b;
    }
    
    public static int multiply(int a, int b) {
        return a * b;
    }
    
    public static double divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return (double) a / b;
    }
}

package com.utils;

import java.util.Arrays;

public class ArrayUtils {
    public static int findMax(int[] arr) {
        return Arrays.stream(arr).max().orElseThrow();
    }
    
    public static int findMin(int[] arr) {
        return Arrays.stream(arr).min().orElseThrow();
    }
    
    public static double findAverage(int[] arr) {
        return Arrays.stream(arr).average().orElse(0.0);
    }
}

import com.math.Calculator;
import com.utils.ArrayUtils;

public class PackageDemo {
    public static void main(String[] args) {
        System.out.println("Calculator Operations:");
        System.out.println("10 + 5 = " + Calculator.add(10, 5));
        System.out.println("10 - 5 = " + Calculator.subtract(10, 5));
        System.out.println("10 * 5 = " + Calculator.multiply(10, 5));
        System.out.println("10 / 5 = " + Calculator.divide(10, 5));
        
        int[] numbers = {5, 2, 8, 1, 9, 3};
        System.out.println("\nArray Operations:");
        System.out.println("Array: " + java.util.Arrays.toString(numbers));
        System.out.println("Max: " + ArrayUtils.findMax(numbers));
        System.out.println("Min: " + ArrayUtils.findMin(numbers));
        System.out.println("Average: " + ArrayUtils.findAverage(numbers));
    }
}

