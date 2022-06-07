package Task3.Widgets;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import Task3.Button.LightControlMinus;
import Task3.Button.LightControlPlus;
import Task3.Utility.CustomUtil;
import java.util.HashMap;

//Widget that creates a row with 2 buttons +/- and 6 light diodes in the middle which the buttons can control
public class LightControlWidget extends HBox {

    //sets the default light level 0 being all off
    private int lightLevel = 0;
    //Stores the color the led boxes should be when they are off
    private String lightOff = "#333300";
    //stores the array of light diodes which can later be accessed via index
    private HBox[] lightPaneArray = new HBox[6];



    public LightControlWidget(){
        //sets the size of the widget
        CustomUtil.setPaneSize(this , 25 , 270);

        //creates the decrement light level button
        LightControlMinus minus = new LightControlMinus(this);
        //adds the minus button and sets its margin to this widget
        this.getChildren().add(minus);
        this.setMargin(minus , new Insets( 0 , 1.5 , 0 ,0 ));

        //creates the 6 led boxes and adds them to the widget
        for(int i = 0; i < 6; i++){
            HBox lightPane = new HBox();
            CustomUtil.setPaneSize(lightPane , 20 , 30);
            //initialises them with the off color by default
            lightPane.setStyle("-fx-background-color:" + lightOff + ";");
            //adds them to the array so they can be referenced outside of this loop
            lightPaneArray[i] = lightPane;
            this.getChildren().add(lightPane);
            //sets a margin/spacer between each light
            this.setMargin(lightPane, new Insets(2.5 , 1,0 ,1 ));
        }
        //creates the increment light level button
        LightControlPlus plus = new LightControlPlus(this);
        this.getChildren().add(plus);
        this.setMargin(plus , new Insets(0 , 0 , 0 , 1.5));
    }

    //function returns table of Strings for all required light levels
    private HashMap<Integer ,String> provideTable(){

        HashMap<Integer , String > colorMAp = new HashMap<>();
        colorMAp.put(1 , "#666600");
        colorMAp.put(2 , "#999900");
        colorMAp.put(3 , "#cccc00");
        colorMAp.put(4 , "#ffff00");
        colorMAp.put(5 , "#ffff99");
        colorMAp.put(6 , "#ffffcc");

        return colorMAp;
    }

    //function used to increase light level and update the leds to reflect that fact
    public void increaseLightLevel(){
        //if not max light level
        if(lightLevel != 6){
            lightLevel++;
            HashMap<Integer , String> colorMap = provideTable();
            //set the current light level led to the hashmap index
            lightPaneArray[lightLevel - 1].setStyle("-fx-background-color:" + colorMap.get(lightLevel));
        }
    }

    //function used to decrease light level and update the leds to reflect that fact
    public void decreaseLightLevel(){
        //if not lowest light level
        if(lightLevel != 0){
            lightLevel--;
            lightPaneArray[lightLevel].setStyle("-fx-background-color:" + lightOff);
        }
    }
}
