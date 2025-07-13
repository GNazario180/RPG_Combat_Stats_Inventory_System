import java.util.*;

public class Main {
    public static void main(String[] args) {
        Player player = new Player("Warrior of Light");

        player.addStatusEffect(Status.POISON, 3);

        player.equipWeapon("Excalibur");
        player.equipArmor("Dragon Armor");
        System.out.println("----------------------------------");

        player.displayStats();
    }
}

class Player {
    private String name;
    private Attributes attributes;
    private List<StatusEffect> statusEffect;
    private Equipment equipment;

    public Player(String name) {
        this.name = name;
        this.attributes = new Attributes();
        this.statusEffect = new ArrayList<>();
        this.equipment = new Equipment();
    }

    public String getName() {
        return this.name;
    }

    public Attributes getAttributes() {
        return this.attributes;
    }

    public List<StatusEffect> getStatusEffect() {
        return this.statusEffect;
    }

    public Equipment getEquipment() {
        return this.equipment;
    }

    public void equipWeapon(String weaponName) {
        Item oldWeapon = this.equipment.getEquippedItem(SlotType.WEAPON);
        if (oldWeapon != null && oldWeapon.getItem() instanceof Weapon old) {
            this.attributes.decreaseStrength(old.getStrengthBonus());
            System.out.println(this.name + " has unequipped " + old.getWeaponName() + " (-STR " + old.getStrengthBonus() + ")");
        }

        Weapon newWeapon = new Weapon(weaponName);
        Item newItem = new Item (weaponName, newWeapon, SlotType.WEAPON);

        this.equipment.equip(newItem);
        newWeapon.use(this);
    }

    public void equipArmor(String armorName) {
        Item oldArmor = this.equipment.getEquippedItem(SlotType.ARMOR);
        if (oldArmor != null && oldArmor.getItem() instanceof Armor old) {
            System.out.println(this.name + " has unequipped " + old.getArmorName() + " (-DEF " + old.getDefenseBonus() + ")");
        }

        Armor newArmor = new Armor(armorName);
        Item newItem = new Item (armorName, newArmor, SlotType.ARMOR);

        this.equipment.equip(newItem);
        newArmor.use(this);
    }

    public void unequip(SlotType type) {
        Item removed = this.equipment.unequip(type);
        if (removed != null) {
            System.out.println(this.name + " has unequipped " + removed.getItemName());
        } else {
            System.out.println(this.name + " has nothing equipped in " + type + " slot.");
        }
    }

    // TODO: Later add more: equipBoots(), equipGauntlets(), equipAccessory(), etc.

    public void addStatusEffect(Status status, int turns) {
        this.statusEffect.add(new StatusEffect(status, turns));
    }

    public void updateStatusEffect() {
        Iterator<StatusEffect> it = this.statusEffect.iterator();
        while (it.hasNext()) {
            StatusEffect se = it.next();
            se.decrementingTurns();
            if (se.isExpired()) {
                it.remove();
            }
        }
    }

    public void displayStats() {
        PlayerStatsDisplay.displayPlayerStats(this);
    }
}

class Attributes {
    private int level;
    private int health, mana, strength, defense, speed, luck;
    private double experience;
    private long expToNextLevel;
    private double baseEXP;

    public Attributes() {
        this.level = 1;
        this.health = 100;
        this.mana = 50;
        this.strength = 10;
        this.defense = 10;
        this.speed = 5;
        this.luck = 2;

        this.experience = 0;
        this.baseEXP = 500;
        this.expToNextLevel = (long) this.baseEXP;
    }

    public int getLevel() {
        return this.level;
    }

    public int getHealth() {
        return this.health;
    }

    public int getMana() {
        return this.mana;
    }

    public int getStrength() {
        return this.strength;
    }

    public int getDefense() {
        return this.defense;
    }

    public int getSpeed() {
        return this.speed;
    }

    public int getLuck() {
        return this.luck;
    }

    public double getExperience() {
        return this.experience;
    }

    public double getExpToNextLevel() {
        return this.expToNextLevel;
    }

    public double getBaseEXP() {
        return this.baseEXP;
    }

    public double expNeededToLevelUp() {
        return Math.abs(this.experience - this.expToNextLevel);
    }

    public void nextLevelExpAmount() {
        this.expToNextLevel = (long) this.baseEXP * (long) (Math.ceil(Math.pow(this.level, 1.75)));
    }

    public void levelUp() {
        this.level++;
        nextLevelExpAmount();
    }

    public void gainExperience(double amount) {
        this.experience += amount;
        while (this.experience >= this.expToNextLevel) {
            this.experience -= this.expToNextLevel;
            levelUp();
        }
    }

    public void increaseStrength(int amount) {
        this.strength += amount;
    }

    public void decreaseStrength(int amount) {
        this.strength -= amount;
    }

    public void increaseDefense(int amount) {
        this.defense += amount;
    }

    public void decreaseDefense(int amount) {
        this.defense -= amount;
    }
}

class PlayerStatsDisplay {
    public static void displayPlayerStats(Player player) {
        // TODO: Add tier display to PlayerStatsDisplay
        // - Add a `tier` field to Player or Attributes (e.g., "Novice", "Warrior", etc.)
        // - Display the current tier in PlayerStatsDisplay alongside stats
        // - Tier progression logic will NOT be implemented in this project
        //   (e.g., unlocking skills, class changes, or item-based ascension will be handled in future projects)

        Attributes a = player.getAttributes();

        System.out.println(player.getName());
        System.out.printf("%-8s: %d\n", "level", a.getLevel());

        System.out.printf("%-8s: %-8d %-8s: %-8d\n",
                "HP", a.getHealth(), "MP", a.getMana());

        System.out.printf("%-8s: %-8d %-8s: %-8d %-8s: %-8d %-8s: %-8d\n",
                "STR", a.getStrength(), "DEF", a.getDefense(), "SPD", a.getSpeed(), "LCK", a.getLuck());

        System.out.printf("%-8s: %.0f / %.0f\n", "EXP", a.getExperience(), a.getExpToNextLevel());
        System.out.printf("%15s : %.0f\n", "EXP to lvl " + (a.getLevel() + 1), a.expNeededToLevelUp());

        if (player.getStatusEffect().isEmpty()) {
            System.out.println();
        } else {
            for (StatusEffect se : player.getStatusEffect()) {
                System.out.println(se.getDescription() + " ");
            }
            System.out.println();
        }

        System.out.println("Equipment:");
        Equipment eq = player.getEquipment();

        Map<SlotType, Item> equippedItems = eq.getAllEquippedItems();

        for (Map.Entry<SlotType, Item> entry : equippedItems.entrySet()) {
            SlotType slot = entry.getKey();
            Item item = entry.getValue();

            String display = slot.getDisplayName();
            String itemName = (item != null) ? item.getItemName() : "";
            System.out.printf(" %-8s: %s\n", display, itemName);
        }
    }
}

class StatusEffect {
    private Status status;
    private int turnRemaining;

    public StatusEffect(Status status, int turns) {
        this.status = status;
        this.turnRemaining = turns;
    }

    public Status getStatus() {
        return this.status;
    }

    public int getTurnRemaining() {
        return this.turnRemaining;
    }

    public void decrementingTurns() {
        if (turnRemaining > 0) {
            this.turnRemaining--;
        }
    }

    public boolean isExpired() {
        return this.turnRemaining <= 0;
    }

    public void cast() {
        this.status.cast();
    }

    public String getDescription() {
        return this.status.getDescription();
    }

    @Override
    public String toString() {
        return getDescription() + " (" + this.turnRemaining + " turns left)";
    }
}

class Equipment {
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

enum SlotType {
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

class Item {
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

interface ItemType {
    void use(Player player);
    void unuse(Player player);
}

class Weapon implements ItemType {
    private String weaponName;
    private int strengthBonus;

    public Weapon(String weaponName) {
        this.weaponName = weaponName;
        this.strengthBonus = 10;
    }

    public String getWeaponName() {
        return this.weaponName;
    }

    public int getStrengthBonus() {
        return this.strengthBonus;
    }

    @Override
    public void use(Player player) {
        player.getAttributes().increaseStrength(this.strengthBonus);
        System.out.println(player.getName() + " has equipped " + this.weaponName);
    }

    @Override
    public void unuse(Player player) {
        player.getAttributes().decreaseStrength(this.strengthBonus);
        System.out.println(player.getName() + " has equipped " + this.weaponName);
    }
}

class Armor implements ItemType {
    private String armorName;
    private int defenseBonus;

    public Armor(String armorName) {
        this.armorName = armorName;
        this.defenseBonus = 8;
    }

    public String getArmorName() {
        return this.armorName;
    }

    public int getDefenseBonus() {
        return this.defenseBonus;
    }

    @Override
    public void use(Player player) {
        player.getAttributes().increaseDefense(this.defenseBonus);
        System.out.println(player.getName() + " has equipped " + this.armorName);
    }

    @Override
    public void unuse(Player player) {
        player.getAttributes().decreaseDefense(this.defenseBonus);
        System.out.println(player.getName() + " has unequipped " + this.armorName);
    }
}

interface StatusType {
    void cast();
}

enum Status implements StatusType {
    POISON("Poison") {
        @Override
        public void cast() {
            System.out.println(" has been poisoned");
        }
    };

    private String description;

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}