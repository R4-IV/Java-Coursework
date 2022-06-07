package Task3.Button;


import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import Task3.Widgets.MidWidget;
import java.io.File;
import java.nio.file.Paths;

//class styles and provides the up/increment button for mid row widgets
public class UpButton extends ImageView {

    //variables provide string path for the button graphic
    private String imagePath = System.getProperty("user.dir") + File.separator + "images" + File.separator + "up-icon.png";
    private String imageURI = Paths.get(imagePath).toUri().toString();

    //Passed a midwidget object so that its increment function can be called
    public UpButton(MidWidget widget){
        //styles the button
        this.setImage(new Image(imageURI));
        this.setFitHeight(35);
        this.setFitWidth(35);

        //adds a click listener to the button
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                widget.incrementValue();
            }
        });
    }
}
