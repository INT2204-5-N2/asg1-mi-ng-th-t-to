package Viewer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Main extends Application {
    //public static  Stage window=new Stage();
    public static  void main(String [] argv)
    {
        launch(argv);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("window.fxml"));
            Scene scene=new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
