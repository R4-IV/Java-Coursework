package Task2.Business;

import java.util.ArrayList;
import java.util.Collections;

public class Competitor {

    //variables
    private String firstName;
    private String surName;
    private int age;
    //keeps track of all the run entries the competitor has
    private ArrayList<RunEntry> runEntries = new ArrayList<>();

    //constructor
    public Competitor(String firstName , String surName , int age){
        this.firstName = firstName;
        this.surName = surName;
        this.age = age;
    }

    //getters
    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }

    public int getAge() {
        return age;
    }

    //adds an entry object to the entry arraylist
    public void addEntry(RunEntry entry){
        runEntries.add(entry);
    }

    //to String method
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("----------------------------------------------------------------------------------\n");
        sb.append("| First Name            |" + rightPad(firstName , 55 ) + "|\n");
        sb.append("| Surname               |" + rightPad(surName , 55) + "|\n");
        sb.append("| Age                   |" + rightPad(String.valueOf(age) , 55 )+ "|\n");
        sb.append("----------------------------------------------------------------------------------\n");
        sb.append("Event Entries:\n");
        sb.append("----------------------------------------------------------------------------------\n");
        sb.append("| Venue Name    | Event Type     | Entry Number    | Event Date   | Start Time   |\n");
        sb.append("----------------------------------------------------------------------------------\n");
        Collections.sort(runEntries);
        //outputs all the user event entries
        for(RunEntry r : runEntries){
            sb.append("|" + rightPad(r.getCharityRun().getVenue().getName() , 14));
            sb.append("|" + rightPad(r.getCharityRun().getClass().getSimpleName() , 15));
            sb.append("|" + rightPad(String.valueOf(r.getEventNumber()),16));
            sb.append("|" + rightPad(r.getCharityRun().getDate() , 13));
            sb.append("|" + rightPad(r.getCharityRun().getStartTime() , 13) + "|\n");
        }
        //handles possible generation where a competitor does not participate in any events 
        //this is likely to occur with competitors under 16 as the joining restrictions are greater for them 
        if(runEntries.isEmpty()){
            sb.append("This competitor has not registered for any events yet");
        }
        else{   
        sb.append("----------------------------------------------------------------------------------\n");
        }
        return sb.toString();
    }

    //right pad method offers fixed string length based on the supplied integer
    private String rightPad(String s , int length){
        return String.format(" %1$-" + length + "s" , s );
    }
}
