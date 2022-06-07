package Pepek.Robert;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static String chosenDifficulty = "";
    public static String difficultyIndicator = "";
    //value that is printed to represent hidden name values.
    public static String placeholderVal = "xxxxxxxxxxxxxx";
    public static Scanner userInput = new Scanner(System.in);
    //integers storing the first and second coordinates when the user enters them.
    public static int first = 0;
    public static int second = 0;
    //contains valid coordinate boolean.
    public static boolean isValidCoordinate = false;
    //tracks the amount of matches allowing the game to end once they reach a specific amount.
    public static int matches = 0;
    //tracks the amount of guesses the user has made.
    public static int guesses = 0;
    
    //function handles user difficulty selection and input error handling.
    public static void beginGame(){
        //stores the users chosen difficulty
        String choice = "";

        //maps the users chosen difficulty an integer number to a string so that the correct file can be loaded in.
        HashMap<Integer,String> difficultyMap = new HashMap<Integer, String>();
        difficultyMap.put(0 , "EXIT");
        difficultyMap.put(1 , "small.txt");
        difficultyMap.put(2 , "medium.txt");
        difficultyMap.put(3 , "large.txt");

        //instructions for the user.
        System.out.print("--------------------------\n");
        System.out.print("Welcome to the memory game\n");
        System.out.print("--------------------------\n\n");
        System.out.print("Easy........1\n");
        System.out.print("Normal......2\n");
        System.out.print("Hard........3\n");
        System.out.print("Quit Now....0\n");

        try{
            //retrieves the selected string from hashmap
            System.out.print("\nEnter your preferred difficulty >> ");
            int userIn = userInput.nextInt();
            switch(userIn){
                case 1:
                    difficultyIndicator = "Easy";
                    break;
                case 2:
                    difficultyIndicator = "Normal";
                    break;
                case 3:
                    difficultyIndicator = "Hard";
            }
            choice = difficultyMap.get(userIn);
            //Checks if the user selected the quit option and exits the program if it is.
            if(choice.equals("EXIT")){
                System.out.println("Thanks for playing");
                userInput.close();
                System.exit(0);
            }
            //updates public static string with the selected difficulty path.
            chosenDifficulty = choice;
        }
        catch(InputMismatchException | NullPointerException e){
            //checks if the user input is a number and if it is then is it range of the above hashmap.
            System.out.println("\n[ Not A Valid Option Try Again ]\n");
            userInput.nextLine();
            beginGame();
        }
    }

    //function checks whether the use input coordinate is valid and non repeated
    public static void checkCoordinate(String[][] board , String[][] displayedBoard , int columns , int whichCoord) {
        while (!isValidCoordinate) {
            int container = 0;
            //Scanner Object captures user coordinate input as a string
            String EnteredCoordinate = userInput.next();
            //checks if the entered strings are 2 characters long else will display an error and restart the loop.
            if (EnteredCoordinate.length() != 2) {
                System.out.println("Not a valid coordinate");
                System.out.print("\nRe-Enter Coordinate => ");
                continue;
            }
            //try to parse string to int if this fails display error and restart the loop.
            try {
                container = Integer.parseInt(EnteredCoordinate);
            } catch (NumberFormatException e) {
                System.out.println("Not a valid coordinate");
                System.out.print("\nRe-Enter Coordinate => ");
                continue;
            }
            //splits the entered coordinate into x and y components
            int y = container / 10;
            int x = container % 10;
            //checks if both coordinates are within board range.
            if ((y >= 0 && y < columns) && (x >= 0 && x < columns)) {
                //checks if the coordinate has been previously entered.
                if (!displayedBoard[y][x].equals(placeholderVal)) {
                    System.out.println("Box has already been chosen");
                    System.out.print("\nRe-Enter Coordinate => ");
                    continue;
                }
                else {
                    //displays the selected coord name and breaks out of the loop.
                    displayedBoard[y][x] = board[y][x];
                    isValidCoordinate = true;
                }
            }
            else {
                System.out.println("Coordinates outside of board range");
                System.out.print("\nRe-Enter Coordinate => ");
                continue;
            }
            //selects which coord the user is inputting.
            if (whichCoord == 1) {
                first = container;
            } else {
                second = container;
            }
        }
    }
    //function to check if the 2 selected names match if they don't the board resets.
    public static void checkIfMatch(String[][] displayedBoard){
        //splits coordinate containers to their x , y components
        int x1 , y1 , x2 ,y2;
        y1 = first/10;
        x1 = first%10;
        y2 = second/10;
        x2 = second%10;

        if(displayedBoard[y1][x1].equals(displayedBoard[y2][x2])){
            System.out.println("\nMatch Found");
            matches++;
        }
        else{
            System.out.println("\nMatch not Found");
            displayedBoard[y1][x1] = placeholderVal;
            displayedBoard[y2][x2] = placeholderVal;
        }
        guesses++;
    }
    //function used to display board based on the amount of columns
    public static void renderBoard(String[][] board , int columns){
        System.out.print("  ");
        for(int i = 0; i < columns; i++){
            System.out.print("              " + i + "|");
        }
        System.out.print("\n");
        System.out.print("--");
        for(int i = 0; i < columns; i++){
            System.out.print("----------------");
        }
        for(int i = 0; i < board.length; i++){
            System.out.print("\n");
            System.out.print(i + " ");
            for(int j = 0; j < board[i].length; j++){
                String currentWord = board[i][j];
                //checks string length and adds leading and trailing spaces based on its length so that the word is centered
                if(currentWord.length() == 14){
                    System.out.print("[" + currentWord +"]");
                }
                else{
                    int wordLength = currentWord.length();
                    int spaceIndicator = 14 - wordLength;
                    System.out.print("[");
                    for(int a = 1; a <= spaceIndicator; a++){
                        System.out.print(" ");
                        if(a == spaceIndicator/2){
                            System.out.print(currentWord);
                        }
                    }
                    System.out.print("]");
                }
            }
        }
    }
    //function used to handle user input between guesses.
    public static void userChoiceGuessOrQuit(){

        System.out.println("Make a Guess........0");
        System.out.println("Exit Now............1");
        System.out.println("\nYou made " + guesses + " guess(es) so far\n");
        System.out.print(">> ");
        int guessOrQuit = 0;

        try{
            guessOrQuit = userInput.nextInt();
        }
        //error handling for user input it will capture any non integer input.
        catch (InputMismatchException e){
            System.out.println("Not a valid selection");
            userInput.nextLine();
            userChoiceGuessOrQuit();
        }

        if(guessOrQuit == 1){
            System.out.println("Thanks for playing");
            userInput.close();
            System.exit(0);
        }
        //if the input is greater than 1 or less than 0 print an error message and call the function recursively.
        if(guessOrQuit > 1 || guessOrQuit < 0){
            System.out.println("Not a valid selection");
            userInput.nextLine();
            userChoiceGuessOrQuit();
        }
    }
    //main function runs the game
    public static void main(String[] args) {

        beginGame();
        System.out.println("\nChosen difficulty " + difficultyIndicator + "\n");
        System.out.println("INSTRUCTIONS:");
        System.out.println("Select Row first then column in the following format:");
        System.out.println("Entering \"23\" without quotes will result in the selection of row 2 column 3.");
        System.out.println("Begin the game by selecting \"Make a Guess\" or quit at any time selecting \"Exit Now\"\n");
        //gets filepath for the input file text.
        String pathName = System.getProperty("user.dir") + File.separator + chosenDifficulty;
        File activeFile = new File(pathName);

        //creates a scanner for file input.
        Scanner fileLoader = null;
        //arraylist used to store all the loaded names , using it as it does not need a predefined size.
        ArrayList<String> nameBank = new ArrayList<String>();

        try{
            //loads 2 of each name in "currentName" into the nameBank array
            fileLoader = new Scanner(activeFile);
            while(fileLoader.hasNext()){
                String currentName = fileLoader.nextLine();
                nameBank.add(currentName);
                nameBank.add(currentName);
            }
        }
        catch (FileNotFoundException e){
            System.out.println("selected name file not found");
            System.exit(1);
        }
        finally {
            //closes the fileLoader scanner
            fileLoader.close();
        }
        //Creates a null string array that will hold the board format
        String[][] board = null;
        String[][] displayedBoard = null;
        int cols = 0;

        //updates the board based on the amount of items in the name bank array list
        //All the boards will be square ie. 4x4 , 6x6 , 8x8 columns are set so that the 2d array is the correct size.
        cols = (int)Math.sqrt(nameBank.size());
        board =  new String[cols][cols];
        displayedBoard = new String[cols][cols];

        //fills displayed array with the placeholder value so that names can't be seen.
        for(String[] row : displayedBoard){
            Arrays.fill(row , placeholderVal);
        }
        //loads random names from the arraylist in consecutive positions.
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                int randomVar = (int) Math.floor(Math.random() * (nameBank.size() - 1));
                board[i][j] = nameBank.get(randomVar);
                nameBank.remove(randomVar);
            }
        }
        //game loop
        while(true){
            System.out.print("\n");
            userChoiceGuessOrQuit();
            renderBoard(displayedBoard , cols);
            isValidCoordinate = false;
            //enter coordinates
            System.out.print("\n\nEnter first coordinate >> ");
            checkCoordinate(board , displayedBoard , cols , 1);
            renderBoard(displayedBoard , cols);
            //resets valid coord bool from previous coordinate
            isValidCoordinate = false;
            System.out.print("\n\nEnter second coordinate >> ");
            checkCoordinate(board , displayedBoard , cols , 0);
            renderBoard(displayedBoard , cols);
            //checks if the selected coords match.
            checkIfMatch(displayedBoard);
            //check if the game should end based on the amount of matches completed.
            if(matches == Math.pow(cols , 2)/2){
                System.out.println("\n\nYou win , you completed the game with " + guesses + " guesses");
                //closes System.in scanner it can't be closed after user input as once the system.in stream is closed
                //it can't be reopened hence every instance where the program would terminate has a close.
                userInput.close();
                System.exit(0);
            }
        }
    }
}
