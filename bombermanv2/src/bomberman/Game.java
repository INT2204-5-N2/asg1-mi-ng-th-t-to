package bomberman;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Game {
    private GameObjectManager goManager;
    private LevelLoader levelLoader;
    private GameScene gameScene;
    private Queue<KeyEvent> eventQueue = new LinkedList<>();
    private static Game instance;
    private int width, heigh;
    private InfoBar infoBar;
    private Game(){

    }
    public void start(Stage primaryStage){
        levelLoader = new LevelLoader();
        levelLoader.loadLevelInfo(1);
        goManager = new GameObjectManager(width, heigh);
        GameScene.GAMETILE_SIZE =((int) Screen.getPrimary().getVisualBounds().getHeight() * 3 / 4) / heigh;
        gameScene = new GameScene(goManager, width, heigh);
        levelLoader.loadGameObject(goManager);
        gameScene.setFocusTraversable(true);
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(eventQueue.isEmpty() || eventQueue.peek().getCode() != event.getCode()){
                    eventQueue.add(event);
                }
            }
        });
        infoBar = new InfoBar();
        AnchorPane root = new AnchorPane();
        Node infoNode = null;
        try {
            FXMLLoader infoLoader = new FXMLLoader(getClass().getResource("InfoBar.fxml"));
            infoLoader.setController(infoBar);
            infoNode = infoLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.getChildren().add(infoNode);
        root.getChildren().add(gameScene);
        infoNode.setLayoutX(0);
        infoNode.setLayoutY(0);
        gameScene.setLayoutX(0);
        gameScene.setLayoutY(infoNode.getBoundsInParent().getHeight());
        AnchorPane.setRightAnchor(infoNode, 0d);
        AnchorPane.setLeftAnchor(infoNode, 0d);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }

    public static Game getInstance(){
        if(instance == null){
            instance = new Game();
        }
        return instance;
    }
    public GameObjectManager getGoManager() {
        return goManager;
    }

    public GameScene getGameScene() {
        return gameScene;
    }

    public void update(){
        gameScene.update();
        infoBar.update();
    }

    public InfoBar getInfoBar() {
        return infoBar;
    }

    public int getWidth() {
        return width;
    }

    public int getHeigh() {
        return heigh;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeigh(int heigh) {
        this.heigh = heigh;
    }

    public Queue<KeyEvent> getEventQueue() {
        return eventQueue;
    }
}
