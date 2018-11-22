package bomberman;

import bomberman.Entity.*;
import javafx.scene.canvas.Canvas;

import java.util.ArrayList;


public class GameScene extends Canvas {
    public static final int GAMETILE_SIZE = 16;
    private GameObjectManager goManager;
    public GameScene(GameObjectManager gameObjectManager){
        super(400, 200);
        goManager = gameObjectManager;
    }
    public void update(){
        for(int i = 0; i < goManager.getFixedObjectList().length; i++){
            for(int j = 0; j < goManager.getFixedObjectList()[i].length; j++){
                goManager.getFixedObjectList()[i][j].render();
            }

        }
        for(int i = 0; i < goManager.getCharacters().size(); i++){
            goManager.getCharacters().get(i).render();
        }
    }


}
