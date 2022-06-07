package Task3.Widgets;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import Task3.Utility.CustomUtil;

//Widget provides the static gas pane each widget only provides one gas hence multiple calls are required in the root pane
public class MedicalGasWidget extends VBox {
    //Variables keeping track of initial values
    private String gasName;
    private String gasStatus;
    private String diodeColor;

    public MedicalGasWidget(String gasName , String gasStatus , String diodeColor){
        this.gasName = gasName;
        this.gasStatus = gasStatus;
        this.diodeColor = diodeColor;
        this.styleGases();
    }

    //styling for the widget
    private void styleGases(){
        //box that holds the Yellow gas label above its indicator this is used so that its set alignment property can
        //easily center the label
        HBox gasLabelBox = new HBox();
        CustomUtil.setPaneSize(gasLabelBox , 20 , 45);

        //Creates Text object label and sets its color to yellow
        Text gasNameLabel = new Text(gasName);
        gasNameLabel.setFill(Color.YELLOW);
        //possibly redundant call to text alignment
        gasNameLabel.setTextAlignment(TextAlignment.CENTER);

        //adds the label to its container and centers it
        gasLabelBox.getChildren().add(gasNameLabel);
        gasLabelBox.setAlignment(Pos.CENTER);

        //Creates the Hbox that will be the diode / indicator for the gas
        HBox diode = new HBox();
        CustomUtil.setPaneSize(diode ,45 , 45);
        //Gas status text object
        Text gasStatusLabel = new Text(gasStatus);
        //adds and center the status on the diode / indicator
        diode.getChildren().add(gasStatusLabel);
        diode.setAlignment(Pos.CENTER);
        diode.setStyle("-fx-background-color:" + diodeColor +";");

        //adds both the label box and diode to the widget vbox is used so they can stack vertically
        this.getChildren().add(gasLabelBox);
        this.getChildren().add(diode);
    }
}
