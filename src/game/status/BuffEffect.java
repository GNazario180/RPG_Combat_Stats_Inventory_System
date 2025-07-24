package game.status;

import game.player.Character;
import game.stats.StatType;

/**
 * Defines a buff effect that affects one or more stats,
 * and resets after expiring base on its EffectType.
 */
public class BuffEffect extends StatModifierEffect {

    public BuffEffect(String name, StatType stat, int amount, EffectType effectType, boolean shouldReset) {
        super(name, stat, Math.abs(amount), effectType, EffectPolarity.BUFF, shouldReset);
    }

    @Override
    public void apply(Character target) {
        target.getAttributes().getStat(affectedStat).increaseFlat(modifierAmount);
    }
}