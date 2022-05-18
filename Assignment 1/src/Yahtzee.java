/**
 * This class represents the Yahtzee game
 * All possible scores of a given dice roll are calculated using the Yahtzee scoresheet
 * @author gsotelo
*/

public class Yahtzee {

    // Private int array containing values of the rolled dice
    private Dice[] dice;

    /**
     * Constructor initializes the dice array to a random set of values from one to six
     */
    public Yahtzee() {
        dice = new Dice[]{new Dice(-1), new Dice(-1), new Dice(-1), new Dice(-1), new Dice(-1)};
        for (int i = 0; i < dice.length; i++) {
            dice[i].roll();
        }
    }

    /**
     * Constructor initializes the dice array using a predetermined roll as an argument
     * @param dice array containing five values representing dice the rolls
     */
    public Yahtzee(Dice[] dice) {
        this.dice = dice;
    }

    /**
     * Method counts values of the dice roll
     * @return counts the amount of times each dice value is rolled
     */
    public int[] getValueCount() {
        int[] rolls = new int[5];
        for (int i = 0; i < rolls.length; i++) {
            rolls[i] = dice[i].getValue();
        }

        int[] numCounts = new int[6];
        for (int i = 0; i < rolls.length; i++) {
            if (rolls[i] == 1) {
                numCounts[0]++;

            } else if (rolls[i] == 2) {
                numCounts[1]++;

            } else if (rolls[i] == 3) {
                numCounts[2]++;

            } else if (rolls[i] == 4) {
                numCounts[3]++;

            } else if (rolls[i] == 5) {
                numCounts[4]++;

            } else if (rolls[i] == 6) {
                numCounts[5]++;
            }
        }
        return numCounts;
    }

    /**
     * Method sets the score options
     * @return returns an array containing all possible scores of a roll using the Yahtzee scoresheet
     */
    public int[] getScoreOptions() {
        int[] scoreOptions = new int[13];
        int[] numCounts = getValueCount();
        int[] diceFaces = new int[]{1, 2, 3, 4, 5, 6};

        // Sum of dice showing 1-6
        for (int i = 0; i < 6; i++) {
            scoreOptions[i] = numCounts[i] * (i + 1);
        }

        // Of A Kinds
        scoreOptions[6] = ofAKinds()[0];
        scoreOptions[7] = ofAKinds()[1];

        // Full House
        if (fullHouse()) {
            scoreOptions[8] = 25;
        }

        // Straights
        scoreOptions[9] = straights()[0];
        scoreOptions[10] = straights()[1];

        // Yahtzee
        for (int i = 0; i < numCounts.length; i++) {
            if (numCounts[i] == 5) {
                scoreOptions[11] = 50;
            }
        }

        // Chance
        for (int i = 0; i < numCounts.length; i++) {
            scoreOptions[12] += (numCounts[i] * diceFaces[i]);
        }

        return scoreOptions;
    }

    // Helper method to calculate score option for 3 and 4 of a kind
    private int[] ofAKinds() {
        int[] ofaKinds = new int[2];
        int[] numCounts = getValueCount();
        int[] diceFaces = new int[]{1, 2, 3, 4, 5, 6};
        boolean threeOfAKind = false, fourOfAKind = false;

        // Loop through dice counts to find if there are 3 or 4 of a kind.
        for (int i = 0; i < numCounts.length; i++) {

            if (numCounts[i] >= 3) {
                threeOfAKind = true;
            }

            if (numCounts[i] >= 4) {
                fourOfAKind = true;
            }
        }

        if (threeOfAKind) {
            for (int i = 0; i < numCounts.length; i++) {
                ofaKinds[0] += numCounts[i] * diceFaces[i];
            }
        }

        if (fourOfAKind) {
            for (int i = 0; i < numCounts.length; i++) {
                ofaKinds[1] += numCounts[i] * diceFaces[i];
            }
        }

        return ofaKinds;
    }

    // Helper method to calculate score option of full house
    private boolean fullHouse() {
        int[] numCounts = getValueCount();
        boolean fullHouse = false;
        boolean twoDice = false, threeDice = false;

        for (int i = 0; i < numCounts.length; i++)
        {
            if (numCounts[i] == 2) {
                twoDice = true;
            }

            if (numCounts[i] == 3) {
                threeDice = true;
            }
        }

        if (twoDice && threeDice)
        {
            fullHouse = true;
        }

        return fullHouse;
    }

    // Helper method to calculate score option of straights
    private int[] straights()
    {
        int[] straights = new int[2];
        int[] numCounts = getValueCount();
        int numConsecutive = 1;

        for (int i = 0; i < numCounts.length - 1; i++) {

            if (numCounts[i] >= 1 && numCounts[i + 1] >= 1) {
                numConsecutive++;
            }
        }

        if (numConsecutive == 4) {
            straights[0] = 30;
        }

        if (numConsecutive == 5) {
            straights[1] = 40;
        }

        return straights;
    }

    /** Method used to find the highest score option
     * @return an int array containing the highest score and its index
     */
    public int[] score()
    {
        int[] score = new int[2];
        int[] scoreOptions = getScoreOptions();
        int max = scoreOptions[0];
        int index = 0;

        for (int i = 0; i < scoreOptions.length; i++) {
            if (scoreOptions[i] > max) {
                max = scoreOptions[i];
                score[0] = max;
                index = i;
            }
        }

        score[1] = index;

        return score;
    }

    /** Overrides equals method to compare whether two rolls are equal
     * @return a boolean indicating whether two rolls are the same
     */
    public boolean equals(Yahtzee yahtzee)
    {
        int[] rollOne = getValueCount();
        int[] rollTwo = yahtzee.getValueCount();
        int elementsEqual = 0;
        boolean isEqual = false;

        for (int i = 0; i < getValueCount().length; i++) {
            if (rollOne[i] == rollTwo[i]) {
                elementsEqual++;
            }
        }

        if (elementsEqual == 6) {
            isEqual = true;
        }

        return isEqual;
    }

    /** Overrides toString method to return formatted dice roll
     * @return a String formatted to show the five dice values
     */
    public String toString() {
        return "Dice: {" + dice[0].getValue() + ", " + dice[1].getValue() + ", " + dice[2].getValue() + ", " +
                dice[3].getValue() + ", " + dice[4].getValue() + "}";
    }
}