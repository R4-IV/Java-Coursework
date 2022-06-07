package Task3.Widgets;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import Task3.Button.DownButton;
import Task3.Button.UpButton;
import Task3.Utility.CustomUtil;

//abstract class for all the widgets in the midrow as they have identical layouts
public abstract class MidWidget extends VBox {

    private String imageURI;
    private String suffix;
    private int imgWidth;
    private Text displayedValue;

    //getter method for text which displays the numerical value for these widgets such as temperature and humidity
    public Text getDisplayedValue() {
        return displayedValue;
    }

    //constructor
    protected MidWidget(String imageURI , int imgWidth  , String suffix , String defaultValue){
        this.imageURI = imageURI;
        this.suffix = suffix;
        this.imgWidth = imgWidth;
        displayedValue = new Text(defaultValue);
        this.styleWidget();
    }

    //styles the widget with provided image uri suffix and default value
    private void styleWidget(){

        //Top Row ->  [[image][value][suffix]]
        //BottomRow ->[[UpButton][spacer][DownButton]]

        //Top Row
        HBox topRow = new HBox();
        //sets the icon
        ImageView widgetIcon = new ImageView(new Image(imageURI));
        widgetIcon.setFitHeight(50);
        widgetIcon.setFitWidth(imgWidth);

        //container for holding the value this is used so that changes in the value do not affect the position of
        //the suffix
        HBox valueContainer = new HBox();
        CustomUtil.setPaneSize(valueContainer , 50 , 90);

        //styles the value
        displayedValue.setFill(Color.RED);
        displayedValue.setStyle("-fx-font-size: 35;");

        //adds value to its container and centers
        valueContainer.getChildren().add(displayedValue);
        valueContainer.setAlignment(Pos.CENTER);

        //container holds suffix used for easy center alignment
        HBox suffixContainer = new HBox();
        CustomUtil.setPaneSize(suffixContainer , 50 , 20);

        //styling for the suffix text object
        Text displayedSuffix = new Text(suffix);
        displayedSuffix.setFill(Color.WHITE);
        displayedSuffix.setStyle("-fx-font-size: 20;");

        //adds suffix to its container and centers
        suffixContainer.getChildren().add(displayedSuffix);
        suffixContainer.setAlignment(Pos.CENTER);

        //adds all 3 elements to the top row of this widget
        topRow.getChildren().add(widgetIcon);
        topRow.getChildren().add(valueContainer);
        topRow.getChildren().add(suffixContainer);

        //Bottom Row
        HBox bottomRow = new HBox();

        //creates a button divider (essentially an empty hbox with a set width / height)
        HBox buttonDivider = new HBox();
        CustomUtil.setPaneSize(buttonDivider , 35 , 10);

        //creates buttons
        UpButton up = new UpButton(this);
        DownButton down = new DownButton(this);

        //adds all elements to the bottom row
        bottomRow.getChildren().add(up);
        bottomRow.getChildren().add(buttonDivider);
        bottomRow.getChildren().add(down);

        //aligns all elements to the center in the bottom row
        bottomRow.setAlignment(Pos.CENTER);

        //adds both top and bottom row to this widget
        this.getChildren().add(topRow);
        this.getChildren().add(bottomRow);
        //offset between top and bottom row prevents the elements in the 2 rows from touching
        this.setMargin(bottomRow , new Insets(3 , 0 , 0 , 0));

        //theres one image that is wider than the other hence this condition exists
        //it offsets the buttons by 10 as to match the increase in image size the result is that the
        //layout closer resembles the one in the guide material
        if(imgWidth == 50){
            this.setMargin(bottomRow , new Insets(0 , 0 , 0 , 10));
        }

    }

    //abstract methods for incrementing and decrementing values
    //the reason an abstract class exists for this is because one set uses integers and the other uses doubles
    //this can probably be refactored to use method overloading with multiple constructors
    public abstract void incrementValue();

    public abstract void decrementValue();

}
