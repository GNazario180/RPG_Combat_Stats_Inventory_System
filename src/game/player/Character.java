package game.player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import game.stats.StatType;
import game.stats.Stat;
import game.status.StatusEffect;
import game.status.EffectType;

/**
 * Represents all Characters, player, enemy, NPC, etc.
 * Handles stat (aka Attributes), applying status effects, and combat scenes.
 */
public abstract class Character {
    private String name;
    protected Attributes attributes;
    private List<StatusEffect> activeEffects;

    public Character(String name, Attributes attributes) {
        this.name = name;
        this.attributes = attributes;
        this.activeEffects = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public Attributes getAttributes() {
        return this.attributes;
    }

    public List<StatusEffect> getActiveEffects() {
        return this.activeEffects;
    }

    public void heal(int amount) {
        Stat health = this.attributes.getHealth();
        int newHealth = Math.min(health.getModifiedValue() + amount, health.getBaseValue());

        health.setModifiedValue(newHealth);
        System.out.println(getName() + " healed for " + amount + " HP.");
    }

    public void restoreMana(int amount) {
        Stat mana = this.attributes.getMana();
        int newMana = Math.min(mana.getModifiedValue() + amount, mana.getBaseValue());

        mana.setModifiedValue(newMana);
        System.out.println(getName() + " restored " + amount + " MP.");
    }


    public void addStatusEffect(StatusEffect effect) {
        this.activeEffects.add(effect);
        System.out.println(effect.getEffect().getName() + " has been added");
    }

    /**
     * Processes all status effects during combat and removes them when expired.
     */
    public void processStatusList() {
        Iterator<StatusEffect> it = this.activeEffects.iterator();

        StatusEffect effect;
        while (it.hasNext()) {
            effect = it.next();
            effect.apply(this);

            if (effect.getEffect().getEffectType() == EffectType.TURN_BASED) {
                effect.decreaseDuration();
                System.out.println(effect.getEffect().getName() + " has " + effect.getTurnRemaining() + " turns left!");

                if (effect.isExpired()) {
                    System.out.println(effect.getEffect().getName() + " has expired!");

                    if (effect.getEffect().shouldReset()) {
                        this.resetStat(effect.getEffect().getAffectedStat());
                    }

                    it.remove();
                }
            } else if (effect.getEffect().getEffectType() == EffectType.INSTANT) {
                it.remove();
            } else if (effect.getEffect().getEffectType() == EffectType.CONDITIONAL) {
                // TODO: needs to add conditionals effects.
            }
        }
    }

    public void applyEffect() {
        System.out.println("Applying active effect to " + this.getName());
        processStatusList();
    }

    /**
     * Attacks the target character and applies damage based on strength vs defense.
     *
     * @param target, the character receiving the attack.
     */
    public void attack(Character target) {
        int damage = Math.max(0, this.attributes.getStrength().getModifiedValue() - target.attributes.getDefense().getModifiedValue());
        target.attributes.getHealth().decreaseFlat(damage);

        System.out.println(this.name + " attacks " + target.name + " for " + damage + " damage!");
    }

    public boolean isDead() {
        return this.attributes.getHealth().getModifiedValue() <= 0;
    }

    public void resetStat(StatType statType) {
        this.attributes.getStat(statType).reset();
    }

    public void printStats() {
        System.out.println(this.attributes.toString());
    }

    public abstract int getLevel();
}