package task1.Business;

import java.util.List;

public class Album implements Comparable<Album>{
    //private data members following the provided specification
    private final int ranking;
    private final String title;
    private final String artist;
    private final int year;
    private final String sales;
    //using collection type list to reduce coupling this allows replacement of arraylist with linked list in the future
    //assuming that the data will then require insertion and deletion as a linked list is better suited for that
    private final List<Track> tracks;
    //longest track name is variable stored so that toString method knows how big to create the bounding boxes
    private final int longestTrackName;

    //constructor used in the data package to create album objects
    public Album(int ranking, String title, String artist, int year, String sales, List<Track> tracks, int longestTrackName) {
        this.ranking = ranking;
        this.title = title;
        this.artist = artist;
        this.year = year;
        this.sales = sales;
        this.tracks = tracks;
        this.longestTrackName = longestTrackName;
    }
    //Getter Methods required as per spec
    public int getRanking() {
        return ranking;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getYear() {
        return year;
    }

    public String getSales() {
        return sales;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    //to string method called when option 2 is selected in the Ui
    @Override
    public String toString() {
        //code that creates the string ----- that bound the top and bottom of the track list
        String trackDivider = "";
        int trackDividerLength = 17 + 5 + longestTrackName;
        for(int i = 0; i < trackDividerLength; i++){
            trackDivider += "-";
        }
        //creates the space in the table header that accommodates the longest track name in that specific album
        String titleHeader = "Title";
        for(int i = 0; i < longestTrackName; i++){
            titleHeader += " ";
        }
        //Using string builder this would work just as well with string concatenation
        StringBuilder sb = new StringBuilder();
        sb.append("Album title:          " + title + "\n");
        sb.append("Artist:               " + artist + "\n");
        sb.append("Year of release:      " + year + "\n");
        sb.append("Sales to date:        " + sales + "\n\n");
        sb.append("Track List:           " + "\n");
        sb.append(trackDivider + "\n");
        sb.append("|No. |" + titleHeader + "|Mins|Secs|" + "\n");
        sb.append(trackDivider + "\n");
        for(Track t : tracks){
            sb.append(t.toString(longestTrackName) + "\n");
        }
        sb.append(trackDivider + "\n");
        //returns a string that can be called with a print method
        return sb.toString();
    }
    //compareTo method implemented from Comparable interface
    //the default txt is already sorted by rank however different files might not have the same formatting
    @Override
    public int compareTo(Album otherAlbum) {
        if(this.ranking > otherAlbum.ranking){
            return 1;
        }
        else if(this.ranking < otherAlbum.ranking){
            return -1;
        }
        else{
            return 0;
        }
    }
}
