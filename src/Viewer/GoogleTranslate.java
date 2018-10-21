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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.*;

import java.net.URL;

public class GoogleTranslate extends Thread implements Initializable {
    @FXML
    private Button jbExit;
    @FXML
    private TextArea jtasrcLang;
    @FXML
    private TextArea jtargetLang;
    @FXML
    private ChoiceBox<String> cbSelectLanguage;
    private MediaPlayer mediaPlayer;
    private GoogleTranslator translator = new GoogleTranslator();
    private Timer timer = new Timer();
    private static long DELAY_TIME = 200;

    public void CloseGoogleTranslateWindow(ActionEvent event)
    {
        Alert close=new Alert(Alert.AlertType.CONFIRMATION);
        close.setTitle("Xác nhận");
        close.setHeaderText(null);
        close.setContentText("Bạn có chắc chắc muốn thoát");
        Optional <ButtonType> result= close.showAndWait();
        ButtonType button=result.orElse(ButtonType.CANCEL);
        if(button==ButtonType.OK)
        {
            ((Node)(event.getSource())).getScene().getWindow().hide();
            HomeController.stage1=null;
        }
        else
        {
            close.close();
        }
    }

    public void autoTranslate(){
        String result=null;
        if(cbSelectLanguage.getSelectionModel().getSelectedItem().equals("ENG-VIE"))
        {
            result=translator.translate(jtasrcLang.getText(), GoogleTranslator.Language.en, GoogleTranslator.Language.vi);
        }
        else if(cbSelectLanguage.getSelectionModel().getSelectedItem().equals("VIE-ENG"))
        {
            result=translator.translate(jtasrcLang.getText(), GoogleTranslator.Language.vi, GoogleTranslator.Language.en);
        }
        jtargetLang.setText(result);
    }

    @FXML
    public void playSound(){
        Media sound = null;
        if(cbSelectLanguage.getSelectionModel().getSelectedItem().equals("ENG-VIE"))
        {
            sound = new Media(translator.getSoundFile(jtasrcLang.getText(), GoogleTranslator.Language.en).toURI().toString());
        }
        else if(cbSelectLanguage.getSelectionModel().getSelectedItem().equals("VIE-ENG"))
        {
            sound = new Media(translator.getSoundFile(jtasrcLang.getText(), GoogleTranslator.Language.vi).toURI().toString());
        }
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbSelectLanguage.setItems(FXCollections.observableArrayList("ENG-VIE", "VIE-ENG"));
        jtasrcLang.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                timer.cancel();
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        autoTranslate();
                    }
                }, DELAY_TIME);
            }
        });
        cbSelectLanguage.getSelectionModel().selectFirst();
        cbSelectLanguage.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> autoTranslate());

    }
}
