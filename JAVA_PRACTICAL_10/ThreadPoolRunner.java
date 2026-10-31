
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolRunner {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 10; i++) {

            final int taskId = i;

            executor.submit(new Runnable() {

                @Override
                public void run() {

                    System.out.println(
                        "Task " + taskId + " is running on "
                        + Thread.currentThread().getName()
                    );

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                    System.out.println("Task " + taskId + " completed.");
                }
            });
        }

        executor.shutdown();

        try {
            if (executor.awaitTermination(1, TimeUnit.MINUTES)) {
                System.out.println("All tasks completed successfully.");
            } else {
                System.out.println("Time limit reached.");
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}