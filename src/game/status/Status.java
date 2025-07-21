package game.status;

public enum Status implements StatusType {
    POISON("Poison") {
        @Override
        public void cast() {
            System.out.println(" has been poisoned");
        }
    };

    private String description;

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}