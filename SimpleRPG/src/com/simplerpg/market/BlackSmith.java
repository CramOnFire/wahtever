package com.simplerpg.market;

import com.simplerpg.area.Area;
import com.simplerpg.engine.GameEngine;
import com.simplerpg.entity.Player;
import com.simplerpg.inventory.Armor;
import com.simplerpg.inventory.Item;
import com.simplerpg.inventory.Weapon;
import com.simplerpg.inventory.armor.armordecorator.*;
import com.simplerpg.inventory.weapon.weapondecorator.*;
import com.simplerpg.market.stock.ArmorStock;
import com.simplerpg.market.stock.ShopItem;
import com.simplerpg.market.stock.WeaponStock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Blacksmith implements Area {

    private GameEngine engine;
    private final Random random = new Random();

    private final WeaponStock weaponStock = new WeaponStock();
    private final ArmorStock  armorStock  = new ArmorStock();

    private static final int MODIFY_COST = 75;

    private static final String[] WEAPON_DECORATORS =
        { "Sharp", "Keen", "Legendary", "Dull", "Chipped", "Broken" };

    private static final String[] ARMOR_DECORATORS =
        { "Hardened", "Reinforced", "Unbreakable", "Worn", "Damaged", "Shattered" };

    public Blacksmith(GameEngine engine) {
        this.engine = engine;
    }

    @Override
    public int showMenu() {
        System.out.println("\n=== BLACKSMITH ===");
        System.out.printf("Gold: %d%n", engine.getPlayer().getGold());
        System.out.println("1. Purchase Weapon");
        System.out.println("2. Purchase Armor");
        System.out.println("3. Modify Item");
        System.out.println("4. Exit Blacksmith");
        System.out.print("Choose: ");
        return 4;
    }

    @Override
    public void handleChoice(int choice) {
        switch (choice) {
            case 1: handlePurchaseWeapon(); break;
            case 2: handlePurchaseArmor();  break;
            case 3: handleModify();         break;
            case 4: engine.setArea(new Market(engine)); break;
        }
    }

    private void handlePurchaseWeapon() {
        Player player = engine.getPlayer();
        List<ShopItem> items = weaponStock.getItems();

        System.out.println("\n--- WEAPONS FOR SALE ---");
        System.out.printf("Your gold: %d%n%n", player.getGold());

        boolean anyInStock = false;
        List<Integer> available = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            ShopItem s = items.get(i);
            if (!weaponStock.isSold(i)) {
                anyInStock = true;
                available.add(i);
                System.out.printf("%d. %-24s | DMG: %5.1f | Price: %d gold%n",
                    available.size(), s.getName(), s.getStat(), s.getPrice());
            } else {
                System.out.printf("   %-24s | [OUT OF STOCK]%n", s.getName());
            }
        }

        if (!anyInStock) { System.out.println("All weapons are out of stock."); return; }

        System.out.printf("%d. Cancel%n", available.size() + 1);
        System.out.print("Choose: ");
        int choice = engine.getInput().getValidInt(1, available.size() + 1);
        if (choice == available.size() + 1) return;

        int shopIdx = available.get(choice - 1);
        int price   = items.get(shopIdx).getPrice();

        if (player.getGold() < price) { System.out.println("Not enough gold! You need " + price + " gold."); return; }

        Weapon purchased = weaponStock.create(shopIdx);
        weaponStock.markSold(shopIdx);
        player.deductGold(price);
        player.getInventory().add(purchased);
        System.out.printf("Purchased %s for %d gold! Remaining gold: %d%n",
            purchased.getName(), price, player.getGold());
    }

    private void handlePurchaseArmor() {
        Player player = engine.getPlayer();
        List<ShopItem> items = armorStock.getItems();

        System.out.println("\n--- ARMORS FOR SALE ---");
        System.out.printf("Your gold: %d%n%n", player.getGold());

        boolean anyInStock = false;
        List<Integer> available = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            ShopItem s = items.get(i);
            if (!armorStock.isSold(i)) {
                anyInStock = true;
                available.add(i);
                System.out.printf("%d. %-24s | DEF: %5.1f | Price: %d gold%n",
                    available.size(), s.getName(), s.getStat(), s.getPrice());
            } else {
                System.out.printf("   %-24s | [OUT OF STOCK]%n", s.getName());
            }
        }

        if (!anyInStock) { System.out.println("All armors are out of stock."); return; }

        System.out.printf("%d. Cancel%n", available.size() + 1);
        System.out.print("Choose: ");
        int choice = engine.getInput().getValidInt(1, available.size() + 1);
        if (choice == available.size() + 1) return;

        int shopIdx = available.get(choice - 1);
        int price   = items.get(shopIdx).getPrice();

        if (player.getGold() < price) { System.out.println("Not enough gold! You need " + price + " gold."); return; }

        Armor purchased = armorStock.create(shopIdx);
        armorStock.markSold(shopIdx);
        player.deductGold(price);
        player.getInventory().add(purchased);
        System.out.printf("Purchased %s for %d gold! Remaining gold: %d%n",
            purchased.getName(), price, player.getGold());
    }

    private void handleModify() {
        Player player = engine.getPlayer();

        List<Item> modifiables = new ArrayList<>();
        for (Item item : player.getInventory()) {
            if (item instanceof Weapon || item instanceof Armor) modifiables.add(item);
        }

        if (modifiables.isEmpty()) { System.out.println("You have no weapons or armors to modify."); return; }

        System.out.println("\n--- YOUR ITEMS ---");
        System.out.printf("Modify cost: %d gold | Your gold: %d%n%n", MODIFY_COST, player.getGold());

        for (int i = 0; i < modifiables.size(); i++) {
            Item item = modifiables.get(i);
            if (item instanceof Weapon) {
                Weapon w = (Weapon) item;
                String tag = (w instanceof WeaponDecorator) ? "[MODIFIED]" : "[NO MOD]  ";
                System.out.printf("%d. %s %-28s | DMG: %.1f%n", i + 1, tag, w.getName(), w.getBaseDamage());
            } else {
                Armor a = (Armor) item;
                String tag = (a instanceof ArmorDecorator) ? "[MODIFIED]" : "[NO MOD]  ";
                System.out.printf("%d. %s %-28s | DEF: %.1f%n", i + 1, tag, a.getName(), a.getDefense());
            }
        }
        System.out.printf("%d. Cancel%n", modifiables.size() + 1);
        System.out.print("Select item to modify: ");

        int iChoice = engine.getInput().getValidInt(1, modifiables.size() + 1);
        if (iChoice == modifiables.size() + 1) return;

        Item target = modifiables.get(iChoice - 1);

        if (player.getGold() < MODIFY_COST) { System.out.println("Not enough gold! Modification costs " + MODIFY_COST + " gold."); return; }

        if (target instanceof Weapon) modifyWeapon(player, (Weapon) target);
        else                          modifyArmor(player, (Armor) target);
    }

    private void modifyWeapon(Player player, Weapon target) {
        Weapon base = (target instanceof WeaponDecorator)
            ? ((WeaponDecorator) target).removeDecorator() : target;

        String roll     = WEAPON_DECORATORS[random.nextInt(WEAPON_DECORATORS.length)];
        Weapon modified = applyWeaponDecorator(base, roll);

        player.deductGold(MODIFY_COST);
        int idx = player.getInventory().indexOf(target);
        player.getInventory().set(idx, modified);
        if (player.getEquippedWeapon() == target) player.setEquippedWeapon(modified);

        System.out.printf("%nResult: %s (DMG: %.1f)%n", modified.getName(), modified.getBaseDamage());
        System.out.printf("Remaining gold: %d%n", player.getGold());
        rerollWeapon(player, modified, base, idx);
    }

    private void modifyArmor(Player player, Armor target) {
        Armor base = (target instanceof ArmorDecorator)
            ? ((ArmorDecorator) target).removeDecorator() : target;

        String roll    = ARMOR_DECORATORS[random.nextInt(ARMOR_DECORATORS.length)];
        Armor modified = applyArmorDecorator(base, roll);

        player.deductGold(MODIFY_COST);
        int idx = player.getInventory().indexOf(target);
        player.getInventory().set(idx, modified);

        System.out.printf("%nResult: %s (DEF: %.1f)%n", modified.getName(), modified.getDefense());
        System.out.printf("Remaining gold: %d%n", player.getGold());
        rerollArmor(player, modified, base, idx);
    }

    private void rerollWeapon(Player player, Weapon current, Weapon base, int invIdx) {
        while (true) {
            if (player.getGold() < MODIFY_COST) { System.out.println("Not enough gold to reroll. Keeping current modifier."); return; }
            System.out.println("\nSatisfied with the modifier?");
            System.out.println("1. Keep it");
            System.out.printf("2. Reroll (costs %d gold | Your gold: %d)%n", MODIFY_COST, player.getGold());
            System.out.print("Choose: ");

            if (engine.getInput().getValidInt(1, 2) == 1) { System.out.println("Confirmed: " + current.getName()); return; }

            String roll    = WEAPON_DECORATORS[random.nextInt(WEAPON_DECORATORS.length)];
            Weapon rerolled = applyWeaponDecorator(base, roll);
            player.deductGold(MODIFY_COST);
            player.getInventory().set(invIdx, rerolled);
            if (player.getEquippedWeapon() == current) player.setEquippedWeapon(rerolled);
            current = rerolled;
            System.out.printf("Rerolled: %s (DMG: %.1f) | Remaining gold: %d%n",
                rerolled.getName(), rerolled.getBaseDamage(), player.getGold());
        }
    }

    private void rerollArmor(Player player, Armor current, Armor base, int invIdx) {
        while (true) {
            if (player.getGold() < MODIFY_COST) { System.out.println("Not enough gold to reroll. Keeping current modifier."); return; }
            System.out.println("\nSatisfied with the modifier?");
            System.out.println("1. Keep it");
            System.out.printf("2. Reroll (costs %d gold | Your gold: %d)%n", MODIFY_COST, player.getGold());
            System.out.print("Choose: ");

            if (engine.getInput().getValidInt(1, 2) == 1) { System.out.println("Confirmed: " + current.getName()); return; }

            String roll   = ARMOR_DECORATORS[random.nextInt(ARMOR_DECORATORS.length)];
            Armor rerolled = applyArmorDecorator(base, roll);
            player.deductGold(MODIFY_COST);
            player.getInventory().set(invIdx, rerolled);
            current = rerolled;
            System.out.printf("Rerolled: %s (DEF: %.1f) | Remaining gold: %d%n",
                rerolled.getName(), rerolled.getDefense(), player.getGold());
        }
    }

    private Weapon applyWeaponDecorator(Weapon base, String type) {
        switch (type) {
            case "Sharp":     return new SharpDecorator(base);
            case "Keen":      return new KeenDecorator(base);
            case "Legendary": return new LegendaryDecorator(base);
            case "Dull":      return new DullDecorator(base);
            case "Chipped":   return new ChippedDecorator(base);
            default:          return new BrokenDecorator(base);
        }
    }

    private Armor applyArmorDecorator(Armor base, String type) {
        switch (type) {
            case "Hardened":    return new HardenedDecorator(base);
            case "Reinforced":  return new ReinforcedDecorator(base);
            case "Unbreakable": return new UnbreakableDecorator(base);
            case "Worn":        return new WornDecorator(base);
            case "Damaged":     return new DamagedDecorator(base);
            default:            return new ShatteredDecorator(base);
        }
    }
}