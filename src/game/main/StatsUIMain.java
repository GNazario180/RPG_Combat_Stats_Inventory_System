package game.main;

import game.UI.PlayerStatsDisplay;
import game.player.Player;
import game.stats.StatType;
import game.inventory.Rarity;
import game.item.Item;
import game.item.Weapon;
import game.item.Armor;
import game.status.StatusEffect;
import game.status.EffectType;
import game.status.BuffEffect;
import game.status.DebuffEffect;

public class StatsUIMain {
    public static void main(String[] args) {
        Player player = new Player("Warrior of Light");
        player.gainExperience(120);

        Weapon testWeapon = new Weapon("Iron Sword", StatType.STRENGTH, 5);
        Item weaponItem = new Item(testWeapon, Rarity.UNCOMMON);
        player.getEquipment().equip(weaponItem, player);

        Armor testArmor = new Armor("Leather Armor", StatType.DEFENSE, 3);
        Item armorItem = new Item(testArmor, Rarity.UNCOMMON);
        player.getEquipment().equip(armorItem, player);

        BuffEffect strengthUP = new BuffEffect("Strength Up", StatType.STRENGTH, 2, EffectType.TURN_BASED, false);
        StatusEffect strengthBuff = new StatusEffect(strengthUP, 3);
        player.addStatusEffect(strengthBuff);

        DebuffEffect poison = new DebuffEffect("Poison", StatType.HEALTH, 8, EffectType.TURN_BASED, false);
        StatusEffect poisonDebuff = new StatusEffect(poison, 2);
        player.addStatusEffect(poisonDebuff);

        player.applyEffect();

        System.out.println();
        PlayerStatsDisplay.displayPlayerStats(player);
    }
}