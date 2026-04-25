package com.ttt.inventory.armor.armordecorator;
import com.ttt.inventory.Armor;

public abstract class ArmorDecorator extends Armor {
    protected Armor wrappedArmor;
    
    public ArmorDecorator(Armor armor) {
        super(armor.getName(), armor.getDefense());
        this.wrappedArmor = armor;
    }
    
    public Armor removeDecorator() { return wrappedArmor; }
}