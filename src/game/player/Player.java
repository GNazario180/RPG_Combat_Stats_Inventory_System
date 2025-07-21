package game.player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import game.status.StatusEffect;
import game.status.Status;
import game.equipment.Equipment;
import game.item.Item;
import game.item.SlotType;
import game.item.Weapon;
import game.item.Armor;

public class Player {
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