package Viewer;

import Controller.DictionaryManagement;
import Models.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class EditController implements Initializable {

    @FXML
    private Text txtWord;
    @FXML
    private TextField txtPronounce;
    @FXML
    private TextArea txtMeaning;
    @FXML
    private Button jbCancel;
    @FXML
    private Pane rootPane;
    private Word wordToEdit;

    public EditController(Word word){
        wordToEdit = word;
    }
    public void setWordToEdit(Word wordToEdit) {
        this.wordToEdit = wordToEdit;
    }

    public  void CloseEditWindow(ActionEvent event)
    {
        Alert close =new Alert(Alert.AlertType.CONFIRMATION);
        close.setTitle("Xác nhận");
        close.setHeaderText(null);
        close.setContentText("Bạn có chắc chắn muốn thoát");
        Optional<ButtonType> result= close.showAndWait();
        ButtonType button=result.orElse(ButtonType.CANCEL);
        if(button==ButtonType.OK)
        {
            Stage currentStage = (Stage) ((Node)(event.getSource())).getScene().getWindow();
            currentStage.close();
        }
        else
        {
            close.close();
        }
        HomeController.editStage = null;
    }

    @FXML
    public void saveDB(){
        wordToEdit.setWord_explain(txtMeaning.getText());
        wordToEdit.setPronounce(txtPronounce.getText());
        DictionaryManagement.getInstance().getDBManager().edit(wordToEdit);
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Thông báo");
        successAlert.setHeaderText(null);
        successAlert.setContentText("Sửa thành công");
        successAlert.showAndWait();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtWord.setText(wordToEdit.getWord_target());
        txtPronounce.setText(wordToEdit.getPronounce());
        txtMeaning.setText(wordToEdit.getWord_explain());
    }
}
