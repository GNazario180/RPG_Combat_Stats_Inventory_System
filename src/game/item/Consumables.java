package game.item;

import game.player.Player;

/**
 * Represents consumables, like potions, ether, mana potions, etc.
 * Implements use and unuse behavior affecting health, mana, strength, etc.
 * Not equipable; unuse only logs a message.
 */
public class Consumables implements ItemType {
    private String consumableName;
    private int maxStacks;
    private String description;

    public Consumables(String consumableName, String description) {
        this.consumableName = consumableName;
        this.maxStacks = 99;
        this.description = description;
    }

    @Override
    public String getItemName() {
        return this.consumableName;
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
    public void use(Player player) {
        switch (this.consumableName.toLowerCase()) {
            case "health potion" -> {
                player.heal(25);
                System.out.println(player.getName() + " used a Health Potion and recovered 25 HP.");
            }

            case "mana potion" -> {
                player.restoreMana(15);
                System.out.println(player.getName() + " used a Mana Potion and restored 15 MP.");
            }

            default -> {
                System.out.println(player.getName() + " has consumed " + this.consumableName);
            }
        }
    }

    @Override
    public void unuse(Player player) {
        System.out.println(this.consumableName + " cannot be unequipped or reversed once consumed.");
    }

    @Override
    public SlotType getSlotType() {
        return SlotType.NONE;
    }
}