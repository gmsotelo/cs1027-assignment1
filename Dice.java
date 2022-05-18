/**
 * This class represents a single dice roll
 * @author gsotelo
 */

public class Dice {

    // Private int containing value of the rolled dice
    private int value;

    /**
     * This constructor initializes the value to a non-existent dice roll
     */
    public Dice() {
        value = -1;
    }

    /**
     * This constructor initializes value to an argument
     * @param value the side of the dice facing up
     */
    public Dice(int value) {
        this.value = value;
    }

    /**
     * Mutator method sets the value to equal a random number between one and six
     */
    public void roll() {
        this.value = RandomNumber.getRandomNumber(1, 6);
    }

    /**
     * Accessor method returns the value of the rolled dice
     */
    public int getValue() {
        return value;
    }
}