package Task3.Utility;

import javafx.scene.layout.Pane;

//static class with utility methods used across the program
public class CustomUtil {

    //method receives a one or two digit integer and returns a string of 2 characters
    public static String addLeadingZero(int timeUnit){
        String output;
        //if the input is single digit a leading 0 is prepended to it
        if(timeUnit < 10){
            output = "0" + String.valueOf(timeUnit);
        }
        else{
            output = String.valueOf(timeUnit);
        }
        return output;
    }

    //method allows setting of specified width and height to Pane Object in a much quicker way
    public static void setPaneSize(Pane pane , double height , double width){
        //sets all the height related parameters
        pane.setPrefHeight(height);
        pane.setMinHeight(height);
        pane.setMaxHeight(height);
        //sets all the width related parameters
        pane.setPrefWidth(width);
        pane.setMinWidth(width);
        pane.setMaxWidth(width);
    }
}
