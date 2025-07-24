package game.equipment;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import game.player.Player;
import game.item.Item;
import game.item.SlotType;

/**
 * Manages the equipment system for a character, allowing equipping and unequipping
 * or items such as weapon or armor based on their slot types.
 */
public class Equipment {
    private Map<SlotType, Item> equippedItems;

    public Equipment() {
        this.equippedItems = new EnumMap<>(SlotType.class);
    }

    public Item getEquippedItem(SlotType type) {
        return this.equippedItems.get(type);
    }

    public Map<SlotType, Item> getAllEquippedItems() {
        return new HashMap<>(this.equippedItems);
    }

    /**
     * Equips an item into its designated slot. If item is already in that slot,
     * it will be unequipped (its effects removed) before equipping new item.
     *
     * @param item the item to equip.
     * @param player controlled player equipping the item.
     */
    public void equip(Item item, Player player) {
        SlotType slot = item.getType().getSlotType();

        if (!item.getType().isEquipable()) {
            System.out.println("You can't equip " + item.getItemName() + " -- it's not an equipable item.");
            return;
        }

        Item oldItem = this.equippedItems.get(slot);
        if (oldItem != null) {
            oldItem.unuse(player); /// Remove stat bonuses or effects from previous item
        }

        this.equippedItems.put(slot, item);
        item.use(player); /// Apply new item's effects
    }

    public Item unequip(SlotType type) {
        return this.equippedItems.remove(type);
    }
}