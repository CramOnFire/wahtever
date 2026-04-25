package com.ttt.entity.enemy;
import java.util.Random;

public class EnemyFactory {
    private Random random = new Random();

    private int weightedRoll(int[] weights) {
        int total = 0;
        for (int w : weights) total += w;

        int roll = random.nextInt(total);
        int cumulative = 0;
        for (int i = 0; i < weights.length; i++) {
            cumulative += weights[i];
            if (roll < cumulative) return i;
        }
        return weights.length - 1;
    }

    public Enemy createEnemy(String areaName) {
        switch (areaName.toLowerCase()) {
            case "forest": {
                // Slime=60%, Snake=30%, Sorcerer=10%
                int[] weights = {60, 30, 10};
                int tier = weightedRoll(weights);
                if (tier == 0) return new Slime();
                if (tier == 1) return new Snake();
                return new Sorcerer();
            }

            case "mountain":
                return new Dragon();

            default:
                return new Slime();
        }
    }
}