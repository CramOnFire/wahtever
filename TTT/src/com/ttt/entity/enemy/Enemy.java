package com.ttt.entity.enemy;

import com.ttt.entity.Entity;

public abstract class Enemy extends Entity {
    public Enemy(String name, int maxHealth, int baseAttack, int baseDefense, int gold) {
        super(name, maxHealth, baseAttack, baseDefense, gold);
    }
}