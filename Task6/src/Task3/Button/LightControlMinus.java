package Task3.Button;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import Task3.Widgets.LightControlWidget;

//Concrete Class provides the decrement button for controlling light levels
public class LightControlMinus extends LightControlButton {

    //provides the widget this button will be used with from constructor
    LightControlWidget parent;

    public LightControlMinus(LightControlWidget parent) {
        //super class constructor is passed the - symbol as the label
        super("-");
        this.parent = parent;
        //adds on click handler
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            //calls implemented handle click method
            public void handle(MouseEvent event) {
                handleClickEvent();
            }
        });
    }

    @Override
    protected void handleClickEvent() {
        //parent widget gives the button access to its light level as well as the ability to decrease it
        //via this function
        parent.decreaseLightLevel();
    }
}
