package Task3.Button;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import Task3.Widgets.LightControlWidget;

//Similar to Light control minus this is concrete class that provides increment functionality to light control
public class LightControlPlus extends LightControlButton {

    //parent widget initialised from constructor gives access to increment method
    LightControlWidget parent;

    public LightControlPlus(LightControlWidget parent) {
        //super class constructor passed with a + label
        super("+");
        this.parent = parent;
        //adds click listener
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                handleClickEvent();
            }
        });
    }
    //implemented handle click method
    @Override
    protected void handleClickEvent() {
        parent.increaseLightLevel();
    }
}
