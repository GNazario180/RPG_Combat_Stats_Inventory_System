package game.status;

public class StatusEffect {
    private Status status;
    private int turnRemaining;

    public StatusEffect(Status status, int turns) {
        this.status = status;
        this.turnRemaining = turns;
    }

    public Status getStatus() {
        return this.status;
    }

    public int getTurnRemaining() {
        return this.turnRemaining;
    }

    public void decrementingTurns() {
        if (turnRemaining > 0) {
            this.turnRemaining--;
        }
    }

    public boolean isExpired() {
        return this.turnRemaining <= 0;
    }

    public void cast() {
        this.status.cast();
    }

    public String getDescription() {
        return this.status.getDescription();
    }

    @Override
    public String toString() {
        return getDescription() + " (" + this.turnRemaining + " turns left)";
    }
}