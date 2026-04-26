package com.ttt.engine;
 
import com.ttt.area.*;
import com.ttt.entity.Player;
import com.ttt.market.Market;
import com.ttt.ui.InputHandler;
 
 
public class GameEngine {
 
    private InputHandler input;
    private Market market;
    private Area currentArea;
    private Player player;
    private boolean running;
 
    public GameEngine() {
        input = new InputHandler();
        player = new Player("Aaron");
        player.addGold(100000);
        currentArea = new TownArea(this);
        running = true;
    }
 
    public Player getPlayer() {
        return player;
    }
 
    public void run() {
        System.out.println("=== Tales of Triumph and Tragedy ===");

        printLoreSequence(new String[] {
            "\nSword and shield, medieval kingdom, and a looming dragon threat.",
            "Old tomes of a hero's journey, from humble beginnings to legendary deeds.",
            "All fitting elements of a fantasy.",
            "But why would these inscriptions be written in a language indecipherable to a child?",
            player.getName() + ": Growing up on the streets, I faced poverty and hardship.",
            player.getName() + ": I never believed in these ancient legends, all I cared about was survival.",
            "A divination was performed: The dragon will awaken from its slumber, and only a hero can stop it.",
            "The king offered a reward to anyone who could defeat the dragon, but no one dared to face it.",
            player.getName() + ": At this point, I had nothing to lose. I decided to take on the challenge, hoping to change my fate.",
            "And so begins the tale of " + player.getName() + ", the unlikely hero destined to confront the dragon and save the kingdom.\n"
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
            "The dragon has been defeated, and silence returns to the mountains.",
            "The kingdom is saved from calamity, no longer bound by fear.",
            "Villagers celebrate, and the hero's name is etched into history.",
            "You begin your new life as a royal knight, free from the hardships of your past.",
            "But deep down, you know that the world is full of challenges, and your journey is far from over.\n"
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
            System.out.print(lines[i]);
            if (i < lines.length - 1) {
                input.waitForEnter();
            }
        }
    }
}