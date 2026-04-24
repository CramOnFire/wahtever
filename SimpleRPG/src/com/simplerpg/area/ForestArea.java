package com.simplerpg.area;

import com.simplerpg.combat.CombatLoop;
import com.simplerpg.engine.GameEngine;
import com.simplerpg.entity.enemy.EnemyFactory;

public class ForestArea implements Area {

    private GameEngine engine;

    public ForestArea(GameEngine engine) {
        this.engine = engine;
    }

    @Override
    public int showMenu() {
        System.out.println("\n=== FOREST ===");
        System.out.println("1. Fight");
        System.out.println("2. Leave");
        System.out.print("Choose: ");
        return 2;
    }

    @Override
    public void handleChoice(int choice) {
        switch (choice) {
            case 1:
                CombatLoop forestCombat = new CombatLoop(engine);
                EnemyFactory factory = new EnemyFactory();
                CombatLoop.CombatResult result = forestCombat.start(() -> factory.createEnemy("forest"));
                if (result == CombatLoop.CombatResult.EXIT_AREA) {
                    engine.setArea(new PathArea(engine));
                }
                break;

            case 2:
                engine.setArea(new PathArea(engine));
                break;
        }
    }
}
