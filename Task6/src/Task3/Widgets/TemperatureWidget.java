package Task3.Widgets;

//concrete class used for temperature widget as it used type double numbers
public class TemperatureWidget extends MidWidget{

    //variables storing contents of the limit array passed in the constructor
    double currentValue;
    double higherLimit;
    double lowerLimit;
    double incrementAmount;

    public TemperatureWidget(String imageURI, int imgWidth, String suffix, double[] limits) {
        super(imageURI, imgWidth, suffix, String.valueOf(limits[0]));
        currentValue = limits[0];
        higherLimit = limits[1] ;
        lowerLimit = limits[2];
        incrementAmount = limits[3];
    }
    //implemented increment value method
    @Override
    public void incrementValue() {
        //doubles don't work well with equality comparison hence i am comparing an aproximation
        if(higherLimit - currentValue > 0.001){
            currentValue += incrementAmount;
            //Using String.format to receive the output to 1 decimal place
            getDisplayedValue().setText(String.valueOf(String.format("%.1f" , currentValue)));
        }
    }
    //implemented decrement value method
    @Override
    public void decrementValue() {
        if(currentValue - lowerLimit > 0.001){
            currentValue -= incrementAmount;
            getDisplayedValue().setText(String.valueOf(String.format("%.1f" , currentValue)));
        }
    }
}
