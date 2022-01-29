public class Dice {

    // attributes
    private int value;

    // constructors
    public Dice() {
        value = -1;
    }

    public Dice(int value) {
        this.value = value;
    }

    // methods
    public void roll() {
        this.value = RandomNumber.getRandomNumber(1, 6);
    }

    public int getValue() {
        return value;
    }
}