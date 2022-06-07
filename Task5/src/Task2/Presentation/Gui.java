package Task2.Presentation;

import Task2.Business.CharityRun;
import Task2.Business.Competitor;
import Task2.Business.Venue;
import Task2.Data.DataLoader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//class responsible for the console gui
public class Gui {

    //Scanner Object captures user input from the gui
    private Scanner userIn = new Scanner(System.in);
    //DataLoader object gives the presentation layer access to the arraylists
    DataLoader dataLoader = new DataLoader();

    //starts the gui
    public void start(){
        //infinite loop until broken by exit
        while(true){
            generatePrompt();
            System.out.print(">> ");
            //captures user input
            String userString = userIn.nextLine();
            //passes captured user String to the handler method
            handleUserSelection(userString);
        }
    }

    //Method creates the console output describing the possible options
    private void generatePrompt(){
        System.out.println("\nList Event Information................1");
        System.out.println("List Venue Details....................2");
        System.out.println("Search Competitor’s Event Entries.....3");
        System.out.println("Exit..................................0\n");
    }

    //method handles all possible user selections from the generate prompt method
    private void handleUserSelection(String userString){
        switch (userString){
            case "0":
                //closes the Scanner
                userIn.close();
                System.exit(0);
                break;
            case "1":
                listEvents();
                break;
            case "2":
                listVenues();
                break;
            case "3":
                matchCompetitor();
                break;
            default:
                //handles all non compliant input
                System.out.println("Invalid selection try again\n");
                return;
        }
    }

    //Method lists all available events and displays the one chosen by the user
    private void listEvents(){
        //variable keeps track of the current event used in output to number the events and make them selectable via index
        int eventNumber = 1;
        System.out.println("\nEvent List:\n");
        //displays all existing events
        for(CharityRun c : dataLoader.getEvents()){
            System.out.println(rightPad(eventNumber++ + ". " , 4) + rightPad(c.getVenue().getName() ,15) + " " + c.getDate() );
        }
        System.out.print("\nSelect Event from the list [1-50] >> ");
        String userSelection = userIn.nextLine();
        int eventIndex;
        //Input handling makes sure the user passed an integer and is in range
        try{
            eventIndex = Integer.parseInt(userSelection);
        }
        catch (NumberFormatException nfe){
            System.out.println("Invalid Input");
            return;
        }
        try{
            System.out.println(dataLoader.getEvents().get(eventIndex - 1));
        }
        catch (IndexOutOfBoundsException oobe){
            System.out.println("Number Out Of Bounds Try again");
            //recursively calls the list Events function to display the events again instead of returning to
            //the original prompt
            listEvents();
        }
    }

    //method that list venues
    private void listVenues(){
        int venueNumber = 1;
        //lists venues
        for(Venue v : dataLoader.getVenues()){
            System.out.println(rightPad(venueNumber++ + "." , 4) + v.getName());
        }
        //lets user select venue to lists its details
        System.out.print("\nSelect Venue from the list [1-18] >> ");
        String userSelection = userIn.nextLine();
        int eventIndex;
        //error handling for the selection
        try{
            eventIndex = Integer.parseInt(userSelection);
        }
        catch (NumberFormatException nfe){
            System.out.println("Invalid Input");
            return;
        }
        try{
            System.out.println(dataLoader.getVenues().get(eventIndex - 1));
        }
        catch (IndexOutOfBoundsException oobe){
            System.out.println("Number Out Of Bounds Try again");
            listVenues();
        }
    }

    //Matcher function takes String from the user and checks all existing competitors for a match
    public void matchCompetitor() {
        int competitorNumber = 1;
        System.out.println("\nEnter a competitors full/partial firstname and/or surname");
        System.out.print(">> ");
        //stores all competitors that match the specified String
        ArrayList<Competitor> matchedList = new ArrayList<>();
        String patternToMatch = userIn.nextLine();
        //creates a pattern object with case insensitivity
        Pattern pattern = Pattern.compile(patternToMatch, Pattern.CASE_INSENSITIVE);

        //Loop checks if theres a match on all competitors in the data loader object
        for(Competitor c : dataLoader.getCompetitors()){
            Matcher matcher = pattern.matcher(c.getFirstName() + " " + c.getSurName());
            if(matcher.find()){
                //adds matched competitors to the arraylist
                matchedList.add(c);
            }
        }
        //handles the case where no matches are found
        if(matchedList.size() == 0){
            System.out.println("No matches found");
        }
        //outputs the details of all matched competitors
        else{
            for(Competitor c : matchedList){
                System.out.println(competitorNumber++ + ".");
                System.out.println(c + "\n");
            }
        }
    }

    //function takes a string and right pads it with spaces until its as long as the supplied int
    private String rightPad(String s , int i){
        return String.format("%1$-" + i + "s" , s );
    }
}
