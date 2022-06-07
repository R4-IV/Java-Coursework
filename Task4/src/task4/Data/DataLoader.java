package task1.Data;

import task1.Business.Track;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import task1.Business.Album;

//class responsible for parsing data to provide the other subpackages an arraylist of albums
public class DataLoader {
    //private variables
    private String filename;
    private String pathName;
    private File albumSource;
    private Scanner fileScanner = null;

    //constructor initialises the values for file pathname
    public DataLoader(){
        filename = "albums.txt";
        pathName = System.getProperty("user.dir") + File.separator + filename;
        albumSource = new File(pathName);
    }

    //public function that returns an Album List
   public List<Album> parseSource(){
        try {
            //sets the fileScanner in a try catch as file not found exception is checked
            fileScanner = new Scanner(albumSource);
            //line used to check when the last album object ends and next one begins
            int line = 0;
            //track number starting from 1 incremented and used to create a Track Object
            int trackNo = 1;
            //variable tracks the longest track length
            int maxTrackLength = 0;
            //holds tracks for a single Album
            List<Track> tracks = new ArrayList();
            //holds Albums
            List<Album> albumList = new ArrayList<>();
            //used to hold the details using string.split for the first couple variables delimited by :
            String[] values = new String[5];
            //while loop used runs while the scanner still has data in the buffer
            while(fileScanner.hasNext()){
                String currentLine = fileScanner.nextLine();
                //loads the first line of the input into the array by delimiting via ":"
                if(line == 0){
                    values = currentLine.split(":");
                    line++;
                }
                //if the line contains "-----" loads all data into an Album Object
                else if(currentLine.substring(0 , 5).compareTo("-----") == 0){
                    line = 0;
                    ArrayList<Track> tracksCopy = new ArrayList<>(tracks);
                    Album album = new Album(Integer.parseInt(values[0]) , values[1] , values[2] , Integer.parseInt(values[3]) , values[4]  , tracksCopy, maxTrackLength);
                    albumList.add(album);
                    tracks.clear();
                    maxTrackLength = 0;
                    trackNo = 1;
                }
                //adds the current line to the track arraylist by creating a Track object
                else{
                    if(currentLine.length() > maxTrackLength){
                        maxTrackLength = currentLine.length();
                    }
                    String title = currentLine.split("\\(")[0];
                    String time = currentLine.split("\\(")[1];
                    String minutes = time.split(":")[0];
                    String seconds = time.split(":")[1];
                    seconds = seconds.substring(0 , seconds.length()-1);

                    Track track = new Track(trackNo , title , minutes , seconds);
                    tracks.add(track);
                    trackNo++;
                }
            }
            //function returns album list that can be passed to the presentation layer
            return albumList;
            //catches file not found exception and exits the program
        } catch (FileNotFoundException fnfe) {
            System.out.println("Error: File not found");
            System.exit(0);
        }finally {
            //closes scanner regardless of the try catch result
            fileScanner.close();
        }
        //function needs a return statement this will never occur as the catch exits the program
        return null;
    }

}
