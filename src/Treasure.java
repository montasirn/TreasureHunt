public class Treasure {
    private String treasure1;
    private String treasure2;
    private String treasure3;
    private String treasureInTown;
    private boolean foundTreasure;

    Treasure() {
        treasure1 = "Twisted Dagger";
        treasure2 = "Obsidian Pendant";
        treasure3 = "Golden Eye";
        treasureInTown = null;
        foundTreasure = false;
    }

    //Accessor methods
    public boolean isFoundTreasure() {
        return foundTreasure;
    }

    //Mutator method
    public void setFoundTreasure(boolean foundTreasure) {
        this.foundTreasure = foundTreasure;
    }



}
