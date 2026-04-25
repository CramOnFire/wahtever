package com.ttt.inventory.armor.armordecorator;
import com.ttt.inventory.Armor;

public class WornDecorator extends ArmorDecorator {
    public WornDecorator(Armor armor) {
        super(armor);
        this.name = "Worn " + armor.getName();
        this.defense = armor.getDefense() * 0.95;
    }
}