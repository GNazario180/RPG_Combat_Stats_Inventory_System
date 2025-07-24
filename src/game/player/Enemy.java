package game.player;

/**
 * Represents the enemy character in the game.
 * Inherits from the Character class and includes additional properties.
 * Such as enemy type and level.
 */
public class Enemy extends Character {
    private String enemyType;
    private final int level;

    public Enemy(String name, String enemyType, int level, Attributes attributes) {
        super(name, attributes);
        this.enemyType = enemyType;
        this.level = level;
    }

    public String getType() {
        return enemyType;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    public void setEnemyType(String enemyType) {
        this.enemyType = enemyType;
    }

    /**
     * Executes the enemy's action for the turn.
     * Currently just performs a basic attack on the player.
     *
     * @param player the controlled character.
     */
    public void performAction(Character player) {
        System.out.println(getName() + " takes their turn!");
        this.attack(player);
    }
}