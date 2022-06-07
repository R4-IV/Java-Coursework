package Task2.Business;

//concrete FiveKM Run class
public class FiveKmRun extends CharityRun {

    //fun runs only occur in a park hence a reference to it is here
    private Park venue;

    //constructor
    public FiveKmRun(int[] dateValues, Park venue) {
        super(dateValues , venue);
        this.venue = venue;
    }

    //implemented to String method uses the partial superclass method
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(partialToString());
        sb.append("--------------------------------------------------\n");
        return sb.toString();
    }

    //compare function allows sorting of events chronologically
    @Override
    public int compareTo(Object o) {
        //comparison with another FiveKMRun Object
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
        //Comparison with a HalfMarathon Object
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

