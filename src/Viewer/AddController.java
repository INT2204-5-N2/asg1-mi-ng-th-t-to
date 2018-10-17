package Viewer;

import Controller.DatabaseManagement;
import Controller.DictionaryManagement;
import Models.Word;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;

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
        close.setTitle("Comfirmation close Window");
        close.setHeaderText("Ban co chac chan");
        close.setContentText("Thoát");
        Optional<ButtonType> result= close.showAndWait();
        ButtonType button=result.orElse(ButtonType.CANCEL);
        if(button==ButtonType.OK)
        {
            System.out.println("Dong cua so");
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        else if(button==ButtonType.CANCEL)
        {
            System.out.println("Khong dong cua so nay");
            close.close();
        }
    }

    public void AddWord()
    {
        Alert Addwindow =new Alert(Alert.AlertType.CONFIRMATION);
        Addwindow.setTitle("Confirmation Add Word ");
        Addwindow.setHeaderText("Ban co muon them?");
        Addwindow.setContentText(jtaWordTarget.getText());
        Optional<ButtonType> result= Addwindow.showAndWait();
        ButtonType button=result.orElse(ButtonType.CANCEL);
        if(button==ButtonType.OK)
        {
            System.out.println("Dong cua so");
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
            System.out.println("Thêm thành công");
            HomeController.stage=null;
        }
        else
        {
            HomeController.stage=null;
            Addwindow.close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbLanguage.setItems(FXCollections.observableArrayList("Tiếng Anh", "Tiếng Việt"));
    }
}
