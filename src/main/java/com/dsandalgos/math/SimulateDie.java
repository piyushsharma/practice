package com.dsandalgos.math;

import java.util.Random;

public class SimulateDie {

    private final Random random = new Random();

    private int rand5() {
        return random.nextInt(5);
    }

    private int rand7From5() {
        while(true) {
            // Do our die rolls
            int roll1 = rand5() + 1;
            int roll2 = rand5() + 1;

            // attempt is to get a range 1 - 25 from rolling twice
            // lowest value of roll1 & roll2 = 1 -> 1
            // highest value of roll1 & roll2 = 5 -> 25
            int outcomeNumber = (roll1 - 1) * 5 + (roll2 - 1) + 1;

            // If we hit an extraneous outcome we just re -roll
            if(outcomeNumber > 21) {
                continue;
            }

            // Our outcome was fine.return it !
            return outcomeNumber % 7 + 1;
        }
    }


    private int rand7() {
        return random.nextInt(7);
    }

    private int rand5From7() {
        while(true) {
            // Do our die rolls
            int roll = rand7();

            if(roll > 5) {
                continue;
            }

            // Our outcome was fine, return it !
            return roll + 1;
        }
    }
}
