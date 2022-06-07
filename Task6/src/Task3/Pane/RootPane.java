package Task3.Pane;

import Task3.Widgets.*;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.io.File;
import java.nio.file.Paths;

//used borderPane as I have 3 rows of widgets top center and bottom
public class RootPane extends BorderPane {
    //private variable that stores the insets between each of the widget panes
    private Insets paneInsets = new Insets(10 , 10 , 10 , 10);
    //specifies the height of each row
    private int childHeight = 130;

    //root PAne constructor
    public RootPane(){
        //sets the brown background color
        this.setStyle("-fx-background-color: #654321;");
        //allocates rows of widgets to the respective sections of the border pane by the use of their methods
        //can also be called without this keyword
        this.setTop(makeTopRow());
        this.setCenter(makeMidRow());
        this.setBottom(makeBotRow());
    }

    //private method creates the top row of widgets
    private Pane makeTopRow(){
        //Hbox holds all the top row widgets
        HBox topRow = new HBox();

        //Clock Pane and related widget
        CustomPane clock = new CustomPane(224 , childHeight , "Day Time Clock");
        ClockWidget clockWidget = new ClockWidget();
        //adds the clock widget tot the clock pane
        clock.setCenter(clockWidget);
        //adds the clock pane to the top row box
        topRow.getChildren().add(clock);
        topRow.setMargin(clock , paneInsets);

        //Timer Pane and related widget
        CustomPane timer = new CustomPane( 336 , childHeight , "Elapsed Time");
        TimerWidget timerWidget = new TimerWidget(timer);
        //adds the timer widget to the timer pane
        timer.setCenter(timerWidget);
        //adds timer pane to top row hbox
        topRow.getChildren().add(timer);
        topRow.setMargin(timer , paneInsets);

        //returns the top row hbox containing the clock and timer widgets
        return topRow;
    }

    private Pane makeMidRow(){
        //hbox holds all the mid row widgets
        HBox midRow = new HBox();

        //Temperature pane and related Widget
        CustomPane temperature = new CustomPane(178.2 , childHeight , "Temperature Control");

        //Variables holding Temperature icon URI and an array of values as follows initial temp , upper limit temp ,
        //lower limit temp , increment temp
        String pathToImageTemperature = System.getProperty("user.dir") + File.separator + "images" + File.separator + "thermometer-icon.png";
        String imageURITemperature = Paths.get(pathToImageTemperature).toUri().toString();
        double[] temperatureValues = new double[]{21.5 , 27.5 , 10.0 , 0.1 };

        //creates the temperature widget
        MidWidget temperatureWidget = new TemperatureWidget(imageURITemperature , 40  , "°C" , temperatureValues);
        //adds the temperature widget to the temperature pane
        temperature.setCenter(temperatureWidget);
        temperature.setMargin(temperatureWidget , new Insets(20 , 0 , 0 ,15));

        //adds temperature pane to the mid row pane
        midRow.getChildren().add(temperature);
        midRow.setMargin(temperature , paneInsets);
        //---------------------------------------------------------------------------------------------------

        //Humidity Pane and related widget
        CustomPane humidity = new CustomPane(183.6 , childHeight , "Humidity Control");

        //Humidity URI and array of limits for the humidity widget
        String pathToImageHumidity = System.getProperty("user.dir") + File.separator + "images" + File.separator + "humidity-icon.png";
        String imageURIHumidity = Paths.get(pathToImageHumidity).toUri().toString();
        int[] humidityValues = new int[]{40 , 55 , 30 , 1 };

        //creates humidity widget with the above values
        MidWidget humidityWidget = new PressureHumidityWidget(imageURIHumidity , 50  , "%" , humidityValues);
        humidity.setCenter(humidityWidget);
        humidity.setMargin(humidityWidget , new Insets(20 , 0 , 0 ,12.5));

        midRow.getChildren().add(humidity);
        midRow.setMargin(humidity , paneInsets);
        //---------------------------------------------------------------------------------------------------

        //Pressure Pane and related widget
        CustomPane pressure = new CustomPane(178.2 , childHeight , "Pressure Control");

        //Pressure URI and limit values
        String pathToImagePressure = System.getProperty("user.dir") + File.separator + "images" + File.separator + "pressure-icon.png";
        String imageURIPressure = Paths.get(pathToImagePressure).toUri().toString();
        int[] pressureValues = new int[]{50 , 120 , 50 , 10 };

        MidWidget pressureWidget = new PressureHumidityWidget(imageURIPressure , 40  , "kPa" , pressureValues);
        pressure.setCenter(pressureWidget);
        pressure.setMargin(pressureWidget , new Insets(20 , 0 , 0 ,10));

        midRow.getChildren().add(pressure);
        midRow.setMargin(pressure , paneInsets);

        //returns mid row and its widgets
        return midRow;
    }

    private Pane makeBotRow(){
        //hbox containing all the widgets from the bottom row
        HBox botRow = new HBox();

        //Light Control Pane and related widget
        CustomPane light = new CustomPane(280 , childHeight , "Light Control");
        //V box holds all 3 of the light widgets used vbox as the panes need to stack vertically
        VBox lightContainer = new VBox();
        Insets lightInsets = new Insets(2.5 , 0 , 2.5, 0);

        //each light widget accounts for one of the rows controlling the lights
        LightControlWidget firstRow = new LightControlWidget();
        LightControlWidget secondRow = new LightControlWidget();
        LightControlWidget thirdRow = new LightControlWidget();

        //adds the light widgets to the vbox and sets their inset/margins
        lightContainer.getChildren().add(firstRow);
        lightContainer.setMargin(firstRow , lightInsets);
        lightContainer.getChildren().add(secondRow);
        lightContainer.setMargin(secondRow , lightInsets);
        lightContainer.getChildren().add(thirdRow);
        lightContainer.setMargin(thirdRow , lightInsets);

        //adds the light pane to the bot row
        light.setCenter(lightContainer);
        light.setMargin(lightContainer , new Insets(15 , 0 , 0 , 8));
        botRow.getChildren().add(light);
        botRow.setMargin(light , paneInsets);
        //--------------------------------------------------------------------------------------------------

        //Gas display pane and its related widget
        CustomPane gases = new CustomPane(280 , childHeight , "Medical Gases");
        //holds all the widgets so that a single pane can then later be added to the parent
        HBox gasContainer = new HBox();

        //creates widgets for each of the required gasses
        MedicalGasWidget o2 = new MedicalGasWidget("O2" , "Low" , "#ff4929");
        MedicalGasWidget n2O = new MedicalGasWidget("N20" , "High" , "#42f590");
        MedicalGasWidget air1 = new MedicalGasWidget("AIR1" , "Norm" , "#42f590");
        MedicalGasWidget co2 = new MedicalGasWidget("CO2" , "Norm" , "#ff4929");
        MedicalGasWidget vac = new MedicalGasWidget("VAC" , "High" , "#ff4929");

        //Inset provides a top margin for each gas widget and horizontal spacing between the boxes
        Insets gasSpacer = new Insets(25 ,2.5 ,0 ,2.5);

        //adds each gas widget to the gas container and applies the specified margin
        gasContainer.getChildren().add(o2);
        gasContainer.setMargin(o2 , new Insets(25 , 2.5 , 0 ,12.5));
        gasContainer.getChildren().add(n2O);
        gasContainer.setMargin(n2O , gasSpacer);
        gasContainer.getChildren().add(air1);
        gasContainer.setMargin(air1 , gasSpacer);
        gasContainer.getChildren().add(co2);
        gasContainer.setMargin(co2 , gasSpacer);
        gasContainer.getChildren().add(vac);
        gasContainer.setMargin(vac , new Insets(25 , 12.5 , 0 ,2.5));

        gases.setCenter(gasContainer);

        botRow.getChildren().add(gases);
        botRow.setMargin(gases , paneInsets);
        //returns all panes in the bottom row
        return botRow;
    }
}
