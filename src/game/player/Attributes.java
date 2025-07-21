package game.player;

public class Attributes {
    private int level;
    private int health, mana, strength, defense, speed, luck;
    private double experience;
    private long expToNextLevel;
    private double baseEXP;

    public Attributes() {
        this.level = 1;
        this.health = 100;
        this.mana = 50;
        this.strength = 10;
        this.defense = 10;
        this.speed = 5;
        this.luck = 2;

        this.experience = 0;
        this.baseEXP = 500;
        this.expToNextLevel = (long) this.baseEXP;
    }

    public int getLevel() {
        return this.level;
    }

    public int getHealth() {
        return this.health;
    }

    public int getMana() {
        return this.mana;
    }

    public int getStrength() {
        return this.strength;
    }

    public int getDefense() {
        return this.defense;
    }

    public int getSpeed() {
        return this.speed;
    }

    public int getLuck() {
        return this.luck;
    }

    public double getExperience() {
        return this.experience;
    }

    public double getExpToNextLevel() {
        return this.expToNextLevel;
    }

    public double getBaseEXP() {
        return this.baseEXP;
    }

    public double expNeededToLevelUp() {
        return Math.abs(this.experience - this.expToNextLevel);
    }

    public void nextLevelExpAmount() {
        this.expToNextLevel = (long) this.baseEXP * (long) (Math.ceil(Math.pow(this.level, 1.75)));
    }

    public void levelUp() {
        this.level++;
        nextLevelExpAmount();
    }

    public void gainExperience(double amount) {
        this.experience += amount;
        while (this.experience >= this.expToNextLevel) {
            this.experience -= this.expToNextLevel;
            levelUp();
        }
    }

    public void increaseStrength(int amount) {
        this.strength += amount;
    }

    public void decreaseStrength(int amount) {
        this.strength -= amount;
    }

    public void increaseDefense(int amount) {
        this.defense += amount;
    }

    public void decreaseDefense(int amount) {
        this.defense -= amount;
    }
}