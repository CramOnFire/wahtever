package com.simplerpg.inventory.armor.armordecorator;
import com.simplerpg.inventory.Armor;

public abstract class ArmorDecorator extends Armor {
    protected Armor wrappedArmor;
    
    public ArmorDecorator(Armor armor) {
        super(armor.getName(), armor.getDefense());
        this.wrappedArmor = armor;
    }
    public Armor removeDecorator() { return wrappedArmor; }
    
}