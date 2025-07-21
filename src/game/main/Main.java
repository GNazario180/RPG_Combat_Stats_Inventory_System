package game.main;

import game.player.Player;
import game.status.Status;

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