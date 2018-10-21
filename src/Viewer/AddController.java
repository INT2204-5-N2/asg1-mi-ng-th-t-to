package Viewer;

import Controller.DatabaseManagement;
import Controller.DictionaryManagement;
import Models.Word;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddController implements Initializable {
    @FXML
    private TextField jtaWordTarget;
    @FXML
    private  TextArea jtaWordExplain;
    @FXML
    private Button jbAdd;
    @FXML
    private Button jbCancel;
    @FXML
    private ChoiceBox<String> cbLanguage;

    public  void CloseAddWindow(ActionEvent event)
    {
        Alert close =new Alert(Alert.AlertType.CONFIRMATION);
        close.setTitle("Xác nhận");
        close.setContentText("Bạn có chắc chắn muốn thoát không?");
        close.setHeaderText(null);
        Optional<ButtonType> result= close.showAndWait();
        ButtonType button=result.orElse(ButtonType.CANCEL);
        if(button==ButtonType.OK)
        {
            ((Node)(event.getSource())).getScene().getWindow().hide();
            HomeController.addStage=null;
        }
        else if(button==ButtonType.CANCEL)
        {
            close.close();
        }
    }

    public void AddWord()
    {
        Alert Addwindow =new Alert(Alert.AlertType.CONFIRMATION);
        Addwindow.setTitle("Confirm");
        Addwindow.setHeaderText("Bạn có muốn thêm từ này vào từ điển không?");
        Addwindow.setContentText(jtaWordTarget.getText());
        Optional<ButtonType> result= Addwindow.showAndWait();
        ButtonType button=result.orElse(ButtonType.CANCEL);
        if(button==ButtonType.OK)
        {
            Word newWord=new Word();
            newWord.setWord_target(jtaWordTarget.getText());
            newWord.setWord_explain(jtaWordExplain.getText());
            DatabaseManagement dbManagement = DictionaryManagement.getInstance().getDBManager();
            if(cbLanguage.getSelectionModel().getSelectedItem().equals("Tiếng Anh")){
                DictionaryManagement.getInstance().setDictType(DictionaryManagement.evDict);
            } else {
                DictionaryManagement.getInstance().setDictType(DictionaryManagement.veDict);
            }
            dbManagement.addNewWord(newWord);

        }
        Addwindow.close();
        HomeController.addStage = null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbLanguage.setItems(FXCollections.observableArrayList("Tiếng Anh", "Tiếng Việt"));
        cbLanguage.getSelectionModel().selectFirst();
        jtaWordTarget.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.trim().equals("") || jtaWordExplain.getText().trim().equals("")){
                    jbAdd.disableProperty().setValue(true);
                } else {
                    jbAdd.disableProperty().setValue(false);
                }
            }
        });
        jtaWordExplain.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.trim().equals("") || jtaWordTarget.getText().trim().equals("")){
                    jbAdd.disableProperty().setValue(true);
                } else {
                    jbAdd.disableProperty().setValue(false);
                }
            }
        });
    }
}
