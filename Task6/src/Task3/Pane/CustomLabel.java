package Task3.Pane;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

//Class extends the original label class to create a label that can be applied to the borders of each widget space
class CustomLabel extends Label {

    public CustomLabel(String title){
        //creates a new label and passes the title argument from the constructor
        Label label = new Label(title);
        label.setTextFill(Color.WHITE);

        //sets the graphic of this label to be the previously created label this results in a label with a non transparent background
        this.setGraphic(label);
        //set style methods set up the background color to match the root pane color giving the illusion of break in the border for
        //the label
        this.setStyle("-fx-background-color: #654321;" +
                "-fx-padding: 0 5 0 5;" +
                "-fx-font-size: 10;" +
                "-fx-font-weight: bolder;");
    }

}
