package com.ttt.combat;

// This action allows the player to attempt to flee from combat.
public class FleeAction implements CombatAction {
    private final int successChancePercent;

    public FleeAction(int successChancePercent) {
        this.successChancePercent = successChancePercent;
    }

    @Override
    public ActionResult execute(ActionExecutionInput input) {
        int roll = input.getRandom().nextInt(100);
        boolean succeeded = roll < successChancePercent;
        if (succeeded) {
            return new ActionResult(0, false, false, false, true, "You escaped successfully. The enemy disappears into the distance.");
        }
        return new ActionResult(0, false, false, false, false, "Flee failed. Your turn is wasted.");
    }
}