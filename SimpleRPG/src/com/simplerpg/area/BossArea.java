package com.simplerpg.area;

import com.simplerpg.engine.GameEngine;

public class BossArea implements Area {

    private GameEngine engine;

    public BossArea(GameEngine engine) {
        this.engine = engine;
    }

    @Override
    public int showMenu() {
        System.out.println("\n=== DRAGON!??!? ===");
        System.out.println("1. Fight");
        System.out.println("2. Leave");
        System.out.print("Choose: ");
        return 2;
    }

    @Override
    public void handleChoice(int choice) {
        switch (choice) {
            case 1:
                System.out.println("Combat happens here (placeholder)");
                break;

            case 2:
                engine.setArea(new PathArea(engine));
                break;
        }
    }
}
