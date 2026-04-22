package com.simplerpg.engine;
 
import com.simplerpg.ui.InputHandler;
import com.simplerpg.area.*;
import com.simplerpg.market.Market;
import com.simplerpg.entity.Player;
 
 
public class GameEngine {
 
    private InputHandler input;
    private Market market;
    private Area currentArea;
    private Player player;
 
    public GameEngine() {
        input = new InputHandler();
        player = new Player("Hero");
        player.addGold(500000000);
        currentArea = new TownArea(this);
    }
 
    public Player getPlayer() {
        return player;
    }
 
    public void run() {
        System.out.println("=== SIMPLE RPG START ===");
 
        // Display lore, press enter to read the next line.
        // After that, the player will be taken to the town area.
 
        while (true) {
            // Edge case: The player should be able to exit during combat, but they need to try to flee.
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
