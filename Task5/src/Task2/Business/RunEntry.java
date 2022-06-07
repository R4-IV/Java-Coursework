package Task2.Business;

public class RunEntry implements Comparable {

    //variables
    private int eventNumber;
    private Competitor competitor;
    private CharityRun charityRun;

    public RunEntry(int eventNumber , Competitor competitor){
        this.eventNumber = eventNumber;
        this.competitor = competitor;
        //adds this entry to the competitor
        this.competitor.addEntry(this);
    }

    //assigns this entry to an event this happens automatically in the Charity Run class
    //when an entry is created
    public void assignEvent(CharityRun run){
        charityRun = run;
    }

    //getters
    public CharityRun getCharityRun(){
        return charityRun;
    }

    public int getEventNumber(){
        return eventNumber;
    }

    //allows run entries to be sorted chronologically just like events used when printing competitor
    //event participation, this ensures that all the listed events the competitor is participating in are shown
    //in descending order by date.
    @Override
    public int compareTo(Object o) {
        if(this.getCharityRun().date.before(((RunEntry) o).charityRun.date)){
            return -1;
        }
        if(((RunEntry) o).charityRun.date.before(this.getCharityRun().date)){
            return 1;
        }
        return 0;
    }
}
