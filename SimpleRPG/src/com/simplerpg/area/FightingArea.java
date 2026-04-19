package com.simplerpg.area;

import com.simplerpg.engine.GameEngine;

// Change to path area, where the player can choose to fight in the forest or fight the dragon boss.
public class FightingArea implements Area {

    private GameEngine engine;

    public FightingArea(GameEngine engine) {
        this.engine = engine;
    }

    @Override
    public int showMenu() {
        System.out.println("\n=== PATH ===");
        System.out.println("1. Forest");
        System.out.println("2. Dragon");
        System.out.println("3. Walk back");
        return 3;
    }

    @Override
    public void handleChoice(int choice) {
        switch (choice) {
            case 1:
                engine.setArea(new ForestArea(engine));
                break;

            case 2:
                engine.setArea(new BossArea(engine));
                break;

            case 3:
                engine.setArea(new TownArea(engine));
                break;
        }
    }
}
