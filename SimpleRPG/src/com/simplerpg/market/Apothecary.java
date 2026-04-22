package com.simplerpg.market;

import com.simplerpg.area.Area;
import com.simplerpg.engine.GameEngine;
import com.simplerpg.entity.Player;
import com.simplerpg.inventory.HealthPotion;
import com.simplerpg.inventory.Item;
import com.simplerpg.market.stock.PotionStock;
import com.simplerpg.market.stock.ShopItem;

import java.util.ArrayList;
import java.util.List;

public class Apothecary implements Area {

    private GameEngine engine;
    private final PotionStock potionStock = new PotionStock();

    public Apothecary(GameEngine engine) {
        this.engine = engine;
    }

    @Override
    public int showMenu() {
        System.out.println("\n=== APOTHECARY ===");
        System.out.printf("Gold: %d%n", engine.getPlayer().getGold());
        System.out.println("1. Purchase Potion");
        System.out.println("2. Use Potion");
        System.out.println("3. Exit Apothecary");
        System.out.print("Choose: ");
        return 3;
    }

    @Override
    public void handleChoice(int choice) {
        switch (choice) {
            case 1: handlePurchase(); break;
            case 2: handleUse();      break;
            case 3: engine.setArea(new Market(engine)); break;
        }
    }

    private void handlePurchase() {
        Player player = engine.getPlayer();
        List<ShopItem> items = potionStock.getItems();

        System.out.println("\n--- POTIONS FOR SALE ---");
        System.out.printf("Your gold: %d%n%n", player.getGold());

        for (int i = 0; i < items.size(); i++) {
            ShopItem s = items.get(i);
            int owned = countOwned(player, s.getName());
            System.out.printf("%d. %-24s | Heals: %3d HP | Price: %d gold (%d owned)%n",
                i + 1, s.getName(), (int) s.getStat(), s.getPrice(), owned);
        }
        System.out.printf("%d. Cancel%n", items.size() + 1);
        System.out.print("Choose: ");

        int choice = engine.getInput().getValidInt(1, items.size() + 1);
        if (choice == items.size() + 1) return;

        ShopItem selected = items.get(choice - 1);
        if (player.getGold() < selected.getPrice()) {
            System.out.println("Not enough gold! You need " + selected.getPrice() + " gold.");
            return;
        }

        HealthPotion potion = potionStock.create(choice - 1);
        player.deductGold(selected.getPrice());
        player.getInventory().add(potion);
        System.out.printf("Purchased %s for %d gold! Remaining gold: %d%n",
            potion.getName(), selected.getPrice(), player.getGold());
    }

    private void handleUse() {
        Player player = engine.getPlayer();

        List<HealthPotion> potions = new ArrayList<>();
        for (Item item : player.getInventory()) {
            if (item instanceof HealthPotion) potions.add((HealthPotion) item);
        }

        if (potions.isEmpty()) { System.out.println("You have no potions in your inventory."); return; }

        int missing = player.getMaxHealth() - player.getHealth();
        System.out.printf("\n--- YOUR POTIONS --- (HP: %d / %d | Missing: %d)%n%n",
            player.getHealth(), player.getMaxHealth(), missing);

        for (int i = 0; i < potions.size(); i++) {
            HealthPotion p = potions.get(i);
            System.out.printf("%d. %-24s | Heals: %d HP%n", i + 1, p.getName(), p.getHealAmount());
        }
        System.out.printf("%d. Cancel%n", potions.size() + 1);
        System.out.print("Choose potion to use: ");

        int choice = engine.getInput().getValidInt(1, potions.size() + 1);
        if (choice == potions.size() + 1) return;

        HealthPotion selected = potions.get(choice - 1);
        if (player.getHealth() >= player.getMaxHealth()) { System.out.println("You are already at full health!"); return; }

        int healed = Math.min(selected.getHealAmount(), missing);
        player.heal(healed);
        player.getInventory().remove(selected);
        System.out.printf("Used %s. Healed %d HP. HP: %d / %d%n",
            selected.getName(), healed, player.getHealth(), player.getMaxHealth());
    }

    private int countOwned(Player player, String name) {
        int count = 0;
        for (Item item : player.getInventory()) {
            if (item instanceof HealthPotion && item.getName().equals(name)) count++;
        }
        return count;
    }
}