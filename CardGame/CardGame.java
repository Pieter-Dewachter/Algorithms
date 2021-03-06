package algorithms.CardGame;

import java.util.Scanner;

public class CardGame {
    private ArrayQueue<Card> player1;
    private ArrayQueue<Card> player2;
    private ArrayQueue<Card> aside;
    private StringBuffer buffer;

    public CardGame() throws Exception {
        // Initialize everything
        player1 = new ArrayQueue<Card>(52);
        player2 = new ArrayQueue<Card>(52);
        aside   = new ArrayQueue<Card>(52);
        buffer  = new StringBuffer();

        int[] cursor = new int[52];
        for (int i = 0; i < cursor.length; i++)
            cursor[i] = i+1;

        // Shuffle the cards
        for (int i = 0; i < cursor.length; i++) {
            int random      = (int) (Math.random() * 52);
            int cursorInt   = cursor[random];
            cursor[random]  = cursor[i];
            cursor[i]       = cursorInt;
        }

        // Assign cards to the players
        for (int i = 0; i < cursor.length; i++) {
            int type    = (int) (cursor[i]/13.25);
            int weight  = cursor[i] - type * 13;
            if (i % 2 == 0)
                player1.enqueue(new Card(type, weight));
            else
                player2.enqueue(new Card(type, weight));
        }
    }

    /**
     * Give the cards in the aside queue and the ones on table to the winning player
     * @param wonPlayer The player that won, and will thus receive the cards
     * @param card1 The card of player 1 on the table
     * @param card2 The card of player 2 on the table
     * @return A string containing all cards of both players
     * @throws Exception Caused by the enqueue method,
     *                      can't occur due to this implementation
     */
    private String giveCards(ArrayQueue<Card> wonPlayer, Card card1, Card card2) throws Exception {
        // First give all cards that are aside
        while (aside.size() != 0)
            wonPlayer.enqueue(aside.dequeue());

        // Now give the cards that are on the table
        wonPlayer.enqueue(card1);
        wonPlayer.enqueue(card2);

        // Add the new cards to the StringBuffer, and empty it afterwards
        buffer.append("Player 1: ");
        buffer.append(card1.toString());
        buffer.append(" || Player 2: ");
        buffer.append(card2.toString());
        buffer.append("\n");

        String currentString = buffer.toString();
        buffer.setLength(0);

        return currentString;
    }

    /**
     * Initialize the next round, compare both cards and act accordingly
     * Uses recursion when both cards are equally weighted
     * @return Will eventually return what giveCards returns,
     *          can be "delayed" due to the recursion
     * @throws Exception Due to the enqueue and dequeue methods,
     *          won't occur due to this implementation
     */
    public String nextRound() throws Exception {
        // Each player puts a card on the table
        Card card1 = player1.dequeue();
        Card card2 = player2.dequeue();

        // Compare the cards and act accordingly
        if (card1.getWeight() != card2.getWeight()) {
            if (card1.getWeight() < card2.getWeight())
                return giveCards(player2, card1, card2);
            else
                return giveCards(player1, card1, card2);
        }
        else {
            // Equally weighted cards, these cards go to the aside queue
            aside.enqueue(card1);
            aside.enqueue(card2);

            // Make sure we also display the cards in the aside queue
            buffer.append("Player 1: ");
            buffer.append(card1.toString());
            buffer.append(" || Player 2: ");
            buffer.append(card2.toString());
            buffer.append("\n");

            // Game over when someone is out of cards, this should never occur though
            if (player1.size() == 0 || player2.size() == 0) {
                System.out.println("The game is over!");
                System.exit(0);
            }

            return nextRound();
        }
    }

    public String toString() {
        return "Card amounts: " + player1.size() + "  - " + player2.size() + "\n";
    }

    /**
     * THe main method which makes a new game and allows interaction
     * @param args Needed for the user input
     * @throws Exception Because of the CardGame constructor which uses enqueue methods,
     *                      won't occur due to this implementation
     */
    public static void main(String[] args) throws Exception {
        CardGame game   = new CardGame();
        Scanner scanner = new Scanner(System.in);
        System.out.println("******************* Card Game *******************");
        System.out.println("Press q to quit, any other key for the next round");

        // Uncomment this to see the card amounts will eventually stabilize themselves
        // for(int i = 0; i < 1000000; i++)
        //    game.nextRound();

        while(true) {
            String next = scanner.next();
            if (next.equals("q"))
                System.exit(0);

            System.out.println(game.nextRound());
            System.out.println(game.toString());
        }
    }
}
