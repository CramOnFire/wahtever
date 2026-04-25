package com.ttt.inventory.armor.armordecorator;
import com.ttt.inventory.Armor;

public class UnbreakableDecorator extends ArmorDecorator {
    public UnbreakableDecorator(Armor armor) {
        super(armor);
        this.name = "Unbreakable " + armor.getName();
        this.defense = armor.getDefense() * 1.2;
    }
}