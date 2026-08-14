package java,practical.lab-02;


public class CinemaShow {
    private String title;
    private int seatsAvailable;
    private final int capacity;
    private static int totalBooked=0;
    CinemaShow(String title, int capacity)
    {
        this.title=title;
        this.capacity=capacity;
        this.seatsAvailable=capacity;
    }
    CinemaShow(String title)
    {
        this(title,100);
    }
    public boolean book(int n)
    {
        if(n<=seatsAvailable)
        {
            seatsAvailable-=n;
            totalBooked+=n;
            return true;
        }
        else
        {
            return false;
        }


    }
    public void cancel(int n)
    {
        seatsAvailable += n;

        if (seatsAvailable > capacity) {
            seatsAvailable = capacity;
        }

    }
     public int getSeatsAvailable() {
        return seatsAvailable;
    }
      public static int getTotalBooked() {
        return totalBooked;
    }
   public static void main(String[] args) {

        CinemaShow show1 = new CinemaShow("Avengers", 50);
        CinemaShow show2 = new CinemaShow("Batman");

        System.out.println("Initial Seats: " + show1.getSeatsAvailable());

        System.out.println("\nBook 20 seats: " + show1.book(20));
        System.out.println("Seats Available: " + show1.getSeatsAvailable());
        System.out.println("Total Booked: " + show1.getTotalBooked());
        System.out.println("Total Capacity: " + show1.capacity);
   }
}

