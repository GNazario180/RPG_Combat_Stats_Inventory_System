package game.item;

public class Item {
    private String itemName;
    private ItemType item;
    private SlotType slotType;

    public Item(String itemName, ItemType item, SlotType slotType) {
        this.itemName = itemName;
        this.item = item;
        this.slotType = slotType;
    }

    public String getItemName() {
        return this.itemName;
    }

    public ItemType getItem() {
        return this.item;
    }

    public SlotType getSlotType() {
        return this.slotType;
    }
}