package com.simplerpg.engine;

import com.simplerpg.ui.InputHandler;
import com.simplerpg.area.*;
import com.simplerpg.market.Market;


public class GameEngine {

    private InputHandler input;
    private Market market;
    private Area currentArea;

    public GameEngine() {
        input = new InputHandler();
        currentArea = new TownArea(this);
    }

    public void run() {
        System.out.println("=== SIMPLE RPG START ===");

        while (true) {
            int max = currentArea.showMenu();
            int choice = input.getValidInt(1, max);
            currentArea.handleChoice(choice);
        }
    }

    public void setArea(Area area) {
        this.currentArea = area;
    }

    public InputHandler getInput() {
        return input;
    }

    public Market getMarket() {
        return market;
    }

}