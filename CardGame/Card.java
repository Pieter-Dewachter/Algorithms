package algorithms.CardGame;

public class Card {
    private int type; // 0, 1, 2 and 3 are respectively Hearts, Tiles, Clovers and Pikes
    private int number; // 11, 12 and 13 are respectively Jack, Queen and King

    public Card(int type, int number) {
        this.type = type;
        this.number = number;
    }

    public int getWeight() {
        return number;
    }

    private String getType() {
        switch(type) {
            case 0:
                return "Hearts";
            case 1:
                return "Tiles";
            case 2:
                return "Clovers";
            default:
                return "Pikes";
        }
    }

    private String getFancyType() {
        switch(type) {
            case 0:
                return "♥";
            case 1:
                return "♦";
            case 2:
                return "♣";
            default:
                return "♠";
        }
    }

    public String toString() {
        String realValue;
        switch (number) {
            case 1:
                realValue = "Ace";
                break;
            case 11:
                realValue = "Jack";
                break;
            case 12:
                realValue = "Queen";
                break;
            case 13:
                realValue = "King";
                break;
            default:
                realValue = Integer.toString(number);
        }
        return getFancyType() + " " + realValue + " of " + getType();
    }
}
