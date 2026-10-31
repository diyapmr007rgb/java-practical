
class SeatBookingRace {
    static int seatsLeft = 5;
    static int booked = 0;

    static void book() {
        if (seatsLeft > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }

            seatsLeft--;
            booked++;
            System.out.println("Seat booked!");
        } else {
            System.out.println("No seats available.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> book());
            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("Seats left: " + seatsLeft);
        System.out.println("Successful bookings: " + booked);
    }
}
