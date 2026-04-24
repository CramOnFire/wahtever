package com.simplerpg.area;

import com.simplerpg.combat.CombatLoop;
import com.simplerpg.engine.GameEngine;
import com.simplerpg.entity.enemy.EnemyFactory;

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
                CombatLoop.CombatResult result = bossCombat.start(() -> factory.createEnemy("mountain"));
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
