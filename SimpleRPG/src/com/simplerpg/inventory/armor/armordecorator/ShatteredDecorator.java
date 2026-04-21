package com.simplerpg.inventory.armor.armordecorator;
import com.simplerpg.inventory.Armor;

public class ShatteredDecorator extends ArmorDecorator {
    public ShatteredDecorator(Armor armor) {
        super(armor);
        this.name = "Shattered " + armor.getName();
        this.defense = armor.getDefense() * 0.8;
    }
}