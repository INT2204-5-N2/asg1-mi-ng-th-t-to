package bomberman;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.Queue;

public class Game {
    private GameObjectManager goManager;
    private LevelLoader levelLoader;
    private GameScene gameScene;
    private Queue<KeyEvent> eventQueue = new LinkedList<>();
    private static Game instance;
    private int width, heigh;
    private Game(){

    }
    public void start(Stage primaryStage){
        levelLoader = new LevelLoader();
        levelLoader.loadLevelInfo(1);
        goManager = new GameObjectManager(width, heigh);
        gameScene = new GameScene(goManager);
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
        Group root = new Group();
        root.getChildren().add(gameScene);
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
