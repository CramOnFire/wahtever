# SimpleRPG (placeholder name)

Combat can be turn-based or both plays one turn simultaneously. Damage should take into account baseDamage, weaponDecorator, potionBuff, baseDefense + armorDefense, damageOverTime.

World management: When an enemy dies, the player is given the choice to leave the area or search for another enemy.

`com.simplerpg.inventory`:
- This is a sample implementation so that I can code my own part. Fourth, please feel free to modify.
- The player is not allowed to have duplicate armor or weapon, but they can have multiple of the same potion.
- An item can be re-enhanced (removes the old suffix decorator and adds a new one) to try to roll for a better modifier.
- Potion implementation should probably be modified since it is currently planned to be only used on the player.

And yes I will follow SRP OCP later I just want the logic to work first.