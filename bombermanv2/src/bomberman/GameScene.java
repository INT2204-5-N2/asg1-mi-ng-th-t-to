package bomberman;
import bomberman.Entity.MovableObject;
import javafx.scene.canvas.Canvas;
import javafx.stage.Screen;

import java.util.ArrayList;

public class GameScene extends Canvas {
    //TODO: DONE tính toán lại GAMETILE_SIZE và with, heigh của cửa sổ
    public static final int GAMETILE_SIZE = (int) Screen.getPrimary().getVisualBounds().getWidth()/31;
    private GameObjectManager goManager;
    public GameScene(GameObjectManager gameObjectManager){
        super(Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight());
        goManager = gameObjectManager;
    }
    public void update(){
        getGraphicsContext2D().clearRect(0, 0, getWidth(), getHeight());
        for(int i = 0; i < goManager.getFixedObjectList().length; i++){
            for(int j = 0; j < goManager.getFixedObjectList()[i].length; j++){
                goManager.getFixedObjectList()[i][j].update();
            }
        }
        for(int i = 0; i < goManager.getCharacters().size(); i++){
            goManager.getCharacters().get(i).update();
        }
        checkCharacterCollide();
    }

    public void checkCharacterCollide(){
        ArrayList<MovableObject> characterList = goManager.getCharacters();
        for (int i = 0; i < characterList.size(); i++){
            for (int j = i + 1; j < characterList.size(); j++){
                characterList.get(i).processCollideWithOtherCharacter(characterList.get(j));
            }
        }
    }


}
