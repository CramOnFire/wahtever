package com.ttt.inventory.weapon.weapondecorator;
import com.ttt.inventory.Weapon;

public class ChippedDecorator extends WeaponDecorator {
    public ChippedDecorator(Weapon weapon) {
        super(weapon);
        this.name = "Chipped " + weapon.getName();
        this.baseDamage = weapon.getBaseDamage() * 0.8;
    }
}