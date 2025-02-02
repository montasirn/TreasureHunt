/**
 * This class is responsible for controlling the Treasure Hunter game.<p>
 * It handles the display of the menu and the processing of the player's choices.<p>
 * It handles all of the display based on the messages it receives from the Town object.
 *
 */
import java.util.Scanner;
// smth
public class TreasureHunter
{
    //Instance variables
    private Town currentTown;
    private Hunter hunter;
    private Treasure treasure;
    public static boolean hardMode;
    public static boolean easyMode;
    public static boolean cheatMode;
    public static final String BLACK_BOLD = "\033[1;30m";  // BLACK
    public static final String RED_BOLD = "\033[1;31m";    // RED
    public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    public static final String BLUE_BOLD = "\033[1;34m";   // BLUE
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String CYAN_BOLD = "\033[1;36m";   // CYAN
    public static final String WHITE_BOLD = "\033[1;37m";  // WHITE
    public static final String RED_BACKGROUND = "\033[41m";    // RED
    public static final String GREEN_BACKGROUND = "\033[42m";  // GREEN
    public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW

    //Constructor
    /**
     * Constructs the Treasure Hunter game.
     */
    public TreasureHunter()
    {
        // these will be initialized in the play method
        currentTown = null;
        hunter = null;
        hardMode = false;
        easyMode = false;
        cheatMode = false;
        treasure = new Treasure();
    }

    // starts the game; this is the only public method
    public void play ()
    {
        welcomePlayer();
        enterTown();
        showMenu();
    }

    /**
     * Creates a hunter object at the beginning of the game and populates the class member variable with it.
     */
    private void welcomePlayer()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to TREASURE HUNTER!");
        System.out.println("Going hunting for the big treasure, eh?");
        System.out.print("What's your name, Hunter? ");
        String name = scanner.nextLine();

        // set hunter instance variable
        hunter = new Hunter(name, 10);

        System.out.print("Easy or Hard mode? (" +GREEN_BACKGROUND+ "easy" +Town.RESET + "/" + YELLOW_BACKGROUND + "normal" + Town.RESET + "/" + RED_BACKGROUND + "hard" + Town.RESET +"): ");
        String hard = scanner.nextLine();
        if (hard.equalsIgnoreCase( "easy")){
            easyMode = true;
        } else if (hard.equalsIgnoreCase( "hard")){
            hardMode = true;
        } else if (hard.equalsIgnoreCase("cookies")) {
            cheatMode = true;
        }

    }

    /**
     * Creates a new town and adds the Hunter to it.
     */
    private void enterTown()
    {
        double markdown = 0.25;
        double toughness = 0.4;
        if (hardMode)
        {
            // in hard mode, you get less money back when you sell items
            markdown = 0.5;

            // and the town is "tougher"
            toughness = 0.75;
        }
        if (easyMode) {
            markdown = 0.1;

            toughness = 0.2;
        }

        // note that we don't need to access the Shop object
        // outside of this method, so it isn't necessary to store it as an instance
        // variable; we can leave it as a local variable
        Shop shop = new Shop(markdown, 2, 4, 6, 12, 20);
        if (cheatMode) {
            shop.setWaterCost(1);
            shop.setRopeCost(1);
            shop.setMacheteCost(1);
            shop.setHorseCost(1);
            shop.setBoatCost(1);
        }

        // creating the new Town -- which we need to store as an instance
        // variable in this class, since we need to access the Town
        // object in other methods of this class
        currentTown = new Town(shop, toughness, treasure);

        // calling the hunterArrives method, which takes the Hunter
        // as a parameter; note this also could have been done in the
        // constructor for Town, but this illustrates another way to associate
        // an object with an object of a different class
        currentTown.hunterArrives(hunter);

        currentTown.setTreasureInTown(treasure.generateTreasure());
        treasure.setFoundTreasureInTown(false);
    }

    /**
     * Displays the menu and receives the choice from the user.<p>
     * The choice is sent to the processChoice() method for parsing.<p>
     * This method will loop until the user chooses to exit.
     */
    private void showMenu()
    {
        Scanner scanner = new Scanner(System.in);
        String choice = "";

        while (!(choice.equalsIgnoreCase("x")))
        {
            System.out.println();
            System.out.println(currentTown.getLatestNews());
            System.out.println("***");
            System.out.println(hunter);
            System.out.println("Your luck is currently: " + hunter.getLuck());
            System.out.println(currentTown);
            System.out.println("(" + PURPLE_BOLD + "B" + Town.RESET + ")uy something at the shop.");
            System.out.println("(" + YELLOW_BOLD + "S" + Town.RESET +")ell something at the shop.");
            System.out.println("(" + BLUE_BOLD + "M" + Town.RESET + ")ove on to a different town.");
            System.out.println("(" + CYAN_BOLD + "L" + Town.RESET + ")ook for trouble!");
            System.out.println("(" + GREEN_BOLD + "H" + Town.RESET + ")unt for treasure!");
            System.out.println("Gamble at the (" + RED_BOLD + "C" + Town.RESET + ")asino.");
            System.out.println("Give up the hunt and e(" + WHITE_BOLD + "X" +Town.RESET+ ")it.");
            System.out.println();
            System.out.print("What's your next move? ");
            choice = scanner.nextLine();
            choice = choice.toUpperCase();
            processChoice(choice);

            //Determines win/lose for the hunter
            if (hunter.hasItemInKit(treasure.getTreasure1()) && hunter.hasItemInKit(treasure.getTreasure2()) && hunter.hasItemInKit(treasure.getTreasure3()))
            {
                System.out.println(currentTown.getLatestNews());
                choice = "x";
                System.out.println("YOU FOUND ALL THREE TREASURES, " + hunter.getHunterName() + ". YOU WON!");
            }
            else if (hunter.getGold() == 0)
            {
                System.out.println(currentTown.getLatestNews());
                choice = "x";
                System.out.println("YOU HAVE NO GOLD LEFT, " + hunter.getHunterName() + ". YOU LOST!");
            }
        }
    }

    /**
     * Takes the choice received from the menu and calls the appropriate method to carry out the instructions.
     * @param choice The action to process.
     */
    private void processChoice(String choice)
    {
        if (choice.equalsIgnoreCase("B") || choice.equalsIgnoreCase("S"))
        {
            currentTown.enterShop(choice);
        }
        else if (choice.equalsIgnoreCase("M"))
        {
            if (currentTown.leaveTown())
            {
                //This town is going away so print its news ahead of time.
                System.out.println(currentTown.getLatestNews());
                enterTown();
            }
        }
        else if (choice.equalsIgnoreCase("L"))
        {
            currentTown.lookForTrouble();
        }
        else if (choice.equalsIgnoreCase("X"))
        {
            System.out.println("Fare thee well, " + hunter.getHunterName() + "!");
        }
        else if (choice.equalsIgnoreCase("H"))
        {
            currentTown.huntTreasure();
        } else if (choice.equalsIgnoreCase("C")) {
            Casino c = new Casino("Lucky Dice");
            c.gamble();
            c.gold(hunter);
        } else
        {
            System.out.println("Yikes! That's an invalid option! Try again.");
        }
    }
}