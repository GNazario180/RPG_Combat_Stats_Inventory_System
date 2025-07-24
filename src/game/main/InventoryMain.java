package game.main;

import game.player.Player;
import game.stats.StatType;
import game.inventory.Rarity;
import game.item.Consumables;
import game.item.Weapon;
import game.item.Armor;

public class InventoryMain {
    public static void main(String[] args) {
        Player player = new Player("Warrior of Light");

        Consumables potion = new Consumables("Potion(s)", "Heals for 30 HP.");
        player.addItemsToInventory(potion, Rarity.UNCOMMON);

        Consumables ether = new Consumables("Ether(s)", "restores 20 MP");
        player.addItemsToInventory(ether, Rarity.UNCOMMON, 50);

        Weapon weapon = new Weapon("Iron Sword", StatType.STRENGTH, 5);
        player.addItemsToInventory(weapon, Rarity.UNCOMMON, 2);

        Armor armor = new Armor("Leather Armor", StatType.DEFENSE, 3);
        player.addItemsToInventory(armor, Rarity.UNCOMMON, 1);

        System.out.println();
        player.getInventory().displayInventory();
        System.out.println();

        player.addItemsToInventory(ether, Rarity.UNCOMMON, 51);

        System.out.println();
        player.getInventory().displayInventory();
    }
}