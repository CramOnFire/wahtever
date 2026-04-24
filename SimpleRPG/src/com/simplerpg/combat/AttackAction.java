package com.simplerpg.combat;

import com.simplerpg.entity.Entity;
import com.simplerpg.entity.Player;
import com.simplerpg.inventory.Armor;
import com.simplerpg.inventory.Weapon;

public class AttackAction implements CombatAction {
    private final int chargeBonusDamage;
    private final double blockMultiplier;

    public AttackAction(int chargeBonusDamage, double blockMultiplier) {
        this.chargeBonusDamage = chargeBonusDamage;
        this.blockMultiplier = blockMultiplier;
    }

    @Override
    public ActionResult execute(ActionExecutionInput input) {
        Entity attacker = input.getActor();
        Entity defender = input.getTarget();

        int attack = attacker.getBaseAttack();
        int defense = defender.getBaseDefense() + input.getDefenseBonus();

        if (attacker instanceof Player) {
            Weapon weapon = ((Player) attacker).getEquippedWeapon();
            if (weapon != null) {
                attack += (int) Math.round(weapon.getBaseDamage());
            }
        }

        if (defender instanceof Player) {
            Armor armor = ((Player) defender).getEquippedArmor();
            if (armor != null) {
                defense += (int) Math.round(armor.getDefense());
            }
        }

        // Apply attack multiplier (e.g., 1.20 = 20% bonus)
        attack = (int) Math.round(attack * input.getAttackMultiplier());

        boolean consumesCharge = false;
        if (input.isActorCharged()) {
            attack += chargeBonusDamage;
            consumesCharge = true;
        }

        int damage = Math.max(0, attack - defense);
        if (input.isTargetBlocking()) {
            damage = (int) Math.floor(damage * blockMultiplier);
        }

        String message;
        if (damage > 0) {
            message = attacker.getName() + " deals " + damage + " damage to " + defender.getName() + ".";
        } else {
            message = attacker.getName() + "'s attack was fully mitigated.";
        }

        if (consumesCharge) {
            message = attacker.getName() + " consumes charge for +" + chargeBonusDamage + " attack. " + message;
        }

        return new ActionResult(
            damage,
            consumesCharge,
            false,
            input.getAttackMultiplier() > 1.0,  // mark for consumption of strength buff
            false,
            message
        );
    }
}
