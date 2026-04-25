package com.ttt.inventory.weapon.weapondecorator;
import com.ttt.inventory.Weapon;

public class DullDecorator extends WeaponDecorator {
    public DullDecorator(Weapon weapon) {
        super(weapon);
        this.name = "Dull " + weapon.getName();
        this.baseDamage = weapon.getBaseDamage() * 0.9;
    }
}