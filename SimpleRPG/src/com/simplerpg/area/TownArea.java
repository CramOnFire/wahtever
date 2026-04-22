package com.simplerpg.area;
import com.simplerpg.engine.GameEngine;
import com.simplerpg.market.Market;


public class TownArea implements Area {
    

    private GameEngine engine;

    public TownArea(GameEngine engine) {
        this.engine = engine;
    }

    @Override
    public int showMenu() {
        System.out.println("\n=== TOWN ===");
        System.out.println("1. ADVENTURE TIME!!!");
        System.out.println("2. Market");
        System.out.println("3. Exit");
        System.out.print("Choose: ");
        return 3;
    }

    @Override
    public void handleChoice(int choice) {
        switch (choice) {
            case 1:
                engine.setArea(new PathArea(engine));
                break;

            case 2:
                engine.setArea(new Market(engine));
                break;

            case 3:
                System.out.println("Exiting game...");
                System.exit(0);
                break;
        }
    }
}
