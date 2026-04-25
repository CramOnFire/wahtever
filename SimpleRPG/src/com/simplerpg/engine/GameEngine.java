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
    private boolean running;
 
    public GameEngine() {
        input = new InputHandler();
        player = new Player("Hero");
        player.addGold(500000000);
        currentArea = new TownArea(this);
        running = true;
    }
 
    public Player getPlayer() {
        return player;
    }
 
    public void run() {
        System.out.println("=== SIMPLE RPG START ===");

        printLoreSequence(new String[] {
            "[Intro Placeholder] A restless wind blows over the old road.",
            "[Intro Placeholder] The kingdom waits for a hero to decide its fate.",
            "[Intro Placeholder] Your journey begins now."
        });

        while (running) {
            int max = currentArea.showMenu();
            int choice = input.getValidInt(1, max);
            currentArea.handleChoice(choice);
        }

        System.out.println("Game closed.");
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

    public void handlePlayerDeath() {
        System.out.println("\n=== GAME OVER ===");
        System.out.println("Press Enter to exit...");
        input.waitForEnter();
        running = false;
    }

    public void handleDragonVictory() {
        System.out.println("\n=== VICTORY ===");

        printLoreSequence(new String[] {
            "[Ending Placeholder] The dragon falls, and silence returns to the mountains.",
            "[Ending Placeholder] Fires in distant villages burn with hope once again.",
            "[Ending Placeholder] But your story does not have to end here."
        });

        System.out.println("1. Continue playing");
        System.out.println("2. Exit game");
        System.out.print("Choose: ");
        int choice = input.getValidInt(1, 2);

        if (choice == 2) {
            running = false;
            return;
        }

        currentArea = new PathArea(this);
    }

    private void printLoreSequence(String[] lines) {
        for (int i = 0; i < lines.length; i++) {
            System.out.println(lines[i]);
            if (i < lines.length - 1) {
                System.out.println("Press Enter for next line...");
                input.waitForEnter();
            }
        }
    }
}
