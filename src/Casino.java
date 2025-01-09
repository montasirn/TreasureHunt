import java.sql.SQLOutput;
import java.util.Scanner;
// smth
public class Casino {
    private int max;
    private int min;
    private int gamble;
    private int goldBet;
    private String name;
    private Hunter gambler;
    private int roll;
    Scanner s = new Scanner(System.in);


    public Casino(String n){
        name = n;
        gambler = null;
    }

    public void gamble(){
        System.out.println("What number are you gambling on? (1-12)");
        gamble = s.nextInt();
        min = gamble - 2;
        max = gamble + 2;
        diceRoll();
    }

    public void gold(Hunter hunter){
        gambler = hunter;
        int goldAvailable = hunter.getGold();
        System.out.println("How much are you betting?");
        goldBet = s.nextInt();
        if (goldBet > goldAvailable){
            System.out.println("You don't have that much!");
        } else if (goldBet <= 0){
            System.out.println("Stop messing around!");
        } else {
            if (roll == gamble){
                System.out.println("The lucky number was " + roll + "! You just doubled your gold!");
                gambler.changeGold(gamble);
            } else if (min <= roll && roll <= max) {
                System.out.println("The lucky number was " + roll + ". You were close so you get to keep your gold.");
            } else {
                System.out.println("The lucky number was " + roll + ". You just lost all your gold!");
                gambler.changeGold(-gamble);
            }
        }
    }

    public void diceRoll(){
        roll = ((int) (Math.random() * 6 + 1)) + ((int) (Math.random() * 6 + 1));
    }

}
