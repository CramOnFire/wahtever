package com.ttt.combat;

import java.util.Random;

import com.ttt.entity.Entity;

public class ActionExecutionInput {
    private final Entity actor;
    private final Entity target;
    private final boolean actorCharged;
    private final boolean targetBlocking;
    private final double attackMultiplier;  // e.g., 1.20 for 20% bonus
    private final int defenseBonus;
    private final Random random;

    public ActionExecutionInput(
        Entity actor,
        Entity target,
        boolean actorCharged,
        boolean targetBlocking,
        double attackMultiplier,
        int defenseBonus,
        Random random
    ) {
        this.actor = actor;
        this.target = target;
        this.actorCharged = actorCharged;
        this.targetBlocking = targetBlocking;
        this.attackMultiplier = attackMultiplier;
        this.defenseBonus = defenseBonus;
        this.random = random;
    }

    public Entity getActor() {
        return actor;
    }

    public Entity getTarget() {
        return target;
    }

    public boolean isActorCharged() {
        return actorCharged;
    }

    public boolean isTargetBlocking() {
        return targetBlocking;
    }

    public double getAttackMultiplier() {
        return attackMultiplier;
    }

    public int getDefenseBonus() {
        return defenseBonus;
    }

    public Random getRandom() {
        return random;
    }
}