package Viewer;

import Controller.DatabaseManagement;
import Models.Dictionary;
import Models.Word;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeController implements Initializable  {


    @FXML
    private Button jbEdit;
    @FXML
    private TextArea jtaMeaning;
    @FXML
    private TextField jtxtSearch;
    @FXML
    private Button jbAdd;
    @FXML
    private Button jbOnline;
    @FXML
    private ListView <Word> jlWord=new ListView<>();

    public ArrayList<Word> listWord=new ArrayList<>();
    ObservableList list=FXCollections.observableArrayList();
    private DatabaseManagement dbManager = new DatabaseManagement(Dictionary.DICT_PATH);
    public void LoadData()
    {
        //list.removeAll(list);
        String a="thanh";
        String b="nam";
        String c="Nhung";
        String d="Hai";
        list.addAll(a,b,c,d);
        //jlWord.setItems(list);
        jlWord.getItems().addAll(listWord);

//        jlWord.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//        listWord=dbManager.showAllWords(Dictionary.DictType.ENG2VIET);
//        ObservableList<Word> list= FXCollections.observableArrayList();
//        //jlWord.setItems(list);
//        jlWord.getItems().addAll(list);


//        jlWord.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//        jlWord.getFocusModel().focus(1);
//        //Bắt sự kiện thay đổi text và load lên listView
//        ArrayList<Word> suggestList = dbManager.showAllWords(Dictionary.DictType.ENG2VIET);
//        jlWord.setItems(FXCollections.observableArrayList(suggestList));
//        System.out.println(suggestList.get(0).getWord_target() + " " + suggestList.get(0).getWord_explain());
//        //Cái visible này của ng ko cần
//        jlWord.setVisible(true);
    }
    @FXML
    //Lấy kết quả khi từ được chọn
    public void selectWord(){
        Word selectedWord = jlWord.getSelectionModel().getSelectedItem();
        jlWord.setVisible(false);
        jtxtSearch.setText(selectedWord.getWord_explain());
    }
    public void Subbit(ActionEvent e) {
        String a = jtxtSearch.getText();
        Alert show = new Alert(Alert.AlertType.INFORMATION);
        show.setContentText(a);
        show.show();
    }
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
        /*Stage stage=(Stage) ((Node)e.getSource()).getScene().getWindow();
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("AddWindow.fxml"));
        Parent ShowAdd=loader.load();
        Scene scene1=new Scene(ShowAdd);
        stage.setScene(scene1);*/
        Parent root = FXMLLoader.load(getClass().getResource("AddWindow.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Add Window");
        stage.setScene(scene);
        stage.show();
        //jlWord.get
    }
    public void jtxtSearchClick(MouseEvent event)
    {
        jtxtSearch.setText("");
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoadData();
    }

}
