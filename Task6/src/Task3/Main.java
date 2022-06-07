package Task3;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import Task3.Pane.RootPane;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //creates a new root pane containing all the widgets
        RootPane root = new RootPane();

        //sets root pane displayed label
        primaryStage.setTitle("Surgery Control Panel");
        primaryStage.setScene(new Scene(root, 600, 450));
        //make root Pane visible on launch
        primaryStage.show();
        //prevent the root pane from resizing
        primaryStage.setResizable(false);

        //On close event listener exits both the application window and program on close
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>(){
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
