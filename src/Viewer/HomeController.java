package Viewer;

import Controller.DictionaryManagement;
import Controller.GoogleTranslator;
import Models.Word;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class HomeController implements Initializable  {
    private String curentWord;
    public static Stage addStage =null;
    public static Stage editStage =null;
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
    private Text txtWord;
    @FXML
    private Text txtPronounce;
    @FXML
    private TextArea txtMeaning;
    @FXML
    private ImageView btnSound;
    public static Stage stage1=null;
    private MediaPlayer mediaPlayer;
    @FXML
    public void playSound(){
        GoogleTranslator gg = new GoogleTranslator();
        Media sound = new Media(gg.getSoundFile(curentWord, GoogleTranslator.Language.en).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }
    @FXML
    public void deleteWord(){
        Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
        deleteAlert.setTitle("Xác nhận");
        deleteAlert.setHeaderText(null);
        deleteAlert.setContentText("Bạn có chắc chắn muốn xóa từ này?");
        Optional<ButtonType> result = deleteAlert.showAndWait();
        if(result.get() == ButtonType.OK){
            DictionaryManagement.getInstance().getDBManager().delete(curentWord);
            loadDefault();
        }
    }
    @FXML
    public void editWord(){
        FXMLLoader editLoader = new FXMLLoader(getClass().getResource("EditWindow.fxml"));
        editLoader.setController(new EditController(jlWord.getSelectionModel().getSelectedItem()));
        Pane root = null;
        try {
            root = editLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        editStage = openNewWindow(editStage, "Sửa từ vựng", root);
        editStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                HomeController.editStage = null;
            }
        });
    }
    private void loadSuggestList(String value)
    {
        ArrayList<Word> suggestList = DictionaryManagement.getInstance().getDBManager().searchByWord(value);
        if(suggestList.size() == 0){
        }
        ObservableList<Word> list = FXCollections.observableArrayList(suggestList);
        jlWord.setItems(list);
    }
    private void loadDefault(){
        DictionaryManagement.getInstance().setDictType(jCBDictType.getValue());
        loadSuggestList(jtxtSearch.getText());
        jlWord.getSelectionModel().selectFirst();
        selectWord();
    }
    @FXML
    public void selectWord(){
        Word selectedWord = jlWord.getSelectionModel().getSelectedItem();
        if(selectedWord != null){
            curentWord = selectedWord.getWord_target();
            txtWord.setText(curentWord);
            txtPronounce.setText("[" + selectedWord.getPronounce() + "]");
            txtMeaning.setText(selectedWord.getWord_explain());
        } else {
            txtWord.setText("");
            txtMeaning.setText("");
            txtPronounce.setText("");
        }
    }
    @FXML
    public void showAddWindow(ActionEvent e) throws IOException {
        Pane root = FXMLLoader.load(getClass().getResource("AddWindow.fxml"));
        addStage = openNewWindow(addStage, "Thêm từ mới", root);
        addStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                HomeController.addStage = null;
            }
        });
    }

    public void ShowGoogleTranslate(ActionEvent event) throws IOException {
        Pane root = FXMLLoader.load(getClass().getResource("GoogleTranslate.fxml"));
        stage1 = openNewWindow(stage1, "Dịch online",  root);
        stage1.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                HomeController.stage1 = null;
            }
        });
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        VBox vBox = new VBox();
        vBox.getChildren().add(new Text("Không tìm thấy từ tương ứng"));
        jlWord.setPlaceholder(vBox);
        jlWord.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        jlWord.getFocusModel().focus(1);
        jlWord.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectWord();
            }
        });

        jtxtSearch.textProperty().addListener((observable, oldValue, newValue) -> loadSuggestList(newValue));
        jtxtSearch.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue && jtxtSearch.getText().equals("")){
                    loadSuggestList("");
                }
            }
        });
        jtxtSearch.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                jtxtSearch.setText("");
            }
        });

        jCBDictType.setItems(FXCollections.observableArrayList("ENG-VIET", "VIET-ENG"));
        jCBDictType.getSelectionModel().selectFirst();
        jCBDictType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                btnSound.setVisible(!btnSound.isVisible());
                loadDefault();
            }
        });

        //loadDefault();
    }
    private Stage openNewWindow(Stage stage, String title, Pane root){
        if(stage == null){
            Scene scene = new Scene(root);
            stage = new Stage();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.resizableProperty().setValue(false);
            stage.show();
        } else {
            stage.requestFocus();
            stage.setIconified(false);
        }
        return stage;
    }
}
