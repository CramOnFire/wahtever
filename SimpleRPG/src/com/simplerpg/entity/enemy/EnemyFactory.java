package com.simplerpg.entity.enemy;
import java.util.Random;

public class EnemyFactory {
    private Random random = new Random();

    public Enemy createEnemy(String areaName) {
        switch (areaName.toLowerCase()) {
            case "forest":
                int roll = random.nextInt(3);
                if (roll == 0) return new Slime();
                if (roll == 1) return new Enemy1();
                return new Enemy2();
                // will change name and add more enemies

            case "mountain":
                return new Dragon();

            default:
                return new Slime();
        }
    }
}