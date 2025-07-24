package game.stats;

/**
 * Represents a single stat (health, strength, etc.) for a character.
 * Maintains both a base value and a modified value, which may change
 * by flat or percentage-based modifiers (e.g., equipment or status effects).
 */
public class Stat {
    private int baseValue;
    private int modifiedValue;

    public Stat(int baseValue) {
        this.baseValue = baseValue;
        this.modifiedValue = baseValue;
    }

    public int getBaseValue() {
        return this.baseValue;
    }

    public int getModifiedValue() {
        return this.modifiedValue;
    }

    /**
     * Sets the current modified value of the stat, with a floor of 0.
     * This is typically used directly by effects or restoration logic.
     *
     * @param value amount to set modifiedValue stat.
     */
    public void setModifiedValue(int value) {
        this.modifiedValue = Math.max(0, value);
    }

    /// Applies a flat increase to stat (e.g., +5 strength).
    public void increaseFlat(int amount) {
        setModifiedValue(this.modifiedValue + amount);
    }

    public void decreaseFlat(int amount) {
        setModifiedValue(this.modifiedValue - amount);
    }

    /**
     * Applies a percentage-based reduction to the stat,
     * calculated from the base value.
     *
     * @param amount how much to increase stat by percentage.
     */
    public void increasePercent(double amount) {
        this.modifiedValue += (int) (this.baseValue * amount);
    }

    public void decreasePercent(double amount) {
        this.modifiedValue = Math.max(0, this.modifiedValue - (int) (this.baseValue * amount));
    }

    /**
     * Resets the stat back to its original base value.
     * Typically used when status effects or temporary buffs expire.
     */
    public void reset() {
        this.modifiedValue = this.baseValue;
    }

    @Override
    public String toString() {
        return String.valueOf(this.modifiedValue);
    }
}