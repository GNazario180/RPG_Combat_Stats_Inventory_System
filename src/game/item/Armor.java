package game.item;

import game.player.Player;
import game.stats.StatType;

/**
 * Represents an armor item that provides stat bonuses when equipped.
 * Implements use and unuse behavior, boosting specified stats.
 */
public class Armor implements ItemType {
    private String armorName;
    private int statBonus;
    private StatType boostedStat;
    private final int maxStacks;
    private final String description;

    public Armor(String armorName, StatType boostedStat, int statBonus) {
        this.armorName = armorName;
        this.boostedStat = boostedStat;
        this.statBonus = statBonus;
        this.maxStacks = 1;
        this.description = "A powerful " + getSlotType().getDisplayName().toLowerCase() +
                        " that increases " + boostedStat.name().charAt(0) + boostedStat.name().substring(1).toLowerCase() + ".";
    }

    public int getStatBonus() {
        return this.statBonus;
    }

    public StatType getBoostedStat() {
        return this.boostedStat;
    }

    @Override
    public void use(Player player) {
        player.getAttributes().getStat(this.boostedStat).increaseFlat(this.statBonus);
        System.out.println(player.getName() + " has equipped " + this.armorName +
                        ", boosting " + this.boostedStat.name().toLowerCase());
    }

    @Override
    public void unuse(Player player) {
        player.getAttributes().getStat(this.boostedStat).decreaseFlat(this.statBonus);
        System.out.println(player.getName() + " has unequipped " + this.armorName +
                        ", removing bonus to " + this.boostedStat.name().toLowerCase());
    }

    @Override
    public String getItemName() {
        return this.armorName;
    }

    @Override
    public int getMaxStacks() {
        return this.maxStacks;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public SlotType getSlotType() {
        return SlotType.ARMOR;
    }
}