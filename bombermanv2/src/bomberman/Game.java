package bomberman;

import bomberman.Entity.Bomber;
import bomberman.Entity.Wall;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Game {
    private GameObjectManager goManager;
    private LevelLoader levelLoader;
    private GameScene gameScene;
    private static Game instance;
    private int width, heigh;
    private Game(Stage primaryStage){
        goManager = new GameObjectManager(width,heigh);
        gameScene = new GameScene(goManager);
        Group root = new Group();
        root.getChildren().add(gameScene);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        goManager.addObject(new Bomber(0,0));
        goManager.addObject(new Wall(1, 2));
    }
    public static Game getInstance(Stage primaryStage){
        if(instance == null){
            instance = new Game(primaryStage);
        }
        return instance;
    }

    public static Game getInstance(){
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
}
