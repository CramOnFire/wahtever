package com.ttt.area;

import com.ttt.combat.CombatLoop;
import com.ttt.engine.GameEngine;
import com.ttt.entity.enemy.EnemyFactory;

public class BossArea implements Area {

    private GameEngine engine;

    public BossArea(GameEngine engine) {
        this.engine = engine;
    }

    @Override
    public int showMenu() {
        System.out.println("\n=== DRAGON!??!? ===");
        System.out.println("1. Fight");
        System.out.println("2. Leave");
        System.out.print("Choose: ");
        return 2;
    }

    @Override
    public void handleChoice(int choice) {
        switch (choice) {
            case 1:
                CombatLoop bossCombat = new CombatLoop(engine);
                EnemyFactory factory = new EnemyFactory();
                CombatLoop.CombatResult result = bossCombat.start(() -> factory.createEnemy("mountain"), false);
                if (result == CombatLoop.CombatResult.EXIT_AREA) {
                    engine.setArea(new PathArea(engine));
                } else if (result == CombatLoop.CombatResult.ENEMY_DEFEATED) {
                    engine.handleDragonVictory();
                } else if (result == CombatLoop.CombatResult.PLAYER_DEAD) {
                    engine.handlePlayerDeath();
                }
                break;

            case 2:
                engine.setArea(new PathArea(engine));
                break;
        }
    }
}