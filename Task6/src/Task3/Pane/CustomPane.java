package Task3.Pane;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import Task3.Utility.CustomUtil;

//Class that holds the widgets
public class CustomPane extends BorderPane {

    public CustomPane(double width, double height , String title){
        //sets constant width and height for the pane
        CustomUtil.setPaneSize(this , height ,width);

        //creates a label to assign to the pane
        CustomLabel label = new CustomLabel(title);
        //adds the label to the top portion of the border pane this will leave center free to put in the widgets
        this.setTop(label);
        //sets negative top margin for the pane
        this.setMargin(label , new Insets(-7 , 0 ,0 ,5));
        stylePane();
    };

    //private method Styles the box
    private void stylePane(){
        this.setStyle("-fx-border-width: 1;" +
                "-fx-border-radius: 5;" +
                "-fx-border-style: solid;" +
                "-fx-border-color: white;");
    }
}
