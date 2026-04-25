package com.ttt.inventory.armor.armordecorator;
import com.ttt.inventory.Armor;

public class ReinforcedDecorator extends ArmorDecorator {
    public ReinforcedDecorator(Armor armor) {
        super(armor);
        this.name = "Reinforced " + armor.getName();
        this.defense = armor.getDefense() * 1.1;
    }
}