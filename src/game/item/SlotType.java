package game.item;

public enum SlotType {
    WEAPON("Weapon"),
    ARMOR("Armor"),
    BOOTS("Boots"),
    HELM("Helm"),
    GAUNTLETS("Gauntlets"),
    ACCESSORY("Accessory");

    private final String displayName;

    SlotType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}