/**
 * The Town Class is where it all happens.
 * The Town is designed to manage all of the things a Hunter can do in town.
 */
public class Town
{
    //something
    //instance variables
    private Hunter hunter;
    private Shop shop;
    private Terrain terrain;
    private Treasure treasure;
    private String treasureInTown;
    private String printMessage;
    private boolean toughTown;
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String RESET = "\u001B[0m";

    //Constructor
    /**
     * The Town Constructor takes in a shop and the surrounding terrain, but leaves the hunter as null until one arrives.
     * @param s The town's shoppe.
     * @param t The surrounding terrain.
     */
    public Town(Shop shop, double toughness, Treasure treasure)
    {
        this.shop = shop;
        this.terrain = getNewTerrain();
        this.treasure = treasure;
        treasureInTown = "";

        // the hunter gets set using the hunterArrives method, which
        // gets called from a client class
        hunter = null;

        printMessage = "";

        // higher toughness = more likely to be a tough town
        toughTown = (Math.random() < toughness);
    }

    public String getLatestNews()
    {
        return printMessage;
    }

    public void setTreasureInTown(String treasureInTown) {
        this.treasureInTown = treasureInTown;
    }

    /**
     * Assigns an object to the Hunter in town.
     * @param h The arriving Hunter.
     */
    public void hunterArrives(Hunter hunter)
    {
        this.hunter = hunter;
        printMessage = "Welcome to town, " + hunter.getHunterName() + ".";

        if (toughTown)
        {
            printMessage += "\nIt's pretty rough around here, so watch yourself.";
        }
        else
        {
            printMessage += "\nWe're just a sleepy little town with mild mannered folk.";
        }
    }

    /**
     * Handles the action of the Hunter leaving the town.
     * @return true if the Hunter was able to leave town.
     */
    public boolean leaveTown()
    {
        boolean canLeaveTown = terrain.canCrossTerrain(hunter);
        if (canLeaveTown)
        {
            String item = terrain.getNeededItem();
            printMessage = "You used your " + item + " to cross the " + terrain.getTerrainName() + ".";
            if (checkItemBreak())
            {
                hunter.removeItemFromKit(item);
                printMessage += "\nUnfortunately, your " + item + " broke.";
            }

            return true;
        }

        printMessage = "You can't leave town, " + hunter.getHunterName() + ". You don't have a " + terrain.getNeededItem() + ".";
        return false;
    }

    public void enterShop(String choice)
    {
        shop.enter(hunter, choice);
    }

    /**
     * Gives the hunter a chance to fight for some gold.<p>
     * The chances of finding a fight and winning the gold are based on the toughness of the town.<p>
     * The tougher the town, the easier it is to find a fight, and the harder it is to win one.
     */
    public void lookForTrouble()
    {
        double noTroubleChance;
        if (toughTown)
        {
            noTroubleChance = 0.66;
        }
        else
        {
            noTroubleChance = 0.33;
        }

        if (Math.random() > noTroubleChance)
        {
            printMessage = "You couldn't find any trouble";
        }
        else {
            printMessage = "You want trouble, stranger!  You got it!\nOof! Umph! Ow!\n";
            int goldDiff;
            int goldLost;
            if (TreasureHunter.easyMode == true) {
                goldDiff = (int) (Math.random() * 15) + 1;
                goldLost = (int) (Math.random() * 5 + 1);
            } else {
                goldDiff = (int) (Math.random() * 10) + 1;
                goldLost = (int) (Math.random() * 10 + 1);
            }
            if (Math.random() > noTroubleChance) {
                printMessage += "Okay, stranger! You proved yer mettle. Here, take my gold.";
                printMessage += "\nYou won the brawl and receive " + goldDiff + " gold.";
                hunter.changeGold(goldDiff);
            } else {
                printMessage += "That'll teach you to go lookin' fer trouble in MY town! Now pay up!";
                printMessage += "\nYou lost the brawl and pay " + goldDiff + " gold.";
                hunter.changeGold(-1 * goldLost);
            }
        }
    }

    public void huntTreasure() {
        if (treasure.isFoundTreasureInTown())
        {
            printMessage = "You already found the treasure in this town. Go to the next town.";
        }
        else
        {
            int choice = (int) (Math.random() * 2) + 1;
            if (choice == 1)
            {
                treasure.setFoundTreasureInTown(true);
                if (treasureInTown.equals(treasure.getTreasure1()))
                {
                    if (hunter.hasItemInKit(treasure.getTreasure1()))
                    {
                        printMessage = "You already found this treasure.";
                    }
                    else
                    {
                        printMessage = "You found the Twisted Dagger!";
                        hunter.addItem(treasure.getTreasure1());
                    }
                }
                else if (treasureInTown.equals(treasure.getTreasure2()))
                {
                    if (hunter.hasItemInKit(treasure.getTreasure2()))
                    {
                        printMessage = "You already found this treasure.";
                    }
                    else
                    {
                        printMessage = "You found the Obsidian Pendant!";
                        hunter.addItem(treasure.getTreasure2());
                    }
                }
                else if (treasureInTown.equals(treasure.getTreasure3()))
                {
                    if (hunter.hasItemInKit(treasure.getTreasure3()))
                    {
                        printMessage = "You already found this treasure.";
                    }
                    else
                    {
                        printMessage = "You found the Golden Eye!";
                        hunter.addItem(treasure.getTreasure3());
                    }
                }
            }
            else
            {
                printMessage = "You failed to find the treasure. Try again!";
            }
        }

    }

    public String toString()
    {
        return "This nice little town is surrounded by " + terrain.getTerrainName() + ".";
    }

    /**
     * Determines the surrounding terrain for a town, and the item needed in order to cross that terrain.
     *
     * @return A Terrain object.
     */
    private Terrain getNewTerrain()
    {
        double rnd = Math.random();
        if (rnd < .2)
        {
            return new Terrain(  CYAN + "Mountains" + RESET, "Rope");
        }
        else if (rnd < .4)
        {
            return new Terrain(BLUE + "Ocean" + RESET, "Boat");
        }
        else if (rnd < .6)
        {
            return new Terrain(GREEN + "Plains" + RESET, "Horse");
        }
        else if (rnd < .8)
        {
            return new Terrain(YELLOW + "Desert" + RESET, "Water");
        }
        else
        {
            return new Terrain(PURPLE + "Jungle" + RESET, "Machete");
        }
    }

    /**
     * Determines whether or not a used item has broken.
     * @return true if the item broke.
     */
    private boolean checkItemBreak()
    {
        double rand = Math.random();
        return (rand < 0.5);
    }
}
