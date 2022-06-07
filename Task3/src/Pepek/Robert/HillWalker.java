package Pepek.Robert;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.TimerTask;

public class HillWalker extends TimerTask {
    //tracks the state of the hill walker
    private int state = 0;
    //sets a constant for altitude height
    private final int ALTITUDE = 1500;
    //Variables to store the time values Strings used because integers can't display leading zeros
    private int hours;
    private int minutes;
    private int seconds = 0;
    private String minutesString = "";
    private String hoursString = "";

    //constructor initialises local time and displays the mode and time of state 0
    HillWalker(){
        initTime();
        System.out.println("Mode : " + provideMap().get(state));
        System.out.println("Time : " + hoursString + ":" + minutesString );
    }
    //function called in main method every second makes the clock run
    @Override
    public void run() {
        seconds++;
        if(seconds == 60){
            seconds = 0;
            minutes++;
            minutesString = formatTime(minutes);
        }
        if(minutes == 60){
            minutes = 0;
            hours++;
            minutesString = formatTime(minutes);
            hoursString = formatTime(hours);
        }
        if(hours == 24){
            hours = 0;
            hoursString = formatTime(hours);
        }
    }

    //private method to convert integer hours or minutes to string values and add leading zeros if
    //they are too short
    private String formatTime(int timeInt){
        String timeString = String.valueOf(timeInt);
        if(timeString.length() == 1){
            timeString = "0" + timeString;
        }
        return timeString;
    }

    //private method that loads the current local time to the time string variables, called in the constructor.
    private void initTime(){
        LocalTime now = LocalTime.now();
        minutes = now.getMinute();
        hours = now.getHour();
        minutesString = formatTime(minutes);
        hoursString = formatTime(hours);
    }

    //private method provides a map with state to mode conversion used to display what mode the hill walker is
    //in
    private HashMap<Integer,String> provideMap(){
        HashMap<Integer,String> stateMode = new HashMap<Integer, String>();
        stateMode.put(0,"Display Time");
        stateMode.put(1,"Altimeter");
        stateMode.put(2,"Set Hours");
        stateMode.put(3,"Set Minutes");
        return stateMode;
    }
    //public method for pressing mode will behave differently based on the current state.
    public void pressMode(){
        HashMap<Integer,String> map = provideMap();
        switch (state){
            case 0:
                state = 1;
                System.out.println("Mode: " + map.get(state));
                System.out.println("Altitude: " + ALTITUDE);
                break;
            case 1:
            case 3:
                state = 0;
                System.out.println("Mode : " + map.get(state));
                System.out.println("Time : " + hoursString + ":" + minutesString );
                break;
            case 2:
                state = 3;
                System.out.println("Mode: " + map.get(state));
                System.out.println("Current Minutes :" + minutesString );
                break;
        }
    }
    //public method for pressing set will behave differently based on the current state.
    public void pressSet(){
        HashMap<Integer,String> map = provideMap();
        switch (state){
            case 0:
                state = 2;
                System.out.println("Mode: " + map.get(state));
                System.out.println("Current Hours :" + hoursString );
                break;
            case 1:
                System.out.println("Mode: " + map.get(state));
                System.out.println("Altitude: " + ALTITUDE);
                break;
            case 2:
                System.out.println("Mode: " + map.get(state));
                //handles hour incrementation.
                if(hours == 24){
                    hours = 0;
                }
                else{
                    hours++;
                }
                hoursString = formatTime(hours);
                System.out.println("Current Hours :" + hoursString );
                break;
            case 3:
                System.out.println("Mode: " + map.get(state));
                //handles minute incrementation
                if(minutes == 60){
                    minutes = 0;
                }
                else{
                    minutes++;
                }
                minutesString = formatTime(minutes);
                System.out.println("Current Minutes :" + minutesString );
                break;
        }
    }
}
