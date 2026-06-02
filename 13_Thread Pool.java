import java.util.concurrent.*;
import java.util.Random;

class Task implements Runnable {
    private int taskId;
    private Random random = new Random();
    
    public Task(int taskId) {
        this.taskId = taskId;
    }
    
    @Override
    public void run() {
        System.out.println("Task " + taskId + " started by " + 
            Thread.currentThread().getName());
        
        try {
            Thread.sleep(random.nextInt(2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Task " + taskId + " completed");
    }
}

public class ThreadPoolDemo {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        for (int i = 1; i <= 10; i++) {
            executor.submit(new Task(i));
        }
        
        executor.shutdown();
        
        try {
            if (!executor.awaitTermination(30, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
        
        System.out.println("All tasks completed!");
    }
}