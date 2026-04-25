package com.ttt.inventory.weapon.weapondecorator;
import com.ttt.inventory.Weapon;

public abstract class WeaponDecorator extends Weapon {
    protected Weapon wrappedWeapon;

    public WeaponDecorator(Weapon weapon) {
        super(weapon.getName(), weapon.getBaseDamage());
        if (weapon instanceof WeaponDecorator) {
            throw new IllegalArgumentException("Weapon already has an upgrade. Remove it first.");
        }
        this.wrappedWeapon = weapon;
    }

    public Weapon removeDecorator() {
    return wrappedWeapon;
    }
}