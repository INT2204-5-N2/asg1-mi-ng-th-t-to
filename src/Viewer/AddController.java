package Viewer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class AddController implements Initializable {
    @FXML
    private TextArea jtaEnglish;
    @FXML
    private  TextArea jtaVietnamese;
    @FXML
    private Button jbAdd;
    @FXML
    private Button jbCancel;
    public void JtaEnglishGetText(ActionEvent e)
    {
        String a=jtaEnglish.getText();
        Alert notif=new Alert(Alert.AlertType.INFORMATION);
        notif.setContentText(a);
        notif.show();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
