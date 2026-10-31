
class ArraySumRace {
    static final int SIZE = 1_000_000;
    static final int THREADS = 4;

    static long total = 0;
    static long synchronizedTotal = 0;

    static int[] numbers = new int[SIZE];

    static synchronized void addSafely(long value) {
        synchronizedTotal += value;
    }

    public static void main(String[] args)
            throws InterruptedException {

        for (int i = 0; i < SIZE; i++) {
            numbers[i] = 1;
        }

        Thread[] threads = new Thread[THREADS];

        // Approach 1: shared total without synchronization
        total = 0;

        for (int i = 0; i < THREADS; i++) {
            final int start = i * (SIZE / THREADS);
            final int end = start + SIZE / THREADS;

            threads[i] = new Thread(() -> {
                for (int j = start; j < end; j++) {
                    total += numbers[j];
                }
            });

            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("Expected: " + SIZE);
        System.out.println("Unsafe total: " + total);

        // Approach 2: synchronized shared total
        synchronizedTotal = 0;

        for (int i = 0; i < THREADS; i++) {
            final int start = i * (SIZE / THREADS);
            final int end = start + SIZE / THREADS;

            threads[i] = new Thread(() -> {
                long localSum = 0;

                for (int j = start; j < end; j++) {
                    localSum += numbers[j];
                }

                addSafely(localSum);
            });

            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println(
            "Synchronized total: " + synchronizedTotal);
    }
}
