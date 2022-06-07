package Task3.Widgets;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import Task3.Button.TimerButtonReset;
import Task3.Button.TimerButtonStart;
import Task3.Button.TimerButtonStop;
import Task3.Utility.CustomUtil;

import java.util.Timer;
import java.util.TimerTask;

//Class provides the visuals + functionality of the timer pane
public class TimerWidget extends HBox {

    //variables store each of the respective units of time
    private int seconds = 0;
    private int hours = 0;
    private int minutes = 0;
    //Inset specifying the margins between each of the blue buttons on the rhs
    private Insets buttonSpacer = new Insets(1.5 , 0 , 1.5 ,0);
    //Text Object used to display the current elapsed time
    private Text timerText;
    //Timer Object will facilitate the passage of time via scheduling a task every second
    private Timer timer = new Timer();
    //variable preventing the user from creating multiple timer objects by repeatedly pressing the start button
    private boolean timerRunning = false;


    public TimerWidget(BorderPane parent){
        double parentWidth = parent.getMaxWidth();
        double parentHeight = parent.getMaxHeight();

        CustomUtil.setPaneSize(this ,parentHeight ,parentWidth);

        //Creates a box for the timer text this ensures that the button positions are unaffected by changes
        //in the width of the timer String as well as providing an easy way to center the text with set Alignment
        HBox textContainer = new HBox();
        textContainer.setPrefWidth(200);
        textContainer.setMinWidth(200);
        textContainer.setMaxWidth(200);

        //Creates the text + style for the timer as well as loading the initial value 00:00:00
        timerText = new Text();
        timerText.setFill(Color.RED);
        timerText.setStyle("-fx-font-size:45px;");
        timerText.setText(CustomUtil.addLeadingZero(hours) + ":" + CustomUtil.addLeadingZero(minutes) + ":" + CustomUtil.addLeadingZero(seconds));

        //adds the text to its container + centers it
        textContainer.getChildren().add(timerText);
        textContainer.setAlignment(Pos.CENTER);

        //Vbox used to store the 3 buttons vertically
        VBox buttonContainer = new VBox();
        CustomUtil.setPaneSize(buttonContainer , 100 ,80);

        //creates the 3 buttons
        TimerButtonStart startButton = new TimerButtonStart("start" , this);
        TimerButtonStop stopButton = new TimerButtonStop("stop" , this);
        TimerButtonReset resetButton = new TimerButtonReset("reset" , this);

        //adds the buttons to the buttonContainer and applies the margins from the button spacer variable
        buttonContainer.getChildren().add(startButton);
        buttonContainer.setMargin(startButton , buttonSpacer);
        buttonContainer.getChildren().add(stopButton);
        buttonContainer.setMargin(stopButton , buttonSpacer);
        buttonContainer.getChildren().add(resetButton);
        buttonContainer.setMargin(resetButton , buttonSpacer);

        //adds both elements to this widget and applies offsets to achieve the layout in the guide material
        this.getChildren().add(textContainer);
        this.setMargin(textContainer , new Insets( 0 , 20 , 0 ,10));
        this.getChildren().add(buttonContainer);
        this.setMargin(buttonContainer , new Insets(12.5 , 20 , 0 ,5));
    }

    //function that starts the timer called from the start button
    public void startTimer(){
        //if a timer is not already running
        if(timerRunning == false){
            //sets timer to be running
            timerRunning = true;
            //creates a new timer object
            timer = new Timer();
            //schedules the following task at fixed rate 1000ms
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    //increases the amount of seconds
                    seconds++;
                    //calls the handler function that updates the text object
                    timerText.setText(passageOfTime());
                }
            } , 0 ,1000);

        }
    }

    //method to stop timer called by the stop button
    public void stopTimer(){
        //timer.cancel essentially destroys the timer object hence a new one is created on every start call
        timer.cancel();
        //timer running is set to false so that start can restart it
        timerRunning = false;
    }

    //method sets all time related variables to 0 and updates the Text object to reflect that immediately
    public void resetTimer(){
        seconds = 0;
        minutes = 0;
        hours = 0;
        timerText.setText(CustomUtil.addLeadingZero(hours) + ":" + CustomUtil.addLeadingZero(minutes) + ":" + CustomUtil.addLeadingZero(seconds));
    }

    //method handles movement of time up the chain as every second is added
    private String  passageOfTime(){
        if(seconds == 60){
            minutes ++;
            seconds = 0;
        }
        if(minutes == 60){
            hours++;
            minutes = 0;
        }
        if(hours == 99){
            hours = 0;
        }
        //returns formatted time hh:mm:ss using the CustomUtil leading zero method
        return CustomUtil.addLeadingZero(hours) + ":" + CustomUtil.addLeadingZero(minutes) + ":" + CustomUtil.addLeadingZero(seconds);
    }
}



