package game.item;

import game.player.Player;

public class Weapon implements ItemType {
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