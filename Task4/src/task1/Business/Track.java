package task1.Business;

public class Track {
    //private variables
    private final int trackNumber;
    private final String title;
    private final String minutes;
    private final String seconds;

    //constructor
    public Track(int trackNumber, String title, String minutes, String seconds) {
        this.trackNumber = trackNumber;
        this.title = title;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    //required getter methods used in option 3 to display matching songs
    public int getTrackNumber() {
        return trackNumber;
    }

    public String getTitle() {
        return title;
    }

    //private function used to handle varying lengths of minutes and seconds
    private String handleSingleDigit(String singleDigit){
        if(singleDigit.length() == 1){
            return "   ";
        }
        else{
            return "  ";
        }
    }
    //overloaded toString method takes max track length from the Album class to print correct format track entry
    public String toString(int maxTrackLength) {
        int rightOffset = (maxTrackLength + 5) - title.length();
        String rightString = "|";
        for(int i = 0; i <rightOffset; i++){
            rightString = " " + rightString;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("|" + trackNumber + handleSingleDigit(String.valueOf(trackNumber)) + "|" + title + rightString + handleSingleDigit(minutes) + minutes + "|" + handleSingleDigit(seconds) + seconds + "|");
        return sb.toString();
    }
}
