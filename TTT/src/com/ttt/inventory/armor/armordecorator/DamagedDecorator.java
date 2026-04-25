package com.ttt.inventory.armor.armordecorator;
import com.ttt.inventory.Armor;

public class DamagedDecorator extends ArmorDecorator {
    public DamagedDecorator(Armor armor) {
        super(armor);
        this.name = "Damaged " + armor.getName();
        this.defense = armor.getDefense() * 0.9;
    }
}