package bomberman;

import bomberman.Sound.SoundPlay;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
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
    private boolean endGame = false;
    private int level;
    private boolean changeLevel = false;
    private int delayFrame = 0;
    private Game(){

    }
    public void start(Stage primaryStage){
        level = 1;
        levelLoader = new LevelLoader();
        levelLoader.loadLevelInfo(level);
        GameScene.GAMETILE_SIZE =((int) Screen.getPrimary().getVisualBounds().getHeight() * 3 / 4) / heigh;
        goManager = new GameObjectManager(width, heigh);
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
        AnchorPane root = new AnchorPane();

        infoBar = new InfoBar();
        Node infoNode = null;
        try {
            FXMLLoader infoLoader = new FXMLLoader(getClass().getResource("InfoBar.fxml"));
            infoLoader.setController(infoBar);
            infoNode = infoLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AnchorPane.setRightAnchor(infoNode, 0d);
        AnchorPane.setLeftAnchor(infoNode, 0d);

        root.getChildren().add(infoNode);
        infoNode.setLayoutX(0);
        infoNode.setLayoutY(0);
        root.getChildren().add(gameScene);
        gameScene.setLayoutY(infoNode.getBoundsInParent().getHeight());

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
        if(endGame){
            gameScene.drawText("GAMEOVER");
            SoundPlay.playSound(SoundPlay.GAME_OVER);
        } else if(changeLevel){
            if(delayFrame <= 1){
                loadLevel(level + 1);
                changeLevel = false;
            } else {
                delayFrame--;
            }
        }
        else {
            gameScene.update();
            infoBar.update();
        }
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

    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }

    public void loadLevel(int level){
        this.level = level;
        levelLoader.loadLevelInfo(level);
        levelLoader.loadGameObject(goManager);
        infoBar.reset();
    }
    public void loadNextLevel(){
        changeLevel = true;
        delayFrame = 100;
        gameScene.drawText("LEVEL " + level);
    }
}
