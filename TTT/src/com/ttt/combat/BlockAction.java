package com.ttt.combat;

public class BlockAction implements CombatAction {
    @Override
    public ActionResult execute(ActionExecutionInput input) {
        return new ActionResult(0, false, false, false, false, input.getActor().getName() + " braces and blocks incoming damage.");
    }
}