package Task2.Business;

//concrete class for venue only difference is that it initialises changing facilities
public class Park extends Venue {
    public Park(String name , int numChangingFacilities) {
        super(name);
        this.numChangingFacilities = numChangingFacilities;
    }
}
