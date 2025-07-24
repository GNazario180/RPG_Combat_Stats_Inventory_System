package game.status;

import game.stats.StatType;
import game.player.Character;

/**
 * Defines a debuff effect that affects one or more stats,
 * and resets after expiring base on its EffectType.
 */
public class DebuffEffect extends StatModifierEffect {

    public DebuffEffect(String name, StatType stat, int amount, EffectType effectType, boolean shouldReset) {
        super(name, stat, Math.abs(amount), effectType, EffectPolarity.DEBUFF, shouldReset);
    }

    @Override
    public void apply(Character target) {
        target.getAttributes().getStat(affectedStat).decreaseFlat(modifierAmount);
    }
}