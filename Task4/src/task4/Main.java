package task1;

import task1.Data.DataLoader;
import task1.Presentation.Ui;

public class Main {
    public static void main(String[] args) {
        //autoloader object creates the ability to access the album arraylist
        DataLoader dl = new DataLoader();
        //Ui object takes the arraylist from dataLoader as an argument thus providing the data to the presentation layer
        Ui ui  = new Ui(dl.parseSource());
        //Ui function that displays the text based interface for the user
        ui.displayUi();
    }
}
