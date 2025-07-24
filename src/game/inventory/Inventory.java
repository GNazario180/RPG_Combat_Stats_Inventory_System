package game.inventory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import game.item.Item;
import game.player.Player;

/**
 * Manages inventory including Consumables, Weapons, and Armors.
 * Supports sorting inventory, adding/storing stackable items, and using consumables.
 */
public class Inventory {
    private List<InventorySlot> slots;

    public Inventory() {
        this.slots = new ArrayList<>();
    }

    public List<InventorySlot> getInventoryList() {
        return this.slots;
    }

    /**
     * Uses all usable items in inventory (e.g., potions, ether).
     * Delegates the use effect to each item's implementation.
     *
     * @param player the player who is using the items.
     */
    public void useItems(Player player) {
        for (InventorySlot slot : this.slots) {
            slot.getItem().use(player);
        }
    }

    /**
     * Adds an item to the inventory. If item already exists,
     * its stack quantity is increased (assuming it's a stackable).
     * Otherwise, it creates a new slot.
     *
     * @param item the item to add.
     * @param amount how many of the item to add.
     * @param player the player who receives the item (used for events hooks or logs).
     */
    public void addItem(Item item, int amount, Player player) {
        for (InventorySlot slot : this.slots) {
            if (slot.getItem().equals(item)) {
                slot.addToStacks(amount, player);
                return;
            }
        }

        /// If not found, add it as a new slot.
        InventorySlot newSlot = new InventorySlot(item);
        if (newSlot.addToStacks(amount, player)) {
            this.slots.add(newSlot);
        }
    }

    public void sortAlphabetically() {
        this.slots.sort(Comparator.comparing(slot -> slot.getItem().getItemName().toLowerCase()));
    }

    public void sortByDescending() {
        this.slots.sort(Comparator.comparingInt(InventorySlot::getQuantity).reversed());
    }

    public void sortByAscending() {
        this.slots.sort(Comparator.comparingInt(InventorySlot::getQuantity));
    }

    public void sortByRarity() {
        this.slots.sort(Comparator.comparing((InventorySlot slot) -> slot.getItem().getRarity()).reversed());
    }

    public void displayInventory() {
        System.out.println("=== Inventory ===");
        for (InventorySlot slot : this.slots) {
            System.out.println(slot);
        }
    }
}