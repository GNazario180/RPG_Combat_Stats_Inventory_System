package game.player;

import java.util.Map;

import game.status.StatusEffect;
import game.equipment.Equipment;
import game.item.Item;
import game.item.SlotType;

public class PlayerStatsDisplay {
    public static void displayPlayerStats(Player player) {
        // TODO: Add tier display to PlayerStatsDisplay
        // - Add a `tier` field to Player or Attributes (e.g., "Novice", "Warrior", etc.)
        // - Display the current tier in PlayerStatsDisplay alongside stats
        // - Tier progression logic will NOT be implemented in this project
        //   (e.g., unlocking skills, class changes, or item-based ascension will be handled in future projects)

        Attributes a = player.getAttributes();

        System.out.println(player.getName());
        System.out.printf("%-8s: %d\n", "level", a.getLevel());

        System.out.printf("%-8s: %-8d %-8s: %-8d\n",
                "HP", a.getHealth(), "MP", a.getMana());

        System.out.printf("%-8s: %-8d %-8s: %-8d %-8s: %-8d %-8s: %-8d\n",
                "STR", a.getStrength(), "DEF", a.getDefense(), "SPD", a.getSpeed(), "LCK", a.getLuck());

        System.out.printf("%-8s: %.0f / %.0f\n", "EXP", a.getExperience(), a.getExpToNextLevel());
        System.out.printf("%15s : %.0f\n", "EXP to lvl " + (a.getLevel() + 1), a.expNeededToLevelUp());

        if (player.getStatusEffect().isEmpty()) {
            System.out.println();
        } else {
            for (StatusEffect se : player.getStatusEffect()) {
                System.out.println(se.getDescription() + " ");
            }
            System.out.println();
        }

        System.out.println("Equipment:");
        Equipment eq = player.getEquipment();

        Map<SlotType, Item> equippedItems = eq.getAllEquippedItems();

        for (Map.Entry<SlotType, Item> entry : equippedItems.entrySet()) {
            SlotType slot = entry.getKey();
            Item item = entry.getValue();

            String display = slot.getDisplayName();
            String itemName = (item != null) ? item.getItemName() : "";
            System.out.printf(" %-8s: %s\n", display, itemName);
        }
    }
}