package bomberman.Entity;


import bomberman.Game;
import javafx.scene.image.Image;

public class Bomb extends FixedObject{
    private static final int TIME_ALIVE = 2000;
    private static Image[][] sprites;
    private boolean isExploded = false;
    private int indexOfSprite = 0;
    private long timeToExplode;
    static {
        sprites = new Image[2][3];
        for (int i = 0; i < 3; i++){
            sprites[0][i] = new Image(Bomb.class.getResource("/sprite/bomb_" + i +".png").toExternalForm());
            sprites[1][i] = new Image(Bomb.class.getResource("/sprite/bomb_exploded_" + i +".png").toExternalForm());
        }
    }

    public Bomb(int xInGrid, int yInGrid) {
        super(xInGrid, yInGrid);
        timeToExplode = System.currentTimeMillis() + TIME_ALIVE;
    }

    @Override
    public void update() {
        indexOfSprite++;
        if(timeToExplode >= System.currentTimeMillis()){
            isExploded = true;
            indexOfSprite = 0;
        }
        gc.drawImage(sprites[isExploded ? 1: 0][indexOfSprite % 3], x, y, width, heigh);
        if(isExploded && indexOfSprite >= 2){
            Game.getInstance().getGoManager().removeObject(this);
        }
    }

}
