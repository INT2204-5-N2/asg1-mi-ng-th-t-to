package Viewer;

import Controller.GoogleTranslator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.util.Calendar;

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
    @FXML
    private ChoiceBox<String> cbSelectLanguage;

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
        }
    }
    private long begin = System.currentTimeMillis();
    public void Translate()
    {
        long end = System.currentTimeMillis();
        System.out.println("\nTime: " + (end - begin));
        if(end-begin>=1000)
        {
            String a=null;
            GoogleTranslator translation=new GoogleTranslator();
            if(cbSelectLanguage.getSelectionModel().getSelectedItem().equals("ENG-VIE"))
            {
                a=translation.translate(jtasrcLang.getText(), GoogleTranslator.Language.en, GoogleTranslator.Language.vi);
            }
            else if(cbSelectLanguage.getSelectionModel().getSelectedItem().equals("VIE-ENG"))
            {
                a=translation.translate(jtasrcLang.getText(), GoogleTranslator.Language.vi, GoogleTranslator.Language.en);
            }
            jtargetLang.setText(a);
            begin=end;
        }

        //jtasrcLang.textProperty().addListener(new );

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbSelectLanguage.setItems(FXCollections.observableArrayList("ENG-VIE", "VIE-ENG"));
        jtasrcLang.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Translate();
                System.out.print("Dang dich tu");
            }
        });

    }
}
