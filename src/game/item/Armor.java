package game.item;

import game.player.Player;

public class Armor implements ItemType {
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