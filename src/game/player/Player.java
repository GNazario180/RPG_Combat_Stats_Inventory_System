package game.player;

import java.util.List;

import game.inventory.Inventory;
import game.inventory.Rarity;
import game.item.Item;
import game.item.SlotType;
import game.item.Weapon;
import game.item.Armor;
import game.item.ItemType;
import game.equipment.Equipment;
import game.stats.StatType;
import game.status.StatusEffect;
import game.UI.PlayerStatsDisplay;

/**
 * Represents the player character, extending the base Character class.
 * inherits from Character class and includes level, experience, inventory, and equipment.
 */
public class Player extends Character {
    private int level;
    private int experience;
    private int experienceToNextLevel;

    private Inventory inventory;
    private Equipment equipment;

    public Player(String name) {
        super(name, new Attributes());

        this.level = 1;
        this.experience = 0;
        this.experienceToNextLevel = calculateXPThreshold(level);

        this.inventory = new Inventory();
        this.equipment = new Equipment();
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public Equipment getEquipment() {
        return this.equipment;
    }

    public List<StatusEffect> getStatusEffect() {
        return super.getActiveEffects();
    }

    /**
     * Uses all usable items in the player's inventory.
     */
    public void useInventoryItems() {
        this.inventory.useItems(this);
    }

    /**
     * Adds a single item of the specified type and rarity to inventory.
     *
     * @param type the type of item to be added.
     * @param rarity the rarity of item to be added.
     */
    public void addItemsToInventory(ItemType type, Rarity rarity) {
        this.inventory.addItem(new Item(type, rarity), 1, this);
    }

    /**
     * Adds a specified number of items to the inventory.
     *
     * @param type the type of item to be added.
     * @param rarity the rarity of item to be added.
     * @param amount the number of this item to be added.
     */
    public void addItemsToInventory(ItemType type, Rarity rarity, int amount) {
        this.inventory.addItem(new Item(type, rarity), amount, this);
    }

    /**
     * Sorts the inventory based on the given criteria.
     *
     * @param sortType the type of sorting.
     */
    public void sortInventory(String sortType) {
        switch (sortType.toLowerCase()) {
            case "alphabetically" -> this.inventory.sortAlphabetically();
            case "descending" -> this.inventory.sortByDescending();
            case "ascending" -> this.inventory.sortByAscending();
            case "rarity" -> this.inventory.sortByRarity();
            default -> System.out.println("Unknown sort type: " + sortType);
        }
    }

    /**
     * Equips a weapon, replacing any currently equipped weapon.
     *
     * @param weaponName name of weapon to equip.
     * @param boostedStat the specified stat to boost by the bonus.
     * @param statBonus the number that adds to the specified stat.
     */
    public void equipWeapon(String weaponName, StatType boostedStat, int statBonus) {
        Item oldWeapon = equipment.getEquippedItem(SlotType.WEAPON);
        if (oldWeapon != null && oldWeapon.getType() instanceof Weapon) {
            oldWeapon.unuse(this);
        }

        Weapon newWeapon = new Weapon(weaponName, boostedStat, statBonus);
        Item newItem = new Item(newWeapon, Rarity.UNCOMMON);

        this.equipment.equip(newItem, this);
        newWeapon.use(this);
    }

    /**
     * Equips a Armor piece, replacing any currently equipped armor.
     *
     * @param armorName name of armor to equip.
     * @param boostedStat the specified stat to boost by the bonus.
     * @param statBonus the number that adds to the specified stat.
     */
    public void equipArmor(String armorName, StatType boostedStat, int statBonus) {
        Item oldArmor = this.equipment.getEquippedItem(SlotType.ARMOR);
        if (oldArmor != null && oldArmor.getType() instanceof Armor) {
            oldArmor.unuse(this);
        }

        Armor newArmor = new Armor(armorName, boostedStat, statBonus);
        Item newItem = new Item (newArmor, Rarity.UNCOMMON);

        this.equipment.equip(newItem, this);
        newArmor.use(this);
    }

    /**
     * Unequips the item in the specified equipment slot.
     *
     * @param type the specified slot type to remove equipment.
     */
    public void unequip(SlotType type) {
        Item removed = this.equipment.unequip(type);
        if (removed != null) {
            removed.unuse(this);
            System.out.println(getName() + " has unequipped " + removed.getItemName());
        } else {
            System.out.println(getName() + " has nothing equipped in " + type + " slot.");
        }
    }

    // TODO: Later add more: equipBoots(), equipGauntlets(), equipAccessory(), etc.

    /**
     * Grants XP and triggers level-up if threshold is met.
     *
     * @param amount number to give to current amount of experience.
     */
    public void gainExperience(int amount) {
        System.out.println(getName() + " gained " + amount + " XP.");
        this.experience += amount;

        while (this.experience >= this.experienceToNextLevel) {
            levelUp();
        }
    }

    /**
     * Levels up the player, increases stats, and recalculates XP threshold
     */
    private void levelUp() {
        this.level++;
        this.experience -= this.experienceToNextLevel;
        this.experienceToNextLevel = calculateXPThreshold(this.level);

        System.out.println(getName() + " leveled up to level " + this.level + "!");

        // Example: boost stats
        this.attributes.getHealth().increaseFlat(5);
        this.attributes.getMana().increaseFlat(3);
        this.attributes.getStrength().increaseFlat(2);
        this.attributes.getDefense().increaseFlat(2);
    }

    /**
     * Returns the XP needed to reach the next level.
     *
     * @param level current level to calculate XP.
     */
    private int calculateXPThreshold(int level) {
        return 100 + level * 50;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    public int getExperience() {
        return this.experience;
    }

    public int getExperienceToNextLevel() {
        return this.experienceToNextLevel;
    }

    /**
     * Returns the remaining XP required to level up.
     */
    public int expNeededToLevelUp() {
        return this.experienceToNextLevel - this.experience;
    }

    /**
     * Displays the player's current stats using the UI layer.
     */
    public void displayStats() {
        PlayerStatsDisplay.displayPlayerStats(this);
    }
}