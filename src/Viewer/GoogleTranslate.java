package Viewer;

import Controller.GoogleTranslation;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class GoogleTranslate extends Thread implements Initializable {
    @FXML
    private Button jbExit;
    @FXML
    private TextArea jtasrcLang;
    @FXML
    private TextArea jtargetLang;

    public void CloseGoogleTranslateWindow(ActionEvent event)
    {
        Alert close=new Alert(Alert.AlertType.INFORMATION);
        close.setContentText("You want close GoogleTranslate Window??");
        Optional<ButtonType> result= close.showAndWait();
        ButtonType button=result.orElse(ButtonType.CANCEL);
        if(button==ButtonType.OK)
        {
            System.out.println("Dong cua so");
            ((Node)(event.getSource())).getScene().getWindow().hide();
            HomeController.stage1=null;
        }
        else
        {
            close.close();
            HomeController.stage1=null;
        }
    }
    public void TranslateEngVie()
    {
        GoogleTranslation translation=new GoogleTranslation();
        String a=translation.translate(jtasrcLang.getText(), GoogleTranslation.Language.en, GoogleTranslation.Language.vi);
        //jtasrcLang.textProperty().addListener(new );

        jtargetLang.setText(a);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        jtasrcLang.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                TranslateEngVie();
                System.out.print("Dang dich tu");

            }
        });
    }
}
