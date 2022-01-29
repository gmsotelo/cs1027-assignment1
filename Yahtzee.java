import java.util.Arrays;

public class Yahtzee {

    private Dice[] dice;

    public Yahtzee() {
        dice = new Dice[]{new Dice(-1), new Dice(-1), new Dice(-1), new Dice(-1), new Dice(-1)};
        for (int i = 0; i < dice.length; i++) {
            dice[i].roll();
        }
    }

    public Yahtzee(Dice[] dice) {
        this.dice = dice;
    }

    public int[] getValueCount() {

    }
//
//    public int[] getScoreOptions() {
//
//    }
//
//    public int[] score() {
//
//    }
//
//    public boolean equals(Yahtzee yahtzee) {
//
//    }
//
//    public String toString() {
//
//    }
}