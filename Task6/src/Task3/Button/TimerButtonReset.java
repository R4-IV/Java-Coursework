package Task3.Button;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import Task3.Widgets.TimerWidget;

//Timer button reset concrete class allows for the button to set all time values of the referenced widget back to 0
public class TimerButtonReset extends TimerButton {

    //parent widget allows for the button to access its reset method
    TimerWidget timerWidget;

    //label is passed to be constructed in root pane it can also be set here as "reset" automatically
    public TimerButtonReset(String label , TimerWidget timerWidget) {
        super(label);
        this.timerWidget = timerWidget;

        //click handler for the button
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                handleOnClick();

            }
        });
    }
    //implemented click handle method calls parent widget reset method
    @Override
    protected void handleOnClick() {
        timerWidget.resetTimer();
    }
}

