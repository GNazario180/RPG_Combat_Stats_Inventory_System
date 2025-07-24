package game.inventory;

import game.item.Item;
import game.player.Player;

/**
 * Represents and manages a single inventory slot that holds an item and its quantity.
 * Handles stack size limits and displays stack information.
 */
public class InventorySlot {
    private Item item;
    private int quantity;

    public InventorySlot(Item item) {
        this.item = item;
        this.quantity = 0;
    }

    public Item getItem() {
        return this.item;
    }

    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Returns ture or false, by attempting to add a specified amount of this item to the stack.
     * Prevents exceeding the item's maximum stack size.
     *
     * @param amount the number of items to add.
     * @param player the controlled player receiving the item (used for context/logging).
     * @return true if the items were successfully added; false if stack limit was exceeded.
     */
    public boolean addToStacks(int amount, Player player) {
        int max = item.getMaxStacks();
        if (this.quantity + amount <= max) {
            this.quantity += amount;
            System.out.println("Added " + amount + "x " + this.item + " to inventory.");
            return true;
        } else {
            System.out.println("Cannot exceed max stack size of " + max + " for " + this.item);
            return false;
        }
    }

    public String getDisplayName() {
        return this.item.getItemName() + " x" + this.quantity + " (" + item.getRarity() + ")";
    }

    @Override
    public String toString() {
        return getDisplayName();
    }
}