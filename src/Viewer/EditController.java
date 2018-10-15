package Viewer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class EditController implements Initializable {

    @FXML
    private Button jbCancel;
    public  void CloseEditWindow(ActionEvent event)
    {
        Alert close =new Alert(Alert.AlertType.INFORMATION);
        close.setContentText("Thoát");
        Optional<ButtonType> result= close.showAndWait();
        ButtonType button=result.orElse(ButtonType.CANCEL);
        if(button==ButtonType.OK)
        {
            System.out.println("Dong cua so");
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        else
        {
            close.close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
