/*
 * Introduction to multithreading in Java
 * @author Ramsey Njema
 * @version 1.0
 */

public class TaskHandler {
    public static void main(String[] args) {
        System.out.printf("Running Threads\n");
        for (int i = 0; i < 3; i++) {
            new Thread(new Task()).start();
        }
    }

}

class Task implements Runnable {
    @Override
    public void run() {
        // Executes operations associated with task when invoked
        try {
            System.out.printf("Hello from %s\n", Thread.currentThread().toString());
            Thread.sleep(1000);
            System.out.println("World");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

}
