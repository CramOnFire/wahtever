package com.ttt.combat;

public class CombatContext {
    public static class TurnState {
        public boolean charged;
    }

    public final TurnState playerState = new TurnState();
    public final TurnState enemyState = new TurnState();
    public int temporaryStrengthMultiplier = 0;  // percentage bonus, e.g., 20 = 1.20x
    public int strengthBuffTurnsRemaining = 0;   // tracks how many turns the strength buff lasts
    public int temporaryDefenseBonus = 0;
    public int defenseBuffTurnsRemaining = 0;    // tracks how many turns the defense buff lasts

    public void clearTemporaryBuffs() {
        temporaryStrengthMultiplier = 0;
        strengthBuffTurnsRemaining = 0;
        temporaryDefenseBonus = 0;
        defenseBuffTurnsRemaining = 0;
    }
}
