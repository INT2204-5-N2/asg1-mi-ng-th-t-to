package Viewer;

import Controller.DictionaryManagement;
import Controller.GoogleTranslation;
import Models.Word;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeController implements Initializable  {
    private String curentWord;
    private Voice voice;
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
    private WebView tabMeaning;
    @FXML
    private ImageView btnSound;
    @FXML
    public void playSound(){
        GoogleTranslation gg = new GoogleTranslation();
        Media sound = new Media(gg.getSoundFile(curentWord, GoogleTranslation.Language.en).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        //voice.speak(curentWord);
    }
    @FXML
    public void deleteWord(){
        DictionaryManagement.getInstance().getDBManager().delete(curentWord);
        loadDefault();
    }
    public void loadSuggestList(String value)
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
    //Lấy kết quả khi từ được chọn
    public void selectWord(){
        Word selectedWord = jlWord.getSelectionModel().getSelectedItem();
        curentWord = selectedWord.getWord_target();
        tabMeaning.getEngine().loadContent(selectedWord.getWord_explain());
    }
    public static Stage stage =null;
    @FXML
    public void showEditWindow(ActionEvent e)  throws  IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("EditWindow.fxml"));
        Scene scene = new Scene(root);
        if(stage==null)
        {
            stage=new Stage();
            stage.setTitle("Edit Window");
            stage.setScene(scene);
            stage.show();
        }

    }
    @FXML
    public void showAddWindow(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AddWindow.fxml"));
        Scene scene = new Scene(root);
        {
            if(stage==null)
            {
                stage = new Stage();
                stage.setTitle("Add Window");
                stage.setScene(scene);
                stage.show();
            }
        }

    }
    public static Stage stage1=null;
    public void ShowGoogleTranslate(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GoogleTranslate.fxml"));
        Scene scene = new Scene(root);
        {
            if(stage1==null)
            {
                stage1 = new Stage();
                stage1.setTitle("Google Search");
                stage1.setScene(scene);
                stage1.show();
            }
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        if(VoiceManager.getInstance().contains("kevin16")){
            voice = VoiceManager.getInstance().getVoice("kevin");
            if(voice != null){
                voice.allocate();
                voice.setVolume(4.0f);
                voice.setRate(150);
                voice.setPitch(150);
            }
        } else {
            System.out.println("NO name available");
        }

        jlWord.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        jlWord.getFocusModel().focus(1);
        VBox vBox = new VBox();
        vBox.getChildren().add(new Text("No matching word"));
        //vBox.getChildren().add(jbOnline);
        jlWord.setPlaceholder(vBox);
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
                btnSound.setVisible(!btnSound.isVisible());
                loadDefault();
            }
        });
    }

}
