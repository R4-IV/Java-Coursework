package Task3.Button;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import Task3.Widgets.TimerWidget;

//concrete class Timer Button provides the functionality of pausing the counter
public class TimerButtonStop extends TimerButton {

    //parent widget gives access to the stop Timer method
    TimerWidget timerWidget;

    public TimerButtonStop(String label , TimerWidget timerWidget) {
        super(label);
        this.timerWidget = timerWidget;

        //adds a click listener
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                handleOnClick();

            }
        });
    }

    //implemented handle click method
    @Override
    protected void handleOnClick() {
        timerWidget.stopTimer();
    }
}

