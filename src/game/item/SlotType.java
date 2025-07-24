package game.item;

/**
 * Represents slot type for equipping items.
 * Each slot determines where an item can be equipped (e.g., weapon, armor).
 */
public enum SlotType {
    WEAPON("Weapon"),
    ARMOR("Armor"),
    BOOTS("Boots"),
    HELM("Helm"),
    GAUNTLETS("Gauntlets"),
    ACCESSORY("Accessory"),
    NONE("Nothing"); /// Represents an item that isn't equipable

    private final String displayName;

    SlotType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}