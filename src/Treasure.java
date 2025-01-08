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
    public boolean isFoundTreasureInTown()
    {
        return foundTreasureInTown;
    }

    //setter methods
    public void setFoundTreasure1(boolean foundTreasure1) {
        this.foundTreasure1 = foundTreasure1;
    }

    public void setFoundTreasure2(boolean foundTreasure2) {
        this.foundTreasure2 = foundTreasure2;
    }

    public void setFoundTreasure3(boolean foundTreasure3) {
        this.foundTreasure3 = foundTreasure3;
    }

    public void setFoundTreasureInTown(boolean foundTreasureInTown) {
        this.foundTreasureInTown = foundTreasureInTown;
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

}
