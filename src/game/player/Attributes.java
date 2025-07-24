package game.player;

import game.stats.Stat;
import game.stats.StatType;

/**
 * Holds characters core stats, such as health, mana, strength, etc.
 * Provides access to read, modify, and reset individual stats - often influenced by equipment or status effects.
 */
public class Attributes {
    private final Stat health;
    private final Stat mana;
    private final Stat strength;
    private final Stat defense;
    private final Stat speed;
    private final Stat luck;

    public Attributes(int health, int mana, int strength, int defense, int speed, int luck) {
        this.health = new Stat(health);
        this.mana = new Stat(mana);
        this.strength = new Stat(strength);
        this.defense = new Stat(defense);
        this.speed = new Stat(speed);
        this.luck = new Stat(luck);
    }

    public Attributes() {
        this(100, 50, 10, 10, 5, 2);
    }

    public Stat getHealth() {
        return this.health;
    }

    public Stat getMana() {
        return this.mana;
    }

    public Stat getStrength() {
        return this.strength;
    }

    public Stat getDefense() {
        return this.defense;
    }

    public Stat getSpeed() {
        return this.speed;
    }

    public Stat getLuck() {
        return this.luck;
    }

    public int getCurrentHealth() {
        return this.health.getModifiedValue();
    }

    public int getMaxedHealth() {
        return this.health.getBaseValue();
    }

    public int getCurrentMana() {
        return this.mana.getModifiedValue();
    }

    public int getMaxMana() {
        return this.mana.getBaseValue();
    }

    public void setCurrentHealth(int value) {
        this.health.setModifiedValue(Math.min(value, getMaxedHealth()));
    }

    public void setCurrentMana(int value) {
        this.mana.setModifiedValue(Math.min(value, getMaxMana()));
    }

    /**
     * Resets modified stats to its base value.
     * Typically used when temporary bonuses from effects or equipment wear off.
     *
     * @param type the stat to be reset.
     */
    public void resetStats(StatType type) {
        switch (type) {
            case HEALTH -> this.health.reset();
            case MANA -> this.mana.reset();
            case STRENGTH -> this.strength.reset();
            case DEFENSE -> this.defense.reset();
            case SPEED -> this.speed.reset();
            case LUCK -> this.luck.reset();
        }
    }

    /**
     * Returns the Stat object based on the given StatType.
     * Useful for generic access/modification in systems like effects or gear bonuses.
     *
     * @param type the stat to retrieve.
     */
    public Stat getStat(StatType type) {
        return switch (type) {
            case HEALTH -> this.health;
            case MANA -> this.mana;
            case STRENGTH -> this.strength;
            case DEFENSE -> this.defense;
            case SPEED -> this.speed;
            case LUCK -> this.luck;
        };
    }

    /// Temporarily boosts strength (commonly used by effects or skills).
    public void increaseStrength(int amount) {
        this.strength.increaseFlat(amount);
    }

    /// Removes temporary strength bonuses.
    public void decreaseStrength(int amount) {
        this.strength.decreaseFlat(amount);
    }

    public void increaseDefense(int amount) {
        this.defense.increaseFlat(amount);
    }

    public void decreaseDefense(int amount) {
        this.defense.decreaseFlat(amount);
    }

    public String toString() {
        return "Health: " + this.health + ", Mana: " + this.mana + ", Strength: " + this.strength +
                ", Defense: " + this.defense + ", Speed: " + this.speed + ", Luck: " + this.luck;
    }
}