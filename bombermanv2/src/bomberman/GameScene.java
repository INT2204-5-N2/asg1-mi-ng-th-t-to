package bomberman;
import javafx.scene.canvas.Canvas;
import javafx.stage.Screen;

public class GameScene extends Canvas {
    //TODO: tính toán lại GAMETILE_SIZE và with, heigh của cửa sổ
    public static int GAMETILE_SIZE = 40;
    private int gridWidth;
    private int gridHeigh;
    private GameObjectManager goManager;
    public GameScene(GameObjectManager gameObjectManager, int width, int heigh){
        super(width * GAMETILE_SIZE, heigh * GAMETILE_SIZE);
        goManager = gameObjectManager;
        gridWidth = width;
        gridHeigh = heigh;
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
    }


}
