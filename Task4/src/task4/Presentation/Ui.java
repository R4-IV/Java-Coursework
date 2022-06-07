package task1.Presentation;

import task1.Business.Album;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ui {
    //private data albums loaded from data layer
    private List<Album> albums;
    //constructor takes an album list and then sorts it via ranking
    public Ui(List<Album> albums) {
        this. albums = albums;
        Collections.sort(albums);
    }

    //public function used to display user choices and perform functions based on them
    public void displayUi(){
        Scanner userIn = new Scanner(System.in);
        String userSelection;
        while(true){
            System.out.println("List albums.........1");
            System.out.println("Select album........2");
            System.out.println("Search titles.......3");
            System.out.println("Exit................0");
            System.out.println();
            System.out.print("Enter choice:> ");
            //uses next line so that if the user inputs values with spaces the left over input will not affect
            //the subsequent input
            userSelection = userIn.nextLine();
            System.out.println();
            switch (userSelection){
                case "0":
                    //only place where scanner is closed as reopening the system.in stream is impossible
                    userIn.close();
                    System.exit(0);
                    break;
                case "1":
                    //lists albums using the album toString method
                    System.out.println(listAlbums());
                    break;
                case "2":
                    selectAlbum(userIn);
                    break;
                case "3":
                    searchTitles(userIn);
                    break;
                    //easy input validation.
                default:
                    System.out.println("Invalid Selection try again");
                    continue;
            }
        }
    }

    //private method to list albums
    private String listAlbums(){
        //variables tracking lengths of title and artist allows for dynamic sizing of the album list
        int maxTitleLength = 0;
        int maxArtistLength = 0;
        //updates the max values
        for(Album a : albums){
            if(a.getTitle().length() > maxTitleLength){
                maxTitleLength = a.getTitle().length();
            }
            if(a.getArtist().length() > maxArtistLength){
                maxArtistLength = a.getArtist().length();
            }
        }
        //string builder object used to create the output
        StringBuilder sb = new StringBuilder();
        //creates the top portion "-----..." of the album list
        int boundingLineSize = 37 + maxTitleLength + maxArtistLength;
        String boundingLineString = "";
        for(int i = 0; i < boundingLineSize; i++){
            boundingLineString += "-";
        }
        //creates the album list header (The table columns)
        String albumListHeader = "| Rank | Title";
        for(int i = 0; i < maxTitleLength; i++){
            albumListHeader += " ";
        }
        albumListHeader += "| Artist";
        for(int i = 0; i < maxArtistLength - 1; i++){
            albumListHeader += " ";
        }
        albumListHeader += "| Year | Sales |";

        sb.append(boundingLineString + "\n");
        sb.append(albumListHeader + "\n");
        sb.append(boundingLineString + "\n");
        String albumEntry = "|";
        //loop creates an Album entry correctly formatted to the same size as the Table Header
        for(Album a : albums){
            if(String.valueOf(a.getRanking()).length() == 1){
                albumEntry += "    " + a.getRanking() + " | ";
            }
            else{
                albumEntry += "   " + a.getRanking() + " | ";
            }
            albumEntry += a.getTitle();
            for(int i = 0; i < maxTitleLength + 5 - a.getTitle().length(); i++){
                albumEntry += " ";
            }
            albumEntry += "| " + a.getArtist();
            for(int i = 0; i < maxArtistLength + 5 - a.getArtist().length(); i++){
                albumEntry += " ";
            }
            albumEntry += "| " + a.getYear() + " | ";
            if(a.getSales().length() == 4){
                albumEntry += a.getSales() + "  |";
            }
            else{
                albumEntry += a.getSales() + " |";
            }
            sb.append(albumEntry + "\n");
            albumEntry = "|";
        }
        sb.append(boundingLineString + "\n");
        //returns the formatted table
        return sb.toString();
    }

    //private method for album selection
    private void selectAlbum(Scanner userIn){
        System.out.print("Enter album rank from list [1 - 20] :> ");
        String userSelection = userIn.nextLine();
        System.out.println();
        try{
            //try catch loop to see if the user input is an integer
            int albumSelection = Integer.parseInt(userSelection);
            try{
                //try catch loop checks to see if the entered integer is within range of the array
                System.out.println(albums.get(albumSelection - 1));
            }catch (IndexOutOfBoundsException iobe){
                //if the selection is out of bounds return error and exit the loop
                System.out.println("Selection out of bounds");
                System.out.println();
                return;
            }
        }catch (NumberFormatException nfe){
            //if the input is not a integer prints invalid input and returns to the loop
            System.out.println("Invalid Input");
            System.out.println();
            return;
        }
    }

    //private method that allows the user to enter a phrase and get the songs that match as the output
    private void searchTitles(Scanner userIn){
        System.out.println();
        System.out.print("Enter search word or phrase > ");
        String searchPhrase  = userIn.nextLine();
        System.out.println();
        //creates a String that is the divider between each of tje albums
        String divider = "----";
        //Regex pattern with case insensitivity
        Pattern searchRegex = Pattern.compile(searchPhrase , Pattern.CASE_INSENSITIVE);
        String outputString = "";
        //for loop that returns matching song titles and changes the matching string to uppercase
        for(int i = 0; i < albums.size(); i++){
            //variable tracks whether the matching song is the first for that specific album
            boolean firstMatchingSong = true;
            //loops over each of the tracks in the album
            for(int j = 0 ; j < albums.get(i).getTracks().size(); j++){
                String currentTrackTitle = albums.get(i).getTracks().get(j).getTitle();
                Matcher matcher = searchRegex.matcher(currentTrackTitle);
                //checks if the string entered matches the current song
                if(matcher.find()){
                    //if this is the first song that matches in the specified album output the Album header
                    if(firstMatchingSong){
                        outputString += divider + "\n";
                        outputString += "Artist (" + albums.get(i).getArtist() + ") Album (" + albums.get(i).getTitle() + ")\n";
                        outputString += "Matching song title(s):\n";
                        outputString += divider + "\n";
                        firstMatchingSong = false;
                    }
                    //replaces the matching pattern with an uppercase version of the pattern
                    currentTrackTitle = matcher.replaceAll(searchPhrase.toUpperCase());
                    outputString += "Track " + (albums.get(i).getTracks().get(j).getTrackNumber()) + ". " + currentTrackTitle + "\n";
                }
                //creates a space under the last track of the current album creating some spacing
                if(j == albums.get(i).getTracks().size()-1 && firstMatchingSong == false){
                    outputString += "\n";
                }
            }
        }
        //handles the case where the entered string has no matching values in the track lists
        if(outputString.length() == 0){
            System.out.println("no matches found");
            System.out.println();
        }
        else{
            //outputs the created matching song titles string
            System.out.println(outputString);
        }
    }
}
