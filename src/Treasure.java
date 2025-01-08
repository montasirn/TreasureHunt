public class Treasure {
    private String treasure1;
    private String treasure2;
    private String treasure3;
    private boolean foundTreasureInTown;


    Treasure() {
        treasure1 = "Twisted Dagger";
        treasure2 = "Obsidian Pendant";
        treasure3 = "Golden Eye";
        foundTreasureInTown = false;
    }

    //Accessor methods
    public boolean isFoundTreasureInTown()
    {
        return foundTreasureInTown;
    }

    public String getTreasure1() {
        return treasure1;
    }

    public String getTreasure2() {
        return treasure2;
    }

    public String getTreasure3() {
        return treasure3;
    }

    //setter methods
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
