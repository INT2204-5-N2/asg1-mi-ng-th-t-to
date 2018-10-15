package Viewer;

import Controller.DatabaseManagement;
import Controller.DictionaryManagement;
import Models.Word;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeController implements Initializable  {
    @FXML
    ChoiceBox<String> jCBDictType;

    @FXML
    private TextField jtxtSearch;
    @FXML
    private Button jbAdd;
    @FXML
    private Button jbOnline;
    @FXML
    private ListView<Word> jlWord;
    @FXML
    private WebView wbvMeaning;
    public void loadSuggestList(String value)
    {
        ArrayList<Word> suggestList = DictionaryManagement.getInstance().getDBManager().searchByWord(value);
        if(suggestList.size() == 0){
            System.out.println("size 0");
            return;
        }
        ObservableList<Word> list = FXCollections.observableArrayList(suggestList);
        jlWord.setItems(list);
    }

    @FXML
    //Lấy kết quả khi từ được chọn
    public void selectWord(){
        Word selectedWord = jlWord.getSelectionModel().getSelectedItem();
        System.out.println(selectedWord.getWord_explain());
        wbvMeaning.getEngine().loadContent(selectedWord.getWord_explain());
    }
    /*public void Subbit(ActionEvent e) {
        String a = jtxtSearch.getText();
        Alert show = new Alert(Alert.AlertType.INFORMATION);
        show.setContentText(a);
        show.show();
    }*/
    @FXML
    public void showEditWindow(ActionEvent e)  throws  IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("EditWindow.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Edit Window");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void showAddWindow(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AddWindow.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Add Window");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        jlWord.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        jlWord.getFocusModel().focus(1);
        jtxtSearch.textProperty().addListener((observable, oldValue, newValue) -> loadSuggestList(newValue));
        jtxtSearch.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue && jtxtSearch.getText().equals("")){
                    loadSuggestList("");
                }
            }
        });
        jlWord.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectWord();
            }
        });
        jCBDictType.setItems(FXCollections.observableArrayList("ENG-VIET", "VIET-ENG"));
        jCBDictType.getSelectionModel().selectFirst();
        jCBDictType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                DictionaryManagement.getInstance().setDictType(jCBDictType.getValue());
            }
        });
    }

}
