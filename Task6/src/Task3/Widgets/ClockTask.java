package Task3.Widgets;

import javafx.scene.text.Text;
import Task3.Utility.CustomUtil;
import java.util.Date;
import java.util.TimerTask;

//Timer Task Class provides the task executed at fixed rate by the timer class in the Clock Widget
class ClockTask extends TimerTask {

    //text object passed from the clock widget this is the display label that shows the current time in the pane
    private Text text;

    //constructor provides text object to be updated
    public ClockTask(Text text){
        this.text = text;
    }

    //implemented run function
    @Override
    public void run() {
        //new Date returns the current date allowing for desired sections such as hours / mins / secs to be extracted
        Date currentDate = new Date();

        //variables storing the extracted portions of the current date
        int seconds = currentDate.getSeconds();
        int minutes = currentDate.getMinutes();
        int hours = currentDate.getHours();

        //updating the text object to display the current time
        //Uses the addLeadingZero method from custom Util to ensure that output format does not deviate from hh:mm:ss
        String updateString = CustomUtil.addLeadingZero(hours) + ":" + CustomUtil.addLeadingZero(minutes) + ":" + CustomUtil.addLeadingZero(seconds);
        text.setText(updateString);
    }
}
