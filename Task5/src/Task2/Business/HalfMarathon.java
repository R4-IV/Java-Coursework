package Task2.Business;

public class HalfMarathon extends CharityRun {

    //half marathons need to have water stations
    private int numOfWaterStations;
    //constructor
    public HalfMarathon(int[] dateValues , Venue venue, int numOfWaterStations) {
        super(dateValues ,venue);
        this.numOfWaterStations = numOfWaterStations;
    }

    //to string uses superclass partial method
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(partialToString());
        //adds the half marathon specific water stations cell
        sb.append("| Water Stations        |" + rightPad(String.valueOf(numOfWaterStations)) + "|\n");
        sb.append("--------------------------------------------------\n");
        return sb.toString();
    }

    //compareTo method allows sorting events chronologically
    @Override
    public int compareTo(Object o) {
        //comparing with five km run
        if(o.getClass().getSimpleName().equals("FiveKmRun")){
            if(this.date.before(((FiveKmRun) o).date)){
                return - 1;
            }
            else if(((FiveKmRun) o).date.before(this.date)){
                return 1;
            }
            else{
                return 0;
            }
        }
        //comparing with another half marathon
        if(o.getClass().getSimpleName().equals("HalfMarathon")){
            if(this.date.before(((HalfMarathon) o).date)){
                return - 1;
            }
            else if(((HalfMarathon) o).date.before(this.date)){
                return 1;
            }
            else{
                return 0;
            }
        }
        return 0;
    }
}
