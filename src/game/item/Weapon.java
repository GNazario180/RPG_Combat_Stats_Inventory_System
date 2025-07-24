package game.item;

import game.player.Player;
import game.stats.StatType;

/**
 * Represents a weapon item that provides stat bonuses when equipped.
 * Implements use and unuse behavior, boosting specified stats.
 */
public class Weapon implements ItemType {
    private String weaponName;
    private int statBonus;
    private StatType boostedStat;
    private final int maxStacks;
    private final String description;

    public Weapon(String weaponName, StatType boostedStat, int statBonus) {
        this.weaponName = weaponName;
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
        System.out.println(player.getName() + " has equipped " + this.weaponName +
                        ", boosting " + this.boostedStat.name().toLowerCase());
    }

    @Override
    public void unuse(Player player) {
        player.getAttributes().getStat(this.boostedStat).decreaseFlat(this.statBonus);
        System.out.println(player.getName() + " has unequipped " + this.weaponName +
                        ", removing bonus to " + this.boostedStat.name().toLowerCase());
    }

    @Override
    public String getItemName() {
        return this.weaponName;
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
        return SlotType.WEAPON;
    }
}