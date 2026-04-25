package com.ttt.combat;

import com.ttt.entity.Entity;
import com.ttt.entity.Player;
import com.ttt.inventory.Armor;
import com.ttt.inventory.Weapon;

public class AttackAction implements CombatAction {
    private final double chargeAttackMultiplier;
    private final double blockMultiplier;

    public AttackAction(double chargeAttackMultiplier, double blockMultiplier) {
        this.chargeAttackMultiplier = chargeAttackMultiplier;
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
            attack = (int) Math.round(attack * chargeAttackMultiplier);
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
            message = attacker.getName() + " consumes charge for x" + chargeAttackMultiplier + " attack. " + message;
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
