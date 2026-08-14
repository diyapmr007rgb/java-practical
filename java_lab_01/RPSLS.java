import java.util.Random;
import java.util.Scanner;

public class RPSLS {

    enum Move {
        ROCK, PAPER, SCISSORS, LIZARD, SPOCK
    }

    public static int winner(Move a, Move b) {

        if (a == b) {
            return 0;
        }

        return switch (a) {

            case ROCK -> switch (b) {
                case SCISSORS, LIZARD -> 1;
                default -> -1;
            };

            case PAPER -> switch (b) {
                case ROCK, SPOCK -> 1;
                default -> -1;
            };

            case SCISSORS -> switch (b) {
                case PAPER, LIZARD -> 1;
                default -> -1;
            };

            case LIZARD -> switch (b) {
                case SPOCK, PAPER -> 1;
                default -> -1;
            };

            case SPOCK -> switch (b) {
                case SCISSORS, ROCK -> 1;
                default -> -1;
            };
        };
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        int playerScore = 0;
        int computerScore = 0;

        Move[] moves = Move.values();

        System.out.println("Rock-Paper-Scissors-Lizard-Spock");
        System.out.println("Moves: ROCK, PAPER, SCISSORS, LIZARD, SPOCK");

        for (int round = 1; round <= 5; round++) {

            Move computerMove = moves[random.nextInt(moves.length)];

            System.out.print("\nRound " + round + " - Enter your move: ");
            String input = sc.next().toUpperCase();

            Move playerMove = Move.valueOf(input);

            int result = winner(playerMove, computerMove);

            System.out.println("You chose: " + playerMove);
            System.out.println("Computer chose: " + computerMove);

            if (result == 1) {
                System.out.println("You win this round!");
                playerScore++;
            } else if (result == -1) {
                System.out.println("Computer wins this round!");
                computerScore++;
            } else {
                System.out.println("This round is a tie!");
            }

            System.out.println(
                "Score: You " + playerScore + " - " + computerScore + " Computer"
            );
        }

        System.out.println("\n========== FINAL RESULT ==========");

        if (playerScore > computerScore) {
            System.out.println(
                "You win " + playerScore + "-" + computerScore
            );
        } else if (computerScore > playerScore) {
            System.out.println(
                "Computer wins " + computerScore + "-" + playerScore
            );
        } else {
            System.out.println(
                "It's a tie " + playerScore + "-" + computerScore
            );
        }

        sc.close();
    }
}