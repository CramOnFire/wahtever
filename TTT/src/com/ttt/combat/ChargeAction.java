package com.ttt.combat;

public class ChargeAction implements CombatAction {
    @Override
    public ActionResult execute(ActionExecutionInput input) {
        if (input.isActorCharged()) {
            return new ActionResult(0, false, false, false, false, input.getActor().getName() + " is already charged. Charge does not stack.");
        }

        return new ActionResult(0, false, true, false, false, input.getActor().getName() + " charges up a stronger next attack.");
    }
}