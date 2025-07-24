package game.UI;

import java.util.Map;

import game.status.StatusEffect;
import game.equipment.Equipment;
import game.item.Item;
import game.item.SlotType;
import game.player.Player;
import game.player.Attributes;

/**
 * Responsible for displaying the player's overall stats,
 * level, buffs/debuffs, experience, and currently equipped items.
 */
public class PlayerStatsDisplay {

    public static void displayPlayerStats(Player player) {
        // TODO: Add tier display to PlayerStatsDisplay
        // - Add a `tier` field to Player or Attributes (e.g., "Novice", "Warrior", etc.)
        // - Display the current tier in PlayerStatsDisplay alongside stats
        // - Tier progression logic will NOT be implemented in this project
        //   (e.g., unlocking skills, class changes, or item-based ascension will be handled in future projects)

        Attributes a = player.getAttributes();

        System.out.println("==============================================");
        System.out.println("        " + player.getName() + "'s Stats");
        System.out.println("==============================================");

        System.out.printf("%-8s: %d\n", "level", player.getLevel());

        System.out.printf("%-8s: %-8d %-8s: %-8d\n",
                "HP", a.getHealth().getModifiedValue(), "MP", a.getMana().getModifiedValue());

        System.out.printf("%-8s: %-8d %-8s: %-8d %-8s: %-8d %-8s: %-8d\n",
                "STR", a.getStrength().getModifiedValue(), "DEF", a.getDefense().getModifiedValue(), "SPD",
                a.getSpeed().getModifiedValue(), "LCK", a.getLuck().getModifiedValue());

        System.out.printf("%-8s: %d / %d\n", "EXP", player.getExperience(), player.getExperienceToNextLevel());
        System.out.printf("%15s : %d\n", "EXP to lvl " + (player.getLevel() + 1), player.expNeededToLevelUp());

        if (player.getStatusEffect().isEmpty()) {
            System.out.println();
        } else {
            for (StatusEffect se : player.getStatusEffect()) {
                System.out.print(se.getDescription() + "  ");
            }
            System.out.println();
        }

        System.out.println("\nEquipment:");
        Equipment eq = player.getEquipment();

        Map<SlotType, Item> equippedItems = eq.getAllEquippedItems();

        for (SlotType slot : SlotType.values()) {
            Item item = equippedItems.get(slot);

            String display = slot.getDisplayName();
            String itemName = (item != null) ? item.getItemName() : "[Empty]";
            System.out.printf(" %-8s: %s\n", display, itemName);
        }

        System.out.println("==================================================\n");
    }
}