package Task2.Data;

import Task2.Business.*;
import java.util.ArrayList;
import java.util.Collections;

public class DataLoader {

    //Arraylists storing all the generated data
    private ArrayList<Venue> venues;
    private ArrayList<CharityRun> events;
    private ArrayList<Competitor> competitors;

    //constructor populates the arraylists
    public DataLoader(){
        venues = makeDummyVenues();
        events = generateEvents();
        competitors = createCompetitors();
        //sorts events so they appear in chronological order
        Collections.sort(events);
        populateEvents();
    }

    //Getter methods going to be used to retrieve the data in the presentation layer
    public ArrayList<Venue> getVenues() {
        return venues;
    }

    public ArrayList<CharityRun> getEvents() {
        return events;
    }

    public ArrayList<Competitor> getCompetitors() {
        return competitors;
    }

    //Creates Venue Objects
    private ArrayList<Venue> makeDummyVenues(){
        ArrayList<Venue> venues = new ArrayList<>();
        //String Arrays containing town and park names
        String[] townNames = new String[]{"London","Edinburgh","Birmingham","Bristol","York","Oxford","Glasgow","Sheffield","Reading","Liverpool","Coventry"};
        String[] parkNames = new String[]{"Snowdonia","Dartmoor","Lake District","Peak District","Exmoor","Richmond Park","South Downs"};

        //creates Town Objects and adds them to arraylist
        for(String s : townNames){
            Town town = new Town(s);
            venues.add(town);
        }
        //Creates Park Objects with a random amount of changing stations between 1- 10
        for(String s : parkNames){
            int changingFacilities = (int)(Math.random() * 10) + 1;
            Park park = new Park(s , changingFacilities);
            venues.add(park);
        }
        return venues;
    }

    //generates Event Objects
    public ArrayList<CharityRun> generateEvents(){
        ArrayList<CharityRun> events = new ArrayList<>();
        //guaranties that at least one venue will not have events as per the use cases
        int exclusionVenue = 8;

        //for loop will generate 50 events
        for(int i = 0; i < 50; i++){
            //retrieve a random venue;
            int indexOfRandomEvent = (int)(Math.random() * venues.size());
            //if the random venue is the exclusion venue move to the next venue instead
            if(indexOfRandomEvent == exclusionVenue){
                indexOfRandomEvent++;
            }
            //reference to the random venue object
            Venue activeVenue = venues.get(indexOfRandomEvent);

            //checks if active venue is a park object
            if(activeVenue.getClass().getSimpleName().equals("Park")){
                //variable that randomly selects whether the event should be a 5km run or a half marathon
                int whichRun = (int)(Math.random() * 2);

                //Creation of a 5km run
                if(whichRun == 0){
                    FiveKmRun funRun = new FiveKmRun(generateRandomDate() , (Park) activeVenue);
                    events.add(funRun);
                }
                //Creation of a HalfMarathon
                else{
                    HalfMarathon halfMarathon = new HalfMarathon(generateRandomDate() , activeVenue , (int)(Math.random() * 10) + 1);
                    events.add(halfMarathon);
                }
            }
            //if the venue is a town the only run type that is available is a halfMarathon hence only a half Marathon is created
            else{
                HalfMarathon halfMarathon = new HalfMarathon(generateRandomDate() , activeVenue , (int)(Math.random() * 10) + 1);
                events.add(halfMarathon);
            }
        }
        return events;
    }

    //method generates a random date in 22
    private int[] generateRandomDate(){
        //using an int array as my charity run function converts it into a date object
        int[] dateValues = new int[5];
        //don't want to deal with past dates
        dateValues[0] = 2022;
        //months don't need the + 1 at the end as the date object uses months as 0 indexed
        dateValues[1] = (int)(Math.random() * 12);
        //don't want to deal with irregular month days in dummy data
        dateValues[2] = (int)(Math.random() * 28) + 1;
        dateValues[3] = (int)(Math.random() * 24);
        //makes sure all minutes end in either a 0 or a 5 so no weird start times
        dateValues[4] = (int)(Math.random() * 12) * 5;

        return dateValues;
    }

    //method creates competitors with random ages between 8-60
    private ArrayList<Competitor> createCompetitors(){
        ArrayList<Competitor> competitors = new ArrayList<>();
        //Array of first/lastnames delimited by commas
        String[] competitorNames = "Jami Romanelli ,Macie Denker ,Maira Durfey ,Keri Cowboy ,Sarita Cazares ,Zofia Nasser ,Ethel Bucklew ,Camellia Cockett ,Elaine Freese ,Brigid Keagle ,Tory Gilbreath ,Hope Cremeans ,Ryan Mom ,Song Nathanson ,Rosario Pippen ,Darline Seabaugh ,Ivette Blackstock ,Danielle Fenner ,Arianne Dorr ,Kerrie Summa ,Elida Hayashi ,Mitchell Kellman ,Fay Blacker ,Jeannie Jacob ,Melodi Beardsley ,Erlene Meadows ,Elfrieda Leclair ,Hugo Mckinnon ,Risa Luebke ,Tasia Mccolley Renay Spinner ,Ronny Zayac ,Birdie Caudillo ,Ann Zawacki ,Kraig Stringfield ,Andra Lichtman ,Harlan Brott ,Estell Mike ,Vernon Crandell ,Suzann Shoults ,Krystal Whiting ,Deon Guarino ,Glory Sorrell ,Adrianne Kapinos ,Janice Nyberg ,Dan Brayboy ,Tonie Witcher ,Treva Matinez ,Jessica Belser ,Williams Edson ,Crista Nguyen ,Belva Kohen ,Marisol Griego ,Toccara Eisenman ,Ying Hosch ,Leesa Lauer ,Boyd Molitor ,Georgette Smolen ,Elvera Frew ,Luke Voegele ,Warren Carlsen ,Lyman Fulghum ,Caprice Deschamps ,Waylon Overfelt ,Lane Schloss ,Douglass Huizenga ,Cherly Towles ,Melany Hoeppner ,Robyn Main ,Danita Delao ,Chastity Reading ,Jewell Baskins ,Akiko Gunning ,Dario Bradwell ,Jeffry Simpler ,Madison Courter ,Roscoe Elliot ,Louetta Christoff ,Rosalia Sevy ,Candie Arnette".split(",");

        for(String s : competitorNames){
            //creates random age int
            int randomAge = (int)(Math.random() * 53) + 8;
            //splits the first and last name by space
            Competitor competitor = new Competitor(s.split(" ")[0] , s.split(" ")[1] , randomAge);
            competitors.add(competitor);
        }
        return competitors;
    }

    //method populates all previously created events with competitors
    private void populateEvents(){

        for(CharityRun e : events){
            for(Competitor c : competitors){
                //likely hood of a competitor joining the current event = 20%
                int likelyHoodOfEntry = (int)(Math.random() * 5);
                if(likelyHoodOfEntry == 0){
                    //ensures competitors under 16 do not join half marathons
                    if(c.getAge() > 15 && e.getClass().getSimpleName().equals("HalfMarathon")){
                        e.addEntry(c);
                    }
                    else if(e.getClass().getSimpleName().equals("FiveKmRun")){
                        e.addEntry(c);
                    }
                }
            }
            //rotates the competitors array list by a random interval each loop
            //this is done to ensure competitors at the front of the arraylist have a greater variance in
            //their entry numbers previously the first competitor would have all their entry numbers = 1
            Collections.rotate(competitors , (int)(Math.random() * 10) + 1);
        }
    }
}
