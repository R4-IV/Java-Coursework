package Task3.Button;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

//Abstract class providing styling and an abstract click method to derived timer button classes
public abstract class TimerButton extends HBox {

    //constructor takes string label since the buttons can only be start , stop , reset these values will be passed in the derived class
    protected TimerButton(String label ){
        //calls the style function
        this.styleButton();

        //creates adds the label to the button and then centers it
        Text buttonLabel = new Text(label);
        //label color changed to white
        buttonLabel.setFill(Color.WHITE);
        this.getChildren().add(buttonLabel);
        this.setAlignment(Pos.CENTER);
    }

    //method used to set the background and border of the button
    private void styleButton() {
        //sets the button height only
        this.setPrefHeight(30);
        this.setMinHeight(30);
        this.setMaxHeight(30);

        this.setStyle("-fx-border-radius: 2px;" +
                "-fx-background-radius: 2px;" +
                "-fx-background-color: blue;" +
                "-fx-border-width: 1px;" +
                "-fx-border-color: white;" +
                "-fx-cursor: pointer;");
    }

    //abstract click handler method
    protected abstract void handleOnClick();

}
