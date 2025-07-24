package game.status;

import game.stats.StatType;
import game.player.Character;

public interface CastEffect {
    void cast(Character character);
    StatType getAffectedStat();
    boolean shouldReset();
    int getDuration();
    EffectType getEffectType();
}