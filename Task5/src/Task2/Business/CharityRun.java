package Task2.Business;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

//abstract class implements the common methods for the half marathon and fun run classes
public abstract class CharityRun implements Comparable {

    //date and venue object using protected to access in derived classes
    protected Date date;
    protected Venue venue;

    //Date format
    protected String datePattern = "yyyy-MMM-dd";
    protected SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);

    //Time format
    protected String TimePattern = "KK:mma";
    protected SimpleDateFormat simpleTimeFormat = new SimpleDateFormat(TimePattern);

    //Arraylist keeps track of all the run entries for the event
    protected ArrayList<RunEntry> runEntries = new ArrayList<>();

    //constructor
    protected CharityRun( int[] dateValues , Venue venue){
        this.venue = venue;
        this.date = new GregorianCalendar(dateValues[0] , dateValues[1] ,dateValues[2] , dateValues[3] , dateValues[4]).getTime();
        //adds this event to the corresponding venue object
        venue.addEvent(this);
    }

    //getter methods
    public String getDate() {
        return simpleDateFormat.format(date);
    }

    public String getStartTime(){
        return simpleTimeFormat.format(date);
    }

    public Venue getVenue(){
        return venue;
    }

    //adds Event entry to the event ensuring that the entries are unique
    public void addEntry(Competitor competitor){
        int eventNumber = runEntries.size() + 1;
        RunEntry entry = new RunEntry(eventNumber , competitor);
        runEntries.add(entry);
        //assigns this event to the created entry
        entry.assignEvent(this);

    }

    //method outputs the common part of the 2 string for its 2 derived classes
    protected String partialToString(){
        StringBuilder sb = new StringBuilder();
        sb.append("--------------------------------------------------\n");
        sb.append("| Venue Name            |" + rightPad(venue.getName()) +"|\n");
        sb.append("| Venue Type            |" + rightPad(venue.getClass().getSimpleName()) + "|\n");
        sb.append("| Event Type            |" + rightPad(this.getClass().getSimpleName()) + "|\n");
        sb.append("| Event Date            |" + rightPad(simpleDateFormat.format(date)) + "|\n");
        sb.append("| Start Time            |" + rightPad(simpleTimeFormat.format(date)) + "|\n");
        sb.append("| Participant Number    |" + rightPad(String.valueOf(runEntries.size())) + "|\n");

        return sb.toString();
    }

    //right pad method creates fixed length strings
    protected String rightPad(String s){
        return String.format(" %1$-" + 23 + "s" , s );
    }

}
