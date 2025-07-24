package game.status;

import game.player.Character;
import game.stats.StatType;

/**
 * Takes a status effect (buffs/debuffs) applied to a character.
 * Handles duration tracking and effect application logic.
 */
public class StatusEffect {
    private final StatModifierEffect effect;
    private int turnRemaining;
    private boolean applied = false;

    public StatusEffect(StatModifierEffect effect, int turnRemaining) {
        this.effect = effect;
        this.turnRemaining = turnRemaining;
    }

    public static StatusEffect fromData(String name, String stat, int amount, int duration,
                                        String polarity, String effectType, boolean reset) {

        EffectPolarity effectPolarity = EffectPolarity.valueOf(polarity.toUpperCase());
        EffectType timing = EffectType.valueOf(effectType.toUpperCase());
        StatType statType = StatType.valueOf(stat.toUpperCase());

        StatModifierEffect effect = (effectPolarity == EffectPolarity.BUFF)
                ? new BuffEffect(name, statType, amount, timing, reset)
                : new DebuffEffect(name, statType, amount, timing, reset);

        return new StatusEffect(effect, duration);
    }

    public StatModifierEffect getEffect() {
        return this.effect;
    }

    public int getTurnRemaining() {
        return this.turnRemaining;
    }

    public void decreaseDuration() {
        if (this.turnRemaining > 0) this.turnRemaining--;
    }

    public void apply(Character target) {
        if (!this.applied) {
            this.effect.apply(target);
            applied = true;
        }
    }

    public boolean isExpired() {
        return this.turnRemaining <= 0;
    }

    public String getDescription() {
        return this.effect.getName();
    }
}