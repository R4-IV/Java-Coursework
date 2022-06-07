package Pepek.Robert;

import java.util.Scanner;
import java.util.Timer;

public class Main {

    public static void main(String[] args) {
        //user scanner object used to capture user key presses
        Scanner userIn = new Scanner(System.in);

        System.out.println("Hill Walker device");
        System.out.println("Controls: [M] mode , [S] set , [E] exit program");
        //Hill walker object instantiation
        HillWalker hillWalker = new HillWalker();
        //creates a timer object that makes the time function even if the user sets a custom time
        Timer timer = new Timer(true);
        timer.schedule(hillWalker , 0 , 1000);


        //program loop
        while(true){
            //displays controls available to the user
            System.out.println("\nControls: [M] mode , [S] set , [E] exit program");
            System.out.print(">>");
            //captures users input so it can be used in a switch statement
            String input = userIn.next();
            System.out.print("\n");
            //switch with cases for both uppercase and lowercase selections
            switch (input){
                case "m":
                case "M":
                    //displays that mode button has been pressed
                    System.out.println("Mode pressed");
                    hillWalker.pressMode();
                    break;
                case "s":
                case "S":
                    //displays that set button has been pressed
                    System.out.println("Set pressed");
                    hillWalker.pressSet();
                    break;
                case "e":
                case "E":
                    //case allowing the user to exit the program loop and shutting down the scanner
                    System.out.println("Exit pressed");
                    userIn.close();
                    System.exit(0);
                    break;
                default:
                    //default behaviour when incorrect input is selected
                    System.out.println("Not a valid button press");
                    continue;
            }
        }
    }
}
