package Task3.Widgets;

public class PressureHumidityWidget extends MidWidget {

    //Variables storing the contents of the passed limits int array
    private int currentValue;
    private int higherLimit;
    private int lowerLimit;
    private int incrementAmount;

    //Concrete class used for both pressure and humidity widgets as they both use integer values
    public PressureHumidityWidget(String imageURI, int imgWidth, String suffix, int[] limits) {
        super(imageURI, imgWidth, suffix, String.valueOf(limits[0]));
        currentValue = limits[0];
        higherLimit = limits[1] ;
        lowerLimit = limits[2];
        incrementAmount = limits[3];
    }
    //implemented increment value
    @Override
    public void incrementValue() {
        if(currentValue != higherLimit){
            currentValue += incrementAmount;
            getDisplayedValue().setText(String.valueOf(currentValue));
        }
    }
    //implemented decrement value
    @Override
    public void decrementValue() {
        if(currentValue != lowerLimit){
            currentValue -= incrementAmount;
            getDisplayedValue().setText(String.valueOf(currentValue));
        }
    }
}
