
class SseatBookingRace {
    static int seatsLeft = 5;
    static int successfulBookings = 0;

    static synchronized void book() {
        if (seatsLeft > 0) {
            seatsLeft--;
            successfulBookings++;
            System.out.println(
                Thread.currentThread().getName()
                + " booked a seat");
        } else {
            System.out.println(
                Thread.currentThread().getName()
                + " could not book");
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        Thread[] threads = new Thread[10];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(
                () -> book(), "Customer-" + (i + 1));
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("Successful bookings: "
                + successfulBookings);
        System.out.println("Seats left: " + seatsLeft);
    }
}
