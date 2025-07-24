package game.main;

import game.player.Player;
import game.player.Enemy;
import game.player.Attributes;
import game.stats.StatType;
import game.status.StatusEffect;
import game.status.EffectType;
import game.status.BuffEffect;
import game.status.DebuffEffect;

public class CombatMain {
    public static void main(String[] args) {
        Player player = new Player("Warrior of Light");

        Attributes goblinAttributes = new Attributes(60, 30, 8, 5, 4, 1);
        Enemy goblin = new Enemy("Goblin", "Beast", 1, goblinAttributes);

        player.addStatusEffect(new StatusEffect(
                new BuffEffect("Battle Cry", StatType.STRENGTH, 3, EffectType.TURN_BASED, false), 2
        ));

        goblin.addStatusEffect(new StatusEffect(
                new DebuffEffect("Weaken", StatType.DEFENSE, 2, EffectType.TURN_BASED, false), 2
        ));

        System.out.println("\n--- Battle Start ---\n");

        int round = 1;
        while (!player.isDead() && !goblin.isDead()) {
            System.out.println("===== Round " + round + " =====");

            player.applyEffect();
            goblin.applyEffect();

            player.attack(goblin);
            if (goblin.isDead()) {
                System.out.println(goblin.getName() + " has been defeated!\n");
                break;
            }

            goblin.performAction(player);
            if (player.isDead()) {
                System.out.println(player.getName() + " has been slain!\nYou lost");
                break;
            }

            System.out.println(player.getName() + " HP: " + player.getAttributes().getCurrentHealth());
            System.out.println(goblin.getName() + " HP: " + goblin.getAttributes().getCurrentHealth());
            System.out.println();

            round++;
        }

        System.out.println("--- Battle Over ---");
    }
}