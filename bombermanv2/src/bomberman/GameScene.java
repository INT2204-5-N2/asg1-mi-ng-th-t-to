package bomberman;
import bomberman.Entity.Flame;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;

import java.util.Queue;

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
                //System.out.print(goManager.getFixedObjectList()[i][j].getClass().toString());
                goManager.getFixedObjectList()[i][j].update();
            }
            //System.out.println("");
        }
        System.out.println(goManager.getFixedObjectList()[1][2].getClass().toString());
        for(int i = 0; i < goManager.getCharacters().size(); i++){
            goManager.getCharacters().get(i).update();
        }
    }


}
