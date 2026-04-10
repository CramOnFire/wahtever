package com.simplerpg.entity.enemy;

import com.simplerpg.entity.Entity;

public abstract class Enemy extends Entity {
    public Enemy(String name, int maxHealth, int baseAttack, int baseDefense, int gold) {
        super(name, maxHealth, baseAttack, baseDefense, gold);
    }
}
