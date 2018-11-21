package bomberman.Entity;
import bomberman.GameScene;
public abstract class FixedObject extends GameObject {
    private int xInGrid;
    private int yInGrid;
    public FixedObject(int xInGrid, int yInGrid){
        this.xInGrid = xInGrid;
        this.yInGrid = yInGrid;
        x = xInGrid * GameScene.GAMETILE_SIZE;
        y = yInGrid * GameScene.GAMETILE_SIZE;
        width = GameScene.GAMETILE_SIZE;
        heigh = GameScene.GAMETILE_SIZE;
    }

    public int getxInGrid() {
        return xInGrid;
    }

    public int getyInGrid() {
        return yInGrid;
    }
}
