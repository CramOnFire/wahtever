package com.simplerpg.combat;

public class ActionResult {
    private final int damageToTarget;
    private final boolean consumesCharge;
    private final boolean grantsCharge;
    private final boolean consumeStrengthBuff;
    private final boolean fleeSucceeded;
    private final String message;

    public ActionResult(
        int damageToTarget,
        boolean consumesCharge,
        boolean grantsCharge,
        boolean consumeStrengthBuff,
        boolean fleeSucceeded,
        String message
    ) {
        this.damageToTarget = damageToTarget;
        this.consumesCharge = consumesCharge;
        this.grantsCharge = grantsCharge;
        this.consumeStrengthBuff = consumeStrengthBuff;
        this.fleeSucceeded = fleeSucceeded;
        this.message = message;
    }

    public int getDamageToTarget() {
        return damageToTarget;
    }

    public boolean isConsumesCharge() {
        return consumesCharge;
    }

    public boolean isGrantsCharge() {
        return grantsCharge;
    }

    public boolean isConsumeStrengthBuff() {
        return consumeStrengthBuff;
    }

    public boolean isFleeSucceeded() {
        return fleeSucceeded;
    }

    public String getMessage() {
        return message;
    }
}
