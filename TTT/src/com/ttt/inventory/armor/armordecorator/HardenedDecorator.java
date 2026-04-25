package com.ttt.inventory.armor.armordecorator;
import com.ttt.inventory.Armor;

public class HardenedDecorator extends ArmorDecorator {
    public HardenedDecorator(Armor armor) {
        super(armor);
        this.name = "Hardened " + armor.getName();
        this.defense = armor.getDefense() * 1.05;
    }
}