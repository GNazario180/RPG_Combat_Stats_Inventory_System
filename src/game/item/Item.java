package game.item;

import game.player.Player;
import game.inventory.Rarity;

/**
 * Represents an item instance with specific type and rarity.
 * Delegates behavior (like use/unuse) to its underlying ItemType.
 */
public class Item {
    private final ItemType type;
    private final Rarity rarity;

    public Item(ItemType type, Rarity rarity) {
        this.type = type;
        this.rarity = rarity;
    }

    public String getItemName() {
        return this.type.getItemName();
    }

    public String getDescription() {
        return this.type.getDescription();
    }

    public int getMaxStacks() {
        return this.type.getMaxStacks();
    }

    /**
     * Use an item, mainly for consumables.
     *
     * @param player controlled player using item.
     */
    public void use(Player player) {
        this.type.use(player);
    }

    /**
     * Unuse an item, mainly for equipable, such as weapons and armors.
     *
     * @param player controlled player.
     */
    public void unuse(Player player) {
        this.type.unuse(player);
    }

    public ItemType getType() {
        return this.type;
    }

    public Rarity getRarity() {
        return this.rarity;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Item other)) return false;

        /// Two items are equal if they have the same name (case-insensitive) and rarity.
        return this.getItemName().equalsIgnoreCase(other.getItemName()) &&
                this.rarity == other.rarity;
    }

    @Override
    public int hashCode() {
        /// Consistent with equals(): based on lowercase name and rarity
        return this.getItemName().toLowerCase().hashCode() + this.rarity.hashCode();
    }

    @Override
    public String toString() {
        return "[" + this.rarity + "] " + getItemName();
    }
}