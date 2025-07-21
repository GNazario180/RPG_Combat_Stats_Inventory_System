package game.equipment;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import game.item.Item;
import game.item.SlotType;

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

    public void equip(Item item) {
        this.equippedItems.put(item.getSlotType(), item);
    }

    public Item unequip(SlotType type) {
        return this.equippedItems.remove(type);
    }
}