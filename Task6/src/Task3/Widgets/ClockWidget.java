package Task3.Widgets;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import java.util.Timer;

//Class providing the clock widget visuals and functionality
public class ClockWidget extends HBox {

    //timer object will schedule a task at a fixed rate to display the time updating
    Timer clock = new Timer();

    public ClockWidget(){

        //text object stores the current time string formatted hh:mm:ss
        Text currentTime = new Text();
        //sets the text color green as per the graphic provided
        currentTime.setFill(Color.GREEN);
        //sets font size
        currentTime.setStyle("-fx-font-size:45px;");

        //Creates a new clock task object passing the current Time Text as a parameter
        //The clock task retrieves the current time and sets it as the Text for the label
        ClockTask task = new ClockTask(currentTime);

        //adds the currentTime Text Object to the Widget Hbox
        this.getChildren().add(currentTime);
        //centers the text object in the widget Hbox
        this.setAlignment(Pos.CENTER);
        //need to offset as border label occupies 5px in the hbox
        this.setMargin(currentTime , new Insets( -5  , 0 , 0 ,0));
        //schedules the clock to perform the clock task once per second in perpetuity
        clock.scheduleAtFixedRate(task , 0 , 1000);
    }
}
