package game.item;

import game.player.Player;

/**
 * Delegates the core behavior and metadata for all item types,
 * including usage, stack limits, and equipability.
 */
public interface ItemType {
    void use(Player player);
    void unuse(Player player);

    String getItemName();
    int getMaxStacks();
    String getDescription();
    SlotType getSlotType();

    /**
     * Determines if item is equipable base on its slot type.
     * By default, items with SlotType.NONE are not equipable.
     */
    default boolean isEquipable() {
        return getSlotType() != SlotType.NONE;
    }
}