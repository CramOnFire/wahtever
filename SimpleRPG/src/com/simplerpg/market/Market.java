package com.simplerpg.market;

import com.simplerpg.area.Area;
import com.simplerpg.area.TownArea;
import com.simplerpg.engine.*;

public class Market implements Area{

    private GameEngine engine;

    public Market(GameEngine engine) {
        this.engine = engine;
    }

    @Override
    public int showMenu() {
        System.out.println("\n=== MARKET ===");
        System.out.println("1. Buy Potion");
        System.out.println("2. Buy Sword");
        System.out.println("3. Exit Market");
        System.out.print("Choose: ");
        return 3;
    }

    @Override
    public void handleChoice(int choice) {
        switch (choice) {
            case 1:
                System.out.println("hi");
                break;

            case 2:
                System.out.println("Helo");
                break;

            case 3:
                engine.setArea(new TownArea(engine));
                break;
        }
    }
}