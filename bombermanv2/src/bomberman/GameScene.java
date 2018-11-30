package bomberman;
import bomberman.Entity.MovableObject;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;

public class GameScene extends Canvas {
    //TODO: DONE tính toán lại GAMETILE_SIZE và with, heigh của cửa sổ
    public static int GAMETILE_SIZE;
    private GameObjectManager goManager;
    public GameScene(GameObjectManager gameObjectManager, int w, int h){
        super(w * GAMETILE_SIZE, h * GAMETILE_SIZE);
        goManager = gameObjectManager;
    }
    public void update(){
        getGraphicsContext2D().clearRect(0, 0, getWidth(), getHeight());
        for(int i = 0; i < goManager.getFixedObjectList().length; i++){
            for(int j = 0; j < goManager.getFixedObjectList()[i].length; j++){
                goManager.getFixedObjectList()[i][j].getLast().update();
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

    public void drawText(String text){
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0, getWidth(), getHeight());
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFill(Color.WHITE);
        gc.setFont(new Font(getHeight() / 10));
        gc.fillText(text, getWidth() / 2, getHeight()/2);
    }
}
