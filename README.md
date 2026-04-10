# SimpleRPG (placeholder name)

## Combat
- Undecided: Combat can be turn-based or both plays one turn simultaneously.
- Damage should take into account baseDamage, weaponDecorator, potionBuff, baseDefense + armorDefense, damageOverTime.

## World management and game UX
- When an enemy dies, the player is given the choice to leave the area or search for another enemy.

## Inventory
`com.simplerpg.inventory`:
- This is a sample implementation so that I can code my own part. Please feel free to modify.
- Suggestion: The player is not allowed to have duplicate armor or weapon, but they can have multiple of the same potion.
- An item can be re-enhanced (removes the old suffix decorator and adds a new one) to try to roll for a better modifier.
- To fix: Current potion implementation is rough and incomplete, I suggest you start over. Think of how the class should be structured and how the methods should be called.

And yes I will follow SRP OCP later; I just want the logic to work first.
