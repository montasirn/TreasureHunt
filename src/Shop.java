/**
 * The Shop class controls the cost of the items in the Treasure Hunt game.<p>
 * The Shop class also acts as a go between for the Hunter's buyItem() method.<p>
 */
import java.util.Scanner;

public class Shop
{
    private int waterCost;
    private int ropeCost;
    private int macheteCost;
    private int horseCost;
    private int boatCost;

    // instance variables
    private double markdown;
    private Hunter customer;

    //Constructor
    public Shop(double markdown, int waterCost, int ropeCost, int macheteCost, int horseCost, int boatCost)
    {
        this.markdown = markdown;
        this.waterCost = waterCost;
        this.ropeCost = ropeCost;
        this.macheteCost = macheteCost;
        this.horseCost = horseCost;
        this.boatCost = boatCost;
        customer = null;
    }

    //setter methods


    public void setWaterCost(int waterCost) {
        this.waterCost = waterCost;
    }

    public void setRopeCost(int ropeCost) {
        this.ropeCost = ropeCost;
    }

    public void setMacheteCost(int macheteCost) {
        this.macheteCost = macheteCost;
    }

    public void setHorseCost(int horseCost) {
        this.horseCost = horseCost;
    }

    public void setBoatCost(int boatCost) {
        this.boatCost = boatCost;
    }

    /** method for entering the shop
     * @param hunter  the Hunter entering the shop
     * @param buyOrSell  String that determines if hunter is "B"uying or "S"elling
     */
    public void enter(Hunter hunter, String buyOrSell)
    {
        customer = hunter;

        Scanner scanner = new Scanner(System.in);
        if (buyOrSell.equalsIgnoreCase("b"))
        {
            System.out.println("Welcome to the shop! We have the finest wares in town.");
            System.out.println("Currently we have the following items:");
            System.out.println(inventory());
            System.out.print("What're you lookin' to buy? ");
            String item = scanner.nextLine();
            item = item.substring(0, 1).toUpperCase() + item.substring(1);
            int cost = checkMarketPrice(item, true);
            if (cost == 0)
            {
                System.out.println("We ain't got none of those.");
            }
            else
            {
                System.out.print("It'll cost you " + cost + " gold. Buy it (y/n)? ");
                String option = scanner.nextLine();

                if (option.equalsIgnoreCase("y"))
                {
                    buyItem(item);
                }
            }
        }
        else
        {
            System.out.println("What're you lookin' to sell? ");
            System.out.print("You currently have the following items: " + customer.getInventory());
            String item = scanner.nextLine();
            int cost = checkMarketPrice(item, false);
            if (cost == 0)
            {
                System.out.println("We don't want none of those.");
            }
            else
            {
                System.out.print("It'll get you " + cost + " gold. Sell it (y/n)? ");
                String option = scanner.nextLine();

                if (option.equalsIgnoreCase("y"))
                {
                    sellItem(item);
                }
            }
        }
    }

    /** A method that returns a string showing the items available in the shop (all shops sell the same items)
     *
     * @return  the string representing the shop's items available for purchase and their prices
     */
    public String inventory()
    {
        String str = "Water: " + waterCost + " gold\n";
        str += "Rope: " + ropeCost + " gold\n";
        str += "Machete: " + macheteCost + " gold\n";
        str += "Horse: " + horseCost + " gold\n";
        str += "Boat: " + boatCost + " gold\n";

        return str;
    }

    /**
     * A method that lets the customer (a Hunter) buy an item.
     * @param item The item being bought.
     */
    public void buyItem(String item)
    {
        int costOfItem = checkMarketPrice(item, true);
        if (customer.buyItem(item, costOfItem))
        {
            System.out.println("Ye' got yerself a " + item + ". Come again soon.");
        }
        else
        {
            System.out.println("Hmm, either you don't have enough gold or you've already got one of those!");
        }
    }

    /**
     * A pathway method that lets the Hunter sell an item.
     * @param item The item being sold.
     */
    public void sellItem(String item)
    {
        int buyBackPrice = checkMarketPrice(item, false);
        if (customer.sellItem(item, buyBackPrice))
        {
            System.out.println("Pleasure doin' business with you.");
        }
        else
        {
            System.out.println("Stop stringin' me along!");
        }
    }

    /**
     * Determines and returns the cost of buying or selling an item.
     * @param item The item in question.
     * @param isBuying Whether the item is being bought or sold.
     * @return The cost of buying or selling the item based on the isBuying parameter.
     */
    public int checkMarketPrice(String item, boolean isBuying)
    {
        if (isBuying)
        {
            return getCostOfItem(item);
        }
        else
        {
            return getBuyBackCost(item);
        }
    }

    /**
     * Checks the item entered against the costs listed in the static variables.
     *
     * @param item The item being checked for cost.
     * @return The cost of the item or 0 if the item is not found.
     */
    public int getCostOfItem(String item)
    {
        if (item.equalsIgnoreCase("Water"))
        {
            return waterCost;
        }
        else if (item.equalsIgnoreCase("Rope"))
        {
            return ropeCost;
        }
        else if (item.equalsIgnoreCase("Machete"))
        {
            return macheteCost;
        }
        else if (item.equalsIgnoreCase("Horse"))
        {
            return horseCost;
        }
        else if (item.equalsIgnoreCase("Boat"))
        {
            return boatCost;
        }
        else
        {
            return 0;
        }
    }

    /**
     * Checks the cost of an item and applies the markdown.
     *
     * @param item The item being sold.
     * @return The sell price of the item.
     */
    public int getBuyBackCost(String item)
    {
        int cost = (int)(getCostOfItem(item) * markdown);
        return cost;
    }
}