public class Driver {

    public static void main(String[] args) {

        Card[] cards = new Card[5];

        cards[0] = new Card("Ace", "Spades");
        cards[1] = new Card("King", "Hearts");
        cards[2] = new Card("Queen", "Diamonds");
        cards[3] = new Card("Ace", "Spades");

        for (int i = 0; i < cards.length; i++) {

            boolean duplicate = false;

            for (int j = 0; j < i; j++) {

                if (cards[i].equals(cards[j])) {
                    duplicate = true;
                    break;
                }
            }

            if (duplicate) {
                System.out.println(
                    "Duplicate found: " + cards[i]
                );
                break;
            }
        }
    }
}