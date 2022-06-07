package Task3.Button;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import Task3.Widgets.TimerWidget;

//Concrete Timer button used to start the counter in the timer widget
public class TimerButtonStart extends TimerButton{

    //parent widget gives access to the startTimer method
    TimerWidget timerWidget;

    public TimerButtonStart(String label , TimerWidget timerWidget) {
        super(label);
        this.timerWidget = timerWidget;

        //button click handler
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
        timerWidget.startTimer();
    }
}
