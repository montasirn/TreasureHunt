public class Treasure {
    private String treasure1;
    private String treasure2;
    private String treasure3;
    private boolean foundTreasure1;
    private boolean foundTreasure2;
    private boolean foundTreasure3;
    private boolean foundTreasureInTown;


    Treasure() {
        treasure1 = "Twisted Dagger";
        treasure2 = "Obsidian Pendant";
        treasure3 = "Golden Eye";
        foundTreasure1 = false;
        foundTreasure2 = false;
        foundTreasure3 = false;
        foundTreasureInTown = false;
    }

    //Accessor methods
    public boolean isFoundTreasure() {
        return foundTreasureInTown;
    }

    //Mutator method
    public void setFoundTreasure(boolean foundTreasure) {
        this.foundTreasureInTown = foundTreasure;
    }

    //generate a random treasure in currentTown
    public String generateTreasure() {
        int choice = (int) (Math.random() * 3) + 1;
        if (choice == 1) {
            return treasure1;
        } else if (choice == 2) {
            return treasure2;
        } else if (choice == 3) {
            return treasure3;
        }
        return "";
    }

    public void huntTreasure() {
        int choice = (int) (Math.random() * 2) + 1;
        if (choice == 1) {
            foundTreasureInTown = true;
            if
        }
    }

}
