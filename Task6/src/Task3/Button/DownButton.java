package Task3.Button;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import Task3.Widgets.MidWidget;
import java.io.File;
import java.nio.file.Paths;

//class styles the down button graphic used in mid row widgets to decrement values
public class DownButton extends ImageView {

    //private variables providing the Path and URI to the graphic
    private String imagePath = System.getProperty("user.dir") + File.separator + "images" + File.separator + "down-icon.png";
    private String imageURI = Paths.get(imagePath).toUri().toString();

    //A mid widget is passed into the constructor allowing that widgets decrement value to be called
    public DownButton(MidWidget widget){
        //sets the style of this button
        this.setImage(new Image(imageURI));
        this.setFitHeight(35);
        this.setFitWidth(35);

        //provides a click listener
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //calls the passed widget decrement function
                widget.decrementValue();
            }
        });
    }
}
