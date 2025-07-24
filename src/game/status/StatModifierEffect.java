package game.status;

import game.stats.StatType;
import game.player.Character;

/**
 * Abstract base class for modifying stat-altering effects.
 * Defines how a specific stat is modified based on effect type, amount, and polarity.
 */
public abstract class StatModifierEffect {
    protected final String name;
    protected final StatType affectedStat;
    protected final int modifierAmount;
    protected final EffectType effectType;
    protected final EffectPolarity polarity;
    protected final boolean shouldReset;

    public StatModifierEffect(String name, StatType affectedStat, int modifierAmount,
                              EffectType effectType, EffectPolarity polarity, boolean shouldReset) {
        this.name = name;
        this.affectedStat = affectedStat;
        this.modifierAmount = modifierAmount;
        this.effectType = effectType;
        this.polarity = polarity;
        this.shouldReset = shouldReset;
    }

    public String getName() {
        return this.name;
    }

    public StatType getAffectedStat() {
        return this.affectedStat;
    }

    public int getModifierAmount() {
        return this.modifierAmount;
    }

    public EffectType getEffectType() {
        return this.effectType;
    }

    public EffectPolarity getPolarity() {
        return this.polarity;
    }

    /// Whether the stat should return to its base value after the effect expires.
    public boolean shouldReset() {
        return this.shouldReset;
    }

    public abstract void apply(Character target);

    @Override
    public String toString() {
        return this.name + " (" + (this.modifierAmount > 0 ? "+" : "") +
                this.modifierAmount + " " + this.affectedStat + ")";
    }
}