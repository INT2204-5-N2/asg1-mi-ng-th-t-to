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
            //window=primaryStage;
            Parent root = FXMLLoader.load(getClass().getResource("window.fxml"));
            Scene scene=new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (Exception e)
        {
//            return ;
//            Alert a=new Alert(Alert.AlertType.INFORMATION);
//            a.setContentText(e.getMessage());
//            a.show();
            System.out.println(e.getMessage());
        }
    }
}
