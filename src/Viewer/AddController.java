package Viewer;

import Controller.DatabaseManagement;
import Controller.DictionaryManagement;
import Models.Word;
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

public class AddController implements Initializable {
    @FXML
    private TextArea jtaEnglish;
    @FXML
    private  TextArea jtaVietnamese;
    @FXML
    private Button jbAdd;
    @FXML
    private Button jbCancel;
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

    public void AddWord(ActionEvent event)
    {
        Alert Addwindow =new Alert(Alert.AlertType.CONFIRMATION);
        Addwindow.setTitle("Confirmation Add Word ");
        Addwindow.setHeaderText("Ban co muon them?");
        Optional<ButtonType> result= Addwindow.showAndWait();
        ButtonType button=result.orElse(ButtonType.CANCEL);
        if(button==ButtonType.OK)
        {
            System.out.println("Dong cua so");
            Word newWord=new Word();
            newWord.setWord_target(jtaEnglish.getText());
            newWord.setWord_explain(jtaVietnamese.getText());
            DatabaseManagement dbManagement = DictionaryManagement.getInstance().getDBManager();
            dbManagement.addNewWord(newWord);
//            Alert imformation=new Alert(Alert.AlertType.INFORMATION);
//            imformation.setTitle("Add Word");
//            imformation.setHeaderText("Thêm từ");
//            imformation.setContentText("Thêm thành công");
//            imformation.show();
            System.out.println("Thêm thành công");
            HomeController.stage=null;
            ((Node)(event.getSource())).getScene().getWindow().hide();

        }
        else
        {

            HomeController.stage=null;
            Addwindow.close();

        }
    }
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
