package game.status;

/**
 * Defines how a status effect behaves over time:
 * - TURN_BASED: lasts a fixed number of turns.
 * - INSTANT: applied immediately and then removed.
 * - CONDITIONAL: depends on the in-game conditions to persist or expire.
 */
public enum EffectType {
    TURN_BASED,
    INSTANT,
    CONDITIONAL
}