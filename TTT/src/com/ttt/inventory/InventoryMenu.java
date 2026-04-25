package com.ttt.inventory;

import java.util.ArrayList;
import java.util.List;

import com.ttt.engine.GameEngine;
import com.ttt.entity.Player;
import com.ttt.inventory.armor.armordecorator.ArmorDecorator;
import com.ttt.inventory.weapon.weapondecorator.WeaponDecorator;

public class InventoryMenu {

    private final GameEngine engine;

    public InventoryMenu(GameEngine engine) {
        this.engine = engine;
    }

    public void show() {
        Player player = engine.getPlayer();

        while (true) {
            List<Weapon> weapons = new ArrayList<>();
            List<Armor>  armors  = new ArrayList<>();

            for (Item item : player.getInventory()) {
                if (item instanceof Weapon) weapons.add((Weapon) item);
                else if (item instanceof Armor) armors.add((Armor) item);
            }

            System.out.println("\n--- INVENTORY ---");
            System.out.printf("Equipped Weapon: %s%n",
                player.getEquippedWeapon() != null ? player.getEquippedWeapon().getName() : "None");
            System.out.printf("Equipped Armor:  %s%n",
                player.getEquippedArmor() != null ? player.getEquippedArmor().getName() : "None");

            System.out.println("\n1. Weapons");
            System.out.println("2. Armors");
            System.out.println("3. Back");
            System.out.print("Choose: ");

            int choice = engine.getInput().getValidInt(1, 3);

            switch (choice) {
                case 1: pickWeapon(player, weapons); break;
                case 2: pickArmor(player, armors);   break;
                case 3: return;
            }
        }
    }

    private void pickWeapon(Player player, List<Weapon> weapons) {
        if (weapons.isEmpty()) { System.out.println("No weapons in inventory."); return; }

        System.out.println("\n--- WEAPONS ---");
        for (int i = 0; i < weapons.size(); i++) {
            Weapon w = weapons.get(i);
            boolean equipped = w == player.getEquippedWeapon();
            String mod = (w instanceof WeaponDecorator) ? "[MOD] " : "      ";
            System.out.printf("  %d. %s%-28s | DMG: %.1f%s%n",
                i + 1, mod, w.getName(), w.getBaseDamage(),
                equipped ? " [EQUIPPED]" : "");
        }
        System.out.println("  0. Cancel");
        System.out.print("Choose: ");

        int pick = engine.getInput().getValidInt(0, weapons.size());
        if (pick == 0) return;

        Weapon selected = weapons.get(pick - 1);
        if (selected == player.getEquippedWeapon()) {
            player.unequipWeapon(); // toggle off
        } else {
            player.equipWeapon(selected); // equip new
        }
    }

    private void pickArmor(Player player, List<Armor> armors) {
        if (armors.isEmpty()) { System.out.println("No armors in inventory."); return; }

        System.out.println("\n--- ARMORS ---");
        for (int i = 0; i < armors.size(); i++) {
            Armor a = armors.get(i);
            boolean equipped = a == player.getEquippedArmor();
            String mod = (a instanceof ArmorDecorator) ? "[MOD] " : "      ";
            System.out.printf("  %d. %s%-28s | DEF: %.1f%s%n",
                i + 1, mod, a.getName(), a.getDefense(),
                equipped ? " [EQUIPPED]" : "");
        }
        System.out.println("  0. Cancel");
        System.out.print("Choose: ");

        int pick = engine.getInput().getValidInt(0, armors.size());
        if (pick == 0) return;

        Armor selected = armors.get(pick - 1);
        if (selected == player.getEquippedArmor()) {
            player.unequipArmor(); // toggle off
        } else {
            player.equipArmor(selected); // equip new
        }
    }
}