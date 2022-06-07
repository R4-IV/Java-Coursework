package Task3.Button;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import Task3.Utility.CustomUtil;

//Abstract class for light control buttons used to create light control + and - concrete classes
public abstract class LightControlButton extends HBox {

    //protected constructor can only be accessed by inheriting classes passes label which can be either + or -
    protected LightControlButton(String label){
        //sets the button size using CustomUtil function
        CustomUtil.setPaneSize(this , 25 , 32);

        //creates the label for the button (+/-)
        Text buttonSymbol = new Text(label);
        //sets the label color to white so that it matches the border
        buttonSymbol.setFill(Color.WHITE);

        //call the below style method to apply styling to the button
        this.styleButton();
        //adds the label to the button/hbox
        this.getChildren().add(buttonSymbol);
        //auto aligns the label to the center of the hbox
        this.setAlignment(Pos.CENTER);
    }

    //private method styles the border and the background of the button
    private void styleButton(){
        this.setStyle("-fx-border-radius: 2px;" +
                "-fx-border-width: 1px;" +
                "-fx-border-color: white;" +
                "-fx-background-color: #654321;");
    }

    //abstract method must be implemented as the onclick function
    protected abstract void handleClickEvent();
}
