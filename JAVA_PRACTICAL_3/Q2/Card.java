import java.util.Objects;

public class Card {

    private String rank;
    private String suit;

    // Constructor
    Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    // Convert card to string
    @Override
    public String toString() {
        return rank + " of " + suit;
    }

    // Compare two cards
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Card)) {
            return false;
        }

        Card other = (Card) obj;

        return rank.equals(other.rank)
                && suit.equals(other.suit);
    }

    // Generate hash code
    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }
}