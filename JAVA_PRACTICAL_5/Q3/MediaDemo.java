abstract class Media {

    String title;

    Media(String title) {
        this.title = title;
    }

    abstract double lateFee(int lateDays);
}

class Movie extends Media {

    Movie(String title) {
        super(title);
    }

    double lateFee(int lateDays) {
        return lateDays * 10;
    }
}

class DVD extends Media {

    DVD(String title) {
        super(title);
    }

    double lateFee(int lateDays) {
        return lateDays * 5;
    }
}

class Book extends Media {

    Book(String title) {
        super(title);
    }

    double lateFee(int lateDays) {
        return lateDays * 3;
    }
}

public class MediaDemo {

    public static void main(String[] args) {

        Media[] media = {
            new Movie("Avengers"),
            new DVD("Inception"),
            new Book("Java Programming"),
            new Movie("Interstellar")
        };

        int[] lateDays = {
            3, 4, 2, 1
        };

        double total = 0;

        for (int i = 0; i < media.length; i++) {

            double fee = media[i].lateFee(lateDays[i]);

            System.out.println(
                "Title = " + media[i].title +
                ", Late Days = " + lateDays[i] +
                ", Late Fee = " + fee
            );

            total = total + fee;
        }

        System.out.println("Total Late Fees = " + total);
    }
}