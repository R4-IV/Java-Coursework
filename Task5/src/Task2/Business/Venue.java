package Task2.Business;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Venue {

    //protected variables for easy access from derived classes
    protected int numChangingFacilities;
    protected String name;
    //keeps track of all the events scheduled at the venue
    protected ArrayList<CharityRun> eventList = new ArrayList<>();

    //constructor
    public Venue(String name){
        this.name = name;
    }

    //getters
    public String getName() {
        return name;
    }

    public ArrayList<CharityRun> getEventList(){
        return eventList;
    }

    //method adds an event(halfMarathon/5kmRun) to this venue
    public void addEvent(CharityRun charityRun){
        eventList.add(charityRun);
    }

    //toString method
    @Override
    public String toString() {
        Collections.sort(eventList);
        StringBuilder sb = new StringBuilder();
        sb.append("--------------------------------------------------\n");
        sb.append("| Venue Name            |" + rightPad(name , 23)+ "|\n");
        sb.append("| Venue Type            |" + rightPad(this.getClass().getSimpleName() , 23)+ "|\n");
        if(this.getClass().getSimpleName().equals("Park")){
            sb.append("| Changing Facilities   |" + rightPad(String.valueOf(numChangingFacilities) ,23)+ "|\n");
        }
        sb.append("--------------------------------------------------\n");
        sb.append("EventList:\n");
        if(eventList.size() == 0){
            sb.append("No events at this venue\n");
        }
        else{
            sb.append("--------------------------------------------------\n");
            sb.append("| Event Type     | Event Date    | Start Time    |\n");
            sb.append("--------------------------------------------------\n");
            for(CharityRun c : eventList){
                sb.append("|" + rightPad(c.getClass().getSimpleName() , 15));
                sb.append("|" + rightPad(c.getDate() , 14));
                sb.append("|" + rightPad(c.getStartTime() , 14) + "|\n");
            }
            sb.append("--------------------------------------------------\n");
        }
        return sb.toString();
    }

    //right pad method creates a fixed length string of length determined by the the passed int
    private String rightPad(String s , int length){
        return String.format(" %1$-" + length + "s" , s );
    }
}
